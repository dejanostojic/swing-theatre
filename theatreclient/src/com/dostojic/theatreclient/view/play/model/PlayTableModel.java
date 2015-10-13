/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dostojic.theatreclient.view.play.model;

import com.dostojic.common.model.Play;
import com.dostojic.common.transfer.TransferObject;
import com.dostojic.theatreclient.controller.Controller;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
    
    public void addNewPlay(Play p){
        int size = plays.size();
        plays.add(p);
        fireTableRowsInserted(size, size);
    }
    
    public void updatePlay(Play p){
        int counter = 0;
        for (Play pInList : plays){
            if (p.getId() == pInList.getId()){
                pInList = p;
                fireTableRowsUpdated(counter, counter);
                break;
            }
        }
        
        
    }
    
    public void refreshTableData(){
        try {
            TransferObject sto = Controller.getInstance().getPlays();
            if (sto.isOperationSucess()){
                plays = (List<Play>) sto.getData();
                fireTableDataChanged();
            }
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(PlayTableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void setPlays(List<Play> plays) {
        this.plays = plays;
        fireTableDataChanged();
    }
   
   
    
}
