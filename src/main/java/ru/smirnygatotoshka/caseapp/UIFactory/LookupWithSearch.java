package ru.smirnygatotoshka.caseapp.UIFactory;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Control;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.GridPane;

import java.util.function.Predicate;

/**
 * Базовый класс для
 * */
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
