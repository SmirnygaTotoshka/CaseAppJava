package ru.smirnygatotoshka.caseapp.UIFactory;

import javafx.event.ActionEvent;

public interface DataChanger {
    void addAction(ActionEvent event);

    void editAction(ActionEvent event);

    void deleteAction(ActionEvent event);
}
