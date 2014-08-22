/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.donkeigy.drafttool.dao;

import com.donkeigy.drafttool.objects.hibernate.ByeWeek;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 *
 * @author cedric
 */
@Repository("byeWeekDAO")
public interface ByeWeekDAO 
{
    public void saveByeWeek(ByeWeek bw);
    public void saveByeWeeks(List<ByeWeek> listN);
    public List<ByeWeek> getByeWeeks();
    public ByeWeek getByeWeekById(int byeWeekId);
    public void deleteByeWeek(ByeWeek bw);
    public void clearByeWeeks();
}
