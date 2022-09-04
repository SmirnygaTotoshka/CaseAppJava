package ru.smirnygatotoshka.caseapp.UIFactory;

import javafx.scene.Parent;

import java.util.HashMap;

public abstract class UIFactory {

    protected String id_prefix;

    protected HashMap<String, Parent> elements = new HashMap<>();

    public UIFactory(String id_prefix) {
        this.id_prefix = id_prefix;
    }

    public abstract Parent create();



    public Parent getElement(String id){
        return elements.get(id);
    }

    protected void put(Parent element, String name){
        String id = id_prefix + "_" + name;
        element.setId(id);
        elements.put(id, element);
    }

    protected Parent get(String name){
        return elements.get(id_prefix + "_" + name);
    }

}
