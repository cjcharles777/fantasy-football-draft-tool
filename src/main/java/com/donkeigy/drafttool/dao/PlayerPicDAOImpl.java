/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.donkeigy.drafttool.dao;

import com.donkeigy.drafttool.objects.hibernate.PlayerPic;
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
@Repository("PlayerPicDAO")
public class PlayerPicDAOImpl implements PlayerPicDAO
{
    
    private HibernateTemplate hibernateTemplate;
    
    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        hibernateTemplate = new HibernateTemplate(sessionFactory);
    }
    
    @Transactional(readOnly = false)
    @Override
    public void savePlayerPic(PlayerPic n) 
    {
         hibernateTemplate.saveOrUpdate(n);
    }

    @Transactional(readOnly = false)
    @Override
    public void savePlayerPics(List<PlayerPic> listN) {
         hibernateTemplate.saveOrUpdateAll(listN);
    }

    @Override
    public List<PlayerPic> getPlayerPics() {
          return (List<PlayerPic>) hibernateTemplate.find("from "
                + PlayerPic.class.getName());
    }

    @Override
    public PlayerPic gePlayerPicById(int PlayerPicId) 
    {
        return hibernateTemplate.get(PlayerPic.class, PlayerPicId);
    }

    @Override
    @Transactional(readOnly = false)
    public void deletePlayerPic(PlayerPic n) {
        hibernateTemplate.delete(n);
    }

    @Override
    @Transactional(readOnly = false)
    public void clearPlayerPics() {
         hibernateTemplate.deleteAll(hibernateTemplate.loadAll(PlayerPic.class));
    }
    
}
