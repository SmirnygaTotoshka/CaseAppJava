package ru.smirnygatotoshka.caseapp.UIFactory;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.stage.WindowEvent;
import ru.smirnygatotoshka.caseapp.DataRepresentation.Patient;
import ru.smirnygatotoshka.caseapp.DataRepresentation.Police;
import ru.smirnygatotoshka.caseapp.DataRepresentation.Reference;
import ru.smirnygatotoshka.caseapp.Database.Database;
import ru.smirnygatotoshka.caseapp.Database.PatientsActions;
import ru.smirnygatotoshka.caseapp.Formatters.PassportNumberFormatter;
import ru.smirnygatotoshka.caseapp.GlobalResources;
import ru.smirnygatotoshka.caseapp.Registrator.PatientForm;

import java.sql.SQLException;
import java.util.Optional;

public class PoliceEditFactory extends DatabaseEditFactory{

    private enum Status{
        OK("OK"),
        INVALID_NUMBER("Введите правильный номер полиса."),
        NO_COMPANY("Выберите страховую компанию.");
        public String message;
        Status(String msg){this.message = msg;}
    }

    private Police police;
    private TextField number;
    private ChoiceBox<Reference> smo;
    private Button police_button;

    public PoliceEditFactory(String id_prefix, Patient patient, int row_percent) {
        super(id_prefix,row_percent);
        labels = FXCollections.observableArrayList("Серия и номер","Адрес регистрации");
        if (patient == null){
            police = null;
        }
        else {
            police = PatientsActions.getPoliceByNumber(patient.getPolice());
        }
        police_button = (Button) ((PatientForm) GlobalResources.openedStages.get("PatientForm")).getPatientEditFactory().get("Police");
    }

    @Override
    public Parent create() {
        ScrollPane scrollPane = (ScrollPane) super.create();
        scrollPane.setPrefSize(700,300);

        number = new TextField();
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

        smo = new ChoiceBox<>(domen_smo);
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
        Status status = check();
        switch (status){
            case OK:
                /*patientFormController.getAdd_police().setText(police_number.getText());
                patientFormController.police = new Police(police_number.getText(),police_organization.getValue().toString());
                GlobalResources.openedStages.get("PoliceForm").close();
                GlobalResources.openedStages.remove("PoliceForm",GlobalResources.openedStages.get("PoliceForm"));
                patientFormController.getAdd_police().setDisable(false);*/
                if (police == null){
                    police = new Police(number.getText(), smo.getValue().toString());
                }
                try {
                    if (PatientsActions.isAbsencePolice(police)&&
                            (!police.getNumber().contentEquals(number.getText()) ||
                             !police.getOrganization().contentEquals(smo.getValue().toString()))) {
                        GlobalResources.alert(Alert.AlertType.WARNING, "Данный полис уже существует в БД.");
                    }
                    else {
                        police = new Police(number.getText(), smo.getValue().toString());
                        PatientsActions.setPolice(police);
                        police_button.setDisable(false);
                        police_button.setText(police.getNumber());
                        GlobalResources.closeStage("PoliceForm");
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }

                return;
            case INVALID_NUMBER:
                number.requestFocus();
                break;
            case NO_COMPANY:
                smo.requestFocus();
                break;
        }
        GlobalResources.alert(Alert.AlertType.WARNING,status.message);
    }

    @Override
    protected void closeForm() {
        GlobalResources.closeStage("PoliceForm");
        police_button.setDisable(false);
    }

    private Status check() {
        if (number.getText().length() != 16)
            return Status.INVALID_NUMBER;
        if (smo.getValue().toString().isEmpty() || smo.getValue().toString().isBlank())
            return Status.NO_COMPANY;

        return Status.OK;
    }


}
