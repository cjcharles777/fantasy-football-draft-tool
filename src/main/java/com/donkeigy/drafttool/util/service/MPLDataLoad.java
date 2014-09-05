package com.donkeigy.drafttool.util.service;

import com.donkeigy.drafttool.dao.PlayersDAO;
import com.donkeigy.drafttool.objects.adp.FantasyFootballADP;
import com.donkeigy.drafttool.objects.adp.MFLAverageDraftPosition;
import com.donkeigy.drafttool.objects.hibernate.Player;
import com.donkeigy.drafttool.objects.players.FantasyFootBallPlayer;
import com.donkeigy.drafttool.objects.players.MFLPlayer;
import com.donkeigy.drafttool.util.DataRequestCaller;
import com.google.gson.*;

import java.util.*;

/**
 * Created by cedric on 8/13/14.
 */
public class MPLDataLoad extends FFDataLoad
{

    public MPLDataLoad(PlayersDAO playersDAO)
    {
        super (playersDAO);
    }
    public void loadData()
    {
       loadPlayers();
       loadADP();

    }

    private void loadPlayers()
    {
        playerList = new LinkedList<FantasyFootBallPlayer>();
        playerIDMap = new HashMap<String, Player>();
        String result = DataRequestCaller.requestData("http://football.myfantasyleague.com/2014/export?TYPE=players&DETAILS=1&JSON=1", "GET");
        Gson gson = new GsonBuilder().create();
        JsonObject jsonObject = gson.fromJson(result, JsonObject.class);
        jsonObject = jsonObject.get("players").getAsJsonObject();
        JsonArray playersList = (JsonArray)jsonObject.get("player");
        Iterator<JsonElement> iterator = playersList.iterator();
        while (iterator.hasNext())
        {
           MFLPlayer tmpPlayer = gson.fromJson(iterator.next(), MFLPlayer.class);
           playerList.add(tmpPlayer);
           Player dbExamplePlayer = tmpPlayer.createExampleYahooPlayer();
           List<Player> dbPlayerList = playersDAO.getPlayers(dbExamplePlayer);
           if(dbPlayerList.size() > 0)
           {
               playerIDMap.put(tmpPlayer.getId(), dbPlayerList.get(0));
               System.out.println("Added " + tmpPlayer.getName() + " from " + tmpPlayer.getTeam() + "to list");
           }
           if(dbPlayerList.size() == 0 && dbExamplePlayer.getDisplay_position().equals("TE"))
           {
               //try without the "TE"
               dbExamplePlayer.setDisplay_position(null);
               List<Player> dbPlayerList2 = playersDAO.getPlayers(dbExamplePlayer);
               if(dbPlayerList2.size() > 0)
               {
                   playerIDMap.put(tmpPlayer.getId(), dbPlayerList2.get(0));
                   System.out.println("Added " + tmpPlayer.getName() + " from " + tmpPlayer.getTeam() + "to list");
               }
               else
               {
                   System.out.println("Can not find specific Player Name" + dbExamplePlayer.getName().getFirst() + " "+ dbExamplePlayer.getName().getLast() );
                   System.out.println(" Position TE:" + dbExamplePlayer.getDisplay_position());
                   System.out.println("Team :  "+ dbExamplePlayer.getEditorial_team_abbr());
                   System.out.println("Database returned List of size " + dbPlayerList.size());
               }

           }
           if( dbPlayerList.size() > 1)
           {

               System.out.println("Can not find specific Player Name" + dbExamplePlayer.getName().getFirst() + " "+ dbExamplePlayer.getName().getLast() );
               System.out.println(" Position :" + dbExamplePlayer.getDisplay_position());
               System.out.println("Team : "+ dbExamplePlayer.getEditorial_team_abbr());
               System.out.println("Database returned List of size " + dbPlayerList.size());
           }


        }
        System.out.println("There is " + playerList.size() + " players in the list! ");

    }
    private void loadADP()
    {
        adpMap = new HashMap<String, FantasyFootballADP>();
        String result = DataRequestCaller.requestData("http://football.myfantasyleague.com/2014/export?TYPE=adp&IS_MOCK=0&IS_KEEPER=0&IS_PPR=0&JSON=1", "GET");
        Gson gson = new GsonBuilder().create();
        JsonObject jsonObject = gson.fromJson(result, JsonObject.class);
        jsonObject = jsonObject.get("adp").getAsJsonObject();
        Integer numberOfDrafts = jsonObject.get("totalDrafts").getAsInt();
        MFLAverageDraftPosition.setNumberOfDrafts(numberOfDrafts);
        JsonArray playersList = (JsonArray)jsonObject.get("player");
        Iterator<JsonElement> iterator = playersList.iterator();
        while (iterator.hasNext())
        {
            MFLAverageDraftPosition tmpPlayerADP = gson.fromJson(iterator.next(), MFLAverageDraftPosition.class);

            Player tmpPlayer = playerIDMap.get(tmpPlayerADP.getId());
            if(tmpPlayer != null)
            {
                adpMap.put(tmpPlayer.getPlayer_id(), tmpPlayerADP);
                System.out.println("Added "+ tmpPlayerADP.getId()+ " has an average pick of "+ tmpPlayerADP.getAveragePick() + "");
            }
            else
            {
                System.out.println("Player Does not exsist in map");
            }



        }
        System.out.println("There is "+ adpMap.size()+ " player ADPs in the list! ");

    }
}
