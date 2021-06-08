package com.hospital_manager.dao;

import com.hospital_manager.dao.exceptions.DAOException;
import com.hospital_manager.entities.Diagnosis;

import java.util.List;

public interface DiagnosisDAO {

    void addDiagnosis(Diagnosis epicrisis) throws DAOException;


    List<Diagnosis> getDiagnosisByPatientId(long patientId)throws DAOException;


    void update(Diagnosis diagnosis) throws DAOException;
}
