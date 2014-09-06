/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dostojic.theaterserver.so.play;

import com.dostojic.theaterserver.db.dao.PlayDao;
import com.dostojic.theaterserver.so.GenericSO;

/**
 *
 * @author dejan
 */
public class DeletePlaySO extends GenericSO{

    @Override
    protected void checkPrecondition(Object... args) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected void executeOperation(Object... args) throws Exception {
        PlayDao.getInstance().deleteByPk(((Long) args[0]).longValue());
    }
}
