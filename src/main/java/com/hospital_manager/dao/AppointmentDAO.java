package com.hospital_manager.dao;

import com.hospital_manager.dao.exceptions.DAOException;
import com.hospital_manager.entities.Appointment;
import com.hospital_manager.entities.AppointmentInfo;
import com.hospital_manager.entities.AppointmentStatus;
import com.hospital_manager.entities.AppointmentType;

import java.sql.Date;
import java.util.List;

public interface AppointmentDAO {

    void addAppointment(Appointment appointment) throws DAOException;


    AppointmentInfo getAppointmentInfo(String title, AppointmentType type) throws DAOException;


    List<Appointment> getAllAppointmentsByPatientId(long patientId) throws DAOException;


    AppointmentInfo getAppointmentInfoById(long infoId) throws DAOException;


    List<Appointment> getAllAppointmentsByStaffId(long staffId) throws DAOException;


    void updateAppointmentStatus(Long appointmentId, AppointmentStatus appointmentStatus) throws DAOException;


    List<Appointment> getAllAppointmentBetweenDate(Date dateFrom, Date dateTo, long patientId)throws DAOException;


    void update(Appointment appointment) throws DAOException;
}
