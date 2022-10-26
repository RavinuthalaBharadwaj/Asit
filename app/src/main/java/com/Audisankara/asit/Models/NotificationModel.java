package com.Audisankara.asit.Models;

public class NotificationModel {
    private String NotificationTitle;

    public NotificationModel(String notificationTitle) {
        NotificationTitle = notificationTitle;
    }

    public String getNotificationTitle() {
        return NotificationTitle;
    }

    public void setNotificationTitle(String notificationTitle) {
        NotificationTitle = notificationTitle;
    }

}
