/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.donkeigy.drafttool.dao;

import com.donkeigy.drafttool.objects.hibernate.WeeklyStat;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 *
 * @author cedric
 */
@Repository("WeeklyStatsDAO")
@Transactional
public class WeeklyStatsDAOImpl implements WeeklyStatsDAO
{
     private HibernateTemplate hibernateTemplate;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) 
    {
        hibernateTemplate = new HibernateTemplate(sessionFactory);
    }

    @Transactional(readOnly = false)
    public void saveWeeklyStat(WeeklyStat ss) {
          hibernateTemplate.saveOrUpdate(ss);
    }

    @Transactional(readOnly = false)
    public void saveWeeklyStats(List<WeeklyStat> listS) {
          hibernateTemplate.saveOrUpdateAll(listS);
    }

    public List<WeeklyStat> getAllWeeklyStats() {
        return (List<WeeklyStat>) hibernateTemplate.find("from "
                + WeeklyStat.class.getName());
    }

     @SuppressWarnings("unchecked")
    public WeeklyStat getWeeklyStatById(int weeklyStatId) 
    {
        return hibernateTemplate.get(WeeklyStat.class, weeklyStatId);
    }

    @Transactional(readOnly = false)
    public void deleteWeeklyStat(WeeklyStat ss) 
    {
        hibernateTemplate.delete(ss);
    }

    @Transactional(readOnly = false)
    public void clearWeeklyStat() 
    {
        hibernateTemplate.deleteAll(hibernateTemplate.loadAll(WeeklyStat.class));
    }
}