/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.donkeigy.drafttool.dao;

import com.donkeigy.drafttool.objects.hibernate.WeeklyStat;

import java.util.List;

/**
 *
 * @author cedric
 */
public interface WeeklyStatsDAO 
{
    public void saveWeeklyStat(WeeklyStat sc);
    public void saveWeeklyStats(List<WeeklyStat> listS);
    public List<WeeklyStat> getAllWeeklyStats();
    public WeeklyStat getWeeklyStatById(int weeklyStatId);
    public void deleteWeeklyStat(WeeklyStat s);
    public void clearWeeklyStat();
}
