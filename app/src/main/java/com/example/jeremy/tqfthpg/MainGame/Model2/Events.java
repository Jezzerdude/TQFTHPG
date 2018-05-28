package com.example.jeremy.tqfthpg.MainGame.Model2;

import javax.annotation.Nullable;

import io.realm.RealmObject;

public class Events extends RealmObject {
    private int EventId;
    private String Name;
    private String EventType;
    private String ImgURL;
    private String Description;
    private String Act1;
    @Nullable
    private String Act2;
    @Nullable
    private String Act3;
    private int LeadChar;
    private String eventResult;
    @Nullable
    private String PassOrFail;

    public Events() {
    }

    public Events(int eventId, String name, String eventType, String imgURL, String description, String act1, int leadChar, String eventResult) {
        EventId = eventId;
        Name = name;
        EventType = eventType;
        ImgURL = imgURL;
        Description = description;
        Act1 = act1;
        LeadChar = leadChar;
        this.eventResult = eventResult;
    }

    public Events(int eventId, String name, String eventType, String imgURL, String description, String act1, @android.support.annotation.Nullable String act2, @android.support.annotation.Nullable String act3, int leadChar, String eventResult, @android.support.annotation.Nullable String passOrFail) {
        EventId = eventId;
        Name = name;
        EventType = eventType;
        ImgURL = imgURL;
        Description = description;
        Act1 = act1;
        Act2 = act2;
        Act3 = act3;
        LeadChar = leadChar;
        this.eventResult = eventResult;
        PassOrFail = passOrFail;
    }

    public int getEventId() {
        return EventId;
    }

    public void setEventId(int eventId) {
        EventId = eventId;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getEventType() {
        return EventType;
    }

    public void setEventType(String eventType) {
        EventType = eventType;
    }

    public String getImgURL() {
        return ImgURL;
    }

    public void setImgURL(String imgURL) {
        ImgURL = imgURL;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getAct1() {
        return Act1;
    }

    public void setAct1(String act1) {
        Act1 = act1;
    }

    @Nullable
    public String getAct2() {
        return Act2;
    }

    public void setAct2(@Nullable String act2) {
        Act2 = act2;
    }

    @Nullable
    public String getAct3() {
        return Act3;
    }

    public void setAct3(@Nullable String act3) {
        Act3 = act3;
    }

    public int getLeadChar() {
        return LeadChar;
    }

    public void setLeadChar(int leadChar) {
        LeadChar = leadChar;
    }

    public String getEventResult() {
        return eventResult;
    }

    public void setEventResult(String eventResult) {
        this.eventResult = eventResult;
    }

    @Nullable
    public String getPassOrFail() {
        return PassOrFail;
    }

    public void setPassOrFail(@Nullable String passOrFail) {
        PassOrFail = passOrFail;
    }
}
