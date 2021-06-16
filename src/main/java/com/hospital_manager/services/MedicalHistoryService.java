package com.hospital_manager.services;

import com.hospital_manager.entities.MedicalHistory;
import com.hospital_manager.services.exceptions.ServiceException;

public interface MedicalHistoryService {

    void add(MedicalHistory medicalHistory) throws ServiceException;

    MedicalHistory getByPatientId(long patientId) throws ServiceException;

    void  update(MedicalHistory medicalHistory) throws ServiceException;
}
