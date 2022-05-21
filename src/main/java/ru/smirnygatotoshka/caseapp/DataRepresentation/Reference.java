package ru.smirnygatotoshka.caseapp.DataRepresentation;

import javafx.beans.property.SimpleStringProperty;

public class Reference {
    private SimpleStringProperty NAME;

    public Reference(String NAME) {
        this.NAME = new SimpleStringProperty(NAME);
    }

    public String getNAME() {
        return NAME.get();
    }

    public SimpleStringProperty NAMEProperty() {
        return NAME;
    }

    public void setNAME(String NAME) {
        this.NAME.set(NAME);
    }

    @Override
    public String toString() {
        return NAME.getValue();
    }
}
