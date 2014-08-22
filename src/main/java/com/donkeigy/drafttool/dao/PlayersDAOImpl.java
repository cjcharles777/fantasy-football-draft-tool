/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.donkeigy.drafttool.dao;

import com.donkeigy.drafttool.objects.hibernate.Player;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 *
 * @author cedric
 */

@Repository("PlayersDAO")
@Transactional
public class PlayersDAOImpl implements PlayersDAO
{
    private HibernateTemplate hibernateTemplate;
 
    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        hibernateTemplate = new HibernateTemplate(sessionFactory);
    }
    
    @Transactional(readOnly = false)
    public void savePlayer(Player player) {
        hibernateTemplate.saveOrUpdate(player);
    }
        
    @Transactional(readOnly = false)
    public void savePlayers(List<Player> players) 
    {
        hibernateTemplate.saveOrUpdateAll(players);
    }

    
    public List<Player> getAllPlayers() {
         return (List<Player>) hibernateTemplate.find("from "
                + Player.class.getName());
    }
    
    public List<Player> getPlayers(int firstResult, int maxResults) 
    {
       DetachedCriteria criteria;
       criteria =  DetachedCriteria.forClass(Player.class);
       criteria.addOrder(Order.asc("player_id"));
       return (List<Player>) hibernateTemplate.findByCriteria(criteria, firstResult, maxResults);
      
    



    }
    
    public List<Player> getPlayers(Player p)
    {
        return (List<Player>) hibernateTemplate.findByCriteria(
        DetachedCriteria.forClass(Player.class)
                .add(Example.create(p))
                .createCriteria("name")
                .add(Example.create(p.getName())));
    
    }

    @SuppressWarnings("unchecked")
    public Player getPlayerById(int playerId) {
        return hibernateTemplate.get(Player.class, playerId);
    }
    
    @SuppressWarnings("unchecked")
    public Player getPlayerbyYahooId(int yahooId)
    {
        Player result = null;
        
        List<Player> tempList =  (List<Player>) hibernateTemplate.findByCriteria(
        DetachedCriteria.forClass(Player.class)
        .add(Restrictions.eq("player_id", new Integer(yahooId).toString())));
        
        if (tempList != null && tempList.size() > 0)
        {
            result = tempList.get(0);
        }
        else
        {
            result = null;
        }
        return result;
    }
    
    @Transactional(readOnly = false)
    public void deletePlayer(Player player) {
       hibernateTemplate.delete(player);
    }
    
    @Transactional(readOnly = false)
    public void clearPlayers() 
    {
       hibernateTemplate.deleteAll(hibernateTemplate.loadAll(Player.class));
    }



}
