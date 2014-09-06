/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dostojic.theatreclient.view.play.model;

import com.dostojic.common.model.Play;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author dejan
 */
public class PlayTableModel extends AbstractTableModel{
    
    List<Play> plays;
    
    String[] columns = new String[] {"Naziv", "O predstavi", "Izmeni", "Obriši"};

    public PlayTableModel(List<Play> plays) {
        this.plays = plays;
    }
    
    public PlayTableModel() {}

    @Override
    public int getRowCount() {
        if (plays == null){
            return 0;
        }
        return plays.size();
    }

    @Override
    public int getColumnCount() {
        return columns.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Play play = plays.get(rowIndex);
        switch (columnIndex){
            case -1 : return play.getId();
            case 0 : return play.getTitle();
            case 1 : return play.getAbout();
            case 2 : return "izmeni";
            case 3 : return "obriši";
                default:return "greška";
        }
    }

    @Override
    public String getColumnName(int column) {
        return columns[column];
    }
    
    
    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        switch(columnIndex){
        case 2: 
        case 3: return true;
            default:return false;
        }
    }
    
    public void deletePlay(long id){
        for (int i=0; i< plays.size(); i++){
            if (plays.get(i).getId() == id){
                plays.remove(i);
//                fireTableDataChanged();
                fireTableRowsDeleted(i, i);
            }
        }
    }
    
    public Play getPlayAtIndex(int index){
        return plays.get(index);
    }
    
}
