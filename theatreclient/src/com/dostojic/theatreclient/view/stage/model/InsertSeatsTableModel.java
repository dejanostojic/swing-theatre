/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dostojic.theatreclient.view.stage.model;

import com.dostojic.common.model.Seat;
import java.util.List;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author dejan
 */
public class InsertSeatsTableModel extends DefaultTableModel{

    Seat[][] seatMatrix;

    public InsertSeatsTableModel() {
    }

    public InsertSeatsTableModel(Seat[][] seatArray) {
        this.seatMatrix = seatArray;
    }
    
    public InsertSeatsTableModel(List<Seat> seats){
        throw new UnsupportedOperationException("not implemented yet");
    }
    
    
    @Override
    public int getRowCount() {
        if (seatMatrix == null){
            return 0;
        }
        return seatMatrix.length;
    }

    @Override
    public int getColumnCount() {
        if (seatMatrix != null && seatMatrix[0] != null){
            return seatMatrix[0].length + 1;
        }
        return 0;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
//        if (rowIndex == 0 && columnIndex > 0){
//            return "k"+columnIndex;
//        }else 
        if (columnIndex == 0){
                return "r" + (rowIndex+1);
        }else{
//            return columnIndex;
            return seatMatrix[rowIndex][columnIndex -1].getColumn();
        }
    }

    @Override
    public void setValueAt(Object aValue, int row, int column) {
        
    }

    @Override
    public boolean isCellEditable(int row, int column) {
        return false;
    }
    
    

    @Override
    public String getColumnName(int column) {
        return column == 0 ? "" : "k" + column;
    }
    
    public void setTableDimensions(int rowCount, int columnCount){
        if (rowCount > 100 ){
            throw new IllegalArgumentException("Broj redova mora biti manji od 100");
        }else if (columnCount > 45){
            throw new IllegalArgumentException("Broj kolona mora biti manji od 45");
        }
        seatMatrix = new Seat[rowCount][columnCount];
        //  prvi red prvog niza namerno ostavljen prazan
        for (int r=1; r<=rowCount; r++){
            for (int c=1; c<=columnCount; c++){
                Seat s = new Seat();
                s.setRow(r);
                s.setColumn(c);
                seatMatrix[r-1][c-1] = s;
            }
        }
        fireTableStructureChanged();
        fireTableDataChanged();
    }

    public Seat[][] getSeatMatrix() {
        return seatMatrix;
    }
    
    
    
    
    
}
