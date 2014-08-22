/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.donkeigy.drafttool.dao;

import com.donkeigy.drafttool.objects.hibernate.Name;
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
@Repository("nameDAO")
@Transactional
public class NameDAOImpl implements NameDAO 
{

    private HibernateTemplate hibernateTemplate;
    
    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        hibernateTemplate = new HibernateTemplate(sessionFactory);
    }
    
    @Transactional(readOnly = false)
    @Override
    public void saveName(Name n) 
    {
         hibernateTemplate.saveOrUpdate(n);
    }

    @Transactional(readOnly = false)
    @Override
    public void saveNames(List<Name> listN) {
         hibernateTemplate.saveOrUpdateAll(listN);
    }

    @Override
    public List<Name> getNames() {
          return (List<Name>) hibernateTemplate.find("from "
                + Name.class.getName());
    }

    @Override
    public Name geNameById(int nameId) 
    {
        return hibernateTemplate.get(Name.class, nameId);
    }

    @Override
    @Transactional(readOnly = false)
    public void deleteName(Name n) {
        hibernateTemplate.delete(n);
    }

    @Override
    @Transactional(readOnly = false)
    public void clearNames() {
         hibernateTemplate.deleteAll(hibernateTemplate.loadAll(Name.class));
    }
    
}
