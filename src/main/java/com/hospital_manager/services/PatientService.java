package com.hospital_manager.services;

import com.hospital_manager.entities.Patient;
import com.hospital_manager.services.exceptions.ServiceException;

import javax.servlet.http.Part;
import java.util.List;

public interface PatientService {
    Patient getPatientById(Long id) throws ServiceException;

    
    Patient getPatientByAccount(long id) throws ServiceException;


    List<Patient> getAll() throws ServiceException;


    List<Patient> getFreePatients() throws ServiceException;


    List<Patient> getAllPatientsByStaff(long id) throws ServiceException;


    void update(Patient patient) throws ServiceException;


    void savePictureToPatient(Patient patient, Part part) throws ServiceException;
}
