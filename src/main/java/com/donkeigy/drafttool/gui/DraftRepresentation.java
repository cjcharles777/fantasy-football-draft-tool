package com.donkeigy.drafttool.gui;

import com.donkeigy.drafttool.engine.DraftEngine;
import com.donkeigy.drafttool.engine.exception.DraftIsCompleteException;
import com.donkeigy.drafttool.engine.exception.PlayerIsUndraftableException;
import com.donkeigy.drafttool.objects.adp.MFLAverageDraftPosition;
import com.donkeigy.drafttool.objects.players.MFLPlayer;

import javax.swing.*;

import com.donkeigy.drafttool.objects.yahoo.league.YahooLeague;
import com.donkeigy.drafttool.service.YahooDataService;
import com.donkeigy.drafttool.util.OAuthConnection;
import org.jdesktop.swingx.JXTable;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;
import org.springframework.context.ApplicationContext;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS;

/**
 * Created by cedric on 8/13/14.
 */
public class DraftRepresentation extends JFrame
{
    private JTable table1;
    private JXTable DraftJXTable;
    private JScrollPane scrollPane;


    private List<MFLPlayer> playerList;
    private Map<String, MFLAverageDraftPosition> averageDraftPositionMap;
    private JPanel      panel;
    private JTextField textField1;
    private JButton button1;
    private JTable table2;
    private JXTable ADPJXTable;
    private JPanel draftInputTextPanel;
    private JPanel draftInputAcceptPanel;
    private JPanel draftBoardPanel;
    private JPanel draftPanel;
    private JPanel draftInputPanel;
    private JPanel playerADPPanel;
    private JPanel loadPanel;
    private JButton loadYahooButton;
    private Map<String, MFLPlayer> playerNames;
    private DraftEngine draftEngine;
    private ApplicationContext applicationContext;
    private OAuthConnection conn;
    private YahooDataService yahooDataService;

    private static final String COMMIT_ACTION = "commit";

    public DraftRepresentation (String title, ApplicationContext applicationContext) throws HeadlessException
    {
        super(title);
        this.applicationContext = applicationContext;
        conn = applicationContext.getBean(OAuthConnection.class);
        yahooDataService = applicationContext.getBean(YahooDataService.class);
        playerNames = new HashMap<String, MFLPlayer>();






        draftEngine = new DraftEngine(new MFLPlayer[10][12], DraftJXTable, ADPJXTable,applicationContext);
        draftEngine.init();


        button1.setActionCommand("DRAFT_PLAYER");
        loadYahooButton.setActionCommand("LOAD_YAHOO");
        //AutoCompleteDecorator.decorate(comboBox1);
        // change true to false to disable string restriction
        AutoCompleteDecorator.decorate(textField1, playerList, true); // autocomplete sexiness


        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                String cmd = e.getActionCommand();
                if(cmd.equals("DRAFT_PLAYER")) //action for draft button;
                {
                    String mplPlayerStr = textField1.getText();
                    String [] mplPlayerStrData = mplPlayerStr.split(":");
                    MFLPlayer player = playerNames.get(mplPlayerStrData[1].trim()); // helps retrieve data from the maps
                    try
                    {
                        draftEngine.addToDraft(player);
                    }
                    catch (PlayerIsUndraftableException e1)
                    {
                        e1.printStackTrace();
                    }
                    catch (DraftIsCompleteException e1)
                    {
                        e1.printStackTrace();
                    }

                }
            }
        });



        setContentPane(panel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);


        loadYahooButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String cmd = e.getActionCommand();
                if (cmd.equals("LOAD_YAHOO")) //action for load button;
                {

                    if(!conn.connect())
                    {
                        showYahooOauthDialog();

                    }
                    else
                    {
                        showYahooLoadDialog();
                    }
                }
            }
        });
    }

    private void showYahooOauthDialog()
    {
        YahooOauthDialog dialog = new YahooOauthDialog(conn, this);
        dialog.pack();
        dialog.setVisible(true);
    }

    public void showYahooLoadDialog()
    {
        List<YahooLeague> userLeagues = yahooDataService.getUserLeagues("nfl");
        YahooTeamChoiceDialog dialog = new YahooTeamChoiceDialog(userLeagues, draftEngine);
        dialog.pack();
        dialog.setVisible(true);
    }


    private void createUIComponents() {

        DraftJXTable = new JXTable();
        ADPJXTable = new JXTable();
        table1 = DraftJXTable;
        table2 = ADPJXTable;
        DraftJXTable.setAutoResizeMode(AUTO_RESIZE_ALL_COLUMNS);




    }
}
