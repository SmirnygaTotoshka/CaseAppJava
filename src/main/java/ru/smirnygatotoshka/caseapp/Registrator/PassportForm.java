package ru.smirnygatotoshka.caseapp.Registrator;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import ru.smirnygatotoshka.caseapp.Controllers.Registrator.PatientFormController;
import ru.smirnygatotoshka.caseapp.Controllers.Registrator.PassportFormController;
import ru.smirnygatotoshka.caseapp.GlobalResources;

import java.util.Optional;

public class PassportForm extends Stage {

    private PatientFormController patientFormController;
    public PassportForm() {
        super();
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(PassportForm.class.getResource("add_passport.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
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
            //GlobalResources.openedStages.get("PatientForm").close();
            GlobalResources.openedStages.remove("PassportForm",GlobalResources.openedStages.get("PassportForm"));
            //patientFormController.getAdd_passport().setDisable(false);
        }
        else {
            event.consume();
        }
    }
}
