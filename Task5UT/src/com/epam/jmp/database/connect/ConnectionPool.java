package com.epam.jmp.database.connect;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import org.apache.log4j.Logger;

/**
 * ConnectionPool create and manage pool of database connections.
 * 
 */
public class ConnectionPool {

    /** free connections queue */
    private BlockingQueue<Connection> connectionQueue;
    private static final Logger log = Logger.getLogger(ConnectionPool.class);

    public ConnectionPool(String driverName, String URL, String user,
            String password, int poolSize) {

        try {
            Class.forName(driverName);
            //DriverManager.registerDriver(driver);
            log.info("Register JDBC driver");
        } catch (ClassNotFoundException ex) {
            if (log.isDebugEnabled()) {
                log.error(ConnectionPool.class.getName(), ex);
            }
        }

        connectionQueue = new ArrayBlockingQueue<Connection>(poolSize);

        for (int i = 0; i < poolSize; i++) {
            Connection connection = null;
            try {
                connection = DriverManager.getConnection(URL, user, password);
            } catch (SQLException e) {
                if (log.isDebugEnabled()) {
                    log.error(ConnectionPool.class.getName(), e);
                }
            }
            if (connection != null) {
                connectionQueue.offer(connection);
            }
        }
    }

    
    public Connection getConnection() {
        Connection connection = null;
        try {            
            connection = connectionQueue.take();           
        } catch (InterruptedException ex) {
            if(log.isDebugEnabled()){  
               log.error(ConnectionPool.class.getName(), ex);
            }
        }
        return connection;
    }

    public void closeConnection(Connection connection) {
            
            connectionQueue.offer(connection);            
        
    }

    public void destroyPool() {
        
        Iterator<Connection> iterator = connectionQueue.iterator();
        while(iterator.hasNext()){
            connectionQueue.remove(iterator.next());
        }
       
    }
}
