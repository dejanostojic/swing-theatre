/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dostojic.theaterserver.db;

import com.dostojic.common.util.WrappedException;
import com.dostojic.theaterserver.db.util.DBProperties;
import com.mysql.jdbc.jdbc2.optional.MysqlConnectionPoolDataSource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import javax.sql.DataSource;

/**
 *
 * @author dejan
 */

public class Database {
    
    private static ThreadLocal<Database> dbTLocal = new ThreadLocal<Database>();
    
    public static Database getCurrent() {
        Database instance = dbTLocal.get();
        if(instance == null) {
            instance = new Database();
            dbTLocal.set(instance);
        }
        return instance;
    }
    
    public static void clear() {
        try {
            Database instance = (Database) dbTLocal.get();
            if(instance != null) {
                if(instance.connection != null)
                    instance.connection.close();
                dbTLocal.set(null);
            }
        } catch (SQLException e){ throw new RuntimeException(e);}
    }
    
    public DataSource getDataSource() {
        MysqlConnectionPoolDataSource dataSource = new MysqlConnectionPoolDataSource();
        initDataSource(dataSource);
        return dataSource;
    }
    
    private void initDataSource(MysqlConnectionPoolDataSource ds) {
        try {
            ds.setURL(DBProperties.getInstance().getURL());
            ds.setUser(DBProperties.getInstance().getUser());
            ds.setPassword(DBProperties.getInstance().getPassword());
        } catch (IOException ex) {
            throw new WrappedException(ex);
        }
    }
    
    private Connection connection = null;
    
    
    public  Connection getConnection() {
        try {
            if(connection == null) {
                connection = getDataSource().getConnection();
            }
            
            return connection;
        } catch (SQLException e) {
            throw new WrappedSQLException(e);
        }
    }
    
    public Statement createStatement() {
        try {
            return getConnection().createStatement();
        } catch (SQLException e) {
            throw new WrappedSQLException(e);
        }
    }
    
    public PreparedStatement perepareStatement(String sql) {
        try {
            return getConnection().prepareStatement(sql);
        } catch (SQLException e) {
            throw new WrappedSQLException(e);
        }
    }
    
    public void beginTransaction()  {
        try {
            getConnection().setAutoCommit(false);
        } catch (SQLException e) {
            throw new WrappedSQLException(e);
        }
    }
    
    public boolean isInTransaction() {
        try {
            return ! getConnection().getAutoCommit();
        } catch (SQLException e) {
            throw new WrappedSQLException(e);
        }
    }
    public void rollback() {
        try {
            if(isInTransaction()) {
                getConnection().rollback();
                getConnection().setAutoCommit(true);
            }
        } catch (SQLException e) {
            throw new WrappedSQLException(e);
        }
    }
    
    public void commit()  {
        try {
            connection.commit();
            connection.setAutoCommit(true);
        } catch (SQLException e) {
            throw new WrappedSQLException(e);
        }
    }

}
