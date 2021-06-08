package com.hospital_manager.controllers.commands.implementations;



import com.hospital_manager.controllers.commands.Command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static com.hospital_manager.controllers.commands.CommandParameter.*;


public class GoToAddAdditionalInfoPage implements Command {

    private static final String PATH_TO_ADDITIONAL_INFO_PAGE = "/WEB-INF/jsp/additional_info.jsp";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(true);
        Boolean isAuth = (Boolean) session.getAttribute(ATTRIBUTE_AUTH);
        if (isAuth == null || !isAuth ) {
            session.setAttribute(ATTRIBUTE_URL,GO_TO_INDEX_PAGE);
            response.sendRedirect(GO_TO_INDEX_PAGE);
            return;
        }

        session.setAttribute(ATTRIBUTE_URL,PATH_TO_ADDITIONAL_INFO_PAGE);
        request.getRequestDispatcher(PATH_TO_ADDITIONAL_INFO_PAGE).forward(request, response);
    }
}
