package com.hospital_manager.services;

import com.hospital_manager.entities.Account;
import com.hospital_manager.entities.UserInfo;
import com.hospital_manager.services.exceptions.ServiceException;

public interface AccountService {

    Account authorization(String login, String password) throws ServiceException;

    void registration(UserInfo regInfo) throws ServiceException;

    void updatePassword(long accountId, String oldPass, String newPass) throws ServiceException;
}
