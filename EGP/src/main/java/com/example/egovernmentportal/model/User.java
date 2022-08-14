package com.example.egovernmentportal.model;


import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name="USER")
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;

    private String userName;
    private String email;
    private String identityId;
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private LocalDate birthday;
    private String address;
    private String city;
    private String zipcode;
    private String contry;

    private String image;

    public User() {
    }

    public User(String firstName, String lastName, String userName, String email, String identityId, LocalDate birthday, String address, String city, String zipcode, String contry) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.email = email;
        this.identityId = identityId;
        this.birthday = birthday;
        this.address = address;
        this.city = city;
        this.zipcode = zipcode;
        this.contry = contry;
    }

    public User(String firstName, String lastName, String userName, String email, String identityId) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.email = email;
        this.identityId = identityId;
    }

    public String toString() {
        return firstName + " " + lastName;
    }
}
