/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.donkeigy.drafttool.dao;

import com.donkeigy.drafttool.objects.hibernate.Name;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 *
 * @author cedric
 */
@Repository("nameDAO")
public interface NameDAO 
{
    public void saveName(Name n);
    public void saveNames(List<Name> listN);
    public List<Name> getNames();
    public Name geNameById(int nameId);
    public void deleteName(Name n);
    public void clearNames();
}
