package com.ozayduman.casclient.repository;

import java.io.Serializable;

/**
 * Created by ozayd on 19.09.2017.
 */
public class Citizen implements Serializable {
    public enum Gender {
        MALE, FEMALE
    }

    private String id;
    private String name;
    private Gender gender;

    public Citizen() {
    }
    public Citizen(String id, String name, Gender gender, int grade) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.grade = grade;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    private int grade;

}
