/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dostojic.theatreclient.view.play.model;

import com.dostojic.common.model.Artist;
import java.util.List;
import javax.swing.AbstractListModel;

/**
 *
 * @author dejan
 */
public class ArtistListModel extends AbstractListModel<Artist> {

    private List<Artist> artists;

    public ArtistListModel(List<Artist> artists) {
        this.artists = artists;
    }
    
    @Override
    public int getSize() {
        return artists.size();
    }

    @Override
    public Artist getElementAt(int index) {
        return artists.get(index);
    }

    public List<Artist> getArtists() {
        return artists;
    }

    public void setArtists(List<Artist> artists) {
        this.artists = artists;
    }
    
    
    
}
