/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dostojic.theaterserver.so.play;

import com.dostojic.common.model.Play;
import com.dostojic.theaterserver.db.dao.PlayDao;
import com.dostojic.theaterserver.db.util.QueryUtils;
import com.dostojic.theaterserver.so.GenericSO;
import java.util.List;

/**
 *
 * @author dostojic
 */
public class GetPlaysByName extends GenericSO{
    List<Play> plays;
    
    @Override
    protected void checkPrecondition(Object... args) throws Exception {
        
    }

    @Override
    protected void executeOperation(Object... args) throws Exception {
        String title = (String) args[0];
        plays = PlayDao.getInstance().loadList("title like " + QueryUtils.mySqlLikeLiteral(title), "title asc");
    }
    
    public List<Play> getPlays(){
        return plays;
    }
    
}
