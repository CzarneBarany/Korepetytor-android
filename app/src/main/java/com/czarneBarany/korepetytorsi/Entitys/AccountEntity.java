package com.czarneBarany.korepetytorsi.Entitys;

import com.czarneBarany.korepetytorsi.Entitys.models.Role;

import java.util.ArrayList;
import java.util.List;

public class AccountEntity {

    private int accountId;

    private String email;

    private String password;

    private String firstname;

    private String lastname;

    private String city;

    private int age;

    private String phoneNumber;

    private Role role;

    private List<AdvertisementEntity> myAdvertisements = new ArrayList<>();


    public AccountEntity(String email, String password, String firstname, String lastname, String city, String phoneNumber) {
        this.email = email;
        this.password = password;
        this.firstname = firstname;
        this.lastname = lastname;
        this.city = city;
        this.phoneNumber = phoneNumber;
    }
}
