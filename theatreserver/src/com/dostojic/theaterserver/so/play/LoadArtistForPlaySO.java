/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dostojic.theaterserver.so.play;

import com.dostojic.common.model.Artist;
import com.dostojic.common.model.ext.ArtistPlayX;
import com.dostojic.theaterserver.db.dao.ArtistDao;
import com.dostojic.theaterserver.db.dao.ArtistPlayDao;
import com.dostojic.theaterserver.so.GenericSO;
import java.util.List;

/**
 *
 * @author dejan
 */
public class LoadArtistForPlaySO extends GenericSO{

    private List<ArtistPlayX> apList;
    


    @Override
    protected void checkPrecondition(Object... args) throws Exception {
    }

    @Override
    protected void executeOperation(Object... args) throws Exception {
        String where = "play_id="+((Long) args[0]).longValue();
        apList = ArtistPlayDao.getInstance().loadList(where,"id desc", ArtistPlayX.class);
        for (ArtistPlayX ap : apList){
            Artist artist = ArtistDao.getInstance().loadByPk(ap.getArtistId());
            ap.setArtist(artist);
        }
    }

    public List<ArtistPlayX> getApList() {
        return apList;
    }
    
    
    
}
