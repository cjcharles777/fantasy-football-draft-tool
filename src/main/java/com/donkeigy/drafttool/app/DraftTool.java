package com.donkeigy.drafttool.app;

import com.donkeigy.drafttool.gui.DraftRepresentation;
import com.donkeigy.drafttool.util.MPLDataLoadUtil;

/**
 * Created by cedric on 8/13/14.
 */
public class DraftTool
{
    public static void main( String[] args )
    {
        new DraftRepresentation("Test", MPLDataLoadUtil.loadPlayers(), MPLDataLoadUtil.loadADP());
        //MPLDataLoadUtil.loadData();
    }
}
