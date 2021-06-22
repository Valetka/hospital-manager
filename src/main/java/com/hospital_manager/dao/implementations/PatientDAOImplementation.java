package com.hospital_manager.dao.implementations;

import com.hospital_manager.dao.PatientDAO;
import com.hospital_manager.dao.connection.ConnectionPool;
import com.hospital_manager.dao.connection.PoolProvider;
import com.hospital_manager.dao.connection.exception.ConnectionPoolException;
import com.hospital_manager.dao.exceptions.DAOException;
import com.hospital_manager.entities.Patient;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PatientDAOImplementation implements PatientDAO {

    private static final Logger logger = LogManager.getLogger(PatientDAOImplementation.class);

    private static final String  UPDATE_PATIENT ="UPDATE patients SET firstname= ?, lastname = ?, age = ?, " +
            " department_id = ?, attending_doctor = ?, status =?, account_id = ?, patient_pic = ? WHERE id = ? ";

    private static final String SELECT_PATIENTS = "SELECT * FROM patients WHERE attending_doctor =?";
    private static final String GET_FREE_PATIENTS ="select * from patients where attending_doctor is null and status = 3";
    private static final String GET_ALL_PATIENTS ="select * from patients where age is not null";
    private static final String SELECT_PATIENT_BY_ID = "SELECT * FROM patients WHERE id =?";
    private static final String SELECT_PATIENT_BY_ACCOUNT = "SELECT * FROM patients WHERE account_id =?";


    private final ConnectionPool connectionPool = PoolProvider.getConnectionPool();

    @Override
    public void update(Patient patient) throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = connectionPool.getConnection();
            preparedStatement = connection.prepareStatement(UPDATE_PATIENT);
            preparedStatement.setString(1,patient.getFirstname());
            preparedStatement.setString(2,patient.getLastname());
            preparedStatement.setInt(3,patient.getAge());
            preparedStatement.setLong(4, patient.getDepartment().getId());
            if(patient.getAttendingDoctorID()==0)
            { preparedStatement.setNull(5,0);}
            else{
            preparedStatement.setLong(5,patient.getAttendingDoctorID());}
            preparedStatement.setLong(6,patient.getStatusID());
            preparedStatement.setLong(7,patient.getAccountID());
            preparedStatement.setString(8,patient.getPatientPic());
            preparedStatement.setLong(9,patient.getId());
            preparedStatement.executeUpdate();
        } catch (ConnectionPoolException | SQLException e) {
            logger.log(Level.ERROR,e);
            throw new DAOException(e);
        }finally {
            connectionPool.releaseConnection(connection);
            try {
                if (preparedStatement != null && !preparedStatement.isClosed()) {
                    preparedStatement.close();
                }
            }catch (SQLException e){
                logger.log(Level.ERROR,e);
                throw new DAOException("Close preparedStatement error ", e);
            }
        }
    }


    @Override
    public List<Patient> getFreePatients() throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        List<Patient> patients = new ArrayList<>();
        try {
            connection = connectionPool.getConnection();
            preparedStatement = connection.prepareStatement(GET_FREE_PATIENTS);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Patient patient = patientMapping(resultSet);
                patients.add(patient);
            }
        } catch (SQLException | ConnectionPoolException throwables) {
            logger.log(Level.ERROR,throwables);
            throw new DAOException(throwables);
        }finally {
            connectionPool.releaseConnection(connection);
            try {
                if (preparedStatement != null && !preparedStatement.isClosed()) {
                    preparedStatement.close();
                }
            }catch (SQLException e){
                logger.log(Level.ERROR,e);
                throw new DAOException("Close preparedStatement error ", e);
            }
        }
        return patients;
    }

    @Override
    public List<Patient> getAllPatientsByStaff(long attendingDoctorId) throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        List<Patient> patients = new ArrayList<>();
        try {
            connection = connectionPool.getConnection();
            preparedStatement = connection.prepareStatement(SELECT_PATIENTS);
            preparedStatement.setLong(1, attendingDoctorId);
            ResultSet set = preparedStatement.executeQuery();
            while (set.next()) {
                Patient patient = patientMapping(set);
                patients.add(patient);
            }

        } catch (ConnectionPoolException | SQLException e) {
            logger.log(Level.ERROR,e);
            throw new DAOException(e);
        }finally {
            connectionPool.releaseConnection(connection);
            try {
                if (preparedStatement != null && !preparedStatement.isClosed()) {
                    preparedStatement.close();
                }
            }catch (SQLException e){
                logger.log(Level.ERROR,e);
                throw new DAOException("Close preparedStatement error ", e);
            }
        }
        return patients;
    }

    @Override
    public Patient getPatientById(Long id) throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        Patient patient = null;
        try {
            connection = connectionPool.getConnection();
            preparedStatement = connection.prepareStatement(SELECT_PATIENT_BY_ID);
            preparedStatement.setLong(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                patient = patientMapping(resultSet);
            }
        } catch (SQLException | ConnectionPoolException throwables) {
            logger.log(Level.ERROR,throwables);
            throw new DAOException(throwables);
        }finally {
            connectionPool.releaseConnection(connection);
            try {
                if (preparedStatement != null && !preparedStatement.isClosed()) {
                    preparedStatement.close();
                }
            }catch (SQLException e){
                logger.log(Level.ERROR,e);
                throw new DAOException("Close preparedStatement error ", e);
            }
        }
        return patient;
    }

    @Override
    public Patient getPatientByAccount(long accountId) throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        Patient patient = null;
        try {
            connection = connectionPool.getConnection();
            preparedStatement = connection.prepareStatement(SELECT_PATIENT_BY_ACCOUNT);
            preparedStatement.setLong(1,accountId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                patient = patientMapping(resultSet);
            }
        } catch (SQLException | ConnectionPoolException throwables) {
            logger.log(Level.ERROR,throwables);
            throw new DAOException(throwables);
        }finally {
            connectionPool.releaseConnection(connection);
            try {
                if (preparedStatement != null && !preparedStatement.isClosed()) {
                    preparedStatement.close();
                }
            }catch (SQLException e){
                logger.log(Level.ERROR,e);
                throw new DAOException("Close preparedStatement error ", e);
            }
        }
        return patient;
    }

    @Override
    public List<Patient> getAll() throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        List<Patient> patients = new ArrayList<>();
        try {
            connection = connectionPool.getConnection();
            preparedStatement = connection.prepareStatement(GET_ALL_PATIENTS);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Patient patient = patientMapping(resultSet);
                patients.add(patient);
            }
        } catch (SQLException | ConnectionPoolException throwables) {
            logger.log(Level.ERROR,throwables);
            throw new DAOException(throwables);
        }finally {
            connectionPool.releaseConnection(connection);
            try {
                if (preparedStatement != null && !preparedStatement.isClosed()) {
                    preparedStatement.close();
                }
            }catch (SQLException e){
                logger.log(Level.ERROR,e);
                throw new DAOException("Close preparedStatement error ", e);
            }
        }
        return patients;
    }

    private Patient patientMapping(ResultSet resultSet) throws SQLException {
        Patient patient = new Patient();
        patient.setId(resultSet.getLong(1));
        patient.setFirstname(resultSet.getString(2));
        patient.setLastname(resultSet.getString(3));
        patient.setAge(resultSet.getInt(4));
        patient.setPatientPic(resultSet.getString(9));
        patient.setDepartment(resultSet.getInt(5));
        patient.setAttendingDoctorID(resultSet.getLong(6));
        patient.setStatusID(resultSet.getLong(7));
        patient.setAccountID(resultSet.getLong(8));
        return patient;
    }
}
