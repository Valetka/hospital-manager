package com.hospital_manager.controllers.commands.implementations;


import com.hospital_manager.controllers.commands.Command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static com.hospital_manager.controllers.commands.CommandParameter.*;


public class GoToMainPage implements Command {

    private static final String PATH_TO_MAIN_PATIENT = "/WEB-INF/jsp/main_patient.jsp";
    private static final String PATH_TO_MAIN_STAFF = "/WEB-INF/jsp/main_staff.jsp";
    private static final String PATH_TO_MAIN_ADMIN = "/WEB-INF/jsp/main_admin.jsp";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        HttpSession session = request.getSession(true);
        Boolean isAuth = (Boolean) session.getAttribute(ATTRIBUTE_AUTH);
        String role = (String) session.getAttribute(ATTRIBUTE_ROLE);
        if (isAuth == null || !isAuth) {
            session.setAttribute(ATTRIBUTE_URL, GO_TO_INDEX_PAGE);
            response.sendRedirect(GO_TO_INDEX_PAGE);
            return;
        }
        String pathTo = GO_TO_INDEX_PAGE;
        if (role.equals(ROLE_PATIENT)) {
            pathTo = PATH_TO_MAIN_PATIENT;
        } else if (role.equals(ROLE_DOCTOR) || role.equals(ROLE_NURSE)) {
            pathTo = PATH_TO_MAIN_STAFF;
        } else if (role.equals(ROLE_ADMIN)) {
            pathTo = PATH_TO_MAIN_ADMIN;
        }
        session.setAttribute(ATTRIBUTE_URL, GO_TO_MAIN_PAGE);
        request.getRequestDispatcher(pathTo).forward(request, response);
    }

}
