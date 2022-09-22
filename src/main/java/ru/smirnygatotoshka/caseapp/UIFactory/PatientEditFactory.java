package ru.smirnygatotoshka.caseapp.UIFactory;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.util.Callback;
import javafx.util.converter.DefaultStringConverter;
import ru.smirnygatotoshka.caseapp.DataRepresentation.Patient;
import ru.smirnygatotoshka.caseapp.DataRepresentation.Reference;
import ru.smirnygatotoshka.caseapp.Database.Database;
import ru.smirnygatotoshka.caseapp.Database.PatientsActions;
import ru.smirnygatotoshka.caseapp.Formatters.NamesFormatter;
import ru.smirnygatotoshka.caseapp.Formatters.PhoneNumberFilter;
import ru.smirnygatotoshka.caseapp.Formatters.SNILSFormatter;
import ru.smirnygatotoshka.caseapp.GlobalResources;
import ru.smirnygatotoshka.caseapp.Registrator.PassportForm;
import ru.smirnygatotoshka.caseapp.Registrator.PoliceForm;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Optional;

public class PatientEditFactory extends DatabaseEditFactory{

    private enum Status{
        OK("OK"),
        EMPTY_SIRNAME("Укажите фамилию пациента."),
        EMPTY_NAME("Укажите имя пациента."),
        EMPTY_WORKPLACE("Укажите место работы пациента."),
        INVALID_SNILS("Неправильно записанный СНИЛС."),
        INVALID_TELEPHONE("Неправильно записанный телефон."),
        NO_PASSPORT("Заполните данные паспорта."),
        NO_POLICE("Заполните данные полиса.");
        public String message;
        Status(String msg){this.message = msg;}
    }

    private Patient patient;

    private TextField sirname, name, second_name, workplace, snils, telephone;
    private ChoiceBox<Reference> sex, priviledge, employment, family_status;
    private Button police, passport;
    private DatePicker dob;

    public PatientEditFactory(String id_prefix, Patient patient, int row_percent) {
        super(id_prefix, row_percent);
        this.patient = patient;
        labels = FXCollections.observableArrayList("Фамилия","Имя","Отчество",
                "Пол","Дата рождения","Льгота","Соц.статус","Место работы","Паспорт","СНИЛС","Полис","Семейное положение","Телефон");
    }

    @Override
    public Parent create() {
        ScrollPane scrollPane = (ScrollPane) super.create();
        scrollPane.setPrefSize(900,700);

        sirname = new TextField();
        sirname.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        sirname.setFont(GlobalResources.usualFont);
        sirname.setPromptText("Иванов");
        sirname.setTextFormatter(new TextFormatter<>(new DefaultStringConverter(), "", new NamesFormatter()));
        sirname.setText(patient == null ? "" : patient.getSirname());

        name = new TextField();
        name.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        name.setFont(GlobalResources.usualFont);
        name.setPromptText("Иван");
        name.setTextFormatter(new TextFormatter<>(new DefaultStringConverter(), "", new NamesFormatter()));
        name.setText(patient == null ? "" : patient.getName());

        second_name = new TextField();
        second_name.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        second_name.setFont(GlobalResources.usualFont);
        second_name.setPromptText("Иванович");
        second_name.setTextFormatter(new TextFormatter<>(new DefaultStringConverter(), "", new NamesFormatter()));
        second_name.setText(patient == null ? "" : patient.getSecondName());

        workplace = new TextField();
        workplace.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        workplace.setFont(GlobalResources.usualFont);
        workplace.setPromptText("ООО \"Большой Куш\"");
        workplace.setText(patient == null ? "" : patient.getWorkplace());

        snils = new TextField();
        snils.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        snils.setFont(GlobalResources.usualFont);
        snils.setPromptText("111 111 111 11");
        snils.setTextFormatter(new TextFormatter<>(new DefaultStringConverter(), "", new SNILSFormatter()));
        snils.setText(patient == null ? "" : patient.getSnils());

        telephone = new TextField();
        telephone.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        telephone.setFont(GlobalResources.usualFont);
        telephone.setPromptText("+79876543210");
        telephone.setTextFormatter(new TextFormatter<>(new DefaultStringConverter(), "", new PhoneNumberFilter()));
        telephone.setText(patient == null ? "" : patient.getTelephone().substring(1));

        passport = new Button(patient == null ? "Добавить" : patient.getPassport());
        passport.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        passport.setStyle("-fx-font: 18px Serif;\n" +
                "-fx-background-color: #CCCCFF;\n" +
                "-fx-border-color: #000000;");
        passport.setOnAction(event -> {
            PassportForm passForm = new PassportForm(patient);
            passport.setDisable(true);
            GlobalResources.openedStages.put("PassportForm", passForm);
        });

        police = new Button(patient == null ? "Добавить" : patient.getPolice());
        police.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        police.setStyle("-fx-font: 18px Serif;\n" +
                "-fx-background-color: #CCCCFF;\n" +
                "-fx-border-color: #000000;");
        police.setOnAction(event -> {
            PoliceForm polForm = new PoliceForm(patient);
            police.setDisable(true);
            GlobalResources.openedStages.put("PoliceForm", polForm);
        });

        ObservableList<Reference> domen_sex, domen_priviledge, domen_employment, domen_family_status;

        domen_sex = Database.getReference("spr_Sex");
        domen_priviledge = Database.getReference("spr_Priviledge");
        domen_employment = Database.getReference("spr_Employment");
        domen_family_status = Database.getReference("spr_FamilyStatus");

        sex = new ChoiceBox<>(domen_sex);
        sex.setValue(patient == null ? sex.getItems().get(0) : GlobalResources.findItemFromReference(domen_sex, patient.getSex()));
        sex.setStyle("-fx-font: Serif;" +
                "-fx-font-size: 18px;" +
                "-fx-background-color: #CCCCFF;" +
                "-fx-border-color: #000000;");
        //lookupChoices.setPadding(new Insets(0,20,0,10));
        sex.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);

        priviledge = new ChoiceBox<>(domen_priviledge);
        priviledge.setValue(patient == null ? priviledge.getItems().get(priviledge.getItems().size()-1) : GlobalResources.findItemFromReference(domen_priviledge, patient.getPriviledge()));
        priviledge.setStyle("-fx-font: Serif;" +
                "-fx-font-size: 18px;" +
                "-fx-background-color: #CCCCFF;" +
                "-fx-border-color: #000000;");
        //lookupChoices.setPadding(new Insets(0,20,0,10));
        priviledge.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);

        employment = new ChoiceBox<>(domen_employment);
        employment.setValue(patient == null ? employment.getItems().get(0) : GlobalResources.findItemFromReference(domen_employment, patient.getEmployment()));
        employment.setStyle("-fx-font: Serif;" +
                "-fx-font-size: 18px;" +
                "-fx-background-color: #CCCCFF;" +
                "-fx-border-color: #000000;");
        //lookupChoices.setPadding(new Insets(0,20,0,10));
        employment.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);

        family_status = new ChoiceBox<>(domen_family_status);
        family_status.setValue(patient == null ? family_status.getItems().get(0) : GlobalResources.findItemFromReference(domen_family_status, patient.getFamilyStatus()));
        family_status.setStyle("-fx-font: Serif;" +
                "-fx-font-size: 18px;" +
                "-fx-background-color: #CCCCFF;" +
                "-fx-border-color: #000000;");
        //lookupChoices.setPadding(new Insets(0,20,0,10));
        family_status.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);

        dob = new DatePicker(patient == null ? LocalDate.now().minusYears(18) : patient.getDob().toLocalDate());
        dob.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        dob.setDayCellFactory(new Callback<>() {
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

        addField(sirname, 0, "Sirname");
        addField(name, 1, "Name");
        addField(second_name, 2, "SecondName");
        addField(sex, 3, "Sex");
        addField(dob, 4, "Dob");
        addField(priviledge, 5, "Priviledge");
        addField(employment, 6, "Employment");
        addField(workplace, 7, "Workplace");
        addField(passport, 8, "Passport");
        addField(snils, 9, "Snils");
        addField(police, 10, "Police");
        addField(family_status, 11, "FamilyStatus");
        addField(telephone, 12, "Telephone");

        if (patient != null) {
            PatientsActions.setPassport(PatientsActions.getPassportByNumber(patient.getPassport()));
            PatientsActions.setPolice(PatientsActions.getPoliceByNumber(patient.getPolice()));
        }
        return scrollPane;
    }

    @Override
    protected void saveRecord(ActionEvent event) {
        Status status = check();
        switch (status) {
            case OK:
                java.sql.Date date = new java.sql.Date(Date.from(dob.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()).getTime());
                Patient new_data = new Patient(sirname.getText(), name.getText(), second_name.getText(), sex.getValue().getNAME(), date,
                        priviledge.getValue().getNAME(), employment.getValue().getNAME(), workplace.getText(), passport.getText(), police.getText(),
                        SNILSFormatter.removeSpecial(snils.getText()), family_status.getValue().getNAME(), PhoneNumberFilter.removeSpecial(telephone.getText()));
                if (patient == null) {
                    PatientsActions.setPatient(new_data);
                    PatientsActions.add();
                } else {
                    if (patient.equals(new_data)) {
                        closeForm();
                    } else {
                        PatientsActions.setPatient(new_data);
                        PatientsActions.edit(patient);
                    }
                }
                closeForm();
                return;
            case EMPTY_SIRNAME:
                sirname.requestFocus();
                break;
            case EMPTY_NAME:
                name.requestFocus();
                break;
            case EMPTY_WORKPLACE:
                workplace.requestFocus();
                break;
            case NO_POLICE:
                police.requestFocus();
                break;
            case NO_PASSPORT:
                passport.requestFocus();
                break;
            case INVALID_TELEPHONE:
                telephone.requestFocus();
                break;
            case INVALID_SNILS:
                snils.requestFocus();
                break;
        }
        GlobalResources.alert(Alert.AlertType.WARNING,status.message);

    }

    public void onClose(WindowEvent event){
        Optional<ButtonType> answer = GlobalResources.alert(Alert.AlertType.CONFIRMATION,"Продолжить без сохранения?");
        if (answer.get() == ButtonType.OK){
            closeForm();
        }
        else {
            event.consume();
        }
    }

    private void closeForm(){
        Stage pol = GlobalResources.openedStages.get("PoliceForm");
        Stage pass = GlobalResources.openedStages.get("PassportForm");
        if (pass != null && pass.isShowing()) {
            GlobalResources.closeStage("PassportForm");
        }
        if (pol != null && pol.isShowing()) {
            GlobalResources.closeStage("PoliceForm");
        }
        GlobalResources.closeStage("PatientForm");
        GlobalResources.openedStages.remove("PatientForm",GlobalResources.openedStages.get("PatientForm"));

    }

    private Status check(){
        if (sirname.getText().isEmpty() || sirname.getText().isBlank())
            return Status.EMPTY_SIRNAME;
        if (name.getText().isEmpty() || name.getText().isBlank())
            return Status.EMPTY_NAME;
        if (workplace.getText().isEmpty() || workplace.getText().isBlank())
            return Status.EMPTY_WORKPLACE;
        if (snils.getText().length() != 14)
            return Status.INVALID_SNILS;
        if (telephone.getText().length() != 14)
            return Status.INVALID_TELEPHONE;
        if (passport.getText().contentEquals("Добавить"))
            return Status.NO_PASSPORT;
        if (police.getText().contentEquals("Добавить"))
            return Status.NO_POLICE;

        return Status.OK;
    }

}
