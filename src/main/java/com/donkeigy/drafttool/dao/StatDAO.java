/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.donkeigy.drafttool.dao;

import com.donkeigy.drafttool.objects.hibernate.Stat;

import java.util.List;

/**
 *
 * @author cedric
 */
public interface StatDAO 
{
    public void saveStat(Stat sc);
    public void saveStats(List<Stat> listS);
    public List<Stat> getAllStat();
    public Stat getStatById(int statId);
    public void deleteStat(Stat s);
    public void clearStats();
}
