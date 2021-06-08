package com.hospital_manager.controllers.commands.implementations;


import com.hospital_manager.controllers.commands.Command;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static com.hospital_manager.controllers.commands.CommandParameter.ATTRIBUTE_URL;

public class GoToAddStaffPage implements Command {


    private static final String PATH_TO_ADD_STAFF = "/WEB-INF/jsp/add_staff.jsp";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(true);
        session.setAttribute(ATTRIBUTE_URL, PATH_TO_ADD_STAFF);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher(PATH_TO_ADD_STAFF);
        requestDispatcher.forward(request, response);
    }
}
