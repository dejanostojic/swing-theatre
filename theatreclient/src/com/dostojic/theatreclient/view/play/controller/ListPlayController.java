/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dostojic.theatreclient.view.play.controller;

import com.dostojic.common.model.Artist;
import com.dostojic.common.model.Play;
import com.dostojic.theatreclient.controller.Controller;
import com.dostojic.theatreclient.view.artist.FArtist;
import com.dostojic.theatreclient.view.artist.controller.ListController;
import com.dostojic.theatreclient.view.artist.model.ButtonColumn;
import com.dostojic.theatreclient.view.main.FMain;
import com.dostojic.theatreclient.view.play.model.PlayTableModel;
import com.dostojic.common.transfer.TransferObject;
import com.dostojic.theatreclient.view.play.PInsertPlay;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractAction;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;

/**
 *
 * @author dejan
 */
public class ListPlayController {

    private static void setTableModel(final JTable tablePlay, List<Play> plays) {
        PlayTableModel model = new PlayTableModel(plays);
        tablePlay.setModel(model);
        
        new ButtonColumn(tablePlay, 
            new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    int dialogResult = JOptionPane.showConfirmDialog(tablePlay, "Sigurno želite da obrišete ovo?", "Obriši", JOptionPane.YES_NO_OPTION);
                    if (dialogResult == JOptionPane.YES_OPTION) {
                        JTable table = (JTable) e.getSource();
                        int modelRow = Integer.valueOf(e.getActionCommand());
                        long id = (Long) table.getValueAt(modelRow, -1);
                        try {
                            ListPlayController.deletePlay(id);
                            ((PlayTableModel) table.getModel()).deletePlay(id);
                        } catch (Exception ex) {
                            Logger.getLogger(ListController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
            }, 3, true);

        new ButtonColumn(tablePlay, 
            new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                        JTable table = (JTable) e.getSource();
                        int modelRow = Integer.valueOf(e.getActionCommand());
                        PlayTableModel ptMpodel = (PlayTableModel) table.getModel();
                        Play playAtIndex = ptMpodel.getPlayAtIndex(modelRow);
                        PInsertPlay pInsertPlay = new PInsertPlay(tablePlay);
                        pInsertPlay.loadPlay(playAtIndex);
                        JDialog di = new JDialog((JFrame)null,"Izmeni predstavu", true);
                        di.add(pInsertPlay);
                        di.pack();
                        di.setVisible(true);
                        System.out.println("KRAJ AKCIJE");
                }
            }, 2, true);
    }

    public static void fillTable(JTable tablePlays) {
        try {
            TransferObject sto = Controller.getInstance().getPlays();
            if (sto.isOperationSucess()){
                setTableModel(tablePlays, (List<Play>) sto.getData());
            } else {
                JOptionPane.showMessageDialog(null, sto.getMessage(), "Greška", JOptionPane.ERROR_MESSAGE);
            }
        } catch (IOException ex) {
            Logger.getLogger(ListPlayController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ListPlayController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void fillTable(JTable tablePlays, JTextField textName) {
        try {
            String name = textName.getText();
            TransferObject sto = Controller.getInstance().getPlaysByName(name);
            if (sto.isOperationSucess()){
                PlayTableModel ptm = (PlayTableModel) tablePlays.getModel();
                ptm.setPlays((List<Play>) sto.getData());
            } else {
                JOptionPane.showMessageDialog(tablePlays, sto.getMessage(), "Greška", JOptionPane.ERROR_MESSAGE);
            }
        } catch (IOException ex) {
            Logger.getLogger(ListPlayController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ListPlayController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private static void deletePlay(long playId) {
        try {
            TransferObject sto = Controller.getInstance().deletePlay(playId);
            if (sto.isOperationSucess()){
                JOptionPane.showMessageDialog(null, sto.getMessage(), "Obrisana predstava", JOptionPane.INFORMATION_MESSAGE);
            }else{
                JOptionPane.showMessageDialog(null, sto.getMessage(), "Greška", JOptionPane.ERROR_MESSAGE);
            }
        } catch (IOException ex) {
            Logger.getLogger(ListPlayController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ListPlayController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    
}
