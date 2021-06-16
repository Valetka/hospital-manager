package com.hospital_manager.controllers.commands;

public final class CommandParameter {

    public static final String GO_TO_ADD_APPOINTMENT_PAGE = "Controller?command=go_to_add_appointment_page";
    public static final String GO_TO_APPOINT_PAGE = "Controller?command=go_to_appointment_page";
    public static final String GO_TO_INDEX_PAGE = "index.jsp";
    public static final String GO_TO_APPOINT_NEXT_PAGE = "Controller?command=go_to_add_appointment_next_page";
    public static final String GO_TO_ADD_STAFF_PAGE = "Controller?command=go_to_add_staff_page";
    public static final String GO_TO_ADD_PATIENT_PAGE = "Controller?command=go_to_add_patient_page";
    public static final String GO_TO_MAIN_PAGE = "Controller?command=go_to_main_page";
    public static final String GO_TO_ERROR_PAGE = "error.jsp";
    public static final String PATH_TO_APPOINTMENT ="/WEB-INF/jsp/appointment.jsp";
    public static final String PATH_TO_APPOINTMENT_NEXT_PAGE ="/WEB-INF/jsp/appointment_next_page.jsp";
    public static final String ATTRIBUTE_ERROR_MESSAGE = "errorMessage";
    public static final String ATTRIBUTE_INFO_MESSAGE = "informationMessage";
    public static final String LOGIN = "login";
    public static final String PASSWORD = "password";
    public static final String AGE = "age";
    public static final String FIRSTNAME = "firstname";
    public static final String LASTNAME = "lastname";
    public static final String ATTRIBUTE_URL = "url";
    public static final String ATTRIBUTE_USER_ID = "id";
    public static final String ATTRIBUTE_AUTH = "auth";
    public static final String ROLE_DOCTOR = "doctor";
    public static final String ROLE_NURSE = "nurse";
    public static final String ROLE_ADMIN = "admin";
    public static final String ROLE_PATIENT = "patient";
    public static final String ATTRIBUTE_ROLE = "role";

    private CommandParameter(){}

}
