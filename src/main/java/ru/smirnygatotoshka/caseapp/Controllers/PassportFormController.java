package ru.smirnygatotoshka.caseapp.Controllers;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.util.converter.DefaultStringConverter;
import ru.smirnygatotoshka.caseapp.DataRepresentation.Passport;
import ru.smirnygatotoshka.caseapp.Formatters.PassportNumberFormatter;
import ru.smirnygatotoshka.caseapp.GlobalResources;
import ru.smirnygatotoshka.caseapp.PassportForm;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class PassportFormController implements Initializable {

    private enum Status{
        OK("OK"),
        INVALID_NUMBER("Введите правильную серию/номер паспорта."),
        NO_ADDRESS("Введите адрес регистрации по паспорту.");
        public String message;
        Status(String msg){this.message = msg;}
    }

    private PatientFormController patientFormController;

    public void setPatientFormController(PatientFormController patientFormController) {
        this.patientFormController = patientFormController;
        if (patientFormController.passport == null){
            patientFormController.passport = new Passport();
        }
        else{
            if (patientFormController.passport.getNumber().matches("\\d{10}")){
                patientFormController.getAdd_passport().setText(PassportNumberFormatter.formatNumber(patientFormController.passport.getNumber()));
                passport_number.setText(patientFormController.passport.getNumber());
                passport_address.setText(patientFormController.passport.getAddress());
            }
            /*else{
                GlobalResources.alert(Alert.AlertType.WARNING,"Некорректное заполнение БД, проверьте серии и номера паспортов.");
            }*/
        }
    }

    @FXML
    private TextField passport_number,passport_address;

    /*@FXML
    private Button passport_save;*/

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        passport_address.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String oldVal, String newVal) {
                if (newVal.length() > 500){
                    passport_address.setText(oldVal);
                }
                if (!newVal.matches("[^a-zA-Z]*")){
                    passport_address.setText(oldVal);
                }
            }
        });
        TextFormatter<String> formatter = new TextFormatter(new DefaultStringConverter(), "", new PassportNumberFormatter());
        passport_number.setTextFormatter(formatter);
    }

    @FXML
    protected void onSavePassport(ActionEvent event){
        Status status = check();
        switch (status){
            case OK:
                patientFormController.getAdd_passport().setText(passport_number.getText());
                patientFormController.getAdd_passport().setDisable(false);
                patientFormController.passport = new Passport(PassportNumberFormatter.removeSpecial(passport_number.getText()),passport_address.getText());
                GlobalResources.openedStages.get("PassportForm").close();
                GlobalResources.openedStages.remove("PassportForm",GlobalResources.openedStages.get("PassportForm"));
                return;
            case INVALID_NUMBER:
                passport_number.requestFocus();
                break;
            case NO_ADDRESS:
                passport_address.requestFocus();
                break;
        }
        GlobalResources.alert(Alert.AlertType.WARNING,status.message);
    }

    private Status check() {
        if (passport_number.getText().length() != 11)
            return Status.INVALID_NUMBER;
        if (passport_address.getText().isEmpty() || passport_address.getText().isBlank())
            return Status.NO_ADDRESS;

        return Status.OK;
    }
}
