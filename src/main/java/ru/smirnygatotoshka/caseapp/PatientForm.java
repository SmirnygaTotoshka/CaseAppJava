package ru.smirnygatotoshka.caseapp;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import ru.smirnygatotoshka.caseapp.DataRepresentation.Patient;
import ru.smirnygatotoshka.caseapp.Controllers.PatientFormController;

import java.util.Optional;

public class PatientForm extends Stage {

    public PatientForm(Patient patient) {
        super();
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(PatientForm.class.getResource("add_patient.fxml"));

            Scene scene = new Scene(fxmlLoader.load());
            setTitle("Большой Шлёпа АРМ!");
            setScene(scene);
            PatientFormController controller = fxmlLoader.getController();
            controller.setPatient(patient);
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
            //GlobalResources.openedStages.get("PatientForm").close();
            GlobalResources.openedStages.remove("PatientForm",GlobalResources.openedStages.get("PatientForm"));
            //GlobalResources.openedStages.get("PoliceForm").close();
            Stage pass = GlobalResources.openedStages.get("PassportForm");
            if (pass != null && pass.isShowing()) {
                pass.close();
            }
        }
        else {
            event.consume();
        }
    }
}

