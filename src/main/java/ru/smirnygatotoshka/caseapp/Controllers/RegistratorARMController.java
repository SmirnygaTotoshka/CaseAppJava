package ru.smirnygatotoshka.caseapp.Controllers;

import javafx.collections.FXCollections;
import javafx.collections.MapChangeListener;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import ru.smirnygatotoshka.caseapp.DataRepresentation.Patient;
import ru.smirnygatotoshka.caseapp.Database.Database;
import ru.smirnygatotoshka.caseapp.GlobalResources;
import ru.smirnygatotoshka.caseapp.PatientForm;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

public class RegistratorARMController implements Initializable {

    private ObservableList<Patient> patients = FXCollections.emptyObservableList();
    private FilteredList<Patient> filteredList;

    @FXML
    private TableView<Patient> table_patients;

    @FXML
    private ChoiceBox<String> select_seek;

    @FXML
    private TextField seeking_query;

    @FXML
    private Button add_patient,edit_patient,delete_patient;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
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
        patients = Database.getPatients(query);
        filteredList = new FilteredList<>(patients);
        filteredList.setPredicate(p -> true);
        TableColumn<Patient, String> sirnameCol = new TableColumn("Фамилия");
        sirnameCol.setMinWidth(300);
        sirnameCol.setMinWidth(Control.USE_COMPUTED_SIZE);
        sirnameCol.setCellValueFactory(new PropertyValueFactory<Patient, String>("sirname"));

        TableColumn<Patient, String> nameCol = new TableColumn("Имя");
        nameCol.setMinWidth(300);
        nameCol.setMinWidth(Control.USE_COMPUTED_SIZE);
        nameCol.setCellValueFactory(new PropertyValueFactory<Patient, String>("name"));

        TableColumn<Patient, String> secondNameCol = new TableColumn("Отчество");
        secondNameCol.setMinWidth(300);
        secondNameCol.setMinWidth(Control.USE_COMPUTED_SIZE);
        secondNameCol.setCellValueFactory(new PropertyValueFactory<Patient, String>("secondName"));

        TableColumn<Patient, String> sexCol = new TableColumn("Пол");
        sexCol.setMinWidth(100);
        sexCol.setMinWidth(Control.USE_COMPUTED_SIZE);
        sexCol.setCellValueFactory(new PropertyValueFactory<Patient, String>("sex"));

        TableColumn<Patient, Date> dobCol = new TableColumn("Дата рождения");
        dobCol.setMinWidth(100);
        dobCol.setMinWidth(Control.USE_COMPUTED_SIZE);
        dobCol.setCellFactory(column -> {
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
        dobCol.setCellValueFactory(new PropertyValueFactory<Patient, Date>("dob"));

        TableColumn<Patient, String> priviledgeCol = new TableColumn("Льгота");
        priviledgeCol.setMinWidth(300);
        priviledgeCol.setMinWidth(Control.USE_COMPUTED_SIZE);
        priviledgeCol.setCellValueFactory(new PropertyValueFactory<Patient, String>("priviledge"));

        TableColumn<Patient, String> employmentCol = new TableColumn("Соц.статус");
        employmentCol.setMinWidth(300);
        employmentCol.setMinWidth(Control.USE_COMPUTED_SIZE);
        employmentCol.setCellValueFactory(new PropertyValueFactory<Patient, String>("employment"));

        TableColumn<Patient, String> workplaceCol = new TableColumn("Место работы");
        workplaceCol.setMinWidth(300);
        workplaceCol.setMinWidth(Control.USE_COMPUTED_SIZE);
        workplaceCol.setCellValueFactory(new PropertyValueFactory<Patient, String>("workplace"));

        TableColumn<Patient, String> passportCol = new TableColumn("Паспорт");
        passportCol.setMinWidth(300);
        passportCol.setMinWidth(Control.USE_COMPUTED_SIZE);
        passportCol.setCellValueFactory(new PropertyValueFactory<Patient, String>("passport"));

        TableColumn<Patient, String> snilsCol = new TableColumn("СНИЛС");
        snilsCol.setMinWidth(300);
        snilsCol.setMinWidth(Control.USE_COMPUTED_SIZE);
        snilsCol.setCellValueFactory(new PropertyValueFactory<Patient, String>("snils"));

        TableColumn<Patient, String> policeCol = new TableColumn("Полис");
        policeCol.setMinWidth(300);
        policeCol.setMinWidth(Control.USE_COMPUTED_SIZE);
        policeCol.setCellValueFactory(new PropertyValueFactory<Patient, String>("police"));

        TableColumn<Patient, String> familyStatusCol = new TableColumn("Семейное положение");
        familyStatusCol.setMinWidth(300);
        familyStatusCol.setMinWidth(Control.USE_COMPUTED_SIZE);
        familyStatusCol.setCellValueFactory(new PropertyValueFactory<Patient, String>("familyStatus"));

        TableColumn<Patient, String> telephoneCol = new TableColumn("Телефон");
        telephoneCol.setMinWidth(300);
        telephoneCol.setMinWidth(Control.USE_COMPUTED_SIZE);
        telephoneCol.setCellValueFactory(new PropertyValueFactory<Patient, String>("telephone"));

        table_patients.itemsProperty().set(filteredList);
        //table_patients.setItems(patients);
        table_patients.getColumns().addAll(sirnameCol,nameCol,secondNameCol,sexCol,dobCol,priviledgeCol,employmentCol,
                workplaceCol,passportCol,snilsCol,policeCol,familyStatusCol,telephoneCol);

        select_seek.setItems(FXCollections.observableArrayList("Фамилия","Имя","Отчество","Пол","Дата рождения",
                                                                "Паспорт","СНИЛС","Полис","Телефон"));
        select_seek.setValue("Фамилия");

        seeking_query.textProperty().addListener((observable, oldV, newV) -> {
            filteredList.setPredicate((data) ->
            {
                boolean show = true;
                if (!seeking_query.getText().isEmpty()){
                    String column = null;
                    if (select_seek.getValue() == "Фамилия"){
                        column = data.getSirname();
                    }
                    if (select_seek.getValue() == "Имя"){
                        column = data.getName();
                    }
                    if (select_seek.getValue() == "Отчество"){
                        column = data.getSecondName();
                    }
                    if (select_seek.getValue() == "Пол"){
                        column = data.getSex();
                    }
                    if (select_seek.getValue() == "Дата рождения"){
                        column = data.getDob().toString();
                    }
                    if (select_seek.getValue() == "Паспорт"){
                        column = data.getPassport();
                    }
                    if (select_seek.getValue() == "СНИЛС"){
                        column = data.getSnils();
                    }
                    if (select_seek.getValue() == "Полис"){
                        column = data.getPolice();
                    }
                    if (select_seek.getValue() == "Телефон"){
                        column = data.getTelephone();
                    }
                    show = show && (column.contains(seeking_query.getText()));
                }
                return show;
            });
        });
        GlobalResources.openedStages.addListener((MapChangeListener<String, Stage>) change -> {
            if (change.getKey().contentEquals("PatientForm")){
                System.out.println(change.wasRemoved());
                add_patient.setDisable(!change.wasRemoved());
                edit_patient.setDisable(!change.wasRemoved());
                delete_patient.setDisable(!change.wasRemoved());

            }
        });
    }

    @FXML
    protected void onAddPatient(ActionEvent event){
        PatientForm form = new PatientForm(null);
        //form.setPatient(null);
        GlobalResources.openedStages.put("PatientForm", form);
    }

    @FXML
    protected void onEditPatient(ActionEvent event){
        Patient selectedPatient = table_patients.getSelectionModel().getSelectedItem();
        if (selectedPatient == null){
            GlobalResources.alert(Alert.AlertType.INFORMATION,"Выберите пациента.");
        }
        else {
            PatientForm form = new PatientForm(selectedPatient);
            //form.setPatient(selectedPatient);
            GlobalResources.openedStages.put("PatientForm", form);
        }
    }

    @FXML
    protected void onDeletePatient(ActionEvent event){
        /*Optional<ButtonType> answer = GlobalResources.alert(Alert.AlertType.CONFIRMATION, "Вы действительно хотите открепить этого пациента?");
        if (answer.get() == ButtonType.OK){

        }*///TODO
    }


}
