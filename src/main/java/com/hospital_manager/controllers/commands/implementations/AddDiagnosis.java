package com.hospital_manager.controllers.commands.implementations;

import com.hospital_manager.controllers.commands.Command;
import com.hospital_manager.entities.Appointment;
import com.hospital_manager.entities.Diagnosis;
import com.hospital_manager.entities.MedicalHistory;
import com.hospital_manager.entities.Patient;
import com.hospital_manager.services.*;
import com.hospital_manager.services.exceptions.ServiceException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Date;
import java.util.Arrays;
import java.util.List;

import static com.hospital_manager.controllers.commands.CommandParameter.*;

public class AddDiagnosis implements Command {
    private static final String DISCHARGE_DATE = "dischargeDate";
    private static final String DEFINITIVE_DIAGNOSIS = "definitiveDiagnosis";
    private static final String PATIENT_ID = "patient_id";


    private static final String DIAGNOSIS_ADDED_OK = "local.info.diagnosis_added";
    private static final String DIAGNOSIS_ADDED_ERROR = "local.error.diagnosis_not_added";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(true);
        if(session == null) {
            response.sendRedirect(GO_TO_INDEX_PAGE);
            return;
        }
        Boolean isAuth = (Boolean) session.getAttribute(ATTRIBUTE_AUTH);
        String role  = (String) session.getAttribute(ATTRIBUTE_ROLE);
        if (isAuth == null || !isAuth || role.equals(ROLE_PATIENT)) {
            session.setAttribute(ATTRIBUTE_URL,GO_TO_INDEX_PAGE);
            response.sendRedirect(GO_TO_INDEX_PAGE);
            return;
        }

        Date dischargeDate = Date.valueOf(request.getParameter(DISCHARGE_DATE));
        String  definitiveDiagnosis = request.getParameter(DEFINITIVE_DIAGNOSIS);
        long patientID = Long.parseLong(request.getParameter(PATIENT_ID));

        ServiceProvider provider = ServiceProvider.getInstance();
        DiagnosisService diagnosisService = provider.getDiagnosisService();
        AppointmentService appointmentService = provider.getAppointmentService();
        PatientService patientService = provider.getPatientService();
        MedicalHistoryService medicalHistoryService = provider.getMedicalHistoryService();

        try {
            MedicalHistory medicalHistory = medicalHistoryService.getByPatientId(patientID);

            Diagnosis diagnosis = diagnosisService.getLastDiagnosisByPatientId(patientID);
            diagnosis.setDefinitiveDiagnosis(definitiveDiagnosis);
            diagnosis.setDischargeDate(dischargeDate);
            diagnosis.setMedicalHistoryId(medicalHistory.getId());
            diagnosisService.update(diagnosis);

            Patient patient = patientService.getPatientById(patientID);
            patient.setAttendingDoctorID(0L);
            patient.setStatusID(2);
            patientService.update(patient);

            List<Appointment> appointmentList = appointmentService.getAllAppointmentBetweenDate(diagnosis.getReceiptDate(),diagnosis.getDischargeDate(),patient.getId());
            appointmentService.updateAppointmentDiagnosis(appointmentList, diagnosis.getId());

            session.setAttribute(ATTRIBUTE_URL,GO_TO_MAIN_PAGE);
            session.setAttribute(ATTRIBUTE_INFO_MESSAGE, Arrays.asList(DIAGNOSIS_ADDED_OK));
            response.sendRedirect(GO_TO_MAIN_PAGE);

        } catch (ServiceException e) {
            session.setAttribute(ATTRIBUTE_ERROR_MESSAGE, Arrays.asList(DIAGNOSIS_ADDED_ERROR));
            session.setAttribute(ATTRIBUTE_URL,GO_TO_ERROR_PAGE);
            response.sendRedirect(GO_TO_ERROR_PAGE);
        }



    }
}
