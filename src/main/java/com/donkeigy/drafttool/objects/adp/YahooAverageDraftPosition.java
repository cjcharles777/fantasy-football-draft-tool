package com.donkeigy.drafttool.objects.adp;

import java.math.BigDecimal;

/**
 * Created by cedric on 9/5/14.
 */
public class YahooAverageDraftPosition extends FantasyFootballADP
{
    private BigDecimal adp;
    private BigDecimal percentageOfDrafts;


    public void setAdp(BigDecimal adp) {
        this.adp = adp;
    }

    @Override
    public BigDecimal getPrecentageOfDrafts() {
        return percentageOfDrafts;
    }

    public void setPercentageOfDrafts(BigDecimal percentageOfDrafts) {
        this.percentageOfDrafts = percentageOfDrafts;
    }

    @Override
    public BigDecimal getAdp() {
        return adp;
    }



}
