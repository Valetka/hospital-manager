package com.hospital_manager.controllers.commands.implementations;


import com.hospital_manager.controllers.commands.Command;
import com.hospital_manager.entities.Patient;
import com.hospital_manager.services.PatientService;
import com.hospital_manager.services.ServiceProvider;
import com.hospital_manager.services.exceptions.ServiceException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

import static com.hospital_manager.controllers.commands.CommandParameter.*;


public class GoToDoctorsPatientsPage implements Command {


    private static final String PATH_TO_PATIENTS = "/WEB-INF/jsp/patients.jsp";
    private static final String GO_TO_DOCTORS_PATIENTS = "Controller?command=gotodoctorspatientspage";
    private static final String ATTRIBUTE_PATIENT = "patientList";

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
        ServiceProvider serviceProvider = ServiceProvider.getInstance();
        PatientService patientService = serviceProvider.getPatientService();
        List<Patient> patients;
        try {
            patients = patientService.getAllPatientsByStaff((Long) session.getAttribute(ATTRIBUTE_USER_ID));
            session.setAttribute(ATTRIBUTE_URL,GO_TO_DOCTORS_PATIENTS);
            request.setAttribute(ATTRIBUTE_PATIENT, patients);
            request.getRequestDispatcher(PATH_TO_PATIENTS).forward(request,response);
        }catch (ServiceException e){
            session.setAttribute(ATTRIBUTE_URL, GO_TO_ERROR_PAGE);
            response.sendRedirect(GO_TO_ERROR_PAGE);
        }
    }
}
