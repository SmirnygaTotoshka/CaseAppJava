package ru.smirnygatotoshka.caseapp.DataRepresentation;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;

import java.sql.Date;
import java.sql.Time;

public class Change {
    private SimpleIntegerProperty doctor;
    private SimpleObjectProperty<Date> date;
    private SimpleObjectProperty<Time> startTime;
    private SimpleObjectProperty<Time> finishTime;

    public Change(int doctor, Date date, Time start_time, Time finish_time) {
        this.doctor = new SimpleIntegerProperty(doctor);
        this.date = new SimpleObjectProperty<>(date);
        this.startTime = new SimpleObjectProperty<>(start_time);
        this.finishTime = new SimpleObjectProperty<>(finish_time);
    }

    public Change(){

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

    public Date getDate() {
        return date.get();
    }

    public SimpleObjectProperty<Date> dateProperty() {
        return date;
    }

    public void setDate(Date date) {
        this.date.set(date);
    }

    public Time getStartTime() {
        return startTime.get();
    }

    public SimpleObjectProperty<Time> startTimeProperty() {
        return startTime;
    }

    public void setStartTime(Time startTime) {
        this.startTime.set(startTime);
    }

    public Time getFinishTime() {
        return finishTime.get();
    }

    public SimpleObjectProperty<Time> finishTimeProperty() {
        return finishTime;
    }

    public void setFinishTime(Time finishTime) {
        this.finishTime.set(finishTime);
    }
}
