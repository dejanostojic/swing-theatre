/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dostojic.theaterserver.so.stage;

import com.dostojic.common.model.Seat;
import com.dostojic.common.model.Stage;
import com.dostojic.theaterserver.db.dao.SeatDao;
import com.dostojic.theaterserver.db.dao.StageDao;
import com.dostojic.theaterserver.db.util.QueryUtils;
import com.dostojic.theaterserver.so.GenericSO;

/**
 *
 * @author dejan
 */
public class SaveStageWithSeatsOperation extends GenericSO{


    @Override
    protected void executeOperation(Object... args) throws Exception {
        Stage stage = (Stage) args[0];
        Seat[][] seatMatrix = (Seat[][]) args[1];
        StageDao.getInstance().insert(stage);
        long stageId = stage.getId();
        System.out.println("STAGE ID::: " + stageId);
        System.out.println("STAGE ID::: " + stageId);
        System.out.println("STAGE ID::: " + stageId);
        System.out.println("STAGE ID::: " + stageId);
        System.out.println("STAGE ID::: " + stageId);
        System.out.println("STAGE ID::: " + stageId);
        System.out.println("STAGE ID::: " + stageId);
        System.out.println("STAGE ID::: " + stageId);
        System.out.println("STAGE ID::: " + stageId);
        System.out.println("STAGE ID::: " + stageId);
        System.out.println("STAGE ID::: " + stageId);
        System.out.println("STAGE ID::: " + stageId);
        System.out.println("STAGE ID::: " + stageId);
        for(Seat[] seatArray : seatMatrix){
            for (Seat seat : seatArray){
                seat.setStageId(stageId);
                SeatDao.getInstance().insert(seat);
            }       
        }
        
    }

    @Override
    protected void checkPrecondition(Object... args) throws Exception {
        Stage stage = (Stage) args[0];
        int s = StageDao.getInstance().count("name="+ QueryUtils.stringLiteral(stage.getName()));
        if (s > 0){
            throw new Exception("Već postoji scena sa tim nazivom");
        }
    }
   
}
