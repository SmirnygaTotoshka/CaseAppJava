package ru.smirnygatotoshka.caseapp;

import javafx.beans.property.MapProperty;
import javafx.beans.property.MapPropertyBase;
import javafx.beans.property.SimpleMapProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Control;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import ru.smirnygatotoshka.caseapp.DataRepresentation.Reference;

import java.util.HashMap;
import java.util.Optional;

public class GlobalResources {
    public static SimpleMapProperty<String, Stage> openedStages = new SimpleMapProperty<>(FXCollections.observableHashMap());

    public static final Font usualFont = new Font("Arial", 18);

    public static Optional<ButtonType> alert(Alert.AlertType type, String message){
        Alert alert  = new Alert(type);
        alert.setTitle("Большой Шлёпа АРМ");
        alert.setContentText(message);
        alert.setWidth(500);
        alert.setHeight(500);
        return alert.showAndWait();
    }

    public static Reference findItemFromReference(ObservableList<Reference> list,String name) {
        for (Reference t : list){
            if (t.toString().equals(name))
                return t;
        }
        return null;
    }

    public static void toggleElements(Control[] controls, boolean enable){
        for (Control ctrl: controls) {
            ctrl.disableProperty().set(enable);
        }
    }

    public static void closeStage(String name){
        openedStages.get(name).close();
        openedStages.remove(name,openedStages.get(name));
    }
}
