package ru.smirnygatotoshka.caseapp.Registrator;

import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import ru.smirnygatotoshka.caseapp.DataRepresentation.Change;
import ru.smirnygatotoshka.caseapp.DataRepresentation.Doctor;
import ru.smirnygatotoshka.caseapp.DataRepresentation.Patient;
import ru.smirnygatotoshka.caseapp.GlobalResources;
import ru.smirnygatotoshka.caseapp.UIFactory.EditChangeFactory;
import ru.smirnygatotoshka.caseapp.UIFactory.PatientEditFactory;

public class ChangeForm extends Stage {

    private EditChangeFactory editChangeFactory;

    public ChangeForm(Change change, Doctor doctor) {
        super();
        try {
            editChangeFactory = new EditChangeFactory("ChangeForm", change, 40, doctor);
            Scene scene = new Scene(editChangeFactory.create());
            setTitle("Большой Шлёпа АРМ!");
            setScene(scene);
            scene.getWindow().addEventFilter(WindowEvent.WINDOW_CLOSE_REQUEST,editChangeFactory::onClose);
            //scene.getWindow().addEventFilter(WindowEvent.WINDOW_CLOSE_REQUEST,windowEvent -> {table.refresh();System.out.println("refresh");});
            show();
        }
        catch(Exception e){
            GlobalResources.alert(Alert.AlertType.ERROR,"Не могу открыть форму пациента - " + e.getMessage());
            e.printStackTrace();
        }
    }

}
