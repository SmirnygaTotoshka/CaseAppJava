package ru.smirnygatotoshka.caseapp.UIFactory;

import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.*;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.util.Callback;
import ru.smirnygatotoshka.caseapp.DataRepresentation.Change;
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

        ColumnConstraints[] columns = new ColumnConstraints[4];
        int[] percentage = new int[]{35,5,30,30};
        for (int i = 0; i < columns.length; i++) {
            columns[i] = createColumn(percentage[i]);
        }
        
        GridPane doctors = createDoctorLookup();
        GridPane changes = createChangesLookup();
        GridPane controls = createControls();

        
        parent.getRowConstraints().addAll(createRow(100));
        parent.getColumnConstraints().addAll(columns);

        parent.add(doctors,0,0);

        Separator separator = new Separator(Orientation.VERTICAL);
        parent.add(separator,1,0);

        parent.add(changes,2,0);
        parent.add(controls,3,0);
        //parent.setHgap(15);
        //parent.setVgap(5);
        parent.setAlignment(Pos.CENTER);

        return parent;
    }

    private GridPane createControls() {
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
        });*/
        date_column.setMinWidth(150);
        doctors_changes.getColumns().add(date_column);

        TableColumn<Change, String> start_column = new TableColumn<>("Начало смены");
        /*column2.setCellValueFactory(new PropertyValueFactory<>(getColumnNameFromDB(column2.getText())));

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
        });*/
        start_column.setMinWidth(150);
        doctors_changes.getColumns().add(start_column);

        TableColumn<Change, String> finish_column = new TableColumn<>("Конец смены");
        finish_column.setCellValueFactory(new PropertyValueFactory<>(getColumnNameFromDB(finish_column.getText())));
        finish_column.setMinWidth(150);
       /* column3.setCellFactory(new Callback<>() {

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
        });*/
        doctors_changes.getColumns().add(finish_column);


        //doctors_changes.itemsProperty().set(list_doctors);
        doctors_changes.setStyle("-fx-background-color: #FFFF99;");
        doctors_changes.setFixedCellSize(100);
        GridPane.setMargin(doctors_changes, new Insets(10,10,10,10));
        GridPane.setFillHeight(doctors_changes, false);
        put(doctors_changes, "doctors_changes");
        pane.add(doctors_changes,0,2);
        return pane;
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
    }

}
