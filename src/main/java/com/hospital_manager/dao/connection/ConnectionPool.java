package com.hospital_manager.dao.connection;

import com.hospital_manager.dao.connection.exception.ConnectionPoolException;

import java.sql.Connection;

public interface ConnectionPool {

    Connection getConnection() throws ConnectionPoolException;


    void releaseConnection(Connection connection);


    void init(String bundle) throws ConnectionPoolException;


    void dispose()throws ConnectionPoolException;
}
