/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.donkeigy.drafttool.dao;

import com.donkeigy.drafttool.objects.hibernate.SeasonStat;
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
@Repository("SeasonStatsDAO")
@Transactional
public class SeasonStatsDAOImpl implements SeasonStatsDAO
{
     private HibernateTemplate hibernateTemplate;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) 
    {
        hibernateTemplate = new HibernateTemplate(sessionFactory);
    }

    @Transactional(readOnly = false)
    public void saveSeasonStat(SeasonStat ss) {
          hibernateTemplate.saveOrUpdate(ss);
    }

    @Transactional(readOnly = false)
    public void saveSeasonStats(List<SeasonStat> listS) {
          hibernateTemplate.saveOrUpdateAll(listS);
    }

    public List<SeasonStat> getAllSeasonStat() {
        return (List<SeasonStat>) hibernateTemplate.find("from "
                + SeasonStat.class.getName());
    }

     @SuppressWarnings("unchecked")
    public SeasonStat getSeasonStatById(int seasonStatId) 
    {
        return hibernateTemplate.get(SeasonStat.class, seasonStatId);
    }

    @Transactional(readOnly = false)
    public void deleteSeasonStat(SeasonStat ss) 
    {
        hibernateTemplate.delete(ss);
    }

    @Transactional(readOnly = false)
    public void clearSeasonStat() 
    {
        hibernateTemplate.deleteAll(hibernateTemplate.loadAll(SeasonStat.class));
    }
    
}
