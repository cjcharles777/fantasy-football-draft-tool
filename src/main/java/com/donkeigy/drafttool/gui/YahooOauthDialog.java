package com.donkeigy.drafttool.gui;

import com.donkeigy.drafttool.util.OAuthConnection;

import javax.swing.*;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;
import java.awt.*;
import java.awt.event.*;

public class YahooOauthDialog extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField textField1;
    private JEditorPane editorPane1;
    private  OAuthConnection oAuthConnection;
    private DraftRepresentation draftRepresentation;

    public YahooOauthDialog(OAuthConnection oAuthCon, DraftRepresentation draftRepresentation) {
        oAuthConnection = oAuthCon;
        this.draftRepresentation=draftRepresentation;
        String url = oAuthConnection.retrieveAuthorizationUrl();
        String message = "Please go to the following link <a href='" +url+"'>"+url+"</a> and paste your access code below!!";
        editorPane1.setContentType("text/html");//set content as html
        editorPane1.setText(message);

        editorPane1.setEditable(false);//so its not editable
        editorPane1.setOpaque(false);//so we dont see whit background

        editorPane1.addHyperlinkListener(new HyperlinkListener() {
            @Override
            public void hyperlinkUpdate(HyperlinkEvent hle) {
                if (HyperlinkEvent.EventType.ACTIVATED.equals(hle.getEventType())) {
                    System.out.println(hle.getURL());
                    Desktop desktop = Desktop.getDesktop();
                    try {
                        desktop.browse(hle.getURL().toURI());
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

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
        if(oAuthConnection.retrieveAccessToken(textField1.getText()))
        {
            System.out.println("Oauth Success!!");
            draftRepresentation.showYahooLoadDialog();
        }
        else
        {
            System.out.println("Oauth Failure!!");
        }
        dispose();
    }

    private void onCancel() {
// add your code here if necessary
        dispose();
    }
}
