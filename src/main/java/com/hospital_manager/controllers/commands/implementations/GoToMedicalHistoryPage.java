package com.hospital_manager.controllers.commands.implementations;


import com.hospital_manager.controllers.commands.Command;
import com.hospital_manager.entities.dto.DiagnosisDTO;
import com.hospital_manager.services.DiagnosisService;
import com.hospital_manager.services.ServiceProvider;
import com.hospital_manager.services.exceptions.ServiceException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

import static com.hospital_manager.controllers.commands.CommandParameter.*;

public class GoToMedicalHistoryPage implements Command {

    private static final String PATH_TO_HISTORY = "/WEB-INF/jsp/medical_history.jsp";
    private static final String GO_TO_HISTORY = "Controller?command=go_to_medical_history_page";
    private static final String ATTRIBUTE_MEDICAL_HISTORY = "medicalHistory";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(true);
        if(session == null) {
            response.sendRedirect(GO_TO_INDEX_PAGE);
            return;
        }

        Boolean isAuth = (Boolean) session.getAttribute(ATTRIBUTE_AUTH);
        if (isAuth == null || !isAuth) {
            session.setAttribute(ATTRIBUTE_URL,GO_TO_INDEX_PAGE);
            response.sendRedirect(GO_TO_INDEX_PAGE);
            return;
        }
        Long patientId;
        if(!session.getAttribute(ATTRIBUTE_ROLE).equals(ROLE_PATIENT))
        {
            patientId = Long.valueOf(request.getParameter("patientId"));
        }else{
            patientId = (Long) session.getAttribute(ATTRIBUTE_USER_ID);
        }
        ServiceProvider provider = ServiceProvider.getInstance();
        DiagnosisService diagnosisService = provider.getDiagnosisService();
        List<DiagnosisDTO> diagnosisList;
        try {
            diagnosisList = diagnosisService.getDiagnosisByPatientId(patientId);
        } catch (ServiceException e) {
            session.setAttribute(ATTRIBUTE_URL, GO_TO_ERROR_PAGE);
            response.sendRedirect(GO_TO_ERROR_PAGE);
            return;
        }

        session.setAttribute(ATTRIBUTE_URL,GO_TO_HISTORY);
        request.setAttribute(ATTRIBUTE_MEDICAL_HISTORY,diagnosisList);
        request.getRequestDispatcher(PATH_TO_HISTORY).forward(request,response);
    }
}
