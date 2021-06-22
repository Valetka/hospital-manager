package com.hospital_manager.controllers.commands.implementations;


import com.hospital_manager.controllers.commands.Command;
import com.hospital_manager.entities.AppointmentType;
import com.hospital_manager.entities.Department;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Arrays;

import static com.hospital_manager.controllers.commands.CommandParameter.ATTRIBUTE_URL;

public class GoToAddStaffPage implements Command {


    private static final String PATH_TO_ADD_STAFF = "/WEB-INF/jsp/add_staff.jsp";
    private static final String ATTRIBUTE_DEPARTMENT = "department";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(true);
        session.setAttribute(ATTRIBUTE_URL, PATH_TO_ADD_STAFF);
        request.setAttribute(ATTRIBUTE_DEPARTMENT, Arrays.asList(Department.values()));
        RequestDispatcher requestDispatcher = request.getRequestDispatcher(PATH_TO_ADD_STAFF);
        requestDispatcher.forward(request, response);
    }
}
