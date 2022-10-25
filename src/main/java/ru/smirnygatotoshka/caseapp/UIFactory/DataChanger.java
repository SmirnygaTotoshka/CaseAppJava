package ru.smirnygatotoshka.caseapp.UIFactory;

import javafx.event.ActionEvent;

/**
 *
 * @author stotoshka
 * Методы для работы с формами, содержащие в себе класс @see ControlForm
 * */
public interface DataChanger {
    void addAction(ActionEvent event);

    void editAction(ActionEvent event);

    void deleteAction(ActionEvent event);
}
