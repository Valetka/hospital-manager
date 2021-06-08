package com.hospital_manager.dao;

import com.hospital_manager.dao.implementations.*;

public final class DAOProvider {

    private static DAOProvider instance = new DAOProvider();

    private DAOProvider(){}

    private AccountDAO accountDAO = new AccountDAOImplementation();

    private PatientDAO patientDAO = new PatientDAOImplementation();

    private StaffDAO staffDAO = new StaffDAOImplementation();

    private AppointmentDAO appointmentDAO = new AppointmentDAOImplementation();

    private DiagnosisDAO diagnosisDAO = new DiagnosisDAOImplementation();

    private MedicalHistoryDAO medicalHistoryDAO = new MedicalHistoryDAOImplementation();

    public static DAOProvider getInstance(){
        return instance;
    }

    public AccountDAO getAccountDAO(){
        return accountDAO;
    }

    public PatientDAO getPatientDAO() { return  patientDAO; }

    public StaffDAO getStaffDAO(){ return  staffDAO ;}

    public AppointmentDAO getAppointmentDAO() {return appointmentDAO;}

    public DiagnosisDAO getDiagnosisDAO() {return diagnosisDAO;}

    public MedicalHistoryDAO getMedicalHistoryDAO() {return medicalHistoryDAO;}
}
