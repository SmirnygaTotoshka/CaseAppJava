package ru.smirnygatotoshka.caseapp.Controllers;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.util.converter.DefaultStringConverter;
import ru.smirnygatotoshka.caseapp.DataRepresentation.Patient;
import ru.smirnygatotoshka.caseapp.Formatters.PassportNumberFormatter;
import ru.smirnygatotoshka.caseapp.Formatters.PhoneNumberFilter;

import java.net.URL;
import java.util.ResourceBundle;

public class PassportFormController implements Initializable {
    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    private Patient patient;
    private Button source;

    public void setSource(Button source) {
        this.source = source;
    }

    @FXML
    private TextField passport_number,passport_address;

    @FXML
    private Button passport_save;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        passport_address.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String oldVal, String newVal) {
                if (newVal.length() > 500){
                    passport_address.setText(oldVal);
                }
            }
        });
        TextFormatter<String> textFormatter = new TextFormatter(new DefaultStringConverter(), "", new PassportNumberFormatter());
        passport_number.setTextFormatter(textFormatter);
    }
}
