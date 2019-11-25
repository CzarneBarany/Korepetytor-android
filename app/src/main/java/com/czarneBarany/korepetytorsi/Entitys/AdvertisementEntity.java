package com.czarneBarany.korepetytorsi.Entitys;

import java.util.ArrayList;
import java.util.List;

public class AdvertisementEntity {

    private int adId;

    private String title;

    private String description;

    private String adCategory;

    private String adLevelOfEducation;

    private int pricePerHour;

    private AccountEntity teacher;


    List<AccountEntity> listOfStudents = new ArrayList<>();

    public AdvertisementEntity(String title, String description, String adCategory, String adLevelOfEducation, int pricePerHour) {
        this.title = title;
        this.description = description;
        this.adCategory = adCategory;
        this.adLevelOfEducation = adLevelOfEducation;
        this.pricePerHour = pricePerHour;
    }
}
