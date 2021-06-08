package com.hospital_manager.dao.implementations;

import com.hospital_manager.dao.MedicalHistoryDAO;
import com.hospital_manager.dao.connection.ConnectionPool;
import com.hospital_manager.dao.connection.PoolProvider;
import com.hospital_manager.dao.connection.exception.ConnectionPoolException;
import com.hospital_manager.dao.exceptions.DAOException;
import com.hospital_manager.entities.MedicalHistory;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MedicalHistoryDAOImplementation implements MedicalHistoryDAO {

    private static final Logger logger = LogManager.getLogger(MedicalHistoryDAOImplementation.class);

    private static final String INSERT_HISTORY = "insert into medical_history(patient) values(?)";
    private static final String SELECT_HISTORY = "select * from medical_history where patient = ?";
    private static final String UPDATE_HISTORY = "update medical_history SET patient = ? where id = ?";


    private final ConnectionPool connectionPool = PoolProvider.getConnectionPool();


    @Override
    public void add(MedicalHistory medicalHistory) throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = connectionPool.getConnection();
            preparedStatement = connection.prepareStatement(INSERT_HISTORY);
            preparedStatement.setLong(1, medicalHistory.getPatientId());
            preparedStatement.execute();
        } catch (SQLException | ConnectionPoolException e) {
            logger.log(Level.ERROR, e);
            throw new DAOException("add error ", e);
        } finally {
            connectionPool.releaseConnection(connection);
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    logger.log(Level.ERROR, e);
                    throw new DAOException("Close preparedStatement error ", e);
                }
            }
        }
    }

    @Override
    public MedicalHistory getByPatientId(long patientId) throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        MedicalHistory medicalHistory = null;
        try {
            connection = connectionPool.getConnection();
            preparedStatement = connection.prepareStatement(SELECT_HISTORY);
            preparedStatement.setLong(1, patientId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                medicalHistory = new MedicalHistory();
                medicalHistory.setId(resultSet.getLong(1));
                medicalHistory.setPatientId(resultSet.getLong(2));
            }

        } catch (SQLException | ConnectionPoolException e) {
            logger.log(Level.ERROR, e);
            throw new DAOException("find error ", e);
        } finally {
            connectionPool.releaseConnection(connection);
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    logger.log(Level.ERROR, e);
                    throw new DAOException("Close preparedStatement error ", e);
                }
            }
        }
        return medicalHistory;
    }

    @Override
    public void update(MedicalHistory medicalHistory) throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = connectionPool.getConnection();
            preparedStatement = connection.prepareStatement(UPDATE_HISTORY);

            preparedStatement.setLong(1, medicalHistory.getPatientId());
            preparedStatement.setLong(1, medicalHistory.getId());
            preparedStatement.execute();

        } catch (SQLException | ConnectionPoolException e) {
            logger.log(Level.ERROR, e);
            throw new DAOException("update error ", e);
        } finally {
            connectionPool.releaseConnection(connection);
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    logger.log(Level.ERROR, e);
                    throw new DAOException("Close preparedStatement error ", e);
                }
            }
        }
    }
}
