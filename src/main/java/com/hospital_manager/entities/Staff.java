package com.hospital_manager.entities;

import java.util.Objects;

public class Staff implements User {

    private long id;
    private String firstname;
    private String lastname;
    private String picture;
    private long staffTypeID;
    private Department department;
    private long accountID;

    public Staff() {
    }

    public Staff(long id, String firstname, String lastname, String picture, long staffTypeID, Department department, long accountID) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.picture = picture;
        this.staffTypeID = staffTypeID;
        this.department = department;
        this.accountID = accountID;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public long getStaffTypeID() {
        return staffTypeID;
    }

    public void setStaffTypeID(long staffTypeID) {
        this.staffTypeID = staffTypeID;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(int departmentId) {
        this.department = Department.getDepartmentById(departmentId);
    }

    public long getAccountID() {
        return accountID;
    }

    public void setAccountID(long accountID) {
        this.accountID = accountID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Staff staff = (Staff) o;
        return id == staff.id && staffTypeID == staff.staffTypeID && accountID == staff.accountID && Objects.equals(firstname, staff.firstname) && Objects.equals(lastname, staff.lastname) && Objects.equals(picture, staff.picture) && department == staff.department;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstname, lastname, picture, staffTypeID, department, accountID);
    }

    @Override
    public String toString() {
        return "Staff{" +
                "id=" + id +
                ", firstname='" + firstname + '\'' +
                ", Lastname='" + lastname + '\'' +
                ", picture='" + picture + '\'' +
                ", StaffTypeID=" + staffTypeID +
                ", department=" + department +
                ", accountID=" + accountID +
                '}';
    }
}
