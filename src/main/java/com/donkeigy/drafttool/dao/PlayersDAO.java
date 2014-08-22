/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.donkeigy.drafttool.dao;

import com.donkeigy.drafttool.objects.hibernate.Player;

import java.util.List;

/**
 *
 * @author cedric
 */
public interface PlayersDAO 
{
    public void savePlayer(Player player);
    public void savePlayers(List<Player> players);
    public List<Player> getAllPlayers();
    public List<Player> getPlayers(int firstResult, int maxResults);
    public List<Player> getPlayers(Player p);
    public Player getPlayerById(int playerId);
    public Player getPlayerbyYahooId(int yahooId);
    public void deletePlayer(Player player);
    public void clearPlayers();
}
