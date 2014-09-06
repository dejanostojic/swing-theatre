/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dostojic.theatreclient.view.play;

import com.dostojic.common.model.Artist;
import com.dostojic.theatreclient.view.play.controller.InsertPlayController;
import com.dostojic.theatreclient.view.play.model.ArtistPlayTableModel;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JTable;

/**
 *
 * @author dejan
 */
public class PArtistList extends javax.swing.JPanel {

    private JTable table;
    private JDialog parent;
    /**
     * Creates new form PArtistList
     */
    public PArtistList(JTable table, JDialog parent) {
        initComponents();
        fillList();
        this.table = table;
        this.parent = parent;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane2 = new javax.swing.JScrollPane();
        listArtist = new javax.swing.JList();
        buttonChoose = new javax.swing.JButton();

        listArtist.setDragEnabled(true);
        jScrollPane2.setViewportView(listArtist);

        buttonChoose.setText("Izaberi");
        buttonChoose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonChooseActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 336, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(73, 73, 73)
                        .addComponent(buttonChoose, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(42, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(55, 55, 55)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 403, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(buttonChoose)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void buttonChooseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonChooseActionPerformed
        if (listArtist.getSelectedIndex() > -1){
            ArtistPlayTableModel aptm = (ArtistPlayTableModel) table.getModel();
            aptm.setArtistAtRow(table.getEditingRow(), (Artist) listArtist.getSelectedValue());
            parent.dispose();
        }else{
            JOptionPane.showMessageDialog(null, "Selektujte nekog umetnika");
        }
    }//GEN-LAST:event_buttonChooseActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonChoose;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JList listArtist;
    // End of variables declaration//GEN-END:variables
    
    private void fillList() {
        InsertPlayController.fillArtistList(listArtist);
    }
}