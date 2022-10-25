package ru.smirnygatotoshka.caseapp.DataRepresentation;

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;

import java.sql.Date;
import java.util.Objects;

public class Doctor{
        private SimpleStringProperty sirname,name,secondName;
        private SimpleStringProperty sex;
        private SimpleObjectProperty<Date> dob;
        private SimpleStringProperty position;
        private SimpleStringProperty speciality;
        private SimpleStringProperty department;
        private SimpleStringProperty telephone;



        public Doctor(String sirname, String name, String secondName, String sex, Date dob, String position, String speciality, String department,String telephone) {
            this.sirname = new SimpleStringProperty(sirname);
            this.name = new SimpleStringProperty(name);
            this.secondName = new SimpleStringProperty(secondName);
            this.sex = new SimpleStringProperty(sex);
            this.dob = new SimpleObjectProperty(dob);
            this.position = new SimpleStringProperty(position);
            this.speciality = new SimpleStringProperty(speciality);
            this.department = new SimpleStringProperty(department);
            this.telephone = new SimpleStringProperty(telephone);
        }

        public Doctor() {

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


        public String getPosition() {
            return  position.get();
        }

        public void setPosition(String position) {
            this.position.set(position);
        }

        public String getSpeciality() {
            return speciality.get();
        }

        public void setSpeciality(String speciality) {
            this.speciality.set(speciality);
        }


        public String getDepartment() {
            return  department.get();
        }

        public void setDepartment(String department) {
            this.department.set(department);
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

        public SimpleStringProperty positionProperty(){
            return position;
        }

        public SimpleStringProperty departmentProperty(){
            return department;
        }

        public SimpleStringProperty specialityProperty(){
            return speciality;
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
            Doctor doctor = (Doctor) o;
            return sirname.get().contentEquals(doctor.sirname.get()) && name.get().contentEquals(doctor.name.get()) &&
                    secondName.get().contentEquals(doctor.secondName.get()) && sex.get().contentEquals(doctor.sex.get()) &&
                    dob.get().compareTo(doctor.dob.get()) == 0  &&
                    position.get().contentEquals(doctor.position.get()) && speciality.get().contentEquals(doctor.speciality.get()) &&
                    department.get().contentEquals(doctor.department.get()) && telephone.get().contentEquals(doctor.telephone.get());
        }

        @Override
        public int hashCode() {
            return Objects.hash(sirname.get(), name.get(), secondName.get(), sex.get(), dob.get(), position.get(), speciality.get(), department.get(), telephone.get());
        }
}
