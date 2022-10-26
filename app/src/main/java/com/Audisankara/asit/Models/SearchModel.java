package com.Audisankara.asit.Models;

public class SearchModel {
    String Name,Description,Category;
    int CategoryCardColor;

    public SearchModel(String name, String description,String category,int categoryCardColor) {
        Name = name;
        Description = description;
        Category = category;
        CategoryCardColor = categoryCardColor;
    }

    public String getCategory() {
        return Category;
    }

    public void setCategory(String category) {
        Category = category;
    }

    public int getCategoryCardColor() {
        return CategoryCardColor;
    }

    public void setCategoryCardColor(int categoryCardColor) {
        CategoryCardColor = categoryCardColor;
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

}
