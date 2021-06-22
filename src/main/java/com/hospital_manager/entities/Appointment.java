package com.hospital_manager.entities;

import java.sql.Date;
import java.util.Objects;

public class Appointment {
    private long id;
    private Date dateOfAppointment;
    private Date dateOfCompletion;
    private long patientId;
    private long appointingDoctorId;
    private long executeStaffId;
    private long infoId;
    private long diagnosisID;
    private AppointmentStatus status;


    public Appointment(long id, Date dateOfAppointment, Date dateOfCompletion, long patientId, long appointingDoctorId,
                       long executeStaffId, long infoId, long diagnosisID, AppointmentStatus status) {
        this.id = id;
        this.dateOfAppointment = dateOfAppointment;
        this.dateOfCompletion = dateOfCompletion;
        this.patientId = patientId;
        this.appointingDoctorId = appointingDoctorId;
        this.executeStaffId = executeStaffId;
        this.infoId = infoId;
        this.diagnosisID = diagnosisID;
        this.status = status;
    }

    public Appointment() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getDateOfAppointment() {
        return dateOfAppointment;
    }

    public void setDateOfAppointment(Date dateOfAppointment) {
        this.dateOfAppointment = dateOfAppointment;
    }

    public Date getDateOfCompletion() {
        return dateOfCompletion;
    }

    public void setDateOfCompletion(Date dateOfCompletion) {
        this.dateOfCompletion = dateOfCompletion;
    }

    public long getPatientId() {
        return patientId;
    }

    public void setPatientId(long patientId) {
        this.patientId = patientId;
    }

    public long getAppointingDoctorId() {
        return appointingDoctorId;
    }

    public void setAppointingDoctorId(long appointingDoctorId) {
        this.appointingDoctorId = appointingDoctorId;
    }

    public long getExecuteStaffId() {
        return executeStaffId;
    }

    public void setExecuteStaffId(long executeStaffId) {
        this.executeStaffId = executeStaffId;
    }

    public long getInfoId() {
        return infoId;
    }

    public void setInfoId(long infoId) {
        this.infoId = infoId;
    }

    public AppointmentStatus getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = AppointmentStatus.getStatusById(status);
    }

    public void setStatus(AppointmentStatus status) {
        this.status = status;
    }

    public long getDiagnosisID() {
        return diagnosisID;
    }

    public void setDiagnosisID(long diagnosisID) {
        this.diagnosisID = diagnosisID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Appointment that = (Appointment) o;
        return id == that.id && patientId == that.patientId && appointingDoctorId == that.appointingDoctorId && executeStaffId == that.executeStaffId && infoId == that.infoId && diagnosisID == that.diagnosisID && Objects.equals(dateOfAppointment, that.dateOfAppointment) && Objects.equals(dateOfCompletion, that.dateOfCompletion) && status == that.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, dateOfAppointment, dateOfCompletion, patientId, appointingDoctorId, executeStaffId, infoId, diagnosisID, status);
    }

    @Override
    public String toString() {
        return "Appointment{" +
                "id=" + id +
                ", dateOfAppointment=" + dateOfAppointment +
                ", dateOfCompletion=" + dateOfCompletion +
                ", patientId=" + patientId +
                ", appointingDoctorId=" + appointingDoctorId +
                ", executeStaffId=" + executeStaffId +
                ", infoId=" + infoId +
                ", diagnosisID=" + diagnosisID +
                ", status=" + status +
                '}';
    }
}
