/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dostojic.theatreclient.view.performance.controller;

import com.dostojic.common.model.Performance;
import com.dostojic.common.model.Play;
import com.dostojic.common.model.Stage;
import com.dostojic.common.transfer.TransferObject;
import com.dostojic.theatreclient.controller.Controller;
import com.dostojic.theatreclient.view.ticket.controller.InsertTicketController;
import java.awt.Component;
import java.awt.Label;
import java.io.IOException;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.sql.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.ListCellRenderer;

/**
 *
 * @author dejan
 */
public class PerfInsertController {
    

    public static void savePerformance(JComboBox comboStage, JComboBox comboPlay, JTextField textDate, JTextField textTime, JTextField textPrice) {
        Stage stage = (Stage) comboStage.getSelectedItem();
        Play play = (Play) comboPlay.getSelectedItem();
        String sDate = textDate.getText().trim();
        String sTime = textTime.getText().trim();
        Date date;
        Time time;
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        try {
            long milis = sdf.parse(sDate).getTime();
            date = new Date(milis);
        } catch (ParseException ex) {
            JOptionPane.showMessageDialog(textTime, "Unesite datum u formatu dd.MM.yyyy");
            return;
        }
        
        sdf = new SimpleDateFormat("HH:mm");
        try {
            long milis = sdf.parse(sTime).getTime();
            time = new Time(milis);
        } catch (ParseException ex) {
            JOptionPane.showMessageDialog(textTime, "Unesite vreme u formatu HH:mm");
            return;
        }
        double price = 0.0;
        try{
            price = Double.parseDouble(textPrice.getText());
        }catch(NumberFormatException nfe){
            JOptionPane.showMessageDialog(textPrice, "Cena mora biti broj.");
            return;
        }
        Performance perf = new Performance();
        perf.setStageId(stage.getId());
        perf.setPlayId(play.getId());
        perf.setStartDate(date);
        perf.setStartTime(time);
        perf.setPrice(price);
        
        TransferObject sto = Controller.getInstance().savePerformance(perf);
        if (sto.isOperationSucess()){
            JOptionPane.showMessageDialog(null, sto.getMessage(), "Uspešno sačuvano izvođenje", JOptionPane.INFORMATION_MESSAGE);
        }else{
            JOptionPane.showMessageDialog(null, sto.getMessage(), "Greška", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void fillComboPlays(JComboBox comboShow) {
        try {
            comboShow.removeAllItems();
            TransferObject sto = Controller.getInstance().getPlays();
            if (sto.isOperationSucess()){
                List<Play> plays = (List<Play>) sto.getData();
                if (plays != null){
                    DefaultComboBoxModel<Play> dcm = new DefaultComboBoxModel<>();
                    for (Play p: plays){
                        dcm.addElement(p);
                    }
                    comboShow.setModel(dcm);
                   
                }
            }else{
                JOptionPane.showMessageDialog(null, sto.getMessage(), "Greška", JOptionPane.ERROR_MESSAGE);
            }
        } catch (IOException ex) {
            Logger.getLogger(InsertTicketController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(InsertTicketController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void fillComboScene(JComboBox comboScene) {
        comboScene.removeAllItems();
        TransferObject sto = Controller.getInstance().getScenes();
        if (sto.isOperationSucess()){
            List<Stage> stages = (List<Stage>) sto.getData();
            if (stages != null){
                DefaultComboBoxModel<Stage> dcm = new DefaultComboBoxModel<>();
                for (Stage s : stages){
                    dcm.addElement(s);
                }
                comboScene.setModel(dcm);
            }
        }else{
            JOptionPane.showMessageDialog(null, sto.getMessage(), "Greška", JOptionPane.ERROR_MESSAGE);
        }
    }
    
}
