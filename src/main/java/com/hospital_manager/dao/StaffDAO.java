package com.hospital_manager.dao;

import com.hospital_manager.dao.exceptions.DAOException;
import com.hospital_manager.entities.Staff;

import java.util.List;

public interface StaffDAO {

    Staff getStaffById(Long id)throws DAOException;

    Staff getStaffByAccount(long id) throws DAOException;

    List<Staff> getAllByType(Long typeId) throws DAOException;

    void update(Staff staff) throws DAOException;
}
