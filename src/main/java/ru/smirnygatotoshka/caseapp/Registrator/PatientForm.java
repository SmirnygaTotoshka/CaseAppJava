package ru.smirnygatotoshka.caseapp.Registrator;

import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import ru.smirnygatotoshka.caseapp.DataRepresentation.Patient;
import ru.smirnygatotoshka.caseapp.GlobalResources;
import ru.smirnygatotoshka.caseapp.UIFactory.PatientEditFactory;

import java.util.Optional;

public class PatientForm extends Stage {

    public PatientEditFactory getPatientEditFactory() {
        return patientEditFactory;
    }

    private PatientEditFactory patientEditFactory;

    public PatientForm(Patient patient, TableView<?> table) {
        super();
        try {
            patientEditFactory = new PatientEditFactory("PatientForm", patient, 15);
            Scene scene = new Scene(patientEditFactory.create());
            setTitle("Большой Шлёпа АРМ!");
            setScene(scene);
            scene.getWindow().addEventFilter(WindowEvent.WINDOW_CLOSE_REQUEST,patientEditFactory::onClose);
            scene.getWindow().addEventFilter(WindowEvent.WINDOW_CLOSE_REQUEST,windowEvent -> {table.refresh();System.out.println("refresh");});
            show();
        }
        catch(Exception e){
            GlobalResources.alert(Alert.AlertType.ERROR,"Не могу открыть форму пациента - " + e.getMessage());
            e.printStackTrace();
        }
    }

}

