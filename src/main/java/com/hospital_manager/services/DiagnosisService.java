package com.hospital_manager.services;

import com.hospital_manager.entities.Diagnosis;
import com.hospital_manager.entities.dto.DiagnosisDTO;
import com.hospital_manager.services.exceptions.ServiceException;

import java.util.List;

public interface DiagnosisService {

    void addDiagnosis(Diagnosis diagnosis) throws ServiceException;

    List<DiagnosisDTO> getDiagnosisByPatientId(long patientID) throws ServiceException;

    void update(Diagnosis diagnosis) throws ServiceException;

    Diagnosis getLastDiagnosisByPatientId(long patientID) throws ServiceException;
}
