package ru.smirnygatotoshka.caseapp.UIFactory;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import ru.smirnygatotoshka.caseapp.GlobalResources;

public abstract class DatabaseEditFactory extends UIFactory{

    protected ObservableList<String> labels;

    protected int row_percent;

    public DatabaseEditFactory(String id_prefix, int row_percent) {
        super(id_prefix);
        this.row_percent = row_percent;
    }

    @Override
    public Parent create() {
        ScrollPane scroll = new ScrollPane();
        scroll.setMaxSize(Double.MAX_VALUE,Double.MAX_VALUE);
        scroll.setFitToHeight(true);
        scroll.setFitToWidth(true);

        GridPane grid = new GridPane();
        grid.setMaxSize(Double.MAX_VALUE,Double.MAX_VALUE);
        GridPane.setFillWidth(grid, true);
        GridPane.setFillHeight(grid, true);
        grid.setVgap(10);
        grid.setHgap(5);
        GridPane.setHgrow(grid, Priority.ALWAYS);
        GridPane.setVgrow(grid, Priority.ALWAYS);
        grid.setStyle("-fx-background-color: #FFCCCC;");

        ColumnConstraints label_column = new ColumnConstraints();
        label_column.setHgrow(Priority.ALWAYS);
        label_column.setHalignment(HPos.LEFT);
        label_column.setPercentWidth(30);

        ColumnConstraints field_column = new ColumnConstraints();
        field_column.setHgrow(Priority.ALWAYS);
        field_column.setHalignment(HPos.LEFT);
        field_column.setPercentWidth(70);

        grid.getColumnConstraints().add(label_column);
        grid.getColumnConstraints().add(field_column);

        for (int i = 0;i < labels.size();i++) {

            RowConstraints row = new RowConstraints();
            row.setVgrow(Priority.ALWAYS);
            row.setValignment(VPos.CENTER);
            row.setPercentHeight(row_percent);
            grid.getRowConstraints().add(row);

            Label label = new Label(labels.get(i));
            label.setFont(GlobalResources.usualFont);
            label.setMaxSize(Double.MAX_VALUE,Double.MAX_VALUE);
            grid.add(label, 0, i);
            put(label, label + "_label");


        }

        RowConstraints row = new RowConstraints();
        row.setVgrow(Priority.ALWAYS);
        row.setValignment(VPos.CENTER);
        row.setPercentHeight(15);
        row.setFillHeight(true);
        grid.getRowConstraints().add(row);

        Button save = new Button("Сохранить");
        save.setFont(GlobalResources.usualFont);
        save.setMaxSize(Double.MAX_VALUE,Double.MAX_VALUE);
        save.setStyle("-fx-font: 18px Serif;\n" +
                "-fx-background-color: #CCCCFF;\n" +
                "-fx-border-color: #000000;");
        save.setOnAction(event -> saveRecord(event));
        GridPane.setMargin(save, new Insets(0,10,10,10));
        grid.add(save, 1, labels.size());
        put(save,"Save");
        put(grid,"main");
        scroll.setContent(grid);
        return scroll;
    }

    protected abstract void saveRecord(ActionEvent event);

    protected void addField(Parent parent, int row, String name){
        ((GridPane) get("main")).add(parent,1, row);
        GridPane.setMargin(parent, new Insets(5,10,0,10));
        put(parent, name);
    }
}
