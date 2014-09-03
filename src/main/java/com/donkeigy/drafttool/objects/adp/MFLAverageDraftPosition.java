package com.donkeigy.drafttool.objects.adp;

import java.math.BigDecimal;

/**
 * Created by cedric on 8/13/14.
 */
public class MFLAverageDraftPosition extends FantasyFootballADP
{
    String minPick;
    String maxPick;
    String draftsSelectedIn;
    String id;
    String averagePick;
    private static int numberOfDrafts = 0;

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

    public void setAveragePick(String averagePick)
    {
        this.averagePick = averagePick;
    }

    public static int getNumberOfDrafts() {
        return numberOfDrafts;
    }

    public static void setNumberOfDrafts(int numberOfDrafts) {
        MFLAverageDraftPosition.numberOfDrafts = numberOfDrafts;
    }

    @Override
    public BigDecimal getAdp() {
        return new BigDecimal(averagePick);
    }

    @Override
    public BigDecimal getPrecentageOfDrafts() {
        if (draftsSelectedIn != null && numberOfDrafts > 0)
        {
            BigDecimal selectedNum = new BigDecimal(draftsSelectedIn);
            BigDecimal totalDraftNum = new BigDecimal(numberOfDrafts);
            return selectedNum.divide(totalDraftNum,2,BigDecimal.ROUND_HALF_UP);
        }
        else
        {
            return new BigDecimal(0);
        }
    }
}
