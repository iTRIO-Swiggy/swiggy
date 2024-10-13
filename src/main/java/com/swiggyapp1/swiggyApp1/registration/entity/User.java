package com.swiggyapp1.swiggyApp1.registration.entity;

import jakarta.persistence.*;

import java.math.BigInteger;

@Entity
@Table(name = "Users")
public class User {
  @Id @GeneratedValue private BigInteger id;
  @Column private String fname;
  @Column private String lname;
  @Column private String email;
  @Column private String username;
  @Column private String password;
  @Column private String address;
  @Column private String mobilenumber;
  @Column private String usertype;
  @Column private String profilepicture;
  @Column private String provider;

  public BigInteger getId() {
    return id;
  }

  public void setId(BigInteger id) {
    this.id = id;
  }

  public String getFname() {
    return fname;
  }

  public void setFname(String fname) {
    this.fname = fname;
  }

  public String getLname() {
    return lname;
  }

  public void setLname(String lname) {
    this.lname = lname;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public String getMobilenumber() {
    return mobilenumber;
  }

  public void setMobilenumber(String mobilenumber) {
    this.mobilenumber = mobilenumber;
  }

  public String getUsertype() {
    return usertype;
  }

  public void setUsertype(String usertype) {
    this.usertype = usertype;
  }

  public String getProfilepicture() {
    return profilepicture;
  }

  public void setProfilepicture(String profilepicture) {
    this.profilepicture = profilepicture;
  }

  public String getProvider() {
    return provider;
  }

  public void setProvider(String provider) {
    this.provider = provider;
  }
}
