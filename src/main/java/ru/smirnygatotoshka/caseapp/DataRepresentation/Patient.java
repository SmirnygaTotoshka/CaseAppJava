package ru.smirnygatotoshka.caseapp.DataRepresentation;

import javafx.beans.property.*;

import java.sql.Date;
import java.util.Objects;

public class Patient {
    private SimpleStringProperty sirname,name,secondName;
    private SimpleStringProperty sex;
    private SimpleObjectProperty<Date> dob;
    private SimpleStringProperty priviledge;
    private SimpleStringProperty employment;
    private SimpleStringProperty workplace;
    private SimpleStringProperty passport, police;
    private SimpleStringProperty snils;
    private SimpleStringProperty familyStatus;
    private SimpleStringProperty telephone;



    public Patient(String sirname, String name, String secondName, String sex, Date dob, String priviledge, String employment, String workplace, String passport, String police, String snils, String familyStatus, String telephone) {
        this.sirname = new SimpleStringProperty(sirname);
        this.name = new SimpleStringProperty(name);
        this.secondName = new SimpleStringProperty(secondName);
        this.sex = new SimpleStringProperty(sex);
        this.dob = new SimpleObjectProperty(dob);
        this.priviledge = new SimpleStringProperty(priviledge);
        this.employment = new SimpleStringProperty(employment);
        this.workplace = new SimpleStringProperty(workplace);
        this.passport = new SimpleStringProperty(passport);
        this.police = new SimpleStringProperty(police);
        this.snils = new SimpleStringProperty(snils);
        this.familyStatus = new SimpleStringProperty(familyStatus);
        this.telephone = new SimpleStringProperty(telephone);
    }

    public Patient() {

    }


    public String getSirname() {
        return sirname.get();
    }

    public void setSirname(String sirname) {
        this.sirname.set(sirname);
    }

    public String getName() {
        return name.get();
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public String getSecondName() {
        return secondName.get();
    }

    public void setSecondName(String secondName) {
        this.secondName.set(secondName);
    }

    public String getSex() {
        return sex.get();
    }

    public void setSex(String sex) {
        this.sex.set(sex);
    }

    public Date getDob() {
        return dob.get();
    }

    public void setDob(Date dob) {
        this.dob.set(dob);
    }

    public String getPriviledge() {
        return priviledge.get();
    }

    public void setPriviledge(String priviledge) {
        this.priviledge.set(priviledge);
    }

    public String getEmployment() {
        return  employment.get();
    }

    public void setEmployment(String employment) {
        this.employment.set(employment);
    }

    public String getWorkplace() {
        return workplace.get();
    }

    public void setWorkplace(String workplace) {
        this.workplace.set(workplace);
    }

    public String getPassport() {
        return passport.get();
    }

    public void setPassport(String passport) {
        this.passport.set(passport);
    }

    public String getPolice() {
        return police.get();
    }

    public void setPolice(String police) {
        this.police.set(police);
    }

    public String getSnils() {
        return snils.get();
    }

    public void setSnils(String snils) {
        this.snils.set(snils);
    }

    public String getFamilyStatus() {
        return  familyStatus.get();
    }

    public void setFamilyStatus(String familyStatus) {
        this.familyStatus.set(familyStatus);
    }

    public String getTelephone() {
        return telephone.get();
    }

    public void setTelephone(String telephone) {
        this.telephone.set(telephone);
    }

    public SimpleStringProperty sirnameProperty(){
        return sirname;
    }

    public SimpleStringProperty nameProperty(){
        return name;
    }

    public SimpleStringProperty secondNameProperty(){
        return secondName;
    }

    public SimpleStringProperty sexProperty(){
        return sex;
    }

    public SimpleStringProperty priviledgeProperty(){
        return priviledge;
    }

    public SimpleStringProperty employmentProperty(){
        return employment;
    }

    public SimpleStringProperty passportProperty(){
        return passport;
    }

    public SimpleStringProperty policeProperty(){
        return police;
    }

    public SimpleStringProperty familyStatusProperty(){
        return familyStatus;
    }

    public SimpleStringProperty workplaceProperty(){
        return workplace;
    }

    public SimpleStringProperty snilsProperty(){
        return snils;
    }

    public SimpleStringProperty telephoneProperty(){
        return telephone;
    }

    public SimpleObjectProperty<Date> dobProperty(){
        return dob;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Patient patient = (Patient) o;
        return sirname.get().contentEquals(patient.sirname.get()) && name.get().contentEquals(patient.name.get()) &&
                secondName.get().contentEquals(patient.secondName.get()) && sex.get().contentEquals(patient.sex.get()) &&
                dob.get().compareTo(patient.dob.get()) == 0 && priviledge.get().contentEquals(patient.priviledge.get()) &&
                employment.get().contentEquals(patient.employment.get()) && workplace.get().contentEquals(patient.workplace.get()) &&
                passport.get().contentEquals(patient.passport.get()) && police.get().contentEquals(patient.police.get()) &&
                snils.get().contentEquals(patient.snils.get()) && familyStatus.get().contentEquals(patient.familyStatus.get()) && telephone.get().contentEquals(patient.telephone.get());
    }

    @Override
    public int hashCode() {
        return Objects.hash(sirname.get(), name.get(), secondName.get(), sex.get(), dob.get(), priviledge.get(), employment.get(), workplace.get(), passport.get(), police, snils, familyStatus, telephone);
    }
}
