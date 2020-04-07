package com.example.fitness_app;

public class Model {
    private int image;
    private String title;
    private String set;

    public Model(int image, String title, String set) {
        this.image = image;
        this.title = title;
        this.set = set;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSet() {
        return set;
    }

    public void setSet(String set) {
        this.set = set;
    }
}
