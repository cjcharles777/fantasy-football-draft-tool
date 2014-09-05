package com.donkeigy.drafttool.predicates;

import com.donkeigy.drafttool.objects.adp.FantasyFootballADP;
import com.donkeigy.drafttool.objects.hibernate.Player;
import org.jdesktop.swingx.decorator.ComponentAdapter;
import org.jdesktop.swingx.decorator.HighlightPredicate;

import java.awt.*;
import java.util.Map;

/**
 * Created by cedric on 9/4/14.
 */
public class PositiveDraftPositionHighlightPredicate implements HighlightPredicate
{
    private Map<String, FantasyFootballADP> averageDraftPositionMap;

    public PositiveDraftPositionHighlightPredicate( Map<String, FantasyFootballADP> averageDraftPositionMap) {
        super();
        this.averageDraftPositionMap = averageDraftPositionMap;
    }

    @Override
    public boolean isHighlighted(Component renderer,
                                 ComponentAdapter adapter) {

        Object obj = adapter.getValue();
        Player item;
        int pick = (adapter.row * adapter.getColumnCount()) + adapter.column + 1;
        System.out.println("Row : " + adapter.row + " column : " + adapter.column + " columnCount: "+ adapter.getColumnCount()+" pick : " + pick);
        if (adapter.getValue() instanceof Player) {
            item = (Player) obj;
            System.out.println(item.getName().getFull());
            return testItem(item, pick);
        }
        return false;


    }

    public boolean testItem(Player item, int pick) {
        FantasyFootballADP adp = averageDraftPositionMap.get(item.getPlayer_id());
        if(adp != null && adp.getAdp() != null)
        {
        return ( adp.getAdp().doubleValue() < pick);
        }
        else
        {
            return false;
        }
    }
}
