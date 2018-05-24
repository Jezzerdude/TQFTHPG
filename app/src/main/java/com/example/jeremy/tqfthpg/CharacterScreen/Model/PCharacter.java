package com.example.jeremy.tqfthpg.CharacterScreen.Model;

import io.realm.RealmObject;

public class PCharacter extends RealmObject {
    private int Id;
    private String Race;
    private String Charclass;
    private String Firstname;
    private String Lastname;
    private String Fullname;
    private String Weakness;
    private String Description;

    public PCharacter() {
    }

    public PCharacter(int id, String race, String charclass, String firstname, String lastname, String fullname, String weakness, String description) {
        Id = id;
        Race = race;
        Charclass = charclass;
        Firstname = firstname;
        Lastname = lastname;
        Fullname = fullname;
        Weakness = weakness;
        Description = description;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getRace() {
        return Race;
    }

    public void setRace(String race) {
        Race = race;
    }

    public String getCharclass() {
        return Charclass;
    }

    public void setCharclass(String charclass) {
        Charclass = charclass;
    }

    public String getFirstname() {
        return Firstname;
    }

    public void setFirstname(String firstname) {
        Firstname = firstname;
    }

    public String getLastname() {
        return Lastname;
    }

    public void setLastname(String lastname) {
        Lastname = lastname;
    }

    public String getFullname() {
        return Fullname;
    }

    public void setFullname(String fullname) {
        Fullname = fullname;
    }

    public String getWeakness() {
        return Weakness;
    }

    public void setWeakness(String weakness) {
        Weakness = weakness;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }
}

