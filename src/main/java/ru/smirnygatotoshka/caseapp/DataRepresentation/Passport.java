package ru.smirnygatotoshka.caseapp.DataRepresentation;

import javafx.beans.property.SimpleStringProperty;

public class Passport {
    private SimpleStringProperty number;
    private SimpleStringProperty address;

    public Passport(String number, String address) {
        this.number = new SimpleStringProperty(number);
        this.address = new SimpleStringProperty(address);
    }

    public String getNumber() {
        return number.get();
    }

    public SimpleStringProperty numberProperty() {
        return number;
    }

    public void setNumber(String number) {
        this.number.set(number);
    }

    public String getAddress() {
        return address.get();
    }

    public SimpleStringProperty addressProperty() {
        return address;
    }

    public void setAddress(String address) {
        this.address.set(address);
    }
}
