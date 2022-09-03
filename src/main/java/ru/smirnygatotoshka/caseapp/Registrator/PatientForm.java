package ru.smirnygatotoshka.caseapp.Registrator;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import ru.smirnygatotoshka.caseapp.DataRepresentation.Patient;
import ru.smirnygatotoshka.caseapp.Controllers.Registrator.PatientFormController;
import ru.smirnygatotoshka.caseapp.GlobalResources;
import ru.smirnygatotoshka.caseapp.UIFactory.PatientEditFactory;

import java.util.Optional;

public class PatientForm extends Stage {

    public PatientForm(Patient patient) {
        super();
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(PatientForm.class.getResource("add_patient_new.fxml"));

            //Scene scene = new Scene(fxmlLoader.load());
            //PatientFormController.setPatient(patient);
            PatientEditFactory patientEditFactory = new PatientEditFactory("PatientForm", patient);

            Scene scene = new Scene(patientEditFactory.create());
            setTitle("Большой Шлёпа АРМ!");
            setScene(scene);
            scene.getWindow().addEventFilter(WindowEvent.WINDOW_CLOSE_REQUEST,this::onClose);
            show();
        }
        catch(Exception e){
            GlobalResources.alert(Alert.AlertType.ERROR,"Не могу открыть форму пациента - " + e.getMessage());
            e.printStackTrace();
        }
    }



    private void onClose(WindowEvent event){
        Optional<ButtonType> answer = GlobalResources.alert(Alert.AlertType.CONFIRMATION,"Продолжить без сохранения?");
        if (answer.get() == ButtonType.OK){

            Stage pol = GlobalResources.openedStages.get("PoliceForm");
            Stage pass = GlobalResources.openedStages.get("PassportForm");
            if (pass != null && pass.isShowing()) {
                pass.close();
            }
            if (pol != null && pol.isShowing()) {
                pol.close();
            }
            GlobalResources.openedStages.remove("PatientForm",GlobalResources.openedStages.get("PatientForm"));
            GlobalResources.openedStages.remove("PassportForm",GlobalResources.openedStages.get("PassportForm"));
            GlobalResources.openedStages.remove("PoliceForm",GlobalResources.openedStages.get("PoliceForm"));
        }
        else {
            event.consume();
        }
    }
}

