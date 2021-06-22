package com.hospital_manager.controllers.commands.implementations;


import com.hospital_manager.controllers.commands.Command;
import com.hospital_manager.entities.dto.AppointmentDTO;
import com.hospital_manager.services.AppointmentService;
import com.hospital_manager.services.ServiceProvider;
import com.hospital_manager.services.exceptions.ServiceException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.hospital_manager.controllers.commands.CommandParameter.*;

/**
 * Command to go to appointment list page
 */
public class GoToStaffAppointmentListPage implements Command {

    private static final String GO_TO_APPOINTMENT_STAFF_LIST_PAGE = "Controller?command=go_to_staff_appointment_list_page";
    public static final String PATH_TO_APPOINTMENT_LIST_PAGE ="/WEB-INF/jsp/appointment_list_for_staff.jsp";
    private static final String ATTRIBUTE_APPOINTMENT_LIST = "appointment_list";
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

        long visitorId = (long) session.getAttribute(ATTRIBUTE_USER_ID);
        ServiceProvider serviceProvider = ServiceProvider.getInstance();
        AppointmentService appointmentService = serviceProvider.getAppointmentService();
        List<AppointmentDTO> allAppointments = new ArrayList<>();
        if(session.getAttribute(ATTRIBUTE_ROLE).equals(ROLE_DOCTOR) ||session.getAttribute(ATTRIBUTE_ROLE).equals(ROLE_NURSE))
        {
            try {
                allAppointments = appointmentService.getAllAppointmentsByStaffId(visitorId);
            } catch (ServiceException e) {
                session.setAttribute(ATTRIBUTE_URL,GO_TO_MAIN_PAGE);
                response.sendRedirect(GO_TO_MAIN_PAGE);
                return;
            }
        }
        session.setAttribute(ATTRIBUTE_URL,GO_TO_APPOINTMENT_STAFF_LIST_PAGE);
        request.setAttribute(ATTRIBUTE_APPOINTMENT_LIST,allAppointments );
        request.getRequestDispatcher(PATH_TO_APPOINTMENT_LIST_PAGE).forward(request, response);
    }
}
