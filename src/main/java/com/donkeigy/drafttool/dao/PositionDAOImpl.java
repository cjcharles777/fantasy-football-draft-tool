/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.donkeigy.drafttool.dao;


import com.donkeigy.drafttool.objects.hibernate.Position;
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
@Repository("positionDAO")
@Transactional
public class PositionDAOImpl implements PositionDAO
{
        private HibernateTemplate hibernateTemplate;
    
    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        hibernateTemplate = new HibernateTemplate(sessionFactory);
    }
    
    @Transactional(readOnly = false)
    @Override
    public void savePosition(Position p)
    {
         hibernateTemplate.saveOrUpdate(p);
    }

    @Transactional(readOnly = false)
    @Override
    public void savePositions(List<Position> listN) {
         hibernateTemplate.saveOrUpdateAll(listN);
    }

    @Override
    public List<Position> getPositions() {
          return (List<Position>) hibernateTemplate.find("from "
                + Position.class.getName());
    }

    @Override
    public Position gePositionById(int positionId) 
    {
        return hibernateTemplate.get(Position.class, positionId);
    }

    @Override
    @Transactional(readOnly = false)
    public void deletePosition(Position p) {
        hibernateTemplate.delete(p);
    }

    @Override
    @Transactional(readOnly = false)
    public void clearPositions() {
         hibernateTemplate.deleteAll(hibernateTemplate.loadAll(Position.class));
    }
}
