package ru.smirnygatotoshka.caseapp.UIFactory;

import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.MapChangeListener;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.util.Callback;
import ru.smirnygatotoshka.caseapp.DataRepresentation.Change;
import ru.smirnygatotoshka.caseapp.DataRepresentation.Doctor;
import ru.smirnygatotoshka.caseapp.DataRepresentation.Patient;
import ru.smirnygatotoshka.caseapp.DataRepresentation.ScheduleItem;
import ru.smirnygatotoshka.caseapp.Database.ChangeActions;
import ru.smirnygatotoshka.caseapp.Database.Database;
import ru.smirnygatotoshka.caseapp.Database.PatientsActions;
import ru.smirnygatotoshka.caseapp.Database.ScheduleActions;
import ru.smirnygatotoshka.caseapp.GlobalResources;
import ru.smirnygatotoshka.caseapp.Registrator.ChangeForm;
import ru.smirnygatotoshka.caseapp.Registrator.RegisterPatientForm;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Optional;
import java.util.function.Predicate;
/**
 * Запись на прием
 * */
public class RegisterToDoctorFactory extends LookupWithSearch<String,ScheduleItem> implements DataChanger {

    private static ObservableList<String> LOOKUP_ITEMS = FXCollections.observableArrayList("Время","ФИО пациента");

    private TableView<ScheduleItem> scheduleItemTableView;
    private TableView<Doctor> doctorTableView;
    private DatePicker datePicker;


    public RegisterToDoctorFactory(String id_prefix) {
        super(id_prefix, LOOKUP_ITEMS);
        String query = "SELECT * FROM tbl_Schedule;";
        ObservableList<ScheduleItem> items = Database.getSchedule(query);
        filteredList = new FilteredList<>(items);
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

        doctorTableView = (TableView<Doctor>) get("doctors");
        doctorTableView.getSelectionModel().selectedItemProperty().addListener((observableValue, localDate, t1) -> {
            filteredList.setPredicate(search());
        });
        doctorTableView.getSelectionModel().select(0);

        scheduleItemTableView = (TableView<ScheduleItem>) get("lookupTable");//TODO - refresh after add/edit/delete change
        scheduleItemTableView.setPlaceholder(new Label("Нерабочий день"));
        scheduleItemTableView.itemsProperty().set(filteredList);

        scheduleItemTableView.refresh();
        TableColumn<ScheduleItem, Date> col = (TableColumn<ScheduleItem, Date>) scheduleItemTableView.getColumns().get(0);
        col.setCellFactory(column -> {
            TableCell<ScheduleItem, Date> cell = new TableCell<>() {
                private SimpleDateFormat format = new SimpleDateFormat("HH:mm");

                @Override
                protected void updateItem(Date item, boolean empty) {
                    super.updateItem(item, empty);
                    if(empty) {
                        setText(null);
                    }
                    else {
                        setText(format.format(item));
                    }
                }
            };

            return cell;
        });
        col.setCellValueFactory(new PropertyValueFactory<ScheduleItem, Date>("Time"));

        TableColumn<ScheduleItem, String> col1 = (TableColumn<ScheduleItem, String>) scheduleItemTableView.getColumns().get(1);
        col1.setCellValueFactory(cellData -> {
            Patient patient = PatientsActions.get(cellData.getValue().getPatient());
            return patient.sirnameProperty().concat("\n")
                    .concat(patient.nameProperty()).concat(" ").
                    concat(patient.secondNameProperty());
        });



        Button add = (Button) get("Add");
        Button edit = (Button) get("Edit");
        Button delete = (Button) get("Delete");

        GlobalResources.openedStages.addListener((MapChangeListener<String, Stage>) change -> {
            if (change.getKey().contentEquals("ChoosePatientForm")){
                System.out.println(change.wasRemoved());
                add.setDisable(!change.wasRemoved());
                edit.setDisable(!change.wasRemoved());
                delete.setDisable(!change.wasRemoved());

            }
        });

        scheduleItemTableView.setRowFactory(
                tableView -> {
                    final TableRow<ScheduleItem> row = new TableRow<>();
                    final ContextMenu rowMenu = new ContextMenu();
                    MenuItem refreshItem = new MenuItem("Обновить");
                    refreshItem.setOnAction(event -> refresh());
                    MenuItem editItem = new MenuItem("Редактировать");
                    editItem.setOnAction(event -> edit.fire());
                    MenuItem removeItem = new MenuItem("Удалить");
                    removeItem.setOnAction(event -> delete.fire());

                    rowMenu.getItems().addAll(refreshItem, editItem, removeItem);

                    // only display context menu for non-empty rows:
                    row.contextMenuProperty().bind(
                            Bindings.when(row.emptyProperty())
                                    .then(rowMenu)
                                    .otherwise(rowMenu));
                    return row;
                });

        return parent;
    }

    @Override
    protected void closeForm() {

    }

    @Override
    protected String getColumnNameFromDB(String item) {
        return null;
    }

    @Override
    protected Predicate<ScheduleItem> search() {
        return item -> {
            boolean show = true;
            int id;
            Date date;
            try {
                id = Database.getDoctorId(doctorTableView.getSelectionModel().getSelectedItem());
            }
            catch (NullPointerException e){
                return false;
            }
            java.sql.Date from_date = new java.sql.Date(Date.from(datePicker.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()).getTime());
            show = show && ChangeActions.getDoctorScheduleInDate(from_date, id).contains(item.getChange());
            return show;
        };
    }

    @Override
    protected Parent createLookup() {
        GridPane pane = new GridPane();
        addConstrains(pane, new int[]{50,50}, new int[]{100});
        datePicker = new DatePicker(LocalDate.now());
        datePicker.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        datePicker.valueProperty().addListener((observableValue, localDate, t1) -> {
            filteredList.setPredicate(search());
        });
        datePicker.setDayCellFactory(new Callback<>() {
            @Override
            public DateCell call(DatePicker datePicker) {
                return new DateCell(){
                    LocalDate minDate = LocalDate.now(), maxDate = LocalDate.now().plusYears(1);
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
        pane.add(datePicker, 0, 0);

        Button refresh = new Button("Обновить");
        refresh.setStyle("-fx-font: Serif;" +
                "-fx-font-size: 18px;" +
                "-fx-background-color: #CCCCFF;" +
                "-fx-border-color: #000000;");
        refresh.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        refresh.setOnAction(event -> {
            refresh();
        });
        put(refresh, "Refresh");
        pane.add(refresh, 0, 1);

        GridPane.setMargin(datePicker, new Insets(10));
        GridPane.setMargin(refresh, new Insets(10));

        return pane;
    }

    @Override
    protected void refresh() {
        String query = "SELECT * FROM tbl_Schedule;";
        ObservableList<ScheduleItem> items = Database.getSchedule(query);
        filteredList = new FilteredList<>(items);
        filteredList.setPredicate(search());
        scheduleItemTableView.itemsProperty().set(filteredList);
        scheduleItemTableView.refresh();
    }

    @Override
    public void addAction(ActionEvent event) {
       Doctor selectedDoctor = doctorTableView.getSelectionModel().getSelectedItem();
       ScheduleItem scheduleItem = scheduleItemTableView.getSelectionModel().getSelectedItem();
        if (selectedDoctor == null){
            GlobalResources.alert(Alert.AlertType.INFORMATION,"Выберите врача.");
        }
        else if (scheduleItem == null){
            GlobalResources.alert(Alert.AlertType.INFORMATION,"Выберите время.");
        }
        else {
            RegisterPatientForm form = new RegisterPatientForm(scheduleItem, selectedDoctor);
            form.setOnHiding(new EventHandler<WindowEvent>() {
                @Override
                public void handle(WindowEvent windowEvent) {
                    refresh();
                    System.out.println("Add");
                }
            });
            GlobalResources.openedStages.put("ChoosePatientForm", form);
        }
    }

    @Override
    public void editAction(ActionEvent event) {
       addAction(event);
    }

    @Override
    public void deleteAction(ActionEvent event) {
        Doctor selectedDoctor = doctorTableView.getSelectionModel().getSelectedItem();
        ScheduleItem scheduleItem = scheduleItemTableView.getSelectionModel().getSelectedItem();
        if (selectedDoctor == null){
            GlobalResources.alert(Alert.AlertType.INFORMATION,"Выберите врача.");
        }
        else if (scheduleItem == null){
            GlobalResources.alert(Alert.AlertType.INFORMATION,"Выберите время.");
        }
        else if (scheduleItem.getPatient() == -100){
            GlobalResources.alert(Alert.AlertType.INFORMATION, "Уже отсутствует запись на данное время.");
        }
        else {
            Optional<ButtonType> answer = GlobalResources.alert(Alert.AlertType.CONFIRMATION,"Отменить приём?");
            if (answer.get() == ButtonType.OK){
                ScheduleActions.delete(scheduleItem);
                refresh();
                System.out.println("Delete");
            }
            else {
                event.consume();
            }

        }
    }
}
