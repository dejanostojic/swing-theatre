/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dostojic.theaterserver.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author dejan
 */
public class StartServerThread extends Thread {

    private ServerSocket ss;
    private ServerSocket ss1;
    private volatile boolean runServer;
    private List<ClientThread> clients;
     Thread infoThread;
    
    StartServerThread() {
        runServer = true;
        clients = new ArrayList<ClientThread>();
    }

    @Override
    public void run() {
        try {
            ss = new ServerSocket(9000);
            ss1 = new ServerSocket(9001);
            
            
            infoThread = new Thread(() -> {
                while (!isInterrupted() && runServer) {
                    try {
                        Socket s = ss1.accept();
                        InformTicketStatus.getInstance().addSocket(s);
                        System.out.println("Server je prihvatio klijenta!");
                    } catch (IOException ex) {
                        Logger.getLogger(StartServerThread.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                System.out.println("INFO soket STOP");
                try {
                    if (ss1 != null && !ss1.isClosed()) {
                        ss1.close();
                    }
                } catch (IOException ex) {
                    Logger.getLogger(StartServerThread.class.getName()).log(Level.SEVERE, null, ex);
                }
            });
            
            infoThread.start();
            
            System.out.println("Server je pokrenut i ceka klijenta");
            while (!isInterrupted() && runServer) {
                
                Socket s = ss.accept();
                System.out.println("Server je prihvatio klijenta!");
                ClientThread clientThread = new ClientThread(s,clients);
                clients.add(clientThread);
                clientThread.start();
            }
        } catch (IOException ex) {
            Logger.getLogger(FServer.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("stop");
        try {
            if (ss != null && !ss.isClosed()) {
                ss.close();
            }
        } catch (IOException ex) {
            Logger.getLogger(StartServerThread.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void setRunServer(boolean runServer) {
        this.runServer = runServer;
    }

    public void reRun() {
        runServer = true;
        this.start();
//        run();
    }

    public ServerSocket getSs() {
        return ss;
    }

    public void stopEntireServer() {
        synchronized (this) {
            runServer = false;
            this.interrupt();
            try {
                this.getSs().close();
            } catch (IOException ex) {
                Logger.getLogger(StartServerThread.class.getName()).log(Level.SEVERE, null, ex);
            }
            clients.parallelStream().forEach((ct) -> {
                ct.stopThread();
            });
        }
        
        synchronized (infoThread) {
            infoThread.interrupt();
            try {
                this.ss1.close();
            } catch (IOException ex) {
                Logger.getLogger(StartServerThread.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        
    }
}
