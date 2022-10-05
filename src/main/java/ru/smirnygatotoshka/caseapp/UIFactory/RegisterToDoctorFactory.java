package ru.smirnygatotoshka.caseapp.UIFactory;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import ru.smirnygatotoshka.caseapp.DataRepresentation.ScheduleItem;

import java.util.function.Predicate;

public class RegisterToDoctorFactory extends LookupWithSearch<ScheduleItem,String> implements DataChanger {


    public RegisterToDoctorFactory(String id_prefix) {
        super(id_prefix, null);
    }

    @Override
    protected String getColumnNameFromDB(String item) {
        return null;
    }

    @Override
    protected Predicate<String> search() {
        return null;
    }

    @Override
    public void addAction(ActionEvent event) {

    }

    @Override
    public void editAction(ActionEvent event) {

    }

    @Override
    public void deleteAction(ActionEvent event) {

    }
}
