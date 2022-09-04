package ru.smirnygatotoshka.caseapp.UIFactory;

import javafx.event.ActionEvent;
import ru.smirnygatotoshka.caseapp.DataRepresentation.Patient;

public class PoliceEditFactory extends DatabaseEditFactory{


    public PoliceEditFactory(String id_prefix, Patient patient, int row_percent) {
        super(id_prefix,row_percent);
    }

    @Override
    protected void saveRecord(ActionEvent event) {

    }

    @Override
    protected String getColumnNameFromDB(String item) {
        return null;
    }
}
