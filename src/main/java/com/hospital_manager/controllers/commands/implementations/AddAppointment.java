package com.hospital_manager.controllers.commands.implementations;

import com.hospital_manager.controllers.commands.Command;
import com.hospital_manager.entities.Appointment;
import com.hospital_manager.entities.AppointmentInfo;
import com.hospital_manager.entities.AppointmentStatus;
import com.hospital_manager.entities.AppointmentType;
import com.hospital_manager.services.AppointmentService;
import com.hospital_manager.services.ServiceProvider;
import com.hospital_manager.services.exceptions.ServiceException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Date;
import java.util.Arrays;

import static com.hospital_manager.controllers.commands.CommandParameter.*;

public class AddAppointment implements Command {

    private Date appointmentDate;
    private Date dateOfCompletion;
    private AppointmentType type;
    private String information;
    private Long patientId;
    private Long execStaffId;


    private static final String ATTRIBUTE_APPOINT_DATE = "dateOfAppointment";
    private static final String ATTRIBUTE_COMPLETION_DATE = "dateOfCompletion";
    private static final String INFORMATION = "info";
    private static final String SELECTED_TYPE = "select_type";
    private static final String SELECTED_PATIENT = "select_patient_id";
    private static final String SELECTED_EXEC_STAFF_ = "select_staff_id";


    private static final String APPOINTMENT_ADDED_OK = "local.info.appointed_added";
    private static final String APPOINTMENT_ADDED_ERROR = "local.error.appointed_not_added";


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

        appointmentDate = Date.valueOf(request.getParameter(ATTRIBUTE_APPOINT_DATE));
        type = AppointmentType.getAppointmentTypeById(Integer.parseInt(request.getParameter(SELECTED_TYPE)));
        information = request.getParameter(INFORMATION);
        patientId = Long.valueOf(request.getParameter(SELECTED_PATIENT));
        execStaffId = Long.valueOf(request.getParameter(SELECTED_EXEC_STAFF_));
        if(!request.getParameter(SELECTED_TYPE).equals("1"))
        {
            dateOfCompletion = Date.valueOf(request.getParameter(ATTRIBUTE_COMPLETION_DATE));
        }

        AppointmentService docService = ServiceProvider.getInstance().getAppointmentService();
        AppointmentInfo appointmentInfo = null;
        try {
            appointmentInfo = docService.getAppointmentInfo(information,type);
        } catch (ServiceException e) {
            session.setAttribute(ATTRIBUTE_ERROR_MESSAGE, Arrays.asList(APPOINTMENT_ADDED_ERROR));
            session.setAttribute(ATTRIBUTE_URL, GO_TO_APPOINT_PAGE);
            response.sendRedirect(GO_TO_APPOINT_PAGE);
            return;
        }

        Appointment appointment = new Appointment();
        appointment.setDateOfAppointment(appointmentDate);
        appointment.setInfoId(appointmentInfo.getId());
        appointment.setExecuteStaffId(execStaffId);
        appointment.setPatientId(patientId);
        appointment.setAppointingDoctorId((Long)session.getAttribute(ATTRIBUTE_USER_ID));
        appointment.setStatus(AppointmentStatus.APPOINTED);
        if(dateOfCompletion!=null){
            appointment.setDateOfCompletion(dateOfCompletion);
        }

        AppointmentService appointmentService = ServiceProvider.getInstance().getAppointmentService();
        try {
            appointmentService.addAppointment(appointment);
            session.setAttribute(ATTRIBUTE_INFO_MESSAGE, Arrays.asList(APPOINTMENT_ADDED_OK));
            request.getRequestDispatcher(GO_TO_MAIN_PAGE).forward(request,response);
        } catch (ServiceException e) {
            session.setAttribute(ATTRIBUTE_ERROR_MESSAGE,Arrays.asList(APPOINTMENT_ADDED_ERROR));
            session.setAttribute(ATTRIBUTE_URL, GO_TO_ADD_APPOINTMENT_PAGE);
            response.sendRedirect(GO_TO_ADD_APPOINTMENT_PAGE);
        }
    }
}
