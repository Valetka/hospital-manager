package com.hospital_manager.dao;

import com.hospital_manager.dao.connection.PoolProvider;
import com.hospital_manager.dao.connection.exception.ConnectionPoolException;
import com.hospital_manager.dao.exceptions.DAOException;
import com.hospital_manager.entities.Appointment;
import com.hospital_manager.entities.AppointmentStatus;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.sql.Date;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class AppointmentDAOTest {
    private static final String RESOURCE_FILE = "postgresql_test";


    private static final AppointmentDAO appointmentDAO = DAOProvider.getInstance().getAppointmentDAO();


    @BeforeClass
    public static void connectionPoolInit() throws ConnectionPoolException {
        PoolProvider.getConnectionPool().init(RESOURCE_FILE);
    }

    @AfterClass
    public static void connectionPoolDispose() throws ConnectionPoolException {
        PoolProvider.getConnectionPool().dispose();
    }

    @Test
    public void addAppPositiveTest() throws DAOException {
        Appointment appointment = new Appointment();
        appointment.setDateOfAppointment(new Date(1000));
        appointment.setAppointingDoctorId(1);
        appointment.setDateOfCompletion(new Date(1500));
        appointment.setExecuteStaffId(1);
        appointment.setPatientId(1);
        appointment.setInfoId(1);
        appointment.setStatus(AppointmentStatus.APPOINTED);
        appointmentDAO.addAppointment(appointment);
    }


    @Test
    public void getAppointmentInfoByIdPositiveTest() throws DAOException {
        assertNotNull(appointmentDAO.getAppointmentInfoById(1));
    }

    @Test
    public void getAllAppointmentByPatient() throws DAOException {
        List<Appointment> expect =  appointmentDAO.getAllAppointmentsByPatientId(1);
        assertNotEquals(expect, Collections.emptyList());
    }

    @Test
    public void getBetweenDate() throws DAOException {
        List<Appointment> expected = appointmentDAO.getAllAppointmentBetweenDate(new Date(400),new Date(1000),2);
        assertEquals(expected,Collections.emptyList());
    }

    @Test
    public void getBetweenDateNegative() throws DAOException {
        List<Appointment> expected = appointmentDAO.getAllAppointmentBetweenDate(new Date(1000),new Date(1500),1);
        assertNotEquals(expected,Collections.emptyList());
    }
}
