package ru.smirnygatotoshka.caseapp.UIFactory;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import ru.smirnygatotoshka.caseapp.GlobalResources;

public class ControlForm extends UIFactory{

    private boolean isHorizontal;
    private DataChanger changer;

    public ControlForm(String id_prefix, boolean isHorizontal, DataChanger changer) {
        super(id_prefix);
        this.isHorizontal = isHorizontal;
        this.changer = changer;
    }

    @Override
    public Parent create() {
        Button[] controls = getControls();
        return isHorizontal ? createHorizontalControls(controls) : createVerticalControls(controls);
    }

    protected Button[] getControls() {
        Button[] controls = new Button[3];

        controls[0] = new Button("Добавить");
        controls[0].setFont(GlobalResources.usualFont);
        controls[0].setStyle("-fx-font: Serif;" +
                "-fx-font-size: 18px;" +
                "-fx-background-color: #CCCCFF;" +
                "-fx-border-color: #000000;");
        controls[0].setOnAction(event -> {
            changer.addAction(event);
        });
        controls[0].setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        put(controls[0], "Add");


        controls[1] = new Button("Редактировать");
        controls[1].setStyle("-fx-font: Serif;" +
                "-fx-font-size: 18px;" +
                "-fx-background-color: #CCCCFF;" +
                "-fx-border-color: #000000;");
        controls[1].setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        controls[1].setOnAction(event -> {
            changer.editAction(event);
        });
        put(controls[1], "Edit");

        controls[2] = new Button("Удалить");
        controls[2].setStyle("-fx-font: Serif;" +
                "-fx-font-size: 18px;" +
                "-fx-background-color: #CCCCFF;" +
                "-fx-border-color: #000000;");
        controls[2].setOnAction(event -> {
            changer.deleteAction(event);
        });
        controls[2].setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        put(controls[2], "Delete");

        return controls;
    }

    protected GridPane createVerticalControls(Button[] controls) {
        GridPane controlRow = new GridPane();
        controlRow.setAlignment(Pos.CENTER);
        controlRow.setStyle("-fx-background-color: #FFCCCC;");
        ColumnConstraints column = new ColumnConstraints();
        column.setHgrow(Priority.ALWAYS);
        column.setPercentWidth(30);
        column.setMaxWidth(Double.MAX_VALUE);
        controlRow.getColumnConstraints().add(column);

        for (int i = 0; i < controls.length; i++) {
            RowConstraints row = new RowConstraints();
            row.setPercentHeight(33);
            row.setVgrow(Priority.ALWAYS);
            row.setValignment(VPos.CENTER);
            row.setMaxHeight(Double.MAX_VALUE);
            controlRow.getRowConstraints().add(row);

            GridPane.setFillWidth(controls[i],true);
            GridPane.setFillHeight(controls[i],true);
            GridPane.setMargin(controls[i],new Insets(10,0,10,0));
            controlRow.add(controls[i], i, 0);
        }

        return controlRow;
    }

    protected GridPane createHorizontalControls(Button[] controls) {

        GridPane controlRow = new GridPane();
        controlRow.setAlignment(Pos.CENTER);
        controlRow.setStyle("-fx-background-color: #FFCCCC;");
        RowConstraints row = new RowConstraints();
        row.setVgrow(Priority.ALWAYS);
        row.setPercentHeight(30);
        row.setMaxHeight(Double.MAX_VALUE);
        controlRow.getRowConstraints().add(row);

        for (int i = 0; i < controls.length; i++) {
            ColumnConstraints column = new ColumnConstraints();
            column.setPercentWidth(33);
            column.setHgrow(Priority.ALWAYS);
            column.setHalignment(HPos.CENTER);
            column.setMaxWidth(Double.MAX_VALUE);
            controlRow.getColumnConstraints().add(column);

            GridPane.setFillWidth(controls[i],true);
            GridPane.setFillHeight(controls[i],true);
            GridPane.setMargin(controls[i],new Insets(0,10,0,10));
            controlRow.add(controls[i], i, 0);
        }

        return controlRow;
    }
}
