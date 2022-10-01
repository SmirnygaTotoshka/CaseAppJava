package ru.smirnygatotoshka.caseapp.UIFactory;

import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
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

public class ScheduleFormFactory extends LookupFactory<Reference, Doctor>{


    public ScheduleFormFactory(String id_prefix, ObservableList<Reference> lookup_items) {
        super(id_prefix, lookup_items);
    }

    @Override
    protected String getColumnNameFromDB(String item) {
        if (item.contentEquals("Должность")){
            return "Position";
        }
        else if (item.contentEquals("Специальность")){
            return "Speciality";
        }
        else return "";
    }

    @Override
    public Parent create() {
        GridPane parent = new GridPane();
        parent.setStyle("-fx-background-color: #FFCCCC;");

        RowConstraints[] rows = new RowConstraints[5];
        ColumnConstraints[] columns = new ColumnConstraints[4];
        int[] percentage = new int[]{35,5,30,30};
        
        
        for (int i = 0; i < rows.length; i++) {
            rows[i] = createRow(100 / rows.length);
        }
        for (int i = 0; i < columns.length; i++) {
            columns[i] = createColumn(percentage[i]);
        }
        
        GridPane doctors = createDoctorLookup();
        
        parent.getRowConstraints().addAll(rows);
        parent.getColumnConstraints().addAll(columns);

        parent.add(doctors,0,0, 1, 5);

        //parent.setHgap(15);
        //parent.setVgap(5);
        parent.setAlignment(Pos.CENTER);

        return parent;
    }

    @Override
    protected void addAction(ActionEvent event) {

    }

    @Override
    protected void editAction(ActionEvent event) {

    }

    @Override
    protected void deleteAction(ActionEvent event) {

    }

    private GridPane createDoctorLookup() {
        GridPane pane = new GridPane();

        ColumnConstraints col = createColumn(100);
        RowConstraints row1 = createRow(30);
        RowConstraints row2 = createRow(70);

        pane.getColumnConstraints().add(col);
        pane.getRowConstraints().add(row1);
        pane.getRowConstraints().add(row2);



        ChoiceBox<Reference> department = new ChoiceBox<>(lookup_items);
        TableView<Doctor> doctors = new TableView<>();
        department.setValue(lookup_items.get(0));
        GridPane.setValignment(department,VPos.BOTTOM);
        department.setStyle("-fx-font: Serif;" +
                "-fx-font-size: 18px;" +
                "-fx-background-color: #CCCCFF;" +
                "-fx-border-color: #000000;");
        department.setPrefSize(200,150);
        department.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        department.setOnAction(actionEvent -> {
            ObservableList<Doctor> list_doctors = Database.getDoctors(department.getValue());
            doctors.itemsProperty().set(list_doctors);
        });
        GridPane.setFillHeight(department, false);
        GridPane.setMargin(department, new Insets(10,10,20,10));
        put(department, "department");
        pane.add(department, 0, 0);

        Label label_dep = new Label("Отделение");
        label_dep.setFont(GlobalResources.headerFont);
        GridPane.setMargin(label_dep, new Insets(20,0,0,0));
        GridPane.setValignment(label_dep, VPos.TOP);
        pane.add(label_dep, 0, 0);
        put(label_dep, "label_dep");

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
        column1.setMinWidth(Control.USE_COMPUTED_SIZE);
        doctors.getColumns().add(column1);

        TableColumn<Doctor, String> column2 = new TableColumn<>("Специальность");
        column2.setCellValueFactory(new PropertyValueFactory<>(getColumnNameFromDB(column2.getText())));
        column2.setMinWidth(200);
        column2.setMinWidth(Control.USE_COMPUTED_SIZE);
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
        column3.setCellValueFactory(new PropertyValueFactory<>(getColumnNameFromDB(column3.getText())));
        column3.setMinWidth(200);
        column3.setMinWidth(Control.USE_COMPUTED_SIZE);
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
        pane.add(doctors,0,1,1,5);

        pane.setGridLinesVisible(true);
        return pane;
    }

    private RowConstraints createRow(int percentage){
        RowConstraints row = new RowConstraints();
        row.setVgrow(Priority.ALWAYS);
        row.setValignment(VPos.CENTER);
        row.setPercentHeight(percentage);
        return row;
    }
    
    private ColumnConstraints createColumn(int percentage){
        ColumnConstraints column = new ColumnConstraints();
        column.setHgrow(Priority.ALWAYS);
        column.setHalignment(HPos.CENTER);
        column.setPercentWidth(percentage);
        return column;
    }

}
