package com.Audisankara.asit.Models;

public class HomeScreenViewPagerModel {
    private String UpdateImage;
    private String Title,Arthur,UpdateTime,Priority,ReadTime,NewsType,Description;

    public HomeScreenViewPagerModel() {

    }

    public HomeScreenViewPagerModel(String UpdateImage,String title, String arthur, String updateTime, String priority, String readTime, String newsType,String Description) {
        Title = title;
        Arthur = arthur;
        UpdateTime = updateTime;
        Priority = priority;
        ReadTime = readTime;
        this.UpdateImage = UpdateImage;
        NewsType = newsType;
        this.Description = Description;
    }


    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getUpdateImage() {
        return UpdateImage;
    }

    public void setUpdateImage(String updateImage) {
        UpdateImage = updateImage;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getArthur() {
        return Arthur;
    }

    public void setArthur(String arthur) {
        Arthur = arthur;
    }

    public String getUpdateTime() {
        return UpdateTime;
    }

    public void setUpdateTime(String updateTime) {
        UpdateTime = updateTime;
    }

    public String getPriority() {
        return Priority;
    }

    public void setPriority(String priority) {
        Priority = priority;
    }

    public String getReadTime() {
        return ReadTime;
    }

    public void setReadTime(String readTime) {
        ReadTime = readTime;
    }

    public String getNewsType() {
        return NewsType;
    }

    public void setNewsType(String newsType) {
        NewsType = newsType;
    }
}
