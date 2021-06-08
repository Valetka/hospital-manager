package com.hospital_manager.entities;

import java.sql.Date;
import java.util.Objects;

public class Diagnosis {
    private long id;
    private String definitiveDiagnosis;
    private String preliminaryDiagnosis;
    private Date receiptDate;
    private Date dischargeDate;
    private long patientId;
    private long medicalHistoryId;


    public Diagnosis(long id, String definitiveDiagnosis, String preliminaryDiagnosis, Date receiptDate,
                     Date dischargeDate, long patientId, long medicalHistoryId) {
        this.id = id;
        this.definitiveDiagnosis = definitiveDiagnosis;
        this.preliminaryDiagnosis = preliminaryDiagnosis;
        this.receiptDate = receiptDate;
        this.dischargeDate = dischargeDate;
        this.patientId = patientId;
        this.medicalHistoryId = medicalHistoryId;
    }

    public Diagnosis() {
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

    public Date getDischargeDate() {
        return dischargeDate;
    }

    public void setDischargeDate(Date dischargeDate) {
        this.dischargeDate = dischargeDate;
    }

    public long getPatientId() {
        return patientId;
    }

    public void setPatientId(long patientId) {
        this.patientId = patientId;
    }

    public String getPreliminaryDiagnosis() {
        return preliminaryDiagnosis;
    }

    public void setPreliminaryDiagnosis(String preliminaryDiagnosis) {
        this.preliminaryDiagnosis = preliminaryDiagnosis;
    }

    public long getMedicalHistoryId() {
        return medicalHistoryId;
    }

    public void setMedicalHistoryId(long medicalHistoryId) {
        this.medicalHistoryId = medicalHistoryId;
    }

    public Date getReceiptDate() {
        return receiptDate;
    }

    public void setReceiptDate(Date receiptDate) {
        this.receiptDate = receiptDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Diagnosis epicrisis = (Diagnosis) o;
        return id == epicrisis.id && patientId == epicrisis.patientId && medicalHistoryId == epicrisis.medicalHistoryId && Objects.equals(definitiveDiagnosis, epicrisis.definitiveDiagnosis) && Objects.equals(preliminaryDiagnosis, epicrisis.preliminaryDiagnosis) && Objects.equals(receiptDate, epicrisis.receiptDate) && Objects.equals(dischargeDate, epicrisis.dischargeDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, definitiveDiagnosis, preliminaryDiagnosis, receiptDate, dischargeDate, patientId, medicalHistoryId);
    }

    @Override
    public String toString() {
        return "Diagnosis{" +
                "id=" + id +
                ", definitiveDiagnosis='" + definitiveDiagnosis + '\'' +
                ", preliminaryDiagnosis='" + preliminaryDiagnosis + '\'' +
                ", receiptDate=" + receiptDate +
                ", dischargeDate=" + dischargeDate +
                ", patientId=" + patientId +
                ", medicalHistoryId=" + medicalHistoryId +
                '}';
    }
}
