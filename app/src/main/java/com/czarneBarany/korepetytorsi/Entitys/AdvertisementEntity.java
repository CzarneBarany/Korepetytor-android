package com.czarneBarany.korepetytorsi.Entitys;

import java.util.ArrayList;
import java.util.List;

public class AdvertisementEntity {

    private int adId;

    private String title;

    private String description;

    private String category;

    private String levelOfEducation;

    private int pricePerHour;

    private int teacherId;

    private String xcoord;

    private String ycoord;

    List<AccountEntity> listOfStudents = new ArrayList<>();

    public int getAdId() {
        return adId;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getCategory() {
        return category;
    }

    public String getLevelOfEducation() {
        return levelOfEducation;
    }

    public int getPricePerHour() {
        return pricePerHour;
    }

    public int getTeacherId() {
        return teacherId;
    }

    public List<AccountEntity> getListOfStudents() {
        return listOfStudents;
    }

    public String getXcoord() {
        return xcoord;
    }

    public String getYcoord() {
        return ycoord;
    }

    public AdvertisementEntity(String title, String description, String category, String levelOfEducation, int pricePerHour, int teacherId) {
        this.title = title;
        this.description = description;
        this.category = category;
        this.levelOfEducation = levelOfEducation;
        this.pricePerHour = pricePerHour;
        this.teacherId = teacherId;
    }

    public AdvertisementEntity(String title, String description, String category, String levelOfEducation, int pricePerHour, int teacherId, String xCoord, String yCoord) {
        this.title = title;
        this.description = description;
        this.category = category;
        this.levelOfEducation = levelOfEducation;
        this.pricePerHour = pricePerHour;
        this.teacherId = teacherId;
        this.xcoord = xCoord;
        this.ycoord = yCoord;
    }
}
