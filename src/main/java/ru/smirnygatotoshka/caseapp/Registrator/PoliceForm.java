package ru.smirnygatotoshka.caseapp.Registrator;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import ru.smirnygatotoshka.caseapp.Controllers.Registrator.PatientFormController;
import ru.smirnygatotoshka.caseapp.Controllers.Registrator.PoliceFormController;
import ru.smirnygatotoshka.caseapp.GlobalResources;

import java.util.Optional;

public class PoliceForm extends Stage {
    private PatientFormController patientFormController;
    public PoliceForm(PatientFormController patientFormController) {
        super();
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(PoliceForm.class.getResource("add_police.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            setTitle("Большой Шлёпа АРМ!");
            setScene(scene);
            PoliceFormController controller = fxmlLoader.getController();
            this.patientFormController = patientFormController;
            controller.setPatientFormController(patientFormController);
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
            GlobalResources.openedStages.remove("PoliceForm",GlobalResources.openedStages.get("PoliceForm"));
            patientFormController.getAdd_police().setDisable(false);
        }
        else {
            event.consume();
        }
    }
}
