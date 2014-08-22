/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.donkeigy.drafttool.dao;


import com.donkeigy.drafttool.objects.hibernate.Position;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 *
 * @author cedric
 */
@Repository("positionDAO")
public interface PositionDAO 
{
    public void savePosition(Position p);
    public void savePositions(List<Position> listP);
    public List<Position> getPositions();
    public Position gePositionById(int positionId);
    public void deletePosition(Position p);
    public void clearPositions();
}
