package com.hospital_manager.services.implementations;

import com.hospital_manager.dao.AccountDAO;
import com.hospital_manager.dao.DAOProvider;
import com.hospital_manager.dao.exceptions.DAOException;
import com.hospital_manager.dao.exceptions.DataNotFoundException;
import com.hospital_manager.entities.Account;
import com.hospital_manager.entities.UserInfo;
import com.hospital_manager.services.AccountService;
import com.hospital_manager.services.exceptions.DataFormatServiceException;
import com.hospital_manager.services.exceptions.DataNotFoundServiceException;
import com.hospital_manager.services.exceptions.LoginIsBusyException;
import com.hospital_manager.services.exceptions.ServiceException;
import com.hospital_manager.services.validation.Validator;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class AccountServiceImplementation implements AccountService {

    private static final Logger logger = LogManager.getLogger(AccountServiceImplementation.class);
    private static final String WRONG_LOGIN_OR_PASSWORD = "login and password are invalid";
    private static final String LOGIN_BUSY = "login is busy";
    private static final String INVALID = "user data invalid";


    private static final DAOProvider provider  = DAOProvider.getInstance();

    @Override
    public Account authorization(String login, String password) throws ServiceException {
        login = login.trim();
        password = password.trim();
        if(!Validator.isPasswordValid(password)&&!Validator.isLoginValid(login))
        {
            logger.log(Level.WARN,WRONG_LOGIN_OR_PASSWORD);
            throw new DataFormatServiceException(WRONG_LOGIN_OR_PASSWORD);
        }
        AccountDAO userDAO = provider.getAccountDAO();
        Account account;
        try {
            account = userDAO.authorization(login,password);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return account;
    }

    @Override
    public void registration(UserInfo regInfo) throws ServiceException {
        if(!Validator.isRegistrationInfoValid(regInfo))
        {
            logger.log(Level.WARN,INVALID);
            throw new DataFormatServiceException(INVALID);
        }
        if(!isFreeLogin(regInfo.getLogin())){
            logger.log(Level.WARN,LOGIN_BUSY);
            throw new LoginIsBusyException(LOGIN_BUSY);
        }
        AccountDAO userDAO = provider.getAccountDAO();
        try {
            userDAO.registration(regInfo);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    private boolean isFreeLogin(String login) throws ServiceException {
        AccountDAO userDAO = provider.getAccountDAO();
        Long id;
        try {
            id = userDAO.findByLogin(login);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return id == null;
    }

    @Override
    public void updatePassword(long accountId, String oldPass, String newPass) throws ServiceException {
        if(!Validator.isPasswordValid(newPass)){
            logger.log(Level.WARN, INVALID);
            throw new DataFormatServiceException(INVALID);
        }
        AccountDAO userDAO = provider.getAccountDAO();
        try {
            userDAO.updatePassword(accountId,oldPass,newPass);
        }catch (DataNotFoundException e){
            throw new DataNotFoundServiceException(e);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }
}
