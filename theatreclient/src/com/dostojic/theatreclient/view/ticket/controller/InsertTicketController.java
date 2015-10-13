/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dostojic.theatreclient.view.ticket.controller;

import com.dostojic.common.model.Performance;
import com.dostojic.common.model.Play;
import com.dostojic.common.model.Seat;
import com.dostojic.common.model.Stage;
import com.dostojic.common.model.TicketStatus;
import com.dostojic.common.model.ext.TicketX;
import com.dostojic.common.transfer.TransferObject;
import com.dostojic.theatreclient.controller.Controller;
import com.dostojic.theatreclient.view.ticket.model.ButtonColumn;
import com.dostojic.theatreclient.view.ticket.model.InsertTicketTableModel;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractAction;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.plaf.basic.BasicComboBoxRenderer;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author dejan
 */
public class InsertTicketController {

    public static void fillComboScene(JComboBox comboScene) {
        comboScene.removeAllItems();
        TransferObject sto = Controller.getInstance().getScenes();
        if (sto.isOperationSucess()) {
            List<Stage> stages = (List<Stage>) sto.getData();
            if (stages != null) {
                DefaultComboBoxModel<Stage> dcm = new DefaultComboBoxModel<Stage>();
                for (Stage s : stages) {
                    dcm.addElement(s);
                }
                comboScene.setModel(dcm);
            }
        } else {
            JOptionPane.showMessageDialog(null, sto.getMessage(), "Greška", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void fillComboPlays(JComboBox comboShow) {
        try {
            comboShow.removeAllItems();
            TransferObject sto = Controller.getInstance().getPlays();
            if (sto.isOperationSucess()) {
                List<Play> plays = (List<Play>) sto.getData();
                if (plays != null) {
                    DefaultComboBoxModel<Play> dcm = new DefaultComboBoxModel<>();
                    for (Play p : plays) {
                        dcm.addElement(p);
                    }
                    comboShow.setModel(dcm);

                }
            } else {
                JOptionPane.showMessageDialog(null, sto.getMessage(), "Greška", JOptionPane.ERROR_MESSAGE);
            }
        } catch (IOException ex) {
            Logger.getLogger(InsertTicketController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(InsertTicketController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void fillComboPlaysOnStage(JComboBox comboPlays, JComboBox comboScene) {
        try {
            comboPlays.removeAllItems();
            Stage stage = (Stage) comboScene.getSelectedItem();
            TransferObject sto = Controller.getInstance().getPlays(stage);
            if (sto.isOperationSucess()) {
                List<Play> plays = (List<Play>) sto.getData();
                if (plays != null) {
                    DefaultComboBoxModel<Play> dcm = new DefaultComboBoxModel<>();
                    for (Play p : plays) {
                        dcm.addElement(p);
                    }
                    comboPlays.setModel(dcm);

                }
            } else {
                JOptionPane.showMessageDialog(null, sto.getMessage(), "Greška", JOptionPane.ERROR_MESSAGE);
            }
        } catch (IOException ex) {
            Logger.getLogger(InsertTicketController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(InsertTicketController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void fillComboPerf(JComboBox comboPerformance, JComboBox comboPlays, JComboBox comboScene) {
        comboPerformance.removeAllItems();
        comboPerformance.setRenderer(new  BasicComboBoxRenderer(){

            @Override
            public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                Performance perf = (Performance) value;
                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus); //To change body of generated methods, choose Tools | Templates.
                if (perf != null){
                    setText(perf.getStartDate() + " " + perf.getStartTime());
                }
                return this;
            }
        
        });
        
        System.out.println("combo comboPlays: " + (Play)comboPlays.getSelectedItem());
        System.out.println("combo comboScene: " + (Stage)comboScene.getSelectedItem());
        if ((Play)comboPlays.getSelectedItem() == null || (Stage)comboScene.getSelectedItem() == null){
            return;
        }
        
        TransferObject sto = Controller.getInstance().getPerformances((Play)comboPlays.getSelectedItem(), (Stage) comboScene.getSelectedItem());
        if (sto.isOperationSucess()) {
            List<Performance> performances = (List<Performance>) sto.getData();
            if (performances != null) {
                
                DefaultComboBoxModel<Performance> dcm = new DefaultComboBoxModel<>();
                for (Performance p : performances) {
                    System.out.println("PERFORMANCE: " + p);
                    dcm.addElement(p);
                }
                comboPerformance.setModel(dcm);
                
            }
        } else {
            JOptionPane.showMessageDialog(null, sto.getMessage(), "Greška", JOptionPane.ERROR_MESSAGE);
        }
    }

//    public static void getTicketsForPerformance(JComboBox comboPerformance){
//        Performance perf = (Performance) comboPerformance.getSelectedItem();
//        if (perf == null){
//            System.out.println("EMPTY PERFORMANCE");
//            return;
//        }
//        System.out.println("PERFORMANCE ID: " + perf.getId());
//        System.out.println("PERFORMANCE plaz: " + perf.getPlayId());
//        System.out.println("PERFORMANCE stage: "  + perf.getStageId());
//        TransferObject sto = Controller.getInstance().getTickets(perf);
////        TransferObject sto = Controller.getInstance().getTickets(perf);
//        if (sto.isOperationSucess()) {
//            List<TicketX> tickets = (List<TicketX>) sto.getData();
//        int i = 0;
//        System.out.println("\"TICKTES: \" + ");
//            for (TicketX t : tickets){
//                System.out.println(++i + ":" + t.getSeat().getRow()+","+t.getSeat().getColumn()+"; ");
//            }
//        } else {
//            JOptionPane.showMessageDialog(null, sto.getMessage(), "Greška", JOptionPane.ERROR_MESSAGE);
//        }
//    }
    public static void setTicketsTableModel(final JTable tableTickets) {
        tableTickets.setModel(new InsertTicketTableModel());
//        StatusColumnCellRenderer s = new  StatusColumnCellRenderer();
//        tableTickets.getColumnModel().getColumn(1).setCellRenderer(null);
//        tableTickets.setDefaultRenderer(Object.class, s);



    }

    public static void fillTicketTable(final JTable tableTickets, JComboBox comboPerformance) {
        Performance perf = (Performance) comboPerformance.getSelectedItem();
        if (perf == null) {
            System.out.println("EMPTY PERFORMANCE");
            tableTickets.setVisible(false);
            return;
        }
        TransferObject sto = Controller.getInstance().getTickets(perf);
        if (sto.isOperationSucess()) {
            tableTickets.setVisible(true);
            final InsertTicketTableModel model = (InsertTicketTableModel) tableTickets.getModel();
            List<TicketX> tickets = (List<TicketX>) sto.getData();
            
//            System.out.println("DEB>UGG :: INFO ::: TICKETS: " + tickets);
            
//            tickets.parallelStream().forEach((ticket) -> {
//                ticket.setPrice(perf.getPrice());
//            });
            for (TicketX t : tickets){
                  t.setPrice(perf.getPrice());
            }
            model.setTicketMatrix(tickets);

            for (int j=1; j<tableTickets.getColumnCount(); j++){
                new ButtonColumn(tableTickets, new AbstractAction() {

                    @Override
                    public void actionPerformed(ActionEvent e) {
                        int row = tableTickets.getEditingRow();
                        int col = tableTickets.getEditingColumn();
                        model.addTicketInStore(row, col);
                    }
            }, j, true);
            }

        } else {
            JOptionPane.showMessageDialog(null, sto.getMessage(), "Greška", JOptionPane.ERROR_MESSAGE);
        }

    }

    public static void saveTickets(JComboBox comboPerformance, JTable tableTickets) {
        Performance p = (Performance) comboPerformance.getSelectedItem();
        if (p == null){
            JOptionPane.showMessageDialog(comboPerformance,"Nema selektovanog izvodjenja.");
            return;
        }
        
        InsertTicketTableModel model = (InsertTicketTableModel) tableTickets.getModel();
        Set<TicketX> ticketsInStore = model.getTicketsInStore();
        
        if (ticketsInStore.isEmpty()){
            JOptionPane.showMessageDialog(comboPerformance,"Odaberite sedišta koja zelite da kupite.");
        }else{
            Iterator<TicketX> iterator = ticketsInStore.iterator();
            StringBuilder message = new StringBuilder("Potvrdi kupovinu karata: ");
            double price = 0.0;
            while (iterator.hasNext()){
                TicketX t = iterator.next();
                Seat seat = t.getSeat();
                message.append("\nr").append(seat.getRow()).append(", k").append(seat.getColumn()).append(", cena: ").append(t.getPrice());
                price = price + t.getPrice();
                if (!iterator.hasNext()){
                    message.append("\n\nUkupna cena je: ").append(price).append('.');
                }
            }
            int res = JOptionPane.showConfirmDialog(tableTickets, message.toString());
            
            if (res == JOptionPane.YES_OPTION){
                JOptionPane.showMessageDialog(tableTickets, "SACUVANE karte ");
                TransferObject sto = Controller.getInstance().saveTickets(model.getTicketsForSave());
                if (sto.isOperationSucess()){
                    model.sellTicketsFromStore();
                }
            }
        }
    }

}
