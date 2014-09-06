/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dostojic.theatreclient.view.artist.controller;

import com.dostojic.common.model.Artist;
import com.dostojic.theatreclient.controller.Controller;
import com.dostojic.theatreclient.view.artist.FArtist;
import com.dostojic.theatreclient.view.artist.PInsertArtist;
import com.dostojic.theatreclient.view.artist.model.ArtistTableModel;
import com.dostojic.theatreclient.view.artist.model.ButtonColumn;
import com.dostojic.common.transfer.TransferObject;
import java.awt.Dialog;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JTable;

/**
 *
 * @author dejan
 */
public class ListController {

    public static void fillTable(JTable artistsTable) {
        try {
            TransferObject sto = Controller.getInstance().getArtists();
            if (sto.isOperationSucess()) {
                ArtistTableModel tableModel = new ArtistTableModel((List<Artist>) sto.getData());
                artistsTable.setModel(tableModel);
                Action update;
                update = new AbstractAction() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        JTable table = (JTable) e.getSource();
                        int modelRow = Integer.valueOf(e.getActionCommand());
                        String fname = (String) table.getValueAt(modelRow, 0);
                        String lname = (String) table.getValueAt(modelRow, 1);
                        String about = (String) table.getValueAt(modelRow, 2);
                        String kind = (String) table.getValueAt(modelRow, 3);
                        long id = (Long) table.getValueAt(modelRow, -1);

                        JDialog d = new JDialog((JDialog) null, "Izmeni " + fname + " " + lname, true);
                        d.add(new PInsertArtist(new Artist(id, fname, lname, kind, about)));
                        d.pack();
                        d.setVisible(true);
                        System.out.println("red " + modelRow + " je kliknut");
                        FArtist.getInstance().refresh();
                    }
                };
                new ButtonColumn(artistsTable, update, 4, true);
                new ButtonColumn(artistsTable, new AbstractAction() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        int dialogResult = JOptionPane.showConfirmDialog(null, "Sigurno želite da obrišete ovo", "Obriši", JOptionPane.YES_NO_OPTION);
                        if (dialogResult == JOptionPane.YES_OPTION) {
                            JTable table = (JTable) e.getSource();
                            int modelRow = Integer.valueOf(e.getActionCommand());
                            long id = (Long) table.getValueAt(modelRow, -1);
                            try {
                                ListController.delete(new Artist(id));
                                FArtist.getInstance().refresh();
                            } catch (Exception ex) {
                                Logger.getLogger(ListController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                    }
                }, 5, true);

            } else {
                JOptionPane.showMessageDialog(null, "Nastala je greska pri popunjavanju tabele.", "Greška", JOptionPane.ERROR_MESSAGE);
            }
        } catch (IOException ex) {
            Logger.getLogger(ListController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ListController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void delete(Artist artist) throws Exception {
        TransferObject to = Controller.getInstance().delete(artist);
        JOptionPane.showMessageDialog(null, to.getMessage());
    }
}
