package ru.smirnygatotoshka.caseapp.DataRepresentation;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;

import java.sql.Time;

public class ScheduleItem {

    private SimpleIntegerProperty patient;
    private SimpleIntegerProperty doctor;
    private SimpleIntegerProperty change;
    private SimpleObjectProperty<Time> time;

    public ScheduleItem(int patient, int doctor, int change, Time time) {
        this.patient = new SimpleIntegerProperty(patient);
        this.doctor = new SimpleIntegerProperty(doctor);
        this.change = new SimpleIntegerProperty(change);
        this.time = new SimpleObjectProperty<>(time);
    }

    public int getPatient() {
        return patient.get();
    }

    public SimpleIntegerProperty patientProperty() {
        return patient;
    }

    public void setPatient(int patient) {
        this.patient.set(patient);
    }

    public int getDoctor() {
        return doctor.get();
    }

    public SimpleIntegerProperty doctorProperty() {
        return doctor;
    }

    public void setDoctor(int doctor) {
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
