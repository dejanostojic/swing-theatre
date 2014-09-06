/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dostojic.theaterserver.so.performance;

import com.dostojic.common.model.Performance;
import com.dostojic.common.model.Seat;
import com.dostojic.common.model.Ticket;
import com.dostojic.theaterserver.db.dao.PerformanceDao;
import com.dostojic.theaterserver.db.dao.SeatDao;
import com.dostojic.theaterserver.db.dao.TicketDao;
import com.dostojic.theaterserver.so.GenericSO;
import java.util.List;

/**
 *
 * @author dejan
 */
public class SavePerformanceOperation extends GenericSO{

    @Override
    protected void checkPrecondition(Object... args) throws Exception {
    }

    @Override
    protected void executeOperation(Object... args) throws Exception {
        Performance perf = (Performance) args[0];
        PerformanceDao.getInstance().insert(perf);
        List<Seat> seats = SeatDao.getInstance().loadByStageId(perf.getStageId());
        for (Seat s : seats){
            Ticket t = new Ticket();
            t.setSeatId(s.getId());
            t.setPerformanceId(perf.getId());
            TicketDao.getInstance().insert(t);
        }
    }
    
}
