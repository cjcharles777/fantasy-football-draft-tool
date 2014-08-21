/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.donkeigy.drafttool.dao;


import com.donkeigy.drafttool.objects.hibernate.OAuthToken;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 *
 * @author DMDD
 */
@Repository("OAuthDAO")
@Transactional
public interface OAuthDAO 
{
    public void savePlayer(OAuthToken oat);
    public OAuthToken getOAuthTokenById(int id);
    public void deleteOAuthToken(OAuthToken oat);
    public List<OAuthToken> getAllOAuth();
}
