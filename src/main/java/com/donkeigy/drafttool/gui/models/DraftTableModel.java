package com.donkeigy.drafttool.gui.models;

import com.donkeigy.drafttool.objects.hibernate.Player;
import com.donkeigy.drafttool.objects.players.MFLPlayer;

import javax.swing.table.AbstractTableModel;

/**
 * Created by cedric on 8/13/14.
 */
public class DraftTableModel extends AbstractTableModel
{
    private Player[][] draft;

    public DraftTableModel(Player[][] draft)
    {
        this.draft = draft;
    }
    public int getColumnCount() {
        return draft[0].length;
    }

    public int getRowCount() {
        return draft.length;
    }

    public Object getValueAt(int row, int col) {
        if(draft[row][col] != null)
        {
            return draft[row][col];
        }
        return null;
    }

    @Override
    public String getColumnName(int column)
    {
        return "Pick " + (column +1); // get list sorted by draft order
    }
}
