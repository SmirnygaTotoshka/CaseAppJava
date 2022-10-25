package ru.smirnygatotoshka.caseapp.UIFactory;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.util.Callback;
import ru.smirnygatotoshka.caseapp.DataRepresentation.Change;
import ru.smirnygatotoshka.caseapp.DataRepresentation.Doctor;
import ru.smirnygatotoshka.caseapp.Database.ChangeActions;
import ru.smirnygatotoshka.caseapp.Database.Database;
import ru.smirnygatotoshka.caseapp.GlobalResources;

import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class EditChangeFactory extends DatabaseEditFactory{

    private DatePicker date;
    private RadioButton morning,evening;
    private SimpleDateFormat parser = new SimpleDateFormat("HH:mm");
    private Time start_morning, finish_morning, start_evening, finish_evening;
    private Doctor doctor;
    private Change change;

    public EditChangeFactory(String id_prefix, Change change, int row_percent, Doctor doctor) {
        super(id_prefix, row_percent);
        this.change = change;
        labels = FXCollections.observableArrayList("Дата","Смена");
        this.doctor = doctor;
        try {
            start_morning = new Time(parser.parse("08:00").getTime());
            finish_morning = new Time(parser.parse("14:00").getTime());
            start_evening = new Time(parser.parse("14:30").getTime());
            finish_evening = new Time(parser.parse("20:30").getTime());
        } catch (ParseException e) {
            e.printStackTrace();
            GlobalResources.alert(Alert.AlertType.ERROR, e.getMessage());
            return;
        }
    }

    @Override
    public Parent create() {
        ScrollPane scrollPane = (ScrollPane) super.create();
        scrollPane.setPrefSize(800,350);

        date = new DatePicker(change == null ? LocalDate.now().plusDays(1) : change.getDate().toLocalDate());
        date.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        date.setDayCellFactory(new Callback<>() {
            @Override
            public DateCell call(DatePicker datePicker) {
                return new DateCell(){
                    LocalDate minDate = LocalDate.now().plusDays(1), maxDate = LocalDate.now().plusYears(1);
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
        addField(date,0,"date");

        Date change_border = null;
        try {
            change_border = parser.parse("14:20");
        } catch (ParseException e) {
            e.printStackTrace();
            change_border = new Date(11);
        }

        GridPane pane = new GridPane();
        addConstrains(pane, new int[]{100}, new int[]{50,50});
        morning = new RadioButton("Утренняя (08:00-14:00)");
        morning.setFont(GlobalResources.usualFont);
        morning.setSelected(change == null ? true : change.getStartTime().before(change_border));
        morning.selectedProperty().addListener((observableValue, oldValue, newValue) -> evening.setSelected(!newValue));

        evening = new RadioButton("Вечерняя (14:30-20:30)");
        evening.setFont(GlobalResources.usualFont);
        evening.setSelected(change == null ? false : change.getStartTime().after(change_border));
        evening.selectedProperty().addListener((observableValue, oldValue, newValue) -> morning.setSelected(!newValue));

        pane.add(morning,0,0);
        pane.add(evening,1,0);

        put(morning,"morning");
        put(evening, "evening");
        addField(pane,1,"change");

        return scrollPane;
    }


    @Override
    protected void saveRecord(ActionEvent event) {
        java.sql.Date d = new java.sql.Date(Date.from(this.date.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()).getTime());
        int doctor_id = Database.getDoctorId(doctor);
        Change new_data = new Change(doctor_id, d, morning.isSelected() ? start_morning : start_evening, morning.isSelected() ? finish_morning : finish_evening);
        if (change == null) {
            ChangeActions.add(new_data);
        } else {
            if (change.equals(new_data)) {
                closeForm();
            } else {
                ChangeActions.edit(change, new_data);
            }
        }
        closeForm();
    }

    @Override
    protected void closeForm() {
        GlobalResources.closeStage("ChangeForm");
    }
}
