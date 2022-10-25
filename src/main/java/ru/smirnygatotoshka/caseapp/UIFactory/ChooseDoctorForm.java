package ru.smirnygatotoshka.caseapp.UIFactory;

import javafx.collections.ObservableList;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.util.Callback;
import ru.smirnygatotoshka.caseapp.DataRepresentation.Doctor;
import ru.smirnygatotoshka.caseapp.DataRepresentation.Reference;
import ru.smirnygatotoshka.caseapp.Database.Database;
import ru.smirnygatotoshka.caseapp.GlobalResources;

public class ChooseDoctorForm extends UIFactory{


    public ChooseDoctorForm(String id_prefix) {
        super(id_prefix);
    }

    @Override
    public Parent create() {
        GridPane pane = new GridPane();
        addConstrains(pane, new int[]{20,20, 60}, new int[]{100});
        pane.setStyle("-fx-background-color: #FFCCCC;");
        TableView<Doctor> doctors = new TableView<>();


        Label label_dep = new Label("Отделение");
        label_dep.setFont(GlobalResources.headerFont);
        GridPane.setMargin(label_dep, new Insets(20,0,0,0));
        GridPane.setValignment(label_dep, VPos.TOP);
        pane.add(label_dep, 0, 0);
        put(label_dep, "label_dep");

        ObservableList<Reference> lookup_items = Database.getReference("spr_Departments");
        ChoiceBox<Reference> department = new ChoiceBox<>(lookup_items);
        department.setValue(lookup_items.get(0));
        GridPane.setValignment(department, VPos.BOTTOM);
        department.setStyle("-fx-font: Serif;" +
                "-fx-font-size: 18px;" +
                "-fx-background-color: #CCCCFF;" +
                "-fx-border-color: #000000;");
        department.setPrefSize(200,150);
        department.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        department.setOnAction(actionEvent -> {
            ObservableList<Doctor> list_doctors = Database.getDoctors(department.getValue());
            doctors.itemsProperty().set(list_doctors);
            doctors.getSelectionModel().select(0);
        });
        GridPane.setFillHeight(department, false);
        GridPane.setMargin(department, new Insets(10,10,20,10));
        put(department, "department");
        pane.add(department, 0, 1);


        doctors.setPrefSize(200,800);
        ObservableList<Doctor> list_doctors = Database.getDoctors(department.getValue());

        TableColumn<Doctor, String> column1 = new TableColumn<>("ФИО");
        column1.setCellValueFactory(cellData ->
                cellData.getValue().sirnameProperty().concat("\n")
                        .concat(cellData.getValue().nameProperty()).concat(" ").
                        concat(cellData.getValue().secondNameProperty()));
        column1.setCellFactory(new Callback<>() {

            @Override
            public TableCell call(TableColumn param) {
                return new TableCell<Doctor, String>() {
                    @Override
                    public void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);
                        if (isEmpty()) {
                            setText("");
                        } else {
                            setFont(GlobalResources.usualFont);
                            setText(item);
                        }
                    }
                };
            }
        });
        column1.setMinWidth(300);
        doctors.getColumns().add(column1);

        TableColumn<Doctor, String> column2 = new TableColumn<>("Специальность");
        column2.setCellValueFactory(new PropertyValueFactory<>("Speciality"));
        column2.setMinWidth(200);
        column2.setCellFactory(new Callback<>() {

            @Override
            public TableCell call(TableColumn param) {
                return new TableCell<Doctor, String>() {
                    @Override
                    public void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);
                        if (isEmpty()) {
                            setText("");
                        } else {
                            setFont(GlobalResources.usualFont);
                            setText(item);
                        }
                    }
                };
            }
        });
        doctors.getColumns().add(column2);

        TableColumn<Doctor, String> column3 = new TableColumn<>("Должность");
        column3.setCellValueFactory(new PropertyValueFactory<>("Position"));
        column3.setMinWidth(200);
        column3.setCellFactory(new Callback<>() {

            @Override
            public TableCell call(TableColumn param) {
                return new TableCell<Doctor, String>() {
                    @Override
                    public void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);
                        if (isEmpty()) {
                            setText("");
                        } else {
                            setFont(GlobalResources.usualFont);
                            setText(item);
                        }
                    }
                };
            }
        });
        doctors.getColumns().add(column3);


        doctors.itemsProperty().set(list_doctors);
        doctors.setStyle("-fx-background-color: #FFFF99;");
        doctors.setFixedCellSize(100);
        GridPane.setMargin(doctors, new Insets(10,10,10,10));
        GridPane.setFillHeight(doctors, false);
        put(doctors, "doctors");
        pane.add(doctors,0,2);

        return pane;
    }

    @Override
    protected void closeForm() {

    }

}
