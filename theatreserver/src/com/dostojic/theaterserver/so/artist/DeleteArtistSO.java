/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dostojic.theaterserver.so.artist;

import com.dostojic.theaterserver.db.dao.ArtistDao;
import com.dostojic.common.model.Artist;
import com.dostojic.theaterserver.so.GenericSO;


/**
 *
 * @author dejan
 */
public class DeleteArtistSO extends GenericSO{

//    @Override
//    protected void checkPrecondition() throws Exception {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
//
//    @Override
//    protected void executeOperation() throws Exception {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
//
//    @Override
//    protected void checkPrecondition(Object o) throws Exception {
//        
//    }

    @Override
    protected void executeOperation(Object... args) throws Exception {
        ArtistDao.getInstance().delete((Artist) args[0]);
    }

    @Override
    protected void checkPrecondition(Object... args) throws Exception {
    }
    
}
