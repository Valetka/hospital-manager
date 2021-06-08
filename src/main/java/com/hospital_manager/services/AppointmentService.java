package com.hospital_manager.services;

import com.hospital_manager.entities.Appointment;
import com.hospital_manager.entities.AppointmentInfo;
import com.hospital_manager.entities.AppointmentStatus;
import com.hospital_manager.entities.AppointmentType;
import com.hospital_manager.entities.dto.AppointmentDTO;
import com.hospital_manager.services.exceptions.ServiceException;

import java.sql.Date;
import java.util.List;

public interface AppointmentService {
    void  addAppointment(Appointment appointment) throws ServiceException;

    AppointmentInfo getAppointmentInfo(String title, AppointmentType type) throws ServiceException;

    List<AppointmentDTO> getAllAppointmentsByPatientId(long patientId) throws ServiceException;

    List<AppointmentDTO> getAllAppointmentsByStaffId(long staffId) throws ServiceException;

    void updateAppointmentStatus(Long appointmentId, AppointmentStatus appointmentStatus) throws ServiceException;

    List<Appointment> getAllAppointmentBetweenDate(Date dateFrom, Date dateTo, long id)throws ServiceException;

    void updateAppointmentDiagnosis(List<Appointment> appointmentList, long diagnosisId) throws ServiceException;
}
