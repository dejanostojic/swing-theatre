/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.dostojic.theaterserver.server;

import com.dostojic.common.model.ext.TicketX;
import com.dostojic.common.transfer.Constants;
import com.dostojic.common.transfer.TransferObject;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author dostojic
 */
public class InformTicketStatus {
    
    private List<Socket> sockets;

    private InformTicketStatus() {
        sockets = new ArrayList<>();
    }
    
    public void addSocket(Socket s){
        sockets.add(s);
    }

    public void informTicketReserved(TicketX t){
        sockets.parallelStream().forEach((s) -> {
            try {
                informReserved(s, t);
            } catch (IOException ex) {
                Logger.getLogger(InformTicketStatus.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }
    
    private void informReserved(Socket s, TicketX t) throws IOException{
        TransferObject to = new TransferObject();
        to.setData(t);
        to.setOperation(Constants.RESERVE_TICKET);
        
        ObjectOutputStream outSocket = new ObjectOutputStream(s.getOutputStream());
        outSocket.writeObject(to);
        
    }
    
    
    
    
    
    
    
    
    private static InformTicketStatus instance;

    public static InformTicketStatus getInstance() {
        if (instance == null){
            instance = new InformTicketStatus();
        }
        return instance;
    }
    
    
    
}
