package ru.smirnygatotoshka.caseapp.UIFactory;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import ru.smirnygatotoshka.caseapp.DataRepresentation.Patient;
import ru.smirnygatotoshka.caseapp.Database.Database;
import ru.smirnygatotoshka.caseapp.GlobalResources;

import java.sql.Array;
import java.util.ArrayList;
import java.util.function.Predicate;

public abstract class LookupWithSearch<I, L> extends UIFactory {


    protected ObservableList<I> columns;
    protected FilteredList<L> filteredList;

    public LookupWithSearch(String id_prefix, ObservableList<I> columns) {
        super(id_prefix);
        this.columns = columns;
    }



    protected abstract String getColumnNameFromDB(String item);

    protected abstract Predicate<L> search();

    @Override
    public Parent create() {
        GridPane parent = new GridPane();
        parent.setAlignment(Pos.CENTER);
        parent.setStyle("-fx-background-color: #FFCCCC;");
        int[] row_percentage = new int[]{15, 85};
        int[] cols_percentage = new int[]{100};

        addConstrains(parent, row_percentage, cols_percentage);

        Parent searchingRow = createLookup();

        TableView<L> lookupTable = createLookupTable();

        GridPane.setMargin(searchingRow, new Insets(10));
        GridPane.setMargin(lookupTable, new Insets(10));
        parent.add(searchingRow,0,0);
        parent.add(lookupTable,0,1);

        return parent;
    }

    protected abstract Parent createLookup();

    /*private TextField createLookup() {

        TextField lookup = new TextField();
        lookup.setFont(GlobalResources.usualFont);
        lookup.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        //addHBoxSettings(lookup);
        //searchingRow.add(lookup,0,0);
        lookup.textProperty().addListener(
                (observableValue, s, t1) -> filteredList.setPredicate(search()));
        put(lookup, "lookup");
        GridPane.setFillWidth(lookup, true);
        GridPane.setMargin(lookup,new Insets(10,10,10,20));
        return lookup;
    }*/

    /*protected TextField createSearchingRow(){

        /*GridPane searchingRow = new GridPane();
        RowConstraints row = new RowConstraints();
        row.setVgrow(Priority.ALWAYS);

        ColumnConstraints lookup_constrains = new ColumnConstraints();
        ColumnConstraints label_constrains = new ColumnConstraints();
        ColumnConstraints choice_constrains = new ColumnConstraints();

        row.setPercentHeight(30);
        lookup_constrains.setPercentWidth(60);
        label_constrains.setPercentWidth(10);
        choice_constrains.setPercentWidth(30);
        choice_constrains.setFillWidth(true);

        lookup_constrains.setHgrow(Priority.ALWAYS);
        label_constrains.setHgrow(Priority.ALWAYS);
        choice_constrains.setHgrow(Priority.ALWAYS);

        lookup_constrains.setHalignment(HPos.LEFT);
        label_constrains.setHalignment(HPos.RIGHT);
        choice_constrains.setHalignment(HPos.LEFT);

        searchingRow.getRowConstraints().add(row);
        searchingRow.getColumnConstraints().add(lookup_constrains);
        searchingRow.getColumnConstraints().add(label_constrains);
        searchingRow.getColumnConstraints().add(choice_constrains);*/

        //searchingRow.setHgap(15);
        //searchingRow.setVgap(5);
        //searchingRow.setAlignment(Pos.CENTER);

       /* TextField lookup = new TextField();
        lookup.setFont(GlobalResources.usualFont);
        lookup.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        //addHBoxSettings(lookup);
        //searchingRow.add(lookup,0,0);
        lookup.setOnAction();
        put(lookup, "lookup");
        GridPane.setFillWidth(lookup, true);
        GridPane.setMargin(lookup,new Insets(0,10,0,20));*/

        /*Label label = new Label("Поиск по:");
        label.setFont(GlobalResources.usualFont);
        searchingRow.add(label,1,0);
        put(label, "label");*/

        /*ChoiceBox<I> lookupChoices = new ChoiceBox<>(items);
        lookupChoices.setValue(items.get(0));
        lookupChoices.setStyle("-fx-font: Serif;" +
                "-fx-font-size: 18px;" +
                "-fx-background-color: #CCCCFF;" +
                "-fx-border-color: #000000;");
        //lookupChoices.setPadding(new Insets(0,20,0,10));
        lookupChoices.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);

        //addHBoxSettings(lookupChoices);
        searchingRow.add(lookupChoices,2,0);
        put(lookupChoices, "lookupChoices");
        GridPane.setFillWidth(lookupChoices, true);
        GridPane.setMargin(lookupChoices,new Insets(0,20,0,10));*/
       /* return lookup;
    }*/

    protected abstract void refresh();


    protected TableView<L> createLookupTable(){
        TableView<L> lookupTable = new TableView<>();

        for (I i : columns){
            TableColumn<L, String> column = new TableColumn<>(i.toString());
            column.setMinWidth(200);
            column.setMinWidth(Control.USE_COMPUTED_SIZE);
            lookupTable.getColumns().add(column);
        }

        lookupTable.setStyle("-fx-background-color: #FFFF99;");

        put(lookupTable, "lookupTable");

        return lookupTable;
    }

}
