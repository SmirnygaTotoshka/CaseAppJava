package ru.smirnygatotoshka.caseapp.DataRepresentation;

import javafx.beans.property.SimpleStringProperty;

public class Police {
    private SimpleStringProperty number;
    private SimpleStringProperty organization;

    public Police(String number, String organization) {
        this.number = new SimpleStringProperty(number);
        this.organization = new SimpleStringProperty(organization);
    }

    public Police() {
        number = new SimpleStringProperty("0");
        organization = new SimpleStringProperty("0");
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

    public String getOrganization() {
        return organization.get();
    }

    public SimpleStringProperty organizationProperty() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization.set(organization);
    }
}
