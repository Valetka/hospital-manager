package com.hospital_manager.controllers.commands;

public final class CommandParameter {

    public static final String GO_TO_ADD_APPOINTMENT_PAGE = "Controller?command=gotoaddappointmentpage";
    public static final String GO_TO_APPOINT_PAGE = "Controller?command=gotoaddappointmentpage";
    public static final String GO_TO_INDEX_PAGE = "index.jsp";
    public static final String GO_TO_APPOINT_NEXT_PAGE = "Controller?command=gotoaddappointmentnextpage";
    public static final String GO_TO_ADD_STAFF_PAGE = "Controller?command=gotoaddstaffpage";
    public static final String GO_TO_MAIN_PAGE = "Controller?command=gotomainpage";
    public static final String GO_TO_ERROR_PAGE = "error.jsp";
    public static final String PATH_TO_APPOINTMENT ="/WEB-INF/jsp/appointment.jsp";
    public static final String PATH_TO_APPOINTMENT_NEXT_PAGE ="/WEB-INF/jsp/appointment_nextpage.jsp";
    public static final String ATTRIBUTE_ERROR_MESSAGE = "errorMessage";
    public static final String ATTRIBUTE_INFO_MESSAGE = "informationMessage";
    public static final String LOGIN = "login";
    public static final String PASSWORD = "password";
    public static final String AGE = "age";
    public static final String FIRSTNAME = "firstname";
    public static final String LASTNAME = "lastname";
    public static final String ATTRIBUTE_URL = "url";
    public static final String ATTRIBUTE_VISITOR_ID = "id";
    public static final String ATTRIBUTE_AUTH = "auth";
    public static final String ROLE_DOCTOR = "doctor";
    public static final String ROLE_NURSE = "nurse";
    public static final String ROLE_ADMIN = "admin";
    public static final String ROLE_PATIENT = "patient";
    public static final String ATTRIBUTE_ROLE = "role";

    private CommandParameter(){}

}
