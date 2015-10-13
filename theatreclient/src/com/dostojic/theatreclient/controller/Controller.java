/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dostojic.theatreclient.controller;

import com.dostojic.common.model.Artist;
import com.dostojic.common.model.Performance;
import com.dostojic.common.model.Play;
import com.dostojic.common.model.Seat;
import com.dostojic.common.model.Stage;
import com.dostojic.common.model.Ticket;
import com.dostojic.common.model.User;
import com.dostojic.common.model.ext.ArtistPlayX;
import com.dostojic.common.model.ext.TicketX;
import com.dostojic.common.transfer.Constants;
import com.dostojic.common.transfer.TransferObject;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author dejan
 */
public class Controller {
    
    private static Controller instance;
    private Socket socket;
    private ObjectInputStream inSocket;
    private ObjectOutputStream outSocket;
    
    private Controller(){
    }
    public static Controller getInstance(){
        if (instance == null){
            instance = new Controller();
            instance.connectToServer();
        }
        return instance;
    }

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }
    
    public TransferObject getUsers() throws IOException, ClassNotFoundException{
        TransferObject to = new TransferObject();
        to.setOperation(Constants.GET_USERS);
        outSocket = new ObjectOutputStream(socket.getOutputStream());
        outSocket.writeObject(to);
        
        inSocket = new ObjectInputStream(socket.getInputStream());
        return (TransferObject) inSocket.readObject();
    }
    
    public TransferObject loginUser(String userName, String pass) throws IOException, ClassNotFoundException{
        TransferObject to = new TransferObject();
        to.setOperation(Constants.LOG_IN_USER);
        String[] data = {userName, pass};
        to.setData(data);
        
        outSocket = new ObjectOutputStream(socket.getOutputStream());
        outSocket.writeObject(to);
        
        inSocket = new ObjectInputStream(socket.getInputStream());
        return (TransferObject) inSocket.readObject();
    }
    
    public TransferObject saveUser(User artist) throws IOException, ClassNotFoundException {
        TransferObject to = new TransferObject();
        to.setOperation(Constants.SAVE_USER);
        to.setData(artist);
        outSocket = new ObjectOutputStream(socket.getOutputStream());
        outSocket.writeObject(to);
        
        inSocket = new ObjectInputStream(socket.getInputStream());
        return (TransferObject) inSocket.readObject();
    }
    
    public TransferObject saveArtist(Artist artist) throws IOException, ClassNotFoundException {
        TransferObject to = new TransferObject();
        to.setOperation(Constants.SAVE_ARTIST);
        to.setData(artist);
        outSocket = new ObjectOutputStream(socket.getOutputStream());
        outSocket.writeObject(to);
        
        inSocket = new ObjectInputStream(socket.getInputStream());
        return (TransferObject) inSocket.readObject();
    }

    public TransferObject getArtists() throws IOException, ClassNotFoundException {
        TransferObject to = new TransferObject();
        to.setOperation(Constants.GET_ARTISTS);
        outSocket = new ObjectOutputStream(socket.getOutputStream());
        outSocket.writeObject(to);
        
        inSocket = new ObjectInputStream(socket.getInputStream());
        return (TransferObject) inSocket.readObject();
    }

    
    public TransferObject getArtists(String firstName, String lastName) throws IOException, ClassNotFoundException {
        TransferObject to = new TransferObject();
        to.setOperation(Constants.GET_ARTISTS_BY_NAME);
        String[] data = {firstName, lastName};
        to.setData(data);
        outSocket = new ObjectOutputStream(socket.getOutputStream());
        outSocket.writeObject(to);
        
        inSocket = new ObjectInputStream(socket.getInputStream());
        return (TransferObject) inSocket.readObject();
    }
      
    public TransferObject updateArtist(Artist artist) throws IOException, ClassNotFoundException {
        TransferObject to = new TransferObject();
        to.setOperation(Constants.UPDATE_ARTIST);
        to.setData(artist);
        outSocket = new ObjectOutputStream(socket.getOutputStream());
        outSocket.writeObject(to);
        
        inSocket = new ObjectInputStream(socket.getInputStream());
        return (TransferObject) inSocket.readObject();
    }
    
    public TransferObject delete(Artist artist) throws IOException, ClassNotFoundException{
        TransferObject to = new TransferObject();
        to.setOperation(Constants.DELETE_ARTIST);
        to.setData(artist);
        outSocket = new ObjectOutputStream(socket.getOutputStream());
        outSocket.writeObject(to);
        
        inSocket = new ObjectInputStream(socket.getInputStream());
        return (TransferObject) inSocket.readObject();
    }
    
    public TransferObject savePlay(Play play, List<ArtistPlayX> artistPlayList) throws IOException, ClassNotFoundException{
        Object [] array = new Object[2];
        array[0] = play;
        array[1] = artistPlayList;
        TransferObject cto = new TransferObject();
        cto.setData(array);
        cto.setOperation(Constants.SAVE_PLAY_AND_ARTISTS);
        outSocket = new ObjectOutputStream(socket.getOutputStream());
        outSocket.writeObject(cto);
        
        inSocket = new ObjectInputStream(socket.getInputStream());
        return (TransferObject) inSocket.readObject();
    }
    
    public TransferObject updatePlay(Play play, List<ArtistPlayX> artistPlayList) throws IOException, ClassNotFoundException{
        Object [] array = new Object[2];
        array[0] = play;
        array[1] = artistPlayList;
        TransferObject cto = new TransferObject();
        cto.setData(array);
        cto.setOperation(Constants.UPDATE_PLAY_AND_ARTISTS);
        outSocket = new ObjectOutputStream(socket.getOutputStream());
        outSocket.writeObject(cto);
        
        inSocket = new ObjectInputStream(socket.getInputStream());
        return (TransferObject) inSocket.readObject();
    }

    public TransferObject getPlays() throws IOException, ClassNotFoundException {
        TransferObject cto = new TransferObject();
        cto.setOperation(Constants.GET_PLAYS);
        outSocket = new ObjectOutputStream(socket.getOutputStream());
        outSocket.writeObject(cto);
        
        inSocket = new ObjectInputStream(socket.getInputStream());
        return (TransferObject) inSocket.readObject();
    }
    
    public TransferObject getPlaysByName(String name) throws IOException, ClassNotFoundException {
        TransferObject cto = new TransferObject();
        cto.setOperation(Constants.GET_PLAYS_BY_NAME);
        cto.setData(name);
        outSocket = new ObjectOutputStream(socket.getOutputStream());
        outSocket.writeObject(cto);
        
        inSocket = new ObjectInputStream(socket.getInputStream());
        return (TransferObject) inSocket.readObject();
    }

    public TransferObject getPlays(Stage s) throws IOException, ClassNotFoundException {
        TransferObject cto = new TransferObject();
        cto.setOperation(Constants.GET_PLAYS_FOR_STAGE);
        cto.setData(s);
        outSocket = new ObjectOutputStream(socket.getOutputStream());
        outSocket.writeObject(cto);
        
        inSocket = new ObjectInputStream(socket.getInputStream());
        return (TransferObject) inSocket.readObject();
    }
     
    public TransferObject deletePlay(long playId) throws IOException, ClassNotFoundException{
        TransferObject to = new TransferObject();
        to.setOperation(Constants.DELETE_PLAY);
        to.setData(playId);
        outSocket = new ObjectOutputStream(socket.getOutputStream());
        outSocket.writeObject(to);
        
        inSocket = new ObjectInputStream(socket.getInputStream());
        return (TransferObject) inSocket.readObject();
    }

    public TransferObject getArtistsForPlay(long id) {
        TransferObject sto = new TransferObject();
        try {
            TransferObject to = new TransferObject();
            to.setOperation(Constants.GET_ARTISTS_FOR_PLAY);
            to.setData(id);
            outSocket = new ObjectOutputStream(socket.getOutputStream());
            outSocket.writeObject(to);
            
            inSocket = new ObjectInputStream(socket.getInputStream());
            sto = (TransferObject) inSocket.readObject();
        } catch (IOException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        return sto;
        
    }
    

    public TransferObject saveStageWithSeats(Stage stage, Seat[][] seats) {
        TransferObject sto = new TransferObject();
        try {
            Object [] array = new Object[2];
            array[0] = stage;
            array[1] = seats;
            TransferObject cto = new TransferObject();
            cto.setData(array);
            cto.setOperation(Constants.SAVE_STAGE_WITH_SEATS);
            outSocket = new ObjectOutputStream(socket.getOutputStream());
            outSocket.writeObject(cto);
            
            inSocket = new ObjectInputStream(socket.getInputStream());
            sto = (TransferObject) inSocket.readObject();
        } catch (IOException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        return sto;
    }

    public TransferObject getScenes() {
        TransferObject sto = new TransferObject();
        try {
            TransferObject cto = new TransferObject();
            cto.setOperation(Constants.GET_SCENES);
            outSocket = new ObjectOutputStream(socket.getOutputStream());
            outSocket.writeObject(cto);

            inSocket = new ObjectInputStream(socket.getInputStream());
            sto = (TransferObject) inSocket.readObject();
        } catch (IOException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        return sto;
    }

    public TransferObject getShows() {
        TransferObject sto = new TransferObject();
        try {
            TransferObject cto = new TransferObject();
            cto.setOperation(Constants.GET_SHOWS);
            outSocket = new ObjectOutputStream(socket.getOutputStream());
            outSocket.writeObject(cto);

            inSocket = new ObjectInputStream(socket.getInputStream());
            sto = (TransferObject) inSocket.readObject();
        } catch (IOException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        return sto;
    }

    public TransferObject getPerformances() {
        TransferObject sto = new TransferObject();
        try {
            TransferObject cto = new TransferObject();
            cto.setOperation(Constants.GET_PERFORMANCES);
            outSocket = new ObjectOutputStream(socket.getOutputStream());
            outSocket.writeObject(cto);

            inSocket = new ObjectInputStream(socket.getInputStream());
            sto = (TransferObject) inSocket.readObject();
        } catch (IOException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        return sto;
    }

    public TransferObject getPerformances(Play p, Stage s) {
        TransferObject sto = new TransferObject();
        try {
            TransferObject cto = new TransferObject();
            cto.setOperation(Constants.GET_PERFORMANCES_FOR_PLAY_STAGE);
            Object[] ps = {p,s};
            cto.setData(ps);
            outSocket = new ObjectOutputStream(socket.getOutputStream());
            outSocket.writeObject(cto);

            inSocket = new ObjectInputStream(socket.getInputStream());
            sto = (TransferObject) inSocket.readObject();
        } catch (IOException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        return sto;
    }

    public TransferObject savePerformance(Performance perf) {
        TransferObject sto = new TransferObject();
        try {
            TransferObject cto = new TransferObject();
            cto.setData(perf);
            cto.setOperation(Constants.SAVE_PERFORMANCE);
            outSocket = new ObjectOutputStream(socket.getOutputStream());
            outSocket.writeObject(cto);
            
            inSocket = new ObjectInputStream(socket.getInputStream());
            sto = (TransferObject) inSocket.readObject();
        } catch (IOException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        return sto;
    }

    public TransferObject getTickets(Performance perf) {
        TransferObject sto = new TransferObject();
        try {
            TransferObject cto = new TransferObject();
            cto.setOperation(Constants.GET_TICKETS_FOR_PERFORMANCE);
            cto.setData(perf);
            System.out.println("PERF IN GETT: " + perf.getId());
            outSocket = new ObjectOutputStream(socket.getOutputStream());
            outSocket.writeObject(cto);

            inSocket = new ObjectInputStream(socket.getInputStream());
            sto = (TransferObject) inSocket.readObject();
        } catch (IOException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        return sto;
    }

    public TransferObject saveTickets(Set<Ticket> ticketsInStore) {
        TransferObject sto = new TransferObject();
        try {
            TransferObject cto = new TransferObject();
            cto.setOperation(Constants.SAVE_TICKETS);
            cto.setData(ticketsInStore);
            outSocket = new ObjectOutputStream(socket.getOutputStream());
            outSocket.writeObject(cto);

            inSocket = new ObjectInputStream(socket.getInputStream());
            sto = (TransferObject) inSocket.readObject();
        } catch (IOException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        return sto;
    }
    
    public TransferObject reserveTicket(TicketX ticket){
        TransferObject sto = new TransferObject();
        try {
            TransferObject cto = new TransferObject();
            cto.setOperation(Constants.RESERVE_TICKET);
            cto.setData(ticket);
            outSocket = new ObjectOutputStream(socket.getOutputStream());
            outSocket.writeObject(cto);

            inSocket = new ObjectInputStream(socket.getInputStream());
            sto = (TransferObject) inSocket.readObject();
        } catch (IOException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        return sto;
        
    }

    private void connectToServer() {
        try {
            setSocket(new Socket("127.0.0.1", 9000));
            
            Socket socket2 = new Socket("127.0.0.1", 9001);
            Thread t;
            t = new Thread(() -> {
                ObjectInputStream inSoket = null;

                while (true) {
                    try{
                        inSoket = new ObjectInputStream(socket2.getInputStream());
                        TransferObject sto = (TransferObject) inSoket.readObject();
                        int operation = sto.getOperation();
                        
                        switch (operation) {
                            
                            case Constants.RESERVE_TICKET:
                                TicketX obj = (TicketX) sto.getData();

                                System.out.println("perf id: " + obj.getPerformanceId());
                            break;
                            
                        }
                        
                        
                    }catch(ClassNotFoundException | IOException e){
                    
                    }
                }
            });
            t.start();
        } catch (IOException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

  

}
