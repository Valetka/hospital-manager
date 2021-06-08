package com.hospital_manager.controllers.commands;

import com.hospital_manager.controllers.commands.implementations.*;

import java.util.HashMap;
import java.util.Map;

public final class CommandProvider {

    private Map<CommandNames,Command> commands = new HashMap<>();

    public CommandProvider() {
        commands.put(CommandNames.LOGIN, new Login());
        commands.put(CommandNames.LOGOUT, new Logout());
        commands.put(CommandNames.ADD_ACCOUNT, new AddAccount());
        commands.put(CommandNames.CHANGE_LOCALE, new ChangeLocale());
        commands.put(CommandNames.REGISTRATION, new Registration());
        commands.put(CommandNames.GO_TO_MAIN_PAGE, new GoToMainPage());
        commands.put(CommandNames.GO_TO_INDEX_PAGE, new GoToIndexPage());
        commands.put(CommandNames.UPDATE_PASSWORD,new UpdatePassword());
        commands.put(CommandNames.GO_TO_PROFILE_PAGE, new GoToProfilePage());
        commands.put(CommandNames.GO_TO_PASSWORD_UPDATE_PAGE, new GoToPasswordUpdatePage());

        commands.put(CommandNames.ADD_DIAGNOSIS, new AddDiagnosis());
        commands.put(CommandNames.ADD_APPOINTMENT, new AddAppointment());
        commands.put(CommandNames.GO_TO_DIAGNOSIS_PAGE,new GoToDiagnosisPage());
        commands.put(CommandNames.GO_TO_RECEIPT_DATE_PAGE,new GoToReceiptDatePage());
        commands.put(CommandNames.ADD_PATIENTS_TO_DOCTOR, new AddPatientsToDoctor());
        commands.put(CommandNames.GO_TO_FREE_PATIENTS_PAGE, new GoToFreePatientsPage());
        commands.put(CommandNames.GO_TO_ADD_APPOINTMENT_PAGE, new GoToAddAppointmentPage());
        commands.put(CommandNames.GO_TO_DOCTORS_PATIENTS_PAGE, new GoToDoctorsPatientsPage());
        commands.put(CommandNames.UPDATE_APPOINTMENT_STATUS, new UpdateAppointmentStatus());
        commands.put(CommandNames.GO_TO_ADD_APPOINTMENT_NEXT_PAGE, new GoToAddAppointmentNextPage());
        commands.put(CommandNames.GO_TO_STAFF_APPOINTMENT_LIST_PAGE, new GoToStaffAppointmentListPage());

        commands.put(CommandNames.GO_TO_ADD_STAFF_PAGE, new GoToAddStaffPage());
        commands.put(CommandNames.SUBMIT_APPLICATION, new SubmitApplication());
        commands.put(CommandNames.ADD_ADDITIONAL_INFO, new AddAdditionalInfo());
        commands.put(CommandNames.GO_TO_MEDICAL_HISTORY_PAGE,new GoToMedicalHistoryPage());
        commands.put(CommandNames.GO_TO_ADD_ADDITIONAL_INFO_PAGE, new GoToAddAdditionalInfoPage());
        commands.put(CommandNames.GO_TO_PATIENT_APPOINTMENT_LIST_PAGE, new GoToPatientAppointmentListPage());
    }

    public Command takeCommand(String name){
        CommandNames commandName = CommandNames.valueOf(name.toUpperCase());
        return commands.get(commandName);

    }
}
