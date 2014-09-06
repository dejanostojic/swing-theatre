/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dostojic.theaterserver.so.ticket;

import com.dostojic.common.model.Performance;
import com.dostojic.common.model.Seat;
import com.dostojic.common.model.ext.TicketX;
import com.dostojic.theaterserver.db.ResultSetProcessor;
import com.dostojic.theaterserver.db.dao.TicketDao;
import com.dostojic.theaterserver.db.util.QueryUtils;
import com.dostojic.theaterserver.so.GenericSO;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author dejan
 */
public class GetTicketsSO extends GenericSO{

    List<TicketX> tickets;

    public List<TicketX> getTickets() {
        return tickets;
    }
    
    
    @Override
    protected void checkPrecondition(Object... args) throws Exception {
    }

    @Override
    protected void executeOperation(Object... args) throws Exception {
        tickets = new ArrayList<>();
        Performance  perf= (Performance) args[0];
        String sql = "select * from "
                + "(select " + TicketDao.getInstance().getCommaSepColumnList() + ",seat.seat_row,seat.seat_column "
                + "from ticket left join seat on ticket.seat_id=seat.id where ticket.performance_id="+perf.getId()+") ticket order by ticket.seat_row,ticket.seat_column";
        System.out.println("DEBUG:::");
        System.out.println(sql);
        QueryUtils.forEach(sql, (resultSet) -> {
            TicketX t = new TicketX();
            TicketDao.getInstance().loadFromResultSet(resultSet, t);
            Seat s = new Seat();
            s.setId(t.getSeatId());
            s.setRow(resultSet.getInt("ticket.seat_row"));
            s.setColumn(resultSet.getInt("ticket.seat_column"));
            t.setSeat(s);
            tickets.add(t);
        });
        int i = 0;
        System.out.println("\"TICKTES: \" + ");
            for (TicketX t : tickets){
                System.out.println(++i + ":" + t.getSeat().getRow()+","+t.getSeat().getColumn()+"; ");
            }
    }
    
}
