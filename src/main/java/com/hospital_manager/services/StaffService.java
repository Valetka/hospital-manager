package com.hospital_manager.services;

import com.hospital_manager.entities.Staff;
import com.hospital_manager.services.exceptions.ServiceException;

import javax.servlet.http.Part;
import java.util.List;

public interface StaffService {
    Staff getStaffById(long id) throws ServiceException;

    Staff getStaffByAccount(long id) throws ServiceException;

    List<Staff> getAllByType(long typeId) throws ServiceException;

    void update(Staff staff) throws ServiceException;

    void savePictureToStaff(Staff staff, Part part) throws ServiceException;
}
