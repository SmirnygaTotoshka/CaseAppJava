package ru.smirnygatotoshka.caseapp.UIFactory;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.geometry.*;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.util.Callback;
import ru.smirnygatotoshka.caseapp.DataRepresentation.Change;
import ru.smirnygatotoshka.caseapp.DataRepresentation.Doctor;
import ru.smirnygatotoshka.caseapp.DataRepresentation.Patient;
import ru.smirnygatotoshka.caseapp.DataRepresentation.Reference;
import ru.smirnygatotoshka.caseapp.Database.Database;
import ru.smirnygatotoshka.caseapp.GlobalResources;

import java.time.LocalDate;
import java.util.function.Predicate;

public class ScheduleFormFactory extends LookupWithSearch<String, Change> implements DataChanger {

    private static ObservableList<String> LOOKUP_ITEMS = FXCollections.observableArrayList("Дата","Начало смены", "Конец смены");

    public ScheduleFormFactory(String id_prefix) {
        super(id_prefix, LOOKUP_ITEMS);
        /*String query = "SELECT Sirname, tbl_Patients.Name as Name, SecondName, spr_Sex.NAME as Sex, Birthday, " +
                "spr_Priviledge.NAME as Priviledge, spr_Employment.NAME as Employment," +
                " Workplace, tbl_Passports.Number as Passport,Snils, tbl_Polices.Number as Police, " +
                "spr_FamilyStatus.NAME as FamilyStatus, Telephone FROM tbl_Patients " +
                "INNER JOIN spr_Sex ON spr_Sex.ID = tbl_Patients.Sex " +
                "INNER JOIN spr_Priviledge ON spr_Priviledge.ID = tbl_Patients.Priviledge " +
                "INNER JOIN spr_Employment ON spr_Employment.ID = tbl_Patients.Employment " +
                "INNER JOIN tbl_Passports ON tbl_Passports.ID = tbl_Patients.Passport " +
                "INNER JOIN tbl_Polices ON tbl_Polices.ID = tbl_Patients.Police " +
                "INNER JOIN spr_FamilyStatus ON spr_FamilyStatus.ID = tbl_Patients.FamilyStatus;";
        // patients = Database.getPatients("SELECT * FROM tbl_Patients");
        ObservableList<Change> changes = Database.getChanges(query);
        this.filteredList = new FilteredList<>(changes);*/
    }

    @Override
    protected String getColumnNameFromDB(String item) {
        return "";
    }

    @Override
    protected Predicate<Change> search() {
        return null;
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

/*    private GridPane createControls() {
        GridPane pane = new GridPane();

        pane.getColumnConstraints().add(createColumn(100));
        for (int i = 0;i < 5; i++){
            pane.getRowConstraints().add(createRow(20));
        }

//TODO timepicker
        DatePicker start = new DatePicker();
        DatePicker finish = new DatePicker();


        pane.add(start,0,0);
        put(start, "start");

        pane.add(finish,0,1);
        put(finish, "Add");
        Button add = new Button("Добавить");
        add.setFont(GlobalResources.usualFont);
        add.setStyle("-fx-font: Serif;" +
                "-fx-font-size: 18px;" +
                "-fx-background-color: #CCCCFF;" +
                "-fx-border-color: #000000;");
        add.setOnAction(event -> {
            addAction(event);
        });
        add.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        pane.add(add,0,2);
        put(add, "Add");


        Button edit = new Button("Редактировать");
        edit.setStyle("-fx-font: Serif;" +
                "-fx-font-size: 18px;" +
                "-fx-background-color: #CCCCFF;" +
                "-fx-border-color: #000000;");
        edit.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        edit.setOnAction(event -> {
            editAction(event);
        });
        pane.add(edit,0,3);
        put(edit, "Edit");

        Button delete = new Button("Удалить");
        delete.setStyle("-fx-font: Serif;" +
                "-fx-font-size: 18px;" +
                "-fx-background-color: #CCCCFF;" +
                "-fx-border-color: #000000;");
        delete.setOnAction(event -> {
            deleteAction(event);
        });
        delete.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        pane.add(delete,0,4);
        put(delete, "Delete");

        return pane;
    }

    private GridPane createChangesLookup() {
        GridPane pane = new GridPane();

        ColumnConstraints col = createColumn(100);
        RowConstraints row1 = createRow(20);
        RowConstraints row2 = createRow(20);
        RowConstraints row3 = createRow(60);


        pane.getColumnConstraints().add(col);
        pane.getRowConstraints().add(row1);
        pane.getRowConstraints().add(row2);
        pane.getRowConstraints().add(row3);



        Label label_change = new Label("Смены");
        label_change.setFont(GlobalResources.headerFont);
        GridPane.setMargin(label_change, new Insets(20,0,0,0));
        GridPane.setValignment(label_change, VPos.TOP);
        GridPane.setHalignment(label_change, HPos.CENTER);
        pane.add(label_change, 0, 0);
        put(label_change, "label_dep");

        TextField search_change = new TextField();
        search_change.setFont(GlobalResources.usualFont);
        GridPane.setMargin(search_change, new Insets(0,0,10,0));
        GridPane.setValignment(search_change, VPos.TOP);
        GridPane.setHalignment(search_change, HPos.CENTER);
        pane.add(search_change, 0, 1);
        put(search_change, "search_change");


        TableView<Change> doctors_changes = new TableView<>();
        doctors_changes.setPrefSize(200,800);
        //ObservableList<Doctor> list_doctors = Database.getDoctors(department.getValue());
        TableColumn<Change, String> date_column = new TableColumn<>("Дата");
        /*column1.setCellValueFactory(cellData ->
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
        });*//*
        date_column.setMinWidth(150);
        doctors_changes.getColumns().add(date_column);

        TableColumn<Change, String> start_column = new TableColumn<>("Начало смены");
        *//*column2.setCellValueFactory(new PropertyValueFactory<>(getColumnNameFromDB(column2.getText())));

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
        });*//*
        start_column.setMinWidth(150);
        doctors_changes.getColumns().add(start_column);

        TableColumn<Change, String> finish_column = new TableColumn<>("Конец смены");
        finish_column.setCellValueFactory(new PropertyValueFactory<>(getColumnNameFromDB(finish_column.getText())));
        finish_column.setMinWidth(150);
       *//* column3.setCellFactory(new Callback<>() {

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
        });*//*
        doctors_changes.getColumns().add(finish_column);


        //doctors_changes.itemsProperty().set(list_doctors);
        doctors_changes.setStyle("-fx-background-color: #FFFF99;");
        doctors_changes.setFixedCellSize(100);
        GridPane.setMargin(doctors_changes, new Insets(10,10,10,10));
        GridPane.setFillHeight(doctors_changes, false);
        put(doctors_changes, "doctors_changes");
        pane.add(doctors_changes,0,2);
        return pane;
    }*/

    @Override
    public void addAction(ActionEvent event) {

    }

    @Override
    public void editAction(ActionEvent event) {

    }

    @Override
    public void deleteAction(ActionEvent event) {

    }

  /*  private GridPane createDoctorLookup() {
        GridPane pane = new GridPane();

        ColumnConstraints col = createColumn(100);
        RowConstraints row1 = createRow(30);
        RowConstraints row2 = createRow(70);

        pane.getColumnConstraints().add(col);
        pane.getRowConstraints().add(row1);
        pane.getRowConstraints().add(row2);


        ObservableList<Reference> items = Database.getReference("spr_Departments");
        ChoiceBox<Reference> department = new ChoiceBox<>(items);
        TableView<Doctor> doctors = new TableView<>();
        department.setValue(items.get(0));
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
        doctors.getColumns().add(column1);

        TableColumn<Doctor, String> column2 = new TableColumn<>("Специальность");
        column2.setCellValueFactory(new PropertyValueFactory<>(getColumnNameFromDB(column2.getText())));
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
        column3.setCellValueFactory(new PropertyValueFactory<>(getColumnNameFromDB(column3.getText())));
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
        pane.add(doctors,0,1,1,5);

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
    }*/

}
