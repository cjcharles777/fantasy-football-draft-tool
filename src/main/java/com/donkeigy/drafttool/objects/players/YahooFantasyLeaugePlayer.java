package com.donkeigy.drafttool.objects.players;

import com.donkeigy.drafttool.objects.hibernate.Player;

/**
 * Created by cedric on 9/5/14.
 */
public class YahooFantasyLeaugePlayer extends FantasyFootBallPlayer
{
    private Player player;

    public YahooFantasyLeaugePlayer(Player player) {
        this.player = player;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    @Override
    public Player createExampleYahooPlayer() {
        return player;
    }
}
