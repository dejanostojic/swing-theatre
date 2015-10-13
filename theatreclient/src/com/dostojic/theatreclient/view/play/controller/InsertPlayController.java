/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dostojic.theatreclient.view.play.controller;

import com.dostojic.common.model.Artist;
import com.dostojic.common.model.ArtistPlay;
import com.dostojic.common.model.Play;
import com.dostojic.common.model.ext.ArtistPlayX;
import com.dostojic.theatreclient.controller.Controller;
import com.dostojic.theatreclient.view.artist.model.ButtonColumn;
import com.dostojic.theatreclient.view.play.PArtistList;
import com.dostojic.theatreclient.view.play.model.ArtistListModel;
import com.dostojic.theatreclient.view.play.model.ArtistPlayTableModel;
import com.dostojic.common.transfer.TransferObject;
import com.dostojic.theatreclient.view.play.model.PlayTableModel;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractAction;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.TableModel;

/**
 *
 * @author dejan
 */
public class InsertPlayController {

    public static void fillArtistList(JList listArtist) {
        try {
            TransferObject sto = Controller.getInstance().getArtists();
            if (sto.isOperationSucess()) {
                ArtistListModel alm = new ArtistListModel((List<Artist>) sto.getData());
                listArtist.setModel(alm);
            } else {
                JOptionPane.showMessageDialog(null, sto.getMessage(), "Greška", JOptionPane.ERROR_MESSAGE);
            }

        } catch (IOException ex) {
            Logger.getLogger(InsertPlayController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(InsertPlayController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static void setTableModel(JTable tableArtistRole) {
        ArtistPlayTableModel model = new ArtistPlayTableModel();
        tableArtistRole.setModel(model);
        AbstractAction action = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {

                JDialog d = new JDialog((JFrame) null, "Izaberi umetnika", true);
                d.add(new PArtistList((JTable) e.getSource(), d));
                d.pack();
                d.setVisible(true);
            }
        };
        
        
        new ButtonColumn(tableArtistRole, action, 0, false);
        new ButtonColumn(tableArtistRole, action, 1, false);
        
    }

    public static void save(JTextField editTitle, JTextField editLength, JTextArea textAbout, JTable tableArtistRole, PlayTableModel tablePlays, boolean newData, Play play) {
        String title = editTitle.getText().trim();
        String about = textAbout.getText().trim();
        ArtistPlayTableModel model = (ArtistPlayTableModel) tableArtistRole.getModel();
        List<ArtistPlayX> artistPlayList = model.getArtistPlayList();
        play.setTitle(title);
        play.setAbout(about);
        try {
            TransferObject sto;
            if(newData){
                sto = Controller.getInstance().savePlay(play, artistPlayList);
            }else{
                sto = Controller.getInstance().updatePlay(play, artistPlayList);
            }
             
            if (sto.isOperationSucess()){
                JOptionPane.showMessageDialog(null, sto.getMessage(), "Uspešno sačuvana predstava", JOptionPane.INFORMATION_MESSAGE);
                editTitle.setText("");
                editLength.setText("");
                textAbout.setText("");
                tableArtistRole.removeAll();
                tablePlays.refreshTableData();
                
            }else{
                JOptionPane.showMessageDialog(null, sto.getMessage(), "Greška pri čuvanju predstava", JOptionPane.ERROR_MESSAGE);
            }
        } catch (IOException ex) {
            Logger.getLogger(InsertPlayController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(InsertPlayController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    public static void fillTableArtistRole(JTable tableArtistRole, long id) {
        TransferObject sto = Controller.getInstance().getArtistsForPlay(id);
        if (sto.isOperationSucess()) {
            List<ArtistPlayX> apList = (List<ArtistPlayX>) sto.getData();
            ArtistPlayTableModel aptm = ((ArtistPlayTableModel) tableArtistRole.getModel());
            aptm.setArtistPlayList(apList);
//            aptm.fireTableRowsInserted(0, apList.size() - 1);
        } else {
            JOptionPane.showMessageDialog(null, sto.getMessage(), "Greška pri punjenju tabele umetnicima", JOptionPane.ERROR_MESSAGE);
        }
    }
    
}
