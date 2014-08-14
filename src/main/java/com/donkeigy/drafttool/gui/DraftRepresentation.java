package com.donkeigy.drafttool.gui;

import com.donkeigy.drafttool.gui.models.DraftTableModel;
import com.donkeigy.drafttool.gui.util.AutoComplete;
import com.donkeigy.drafttool.objects.MFLPlayer;

import javax.swing.*;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;
import org.jdesktop.swingx.combobox.ListComboBoxModel;

import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.MouseAdapter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by cedric on 8/13/14.
 */
public class DraftRepresentation extends JFrame
{
    private JTable table1;
    private JScrollPane scrollPane;
    private MFLPlayer[][] draft = new MFLPlayer[10][10];
    private int round;
    private int currentTeam;
    private List<MFLPlayer> playerList;
    private JPanel      panel;
    private JTextField textField1;
    private JButton button1;
    private Map<String, MFLPlayer> playerNames = new HashMap<String, MFLPlayer>();
    private DraftTableModel dataModel;

    private static final String COMMIT_ACTION = "commit";

    public DraftRepresentation (String title, List<MFLPlayer> playerList) throws HeadlessException
    {
        super(title);
        this.playerList = playerList;

        dataModel = new DraftTableModel(draft);

        for(MFLPlayer player : playerList)
        {
            playerNames.put(player.getId(), player);
        }
        //   Comment this code to add table dynamically
        table1.setModel(dataModel);

        //    Uncomment this code to add table dynamically:
        //table1 = new JTable(dataModel);
        //table1.setPreferredScrollableViewportSize(new Dimension(400, 100));
        //scrollPane.setViewportView(table1);
        button1.setActionCommand("DRAFT_PLAYER");

        //AutoCompleteDecorator.decorate(comboBox1);
        // change true to false to disable string restriction
        AutoCompleteDecorator.decorate(textField1, playerList, true);


        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                String cmd = e.getActionCommand();
                if(cmd.equals("DRAFT_PLAYER"))
                {
                    String mplPlayerStr = textField1.getText();
                    String [] mplPlayerStrData = mplPlayerStr.split(":");
                    MFLPlayer player = playerNames.get(mplPlayerStrData[1].trim());
                    addToDraft(player);
                    dataModel.fireTableDataChanged();

                }
            }
        });

        round = 0;
        currentTeam = 0;

        setContentPane(panel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);



    }

    private void addToDraft(MFLPlayer player)
    {
        if (round < draft.length)
        {
            draft[round][currentTeam] = player;
            currentTeam++;
            if(currentTeam >= draft[round].length)
            {
                currentTeam = 0;
                round++;
            }

        }
    }
}
