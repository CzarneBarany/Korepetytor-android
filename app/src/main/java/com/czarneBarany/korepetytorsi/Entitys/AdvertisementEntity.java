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

    private AccountEntity teacher;


    List<AccountEntity> listOfStudents = new ArrayList<>();



    public AdvertisementEntity(String title, String description, String adCategory, String levelOfEducation, int pricePerHour) {
        this.title = title;
        this.description = description;
        this.category = category;
        this.levelOfEducation = levelOfEducation;
        this.pricePerHour = pricePerHour;
    }
}
