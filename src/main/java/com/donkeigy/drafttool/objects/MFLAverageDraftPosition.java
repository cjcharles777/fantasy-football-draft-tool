package com.donkeigy.drafttool.objects;

/**
 * Created by cedric on 8/13/14.
 */
public class MFLAverageDraftPosition
{
    String  minPick;
    String
            maxPick;
    String draftsSelectedIn;
    String id;
    String averagePick;

    public String getMinPick() {
        return minPick;
    }

    public void setMinPick(String minPick) {
        this.minPick = minPick;
    }

    public String getMaxPick() {
        return maxPick;
    }

    public void setMaxPick(String maxPick) {
        this.maxPick = maxPick;
    }

    public String getDraftsSelectedIn() {
        return draftsSelectedIn;
    }

    public void setDraftsSelectedIn(String draftsSelectedIn) {
        this.draftsSelectedIn = draftsSelectedIn;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAveragePick() {
        return averagePick;
    }

    public void setAveragePick(String averagePick) {
        this.averagePick = averagePick;
    }
}
