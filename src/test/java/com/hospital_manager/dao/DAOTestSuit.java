package com.hospital_manager.dao;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@Suite.SuiteClasses({AccountDAOTest.class,AppointmentDAOTest.class,StaffDAOTest.class,PatientDAOTest.class})
@RunWith(Suite.class)
public class DAOTestSuit {
}
