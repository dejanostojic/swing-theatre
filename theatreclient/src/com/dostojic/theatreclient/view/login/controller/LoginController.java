/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dostojic.theatreclient.view.login.controller;

import com.dostojic.common.model.User;
import com.dostojic.common.transfer.TransferObject;
import com.dostojic.theatreclient.controller.Controller;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import com.dostojic.theatreclient.view.main.FMain;
import javax.swing.JDialog;

/**
 *
 * @author dejan
 */
public class LoginController {

    private static User currentUser;

    public static User getCurrentUser() {
        return currentUser;
    }

    public static void logIn(JTextField textUser, JPasswordField passUser, JDialog dialog) {
        
        String userName = textUser.getText().trim();
        char[] charAr = passUser.getPassword();
        String pass = "";
        for (char c : charAr) {
            pass += c;
        }
        try {
            TransferObject sto = Controller.getInstance().loginUser(userName, pass);
            if (sto.isOperationSucess()) {
                User user = (User) sto.getData();
                if (user != null) {
                    JOptionPane.showMessageDialog(dialog, "Uspešno ste se prijavili na sistem", "Dobrodošli!", JOptionPane.INFORMATION_MESSAGE);
                    currentUser = user;
                    dialog.dispose();

                    FMain.getInstance().setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(dialog, "Pogrešno korisničko ime, ili lozinka!", "Greška!", JOptionPane.ERROR_MESSAGE);
                }
            }else{
                JOptionPane.showMessageDialog(dialog, sto.getMessage() != null ?sto.getMessage().toString() : "Greška", "Greška!", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(dialog, ex.getMessage(), "Greška", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    

}
