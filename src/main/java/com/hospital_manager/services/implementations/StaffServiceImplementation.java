package com.hospital_manager.services.implementations;

import com.hospital_manager.dao.DAOProvider;
import com.hospital_manager.dao.StaffDAO;
import com.hospital_manager.dao.exceptions.DAOException;
import com.hospital_manager.entities.Staff;
import com.hospital_manager.services.StaffService;
import com.hospital_manager.services.exceptions.ServiceException;
import com.hospital_manager.services.util.UploadUtil;
import com.hospital_manager.services.validation.Validator;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.Part;
import java.util.List;

public class StaffServiceImplementation implements StaffService {

    private static final Logger logger = LogManager.getLogger(StaffServiceImplementation.class);
    private static final String INVALID = " is wrong";


    private static final DAOProvider provider  = DAOProvider.getInstance();

    @Override
    public Staff getStaffById(Long id) throws ServiceException {
        if (!Validator.isIdValid(id)) {
            logger.log(Level.WARN,id+INVALID);
            throw new ServiceException(id+INVALID);
        }
        StaffDAO staffDAO = provider.getStaffDAO();
        Staff staff;
        try {
            staff = staffDAO.getStaffById(id);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return staff;
    }

    @Override
    public Staff getStaffByAccount(long id) throws ServiceException {
        StaffDAO staffDAO = provider.getStaffDAO();
        Staff staff;
        try {
            staff = staffDAO.getStaffByAccount(id);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }

        return staff;
    }


    @Override
    public List<Staff> getAllByType(Long typeId) throws ServiceException {
        StaffDAO staffDAO = provider.getStaffDAO();
        List<Staff> allStaff;
        try {
            allStaff = staffDAO.getAllByType(typeId);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return allStaff;
    }

    @Override
    public void update(Staff staff) throws ServiceException {
        StaffDAO staffDAO = provider.getStaffDAO();
        try {
            staffDAO.update(staff);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void savePictureToStaff(Staff staff, Part part) throws ServiceException {
        String file = UploadUtil.upload(part);
        staff.setPicture(file);
        update(staff);
    }
}
