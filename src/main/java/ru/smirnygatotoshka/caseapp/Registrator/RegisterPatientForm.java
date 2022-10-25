package ru.smirnygatotoshka.caseapp.Registrator;

import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import ru.smirnygatotoshka.caseapp.DataRepresentation.Change;
import ru.smirnygatotoshka.caseapp.DataRepresentation.Doctor;
import ru.smirnygatotoshka.caseapp.DataRepresentation.ScheduleItem;
import ru.smirnygatotoshka.caseapp.GlobalResources;
import ru.smirnygatotoshka.caseapp.UIFactory.RegisterPatientToDoctorFactory;

public class RegisterPatientForm extends Stage {
    private RegisterPatientToDoctorFactory registerPatientToDoctorFactory;

    public RegisterPatientForm(ScheduleItem item, Doctor doctor) {
        super();
        try {
            registerPatientToDoctorFactory = new RegisterPatientToDoctorFactory("ChoosePatientForm", item);
            Scene scene = new Scene(registerPatientToDoctorFactory.create());
            setTitle("Большой Шлёпа АРМ!");
            setScene(scene);
            scene.getWindow().addEventFilter(WindowEvent.WINDOW_CLOSE_REQUEST,registerPatientToDoctorFactory::onClose);
            //scene.getWindow().addEventFilter(WindowEvent.WINDOW_CLOSE_REQUEST,windowEvent -> {table.refresh();System.out.println("refresh");});
            show();
        }
        catch(Exception e){
            GlobalResources.alert(Alert.AlertType.ERROR,"Не могу открыть форму пациента - " + e.getMessage());
            e.printStackTrace();
        }
    }
}
