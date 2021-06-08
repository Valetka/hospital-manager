package com.hospital_manager.controllers.commands.implementations;

import com.hospital_manager.controllers.builder.UserInfoBuilder;
import com.hospital_manager.controllers.commands.Command;
import com.hospital_manager.entities.*;
import com.hospital_manager.services.*;
import com.hospital_manager.services.exceptions.DataFormatServiceException;
import com.hospital_manager.services.exceptions.LoginIsBusyException;
import com.hospital_manager.services.exceptions.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Arrays;

import static com.hospital_manager.controllers.commands.CommandParameter.*;

public class AddAccount implements Command {


    private static final String GO_TO_REGISTRATION_PAGE = "Controller?command=registration";

    private static final String REGISTRATION_OK = "local.message.registration_ok";
    private static final String ERROR_BUSY = "local.error.login_is_busy";
    private static final String ERROR_DATA = "local.error.data_format";

    private static final String STAFF_TYPE = "staff_type";


    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        long staffType = 0;
        String returnErrorPage;

        HttpSession session = request.getSession(true);
        Boolean isAuth = (Boolean) session.getAttribute(ATTRIBUTE_AUTH);

        UserInfoBuilder userInfoBuilder = new UserInfoBuilder();
        userInfoBuilder.buildUserInfo(request);

        if (isAuth != null) {
            returnErrorPage = GO_TO_ADD_STAFF_PAGE;
            staffType = Long.parseLong(request.getParameter(STAFF_TYPE));
            userInfoBuilder.setRoleId(2);
        } else {

            returnErrorPage = GO_TO_REGISTRATION_PAGE;
            userInfoBuilder.setRoleId(3);
        }

        UserInfo userInfo = userInfoBuilder.getUserInfo();
        ServiceProvider provider = ServiceProvider.getInstance();
        AccountService accountService = provider.getAccountService();
        MedicalHistoryService medicalHistoryService = provider.getMedicalHistoryService();
        StaffService staffService = provider.getStaffService();
        PatientService patientService = provider.getPatientService();
        try {
            accountService.registration(userInfo);
            Account account = accountService.authorization(userInfo.getLogin(), userInfo.getPassword());
            if (account.getRoleId() == 3) {
                Patient patient = ServiceProvider.getInstance().getPatientService().getPatientByAccount(account.getId());
                patientService.savePictureToPatient(patient, null);
                MedicalHistory medicalHistory = new MedicalHistory();
                medicalHistory.setPatientId(patient.getId());
                medicalHistoryService.add(medicalHistory);
            } else if (account.getRoleId() == 2) {
                Staff staff = ServiceProvider.getInstance().getStaffService().getStaffByAccount(account.getId());
                staff.setStaffTypeID(staffType);
                staff.setFirstname(userInfo.getFirstname());
                staff.setLastname(userInfo.getLastname());
                staffService.update(staff);
                staffService.savePictureToStaff(staff, null);
            }
            session.setAttribute(ATTRIBUTE_INFO_MESSAGE, Arrays.asList(REGISTRATION_OK));
            response.sendRedirect(GO_TO_MAIN_PAGE);
        } catch (LoginIsBusyException e) {
            session.setAttribute(ATTRIBUTE_ERROR_MESSAGE, Arrays.asList(ERROR_BUSY));
            response.sendRedirect(returnErrorPage);
        } catch (DataFormatServiceException e) {
            session.setAttribute(ATTRIBUTE_ERROR_MESSAGE, Arrays.asList(ERROR_DATA));
            response.sendRedirect(returnErrorPage);
        } catch (ServiceException e) {
            response.sendRedirect(GO_TO_ERROR_PAGE);
        }
    }
}
