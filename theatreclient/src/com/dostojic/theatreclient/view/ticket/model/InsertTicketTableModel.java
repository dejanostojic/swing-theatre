/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dostojic.theatreclient.view.ticket.model;

import com.dostojic.common.model.Seat;
import com.dostojic.common.model.Ticket;
import com.dostojic.common.model.TicketStatus;
import com.dostojic.common.model.ext.TicketX;
import com.dostojic.theatreclient.controller.Controller;
import java.awt.Color;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author dejan
 */
public class InsertTicketTableModel extends AbstractTableModel {

    TicketX[][] ticketMatrix;
    Set<TicketX> ticketsInStore;
    public InsertTicketTableModel() {
        ticketsInStore = new HashSet<TicketX>();
    }

    @Override
    public int getRowCount() {
        if (ticketMatrix == null) {
            return 0;
        }
        return ticketMatrix.length;
    }

    @Override
    public int getColumnCount() {
        if (ticketMatrix == null){
            return 0;
        }
        if (ticketMatrix != null && ticketMatrix[0] != null) {
            return ticketMatrix[0].length + 1;
        }
        return 0;
    }

    @Override
    public String getColumnName(int column) {
        return column == 0 ? "" : "k" + column;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        if (columnIndex == 0) {
            return "r" + (rowIndex + 1);
        } else {
            return columnIndex;
        }
    }

    public void setTicketMatrix(List<TicketX> tickets) {
        int sumRows = 0;
        int sumCols = 0;

        for (TicketX t : tickets) {
            if (t.getSeat().getRow() == 1) { //  counts number of cols in first row (all are the same length)
                sumCols++;
            }
            if (t.getSeat().getColumn() == 1) { // counts number of rows in first coll (all are the same length)
                sumRows++;
            }
        }
        System.out.println("rows, cols: " + sumRows + ", " + sumCols);
        ticketMatrix = new TicketX[sumRows][sumCols];
        ticketsInStore = new HashSet<>();
        
        for (TicketX t : tickets) {
            Seat s = t.getSeat();
            if (s != null) {
                int row = s.getRow();
                int col = s.getColumn();
                ticketMatrix[row - 1][col - 1] = t;
            }
        }
        fireTableStructureChanged();
    }
    
    public int getStatus(int row, int col){
        if (col == 0){
            return -1;
        }else{
            try{
                return ticketMatrix[row][col-1].getStatus();
            }catch(Exception e){
                e.printStackTrace();
                return -11;
            }
        }
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return (columnIndex > 0 && getStatus(rowIndex, columnIndex) != TicketStatus.STATUS_SOLD);
    }
        
    public Color getColor(int row, int col){
        int status = getStatus(row, col);
        switch (status){
            case TicketStatus.STATUS_FREE:
                return Color.GREEN;
            case TicketStatus.STATUS_RESERVED:
                return Color.ORANGE;
            case TicketStatus.STATUS_SOLD:
                return Color.RED;
            case TicketStatus.STATUS_IN_STORE:
                return Color.BLUE;
        }
        return Color.WHITE;
    }
    
    public void addTicketInStore(int row, int col){
        TicketX ticket = ticketMatrix[row][col-1];
        System.out.println("ADED " + '\n' + "row: " + row + "\ncol: " + (col-1));
        System.out.println("TICKET trow:" +ticket.getSeat().getRow() + ", tcol: " + ticket.getSeat().getColumn());
        if (ticket.getStatus() == TicketStatus.STATUS_SOLD){
            System.out.println("Sold ticket, do nothing");
        }else if (!ticketsInStore.contains(ticket)){
            ticket.setStatus(TicketStatus.STATUS_IN_STORE);
            ticketsInStore.add(ticket);
            Controller.getInstance().reserveTicket(ticket);
        }else{
            ticketsInStore.remove(ticket);
            ticket.setStatus(TicketStatus.STATUS_FREE);
        }
        fireTableCellUpdated(row, col);
    }

    public Set<TicketX> getTicketsInStore() {
        return ticketsInStore;
    }
    
    public Set<Ticket> getTicketsForSave() {
        Set<Ticket> ret = new HashSet<Ticket>();
        Iterator<TicketX> it = ticketsInStore.iterator();
        while (it.hasNext()) {
            Ticket t = it.next();
            t.setStatus(TicketStatus.STATUS_SOLD);
            ret.add(t);
        }
        return ret;
    }
    
    public void sellTicketsFromStore(){
        Iterator<TicketX> it = ticketsInStore.iterator();
        while (it.hasNext()){
            TicketX ticket = it.next();
            int row = ticket.getSeat().getRow();
            int col = ticket.getSeat().getColumn();
            
            ticketMatrix[row-1][col-1].setStatus(TicketStatus.STATUS_SOLD);
            fireTableCellUpdated(row-1, col);
            JOptionPane.showMessageDialog(null, row-1 + ", "  + (col));
            it.remove();
        }
    }
   

}