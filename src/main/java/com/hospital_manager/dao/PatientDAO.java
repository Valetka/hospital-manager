package com.hospital_manager.dao;

import com.hospital_manager.dao.exceptions.DAOException;
import com.hospital_manager.entities.Patient;

import java.util.List;

public interface PatientDAO {

    Patient getPatientById(Long id)throws DAOException;

    Patient getPatientByAccount(long accountId) throws DAOException;

    List<Patient> getAll() throws DAOException;

    List<Patient> getAllPatientsByStaff(long id) throws DAOException;

    List<Patient> getFreePatients() throws DAOException;

    void update(Patient patient) throws DAOException;
}
