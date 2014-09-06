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
public class SaveArtistSO extends GenericSO{

    @Override
    protected void checkPrecondition(Object... o) throws Exception {
        
    }

    @Override
    protected void executeOperation(Object... args) throws Exception {
        ArtistDao.getInstance().insert((Artist) args[0]);
    }
    
}
