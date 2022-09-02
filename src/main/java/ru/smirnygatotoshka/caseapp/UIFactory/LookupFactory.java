package ru.smirnygatotoshka.caseapp.UIFactory;

import javafx.collections.ObservableList;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import ru.smirnygatotoshka.caseapp.GlobalResources;

import java.util.ArrayList;

public abstract class LookupFactory<I, L> extends UIFactory {

    protected ObservableList<I> lookup_items;

    public LookupFactory(String id_prefix, ObservableList<I> lookup_items) {
        super(id_prefix);
        this.lookup_items = lookup_items;
    }

    @Override
    public Parent create() {
        GridPane parent = new GridPane();

        ColumnConstraints column = new ColumnConstraints();
        column.setHgrow(Priority.ALWAYS);
        column.setHalignment(HPos.CENTER);
        column.setPercentWidth(100);

        RowConstraints search_constrains = new RowConstraints();
        search_constrains.setVgrow(Priority.ALWAYS);
        search_constrains.setValignment(VPos.CENTER);
        search_constrains.setPercentHeight(20);

        RowConstraints table_constrains = new RowConstraints();
        table_constrains.setVgrow(Priority.ALWAYS);
        table_constrains.setValignment(VPos.CENTER);
        table_constrains.setPercentHeight(70);

        RowConstraints action_constrains = new RowConstraints();
        action_constrains.setVgrow(Priority.ALWAYS);
        action_constrains.setValignment(VPos.CENTER);
        action_constrains.setPercentHeight(20);

        parent.getColumnConstraints().add(column);
        parent.getRowConstraints().add(search_constrains);
        parent.getRowConstraints().add(table_constrains);
        parent.getRowConstraints().add(action_constrains);

        //parent.setHgap(15);
        //parent.setVgap(5);
        parent.setAlignment(Pos.CENTER);


        GridPane searchingRow = createSearchingRow();

        TableView<L> lookupTable = createLookupTable();
        //addHBoxSettings(lookupTable);

        GridPane controlRow = createСontrolRow();
        //addHBoxSettings(controlRow);
       // addVBoxSettings(controlRow);

        parent.add(searchingRow,0,0);
        parent.add(lookupTable,0,1);
        parent.add(controlRow,0,2);

        return parent;
    }

    private GridPane createSearchingRow(){

        GridPane searchingRow = new GridPane();
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
        searchingRow.getColumnConstraints().add(choice_constrains);

        //searchingRow.setHgap(15);
        //searchingRow.setVgap(5);
        searchingRow.setAlignment(Pos.CENTER);

        TextField lookup = new TextField();
        lookup.setFont(GlobalResources.usualFont);
        lookup.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        //addHBoxSettings(lookup);
        searchingRow.add(lookup,0,0);
        put(lookup, "lookup");
        GridPane.setFillWidth(lookup, true);
        GridPane.setMargin(lookup,new Insets(0,10,0,20));

        Label label = new Label("Поиск по:");
        label.setFont(GlobalResources.usualFont);
        searchingRow.add(label,1,0);
        put(label, "label");

        ChoiceBox<I> lookupChoices = new ChoiceBox<>(lookup_items);
        lookupChoices.setValue(lookup_items.get(0));
        lookupChoices.setStyle("-fx-font: Arial;" +
                "-fx-font-size: 18px;" +
                "-fx-background-color: #CCCCFF;" +
                "-fx-border-color: #000000;");
        //lookupChoices.setPadding(new Insets(0,20,0,10));
        lookupChoices.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);

        //addHBoxSettings(lookupChoices);
        searchingRow.add(lookupChoices,2,0);
        put(lookupChoices, "lookupChoices");
        GridPane.setFillWidth(lookupChoices, true);
        GridPane.setMargin(lookupChoices,new Insets(0,20,0,10));
        return searchingRow;
    }

    private TableView<L> createLookupTable(){
        TableView<L> lookupTable = new TableView<>();

        for (I i : lookup_items){
            TableColumn<L, String> column = new TableColumn<>(i.toString());
            column.setMinWidth(300);
            column.setMinWidth(Control.USE_COMPUTED_SIZE);
            lookupTable.getColumns().add(column);
        }


        addVBoxSettings(lookupTable);
        put(lookupTable, "lookupTable");

        return lookupTable;
    }

    private GridPane createСontrolRow(){

        GridPane controlRow = new GridPane();
        RowConstraints row = new RowConstraints();
        row.setVgrow(Priority.ALWAYS);

        ColumnConstraints add_constrains = new ColumnConstraints();
        ColumnConstraints edit_constrains = new ColumnConstraints();
        ColumnConstraints delete_constrains = new ColumnConstraints();

        row.setPercentHeight(30);
        add_constrains.setPercentWidth(33);
        edit_constrains.setPercentWidth(33);
        delete_constrains.setPercentWidth(33);

        add_constrains.setHgrow(Priority.ALWAYS);
        edit_constrains.setHgrow(Priority.ALWAYS);
        delete_constrains.setHgrow(Priority.ALWAYS);

        add_constrains.setHalignment(HPos.CENTER);
        edit_constrains.setHalignment(HPos.CENTER);
        delete_constrains.setHalignment(HPos.CENTER);

        add_constrains.setMaxWidth(Double.MAX_VALUE);
        row.setMaxHeight(Double.MAX_VALUE);
        edit_constrains.setMaxWidth(Double.MAX_VALUE);
        delete_constrains.setMaxWidth(Double.MAX_VALUE);


        controlRow.getRowConstraints().add(row);
        controlRow.getColumnConstraints().add(add_constrains);
        controlRow.getColumnConstraints().add(edit_constrains);
        controlRow.getColumnConstraints().add(delete_constrains);

        //controlRow.setHgap(15);
        //controlRow.setVgap(5);
        controlRow.setAlignment(Pos.CENTER);

        Button add = new Button("Добавить");
        add.setFont(GlobalResources.usualFont);
        add.setStyle("-fx-font: Arial;" +
                "-fx-font-size: 18px;" +
                "-fx-background-color: #CCCCFF;" +
                "-fx-border-color: #000000;");
        add.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        controlRow.add(add,0,0);
        put(add, "Add");


        Button edit = new Button("Редактировать");
        edit.setStyle("-fx-font: Arial;" +
                "-fx-font-size: 18px;" +
                "-fx-background-color: #CCCCFF;" +
                "-fx-border-color: #000000;");
        edit.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);

        controlRow.add(edit,1,0);
        put(edit, "Edit");

        Button delete = new Button("Удалить");
        delete.setStyle("-fx-font: Arial;" +
                "-fx-font-size: 18px;" +
                "-fx-background-color: #CCCCFF;" +
                "-fx-border-color: #000000;");
        delete.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);

        controlRow.add(delete,2,0);
        put(delete, "Delete");

        GridPane.setFillWidth(add,true);
        GridPane.setFillHeight(add,true);

        GridPane.setFillWidth(edit,true);
        GridPane.setFillHeight(edit,true);

        GridPane.setFillWidth(delete,true);
        GridPane.setFillHeight(delete,true);

        GridPane.setMargin(add,new Insets(0,10,0,10));
        GridPane.setMargin(edit,new Insets(0,10,0,10));
        GridPane.setMargin(delete,new Insets(0,10,0,10));

        return controlRow;
    }

    private void addHBoxSettings(Node node){
        HBox.setHgrow(node, Priority.ALWAYS);
        HBox.setMargin(node, new Insets(10));
    }

    private void addVBoxSettings(Node node){
        VBox.setVgrow(node, Priority.ALWAYS);
        VBox.setMargin(node, new Insets(10));
    }
}
