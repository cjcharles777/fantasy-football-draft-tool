package com.donkeigy.drafttool.gui.models;

import com.donkeigy.drafttool.objects.adp.FantasyFootballADP;
import com.donkeigy.drafttool.objects.adp.MFLAverageDraftPosition;
import com.donkeigy.drafttool.objects.hibernate.Player;

import javax.swing.table.AbstractTableModel;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * Created by cedric on 8/14/14.
 */
public class ADPTableModel extends AbstractTableModel
{
    private List<Player> playerList;
    private Map<String,FantasyFootballADP> adpMap;

    private String[] columnNames = {"Player", "Team", "Position", "Avg. Pick"};
    private Class[] columnClasses = {String.class, String.class, String.class, Double.class};
    public ADPTableModel(List<Player> playerList, Map<String, FantasyFootballADP> adpMap) {
        this.playerList = playerList;
        this.adpMap = adpMap;
    }

    @Override
    public int getRowCount() {
        return playerList.size();
    }

    @Override
    public int getColumnCount() {
        return 4;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Player player = playerList.get(rowIndex);
        FantasyFootballADP adp = adpMap.get(player.getPlayer_id());
        try
        {
            switch (columnIndex)
            {
                case 0: return player.getName().getFull();

                case 1: return player.getEditorial_team_abbr();

                case 2: return player.getDisplay_position();

                case 3: return adp.getAdp().doubleValue(); // casting into numerical value for sorting


                default: return null;

            }
        }
        catch (NullPointerException e)
        {
            System.err.println("");
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
