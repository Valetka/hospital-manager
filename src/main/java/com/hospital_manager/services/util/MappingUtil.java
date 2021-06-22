package com.hospital_manager.services.util;

import com.hospital_manager.dao.DAOProvider;
import com.hospital_manager.dao.StaffDAO;
import com.hospital_manager.dao.exceptions.DAOException;
import com.hospital_manager.entities.*;
import com.hospital_manager.entities.dto.AppointmentDTO;
import com.hospital_manager.entities.dto.DiagnosisDTO;

import java.util.ArrayList;
import java.util.List;

public final class MappingUtil {
    public static AppointmentDTO mapToAppointmentDTO(Appointment appointment) throws UtilException {
        AppointmentDTO dto = new AppointmentDTO();

        Patient patient;
        Staff appointStaff;
        Staff execStaff;
        AppointmentInfo appointmentInfo;
        try {
            patient = DAOProvider.getInstance().getPatientDAO().getPatientById(appointment.getPatientId());
            StaffDAO staffDAO = DAOProvider.getInstance().getStaffDAO();
            appointStaff = staffDAO.getStaffById(appointment.getAppointingDoctorId());
            execStaff = staffDAO.getStaffById(appointment.getExecuteStaffId());
            appointmentInfo = DAOProvider.getInstance().getAppointmentDAO().getAppointmentInfoById(appointment.getInfoId());
        } catch (DAOException e) {
            throw new UtilException(e);
        }
        dto.setId(appointment.getId());
        dto.setDateOfAppointment(appointment.getDateOfAppointment());
        dto.setDateOfCompletion(appointment.getDateOfCompletion());
        dto.setInfo(appointmentInfo.getInfo());
        dto.setStatus(appointment.getStatus());
        dto.setType(appointmentInfo.getType());
        dto.setAppointingDoctorFirstname(appointStaff.getFirstname());
        dto.setAppointingDoctorLastname(appointStaff.getLastname());
        dto.setExecuteStaffFirstname(execStaff.getFirstname());
        dto.setExecuteStaffLastname(execStaff.getLastname());
        dto.setPatientFirstname(patient.getFirstname());
        dto.setPatientLastname(patient.getLastname());
        return dto;

    }

    public static DiagnosisDTO matToDiagnosisDTO(Diagnosis diagnosis) throws UtilException {
        DiagnosisDTO diagnosisDTO = new DiagnosisDTO();
        List<AppointmentDTO> appointmentDTOInDiagnosis = new ArrayList<>();
        Patient patient;
        try {
            patient = DAOProvider.getInstance().getPatientDAO().getPatientById(diagnosis.getPatientId());
            List<Appointment> appointmentsInDiagnosis = DAOProvider.getInstance()
                    .getAppointmentDAO().getAllAppointmentBetweenDate(diagnosis.getReceiptDate(), diagnosis.getDischargeDate(), patient.getId());
            for (Appointment appointment : appointmentsInDiagnosis) {
                appointmentDTOInDiagnosis.add(mapToAppointmentDTO(appointment));
            }
        } catch (DAOException e) {
            throw new UtilException(e);
        }

        diagnosisDTO.setId(diagnosis.getId());
        diagnosisDTO.setReceiptDate(diagnosis.getReceiptDate());
        diagnosisDTO.setDischargeDate(diagnosis.getDischargeDate());
        diagnosisDTO.setPreliminaryDiagnosis(diagnosis.getPreliminaryDiagnosis());
        diagnosisDTO.setDefinitiveDiagnosis(diagnosis.getDefinitiveDiagnosis());
        diagnosisDTO.setPatientFirstname(patient.getFirstname());
        diagnosisDTO.setPatientLastname(patient.getLastname());
        diagnosisDTO.setAppointmentList(appointmentDTOInDiagnosis);
        return diagnosisDTO;
    }
}
