package com.hospital_manager.controllers;


import com.hospital_manager.entities.Patient;
import com.hospital_manager.entities.Staff;
import com.hospital_manager.services.PatientService;
import com.hospital_manager.services.ServiceProvider;
import com.hospital_manager.services.StaffService;
import com.hospital_manager.services.exceptions.ServiceException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.*;
import java.io.IOException;

import static com.hospital_manager.controllers.commands.CommandParameter.*;

@MultipartConfig
public class UploadFileController extends HttpServlet {

    private static final long serialVersionUID = 8428742754L;

    private static final String GO_TO_PROFILE_PAGE = "Controller?command=gotoprofilepage";

    public UploadFileController() {
        super();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession(true);
        if(session == null) {
            response.sendRedirect(GO_TO_INDEX_PAGE);
            return;
        }

        Boolean isAuth = (Boolean) session.getAttribute(ATTRIBUTE_AUTH);
        String role  = (String) session.getAttribute(ATTRIBUTE_ROLE);
        if (isAuth == null || !isAuth) {
            session.setAttribute(ATTRIBUTE_URL,GO_TO_INDEX_PAGE);
            response.sendRedirect(GO_TO_INDEX_PAGE);
            return;
        }

        Part filePart = request.getPart("file");
        if(role.equals(ROLE_PATIENT)) {
            PatientService patientService = ServiceProvider.getInstance().getPatientService();
            try {
                Patient patient = patientService.getPatientById((long) session.getAttribute(ATTRIBUTE_USER_ID));
                patientService.savePictureToPatient(patient,filePart);

            } catch (ServiceException e) {
                session.setAttribute(ATTRIBUTE_URL, GO_TO_MAIN_PAGE);
                response.sendRedirect(GO_TO_MAIN_PAGE);
            }
            session.setAttribute(ATTRIBUTE_URL, GO_TO_PROFILE_PAGE);
            response.sendRedirect(GO_TO_PROFILE_PAGE);
        }else {
            StaffService staffService = ServiceProvider.getInstance().getStaffService();
            try {
                Staff staff = staffService.getStaffById((long) session.getAttribute(ATTRIBUTE_USER_ID));
                staffService.savePictureToStaff(staff,filePart);

            } catch (ServiceException e) {
                session.setAttribute(ATTRIBUTE_URL, GO_TO_MAIN_PAGE);
                request.getRequestDispatcher(GO_TO_MAIN_PAGE).forward(request, response);
            }
            session.setAttribute(ATTRIBUTE_URL, GO_TO_PROFILE_PAGE);
            response.sendRedirect(GO_TO_PROFILE_PAGE);
        }

    }
}
