package org.tsofen.ourstory.model.api;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class User implements Serializable
{

@SerializedName("user_id")
@Expose
private long userId;
@SerializedName("email")
@Expose
private String email;
@SerializedName("first_name")
@Expose
private String firstName;
@SerializedName("last_name")
@Expose
private String lastName;
@SerializedName("password")
@Expose
private String password;
@SerializedName("gender")
@Expose
private String gender;
@SerializedName("date_of_birth")
@Expose
private String dateOfBirth;
@SerializedName("date_of_sign_up")
@Expose
private String dateOfSignUp;
@SerializedName("date_of_last_sign_in")
@Expose
private String dateOfLastSignIn;
@SerializedName("state")
@Expose
private String state;
@SerializedName("city")
@Expose
private String  city;
@SerializedName("status")
@Expose
private Boolean status;
@SerializedName("profile_picture")
@Expose
private String profilePicture;
private final static long serialVersionUID = -5085407755254667737L;

/**
* No args constructor for use in serialization
*
*/
public User() {
}

/**
*
* @param dateOfBirth
* @param lastName
* @param dateOfSignUp
* @param status
* @param profilePicture
* @param state
* @param password
* @param city
* @param email
* @param userId
* @param gender
* @param firstName
* @param dateOfLastSignIn
*/
public User(long userId, String email, String firstName, String lastName, String password, String gender, String dateOfBirth, String dateOfSignUp, String dateOfLastSignIn, String state, String city, Boolean status, String profilePicture) {
super();
this.userId = userId;
this.email = email;
this.firstName = firstName;
this.lastName = lastName;
this.password = password;
this.gender = gender;
this.dateOfBirth = dateOfBirth;
this.dateOfSignUp = dateOfSignUp;
this.dateOfLastSignIn = dateOfLastSignIn;
this.state = state;
this.city = city;
this.status = status;
this.profilePicture = profilePicture;
}

public long getUserId() {
return userId;
}

public void setUserId(long userId) {
this.userId = userId;
}

public String getEmail() {
return email;
}

public void setEmail(String email) {
this.email = email;
}

public String getFirstName() {
return firstName;
}

public void setFirstName(String firstName) {
this.firstName = firstName;
}

public String getLastName() {
return lastName;
}

public void setLastName(String lastName) {
this.lastName = lastName;
}

public String getPassword() {
return password;
}

public void setPassword(String password) {
this.password = password;
}

public String getGender() {
return gender;
}

public void setGender(String gender) {
this.gender = gender;
}

public String getDateOfBirth() {
return dateOfBirth;
}

public void setDateOfBirth(String dateOfBirth) {
this.dateOfBirth = dateOfBirth;
}

public String getDateOfSignUp() {
return dateOfSignUp;
}

public void setDateOfSignUp(String dateOfSignUp) {
this.dateOfSignUp = dateOfSignUp;
}

public String getDateOfLastSignIn() {
return dateOfLastSignIn;
}

public void setDateOfLastSignIn(String dateOfLastSignIn) {
this.dateOfLastSignIn = dateOfLastSignIn;
}

public String getState() {
return state;
}

public void setState(String state) {
this.state = state;
}

public String getCity() {
return city;
}

public void setCity(String city) {
this.city = city;
}

public Boolean getStatus() {
return status;
}

public void setStatus(Boolean status) {
this.status = status;
}

public String getProfilePicture() {
return profilePicture;
}

public void setProfilePicture(String profilePicture) {
this.profilePicture = profilePicture;
}

}