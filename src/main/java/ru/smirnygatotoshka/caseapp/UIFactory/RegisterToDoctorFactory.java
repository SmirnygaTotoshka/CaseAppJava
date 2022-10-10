package ru.smirnygatotoshka.caseapp.UIFactory;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Separator;
import javafx.scene.layout.GridPane;
import javafx.util.Callback;
import ru.smirnygatotoshka.caseapp.DataRepresentation.ScheduleItem;

import java.time.LocalDate;
import java.util.function.Predicate;

public class RegisterToDoctorFactory extends LookupWithSearch<String,ScheduleItem> implements DataChanger {

    private static ObservableList<String> LOOKUP_ITEMS = FXCollections.observableArrayList("Время","ФИО пациента");

    public RegisterToDoctorFactory(String id_prefix) {
        super(id_prefix, LOOKUP_ITEMS);
    }

    @Override
    public Parent create() {
        GridPane parent = new GridPane();
        parent.setStyle("-fx-background-color: #FFCCCC;");
        addConstrains(parent, new int[]{100}, new int[]{35,5,40,5,30});

        ChooseDoctorForm doctorForm = new ChooseDoctorForm(id_prefix);
        ControlForm controlForm = new ControlForm(id_prefix, false, this);

        GridPane choose_doctor = (GridPane) doctorForm.create();
        Separator separator = new Separator(Orientation.VERTICAL);
        Separator separator1 = new Separator(Orientation.VERTICAL);
        GridPane choose_change = (GridPane) super.create();
        GridPane controllers = (GridPane) controlForm.create();

        uniteElements(doctorForm.elements);
        uniteElements(controlForm.elements);

        addConstrains(controllers, new int[0], new int[]{20,20});

        parent.add(choose_doctor,0,0);
        parent.add(separator,1,0);
        parent.add(choose_change,2,0);
        parent.add(separator1,3,0);
        parent.add(controllers,4,0);
        //parent.setHgap(15);
        //parent.setVgap(5);
        parent.setAlignment(Pos.CENTER);

        return parent;
    }

    @Override
    protected String getColumnNameFromDB(String item) {
        return null;
    }

    @Override
    protected Predicate<ScheduleItem> search() {
        return null;
    }

    @Override
    protected Parent createLookup() {
        DatePicker datePicker = new DatePicker(LocalDate.now());
        datePicker.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        datePicker.setDayCellFactory(new Callback<>() {
            @Override
            public DateCell call(DatePicker datePicker) {
                return new DateCell(){
                    LocalDate minDate = LocalDate.now().minusYears(1), maxDate = LocalDate.now().plusYears(1);
                    @Override
                    public void updateItem(LocalDate item, boolean empty){
                        super.updateItem(item,empty);
                        if (item.isBefore(minDate)){
                            setDisable(true);
                            setStyle("-fx-background-color: #ffc0cb");
                        }
                        else if (item.isAfter(maxDate)){
                            setDisable(true);
                            setStyle("-fx-background-color: #ffc0cb");
                        }
                    }
                };
            }
        });
        put(datePicker,"chooseDate");
        return datePicker;
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
