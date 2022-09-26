package ru.smirnygatotoshka.caseapp.Pharmacy;

import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import ru.smirnygatotoshka.caseapp.GlobalResources;

import java.io.IOException;
import java.util.Optional;

public class PharmacyARM extends Stage {

    public PharmacyARM() throws IOException {
        super();
        Parent root = null;
        try {
            root = FXMLLoader.load(PharmacyARM.class.getResource("main_pharmacy.fxml"));
            setTitle("Большой Шлёпа АРМ Регистратор.");
            Scene scene = new Scene(root);
            setScene(scene);

            scene.getWindow().addEventFilter(WindowEvent.WINDOW_CLOSE_REQUEST,this::onClose);
            setMaximized(true);
            show();
        }
        catch(Exception e){
            GlobalResources.alert(Alert.AlertType.ERROR,"Не могу запустить АРМ - " + e.getLocalizedMessage());
        }
    }

    private void onClose(WindowEvent event){
        Optional<ButtonType> answer = GlobalResources.alert(Alert.AlertType.CONFIRMATION,"Вы действительно хотите выйти?");
        if (answer.get() == ButtonType.OK){
            /*for (String key: GlobalResources.openedStages.keySet()) {
                GlobalResources.openedStages.get(key).close();
            }*/
            Platform.exit();
        }
        else {
            event.consume();
        }
    }
}
