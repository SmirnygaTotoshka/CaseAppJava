package ru.smirnygatotoshka.caseapp.UIFactory;

import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.stage.WindowEvent;
import ru.smirnygatotoshka.caseapp.GlobalResources;

import java.util.HashMap;
import java.util.Optional;

/**
 * Базовый класс, описывающий функционал для отрисования базовых форм
 * */
public abstract class UIFactory {

    /**
     * Идентификатор базовой формы
     * */
    protected String id_prefix;

    /**
     * Элементы, содержащиеся в этой форме
     * */
    protected HashMap<String, Parent> elements = new HashMap<>();

    public UIFactory(String id_prefix) {
        this.id_prefix = id_prefix;
    }

    public abstract Parent create();

    protected abstract void closeForm();

    public void onClose(WindowEvent event){
        Optional<ButtonType> answer = GlobalResources.alert(Alert.AlertType.CONFIRMATION,"Продолжить без сохранения?");
        if (answer.get() == ButtonType.OK){
            closeForm();
        }
        else {
            event.consume();
        }
    }


    protected void put(Parent element, String name){
        String id = id_prefix + "_" + name;
        element.setId(id);
        elements.put(id, element);
    }

    protected Parent get(String name){
        return elements.get(id_prefix + "_" + name);
    }

    protected void addConstrains(GridPane pane, int[] rows_percentage, int[] cols_percentage){
        for (int i = 0; i < rows_percentage.length; i++)
            pane.getRowConstraints().add(createRowConstraints(rows_percentage[i]));

        for (int i = 0; i < cols_percentage.length; i++)
            pane.getColumnConstraints().add(createColumnConstraints(cols_percentage[i]));

    }

    protected RowConstraints createRowConstraints(int percentage){
        RowConstraints row = new RowConstraints();
        row.setVgrow(Priority.ALWAYS);
        row.setValignment(VPos.CENTER);
        row.setPercentHeight(percentage);
        row.setMaxHeight(Double.MAX_VALUE);
        return row;
    }

    protected ColumnConstraints createColumnConstraints(int percentage){
        ColumnConstraints column = new ColumnConstraints();
        column.setHgrow(Priority.ALWAYS);
        column.setHalignment(HPos.CENTER);
        column.setPercentWidth(percentage);
        column.setMaxWidth(Double.MAX_VALUE);
        return column;
    }

    protected void uniteElements(HashMap<String, Parent> other) {
        for (String k : other.keySet()){
            String name = k.split("_")[1];
            put(other.get(k), name);
        }
    }

}
