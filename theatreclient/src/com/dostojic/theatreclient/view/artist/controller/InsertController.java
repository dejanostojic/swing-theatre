/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dostojic.theatreclient.view.artist.controller;

import com.dostojic.common.model.Artist;
import com.dostojic.theatreclient.controller.Controller;
import com.dostojic.theatreclient.view.artist.PInsertArtist;
import com.dostojic.common.transfer.TransferObject;
import java.awt.Component;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author dejan
 */
public class InsertController {
    
     public static void insertKindsToCombo(JComboBox kinds) {
         kinds.removeAllItems();
         kinds.setRenderer(new DefaultListCellRenderer(){

             @Override
             public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                 super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus); //To change body of generated methods, choose Tools | Templates.
                 
                 if (value != null){
                     String kind = (String) value;
                     String label = kind;
                     switch(kind){
                         case Artist.KIND_DIRECTOR :
                             label = "Režiser";
                             break;
                         case Artist.KIND_ACTOR :
                             label = "Glumac";
                             break;
                         case Artist.KIND_SET_DESIGNER :
                             label = "Scenograf";
                             break;
                         case Artist.KIND_COSTUME_DESIGNER :
                             label = "Kostimograf";
                             break;
                             
                     }
                     setText(label);
                 }
                 
                 return this;
             }
             
         });
         kinds.addItem(Artist.KIND_DIRECTOR);
         kinds.addItem(Artist.KIND_ACTOR);
         kinds.addItem(Artist.KIND_SET_DESIGNER);
         kinds.addItem(Artist.KIND_COSTUME_DESIGNER);
     }
     
//     public static void fillValuesToComponents(JTextField textName, ){
//         
//     }
     
     public static void save(PInsertArtist panel, JTextField firstName, JTextField lastName, JComboBox kind, JTextArea about){
         try {
             String fName = firstName.getText().trim();
             String lName = lastName.getText().trim();
             String artistKind = (String) kind.getSelectedItem();
             String aboutArtist = about.getText().trim();
             Artist artist = new Artist();
             artist.setFirstName(fName);
             artist.setLastName(lName);
             artist.setBody(aboutArtist);
             artist.setKind(artistKind);
             
             TransferObject to;
             if (panel.getArtist() != null){
                artist.setId(panel.getArtist().getId());
                to = Controller.getInstance().updateArtist(artist);
             }else{
                to = Controller.getInstance().saveArtist(artist);
             }
             JOptionPane.showMessageDialog(panel, to.getMessage());
             
         } catch (IOException ex) {
             Logger.getLogger(InsertController.class.getName()).log(Level.SEVERE, null, ex);
         } catch (ClassNotFoundException ex) {
             Logger.getLogger(InsertController.class.getName()).log(Level.SEVERE, null, ex);
         }
         
     }

    public static void fillValuesToComponents(JTextField textName, JTextField textLastName, JTextArea textAreaAbout, JComboBox comboKind, Artist artist) {
        textName.setText(artist.getFirstName());
        textLastName.setText(artist.getLastName());
        textAreaAbout.setText(artist.getBody());
        comboKind.setSelectedItem(artist.getKind());
    }
    
}
