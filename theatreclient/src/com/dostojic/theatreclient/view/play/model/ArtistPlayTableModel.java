/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dostojic.theatreclient.view.play.model;

import com.dostojic.common.model.Artist;
import com.dostojic.common.model.ArtistPlay;
import com.dostojic.common.model.ext.ArtistPlayX;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author dejan
 */
public class ArtistPlayTableModel extends AbstractTableModel {

    private List<ArtistPlayX> artistPlayList;
//    private List<Artist> artists;
    private String[] columns = new String[]{"Prezime", "Ime", "Uloga"};

    public List<ArtistPlayX> getArtistPlayList() {
        return artistPlayList;
    }

    public void setArtistPlayList(List<ArtistPlayX> artistPlayList) {
        this.artistPlayList = artistPlayList;
        fireTableDataChanged();
    }

    @Override
    public int getRowCount() {
        return artistPlayList.size() + 1;
    }

    @Override
    public int getColumnCount() {
        return columns.length;
    }
//
//    @Override
//    public Object getValueAt(int rowIndex, int columnIndex) {
//        if (rowIndex < artistPlayList.size() && rowIndex < artists.size()) {
//            switch (columnIndex) {
//                case 0:
//                    return artists.get(rowIndex).getFirstName();
//                case 1:
//                    return artists.get(rowIndex).getLastName();
//                case 2:
//                    return artistPlayList.get(rowIndex).getRole();
//                default:
//                    return "";
//            }
//        }
//        return "dodaj";
//    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        if (rowIndex < artistPlayList.size()) {
            Artist artist = artistPlayList.get(rowIndex).getArtist();
            switch (columnIndex) {
                case 0:
                    if (artist != null)
                        return artist.getFirstName();
                    break;
                case 1:
                    if (artist != null){
                        return artist.getLastName();
                    }
                    break;
                case 2:
                    return artistPlayList.get(rowIndex).getRole();
                default:
                    return "";
            }
        }
        return "dodaj";
    }

    @Override
    public String getColumnName(int column) {
        return columns[column];
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return true;
    }

    public ArtistPlayTableModel() {
        artistPlayList = new ArrayList<ArtistPlayX>();
//        artists = new ArrayList<Artist>();
    }
/*
    public void setArtistAtRow(int row, Artist artist) {
        if (artists.size() <= row){
            artists.add(row, artist);
        }
        else{ 
            artists.set(row, artist);
        }
        ArtistPlay newAp = new ArtistPlay();
        newAp.setArtistId(artist.getId());
        
        if (artistPlayList.size() <= row)
            artistPlayList.add(row, newAp);
        else{
            ArtistPlay ap = artistPlayList.get(row);
            if (ap == null){
                artistPlayList.set(row, newAp);
            }else{
                artistPlayList.get(row).setArtistId(row);
            }
        }
        fireTableDataChanged();
    }
*/
    public void setArtistAtRow(int row, Artist artist) {
        ArtistPlayX newAp = new ArtistPlayX();
        newAp.setArtistId(artist.getId());
        newAp.setArtist(artist);
        
        if (artistPlayList.size() <= row){
            artistPlayList.add(row, newAp);
        }else{
            ArtistPlay ap = artistPlayList.get(row);
            if (ap == null){
                artistPlayList.set(row, newAp);
            }else{
                artistPlayList.get(row).setArtistId(row);
                artistPlayList.get(row).setArtist(artist);
            }
        }
        fireTableDataChanged();
    }
/*
    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        
        if (columnIndex == 2 && !aValue.toString().equals("dodaj")){
            String role = aValue.toString();
            
            if (artistPlayList.size() <= rowIndex){
                ArtistPlay ap = new ArtistPlay();
                ap.setRole(role);    
                artistPlayList.add(ap);
                if (artists.size() <= rowIndex){
                    artists.add(new Artist());
                }
            }else{
                artistPlayList.get(rowIndex).setRole(role);
            }
            fireTableDataChanged();

        }
        
    }
*/
    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        
        if (columnIndex == 2 && !aValue.toString().equals("dodaj")){
            String role = aValue.toString();
            
            if (artistPlayList.size() <= rowIndex){
                ArtistPlayX ap = new ArtistPlayX();
                ap.setRole(role);
                artistPlayList.add(ap);
            }else{
                artistPlayList.get(rowIndex).setRole(role);
            }
            fireTableDataChanged();

        }
        
    }
    
    
}
