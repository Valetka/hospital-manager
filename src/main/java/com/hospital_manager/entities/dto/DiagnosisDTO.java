package com.hospital_manager.entities.dto;

import java.sql.Date;
import java.util.List;
import java.util.Objects;

public class DiagnosisDTO {

    private long id;
    private String definitiveDiagnosis;
    private String preliminaryDiagnosis;
    private Date receiptDate;
    private Date dischargeDate;
    private String patientFirstname;
    private String patientLastname;
    private List<AppointmentDTO> appointmentList;

    public DiagnosisDTO() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDefinitiveDiagnosis() {
        return definitiveDiagnosis;
    }

    public void setDefinitiveDiagnosis(String definitiveDiagnosis) {
        this.definitiveDiagnosis = definitiveDiagnosis;
    }

    public String getPreliminaryDiagnosis() {
        return preliminaryDiagnosis;
    }

    public void setPreliminaryDiagnosis(String preliminaryDiagnosis) {
        this.preliminaryDiagnosis = preliminaryDiagnosis;
    }

    public Date getReceiptDate() {
        return receiptDate;
    }

    public void setReceiptDate(Date receiptDate) {
        this.receiptDate = receiptDate;
    }

    public Date getDischargeDate() {
        return dischargeDate;
    }

    public void setDischargeDate(Date dischargeDate) {
        this.dischargeDate = dischargeDate;
    }

    public String getPatientFirstname() {
        return patientFirstname;
    }

    public void setPatientFirstname(String patientFirstname) {
        this.patientFirstname = patientFirstname;
    }

    public String getPatientLastname() {
        return patientLastname;
    }

    public void setPatientLastname(String patientLastname) {
        this.patientLastname = patientLastname;
    }

    public List<AppointmentDTO> getAppointmentList() {
        return appointmentList;
    }

    public void setAppointmentList(List<AppointmentDTO> appointmentList) {
        this.appointmentList = appointmentList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DiagnosisDTO that = (DiagnosisDTO) o;
        return id == that.id && Objects.equals(definitiveDiagnosis, that.definitiveDiagnosis) && Objects.equals(preliminaryDiagnosis, that.preliminaryDiagnosis) && Objects.equals(receiptDate, that.receiptDate) && Objects.equals(dischargeDate, that.dischargeDate) && Objects.equals(patientFirstname, that.patientFirstname) && Objects.equals(patientLastname, that.patientLastname) && Objects.equals(appointmentList, that.appointmentList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, definitiveDiagnosis, preliminaryDiagnosis, receiptDate, dischargeDate, patientFirstname, patientLastname, appointmentList);
    }

    @Override
    public String toString() {
        return "DiagnosisDTO{" +
                "id=" + id +
                ", definitiveDiagnosis='" + definitiveDiagnosis + '\'' +
                ", preliminaryDiagnosis='" + preliminaryDiagnosis + '\'' +
                ", receiptDate=" + receiptDate +
                ", dischargeDate=" + dischargeDate +
                ", patientFirstname='" + patientFirstname + '\'' +
                ", patientLastname='" + patientLastname + '\'' +
                ", appointmentList=" + appointmentList +
                '}';
    }
}
