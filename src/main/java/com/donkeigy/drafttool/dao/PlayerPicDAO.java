/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.donkeigy.drafttool.dao;

import com.donkeigy.drafttool.objects.hibernate.PlayerPic;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 *
 * @author cedric
 */
@Repository("PlayerPicDAO")
public interface PlayerPicDAO 
{
    public void savePlayerPic(PlayerPic pp);
    public void savePlayerPics(List<PlayerPic> listPP);
    public List<PlayerPic> getPlayerPics();
    public PlayerPic gePlayerPicById(int PlayerPicId);
    public void deletePlayerPic(PlayerPic pp);
    public void clearPlayerPics();
    
}
