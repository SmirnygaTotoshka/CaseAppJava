package ru.smirnygatotoshka.caseapp.DataRepresentation;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;

import java.sql.Date;
import java.sql.Time;

public class Change {
    private SimpleIntegerProperty doctor;
    private SimpleObjectProperty<Date> date;
    private SimpleObjectProperty<Time> start_time;
    private SimpleObjectProperty<Time> finish_time;

    public Change(int doctor, Date date, Time start_time, Time finish_time) {
        this.doctor = new SimpleIntegerProperty(doctor);
        this.date = new SimpleObjectProperty<>(date);
        this.start_time = new SimpleObjectProperty<>(start_time);
        this.finish_time = new SimpleObjectProperty<>(finish_time);
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

    public Time getStart_time() {
        return start_time.get();
    }

    public SimpleObjectProperty<Time> start_timeProperty() {
        return start_time;
    }

    public void setStart_time(Time start_time) {
        this.start_time.set(start_time);
    }

    public Time getFinish_time() {
        return finish_time.get();
    }

    public SimpleObjectProperty<Time> finish_timeProperty() {
        return finish_time;
    }

    public void setFinish_time(Time finish_time) {
        this.finish_time.set(finish_time);
    }
}
