package ru.smirnygatotoshka.caseapp.Controllers.Registrator;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import ru.smirnygatotoshka.caseapp.DataRepresentation.Police;
import ru.smirnygatotoshka.caseapp.DataRepresentation.Reference;
import ru.smirnygatotoshka.caseapp.Database.Database;
import ru.smirnygatotoshka.caseapp.GlobalResources;

import java.net.URL;
import java.util.ResourceBundle;

public class PoliceFormController implements Initializable {
    private enum Status{
        OK("OK"),
        INVALID_NUMBER("Введите правильный номер полиса."),
        NO_COMPANY("Выберите страховую компанию.");
        public String message;
        Status(String msg){this.message = msg;}
    }

    //private PatientFormController patientFormController;
    @FXML
    private TextField police_number;

    @FXML
    private ComboBox<Reference> police_organization;

   // public void setPatientFormController(PatientFormController patientFormController) {
        /*this.patientFormController = patientFormController;
        if (patientFormController.police == null){
            patientFormController.police = new Police();
        }
        else{
            if (patientFormController.police.getNumber().matches("\\d{16}")){
                patientFormController.getAdd_police().setText(patientFormController.police.getNumber());
                police_number.setText(patientFormController.police.getNumber());
                police_organization.setValue(GlobalResources.findItemFromReference(domen_smo, patientFormController.police.getOrganization()));
            }
            /*else{
                GlobalResources.alert(Alert.AlertType.WARNING,"Некорректное заполнение БД, проверьте омера полисов.");
            }*/
       // }
   // }



    private ObservableList<Reference> domen_smo;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        police_number.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String oldVal, String newVal) {
                if (newVal.length() > 16){
                    police_number.setText(oldVal);
                }
                if (newVal.matches("\\D*")){
                    police_number.setText(oldVal);
                }
            }
        });
        domen_smo = Database.getReference("spr_SMO");
        police_organization.setItems(domen_smo);
    }

    @FXML
    protected void onSavePolice(ActionEvent event){
        Status status = check();
        switch (status){
            case OK:
                /*patientFormController.getAdd_police().setText(police_number.getText());
                patientFormController.police = new Police(police_number.getText(),police_organization.getValue().toString());
                GlobalResources.openedStages.get("PoliceForm").close();
                GlobalResources.openedStages.remove("PoliceForm",GlobalResources.openedStages.get("PoliceForm"));
                patientFormController.getAdd_police().setDisable(false);*/
                return;
            case INVALID_NUMBER:
                police_number.requestFocus();
                break;
            case NO_COMPANY:
                police_organization.requestFocus();
                break;
        }
        GlobalResources.alert(Alert.AlertType.WARNING,status.message);
    }

    private Status check() {
        if (police_number.getText().length() != 16)
            return Status.INVALID_NUMBER;
        if (police_organization.getValue().toString().isEmpty() || police_organization.getValue().toString().isBlank())
            return Status.NO_COMPANY;

        return Status.OK;
    }

}
