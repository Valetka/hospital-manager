package com.hospital_manager.dao.implementations;

import com.hospital_manager.dao.AppointmentDAO;
import com.hospital_manager.dao.connection.ConnectionPool;
import com.hospital_manager.dao.connection.PoolProvider;
import com.hospital_manager.dao.connection.exception.ConnectionPoolException;
import com.hospital_manager.dao.exceptions.DAOException;
import com.hospital_manager.entities.Appointment;
import com.hospital_manager.entities.AppointmentInfo;
import com.hospital_manager.entities.AppointmentStatus;
import com.hospital_manager.entities.AppointmentType;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AppointmentDAOImplementation implements AppointmentDAO {
    private static final Logger logger = LogManager.getLogger(AppointmentDAOImplementation.class);

    private static final String INSERT_APPOINTMENT = "insert into patient_appointments(date_of_completion,date_of_appointment," +
            "id_patient,id_appointment,id_executor,status,id_staff_appoint ) VALUES (?,?,?,?,?,?,?)";
    private static final String UPDATE_APPOINTMENT = "update patient_appointments SET date_of_completion =?,date_of_appointment = ?" +
            ",id_patient = ?,id_appointment = ?,id_executor = ?,status = ?,id_staff_appoint = ?, id_diagnosis = ? where id = ?";

    private static final String SELECT_APPOINTMENT_INFO = "select * from appointments WHERE title = ? and type = ? ";
    private static final String SELECT_APPOINTMENT_INFO_BY_ID = "select * from appointments WHERE id = ?  ";
    private static final String INSERT_APPOINTMENT_INFO = "insert into appointments(title ,type) values (?,?)";
    private static final String SELECT_APPOINTMENT_BY_PATIENT = "select * from patient_appointments where id_patient =?";
    private static final String SELECT_APPOINTMENT_BY_STAFF = "select * from patient_appointments where id_executor =? and status = 1";
    private static final String UPDATE_APPOINTMENT_STATUS = "update patient_appointments set status = ? where id = ? ";
    private static final String SELECT_APPOINTMENT_BETWEEN_DATES = "select * from patient_appointments where id_patient = ? and date_of_appointment  BETWEEN ? and ?";


    private final ConnectionPool connectionPool = PoolProvider.getConnectionPool();

    @Override
    public void addAppointment(Appointment appointment) throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = connectionPool.getConnection();
            preparedStatement = connection.prepareStatement(INSERT_APPOINTMENT);
            preparedStatement.setDate(1,appointment.getDateOfCompletion());
            preparedStatement.setDate(2,appointment.getDateOfAppointment());
            preparedStatement.setLong(3,appointment.getPatientId());
            preparedStatement.setLong(4,appointment.getInfoId());
            preparedStatement.setLong(5,appointment.getExecuteStaffId());
            preparedStatement.setLong(6,appointment.getStatus().getId());
            preparedStatement.setLong(7,appointment.getAppointingDoctorId());
            preparedStatement.execute();
        }  catch (ConnectionPoolException | SQLException e) {
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
    public AppointmentInfo getAppointmentInfo(String title, AppointmentType type) throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        AppointmentInfo appointmentInfo;
        try {
            connection = connectionPool.getConnection();
            preparedStatement = connection.prepareStatement(SELECT_APPOINTMENT_INFO);
            preparedStatement.setString(1,title);
            preparedStatement.setLong(2,type.getId());
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                appointmentInfo = new AppointmentInfo();
                appointmentInfo.setId(resultSet.getLong(1));
                appointmentInfo.setInfo(resultSet.getString(2));
                appointmentInfo.setType(resultSet.getInt(3));
            }else{
                appointmentInfo = insertAppointmentInfo(title,type);
            }
        } catch (ConnectionPoolException | SQLException e) {
            logger.log(Level.ERROR,e);
            throw new DAOException(e);
        } finally {
            connectionPool.releaseConnection(connection);
            try {
                if (preparedStatement != null && !preparedStatement.isClosed()) {
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                logger.log(Level.ERROR,e);
                throw new DAOException("Close preparedStatement error ", e);
            }
        }
        return appointmentInfo;
    }

    @Override
    public List<Appointment> getAllAppointmentsByPatientId(long patientId) throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        List<Appointment> appointmentsByPatient = new ArrayList<>();
        try {
            connection = connectionPool.getConnection();
            preparedStatement = connection.prepareStatement(SELECT_APPOINTMENT_BY_PATIENT);
            preparedStatement.setLong(1,patientId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Appointment appointment = appointmentMapping(resultSet);
                appointmentsByPatient.add(appointment);
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
        return appointmentsByPatient;
    }

    @Override
    public AppointmentInfo getAppointmentInfoById(long infoId) throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        AppointmentInfo appointmentInfo = null;
        try {
            connection = connectionPool.getConnection();
            preparedStatement = connection.prepareStatement(SELECT_APPOINTMENT_INFO_BY_ID);
            preparedStatement.setLong(1,infoId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                appointmentInfo = new AppointmentInfo();
                appointmentInfo.setId(resultSet.getLong(1));
                appointmentInfo.setInfo(resultSet.getString(2));
                appointmentInfo.setType(resultSet.getInt(3));
            }

        } catch (ConnectionPoolException | SQLException e) {
            logger.log(Level.ERROR,e);
            throw new DAOException(e);
        } finally {
            connectionPool.releaseConnection(connection);
            try {
                if (preparedStatement != null && !preparedStatement.isClosed()) {
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                logger.log(Level.ERROR,e);
                throw new DAOException("Close preparedStatement error ", e);
            }
        }
        return appointmentInfo;
    }

    @Override
    public List<Appointment> getAllAppointmentsByStaffId(long staffId) throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        List<Appointment> appointmentsByStaff = new ArrayList<>();
        try {
            connection = connectionPool.getConnection();
            preparedStatement = connection.prepareStatement(SELECT_APPOINTMENT_BY_STAFF);
            preparedStatement.setLong(1,staffId);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Appointment appointment = appointmentMapping(resultSet);
                appointmentsByStaff.add(appointment);
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
        return appointmentsByStaff;
    }

    @Override
    public void updateAppointmentStatus(Long appointmentId, AppointmentStatus appointmentStatus) throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = connectionPool.getConnection();
            preparedStatement = connection.prepareStatement(UPDATE_APPOINTMENT_STATUS);
            preparedStatement.setLong(2,appointmentId);
            preparedStatement.setLong(1,appointmentStatus.getId());
            preparedStatement.execute();

        } catch (ConnectionPoolException | SQLException e) {
            logger.log(Level.ERROR,e);
            throw new DAOException(e);
        } finally {
            connectionPool.releaseConnection(connection);
            try {
                if (preparedStatement != null && !preparedStatement.isClosed()) {
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                logger.log(Level.ERROR,e);
                throw new DAOException("Close preparedStatement error ", e);
            }
        }
    }

    @Override
    public List<Appointment> getAllAppointmentBetweenDate(Date dateFrom, Date dateTo, long patientId) throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        List<Appointment> selectedAppointments = new ArrayList<>();
        try {
            connection = connectionPool.getConnection();
            preparedStatement = connection.prepareStatement(SELECT_APPOINTMENT_BETWEEN_DATES);
            preparedStatement.setLong(1,patientId);
            preparedStatement.setDate(2,dateFrom);
            preparedStatement.setDate(3,dateTo);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                Appointment appointment = appointmentMapping(resultSet);
                selectedAppointments.add(appointment);
            }

        } catch (ConnectionPoolException | SQLException e) {
            logger.log(Level.ERROR,e);
            throw new DAOException(e);
        } finally {
            connectionPool.releaseConnection(connection);
            try {
                if (preparedStatement != null && !preparedStatement.isClosed()) {
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                logger.log(Level.ERROR,e);
                throw new DAOException("Close preparedStatement error ", e);
            }
        }
        return selectedAppointments;
    }

    @Override
    public void update(Appointment appointment) throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = connectionPool.getConnection();
            preparedStatement = connection.prepareStatement(UPDATE_APPOINTMENT);
            preparedStatement.setDate(1,appointment.getDateOfCompletion());
            preparedStatement.setDate(2,appointment.getDateOfAppointment());
            preparedStatement.setLong(3,appointment.getPatientId());
            preparedStatement.setLong(4,appointment.getInfoId());
            preparedStatement.setLong(5,appointment.getExecuteStaffId());
            preparedStatement.setLong(6,appointment.getStatus().getId());
            preparedStatement.setLong(7,appointment.getAppointingDoctorId());
            preparedStatement.setLong(8,appointment.getDiagnosisID());
            preparedStatement.setLong(9,appointment.getId());
            preparedStatement.execute();
        }  catch (ConnectionPoolException | SQLException e) {
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

    private AppointmentInfo insertAppointmentInfo(String title, AppointmentType type) throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatementForInsert = null;
        PreparedStatement preparedStatementForSelect = null;
        AppointmentInfo  appointmentInfo = null;
        try {
            connection = connectionPool.getConnection();
            preparedStatementForInsert = connection.prepareStatement(INSERT_APPOINTMENT_INFO);
            preparedStatementForInsert.setString(1,title);
            preparedStatementForInsert.setLong(2,type.getId());
            preparedStatementForInsert.execute();

            preparedStatementForSelect = connection.prepareStatement(SELECT_APPOINTMENT_INFO);
            preparedStatementForSelect.setString(1,title);
            preparedStatementForSelect.setLong(2,type.getId());
            ResultSet resultSet = preparedStatementForSelect.executeQuery();
            if(resultSet.next()){
                appointmentInfo = new AppointmentInfo();
                appointmentInfo.setId(resultSet.getLong(1));
                appointmentInfo.setInfo(resultSet.getString(2));
                appointmentInfo.setType(resultSet.getInt(3));
            }
        } catch (ConnectionPoolException | SQLException e) {
            logger.log(Level.ERROR,"insert error");
            throw new DAOException(e);
        } finally {
            connectionPool.releaseConnection(connection);
            try {
                if (preparedStatementForInsert != null && !preparedStatementForInsert.isClosed()) {
                    preparedStatementForInsert.close();
                }
            } catch (SQLException throwables) {
                logger.log(Level.ERROR,throwables);
                throw new DAOException("Close preparedStatement error ", throwables);
            }
            try {
                if (preparedStatementForSelect != null && !preparedStatementForSelect.isClosed()) {
                    preparedStatementForSelect.close();
                }
            } catch (SQLException e) {
                logger.log(Level.ERROR,e);
                throw new DAOException("Close preparedStatement error ", e);
            }
        }
        return appointmentInfo;
    }

    private Appointment appointmentMapping(ResultSet resultSet) throws SQLException {
        Appointment appointment = new Appointment();
        appointment.setId(resultSet.getLong(1));
        appointment.setDateOfCompletion(resultSet.getDate(2));
        appointment.setDateOfAppointment(resultSet.getDate(3));
        appointment.setPatientId(resultSet.getLong(4));
        appointment.setInfoId(resultSet.getLong(5));
        appointment.setExecuteStaffId(resultSet.getLong(6));
        appointment.setStatus(resultSet.getInt(7));
        appointment.setAppointingDoctorId(resultSet.getInt(8));
        appointment.setDiagnosisID(resultSet.getLong(9));
        return appointment;
    }
}
