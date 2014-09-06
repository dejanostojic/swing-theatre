/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dostojic.theatreclient.client;

import com.dostojic.theatreclient.view.login.DLogin;
import com.dostojic.theatreclient.view.login.controller.LoginController;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Locale;

/**
 *
 * @author dejan
 */
public class Client {

    public static void main(String[] args) {
            Locale.setDefault(new Locale("sr"));
            
            DLogin dLogin = new DLogin(null, true);
            dLogin.addWindowListener(new WindowAdapter() {

                @Override
                public void windowClosed(WindowEvent e) {
                    if (LoginController.getCurrentUser() == null){
                        System.exit(0);
                    }
                }
            });
            dLogin.setVisible(true);
        
    }
}
