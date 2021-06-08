package com.hospital_manager.controllers.commands.implementations;



import com.hospital_manager.controllers.commands.Command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static com.hospital_manager.controllers.commands.CommandParameter.*;

public class GoToReceiptDatePage implements Command {

    private static final String GO_TO_RECEIPT_PAGE = "Controller?command=gotoreceiptdatepage";
    private static final String PATH_TO_RECEIPT = "/WEB-INF/jsp/receipt_date.jsp";

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
        session.setAttribute(ATTRIBUTE_URL,GO_TO_RECEIPT_PAGE);
        request.setAttribute("free_patient_id",request.getParameter("free_patient_id"));
        request.getRequestDispatcher(PATH_TO_RECEIPT).forward(request,response);

    }
}
