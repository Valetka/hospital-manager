package com.hospital_manager.controllers.commands.implementations;


import com.hospital_manager.controllers.commands.Command;
import com.hospital_manager.entities.Diagnosis;
import com.hospital_manager.services.DiagnosisService;
import com.hospital_manager.services.ServiceProvider;
import com.hospital_manager.services.exceptions.ServiceException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static com.hospital_manager.controllers.commands.CommandParameter.*;

public class GoToDiagnosisPage implements Command {

    private static final String PATH_TO_DIAGNOSIS = "/WEB-INF/jsp/diagnosis.jsp";
    private static final String GO_TO_DIAGNOSIS_PAGE = "Controller?command=gotodiagnosispage";
    private static final String PATIENT_ID = "patient_id";
    private static final String DIAGNOSIS = "diagnosis";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession(true);
        Boolean isAuth = (Boolean) session.getAttribute(ATTRIBUTE_AUTH);
        String role = (String) session.getAttribute(ATTRIBUTE_ROLE);
        if (isAuth == null || !isAuth || role.equals(ROLE_PATIENT)) {
            session.setAttribute(ATTRIBUTE_URL,GO_TO_INDEX_PAGE);
            response.sendRedirect(GO_TO_INDEX_PAGE);
            return;
        }

        ServiceProvider serviceProvider = ServiceProvider.getInstance();
        DiagnosisService diagnosisService = serviceProvider.getDiagnosisService();
        Diagnosis diagnosis = null;
        try {
            diagnosis = diagnosisService.getLastDiagnosisByPatientId(Long.parseLong(request.getParameter(PATIENT_ID)));
        } catch (ServiceException e) {
            session.setAttribute(ATTRIBUTE_URL,GO_TO_ERROR_PAGE);
            request.getRequestDispatcher(GO_TO_ERROR_PAGE).forward(request, response);
        }

        session.setAttribute(ATTRIBUTE_URL,GO_TO_DIAGNOSIS_PAGE);
        request.setAttribute(PATIENT_ID,request.getParameter(PATIENT_ID));
        request.setAttribute(DIAGNOSIS,diagnosis);
        request.getRequestDispatcher(PATH_TO_DIAGNOSIS).forward(request, response);
    }
}
