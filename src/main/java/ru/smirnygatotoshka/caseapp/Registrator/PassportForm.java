package ru.smirnygatotoshka.caseapp.Registrator;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import ru.smirnygatotoshka.caseapp.DataRepresentation.Passport;
import ru.smirnygatotoshka.caseapp.DataRepresentation.Patient;
import ru.smirnygatotoshka.caseapp.GlobalResources;
import ru.smirnygatotoshka.caseapp.UIFactory.PassportEditFactory;
import ru.smirnygatotoshka.caseapp.UIFactory.PatientEditFactory;

import java.util.Optional;

public class PassportForm extends Stage {

    public PassportForm(Patient patient) {
        super();
        try {
            //FXMLLoader fxmlLoader = new FXMLLoader(PassportForm.class.getResource("add_passport.fxml"));
            //Scene scene = new Scene(fxmlLoader.load());
            PassportEditFactory passportEditFactory = new PassportEditFactory("PassportForm", patient, 40);
            Scene scene = new Scene(passportEditFactory.create());
            setTitle("Большой Шлёпа АРМ!");
            setScene(scene);

            scene.getWindow().addEventFilter(WindowEvent.WINDOW_CLOSE_REQUEST,passportEditFactory::onClose);
            show();
        }
        catch(Exception e){
            GlobalResources.alert(Alert.AlertType.ERROR,"Не могу открыть форму пациента - " + e.getMessage());
            e.printStackTrace();
        }
    }


}
