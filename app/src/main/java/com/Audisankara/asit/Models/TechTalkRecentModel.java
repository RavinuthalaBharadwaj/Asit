package com.Audisankara.asit.Models;

public class TechTalkRecentModel {
    private String TechTalkName;

    public TechTalkRecentModel(String techTalkName) {
        TechTalkName = techTalkName;
    }

    public TechTalkRecentModel() {

    }

    public String getTechTalkName() {
        return TechTalkName;
    }

    public void setTechTalkName(String techTalkName) {
        TechTalkName = techTalkName;
    }

}
