/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dostojic.theaterserver.so.play;

import com.dostojic.theaterserver.db.dao.ArtistPlayDao;
import com.dostojic.theaterserver.db.dao.PlayDao;
import com.dostojic.common.model.ArtistPlay;
import com.dostojic.common.model.Play;
import com.dostojic.common.model.ext.ArtistPlayX;
import com.dostojic.theaterserver.so.GenericSO;
import java.util.List;

/**
 *
 * @author dejan
 */
public class SavePlayAndArtistsSO extends GenericSO{


    @Override
    protected void checkPrecondition(Object... args) throws Exception {
        
    }

    @Override
    protected void executeOperation(Object... args) throws Exception {
        Play play = (Play) args[0];
        PlayDao.getInstance().insert(play);
        long playId = play.getId();
        List<ArtistPlayX> artistPlayList = (List<ArtistPlayX>) args[1];
        for (ArtistPlay ap: artistPlayList){
            ap.setPlayId(playId);
            ArtistPlayDao.getInstance().insert(ap);
        }
    }
    
}
