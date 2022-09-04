package ru.smirnygatotoshka.caseapp.UIFactory;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.util.converter.DefaultStringConverter;
import ru.smirnygatotoshka.caseapp.DataRepresentation.Passport;
import ru.smirnygatotoshka.caseapp.DataRepresentation.Patient;
import ru.smirnygatotoshka.caseapp.DataRepresentation.Police;
import ru.smirnygatotoshka.caseapp.DataRepresentation.Reference;
import ru.smirnygatotoshka.caseapp.Database.Database;
import ru.smirnygatotoshka.caseapp.Formatters.PassportNumberFormatter;
import ru.smirnygatotoshka.caseapp.GlobalResources;

public class PoliceEditFactory extends DatabaseEditFactory{

    private Police police;

    public PoliceEditFactory(String id_prefix, Patient patient, int row_percent) {
        super(id_prefix,row_percent);
        labels = FXCollections.observableArrayList("Серия и номер","Адрес регистрации");
        if (patient == null){
            police = null;
        }
        else {
            String query = "SELECT Number,spr_SMO.NAME as Organization FROM tbl_Polices " +
                    "INNER JOIN spr_SMO ON spr_SMO.ID = tbl_Polices.Organization " +
                    "WHERE Number = " + patient.getPolice() + ";";
            ObservableList<Police> polices = Database.getPolices(query);
            if (polices.size() == 1){
                police = polices.get(0);
            }
            else if (polices.size() > 1){
                GlobalResources.alert(Alert.AlertType.WARNING,"В БД несколько одинаковых полисов! Проверьте БД!");
            }
        }
    }

    @Override
    public Parent create() {
        ScrollPane scrollPane = (ScrollPane) super.create();
        scrollPane.setPrefSize(700,300);

        TextField number = new TextField();
        number.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        number.setFont(GlobalResources.usualFont);
        number.setPromptText("1234567890123456");
        number.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String oldVal, String newVal) {
                if (newVal.length() > 16){
                    number.setText(oldVal);
                }
                if (!newVal.matches("\\d*")){
                    number.setText(oldVal);
                }
            }
        });
        number.setText(police == null ? "" : police.getNumber());


        ObservableList<Reference> domen_smo = Database.getReference("spr_SMO");

        ChoiceBox<Reference> smo = new ChoiceBox<>(domen_smo);
        smo.setValue(police == null ? smo.getItems().get(0) : GlobalResources.findItemFromReference(domen_smo, police.getOrganization()));
        smo.setStyle("-fx-font: Serif;" +
                "-fx-font-size: 18px;" +
                "-fx-background-color: #CCCCFF;" +
                "-fx-border-color: #000000;");
        //lookupChoices.setPadding(new Insets(0,20,0,10));
        smo.setMaxSize(1000, 500);

        addField(number, 0, "Number");
        addField(smo, 1, "Organization");

        return scrollPane;
    }

    @Override
    protected void saveRecord(ActionEvent event) {

    }

    @Override
    protected String getColumnNameFromDB(String item) {
        return null;
    }
}
