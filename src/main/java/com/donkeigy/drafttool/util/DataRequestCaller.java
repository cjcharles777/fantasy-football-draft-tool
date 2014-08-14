/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.donkeigy.drafttool.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 *
 * @author DMDD
 */
public class DataRequestCaller {
    
    public static String requestData(String requestURL, String method)
    {
        
        try
        {
            URL url = new URL(requestURL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod(method);
            conn.setRequestProperty("Accept", "application/json");
            if (conn.getResponseCode() != 200) 
            {
                    throw new RuntimeException("Failed : HTTP error code : "
                                    + conn.getResponseCode());
            }

            
        
            BufferedReader br = new BufferedReader(new InputStreamReader(
                    (conn.getInputStream())));

            String output;
            String result = "";
            //System.out.println("Output from Server .... \n");
            while ((output = br.readLine()) != null) {
                  result += output;  
            }

            conn.disconnect();
            return result;
        }
        catch (Exception e)
        {
          return null;
        }
        
    
    }
    
}
