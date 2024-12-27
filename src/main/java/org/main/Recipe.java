package org.main;

import java.io.Serializable;

public class Recipe implements Serializable {
    private String name;
    private String ingredients;
    private String steps;
    private String imagePath;

    public Recipe(String name, String ingredients, String steps, String imagePath) {
        this.name = name;
        this.ingredients = ingredients;
        this.steps = steps;
        this.imagePath = imagePath;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public String getSteps() {
        return steps;
    }

    public void setSteps(String steps) {
        this.steps = steps;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }
}
