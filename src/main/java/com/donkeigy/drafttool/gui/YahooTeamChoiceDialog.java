package com.donkeigy.drafttool.gui;

import com.donkeigy.drafttool.engine.DraftEngine;
import com.donkeigy.drafttool.objects.yahoo.league.YahooLeague;

import javax.swing.*;
import java.awt.event.*;
import java.util.List;

public class YahooTeamChoiceDialog extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JComboBox comboBox1;
    private JEditorPane instructionPane1;
    private DraftEngine draftEngine;

    public YahooTeamChoiceDialog(List<YahooLeague> leagues, DraftEngine draftEngine)
    {
        this.draftEngine = draftEngine;
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);
        for(YahooLeague league : leagues)
        {
            comboBox1.addItem(league);
        }

        instructionPane1.setEditable(false);//so its not editable
        instructionPane1.setOpaque(false);//so we dont see whit background

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

// call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

// call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    private void onOK() {
        YahooLeague league = (YahooLeague)comboBox1.getSelectedItem();
        draftEngine.loadYahooLeauge(league);
        dispose();
    }

    private void onCancel() {
// add your code here if necessary
        dispose();
    }
}
