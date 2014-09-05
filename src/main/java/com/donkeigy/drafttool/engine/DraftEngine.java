package com.donkeigy.drafttool.engine;

import com.donkeigy.drafttool.dao.PlayersDAO;
import com.donkeigy.drafttool.engine.exception.DraftIsCompleteException;
import com.donkeigy.drafttool.engine.exception.PlayerIsUndraftableException;
import com.donkeigy.drafttool.gui.models.ADPTableModel;
import com.donkeigy.drafttool.gui.models.DraftTableModel;
import com.donkeigy.drafttool.objects.adp.FantasyFootballADP;
import com.donkeigy.drafttool.objects.hibernate.Player;
import com.donkeigy.drafttool.objects.yahoo.league.YahooLeague;
import com.donkeigy.drafttool.objects.yahoo.league.YahooLeagueRosterPosition;
import com.donkeigy.drafttool.objects.yahoo.league.YahooLeagueSettings;
import com.donkeigy.drafttool.objects.yahoo.league.draft.DraftPick;
import com.donkeigy.drafttool.objects.yahoo.league.draft.DraftResults;
import com.donkeigy.drafttool.predicates.NegativeADPHighlightPredicate;
import com.donkeigy.drafttool.predicates.NegativeDraftPositionHighlightPredicate;
import com.donkeigy.drafttool.predicates.PositiveADPHighlightPredicate;
import com.donkeigy.drafttool.predicates.PositiveDraftPositionHighlightPredicate;
import com.donkeigy.drafttool.service.YahooDataService;
import com.donkeigy.drafttool.util.service.MPLDataLoad;
import org.jdesktop.swingx.JXTable;
import org.jdesktop.swingx.decorator.ColorHighlighter;
import org.springframework.context.ApplicationContext;

import java.awt.*;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by cedric on 8/20/14.
 */
public class DraftEngine
{
    private Player[][] draft;
    private int round =0;
    private int currentTeam = 0;
    private int currentPick=0;
    private DraftTableModel draftTableModel;
    private ADPTableModel adpTableModel;
    private JXTable draftTable;
    private JXTable playerListTable;
    private List<Player> playerList;
    private Map<String, FantasyFootballADP> averageDraftPositionMap;
    private NegativeADPHighlightPredicate negativeADPHighlightPredicate;
    private PositiveADPHighlightPredicate positiveADPHighlightPredicate;
    private PositiveDraftPositionHighlightPredicate positivePickHighlightPredicate;
    private NegativeDraftPositionHighlightPredicate negativePickHighlightPredicate;
    private ApplicationContext applicationContext;
    private YahooDataService yahooDataService;
    private PlayersDAO playersDAO;

    public DraftEngine(Player[][] draft, JXTable draftTable, JXTable playerListTable, ApplicationContext applicationContext)
    {
        this.applicationContext = applicationContext;
        this.yahooDataService = applicationContext.getBean(YahooDataService.class);
        this.playersDAO = applicationContext.getBean(PlayersDAO.class);
        this.draft = draft;
        this.draftTable = draftTable;
        this.playerListTable = playerListTable;
    }

    public void init()
    {
        this.playerList = new LinkedList<Player>();
        //this.playerList.addAll(playerList);
        this.averageDraftPositionMap = new HashMap<String, FantasyFootballADP>();
        //this.averageDraftPositionMap.putAll(averageDraftPositionMap);
        draftTableModel = new DraftTableModel(draft);
        draftTable.setModel(draftTableModel);




        adpTableModel = new ADPTableModel(this.playerList,this.averageDraftPositionMap);

        playerListTable.setModel(adpTableModel);
        draftTable.packTable(0); // had to do this due to intellij GUI Builder
        playerListTable.packTable(0);

        negativeADPHighlightPredicate = new NegativeADPHighlightPredicate(currentPick);

        ColorHighlighter negAdpHighlighter = new ColorHighlighter(negativeADPHighlightPredicate,
                Color.RED,   // background color
                null);       // no change in foreground color

        playerListTable.addHighlighter(negAdpHighlighter);

        positiveADPHighlightPredicate = new PositiveADPHighlightPredicate(currentPick);

        ColorHighlighter posAdpHighlighter = new ColorHighlighter(positiveADPHighlightPredicate,
                Color.GREEN,   // background color
                null);       // no change in foreground color

        playerListTable.addHighlighter(posAdpHighlighter);



        negativePickHighlightPredicate = new NegativeDraftPositionHighlightPredicate(this.averageDraftPositionMap);

        ColorHighlighter negativePickHighlighter = new ColorHighlighter(negativePickHighlightPredicate,
                Color.RED,   // background color
                null);       // no change in foreground color

        draftTable.addHighlighter(negativePickHighlighter);

        positivePickHighlightPredicate = new PositiveDraftPositionHighlightPredicate(this.averageDraftPositionMap);

        ColorHighlighter positivePickHighlighter = new ColorHighlighter(positivePickHighlightPredicate,
                Color.GREEN,   // background color
                null);       // no change in foreground color

        draftTable.addHighlighter(positivePickHighlighter);




    }

    public void addToDraft(Player player) throws PlayerIsUndraftableException, DraftIsCompleteException // engine for the draft
    {
        if (playerList.contains(player))
        {
            if (round < draft.length)
            {
                draft[round][currentTeam] = player;
                playerList.remove(player);
                currentPick++;
                currentTeam++;
                if (currentTeam >= draft[round].length) {
                    currentTeam = 0;
                    round++;
                }
                draftTableModel.fireTableDataChanged();
                adpTableModel.fireTableDataChanged();
                negativeADPHighlightPredicate.setCurrentDraftPick(currentPick);
                positiveADPHighlightPredicate.setCurrentDraftPick(currentPick);


            }
            else
            {
                throw new DraftIsCompleteException();
            }
        }
        else
        {
            throw new PlayerIsUndraftableException();
        }
    }
    public void loadYahooLeauge(YahooLeague leauge)
    {

        List<Player> players = yahooDataService.retriveLeaugePlayers(leauge.getLeague_key());
        playersDAO.clearPlayers();
        playersDAO.savePlayers(players);
        MPLDataLoad mplDataLoad = new MPLDataLoad(playersDAO);
        averageDraftPositionMap.putAll(mplDataLoad.getAdpMap());
        this.playerList.addAll(players);
        adpTableModel.fireTableDataChanged();
        playerListTable.packTable(0);

        //start setting up draft board array
        int numTeams = Integer.parseInt(leauge.getNum_teams());
        int rounds = 0;
        YahooLeagueSettings leagueSettings = yahooDataService.getLeagueSettings(leauge.getLeague_key());
        List<YahooLeagueRosterPosition> rosterPositions = leagueSettings.getRoster_positions().getRoster_position();
        for(YahooLeagueRosterPosition rosterPosition: rosterPositions)
        {
            if(!rosterPosition.getPosition().equals("IR"))
            {
                rounds+=Integer.parseInt(rosterPosition.getCount());
            }

        }
        draft = new Player[rounds][numTeams];
        DraftResults draftResults = yahooDataService.getDraftResults(leauge.getLeague_key());
        executeDraftResults(draftResults);
        draftTableModel = new DraftTableModel(draft);
        draftTable.setModel(draftTableModel);
        draftTable.packTable(0);

    }
    private void executeDraftResults(DraftResults results)
    {
        List <DraftPick> pickList =  results.getDraft_result();
        Map<String, Player> playerKeyMap = new HashMap<String, Player>();
        for(Player p :this.playerList)
        {
            playerKeyMap.put(p.getPlayer_key(),p);
        }
        int teams = draft[0].length;
        for(DraftPick pick : pickList)
        {

            int pickNum = Integer.parseInt(pick.getPick());
            System.out.println("Now for round "+((pickNum-1)/teams) +" and pick " + ((pickNum-1)%teams));
            draft[(pickNum-1)/teams][(pickNum-1)%teams] = playerKeyMap.get(pick.getPlayer_key());

            System.out.println("Added "+ pick.getPlayer_key()+" to Draft");
        }
    }
}
