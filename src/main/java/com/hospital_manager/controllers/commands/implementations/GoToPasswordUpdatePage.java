package com.hospital_manager.controllers.commands.implementations;



import com.hospital_manager.controllers.commands.Command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static com.hospital_manager.controllers.commands.CommandParameter.ATTRIBUTE_URL;


public class GoToPasswordUpdatePage implements Command {

    private static final String PATH_TO_UPDATE_PASSWORD = "/WEB-INF/jsp/update_password.jsp";
    private static final String GO_TO_UPDATE_PASSWORD_PAGE = "Controller?command=gotomainadminpage";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession(true);
        session.setAttribute(ATTRIBUTE_URL,GO_TO_UPDATE_PASSWORD_PAGE);
        request.getRequestDispatcher(PATH_TO_UPDATE_PASSWORD).forward(request, response);
    }
}
