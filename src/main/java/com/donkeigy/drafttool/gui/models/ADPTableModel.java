package com.donkeigy.drafttool.gui.models;

import com.donkeigy.drafttool.objects.MFLAverageDraftPosition;
import com.donkeigy.drafttool.objects.MFLPlayer;

import javax.swing.table.AbstractTableModel;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * Created by cedric on 8/14/14.
 */
public class ADPTableModel extends AbstractTableModel
{
    private List<MFLPlayer> playerList;
    private Map<String,MFLAverageDraftPosition> adpMap;

    private String[] columnNames = {"Player", "Team", "Position", "Avg. Pick", "Min. Pick", "Max Pick"};
    private Class[] columnClasses = {String.class, String.class, String.class, Double.class, Integer.class, Integer.class};
    public ADPTableModel(List<MFLPlayer> playerList, Map<String, MFLAverageDraftPosition> adpMap) {
        this.playerList = playerList;
        this.adpMap = adpMap;
    }

    @Override
    public int getRowCount() {
        return playerList.size();
    }

    @Override
    public int getColumnCount() {
        return 6;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        MFLPlayer player = playerList.get(rowIndex);
        MFLAverageDraftPosition adp = adpMap.get(player.getId());
        try
        {
            switch (columnIndex)
            {
                case 0: return player.getName();

                case 1: return player.getTeam();

                case 2: return player.getPosition();

                case 3: return new BigDecimal(adp.getAveragePick()).doubleValue(); // casting into numerical value for sorting

                case 4: return new BigDecimal(adp.getMinPick()).intValue();

                case 5: return new BigDecimal(adp.getMaxPick()).intValue();

                default: return null;

            }
        }
        catch (NullPointerException e)
        {
            //System.err.println("");
            return null;
        }

    }

    @Override
    public String getColumnName(int column)
    {
        return columnNames[column]; // get list sorted by draft order
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return columnClasses[columnIndex];
    }
}
