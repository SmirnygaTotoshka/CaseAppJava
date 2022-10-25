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
import ru.smirnygatotoshka.caseapp.Database.ChangeActions;
import ru.smirnygatotoshka.caseapp.Database.Database;
import ru.smirnygatotoshka.caseapp.GlobalResources;
import ru.smirnygatotoshka.caseapp.Registrator.ChangeForm;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Optional;
import java.util.function.Predicate;
/**
 * Формирование расписания
 * */
public class ScheduleFormFactory extends LookupWithSearch<String, Change> implements DataChanger {

    private static ObservableList<String> LOOKUP_ITEMS = FXCollections.observableArrayList("Дата","Начало смены", "Конец смены");

    private TableView<Change> changeTableView;
    private TableView<Doctor> doctorTableView;
    private DatePicker from, to;

    public ScheduleFormFactory(String id_prefix) {
        super(id_prefix, LOOKUP_ITEMS);
        String query = "SELECT Doctor, Date, StartTime, FinishTime FROM tbl_DoctorChanges;";
        ObservableList<Change> changes = Database.getChanges(query);
        filteredList = new FilteredList<>(changes);

    }

    @Override
    protected String getColumnNameFromDB(String item) {
        if (item.contentEquals("Дата"))
            return "Date";
        else if (item.contentEquals("Начало смены"))
            return "StartTime";
        else if (item.contentEquals("Конец смены"))
            return "FinishTime";
        else
            return "";
    }

    @Override
    protected Predicate<Change> search() {
        return change -> {
            boolean show = true;
            int id;
            try {
                id = Database.getDoctorId(doctorTableView.getSelectionModel().getSelectedItem());
            }
            catch (NullPointerException e){
                return false;
            }
            Date from_date =  Date.from(from.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
            Date to_date =  Date.from(to.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
            show = show && (change.getDoctor() == id && change.getDate().after(from_date) && change.getDate().before(to_date));
            return show;
        };
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

        changeTableView = (TableView<Change>) get("lookupTable");
        changeTableView.setPlaceholder(new Label("Нет зарегистрированных смен."));
        changeTableView.itemsProperty().set(filteredList);

        changeTableView.refresh();

        TableColumn<Change, Date> col1 = (TableColumn<Change, Date>) changeTableView.getColumns().get(0);
        col1.setCellFactory(column -> {
            TableCell<Change, Date> cell = new TableCell<>() {
                private SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");

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
        col1.setCellValueFactory(new PropertyValueFactory<Change, Date>(getColumnNameFromDB(col1.getText())));


       TableColumn<Change, Date> col2 = (TableColumn<Change, Date>) changeTableView.getColumns().get(1);
        col2.setCellFactory(column -> {
            TableCell<Change, Date> cell = new TableCell<>() {
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
        col2.setCellValueFactory(new PropertyValueFactory<Change, Date>(getColumnNameFromDB(col2.getText())));

        TableColumn<Change, Date> col3 = (TableColumn<Change, Date>) changeTableView.getColumns().get(2);
        col3.setCellFactory(column -> {
            TableCell<Change, Date> cell = new TableCell<>() {
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
        col3.setCellValueFactory(new PropertyValueFactory<Change, Date>(getColumnNameFromDB(col3.getText())));



        Button add = (Button) get("Add");
        Button edit = (Button) get("Edit");
        Button delete = (Button) get("Delete");

        GlobalResources.openedStages.addListener((MapChangeListener<String, Stage>) change -> {
            if (change.getKey().contentEquals("ChangeForm")){
                System.out.println(change.wasRemoved());
                add.setDisable(!change.wasRemoved());
                edit.setDisable(!change.wasRemoved());
                delete.setDisable(!change.wasRemoved());

            }
        });


        changeTableView.setRowFactory(
                tableView -> {
                    final TableRow<Change> row = new TableRow<>();
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
    protected Parent createLookup() {
        GridPane pane = new GridPane();
        addConstrains(pane,new int[]{100}, new int[]{10,40,10,40});


        Label lab_from = new Label("От:");
        lab_from.setFont(GlobalResources.usualFont);
        lab_from.setMaxSize(Double.MAX_VALUE,Double.MAX_VALUE);
        pane.add(lab_from, 0, 0);
        put(lab_from, "from_label");

        Label lab_to = new Label("До");
        lab_to.setFont(GlobalResources.usualFont);
        lab_to.setMaxSize(Double.MAX_VALUE,Double.MAX_VALUE);
        pane.add(lab_to, 2, 0);
        put(lab_to, "to_label");

        from = new DatePicker(LocalDate.now());
        from.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        from.valueProperty().addListener((observableValue, localDate, t1) -> {
            filteredList.setPredicate(search());
        });
        from.setDayCellFactory(new Callback<>() {
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
        pane.add(from, 1, 0);
        put(from,"fromPicker");

        to = new DatePicker(LocalDate.now().plusDays(14));
        to.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        to.valueProperty().addListener((observableValue, localDate, t1) -> {
            filteredList.setPredicate(search());
        });
        to.setDayCellFactory(new Callback<>() {
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
        pane.add(to, 3, 0);
        put(to,"toPicker");

        GridPane.setMargin(lab_from,new Insets(10));
        GridPane.setMargin(lab_to,new Insets(10));
        GridPane.setMargin(from,new Insets(10));
        GridPane.setMargin(to,new Insets(10));

        return pane;
    }

    @Override
    protected void refresh() {
        String query = "SELECT Doctor, Date, StartTime, FinishTime FROM tbl_DoctorChanges;";
        ObservableList<Change> changes = Database.getChanges(query);
        filteredList = new FilteredList<>(changes);
        filteredList.setPredicate(search());
        changeTableView.itemsProperty().set(filteredList);
        changeTableView.refresh();
    }

    @Override
    public void addAction(ActionEvent event) {
        Doctor selectedDoctor = doctorTableView.getSelectionModel().getSelectedItem();
        if (selectedDoctor == null){
            GlobalResources.alert(Alert.AlertType.INFORMATION,"Выберите врача.");
        }
        else {
            ChangeForm form = new ChangeForm(null, selectedDoctor);
            form.setOnHiding(new EventHandler<WindowEvent>() {
                @Override
                public void handle(WindowEvent windowEvent) {
                    refresh();
                    System.out.println("Add");
                }
            });
            GlobalResources.openedStages.put("ChangeForm", form);
        }
    }

    @Override
    public void editAction(ActionEvent event) {
        Change selectedChange = changeTableView.getSelectionModel().getSelectedItem();
        Doctor selectedDoctor = doctorTableView.getSelectionModel().getSelectedItem();
        if (selectedChange == null){
            GlobalResources.alert(Alert.AlertType.INFORMATION,"Выберите смену.");
        }
        else if (selectedDoctor == null){
            GlobalResources.alert(Alert.AlertType.INFORMATION,"Выберите врача.");
        }
        else {
            ChangeForm form = new ChangeForm(selectedChange, selectedDoctor);
            form.setOnHiding(new EventHandler<WindowEvent>() {
                @Override
                public void handle(WindowEvent windowEvent) {
                    refresh();
                    System.out.println("Edit");
                }
            });
            GlobalResources.openedStages.put("ChangeForm", form);
        }
    }

    @Override
    public void deleteAction(ActionEvent event) {
        Change selectedChange = changeTableView.getSelectionModel().getSelectedItem();
        Doctor selectedDoctor = doctorTableView.getSelectionModel().getSelectedItem();
        if (selectedChange == null){
            GlobalResources.alert(Alert.AlertType.INFORMATION,"Выберите смену.");
        }
        else if (selectedDoctor == null){
            GlobalResources.alert(Alert.AlertType.INFORMATION,"Выберите врача.");
        }
        else {
            Optional<ButtonType> answer = GlobalResources.alert(Alert.AlertType.CONFIRMATION,"Удалить смену?");
            if (answer.get() == ButtonType.OK){
                ChangeActions.delete(selectedChange);
                refresh();
                System.out.println("Delete");
            }
            else {
                event.consume();
            }

        }
    }

}
