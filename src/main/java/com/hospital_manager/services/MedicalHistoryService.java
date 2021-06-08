package com.hospital_manager.services;

import com.hospital_manager.entities.MedicalHistory;
import com.hospital_manager.services.exceptions.ServiceException;

public interface MedicalHistoryService {

    void add(MedicalHistory medicalHistory) throws ServiceException;

    /**
     * Find medical history by owner
     * @param patientId patient's id who owns MedicalHistory
     * @return found MedicalHistory
     * @throws ServiceException if a service exception occurred while processing
     */
    MedicalHistory getByPatientId(long patientId) throws ServiceException;

    /**
     * Update information about medicalHistory
     * @param medicalHistory medicalHistory with new data to be saved
     * @throws ServiceException if a service exception occurred while processing
     */
    void  update(MedicalHistory medicalHistory) throws ServiceException;
}
