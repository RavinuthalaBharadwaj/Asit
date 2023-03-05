package com.Audisankara.asit.Models;

public class NotificationModel {
    private String Title,Description,Date,Month;

    public NotificationModel(String title, String description, String date, String month) {
        Title = title;
        Description = description;
        Date = date;
        Month = month;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getMonth() {
        return Month;
    }

    public void setMonth(String month) {
        Month = month;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public NotificationModel() {
    }
}
