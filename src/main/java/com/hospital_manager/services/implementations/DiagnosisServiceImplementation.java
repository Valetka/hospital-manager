package com.hospital_manager.services.implementations;



import com.hospital_manager.dao.DAOProvider;
import com.hospital_manager.dao.DiagnosisDAO;
import com.hospital_manager.dao.exceptions.DAOException;
import com.hospital_manager.entities.Diagnosis;
import com.hospital_manager.entities.dto.DiagnosisDTO;
import com.hospital_manager.services.DiagnosisService;
import com.hospital_manager.services.exceptions.ServiceException;
import com.hospital_manager.services.util.MappingUtil;
import com.hospital_manager.services.util.UtilException;
import com.hospital_manager.services.validation.Validator;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class DiagnosisServiceImplementation implements DiagnosisService {

    private static final Logger logger = LogManager.getLogger(DiagnosisServiceImplementation.class);
    private static final String INVALID = " is wrong";

    private static final DAOProvider provider  = DAOProvider.getInstance();

    @Override
    public void addDiagnosis(Diagnosis diagnosis) throws ServiceException {
        if(diagnosis == null){
            logger.log(Level.WARN,"diagnosis"+INVALID);
            throw new ServiceException("diagnosis"+INVALID);
        }
        DiagnosisDAO diagnosisDAO = provider.getDiagnosisDAO();
        try {
            diagnosisDAO.addDiagnosis(diagnosis);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<DiagnosisDTO> getDiagnosisByPatientId(long patientID) throws ServiceException {
        if (!Validator.isIdValid(patientID)) {
            logger.log(Level.WARN,patientID+INVALID);
            throw new ServiceException(patientID+INVALID);
        }
        DiagnosisDAO diagnosisDAO = provider.getDiagnosisDAO();
        List<DiagnosisDTO> diagnosisDTOList = new ArrayList<>();
        List<Diagnosis> diagnosisList;
        try {
            diagnosisList = diagnosisDAO.getDiagnosisByPatientId(patientID);
            for(Diagnosis diagnosis:diagnosisList){
                diagnosisDTOList.add(MappingUtil.matToDiagnosisDTO(diagnosis));
            }
        } catch (DAOException | UtilException e) {
            throw new ServiceException(e);
        }
        return diagnosisDTOList;
    }

    @Override
    public void update(Diagnosis diagnosis) throws ServiceException {
        DiagnosisDAO diagnosisDAO = provider.getDiagnosisDAO();
        try {
            diagnosisDAO.update(diagnosis);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Diagnosis getLastDiagnosisByPatientId(long patientID) throws ServiceException {
        if (!Validator.isIdValid(patientID)) {
            logger.log(Level.WARN,patientID+INVALID);
            throw new ServiceException(patientID+INVALID);
        }
        DiagnosisDAO diagnosisDAO = provider.getDiagnosisDAO();
        List<Diagnosis> diagnosisAll;
        try {
            diagnosisAll = diagnosisDAO.getDiagnosisByPatientId(patientID);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        Diagnosis maxDiagnosis = null;
        Date maxDate  = new Date(0L);
        for(Diagnosis diagnosis:diagnosisAll){
            if(diagnosis.getReceiptDate().getTime()>maxDate.getTime()){
                maxDiagnosis = diagnosis;
                maxDate = diagnosis.getReceiptDate();
            }
        }
        return maxDiagnosis;
    }
}
