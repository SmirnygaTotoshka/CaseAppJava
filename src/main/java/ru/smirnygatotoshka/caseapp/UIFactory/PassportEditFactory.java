package ru.smirnygatotoshka.caseapp.UIFactory;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.stage.WindowEvent;
import javafx.util.converter.DefaultStringConverter;
import ru.smirnygatotoshka.caseapp.Controllers.Registrator.PassportFormController;
import ru.smirnygatotoshka.caseapp.DataRepresentation.Passport;
import ru.smirnygatotoshka.caseapp.DataRepresentation.Patient;
import ru.smirnygatotoshka.caseapp.Database.Database;
import ru.smirnygatotoshka.caseapp.Database.PatientsActions;
import ru.smirnygatotoshka.caseapp.Formatters.NamesFormatter;
import ru.smirnygatotoshka.caseapp.Formatters.PassportNumberFormatter;
import ru.smirnygatotoshka.caseapp.GlobalResources;
import ru.smirnygatotoshka.caseapp.Registrator.PatientForm;

import java.util.Optional;

public class PassportEditFactory extends DatabaseEditFactory {

    private enum Status{
        OK("OK"),
        INVALID_NUMBER("Введите правильную серию/номер паспорта."),
        NO_ADDRESS("Введите адрес регистрации по паспорту.");
        public String message;
        Status(String msg){this.message = msg;}
    }

    private Passport passport;

    private TextField number;
    private TextField address;
    private Button pass_button;

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

        pass_button = (Button) ((PatientForm) GlobalResources.openedStages.get("PatientForm")).getPatientEditFactory().get("Passport");

    }

    @Override
    public Parent create() {
        ScrollPane scrollPane = (ScrollPane) super.create();
        scrollPane.setPrefSize(700,300);

        number = new TextField();
        number.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        number.setFont(GlobalResources.usualFont);
        number.setPromptText("1111 111111");
        number.setTextFormatter(new TextFormatter<>(new DefaultStringConverter(), "", new PassportNumberFormatter()));
        number.setText(passport == null ? "" : passport.getNumber());

        address = new TextField();
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
        Status status = check();
        switch (status){
            case OK:
                //patientFormController.getAdd_passport().setText(passport_number.getText());
                // patientFormController.getAdd_passport().setDisable(false);
                //patientFormController.passport = new Passport(PassportNumberFormatter.removeSpecial(passport_number.getText()),passport_address.getText());
                passport = new Passport(PassportNumberFormatter.removeSpecial(number.getText()),address.getText());
                PatientsActions.setPassport(passport);
                pass_button.setDisable(false);
                pass_button.setText(passport.getNumber());
                GlobalResources.closeStage("PassportForm");
                return;
            case INVALID_NUMBER:
                number.requestFocus();
                break;
            case NO_ADDRESS:
                address.requestFocus();
                break;
        }
        GlobalResources.alert(Alert.AlertType.WARNING,status.message);

    }


    private Status check() {
        if (number.getText().length() != 11)
            return Status.INVALID_NUMBER;
        if (address.getText().isEmpty() || address.getText().isBlank())
            return Status.NO_ADDRESS;

        return Status.OK;
    }

    public void onClose(WindowEvent event){
        Optional<ButtonType> answer = GlobalResources.alert(Alert.AlertType.CONFIRMATION,"Продолжить без сохранения?");
        if (answer.get() == ButtonType.OK){
            GlobalResources.closeStage("PassportForm");
            pass_button.setDisable(false);
        }
        else {
            event.consume();
        }
    }
}
