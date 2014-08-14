package com.donkeigy.drafttool.objects;

/**
 * Created by cedric on 8/13/14.
 */
public class MFLPlayer
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
}
