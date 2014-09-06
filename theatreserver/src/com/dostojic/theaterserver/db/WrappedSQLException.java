/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dostojic.theaterserver.db;

import com.dostojic.common.util.WrappedException;
import java.sql.SQLException;

/**
 *
 * @author dejan
 */
public class WrappedSQLException extends WrappedException {
    
    public WrappedSQLException(SQLException e) {
        super(e);
    }
    
    @Override
    public SQLException getCause() {
        return (SQLException) super.getCause();
    }
}
