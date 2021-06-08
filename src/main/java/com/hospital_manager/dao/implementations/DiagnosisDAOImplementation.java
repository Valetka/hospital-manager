package com.hospital_manager.dao.implementations;

import com.hospital_manager.dao.DiagnosisDAO;
import com.hospital_manager.dao.connection.ConnectionPool;
import com.hospital_manager.dao.connection.PoolProvider;
import com.hospital_manager.dao.connection.exception.ConnectionPoolException;
import com.hospital_manager.dao.exceptions.DAOException;
import com.hospital_manager.entities.Diagnosis;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DiagnosisDAOImplementation implements DiagnosisDAO {

    private static final Logger logger = LogManager.getLogger(DiagnosisDAOImplementation.class);

    private static final String INSERT_DIAGNOSIS = "insert into diagnosis(definitive_diagnosis,date_of_receip,date_of_issue,patient,preliminary_diagnosis,history_id) values(?,?,?,?,?,?)";
    private static final String UPDATE_DIAGNOSIS = "update diagnosis set definitive_diagnosis = ?, date_of_issue = ?, history_id = ? where id = ?";
    private static final String SELECT_BY_PATIENT = "select * from diagnosis where patient = ?";


    private final ConnectionPool connectionPool = PoolProvider.getConnectionPool();

    @Override
    public void addDiagnosis(Diagnosis diagnosis) throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = connectionPool.getConnection();
            preparedStatement = connection.prepareStatement(INSERT_DIAGNOSIS);

            preparedStatement.setString(1,diagnosis.getDefinitiveDiagnosis());
            preparedStatement.setDate(2,diagnosis.getReceiptDate());
            preparedStatement.setDate(3,diagnosis.getDischargeDate());
            preparedStatement.setLong(4,diagnosis.getPatientId());
            preparedStatement.setString(5,diagnosis.getPreliminaryDiagnosis());
            if(diagnosis.getMedicalHistoryId()==0)
            { preparedStatement.setString(6,null);}
            else
            { preparedStatement.setLong(6,diagnosis.getMedicalHistoryId());}
            preparedStatement.execute();

        } catch (SQLException | ConnectionPoolException e) {
            logger.log(Level.ERROR,e);
            throw new DAOException("insert error ",e);
        }finally {
            connectionPool.releaseConnection(connection);
            if(preparedStatement!=null){
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    logger.log(Level.ERROR,e);
                    throw new DAOException("Close preparedStatement error ", e);
                }
            }
        }
    }

    @Override
    public List<Diagnosis> getDiagnosisByPatientId(long patientId) throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        List<Diagnosis> diagnosisList = new ArrayList<>();
        try {
            connection = connectionPool.getConnection();
            preparedStatement = connection.prepareStatement(SELECT_BY_PATIENT);
            preparedStatement.setLong(1,patientId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                Diagnosis diagnosis = mappingDiagnosis(resultSet);
                diagnosisList.add(diagnosis);
            }
        } catch (SQLException | ConnectionPoolException e) {
            logger.log(Level.ERROR,e);
            throw new DAOException("select error ",e);
        }finally {
            connectionPool.releaseConnection(connection);
            if(preparedStatement!=null){
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    logger.log(Level.ERROR,e);
                    throw new DAOException("Close preparedStatement error ", e);
                }
            }
        }
        return diagnosisList;
    }

    @Override
    public void update(Diagnosis diagnosis) throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = connectionPool.getConnection();
            preparedStatement = connection.prepareStatement(UPDATE_DIAGNOSIS);
            preparedStatement.setString(1,diagnosis.getDefinitiveDiagnosis());
            preparedStatement.setDate(2,diagnosis.getDischargeDate());
            if(diagnosis.getMedicalHistoryId() == 0)
                preparedStatement.setString(3,null);
            else
                preparedStatement.setLong(3,diagnosis.getMedicalHistoryId());
            preparedStatement.setLong(4,diagnosis.getId());
            preparedStatement.execute();
        } catch (SQLException | ConnectionPoolException e) {
            logger.log(Level.ERROR,e);
            throw new DAOException("update error ",e);
        }finally {
            connectionPool.releaseConnection(connection);
            if(preparedStatement!=null){
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    logger.log(Level.ERROR,e);
                    throw new DAOException("Close preparedStatement error ", e);
                }
            }
        }
    }

    private Diagnosis mappingDiagnosis(ResultSet resultSet) throws SQLException {
        Diagnosis diagnosis = new Diagnosis();
        diagnosis.setId(resultSet.getInt(1));
        diagnosis.setPreliminaryDiagnosis(resultSet.getString(2));
        diagnosis.setDefinitiveDiagnosis(resultSet.getString(3));
        diagnosis.setReceiptDate(resultSet.getDate(4));
        diagnosis.setDischargeDate(resultSet.getDate(5));
        diagnosis.setPatientId(resultSet.getLong(6));
        return diagnosis;

    }
}
