package com.hospital_manager.dao.connection;

import com.hospital_manager.dao.connection.implementation.ConnectionPoolImplementation;

public class PoolProvider {

    private PoolProvider() {}

    private static ConnectionPool  connectionPool = new ConnectionPoolImplementation();

    public static ConnectionPool getConnectionPool() { return connectionPool;}
}
