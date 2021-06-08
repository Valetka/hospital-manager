package com.hospital_manager.services.implementations;

import com.hospital_manager.dao.AppointmentDAO;
import com.hospital_manager.dao.DAOProvider;
import com.hospital_manager.dao.exceptions.DAOException;
import com.hospital_manager.entities.Appointment;
import com.hospital_manager.entities.AppointmentInfo;
import com.hospital_manager.entities.AppointmentStatus;
import com.hospital_manager.entities.AppointmentType;
import com.hospital_manager.entities.dto.AppointmentDTO;
import com.hospital_manager.services.AppointmentService;
import com.hospital_manager.services.exceptions.DataFormatServiceException;
import com.hospital_manager.services.exceptions.ServiceException;
import com.hospital_manager.services.util.MappingUtil;
import com.hospital_manager.services.util.UtilException;
import com.hospital_manager.services.validation.Validator;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;

public class AppointmentServiceImplementation implements AppointmentService {

    private static final Logger logger = LogManager.getLogger(AppointmentServiceImplementation.class);
    private static final String INVALID = " is wrong";


    private static final DAOProvider provider  = DAOProvider.getInstance();

    @Override
    public void addAppointment(Appointment appointment) throws ServiceException {
        AppointmentDAO appointmentDAO = provider.getAppointmentDAO();
        try {
            appointmentDAO.addAppointment(appointment);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public AppointmentInfo getAppointmentInfo(String title, AppointmentType type) throws ServiceException {
        if(!Validator.isTitleValid(title)){
            logger.log(Level.WARN,title+INVALID);
            throw new DataFormatServiceException(title+INVALID);
        }
        AppointmentDAO appointmentDAO = provider.getAppointmentDAO();
        AppointmentInfo appointmentInfo;
        try {
            appointmentInfo = appointmentDAO.getAppointmentInfo(title,type);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return appointmentInfo;
    }

    @Override
    public List<AppointmentDTO> getAllAppointmentsByPatientId(long patientId) throws ServiceException {
        AppointmentDAO appointmentDAO = provider.getAppointmentDAO();
        List<Appointment> appointmentsByPatient;
        List<AppointmentDTO> dtoList;
        try {
            appointmentsByPatient = appointmentDAO.getAllAppointmentsByPatientId(patientId);
            dtoList = appointmentsByPatient.stream().map(MappingUtil::mapToAppointmentDTO)
                    .collect(Collectors.toList());
        } catch (DAOException | UtilException e) {
            throw new ServiceException(e);
        }

        return dtoList;
    }

    @Override
    public List<AppointmentDTO> getAllAppointmentsByStaffId(long staffId) throws ServiceException {
        if(!Validator.isIdValid(staffId)){
            logger.log(Level.WARN,staffId+INVALID);
            throw new ServiceException(staffId+INVALID);
        }
        AppointmentDAO appointmentDAO = provider.getAppointmentDAO();
        List<Appointment> appointmentsByPatient;
        List<AppointmentDTO> dtoList;
        try {
            appointmentsByPatient = appointmentDAO.getAllAppointmentsByStaffId(staffId);
            dtoList = appointmentsByPatient.stream().map(MappingUtil::mapToAppointmentDTO)
                    .collect(Collectors.toList());
        } catch (DAOException | UtilException e) {
            throw new ServiceException(e);
        }
        return dtoList;
    }

    @Override
    public void updateAppointmentStatus(Long appointmentId, AppointmentStatus appointmentStatus) throws ServiceException {
        if(!Validator.isIdValid(appointmentId)){
            logger.log(Level.WARN,appointmentId+INVALID);
            throw new ServiceException(appointmentId+INVALID);
        }
        AppointmentDAO appointmentDAO = provider.getAppointmentDAO();
        try {
            appointmentDAO.updateAppointmentStatus(appointmentId,appointmentStatus);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Appointment> getAllAppointmentBetweenDate(Date dateFrom, Date dateTo, long id) throws ServiceException {
        if(dateFrom==null || dateTo == null){
            logger.log(Level.WARN,dateFrom+" "+dateTo+INVALID);
            throw new ServiceException(dateFrom+" "+dateTo+INVALID);
        }
        AppointmentDAO appointmentDAO = provider.getAppointmentDAO();
        List<Appointment> appointments;
        try {
            appointments = appointmentDAO.getAllAppointmentBetweenDate(dateFrom,dateTo,id);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return appointments;
    }

    @Override
    public void updateAppointmentDiagnosis(List<Appointment> appointmentList, long diagnosisId) throws ServiceException {
        AppointmentDAO appointmentDAO = provider.getAppointmentDAO();
        for(Appointment appointment:appointmentList){
            appointment.setDiagnosisID(diagnosisId);
            try {
                appointmentDAO.update(appointment);
            } catch (DAOException e) {
                throw new ServiceException(e);
            }
        }
    }
}
