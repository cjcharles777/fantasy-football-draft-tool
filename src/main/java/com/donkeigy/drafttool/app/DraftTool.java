package com.donkeigy.drafttool.app;

import com.donkeigy.drafttool.gui.DraftRepresentation;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by cedric on 8/13/14.
 */
public class DraftTool
{
    private static final ApplicationContext applicationContext = new ClassPathXmlApplicationContext("app-config.xml");

    public static void main( String[] args )
    {

        new DraftRepresentation("Test", applicationContext);
        //MPLDataLoad.loadData();
    }
}
