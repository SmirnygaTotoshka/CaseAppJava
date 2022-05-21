package ru.smirnygatotoshka.caseapp.Controllers;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.util.Callback;
import javafx.util.converter.DefaultStringConverter;
import ru.smirnygatotoshka.caseapp.DataRepresentation.Passport;
import ru.smirnygatotoshka.caseapp.DataRepresentation.Patient;
import ru.smirnygatotoshka.caseapp.DataRepresentation.Police;
import ru.smirnygatotoshka.caseapp.DataRepresentation.Reference;
import ru.smirnygatotoshka.caseapp.Database.Database;
import ru.smirnygatotoshka.caseapp.Formatters.PassportNumberFormatter;
import ru.smirnygatotoshka.caseapp.Formatters.SNILSFormatter;
import ru.smirnygatotoshka.caseapp.PassportForm;
import ru.smirnygatotoshka.caseapp.Formatters.NamesFormatter;
import ru.smirnygatotoshka.caseapp.Formatters.PhoneNumberFilter;
import ru.smirnygatotoshka.caseapp.GlobalResources;
import ru.smirnygatotoshka.caseapp.PoliceForm;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class PatientFormController implements Initializable {

    @FXML
    private TextField sirname,name,second_name,workplace,snils,telephone;

    @FXML
    private ComboBox<Reference> sex, priviledge, employment, family_status;

    public Button getAdd_passport() {
        return add_passport;
    }

    @FXML
    private Button add_passport,add_police;

    @FXML
    private Button save;

    @FXML
    private DatePicker dob;

    private ObservableList<Reference> domen_sex, domen_priviledge, domen_employment, domen_family_status;

    public void setPatient(Patient patient) {

        if (patient != null){
            this.patient = patient;
            sirname.setText(patient.getSirname());
            name.setText(patient.getName());
            second_name.setText(patient.getSecondName());
            workplace.setText(patient.getWorkplace());
            snils.setText(patient.getSnils());
            System.out.println(LocalDate.parse(patient.getDob().toString()));
            dob.setValue(LocalDate.parse(patient.getDob().toString()));
            telephone.setText(patient.getTelephone());
            sex.setValue(GlobalResources.findItemFromReference(domen_sex, patient.getSex()));
            priviledge.setValue(GlobalResources.findItemFromReference(domen_priviledge, patient.getPriviledge()));
            employment.setValue(GlobalResources.findItemFromReference(domen_employment, patient.getEmployment()));
            family_status.setValue(GlobalResources.findItemFromReference(domen_family_status, patient.getFamilyStatus()));
            String query = "SELECT Number,Address FROM tbl_Passports WHERE Number = " + patient.getPassport() + ";";
            ObservableList<Passport> passports = Database.getPassports(query);
            if (passports.size() == 1){
                passport = passports.get(0);
                add_passport.setText(PassportNumberFormatter.formatNumber(passport.getNumber()));
            }
            else if (passports.size() > 1){
                GlobalResources.alert(Alert.AlertType.WARNING,"В БД несколько одинаковых паспортов! Проверьте БД!");
            }

            String query1 = "SELECT Number,spr_SMO.NAME as Organization FROM tbl_Polices " +
                    "INNER JOIN spr_SMO ON spr_SMO.ID = tbl_Polices.Organization " +
                    "WHERE Number = " + patient.getPolice() + ";";
            ObservableList<Police> polices = Database.getPolices(query1);
            if (polices.size() == 1){
                police = polices.get(0);
                add_police.setText(police.getNumber());
            }
            else if (polices.size() > 1){
                GlobalResources.alert(Alert.AlertType.WARNING,"В БД несколько одинаковых полисов! Проверьте БД!");
            }

        }
        else {
            this.patient = new Patient();
            this.passport = new Passport();
            this.police = new Police();
        }
    }

    protected Patient patient = null;
    protected Passport passport = null;
    protected Police police = null;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        TextFormatter<String> sirnameFormatter = new TextFormatter(new DefaultStringConverter(), "", new NamesFormatter());
        TextFormatter<String> nameFormatter = new TextFormatter(new DefaultStringConverter(), "", new NamesFormatter());
        TextFormatter<String> secondNameFormatter = new TextFormatter(new DefaultStringConverter(), "", new NamesFormatter());


        sirname.setTextFormatter(sirnameFormatter);
        name.setTextFormatter(nameFormatter);
        second_name.setTextFormatter(secondNameFormatter);

        domen_sex = Database.getReference("spr_Sex");
        domen_priviledge = Database.getReference("spr_Priviledge");
        domen_employment = Database.getReference("spr_Employment");
        domen_family_status = Database.getReference("spr_FamilyStatus");

        sex.setItems(domen_sex);
        priviledge.setItems(domen_priviledge);
        employment.setItems(domen_employment);
        family_status.setItems(domen_family_status);
        dob.setValue(LocalDate.now().minusYears(18));
        dob.setDayCellFactory(new Callback<DatePicker, DateCell>() {
            @Override
            public DateCell call(DatePicker datePicker) {
                return new DateCell(){
                    //TODO min/max
                    LocalDate minDate = LocalDate.now().minusYears(150), maxDate = LocalDate.now().minusYears(18); // min - today -150 max today - 18
                    @Override
                    public void updateItem(LocalDate item, boolean empty){
                        super.updateItem(item,empty);
                        if (item.isBefore(minDate)){
                            setDisable(true);
                            setStyle("-fx-background-color: #ffc0cb");
                        }
                        else if (item.isAfter(maxDate)){
                            setDisable(true);
                            setStyle("-fx-background-color: #ffc0cb");
                        }
                    }
                };
            }
        });
        TextFormatter<String> snils_formatter = new TextFormatter<>(new DefaultStringConverter(), "", new SNILSFormatter());
        snils.setTextFormatter(snils_formatter);
       /* snils.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String oldVal, String newVal) {
                if (!newVal.matches("\\d*") || newVal.length() > 11){
                    snils.setText(oldVal);
                }
            }
        });*/

        workplace.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String oldVal, String newVal) {
                if (newVal.length() > 200){
                    workplace.setText(oldVal);
                }
            }
        });
        TextFormatter<String> textFormatter = new TextFormatter(new DefaultStringConverter(), "", new PhoneNumberFilter());
        telephone.setTextFormatter(textFormatter);

    }

    @FXML
    protected void onAddPassport(ActionEvent event){
        PassportForm passForm = new PassportForm(this);
        add_passport.setDisable(true);
        GlobalResources.openedStages.put("PassportForm", passForm);
    }

    @FXML
    protected void onAddPolice(ActionEvent event){
        PoliceForm polForm = new PoliceForm(this);
        add_police.setDisable(true);
        GlobalResources.openedStages.put("PoliceForm", polForm);
    }

    public Button getAdd_police() {
        return add_police;
    }
}
