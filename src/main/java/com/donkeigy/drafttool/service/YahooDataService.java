package com.donkeigy.drafttool.service;

import com.donkeigy.drafttool.objects.yahoo.league.YahooLeague;
import com.donkeigy.drafttool.objects.yahoo.league.YahooLeagueSettings;
import com.donkeigy.drafttool.objects.yahoo.league.draft.DraftResults;
import com.donkeigy.drafttool.util.YQLQueryUtil;
import com.google.gson.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by cedric on 8/20/14.
 */
public class YahooDataService
{
    @Autowired
    private YQLQueryUtil yqlUitl ;


    public void retriveLeaugePlayers(String leagueid)
    {
        Gson gson = new GsonBuilder().create();

        JsonObject userData;
        JsonObject  results;
        JsonArray leaugeList;
        JsonObject  query;
        String yql = "select * from fantasysports.players where league_key='"+leagueid+"'";
        String response = yqlUitl.queryYQL(yql);

        //Todo: process this query
    }

    public List<YahooLeague> getUserLeagues(String gameKey)
    {

        Gson gson = new GsonBuilder().create();

        JsonObject userData;
        JsonObject  results;
        JsonArray leaugeList;
        JsonObject  query;
        List<YahooLeague>  leagueListResults = new LinkedList<YahooLeague>();
        String ql = "select * from fantasysports.leagues where game_key = '"+ gameKey+"' and use_login=1";
        String response = yqlUitl.queryYQL(ql);
        try
        {
            userData = gson.fromJson(response, JsonObject.class).getAsJsonObject();
            query = userData.get("query").getAsJsonObject(); // query details
            results = query.get("results").getAsJsonObject(); //result details
            leaugeList = (JsonArray) results.get("league"); //result details
            for (JsonElement leauge : leaugeList)
            {
                YahooLeague tempLeauge =  gson.fromJson(leauge, YahooLeague.class);
                leagueListResults.add(tempLeauge);
            }


        }
        catch(Exception e)
        {
            Logger.getLogger(YahooDataService.class.getName()).log(Level.SEVERE, null, e);
        }
        return leagueListResults;

    }

    public YahooLeague getLeague (String leagueid)
    {

        Gson gson = new GsonBuilder().create();
        JsonObject userData;
        JsonObject results;
        JsonObject leaugeList;
        JsonObject query;
        YahooLeague  leagueListResults = new YahooLeague();
        String yql = "select * from fantasysports.leagues where league_key='"+leagueid+"'";
        String response = yqlUitl.queryYQL(yql);
        try
        {
            userData = gson.fromJson(response, JsonObject.class).getAsJsonObject();
            query = userData.get("query").getAsJsonObject(); // query details
            results = query.get("results").getAsJsonObject(); //result details
            leaugeList = results.get("league").getAsJsonObject(); //result details
            YahooLeague tempLeauge = gson.fromJson(leaugeList, YahooLeague.class);
            leagueListResults = tempLeauge;



        }
        catch(Exception e)
        {
            Logger.getLogger(YahooDataService.class.getName()).log(Level.SEVERE, null, e);
        }

        return leagueListResults;
    }
    public YahooLeagueSettings getLeagueSettings (String leagueid)
    {
        Gson gson = new GsonBuilder().create();
        JsonObject userData;
        JsonObject results;
        JsonObject query;
        YahooLeagueSettings  leagueListResults = new YahooLeagueSettings();
        String yql = "select * from fantasysports.leagues.settings where league_key='"+leagueid+"'";
        String response = yqlUitl.queryYQL(yql);
        try
        {
            userData = gson.fromJson(response, JsonObject.class).getAsJsonObject();
            query = userData.get("query").getAsJsonObject(); // query details
            results = query.get("results").getAsJsonObject(); //result details
            JsonObject leaugeMap = results.get("league").getAsJsonObject(); //result details
            JsonObject resultMap = leaugeMap.get("settings").getAsJsonObject(); //result details
            YahooLeagueSettings tempLeauge = gson.fromJson(resultMap, YahooLeagueSettings.class);
            leagueListResults = tempLeauge;



        }
        catch(Exception e)
        {
            Logger.getLogger(YahooDataService.class.getName()).log(Level.SEVERE, null, e);
        }

        return leagueListResults;
    }
    public DraftResults getDraftResults (String leagueid)
    {
        Gson gson = new GsonBuilder().create();
        JsonObject userData;
        JsonObject results;
        JsonObject query;
        DraftResults  leagueListResults = new DraftResults();
        String yql = "select * from fantasysports.draftresults where league_key='"+leagueid+"'";
        String response = yqlUitl.queryYQL(yql);
        try
        {
            userData = gson.fromJson(response, JsonObject.class).getAsJsonObject();
            query = userData.get("query").getAsJsonObject(); // query details
            results = query.get("results").getAsJsonObject(); //result details
            JsonObject leauge = results.get("league").getAsJsonObject(); //result details
            JsonObject map = leauge.get("draft_results").getAsJsonObject(); //result details
            DraftResults tempLeauge = gson.fromJson(map , DraftResults.class);
            leagueListResults = tempLeauge;



        }
        catch(Exception e)
        {
            Logger.getLogger(YahooDataService.class.getName()).log(Level.SEVERE, null, e);
        }

        return leagueListResults;
    }

}
