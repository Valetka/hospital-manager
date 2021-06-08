package com.hospital_manager.services;

import com.hospital_manager.services.implementations.*;

public final class ServiceProvider {


    private static ServiceProvider instance = new ServiceProvider();


    private ServiceProvider(){}


    private final AccountService accountService = new AccountServiceImplementation();


    private final StaffService staffService = new StaffServiceImplementation();


    private final PatientService patientService = new PatientServiceImplementation();


    private final AppointmentService appointmentService = new AppointmentServiceImplementation();


    private final DiagnosisService diagnosisService = new DiagnosisServiceImplementation();


    private final MedicalHistoryService medicalHistoryService = new MedicalHistoryServiceImplementation();


    public static ServiceProvider getInstance(){return instance;}


    public AccountService getAccountService(){
        return accountService;
    }

    public StaffService getStaffService() {return staffService;}

    public PatientService getPatientService() {return patientService;}


    public AppointmentService getAppointmentService() {return appointmentService;}


    public DiagnosisService getDiagnosisService() {return diagnosisService;}


    public MedicalHistoryService getMedicalHistoryService() {return medicalHistoryService;}
}
