/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dostojic.theaterserver.so.artist;

import com.dostojic.common.model.Artist;
import com.dostojic.theaterserver.db.dao.ArtistDao;
import com.dostojic.theaterserver.db.util.QueryUtils;
import com.dostojic.theaterserver.so.GenericSO;
import java.util.List;

/**
 *
 * @author dostojic
 */
public class GetArtistsByNameSO extends GenericSO{
    private List<Artist> artists;

    public List<Artist> getArtists() {
        return artists;
    }
    
    @Override
    protected void executeOperation(Object... args) throws Exception {
        
        String where = "first_name like " + QueryUtils.mySqlLikeLiteral((String) args[0]) + " and last_name like" +  QueryUtils.mySqlLikeLiteral((String) args[1]);
        System.out.println("WHERE >>> " + where);
        artists = ArtistDao.getInstance().loadList(where, null);
    }

    @Override
    protected void checkPrecondition(Object... args) throws Exception {
    }

}