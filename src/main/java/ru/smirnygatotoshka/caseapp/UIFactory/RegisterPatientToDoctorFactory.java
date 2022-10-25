package ru.smirnygatotoshka.caseapp.UIFactory;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import ru.smirnygatotoshka.caseapp.DataRepresentation.Doctor;
import ru.smirnygatotoshka.caseapp.DataRepresentation.Patient;
import ru.smirnygatotoshka.caseapp.DataRepresentation.ScheduleItem;
import ru.smirnygatotoshka.caseapp.Database.ChangeActions;
import ru.smirnygatotoshka.caseapp.Database.ScheduleActions;
import ru.smirnygatotoshka.caseapp.GlobalResources;

public class RegisterPatientToDoctorFactory extends PatientLookupFormFactory{

    private ScheduleItem item;
    private Doctor doctor;

    public RegisterPatientToDoctorFactory(String id_prefix, ScheduleItem item) {
        super(id_prefix);
        this.item = item;
    }

    @Override
    public Parent create() {
        GridPane pane = (GridPane) super.create();
        GridPane control = (GridPane) pane.getChildren().get(2);
        boolean success_remove = control.getChildren().removeAll(get("Add"), get("Edit"), get("Delete"));
        control.getColumnConstraints().removeAll(control.getColumnConstraints());
        control.getRowConstraints().remove(0);
        addConstrains(control, new int[]{100}, new int[]{100});
        Button select = new Button("Выбрать");
        select.setStyle("-fx-font: Serif;" +
                "-fx-font-size: 18px;" +
                "-fx-background-color: #CCCCFF;" +
                "-fx-border-color: #000000;");
        select.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        select.setOnAction(event -> {
            editAction(event);
        });
        put(select, "Select");
        GridPane.setMargin(select,new Insets(10));
        control.add(select,0,0);
        return pane;
    }

    @Override
    protected void closeForm() {
        GlobalResources.closeStage("ChoosePatientForm");
    }

    @Override
    public void editAction(ActionEvent event) {
        Patient patient = tablePatients.getSelectionModel().getSelectedItem();
        if (patient == null){
            GlobalResources.alert(Alert.AlertType.WARNING, "Выберите пациента.");
        }
        else {
            ScheduleActions.edit(item, patient);
            closeForm();
        }
    }

}
