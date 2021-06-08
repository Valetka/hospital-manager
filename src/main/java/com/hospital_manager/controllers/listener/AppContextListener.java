package com.hospital_manager.controllers.listener;


import com.hospital_manager.dao.connection.PoolProvider;
import com.hospital_manager.dao.connection.exception.ConnectionPoolException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;


public class AppContextListener implements ServletContextListener {


    private static final Logger logger = LogManager.getLogger(AppContextListener.class);

    private static final String UNABLE_TO_INIT_POOL = "Error while initializing pool data";
    private static final String ERROR_ON_CLOSING_POOL = "Error while closing connection pool";
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        try {
            PoolProvider.getConnectionPool().init("postgres");
        } catch (ConnectionPoolException e){
            logger.log(Level.FATAL,UNABLE_TO_INIT_POOL);
            throw new RuntimeException(UNABLE_TO_INIT_POOL,e);

        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        try {
            PoolProvider.getConnectionPool().dispose();
        } catch (ConnectionPoolException e){
            logger.log(Level.FATAL,ERROR_ON_CLOSING_POOL);
            throw new RuntimeException(ERROR_ON_CLOSING_POOL,e);
        }
    }
}
