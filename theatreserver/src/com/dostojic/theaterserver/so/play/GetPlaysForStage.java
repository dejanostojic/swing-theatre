/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dostojic.theaterserver.so.play;

import com.dostojic.common.model.Performance;
import com.dostojic.common.model.Play;
import com.dostojic.common.model.Stage;
import com.dostojic.theaterserver.db.dao.PerformanceDao;
import com.dostojic.theaterserver.db.dao.PlayDao;
import com.dostojic.theaterserver.so.GenericSO;
import java.util.List;

/**
 *
 * @author dostojic
 */
public class GetPlaysForStage extends GenericSO{

    List<Play> plays;
    
    @Override
    protected void checkPrecondition(Object... args) throws Exception {
    }

    @Override
    protected void executeOperation(Object... args) throws Exception {
        Stage s = (Stage) args[0];
        
        StringBuilder playIds = new StringBuilder("(0");
        List<Performance> perfs = PerformanceDao.getInstance().loadList("stage_id=" + s.getId(), "");
        
        for (Performance p : perfs){
            playIds.append(",").append(p.getPlayId());
        }
        
        playIds.append(")");
        plays = PlayDao.getInstance().loadList("id in " + playIds.toString() , "");
                
                
        
    }

    public List<Play> getPlays() {
        return plays;
    }
    
    
    
}
