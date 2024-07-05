package com.VIGNESH.logindatabase.model;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Document(collection = "userinfo")
public class UserInfo {

    @Id
    private String id;
    private String street;
    private String city;
    private String state;
    private String zip;

    @JsonIgnore
    @OneToOne(mappedBy = "userInfo")
    private User user;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public void setUser(User user) {
        this.user = user;
    }

    // Getter method for User
    public User getUser() {
        return user;
    }
}
