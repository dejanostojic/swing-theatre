/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dostojic.common.model.ext;

import com.dostojic.common.model.Seat;
import com.dostojic.common.model.Ticket;

/**
 *
 * @author dejan
 */
public class TicketX extends Ticket{
    
    private Seat seat;

    public Seat getSeat() {
        return seat;
    }

    public void setSeat(Seat seat) {
        this.seat = seat;
    }
    
}
