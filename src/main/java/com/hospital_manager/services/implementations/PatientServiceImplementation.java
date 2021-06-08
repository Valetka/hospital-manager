package com.hospital_manager.services.implementations;

import com.hospital_manager.dao.DAOProvider;
import com.hospital_manager.dao.PatientDAO;
import com.hospital_manager.dao.exceptions.DAOException;
import com.hospital_manager.entities.Patient;
import com.hospital_manager.services.PatientService;
import com.hospital_manager.services.exceptions.ServiceException;
import com.hospital_manager.services.util.UploadUtil;
import com.hospital_manager.services.validation.Validator;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.Part;
import java.util.List;

public class PatientServiceImplementation implements PatientService {

    private static final Logger logger = LogManager.getLogger(PatientServiceImplementation.class);
    private static final String INVALID = " is wrong";

    private static final DAOProvider provider  = DAOProvider.getInstance();

    @Override
    public List<Patient> getFreePatients() throws ServiceException {
        PatientDAO patientDAO = provider.getPatientDAO();
        List<Patient> patients;
        try {
            patients = patientDAO.getFreePatients();
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return patients;
    }

    @Override
    public List<Patient> getAllPatientsByStaff(long id) throws ServiceException {
        if (!Validator.isIdValid(id)) {
            logger.log(Level.WARN,id+INVALID);
            throw new ServiceException(id+INVALID);
        }
        PatientDAO patientDAO = provider.getPatientDAO();
        List<Patient> patients;
        try {
            patients = patientDAO.getAllPatientsByStaff(id);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return patients;
    }

    @Override
    public List<Patient> getAll() throws ServiceException {
        PatientDAO patientDAO = provider.getPatientDAO();
        List<Patient> patients;
        try {
            patients = patientDAO.getAll();
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return patients;
    }

    @Override
    public Patient getPatientById(Long id) throws ServiceException {
        if (!Validator.isIdValid(id)) {
            logger.log(Level.WARN,id+INVALID);
            throw new ServiceException(id+INVALID);
        }
        PatientDAO patientDAO = provider.getPatientDAO();
        Patient patient;
        try {
            patient = patientDAO.getPatientById(id);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return patient;
    }

    @Override
    public Patient getPatientByAccount(long accountId) throws ServiceException {
        PatientDAO patientDAO = provider.getPatientDAO();
        Patient patient;
        try {
            patient = patientDAO.getPatientByAccount(accountId);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return patient;
    }

    @Override
    public void update(Patient patient) throws ServiceException {
        PatientDAO patientDAO = provider.getPatientDAO();
        try {
            patientDAO.update(patient);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void savePictureToPatient(Patient patient, Part part) throws ServiceException {
        String file = UploadUtil.upload(part);
        patient.setPatientPic(file);
        update(patient);
    }
}
