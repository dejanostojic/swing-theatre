/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.dostojic.theaterserver.server;

import com.dostojic.common.model.User;
import com.dostojic.common.model.ext.TicketX;
import com.dostojic.common.transfer.Constants;
import com.dostojic.common.transfer.TransferObject;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.SocketAddress;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author dostojic
 */
public class ThreadInformTicketReserved implements java.lang.Runnable{
    
    private ClientThread current;
    private List<ClientThread> clients;
    private TicketX t;
    
    public ThreadInformTicketReserved(ClientThread current, List<ClientThread> clients, TicketX t) {
        this.current = current;
        this.clients = clients;
        this.t = t;
    }
    
    @Override
    public void run() {
        TransferObject to = new TransferObject();
        to.setData(t);
        to.setOperation(Constants.RESERVE_TICKET);
        clients.parallelStream().forEach((c) -> {
            try {
                if (c.getOutSoket() == null) {
                    c.setOutSoket(new ObjectOutputStream(c.getS().getOutputStream()));
                    System.out.println("BILO NULL");
                }
                if (current != c){
                    InetAddress address = c.getS().getInetAddress();
                    User user = c.getUser();
                    System.out.println("SADA SALJE useru: " + user.getUserName() + ", innet address: " + address.getCanonicalHostName());
                    c.getOutSoket().writeObject(to);
                    System.out.println("POSLATO!");
                }
            } catch (IOException ex) {
                Logger.getLogger(ThreadInformTicketReserved.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        System.out.println("gotovo, SVI OBAVESTENI");
    }
    
}
