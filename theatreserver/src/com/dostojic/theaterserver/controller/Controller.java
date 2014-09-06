/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dostojic.theaterserver.controller;

import com.dostojic.common.model.Artist;
import com.dostojic.common.model.Performance;
import com.dostojic.common.model.Play;
import com.dostojic.common.model.Seat;
import com.dostojic.common.model.Stage;
import com.dostojic.common.model.Ticket;
import com.dostojic.common.model.User;
import com.dostojic.common.model.ext.ArtistPlayX;
import com.dostojic.common.model.ext.TicketX;
import com.dostojic.theaterserver.server.ClientThread;
import com.dostojic.theaterserver.server.InformTicketStatus;
import com.dostojic.theaterserver.server.ThreadInformTicketReserved;
import com.dostojic.theaterserver.so.artist.DeleteArtistSO;
import com.dostojic.theaterserver.so.artist.GetArtistsSO;
import com.dostojic.theaterserver.so.artist.SaveArtistSO;
import com.dostojic.theaterserver.so.artist.UpdateArtistSO;
import com.dostojic.theaterserver.so.login.GetUsersSO;
import com.dostojic.theaterserver.so.login.LoginSO;
import com.dostojic.theaterserver.so.performance.GetAllPerformances;
import com.dostojic.theaterserver.so.performance.SavePerformanceOperation;
import com.dostojic.theaterserver.so.play.DeletePlaySO;
import com.dostojic.theaterserver.so.play.GetPlaySO;
import com.dostojic.theaterserver.so.play.LoadArtistForPlaySO;
import com.dostojic.theaterserver.so.play.SavePlayAndArtistsSO;
import com.dostojic.theaterserver.so.stage.GetStagesOperation;
import com.dostojic.theaterserver.so.stage.SaveStageWithSeatsOperation;
import com.dostojic.theaterserver.so.ticket.GetTicketsSO;
import com.dostojic.theaterserver.so.ticket.SaveTicketsSO;
import com.dostojic.theaterserver.so.user.SaveUserSO;
import java.util.List;
import java.util.Set;

/**
 *
 * @author dejan
 */
public class Controller {
    
    private static Controller instance;
    
    public static Controller getInstance(){
        if (instance == null){
            instance = new Controller();
        }
        return instance;
    }
    
    public List<User> getUsers() throws Exception{
        GetUsersSO operation = new GetUsersSO();
        operation.execute();
        return operation.getUsers();
    }
    
    public User logInUser(String userName, String password) throws Exception{
        LoginSO operation = new LoginSO();
        operation.execute(userName, password);
        return operation.getUser();
    }

    public void save(User user) throws Exception {
        SaveUserSO operation = new SaveUserSO();
        operation.execute(user);
    }
    
    public void save(Artist artist) throws Exception {
        SaveArtistSO operation = new SaveArtistSO();
        operation.execute(artist);
    }

    public List<Artist> getArtists() throws Exception {
        GetArtistsSO operation = new GetArtistsSO();
        operation.execute();
        return operation.getArtists();
        
    }

    public void update(Artist artist) throws Exception {
        UpdateArtistSO operation = new UpdateArtistSO();
        operation.execute(artist);
    }

    public void delete(Artist artist) throws Exception {
        DeleteArtistSO operation = new DeleteArtistSO();
        operation.execute(artist);
    }
    
    public void save(Play play, List<ArtistPlayX> listArtistPlay) throws Exception{
        SavePlayAndArtistsSO operation = new SavePlayAndArtistsSO();
        operation.execute(play, listArtistPlay);
    }

    public List<Play> getPlays() throws Exception{
        GetPlaySO operation = new GetPlaySO();
        operation.execute();
        return operation.getPlays();
    }

    public void deletePlay(Long longValue) throws Exception {
        DeletePlaySO operation = new DeletePlaySO();
        operation.execute(longValue);
    }

    public List<ArtistPlayX> loadArtstsForPlay(Long playId) throws Exception {
        LoadArtistForPlaySO operation = new LoadArtistForPlaySO();
        operation.execute(playId);
        return operation.getApList();
    }
    
    public void save(Stage stage, Seat[][] seats) throws Exception{
        SaveStageWithSeatsOperation operation = new SaveStageWithSeatsOperation();
        operation.execute(stage, seats);
    }

    public List<Stage> getStages() throws Exception {
        GetStagesOperation operation = new GetStagesOperation();
        operation.execute();
        return operation.getStages();
    }

    public List<Performance> getPerformances() throws Exception {
        GetAllPerformances operation = new GetAllPerformances();
        operation.execute();
        return operation.getPerformances();
    }
    
    public void save(Performance perf) throws Exception{
        SavePerformanceOperation operation = new SavePerformanceOperation();
        operation.execute(perf);
    }

    public List<TicketX> getTickets(Performance performance) throws Exception {
        GetTicketsSO operation = new GetTicketsSO();
        operation.execute(performance);
        return operation.getTickets();
    }

    public void saveTickets(Set<Ticket> tickets) throws Exception{
        SaveTicketsSO operation = new SaveTicketsSO();
        operation.execute(tickets);
    }
    
    public void reserveTicket(TicketX ticket) throws Exception{
            InformTicketStatus.getInstance().informTicketReserved(ticket);
    }
    
}
