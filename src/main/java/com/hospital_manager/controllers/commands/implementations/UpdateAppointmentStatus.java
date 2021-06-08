package com.hospital_manager.controllers.commands.implementations;


import com.hospital_manager.controllers.commands.Command;
import com.hospital_manager.entities.AppointmentStatus;
import com.hospital_manager.services.ServiceProvider;
import com.hospital_manager.services.exceptions.ServiceException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static com.hospital_manager.controllers.commands.CommandParameter.*;


public class UpdateAppointmentStatus implements Command {

    private static final String ATTRIBUTE_STATUS = "status";
    private static final String ATTRIBUTE_APPOINTMENT_ID = "appointment_id";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(true);
        if(session == null) {
            response.sendRedirect(GO_TO_INDEX_PAGE);
            return;
        }
        Boolean isAuth = (Boolean) session.getAttribute(ATTRIBUTE_AUTH);
        String role  = (String) session.getAttribute(ATTRIBUTE_ROLE);
        if (isAuth == null || !isAuth || role.equals(ROLE_PATIENT)) {
            session.setAttribute(ATTRIBUTE_URL,GO_TO_INDEX_PAGE);
            response.sendRedirect(GO_TO_INDEX_PAGE);
            return;
        }
        String appointmentNewStatus = request.getParameter(ATTRIBUTE_STATUS);
        Long appointmentId = Long.parseLong(request.getParameter(ATTRIBUTE_APPOINTMENT_ID));
        try {
            ServiceProvider.getInstance().getAppointmentService()
                    .updateAppointmentStatus(appointmentId, AppointmentStatus.valueOf(appointmentNewStatus.toUpperCase()));

            response.sendRedirect(GO_TO_MAIN_PAGE);
        } catch (ServiceException e) {
            session.setAttribute(ATTRIBUTE_URL,GO_TO_ERROR_PAGE);
            response.sendRedirect(GO_TO_ERROR_PAGE);

        }
    }
}
