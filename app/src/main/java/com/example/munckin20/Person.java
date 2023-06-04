package com.example.munckin20;

public class Person {
    String Name;
    String Gender;
    String Lvl;
    String Strong;

    public Person() {
    }
    public Person (String Name, String Gender, String Lvl, String Strong) {
        this.Name = Name;
        this.Gender = Gender;
        this.Lvl = Lvl;
        this.Strong = Strong;

    }

    public String getName() {
        return Name;
    }
    public String getGender() {
        return Gender;
    }
    public String getLvl() {
        return Lvl;
    }
    public String getStrong() {
        return Strong;
    }

    public void setName (String Name) {
        this.Name = Name;
    }
    public void setGender (String Gender) {
        this.Gender = Gender;
    }
    public void setLvl (String Lvl) {
        this.Lvl = Lvl;
    }
    public void setStrong (String Strong) {
        this.Strong = Strong;
    }
}

