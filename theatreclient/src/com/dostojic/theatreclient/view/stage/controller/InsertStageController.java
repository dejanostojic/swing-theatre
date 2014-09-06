/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dostojic.theatreclient.view.stage.controller;

import com.dostojic.common.model.Seat;
import com.dostojic.common.model.Stage;
import com.dostojic.common.transfer.TransferObject;
import com.dostojic.theatreclient.controller.Controller;
import com.dostojic.theatreclient.view.stage.model.InsertSeatsTableModel;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;

/**
 *
 * @author dejan
 */
public class InsertStageController {

    public static void changeSeats(JTextField textRows, JTextField textColls, JTable tableSeats) {
        
        try{
            int rows = Integer.valueOf(textRows.getText());
            int colls = Integer.valueOf(textColls.getText());
            if (rows > 0 && colls > 0){
                InsertSeatsTableModel tm = (InsertSeatsTableModel) tableSeats.getModel();
                try{
                    tm.setTableDimensions(rows, colls);
                }catch (IllegalArgumentException e){
                    JOptionPane.showMessageDialog(tableSeats, e.getMessage());
                }
            }else{
                JOptionPane.showMessageDialog(tableSeats, "Broj redova i kolona mora biti pozitivan");
            }
        }catch (NumberFormatException nfe){
            JOptionPane.showMessageDialog(tableSeats, "Unesite numeričke vrednosti za broj redova i kolona");
        }
        
        
    }

    public static void changeSeats(JSpinner spinnerRow, JSpinner spinnerColl, JTable tableSeats) {
        try{
        int rows = (Integer) spinnerRow.getValue();
        int colls = (Integer) spinnerColl.getValue();
            if (rows > 0 && colls > 0){
                InsertSeatsTableModel tm = (InsertSeatsTableModel) tableSeats.getModel();
                try{
                    tm.setTableDimensions(rows, colls);
                }catch (IllegalArgumentException e){
                    JOptionPane.showMessageDialog(tableSeats, e.getMessage());
                }
            }else{
                JOptionPane.showMessageDialog(tableSeats, "Broj redova i kolona mora biti pozitivan");
            }
        }catch (ClassCastException cce){
            JOptionPane.showMessageDialog(tableSeats, "Unesite numeričke vrednosti za broj redova i kolona");
        }
    }

    public static void save(JTextField textStageName, JTable tableSeats)  {
        String stageName = textStageName.getText();
        if (stageName == null || stageName.trim().equals("")) {
//            throw new IllegalArgumentException("Morate uneti naziv scene");
            JOptionPane.showMessageDialog(null, "Morate uneti naziv scene", "Greška pri čuvanju scene", JOptionPane.ERROR_MESSAGE);
            return;
        }

        InsertSeatsTableModel istm = (InsertSeatsTableModel) tableSeats.getModel();
        Seat[][] seats = istm.getSeatMatrix();
        if (seats == null) {
//            throw new Exception("Morate uneti sedišta!");
            JOptionPane.showMessageDialog(null, "Morate uneti sedišta!", "Greška pri čuvanju scene", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Stage stage = new Stage();
        stage.setName(stageName);
        TransferObject sto = Controller.getInstance().saveStageWithSeats(stage, seats);
            if (sto.isOperationSucess()){
                JOptionPane.showMessageDialog(null, sto.getMessage(), "Uspešno sačuvana scena", JOptionPane.INFORMATION_MESSAGE);
                textStageName.setText("");
                tableSeats.removeAll();
            }else{
                JOptionPane.showMessageDialog(null, sto.getMessage(), "Greška pri čuvanju scene", JOptionPane.ERROR_MESSAGE);
            }

    }
}
