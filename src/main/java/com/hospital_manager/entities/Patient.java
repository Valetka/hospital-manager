package com.hospital_manager.entities;

import java.util.Objects;

public class Patient implements User{

    private long id;
    private String firstname;
    private String lastname;
    private int age;
    private Department department;
    private long attendingDoctorID;
    private long statusID;
    private long accountID;
    private String patientPic;

    public Patient() {
    }

    public Patient(long id, String firstname, String lastname, int age, Department department, long attendingDoctorID,
                   long statusID, long accountID, String patientPic) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.age = age;
        this.department = department;
        this.attendingDoctorID = attendingDoctorID;
        this.statusID = statusID;
        this.accountID = accountID;
        this.patientPic = patientPic;
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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public long getAttendingDoctorID() {
        return attendingDoctorID;
    }

    public void setAttendingDoctorID(long attendingDoctorID) {
        this.attendingDoctorID = attendingDoctorID;
    }

    public long getStatusID() {
        return statusID;
    }

    public void setStatusID(long statusID) {
        this.statusID = statusID;
    }

    public long getAccountID() {
        return accountID;
    }

    public void setAccountID(long accountID) {
        this.accountID = accountID;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(int departmentId) {
        this.department = Department.getDepartmentById(departmentId);
    }

    public String getPatientPic() {
        return patientPic;
    }

    public void setPatientPic(String patientPic) {
        this.patientPic = patientPic;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Patient patient = (Patient) o;
        return id == patient.id && age == patient.age && accountID == patient.accountID && Objects.equals(firstname, patient.firstname) && Objects.equals(lastname, patient.lastname) && department == patient.department && Objects.equals(attendingDoctorID, patient.attendingDoctorID) && Objects.equals(statusID, patient.statusID) && Objects.equals(patientPic, patient.patientPic);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstname, lastname, age, department, attendingDoctorID, statusID, accountID, patientPic);
    }


    @Override
    public String toString() {
        return "Patient{" +
                "id=" + id +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", age=" + age +
                ", department=" + department +
                ", attendingDoctorID=" + attendingDoctorID +
                ", statusID=" + statusID +
                ", accountID=" + accountID +
                ", patientPic='" + patientPic + '\'' +
                '}';
    }
}
