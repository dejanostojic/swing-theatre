/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dostojic.theaterserver.so.user;

import com.dostojic.theaterserver.db.dao.UserDao;
import com.dostojic.common.model.User;
import com.dostojic.theaterserver.so.GenericSO;

/**
 *
 * @author dejan
 */
public class SaveUserSO extends GenericSO{


    @Override
    protected void checkPrecondition(Object... args) throws Exception {
        
    }

    @Override
    protected void executeOperation(Object... args) throws Exception {
        UserDao.getInstance().insert((User) args[0]);
    }
    
}
