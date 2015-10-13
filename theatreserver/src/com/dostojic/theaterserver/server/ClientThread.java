/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dostojic.theaterserver.server;

import com.dostojic.common.model.Artist;
import com.dostojic.common.model.Performance;
import com.dostojic.common.model.Play;
import com.dostojic.common.model.Seat;
import com.dostojic.common.model.Stage;
import com.dostojic.common.model.Ticket;
import com.dostojic.common.model.User;
import com.dostojic.common.model.ext.ArtistPlayX;
import com.dostojic.common.model.ext.TicketX;
import com.dostojic.theaterserver.controller.Controller;
import com.dostojic.common.transfer.Constants;
import com.dostojic.common.transfer.TransferObject;
import java.io.IOException;
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
public class ClientThread extends Thread {

    private Socket s;
    private List<ClientThread> clients;
    private ObjectInputStream inSoket;
    private ObjectOutputStream outSoket;
    private User user;
    public ClientThread(Socket s, List<ClientThread> clients) {
        this.s = s;
        this.clients = clients;
    }

    @Override
    public void run() {
        try {
            inSoket = null;
            outSoket = null;

            while (true) {
                try {
                    inSoket = new ObjectInputStream(s.getInputStream());
                    Object obj = inSoket.readObject();
                    TransferObject cto = (TransferObject) obj;
                    int operation = cto.getOperation();

                    TransferObject sto = new TransferObject();

                    switch (operation) {

                        case Constants.GET_USERS:
                            try {
                                List<User> users = Controller.getInstance().getUsers();
                                sto.setData(users);
                                sto.setOperationSucess(true);
                            } catch (Exception ex) {
                                sto.setOperationSucess(false);
                                sto.setMessage("Greška pri vraćanju članova");
                                Logger.getLogger(ClientThread.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            break;
                            
                        case Constants.LOG_IN_USER:
                            try {
                                String[] data = (String[]) cto.getData();
                                User user = Controller.getInstance().logInUser(data[0], data[1]);
                                sto.setData(user);
                                this.user = user;
                                sto.setOperationSucess(true);
                            } catch (Exception ex) {
                                sto.setOperationSucess(false);
                                sto.setMessage("Greška pri logovanju korisnika");
                                Logger.getLogger(ClientThread.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            break;

                        case Constants.SAVE_ARTIST:
                            try {
                                Controller.getInstance().save((Artist) cto.getData());
                                sto.setOperationSucess(true);
                                sto.setMessage("Umetnik uspešno sačuvan");
                            } catch (Exception ex) {
                                sto.setOperationSucess(false);
                                sto.setMessage("Greška pri čuvanju člana");
                                Logger.getLogger(ClientThread.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            break;
                            
                        case Constants.GET_ARTISTS:
                            try {
                                List<Artist> artists = Controller.getInstance().getArtists();
                                sto.setData(artists);
                                sto.setOperationSucess(true);
                                sto.setMessage("Uspešno su vraćeni umetnici");
                            } catch (Exception ex) {
                                sto.setOperationSucess(false);
                                sto.setMessage("Greška pri vraćanju umetnika");
                                Logger.getLogger(ClientThread.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            break;

                        case Constants.GET_ARTISTS_BY_NAME:
                            try {
                                List<Artist> artists = Controller.getInstance().getArtistsByName((String[]) cto.getData());
                                sto.setData(artists);
                                sto.setOperationSucess(true);
                                sto.setMessage("Uspešno su vraćeni umetnici");
                            } catch (Exception ex) {
                                sto.setOperationSucess(false);
                                sto.setMessage("Greška pri vraćanju umetnika");
                                Logger.getLogger(ClientThread.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            break;
                            
                        case Constants.UPDATE_ARTIST:
                            try {
                                Controller.getInstance().update((Artist) cto.getData());
                                sto.setOperationSucess(true);
                                sto.setMessage("Umetnik uspešno ažuriran.");
                            } catch (Exception ex) {
                                sto.setOperationSucess(false);
                                sto.setMessage("Greška pri čuvanju člana");
                                Logger.getLogger(ClientThread.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            break;
                            
                        case Constants.DELETE_ARTIST:
                            try {
                                Controller.getInstance().delete((Artist) cto.getData());
                                sto.setOperationSucess(true);
                                sto.setMessage("Umetnik uspešno obrisan.");
                            } catch (Exception ex) {
                                sto.setOperationSucess(false);
                                sto.setMessage("Greška pri čuvanju člana");
                                Logger.getLogger(ClientThread.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            break;
                            
                        case Constants.SAVE_PLAY_AND_ARTISTS:
                            Object[] dataArray = (Object[]) cto.getData();
                            try {
                                Controller.getInstance().save((Play) dataArray[0], (List<ArtistPlayX>) dataArray[1]);
                                sto.setOperationSucess(true);
                                sto.setMessage("Predstava uspešno sačuvana.");
                            } catch (Exception ex) {
                                sto.setOperationSucess(false);
                                sto.setMessage("Greška pri čuvanju predstave.");
                                Logger.getLogger(ClientThread.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            break;
                            
                        case Constants.UPDATE_PLAY_AND_ARTISTS:
                            dataArray = (Object[]) cto.getData();
                            try {
                                Controller.getInstance().update((Play) dataArray[0], (List<ArtistPlayX>) dataArray[1]);
                                sto.setOperationSucess(true);
                                sto.setMessage("Predstava uspešno sačuvana.");
                            } catch (Exception ex) {
                                sto.setOperationSucess(false);
                                sto.setMessage("Greška pri čuvanju predstave.");
                                Logger.getLogger(ClientThread.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            break;
                            
                        case Constants.GET_PLAYS:
                            try {
                                List<Play> plays = Controller.getInstance().getPlays();
                                sto.setData(plays);
                                sto.setOperationSucess(true);
                                sto.setMessage("Uspešno su vraćene predstave");
                            } catch (Exception ex) {
                                sto.setOperationSucess(false);
                                sto.setMessage("Greška pri vraćanju predstava");
                                Logger.getLogger(ClientThread.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            break;
                            
                        case Constants.GET_PLAYS_BY_NAME:
                            try {
                                
                                List<Play> plays = Controller.getInstance().getPlaysByName(((String) cto.getData()));
                                sto.setData(plays);
                                sto.setOperationSucess(true);
                                sto.setMessage("Uspešno su vraćene predstave");
                            } catch (Exception ex) {
                                sto.setOperationSucess(false);
                                sto.setMessage("Greška pri vraćanju predstava");
                                Logger.getLogger(ClientThread.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            break;
                            
                        case Constants.GET_PLAYS_FOR_STAGE:
                            try {
                                
                                List<Play> plays = Controller.getInstance().getPlays(((Stage) cto.getData()));
                                sto.setData(plays);
                                sto.setOperationSucess(true);
                                sto.setMessage("Uspešno su vraćene predstave");
                            } catch (Exception ex) {
                                sto.setOperationSucess(false);
                                sto.setMessage("Greška pri vraćanju predstava");
                                Logger.getLogger(ClientThread.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            break;
                            
                        case Constants.DELETE_PLAY:
                            try {
                                Long playId = (Long) cto.getData();
                                Controller.getInstance().deletePlay(playId);
                                sto.setOperationSucess(true);
                                sto.setMessage("Uspešno obrisana predstava");
                            } catch (Exception ex) {
                                sto.setOperationSucess(false);
                                sto.setMessage("Greška pri brisanju predstava");
                                Logger.getLogger(ClientThread.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            break;
                            
                        case Constants.GET_ARTISTS_FOR_PLAY:
                            try {
                                Long playId = (Long) cto.getData();
                                sto.setData(Controller.getInstance().loadArtstsForPlay(playId));
                                sto.setOperationSucess(true);
                                sto.setMessage("Uspešno vraceni umetnici");
                            } catch (Exception ex) {
                                sto.setOperationSucess(false);
                                sto.setMessage("Greška pri vracanju umetnika");
                                Logger.getLogger(ClientThread.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            break;
                            
                        case Constants.SAVE_STAGE_WITH_SEATS:
                            dataArray = (Object[]) cto.getData();
                            try {
                                Controller.getInstance().save((Stage) dataArray[0], (Seat[][]) dataArray[1]);
                                sto.setOperationSucess(true);
                                sto.setMessage("Scena uspešno sačuvana.");
                            } catch (Exception ex) {
                                sto.setOperationSucess(false);
                                sto.setMessage("Greška pri čuvanju scene.");
                                Logger.getLogger(ClientThread.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            break;
                            
                        case Constants.GET_SCENES:
                            try {
                                List<Stage> stages = Controller.getInstance().getStages();
                                sto.setData(stages);
                                sto.setOperationSucess(true);
                                sto.setMessage("Uspešno su vraćene scena");
                            } catch (Exception ex) {
                                sto.setOperationSucess(false);
                                sto.setMessage("Greška pri vraćanju scene");
                                Logger.getLogger(ClientThread.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            break;
                            
                        case Constants.GET_PERFORMANCES:
                            try {
                                List<Performance> perfs = Controller.getInstance().getPerformances();
                                sto.setData(perfs);
                                sto.setOperationSucess(true);
                                sto.setMessage("Uspešno su vraćene predstave");
                            } catch (Exception ex) {
                                sto.setOperationSucess(false);
                                sto.setMessage("Greška pri vraćanju predstava");
                                Logger.getLogger(ClientThread.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            break;
                            
                        case Constants.GET_PERFORMANCES_FOR_PLAY_STAGE:
                            try {
                                Object[] data = (Object[]) cto.getData();
                                List<Performance> perfs = Controller.getInstance().getPerformances((Play) data[0], (Stage) data[1]);
                                sto.setData(perfs);
                                sto.setOperationSucess(true);
                                sto.setMessage("Uspešno su vraćene predstave");
                            } catch (Exception ex) {
                                sto.setOperationSucess(false);
                                sto.setMessage("Greška pri vraćanju predstava");
                                Logger.getLogger(ClientThread.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            break;
                            
                        case Constants.SAVE_PERFORMANCE:
                            try {
                                Controller.getInstance().save((Performance)cto.getData());
                                sto.setOperationSucess(true);
                                sto.setMessage("Uspešno je sačuvano izvođenje");
                            } catch (Exception ex) {
                                sto.setOperationSucess(false);
                                sto.setMessage("Greška pri čuvanju izvođenja");
                                Logger.getLogger(ClientThread.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            break;
                            
                        case Constants.GET_TICKETS_FOR_PERFORMANCE:
                            try {
                                Performance perf = (Performance)cto.getData();
                                System.out.println("PERF>>> " + perf);
                                System.out.println("CT : " + perf.getId());
                                sto.setData(Controller.getInstance().getTickets(perf));
                                sto.setOperationSucess(true);
                                sto.setMessage("Uspešno vraceno izvodjenje");
                            } catch (Exception ex) {
                                sto.setOperationSucess(false);
                                sto.setMessage("Greška pri vraćanju izvodjenja");
                                Logger.getLogger(ClientThread.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            break;
                        case Constants.SAVE_TICKETS:
                            try {
                                Set<Ticket> tickets = (Set<Ticket>)cto.getData();
                                Controller.getInstance().saveTickets(tickets);
                                sto.setOperationSucess(true);
                                sto.setMessage("Uspešno je sacuvane karte");
                            } catch (Exception ex) {
                                sto.setOperationSucess(false);
                                sto.setMessage("Greška pri vraćanju karti");
                                Logger.getLogger(ClientThread.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            break;
                        case Constants.RESERVE_TICKET:
                            try {
                                TicketX ticket = (TicketX) cto.getData();
                                Controller.getInstance().reserveTicket(ticket);
                                sto.setOperationSucess(true);
//                                sto.setMessage("Uspešno je sacuvane karte");
                            } catch (Exception ex) {
                                sto.setOperationSucess(false);
//                                sto.setMessage("Greška pri vraćanju karti");
                                Logger.getLogger(ClientThread.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            break;
                                                        
                    }

                    outSoket = new ObjectOutputStream(s.getOutputStream());
                    outSoket.writeObject(sto);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(ClientThread.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        } //         
        catch (IOException ex) {
            System.out.println("removing from client list");
            clients.remove(this);
            Logger.getLogger(ClientThread.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                s.close();
            } catch (IOException ex) {
                Logger.getLogger(ClientThread.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    void stopThread() {
        synchronized (this) {
            this.interrupt();
            try {
                this.s.close();
            } catch (IOException ex) {
            }
        }
    }

    
    private void informStop(){
    
    }
    
    public ObjectOutputStream getOutSoket() {
        return outSoket;
    }

    public void setOutSoket(ObjectOutputStream outSoket) {
        this.outSoket = outSoket;
    }

    public Socket getS() {
        return s;
    }

    public User getUser() {
        return user;
    }
    
}
