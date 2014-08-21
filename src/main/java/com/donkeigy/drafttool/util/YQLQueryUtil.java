/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.donkeigy.drafttool.util;


import com.simpleyql.Api;
import com.simpleyql.ApiFactory;
import com.simpleyql.QueryResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author cedric
 */
@Repository("yqlQueryUtil")
public class YQLQueryUtil 
{
    @Autowired
    private OAuthConnection conn;
    
    private static final String AUTHDATA_SEPARATOR = "&";
    
    public String queryYQL (String query) 
    {
         Api api = ApiFactory.getApiInstance("dj0yJmk9MWNNeHFyMVZneFdFJmQ9WVdrOVNqVm9hSGQ2TXpZbWNHbzlNVEU0TURVM09UYzJNZy0tJnM9Y29uc3VtZXJzZWNyZXQmeD0wYQ--", "9e1bb2700b79696770c9c931b182bf12260eb4e6",
           null,true, null);
         
         
        try 
        { 
            if(conn.isAuthorized())
            {
                Logger.getLogger(YQLQueryUtil.class.getName()).log(Level.SEVERE, query);
                String authdata = conn.getAccessToken().getToken() + AUTHDATA_SEPARATOR+ conn. getAccessToken().getSecret() + AUTHDATA_SEPARATOR + conn.getOauthSessionHandle();
                QueryResult qr = api.query(query, authdata);
                return qr.getText();
            }
            else
            {
                return null;
            }
        }
        catch (Exception ex) 
        {
            Logger.getLogger(YQLQueryUtil.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
}
