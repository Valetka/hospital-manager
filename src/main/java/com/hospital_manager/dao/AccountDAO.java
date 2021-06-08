package com.hospital_manager.dao;

import com.hospital_manager.dao.exceptions.DAOException;
import com.hospital_manager.entities.Account;
import com.hospital_manager.entities.UserInfo;

public interface AccountDAO {
    Account authorization (String login, String password) throws DAOException;

    void registration(UserInfo regInfo) throws DAOException;


    void updatePassword(long accountId, String oldPass, String newPass) throws DAOException;


    Long findByLogin(String login) throws DAOException;
}
