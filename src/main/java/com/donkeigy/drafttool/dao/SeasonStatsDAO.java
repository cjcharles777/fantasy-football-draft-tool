/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.donkeigy.drafttool.dao;

import com.donkeigy.drafttool.objects.hibernate.SeasonStat;

import java.util.List;

/**
 *
 * @author cedric
 */
public interface SeasonStatsDAO 
{
    public void saveSeasonStat(SeasonStat sc);
    public void saveSeasonStats(List<SeasonStat> listS);
    public List<SeasonStat> getAllSeasonStat();
    public SeasonStat getSeasonStatById(int seasonStatId);
    public void deleteSeasonStat(SeasonStat s);
    public void clearSeasonStat();
}
