package ru.smirnygatotoshka.caseapp;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import ru.smirnygatotoshka.caseapp.DataRepresentation.Patient;
import ru.smirnygatotoshka.caseapp.Database.Database;

import java.io.IOException;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import java.util.Optional;

public class RegistratorARM extends Stage {

    public RegistratorARM() throws IOException {
        super();
        Parent root = null;
        try {
            root = FXMLLoader.load(RegistratorARM.class.getResource("main_registrator.fxml"));
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
