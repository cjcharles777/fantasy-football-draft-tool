package com.donkeigy.drafttool.util.service;

import com.donkeigy.drafttool.dao.PlayersDAO;
import com.donkeigy.drafttool.objects.adp.FantasyFootballADP;
import com.donkeigy.drafttool.objects.hibernate.Player;
import com.donkeigy.drafttool.objects.players.FantasyFootBallPlayer;

import java.util.List;
import java.util.Map;

/**
 * Created by cedric on 8/22/14.
 */
public abstract class FFDataLoad
{
    protected List<FantasyFootBallPlayer> playerList;
    protected Map<String, Player> playerIDMap;
    protected Map<String, FantasyFootballADP> adpMap;
    protected PlayersDAO playersDAO;

    public FFDataLoad(PlayersDAO playersDAO)
    {
        this.playersDAO = playersDAO;
        loadData();
    }

    public List<FantasyFootBallPlayer> getPlayerList() {
        return playerList;
    }

    public void setPlayerList(List<FantasyFootBallPlayer> playerList) {
        this.playerList = playerList;
    }

    public Map<String, Player> getPlayerIDMap() {
        return playerIDMap;
    }

    public void setPlayerIDMap(Map<String, Player> playerIDMap) {
        this.playerIDMap = playerIDMap;
    }

    public Map<String, FantasyFootballADP> getAdpMap() {
        return adpMap;
    }

    public void setAdpMap(Map<String, FantasyFootballADP> adpMap) {
        this.adpMap = adpMap;
    }

    protected abstract void loadData(); // load Data into Map and List
}
