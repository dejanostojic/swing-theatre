/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dostojic.theatreclient.view.play;

import com.dostojic.common.model.Play;
import com.dostojic.theatreclient.view.login.controller.LoginController;
import com.dostojic.theatreclient.view.play.controller.InsertPlayController;
import com.dostojic.theatreclient.view.play.model.PlayTableModel;
import java.awt.Window;
import javax.swing.JTable;
import javax.swing.SwingUtilities;

/**
 *
 * @author dejan
 */
public class PInsertPlay extends javax.swing.JPanel {

    /**
     * Creates new form PInsertPlay
     */
    
    private boolean newData;
    private JTable tablePlays;
    private Play play;
    public PInsertPlay(JTable tablePlays) {
        newData = true;
        this.tablePlays = tablePlays;
        play = new Play();
        initComponents();
        labelUserName.setText("Korisnik: " + LoginController.getCurrentUser() != null ? LoginController.getCurrentUser().getFirstName() : "not logged in");
        setTableModel();
    }
    
    public PInsertPlay(Play p, JTable tablePlays){
        this.tablePlays = tablePlays;
        loadPlay(p);
    }
    
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        editTitle = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        buttonSave = new javax.swing.JButton();
        labelUserName = new javax.swing.JLabel();
        editLength = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableArtistRole = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        textAbout = new javax.swing.JTextArea();

        editTitle.setText(" ");

        jLabel1.setText("Naziv");

        buttonSave.setText("Sačuvaj");
        buttonSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonSaveActionPerformed(evt);
            }
        });

        labelUserName.setText("User name");

        jLabel2.setText("Trajanje");

        tableArtistRole.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null}
            },
            new String [] {
                "Title 1", "Title 2"
            }
        ));
        tableArtistRole.setDropMode(javax.swing.DropMode.ON);
        jScrollPane1.setViewportView(tableArtistRole);

        jLabel3.setText("O predstavi");

        textAbout.setColumns(20);
        textAbout.setRows(5);
        jScrollPane3.setViewportView(textAbout);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(buttonSave, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(438, 438, 438))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jScrollPane1)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane3))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel2))
                                .addGap(40, 40, 40)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(editLength, javax.swing.GroupLayout.PREFERRED_SIZE, 565, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(editTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 565, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 205, Short.MAX_VALUE)
                        .addComponent(labelUserName)
                        .addGap(234, 234, 234))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelUserName)
                            .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel1)))
                        .addGap(38, 38, 38))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(editTitle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(editLength, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 138, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 354, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(buttonSave)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void buttonSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonSaveActionPerformed
        InsertPlayController.save(editTitle, editLength, textAbout, tableArtistRole, (PlayTableModel) tablePlays.getModel(), newData, play);
        if (!newData){
            SwingUtilities.getWindowAncestor(this).dispose();
        }
    }//GEN-LAST:event_buttonSaveActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonSave;
    private javax.swing.JTextField editLength;
    private javax.swing.JTextField editTitle;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel labelUserName;
    private javax.swing.JTable tableArtistRole;
    private javax.swing.JTextArea textAbout;
    // End of variables declaration//GEN-END:variables

    private void setTableModel() {
        InsertPlayController.setTableModel(tableArtistRole);
    }
    
    public void loadPlay(Play p){
        editTitle.setText(p.getTitle());
        textAbout.setText(p.getAbout());
        buttonSave.setText("Sačuvaj izmene");
        newData = false;
        play = p;
        InsertPlayController.fillTableArtistRole(tableArtistRole, p.getId());
    }

    public void setTablePlays(JTable tablePlays) {
        this.tablePlays = tablePlays;
    }
    
}
