package com.campusdual.ejercicio5;

import com.campusdual.ejercicio5.enums.Days;

import java.util.HashMap;
import java.util.Map;

public class Customer {

    //Atributos
    private String name;
    private String surname;
    private Integer weight;
    private Integer height;
    private Integer age;
    private String gender;
    private Map<Days, Diet> costumerDietsList;

    public Customer() {
        costumerDietsList = new HashMap<>();
    }
    public Customer(String name, String surname, Integer weight, Integer height, Integer age, String gender) {
        this.name = name;
        this.surname = surname;
        this.weight = weight;
        this.height = height;
        this.age = age;
        this.gender = gender;
        costumerDietsList = new HashMap<>();
    }

    public Map<Days, Diet> getCostumerDietsList() {
        return costumerDietsList;
    }

    public void setCostumerDietsList(Map<Days, Diet> costumerDietsList) {
        this.costumerDietsList = costumerDietsList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getGender() {
        if(gender.equalsIgnoreCase("h")){
            return "Hombre";
        }else{
            return "Mujer";
        }
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public static Boolean validateGender(String gender){
        if(gender.equalsIgnoreCase("h") || gender.equalsIgnoreCase("m")){
            return true;
        }else{
            return false;
        }
    }
    //* Método para agregar una dieta para un día específico
    public void addDietForDay(Days day, Diet diet) {
        costumerDietsList.put(day, diet);
    }

    //* Método para obtener la dieta para un día específico
    public Diet getDietForDay(Days day) {
        return costumerDietsList.get(day);
    }
}
