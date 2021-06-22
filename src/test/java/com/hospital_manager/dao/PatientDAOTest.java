package com.hospital_manager.dao;

import com.hospital_manager.dao.connection.PoolProvider;
import com.hospital_manager.dao.connection.exception.ConnectionPoolException;
import com.hospital_manager.dao.exceptions.DAOException;
import com.hospital_manager.entities.Patient;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class PatientDAOTest {
    private static final String RESOURCE_FILE = "postgresql_test";


    private static final PatientDAO patientDAO = DAOProvider.getInstance().getPatientDAO();


    @BeforeClass
    public static void connectionPoolInit() throws ConnectionPoolException {
        PoolProvider.getConnectionPool().init(RESOURCE_FILE);
    }

    @AfterClass
    public static void connectionPoolDispose() throws ConnectionPoolException {
        PoolProvider.getConnectionPool().dispose();
    }


    @Test
    public void getFreeTest() throws DAOException {
        assertEquals(patientDAO.getFreePatients(), Collections.emptyList());
    }

    @Test
    public void getFreeNegativeTest() throws DAOException {
        List<Patient> patientList = patientDAO.getAll();
        for (Patient patient :patientList){
            patient.setStatusID(3);
            patientDAO.update(patient);
        }
        assertNotEquals(patientDAO.getFreePatients(), Collections.emptyList());
    }

    @Test
    public void update() throws DAOException {
        Patient patient = patientDAO.getPatientById(1L);
        patient.setId(1);
        patient.setFirstname("alexandr");
        patientDAO.update(patient);
        assertEquals(patientDAO.getPatientById(1L),patient);
    }
}
