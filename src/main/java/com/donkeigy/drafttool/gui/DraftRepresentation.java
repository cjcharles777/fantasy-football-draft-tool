package com.donkeigy.drafttool.gui;

import com.donkeigy.drafttool.gui.models.ADPTableModel;
import com.donkeigy.drafttool.gui.models.DraftTableModel;
import com.donkeigy.drafttool.objects.MFLAverageDraftPosition;
import com.donkeigy.drafttool.objects.MFLPlayer;

import javax.swing.*;

import com.donkeigy.drafttool.predicates.NegativePickHighlightPredicate;
import com.donkeigy.drafttool.predicates.PositivePickHighlightPredicate;
import org.jdesktop.swingx.JXTable;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;
import org.jdesktop.swingx.decorator.ColorHighlighter;
import org.jdesktop.swingx.decorator.HighlightPredicate;

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
    private JXTable jXTable1;
    private JScrollPane scrollPane;
    private MFLPlayer[][] draft = new MFLPlayer[10][12];
    private int round =0;
    private int currentTeam = 0;
    private int currentPick=0;
    private List<MFLPlayer> playerList;
    private Map<String, MFLAverageDraftPosition> averageDraftPositionMap;
    private JPanel      panel;
    private JTextField textField1;
    private JButton button1;
    private JTable table2;
    private JXTable jXTable2;
    private JPanel draftInputTextPanel;
    private JPanel draftInputAcceptPanel;
    private JPanel draftBoardPanel;
    private JPanel draftPanel;
    private JPanel draftInputPanel;
    private JPanel playerADPPanel;
    private Map<String, MFLPlayer> playerNames;
    private DraftTableModel draftTableModel;
    private ADPTableModel adpTableModel;

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


        draftTableModel = new DraftTableModel(draft);
        adpTableModel = new ADPTableModel(playerList,averageDraftPositionMap);
        jXTable1.setModel(draftTableModel);
        jXTable2.setModel(adpTableModel);
        jXTable1.packTable(0); // had to do this due to intellij GUI Builder
        jXTable2.packTable(0);

        final NegativePickHighlightPredicate negativePredicate = new NegativePickHighlightPredicate(currentPick);

        ColorHighlighter negHighlighter = new ColorHighlighter(negativePredicate,
                Color.RED,   // background color
                null);       // no change in foreground color

        jXTable2.addHighlighter(negHighlighter);

        final PositivePickHighlightPredicate positivePredicate = new PositivePickHighlightPredicate(currentPick);

        ColorHighlighter posHighlighter = new ColorHighlighter(positivePredicate,
                Color.GREEN,   // background color
                null);       // no change in foreground color

        jXTable2.addHighlighter(posHighlighter);

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
                    addToDraft(player);
                    draftTableModel.fireTableDataChanged();
                    negativePredicate.setCurrentDraftPick(currentPick);
                    positivePredicate.setCurrentDraftPick(currentPick);

                }
            }
        });



        setContentPane(panel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);



    }

    private void addToDraft(MFLPlayer player) // engine for the draft
    {
        if (round < draft.length)
        {
            draft[round][currentTeam] = player;
            currentPick++;
            currentTeam++;
            if(currentTeam >= draft[round].length)
            {
                currentTeam = 0;
                round++;
            }

        }
    }

    private void createUIComponents() {

        jXTable1 = new JXTable();
        jXTable2 = new JXTable();
        table1 = jXTable1;
        table2 = jXTable2;



    }
}
