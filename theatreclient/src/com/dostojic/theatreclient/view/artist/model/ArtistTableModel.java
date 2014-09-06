/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dostojic.theatreclient.view.artist.model;

import com.dostojic.common.model.Artist;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author dejan
 */
public class ArtistTableModel extends AbstractTableModel{

    List<Artist> artists;

    String [] columns = new String[] {"Ime", "Prezime", "O umetniku", "Vrsta", "Izmeni", "Obriši"};
    
    public ArtistTableModel(List<Artist> artists){
        this.artists = artists;
    }
    
    @Override
    public int getRowCount() {
        if (artists == null){
            return 0;
        }
        return artists.size();
    }
    
    @Override
    public int getColumnCount() {
        return columns.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Artist artist = artists.get(rowIndex);
        switch(columnIndex){
            case -1 : return artist.getId();     // imam id iz baze, ali ga ne prikazujem
            case 0 : return artist.getFirstName();
            case 1 : return artist.getLastName();
            case 2 : return artist.getBody();
            case 3 : return artist.getKind();
            case 4 : return "izmeni";
            case 5 : return "obrisi";
            default : return "GRESKA!";
        }
    }

    @Override
    public String getColumnName(int column) {
        return columns[column];
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        switch(columnIndex){
        case 4: 
        case 5: return true;
            default:return false;
        }
    }
    
    
    
    
    
    
    
    
}
