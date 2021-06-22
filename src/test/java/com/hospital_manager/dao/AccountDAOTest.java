package com.hospital_manager.dao;

import com.hospital_manager.dao.connection.PoolProvider;
import com.hospital_manager.dao.connection.exception.ConnectionPoolException;
import com.hospital_manager.dao.exceptions.DAOException;
import com.hospital_manager.dao.exceptions.DataNotFoundException;
import com.hospital_manager.entities.UserInfo;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

public class AccountDAOTest {
    private static final String RESOURCE_FILE = "postgresql_test";


    private static final AccountDAO accountDao = DAOProvider.getInstance().getAccountDAO();


    @BeforeClass
    public static void connectionPoolInit() throws ConnectionPoolException {
        PoolProvider.getConnectionPool().init(RESOURCE_FILE);
    }

    @AfterClass
    public static void connectionPoolDispose() throws ConnectionPoolException {
        PoolProvider.getConnectionPool().dispose();
    }


    @Test
    public void regPatient() throws DAOException {
        UserInfo userInfo = new UserInfo();
        userInfo.setFirstname("Виолетта");
        userInfo.setLastname("Прихно");
        userInfo.setLogin("violetta");
        userInfo.setPassword("Violetta1");
        userInfo.setRoleId(3);
        accountDao.registration(userInfo);
    }

    @Test
    public void regStaff() throws DAOException {
        UserInfo userInfo = new UserInfo();
        userInfo.setFirstname("Иван");
        userInfo.setLastname("Иванов");
        userInfo.setLogin("ivan");
        userInfo.setPassword("Ivan21");
        userInfo.setRoleId(2);
        accountDao.registration(userInfo);
    }

    @Test
    public void regAdmin() throws DAOException{
        UserInfo userInfo = new UserInfo();
        userInfo.setFirstname("Пётр");
        userInfo.setLastname("Петров");
        userInfo.setLogin("petr");
        userInfo.setPassword("Petrov1");
        userInfo.setRoleId(1);
        accountDao.registration(userInfo);
    }

    @Test
    public void authorizationTest1() throws DAOException {
        assertNotNull(accountDao.authorization("violetta","Violetta1"));
    }

    @Test
    public void authorizationTest2() throws DAOException {
        assertNotNull(accountDao.authorization("ivan","Ivan21"));
    }

    @Test
    public void authorizationNegativeTest1() throws DAOException {
        assertNull(accountDao.authorization("petr","Petrov1"));
    }

    @Test
    public void authorizationNegativeTest12() throws DAOException{
        assertNull(accountDao.authorization("violetta","violetta1"));
    }

    @Test
    public void findByLoginPositiveTest() throws DAOException {
        assertNotNull(accountDao.findByLogin("violetta"));
    }

    @Test
    public void findByLoginNegativeTest() throws DAOException {
        assertNull(accountDao.findByLogin("alex"));
    }

    @Test
    public void updatePasswordPositiveTest() throws DAOException {
        Long id = accountDao.findByLogin("petr");
        accountDao.updatePassword(id,"Petrov1","Petrov2");
    }

    @Test(expected = DataNotFoundException.class)
    public void updatePasswordNegativeTest() throws DAOException {
        Long id = accountDao.findByLogin("petr");
        accountDao.updatePassword(id,"petrovv","viTal00");
    }
}
