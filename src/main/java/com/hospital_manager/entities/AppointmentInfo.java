package com.hospital_manager.entities;

import java.util.Objects;

public class AppointmentInfo {

    private long id;
    private String info;
    private AppointmentType type;

    public AppointmentInfo() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public AppointmentType getType() {
        return type;
    }

    public void setType(int type) {
        this.type = AppointmentType.getAppointmentTypeById(type);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AppointmentInfo that = (AppointmentInfo) o;
        return id == that.id && Objects.equals(info, that.info);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, info);
    }

    @Override
    public String toString() {
        return "AppointmentInfo{" +
                "id=" + id +
                ", info='" + info + '\'' +
                '}';
    }
}
