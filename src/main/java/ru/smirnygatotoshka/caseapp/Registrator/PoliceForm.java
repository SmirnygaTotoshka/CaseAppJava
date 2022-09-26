package ru.smirnygatotoshka.caseapp.Registrator;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import ru.smirnygatotoshka.caseapp.DataRepresentation.Patient;
import ru.smirnygatotoshka.caseapp.GlobalResources;
import ru.smirnygatotoshka.caseapp.UIFactory.PoliceEditFactory;

import java.util.Optional;

public class PoliceForm extends Stage {
    public PoliceForm(Patient patient) {
        super();
        try {
            //FXMLLoader fxmlLoader = new FXMLLoader(PoliceForm.class.getResource("add_police.fxml"));
            //Scene scene = new Scene(fxmlLoader.load());

            PoliceEditFactory policeEditFactory = new PoliceEditFactory("PoliceForm", patient, 40);
            Scene scene = new Scene(policeEditFactory.create());
            setTitle("Большой Шлёпа АРМ!");
            setScene(scene);

            scene.getWindow().addEventFilter(WindowEvent.WINDOW_CLOSE_REQUEST,policeEditFactory::onClose);
            show();
        }
        catch(Exception e){
            GlobalResources.alert(Alert.AlertType.ERROR,"Не могу открыть форму пациента - " + e.getMessage());
            e.printStackTrace();
        }
    }

}
