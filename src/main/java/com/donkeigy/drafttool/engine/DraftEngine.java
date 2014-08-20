package com.donkeigy.drafttool.engine;

import com.donkeigy.drafttool.engine.exception.DraftIsCompleteException;
import com.donkeigy.drafttool.engine.exception.PlayerIsUndraftableException;
import com.donkeigy.drafttool.gui.models.ADPTableModel;
import com.donkeigy.drafttool.gui.models.DraftTableModel;
import com.donkeigy.drafttool.objects.MFLAverageDraftPosition;
import com.donkeigy.drafttool.objects.MFLPlayer;
import com.donkeigy.drafttool.predicates.NegativePickHighlightPredicate;
import com.donkeigy.drafttool.predicates.PositivePickHighlightPredicate;
import org.jdesktop.swingx.JXTable;
import org.jdesktop.swingx.decorator.ColorHighlighter;

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
    private MFLPlayer[][] draft;
    private int round =0;
    private int currentTeam = 0;
    private int currentPick=0;
    private DraftTableModel draftTableModel;
    private ADPTableModel adpTableModel;
    private JXTable draftTable;
    private JXTable playerListTable;
    private List<MFLPlayer> playerList;
    private Map<String, MFLAverageDraftPosition> averageDraftPositionMap;
    private NegativePickHighlightPredicate negativePredicate;
    private PositivePickHighlightPredicate positivePredicate;

    public DraftEngine(MFLPlayer[][] draft, JXTable draftTable, JXTable playerListTable)
    {
        this.draft = draft;
        this.draftTable = draftTable;
        this.playerListTable = playerListTable;
    }

    public void init(List<MFLPlayer> playerList, Map<String, MFLAverageDraftPosition> averageDraftPositionMap)
    {
        this.playerList = new LinkedList<MFLPlayer>();
        this.playerList.addAll(playerList);
        this.averageDraftPositionMap = new HashMap<String, MFLAverageDraftPosition>();
        this.averageDraftPositionMap.putAll(averageDraftPositionMap);
        draftTableModel = new DraftTableModel(draft);
        draftTable.setModel(draftTableModel);




        adpTableModel = new ADPTableModel(this.playerList,this.averageDraftPositionMap);

        playerListTable.setModel(adpTableModel);
        draftTable.packTable(0); // had to do this due to intellij GUI Builder
        playerListTable.packTable(0);

        negativePredicate = new NegativePickHighlightPredicate(currentPick);

        ColorHighlighter negHighlighter = new ColorHighlighter(negativePredicate,
                Color.RED,   // background color
                null);       // no change in foreground color

        playerListTable.addHighlighter(negHighlighter);

        positivePredicate = new PositivePickHighlightPredicate(currentPick);

        ColorHighlighter posHighlighter = new ColorHighlighter(positivePredicate,
                Color.GREEN,   // background color
                null);       // no change in foreground color

        playerListTable.addHighlighter(posHighlighter);
    }

    public void addToDraft(MFLPlayer player) throws PlayerIsUndraftableException, DraftIsCompleteException // engine for the draft
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
                negativePredicate.setCurrentDraftPick(currentPick);
                positivePredicate.setCurrentDraftPick(currentPick);


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
}
