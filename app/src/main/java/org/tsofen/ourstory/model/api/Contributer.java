package org.tsofen.ourstory.model.api;
import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Contributer implements Serializable {

    private final static long serialVersionUID = -6492193173105400755L;
    @SerializedName("user_id")
    @Expose
    private Integer userId;
    @SerializedName("email")
    @Expose
    private Object email;
    @SerializedName("first_name")
    @Expose
    private Object firstName;
    @SerializedName("last_name")
    @Expose
    private Object lastName;
    @SerializedName("password")
    @Expose
    private Object password;
    @SerializedName("gender")
    @Expose
    private Object gender;
    @SerializedName("date_of_birth")
    @Expose
    private Object dateOfBirth;
    @SerializedName("date_of_sign_up")
    @Expose
    private Object dateOfSignUp;
    @SerializedName("date_of_last_sign_in")
    @Expose
    private Object dateOfLastSignIn;
    @SerializedName("state")
    @Expose
    private Object state;
    @SerializedName("city")
    @Expose
    private Object city;
    @SerializedName("status")
    @Expose
    private Boolean status;
    @SerializedName("profile_picture")
    @Expose
    private Object profilePicture;

    /**
     * No args constructor for use in serialization
     */
    public Contributer() {
    }

    /**
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
    public Contributer(Integer userId, Object email, Object firstName, Object lastName, Object password, Object gender, Object dateOfBirth, Object dateOfSignUp, Object dateOfLastSignIn, Object state, Object city, Boolean status, Object profilePicture) {
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

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Object getEmail() {
        return email;
    }

    public void setEmail(Object email) {
        this.email = email;
    }

    public Object getFirstName() {
        return firstName;
    }

    public void setFirstName(Object firstName) {
        this.firstName = firstName;
    }

    public Object getLastName() {
        return lastName;
    }

    public void setLastName(Object lastName) {
        this.lastName = lastName;
    }

    public Object getPassword() {
        return password;
    }

    public void setPassword(Object password) {
        this.password = password;
    }

    public Object getGender() {
        return gender;
    }

    public void setGender(Object gender) {
        this.gender = gender;
    }

    public Object getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Object dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Object getDateOfSignUp() {
        return dateOfSignUp;
    }

    public void setDateOfSignUp(Object dateOfSignUp) {
        this.dateOfSignUp = dateOfSignUp;
    }

    public Object getDateOfLastSignIn() {
        return dateOfLastSignIn;
    }

    public void setDateOfLastSignIn(Object dateOfLastSignIn) {
        this.dateOfLastSignIn = dateOfLastSignIn;
    }

    public Object getState() {
        return state;
    }

    public void setState(Object state) {
        this.state = state;
    }

    public Object getCity() {
        return city;
    }

    public void setCity(Object city) {
        this.city = city;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Object getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(Object profilePicture) {
        this.profilePicture = profilePicture;
    }

}