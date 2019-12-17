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

    public int getAccountId() {
        return accountId;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getCity() {
        return city;
    }

    public int getAge() {
        return age;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public Role getRole() {
        return role;
    }

    public List<AdvertisementEntity> getMyAdvertisements() {
        return myAdvertisements;
    }

    public AccountEntity(String email, String password, String firstname, String lastname, String city, String phoneNumber) {
        this.email = email;
        this.password = password;
        this.firstname = firstname;
        this.lastname = lastname;
        this.city = city;
        this.phoneNumber = phoneNumber;
    }
}
