package ru.smirnygatotoshka.caseapp;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import ru.smirnygatotoshka.caseapp.Database.Database;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Optional;

public class Autorisation extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        System.out.println(Autorisation.class.getResource("auth.fxml"));
        FXMLLoader fxmlLoader = new FXMLLoader(Autorisation.class.getResource("auth.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Большой Шлёпа АРМ!");
        stage.setScene(scene);
        scene.getWindow().addEventFilter(WindowEvent.WINDOW_CLOSE_REQUEST,this::onClose);
        GlobalResources.openedStages.put("Auth",stage);
        stage.show();
    }

    private void onClose(WindowEvent event){
        Optional<ButtonType> answer = GlobalResources.alert(Alert.AlertType.CONFIRMATION,"Вы действительно хотите выйти?");
        if (answer.get() == ButtonType.OK){
            for (String key: GlobalResources.openedStages.keySet()) {
                GlobalResources.openedStages.get(key).close();
            }
            try {
                Database.getConnection().close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            Platform.exit();
        }
        else {
            event.consume();
        }
    }

    public static void main(String[] args) {
        launch();
    }
}