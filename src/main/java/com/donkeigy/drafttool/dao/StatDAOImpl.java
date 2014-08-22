/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.donkeigy.drafttool.dao;

import com.donkeigy.drafttool.objects.hibernate.Stat;
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

@Repository("StatDAOImpl")
@Transactional
public class StatDAOImpl implements StatDAO
{

    
    private HibernateTemplate hibernateTemplate;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) 
    {
        hibernateTemplate = new HibernateTemplate(sessionFactory);
    }
    
    @Transactional(readOnly = false)
    public void saveStat(Stat s) 
    {
        hibernateTemplate.saveOrUpdate(s);
    }
    
    @Transactional(readOnly = false)
    public void saveStats(List<Stat> listS) {
        hibernateTemplate.saveOrUpdateAll(listS);
    }

    public List<Stat> getAllStat() 
    {
          return (List<Stat>) hibernateTemplate.find("from "
                + Stat.class.getName());
    }

    @SuppressWarnings("unchecked")
    public Stat getStatById(int statId) {
       return hibernateTemplate.get(Stat.class, statId);
    }

    @Transactional(readOnly = false)
    public void deleteStat(Stat s) 
    {
        hibernateTemplate.delete(s);
    }

    @Transactional(readOnly = false)
    public void clearStats() {
        hibernateTemplate.deleteAll(hibernateTemplate.loadAll(Stat.class));
    }
    
}
