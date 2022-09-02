package ru.smirnygatotoshka.caseapp.UIFactory;

import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.util.Callback;
import ru.smirnygatotoshka.caseapp.DataRepresentation.Patient;
import ru.smirnygatotoshka.caseapp.Database.Database;

import java.text.SimpleDateFormat;
import java.util.Date;

//TODO
public class PatientLookupFormFactory extends LookupFactory<String, Patient> {

    private FilteredList<Patient> patients;

    private static final ObservableList<String> LOOKUP_HEADERS = FXCollections.observableArrayList("Фамилия","Имя","Отчество",
            "Пол","Дата рождения","Льгота","Соц.статус","Место работы","Паспорт","СНИЛС","Полис","Семейное положение","Телефон");

    public PatientLookupFormFactory(String id_prefix) {
        super(id_prefix, LOOKUP_HEADERS);
        String query = "SELECT Sirname, tbl_Patients.Name as Name, SecondName, spr_Sex.NAME as Sex, Birthday, " +
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
        ObservableList<Patient> list_patients = Database.getPatients(query);
        patients = new FilteredList<>(list_patients);

    }

    @Override
    public Parent create() {
        GridPane parent = (GridPane) super.create();

        TableView<Patient> tablePatients = (TableView<Patient>) get("lookupTable");
        TextField seeking_query = (TextField) get("lookup");
        ChoiceBox<String> select_seek = (ChoiceBox<String>) get("lookupChoices");

        Button edit = (Button) get("Edit");
        Button delete = (Button) get("Delete");

        for (int i = 0; i < tablePatients.getColumns().size(); i++) {
            if (tablePatients.getColumns().get(i).getText().contentEquals("")){
                TableColumn<Patient, Date> col = (TableColumn<Patient, Date>) tablePatients.getColumns().get(i);
                col.setCellFactory(column -> {
                    TableCell<Patient, Date> cell = new TableCell<>() {
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
                col.setCellValueFactory(new PropertyValueFactory<Patient, Date>(getColumnNameFromDB(col.getText())));
            }
            else{
                TableColumn<Patient, String> col = (TableColumn<Patient, String>) tablePatients.getColumns().get(i);
                col.setCellValueFactory(new PropertyValueFactory<>(getColumnNameFromDB(col.getText())));
            }
        }

        tablePatients.setRowFactory(
                tableView -> {
                    final TableRow<Patient> row = new TableRow<>();
                    final ContextMenu rowMenu = new ContextMenu();
                    MenuItem editItem = new MenuItem("Редактировать");
                    editItem.setOnAction(event -> edit.fire());
                    MenuItem removeItem = new MenuItem("Удалить");
                    removeItem.setOnAction(event -> delete.fire());
                    rowMenu.getItems().addAll(editItem, removeItem);

                    // only display context menu for non-empty rows:
                    row.contextMenuProperty().bind(
                            Bindings.when(row.emptyProperty())
                                    .then((ContextMenu) null)
                                    .otherwise(rowMenu));
                    return row;
                });

        seeking_query.textProperty().addListener((observable, oldV, newV) -> {
            patients.setPredicate((data) ->
            {
                boolean show = true;
                if (!seeking_query.getText().isEmpty()){
                    String column = null;
                    if (select_seek.getValue().contentEquals("Фамилия")){
                        column = data.getSirname();
                    }
                    if (select_seek.getValue().contentEquals("Имя")){
                        column = data.getName();
                    }
                    if (select_seek.getValue().contentEquals("Отчество")){
                        column = data.getSecondName();
                    }
                    if (select_seek.getValue().contentEquals("Пол")){
                        column = data.getSex();
                    }
                    if (select_seek.getValue().contentEquals("Дата рождения")){
                        column = data.getDob().toString();
                    }
                    if (select_seek.getValue().contentEquals("Паспорт")){
                        column = data.getPassport();
                    }
                    if (select_seek.getValue().contentEquals("СНИЛС")){
                        column = data.getSnils();
                    }
                    if (select_seek.getValue().contentEquals("Полис")){
                        column = data.getPolice();
                    }
                    if (select_seek.getValue().contentEquals("Телефон")){
                        column = data.getTelephone();
                    }
                    show = show && (column.contains(seeking_query.getText()));
                }
                return show;
            });
        });
        tablePatients.itemsProperty().set(patients);

        return parent;
    }

    @Override
    protected String getColumnNameFromDB(String item){
        if (item.contentEquals("Фамилия")){
            return "sirname";
        }
        else if (item.contentEquals("Имя")){
            return "name";
        }
        else if (item.contentEquals("Отчество")){
            return "secondName";
        }
        else if (item.contentEquals("Пол")){
            return "sex";
        }
        else if (item.contentEquals("Дата рождения")){
            return "dob";
        }
        else if (item.contentEquals("Льгота")){
            return "priviledge";
        }
        else if (item.contentEquals("Соц.статус")){
            return "employment";
        }
        else if (item.contentEquals("Место работы")){
            return "workplace";
        }
        else if (item.contentEquals("Паспорт")){
            return "passport";
        }
        else if (item.contentEquals("СНИЛС")){
            return "snils";
        }
        else if (item.contentEquals("Полис")){
            return "police";
        }
        else if (item.contentEquals("Семейное положение")){
            return "familyStatus";
        }
        else if (item.contentEquals("Телефон")){
            return "telephone";
        }
        else
            return null;

    }
}
