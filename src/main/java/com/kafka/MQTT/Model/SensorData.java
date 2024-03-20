package com.kafka.MQTT.Model;

public class SensorData {
    private String temperature;
    private String pressure;
    private String altitude;
    private String soilmositure;
    private String note;
    private int status;
    private String type;
    private String date ;
    private String time;

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public String getPressure() {
        return pressure;
    }

    public void setPressure(String pressure) {
        this.pressure = pressure;
    }

    public String getAltitude() {
        return altitude;
    }

    public void setAltitude(String altitude) {
        this.altitude = altitude;
    }

    public String getSoilmositure() {
        return soilmositure;
    }

    public void setSoilmositure(String soilmositure) {
        this.soilmositure = soilmositure;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public SensorData(String temperature, String pressure, String altitude, String soilmositure, String note, int status, String type, String date, String time) {
        this.temperature = temperature;
        this.pressure = pressure;
        this.altitude = altitude;
        this.soilmositure = soilmositure;
        this.note = note;
        this.status = status;
        this.type = type;
        this.date = date;
        this.time = time;
    }
}
