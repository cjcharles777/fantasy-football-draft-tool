package com.donkeigy.drafttool.gui;

import com.donkeigy.drafttool.engine.DraftEngine;
import com.donkeigy.drafttool.engine.exception.DraftIsCompleteException;
import com.donkeigy.drafttool.engine.exception.PlayerIsUndraftableException;
import com.donkeigy.drafttool.objects.MFLAverageDraftPosition;
import com.donkeigy.drafttool.objects.MFLPlayer;

import javax.swing.*;

import org.jdesktop.swingx.JXTable;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    private Map<String, MFLPlayer> playerNames;
    private DraftEngine draftEngine;

    private static final String COMMIT_ACTION = "commit";

    public DraftRepresentation (String title, List<MFLPlayer> playerList, Map<String, MFLAverageDraftPosition> averageDraftPositionMap) throws HeadlessException
    {
        super(title);
        this.playerList = playerList;
        this.averageDraftPositionMap = averageDraftPositionMap;

        playerNames = new HashMap<String, MFLPlayer>();

        for(MFLPlayer player : playerList)
        {
            playerNames.put(player.getId(), player); // prepare player pool
        }




        draftEngine = new DraftEngine(new MFLPlayer[10][12], DraftJXTable, ADPJXTable);
        draftEngine.init(this.playerList, this.averageDraftPositionMap);


        button1.setActionCommand("DRAFT_PLAYER");

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



    }



    private void createUIComponents() {

        DraftJXTable = new JXTable();
        ADPJXTable = new JXTable();
        table1 = DraftJXTable;
        table2 = ADPJXTable;



    }
}
