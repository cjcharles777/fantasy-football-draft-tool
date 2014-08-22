package com.donkeigy.drafttool.objects.players;

import com.donkeigy.drafttool.objects.hibernate.Name;
import com.donkeigy.drafttool.objects.hibernate.Player;

/**
 * Created by cedric on 8/13/14.
 */
public class MFLPlayer extends FantasyFootBallPlayer
{
    String position;
    String name;
    String id;
    String team;
    String twitterUsername;

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTwitterUsername()
    {
        return twitterUsername;
    }

    public void setTwitterUsername(String twitterUsername)
    {
        this.twitterUsername = twitterUsername;
    }

    @Override
    public String toString() {
        return getName() + " : " +getId();
    }

    @Override
    public Player createExampleYahooPlayer()
    {
        Player resultPlayer = new Player();
        Name resultName = new Name();
        String [] playerNameArray = name.split(",");
        if(playerNameArray.length > 1)
        {
            resultName.setLast(playerNameArray[0].trim());
            resultName.setFirst(playerNameArray[1].trim());
        }
        else
        {
            resultName.setFull(name);
        }
        resultPlayer.setName(resultName);
        //resultPlayer.setEditorial_team_abbr(team);
        //resultPlayer.setDisplay_position(position);
        return resultPlayer;

    }
}
