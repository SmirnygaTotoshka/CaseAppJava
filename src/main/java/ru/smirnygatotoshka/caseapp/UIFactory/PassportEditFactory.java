package ru.smirnygatotoshka.caseapp.UIFactory;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.util.converter.DefaultStringConverter;
import ru.smirnygatotoshka.caseapp.DataRepresentation.Passport;
import ru.smirnygatotoshka.caseapp.DataRepresentation.Patient;
import ru.smirnygatotoshka.caseapp.Database.Database;
import ru.smirnygatotoshka.caseapp.Formatters.NamesFormatter;
import ru.smirnygatotoshka.caseapp.Formatters.PassportNumberFormatter;
import ru.smirnygatotoshka.caseapp.GlobalResources;

public class PassportEditFactory extends DatabaseEditFactory {

    private Passport passport;

    public PassportEditFactory(String id_prefix, Patient patient, int row_percent) {
        super(id_prefix,row_percent);
        labels = FXCollections.observableArrayList("Серия и номер","Адрес регистрации");
        if (patient == null){
            passport = null;
        }
        else {
            String query = "SELECT Number,Address FROM tbl_Passports WHERE Number = " + patient.getPassport() + ";";
            ObservableList<Passport> passports = Database.getPassports(query);
            if (passports.size() == 1) {
                passport = passports.get(0);
            } else if (passports.size() > 1) {
                GlobalResources.alert(Alert.AlertType.WARNING, "В БД несколько одинаковых паспортов! Проверьте БД!");
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
        number.setPromptText("1111 111111");
        number.setTextFormatter(new TextFormatter<>(new DefaultStringConverter(), "", new PassportNumberFormatter()));
        number.setText(passport == null ? "" : passport.getNumber());

        TextField address = new TextField();
        address.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        address.setFont(GlobalResources.usualFont);
        address.setPromptText("г.Москва, ул. Островитянова д.1");
        address.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String oldVal, String newVal) {
                if (newVal.length() > 500){
                    address.setText(oldVal);
                }
                if (!newVal.matches("[^a-zA-Z]*")){
                    address.setText(oldVal);
                }
            }
        });
        address.setText(passport == null ? "" : passport.getAddress());

        addField(number, 0, "Number");
        addField(address, 1, "Address");

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
