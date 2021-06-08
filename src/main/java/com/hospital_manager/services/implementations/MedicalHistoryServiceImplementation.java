package com.hospital_manager.services.implementations;

import com.hospital_manager.dao.DAOProvider;
import com.hospital_manager.dao.MedicalHistoryDAO;
import com.hospital_manager.dao.exceptions.DAOException;
import com.hospital_manager.entities.MedicalHistory;
import com.hospital_manager.services.MedicalHistoryService;
import com.hospital_manager.services.exceptions.ServiceException;
import com.hospital_manager.services.validation.Validator;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MedicalHistoryServiceImplementation implements MedicalHistoryService {

    private static final Logger logger = LogManager.getLogger(MedicalHistoryServiceImplementation.class);
    private static final String INVALID = " is wrong";

    private static final DAOProvider provider = DAOProvider.getInstance();

    @Override
    public void add(MedicalHistory medicalHistory) throws ServiceException {
        MedicalHistoryDAO medicalHistoryDAO = provider.getMedicalHistoryDAO();
        try {
            medicalHistoryDAO.add(medicalHistory);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public MedicalHistory getByPatientId(long patientId) throws ServiceException {
        if (!Validator.isIdValid(patientId)) {
            logger.log(Level.WARN, patientId + INVALID);
            throw new ServiceException(patientId + INVALID);
        }
        MedicalHistoryDAO medicalHistoryDAO = provider.getMedicalHistoryDAO();
        MedicalHistory medicalHistory;
        try {
            medicalHistory = medicalHistoryDAO.getByPatientId(patientId);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return medicalHistory;
    }

    @Override
    public void update(MedicalHistory medicalHistory) throws ServiceException {
        MedicalHistoryDAO medicalHistoryDAO = provider.getMedicalHistoryDAO();
        try {
            medicalHistoryDAO.update(medicalHistory);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }
}
