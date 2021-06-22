package com.hospital_manager.dao;

import com.hospital_manager.dao.connection.PoolProvider;
import com.hospital_manager.dao.connection.exception.ConnectionPoolException;
import com.hospital_manager.dao.exceptions.DAOException;
import com.hospital_manager.entities.Staff;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;

public class StaffDAOTest {
    private static final String RESOURCE_FILE = "postgresql_test";


    private static final StaffDAO staffDAO = DAOProvider.getInstance().getStaffDAO();


    @BeforeClass
    public static void connectionPoolInit() throws ConnectionPoolException {
        PoolProvider.getConnectionPool().init(RESOURCE_FILE);
    }

    @AfterClass
    public static void connectionPoolDispose() throws ConnectionPoolException {
        PoolProvider.getConnectionPool().dispose();
    }

    @Test
    public void getStaffPositive() throws DAOException {
        assertNotNull(staffDAO.getStaffById(1L));
    }

    @Test
    public void getStaffNegative() throws DAOException {
        assertNull(staffDAO.getStaffById(5L));
    }

    @Test
    public void getStaffAccountPositive() throws DAOException {
        assertNotNull(staffDAO.getStaffByAccount(48L));
    }

    @Test
    public void update() throws DAOException {
        Staff staff = new Staff();
        staff.setId(1L);
        staff.setDepartment(1);
        staff.setFirstname("staff1");
        staff.setLastname("LstStaff");
        staffDAO.update(staff);
        assertNotEquals(staffDAO.getStaffById(1L),staff);
    }
}
