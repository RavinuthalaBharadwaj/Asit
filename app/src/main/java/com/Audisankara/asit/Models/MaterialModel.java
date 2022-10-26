package com.Audisankara.asit.Models;

public class MaterialModel {
    String Name,Description;
    int imageId;

    public MaterialModel(String name, String description, int imageId) {
        Name = name;
        Description = description;
        this.imageId = imageId;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }
}
