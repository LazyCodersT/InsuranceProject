package Model.Entities;

import java.sql.Date;

public class User {
    private Integer  id;
    private String   username;
    private String   password;
    private String   firstname;
    private String   lastname;
    private Date     birthdate;
    private String   fatherName;
    private String   nationalCode;
    private String   idNumber;
    private String   nationality;
    private String   city;
    private String   phoneNumber;
    private String   homeNumber;
    private UserType type;

    public User() {}
    public Integer getId() {
        return id;
    }

    public User setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public User setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public User setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getFirstname() {
        return firstname;
    }

    public User setFirstname(String firstname) {
        this.firstname = firstname;
        return this;
    }

    public String getLastname() {
        return lastname;
    }

    public User setLastname(String lastname) {
        this.lastname = lastname;
        return this;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public User setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
        return this;
    }

    public String getFatherName() {
        return fatherName;
    }

    public User setFatherName(String fatherName) {
        this.fatherName = fatherName;
        return this;
    }

    public String getNationalCode() {
        return nationalCode;
    }

    public User setNationalCode(String nationalCode) {
        this.nationalCode = nationalCode;
        return this;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public User setIdNumber(String idNumber) {
        this.idNumber = idNumber;
        return this;
    }

    public String getNationality() {
        return nationality;
    }

    public User setNationality(String nationality) {
        this.nationality = nationality;
        return this;
    }

    public String getCity() {
        return city;
    }

    public User setCity(String city) {
        this.city = city;
        return this;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public User setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public String getHomeNumber() {
        return homeNumber;
    }

    public User setHomeNumber(String homeNumber) {
        this.homeNumber = homeNumber;
        return this;
    }

    public UserType getType() {
        return type;
    }

    public User setType(UserType type) {
        this.type = type;
        return this;
    }
}
