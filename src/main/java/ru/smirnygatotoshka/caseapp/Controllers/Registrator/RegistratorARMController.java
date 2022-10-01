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
import ru.smirnygatotoshka.caseapp.Database.Database;
import ru.smirnygatotoshka.caseapp.GlobalResources;
import ru.smirnygatotoshka.caseapp.Registrator.PatientForm;
import ru.smirnygatotoshka.caseapp.UIFactory.PatientLookupFormFactory;
import ru.smirnygatotoshka.caseapp.UIFactory.ScheduleFormFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class RegistratorARMController implements Initializable {


    @FXML
    private GridPane schedule_form_container;

    @FXML
    private GridPane patients_lookup_container;




    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        PatientLookupFormFactory patientFactory = new PatientLookupFormFactory("mainRegistrator");
        GridPane patientLookupLayout = (GridPane) patientFactory.create();
        ScheduleFormFactory scheduleFormFactory = new ScheduleFormFactory("Schedule", Database.getReference("spr_Departments"));
        GridPane schedule_form = (GridPane) scheduleFormFactory.create();
        //HBox.setHgrow(patients_lookup_container, Priority.ALWAYS);
        patients_lookup_container.add(patientLookupLayout, 0, 0);
        schedule_form_container.add(schedule_form, 0, 0);
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
