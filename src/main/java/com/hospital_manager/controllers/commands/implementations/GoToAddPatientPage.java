package com.hospital_manager.controllers.commands.implementations;


import com.hospital_manager.controllers.commands.Command;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static com.hospital_manager.controllers.commands.CommandParameter.ATTRIBUTE_URL;

public class GoToAddPatientPage implements Command {


    private static final String PATH_TO_ADD_PATIENT = "/WEB-INF/jsp/add_patient.jsp";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(true);
        session.setAttribute(ATTRIBUTE_URL, PATH_TO_ADD_PATIENT);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher(PATH_TO_ADD_PATIENT);
        requestDispatcher.forward(request, response);
    }
}
