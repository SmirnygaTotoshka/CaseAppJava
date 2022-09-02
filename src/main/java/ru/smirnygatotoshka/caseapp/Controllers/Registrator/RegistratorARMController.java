package ru.smirnygatotoshka.caseapp.Controllers.Registrator;

import javafx.collections.FXCollections;
import javafx.collections.MapChangeListener;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import ru.smirnygatotoshka.caseapp.DataRepresentation.Patient;
import ru.smirnygatotoshka.caseapp.GlobalResources;
import ru.smirnygatotoshka.caseapp.Registrator.PatientForm;
import ru.smirnygatotoshka.caseapp.UIFactory.PatientLookupFormFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class RegistratorARMController implements Initializable {

    private ObservableList<Patient> patients = FXCollections.emptyObservableList();
    private FilteredList<Patient> filteredList;

    @FXML
    private ComboBox<String> schedule_mode;

    @FXML
    private TableView<Patient> table_patients;

    @FXML
    private ChoiceBox<String> select_seek;

    @FXML
    private TextField seeking_query;

    @FXML
    private Button add_patient,edit_patient,delete_patient;

    @FXML
    private VBox schedule_container;

    @FXML
    private GridPane patients_lookup_container;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        PatientLookupFormFactory patientFactory = new PatientLookupFormFactory("mainRegistrator");
        GridPane patientLookupLayout = (GridPane) patientFactory.create();

        //HBox.setHgrow(patients_lookup_container, Priority.ALWAYS);
        patients_lookup_container.getChildren().add(patientLookupLayout);

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

   /* @FXML
    protected void onScenarioSelect(ActionEvent event){
        String root_path = "";
        if (schedule_mode.getValue().contentEquals("Записать на приём")){
            root_path = "regis"
        }
        else if (schedule_mode.getValue().contentEquals("Сформировать расписание")){

        }
    }*/

}
