package com.donkeigy.drafttool.util;

import com.donkeigy.drafttool.objects.MFLAverageDraftPosition;
import com.donkeigy.drafttool.objects.MFLPlayer;
import com.google.gson.*;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by cedric on 8/13/14.
 */
public class MPLDataLoadUtil
{
    public static void loadData()
    {
        loadPlayers();
        loadADP();

    }

    public static List<MFLPlayer> loadPlayers()
    {
        List<MFLPlayer> playerList = new LinkedList<MFLPlayer>();
        String result = DataRequestCaller.requestData("http://football.myfantasyleague.com/2014/export?TYPE=players&DETAILS=1&JSON=1","GET");
        Gson gson = new GsonBuilder().create();
        JsonObject jsonObject = gson.fromJson(result, JsonObject.class);
        jsonObject = jsonObject.get("players").getAsJsonObject();
        JsonArray playersList = (JsonArray)jsonObject.get("player");
        Iterator<JsonElement> iterator = playersList.iterator();
        while (iterator.hasNext())
        {
           MFLPlayer tmpPlayer = gson.fromJson(iterator.next(), MFLPlayer.class);
           playerList.add(tmpPlayer);
           System.out.println("Added " + tmpPlayer.getName() + " from " + tmpPlayer.getTeam() + "to list");

        }
        System.out.println("There is " + playerList.size() + " players in the list! ");
        return playerList;
    }
    private static void loadADP()
    {
        List<MFLAverageDraftPosition> playerADPList = new LinkedList<MFLAverageDraftPosition>();
        String result = DataRequestCaller.requestData("http://football.myfantasyleague.com/2014/export?TYPE=adp&FRANCHISES=12&IS_MOCK=0&IS_PPR=0&DAYS=7&JSON=1", "GET");
        Gson gson = new GsonBuilder().create();
        JsonObject jsonObject = gson.fromJson(result, JsonObject.class);
        jsonObject = jsonObject.get("adp").getAsJsonObject();
        JsonArray playersList = (JsonArray)jsonObject.get("player");
        Iterator<JsonElement> iterator = playersList.iterator();
        while (iterator.hasNext())
        {
            MFLAverageDraftPosition tmpPlayerADP = gson.fromJson(iterator.next(), MFLAverageDraftPosition.class);
            playerADPList.add(tmpPlayerADP);
            System.out.println("Added "+ tmpPlayerADP.getId()+ " has an average pick of "+ tmpPlayerADP.getAveragePick() + "");

        }
        System.out.println("There is "+ playerADPList.size()+ " player ADPs in the list! ");
    }
}
