package com.example.lenovo.baodingchejian;

public class ProjectItemForCheJian {
    private String name;
    private int imageId;

    public ProjectItemForCheJian (String name, int imageId) {
        this.name = name;
        this.imageId = imageId;
    }

    public String getName() {
        return name;
    }

    public int getImageId() {
        return imageId;
    }
}
