package com.hospital_manager.entities;

import java.util.Objects;

public class MedicalHistory {
    private long id;
    private long patientId;


    public MedicalHistory(long id, long patientId) {
        this.id = id;
        this.patientId = patientId;
    }

    public MedicalHistory() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getPatientId() {
        return patientId;
    }

    public void setPatientId(long patientId) {
        this.patientId = patientId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MedicalHistory that = (MedicalHistory) o;
        return id == that.id && patientId == that.patientId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, patientId);
    }

    @Override
    public String toString() {
        return "MedicalHistory{" +
                "id=" + id +
                ", patientId=" + patientId +
                '}';
    }
}
