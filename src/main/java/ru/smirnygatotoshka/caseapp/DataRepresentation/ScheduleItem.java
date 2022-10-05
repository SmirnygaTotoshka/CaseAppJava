package ru.smirnygatotoshka.caseapp.DataRepresentation;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;

import java.sql.Time;

public class ScheduleItem {

    private SimpleStringProperty patient;
    private SimpleStringProperty doctor;
    private SimpleIntegerProperty change;
    private SimpleObjectProperty<Time> time;

    public ScheduleItem(String patient, String doctor, int change, Time time) {
        this.patient = new SimpleStringProperty(patient);
        this.doctor = new SimpleStringProperty(doctor);
        this.change = new SimpleIntegerProperty(change);
        this.time = new SimpleObjectProperty<>(time);
    }

    public String getPatient() {
        return patient.get();
    }

    public SimpleStringProperty patientProperty() {
        return patient;
    }

    public void setPatient(String patient) {
        this.patient.set(patient);
    }

    public String getDoctor() {
        return doctor.get();
    }

    public SimpleStringProperty doctorProperty() {
        return doctor;
    }

    public void setDoctor(String doctor) {
        this.doctor.set(doctor);
    }

    public int getChange() {
        return change.get();
    }

    public SimpleIntegerProperty changeProperty() {
        return change;
    }

    public void setChange(int change) {
        this.change.set(change);
    }

    public Time getTime() {
        return time.get();
    }

    public SimpleObjectProperty<Time> timeProperty() {
        return time;
    }

    public void setTime(Time time) {
        this.time.set(time);
    }
}
