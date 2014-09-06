/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dostojic.theaterserver.so.stage;

import com.dostojic.common.model.Stage;
import com.dostojic.theaterserver.db.dao.StageDao;
import com.dostojic.theaterserver.so.GenericSO;
import java.util.List;

/**
 *
 * @author dejan
 */
public class GetStagesOperation extends GenericSO{

    List<Stage> stages;

    public List<Stage> getStages() {
        return stages;
    }
    
    @Override
    protected void checkPrecondition(Object... args) throws Exception {
    }

    @Override
    protected void executeOperation(Object... args) throws Exception {
        stages = StageDao.getInstance().loadAll();
    }
    
}
