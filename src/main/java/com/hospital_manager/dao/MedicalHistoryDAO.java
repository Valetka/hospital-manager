package com.hospital_manager.dao;

import com.hospital_manager.dao.exceptions.DAOException;
import com.hospital_manager.entities.MedicalHistory;

public interface MedicalHistoryDAO {

    void add(MedicalHistory medicalHistory) throws DAOException;

    /**
     * Find medical history by owner
     * @param patientId patient's id who owns MedicalHistory
     * @return found MedicalHistory
     * @throws DAOException if an dao exception occurred while processing
     */
    MedicalHistory getByPatientId(long patientId) throws DAOException;

    /**
     * Update information about medicalHistory
     * @param medicalHistory medicalHistory with new data to be saved
     * @throws DAOException if an dao exception occurred while processing
     */
    void  update(MedicalHistory medicalHistory) throws DAOException;
}
