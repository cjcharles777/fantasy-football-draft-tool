package com.donkeigy.drafttool.predicates;

import org.jdesktop.swingx.decorator.ComponentAdapter;
import org.jdesktop.swingx.decorator.HighlightPredicate;

import java.awt.*;

/**
 * Created by cedric on 8/14/14.
 */
public class NegativePickHighlightPredicate implements HighlightPredicate
{
    private int currentDraftPick;

    public NegativePickHighlightPredicate(int currentDraftPick) {
        super();
        this.currentDraftPick = currentDraftPick;
    }

    @Override
    public boolean isHighlighted(Component renderer,
                                           ComponentAdapter adapter)
    {

        Object obj  = adapter.getValue();
        Number item;
        if(adapter.getValue() instanceof Number)
        {
            item = (Number) obj;
            return testItem(item);
        }
        return false;


    }
    public boolean testItem(Number item)
    {
        return (item.doubleValue() > currentDraftPick);
    }
    public int getCurrentDraftPick() {
        return currentDraftPick;
    }

    public void setCurrentDraftPick(int currentDraftPick) {
        this.currentDraftPick = currentDraftPick;
    }
}
