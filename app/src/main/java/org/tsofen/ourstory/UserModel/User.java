package org.tsofen.ourstory.UserModel;

public class User {

    public static long UserId = 0;
    private long mId;
    private String mFirstName;
    private String mLastName;
    private String mPassword;
    private String mEmail;
    private String mProfilePicture;
    private String mGender;

    //temp//later to be changed to Date Type
    private String mDateOfBirth;
    private String mDateOfRegistration;
    private String mDateOfLastSignIn;

    //private Date mDateOfBirth;
    // private Date mDateOfRegistration;
    // private Date mDateOfLastSignIn;
    private String mState;
    private String mCity;


    public User(String FirstName, String LastName, String Password,
                String Email, String ProfilePicture, String Gender, String DateOfBirth,
                String DateOfRegistration, String DateOfLastSignIn, String State, String City) {

        UserId++;
        this.mId = UserId;
        this.mFirstName = FirstName;
        this.mLastName = LastName;
        this.mPassword = Password;
        this.mEmail = Email;
        this.mProfilePicture = ProfilePicture;
        this.mGender = Gender;

        this.mDateOfBirth = DateOfBirth;
        this.mDateOfRegistration = DateOfRegistration;
        this.mDateOfLastSignIn = DateOfLastSignIn;

        //this.mDateOfBirth = DateOfBirth;
        //this.mDateOfRegistration = DateOfRegistration;
        //this.mDateOfLastSignIn = DateOfLastSignIn;
        this.mState = State;
        this.mCity = City;
    }

    public long getmId() {
        return mId;
    }

    public String getmFirstName() {
        return mFirstName;
    }

    public void setmFirstName(String mFirstName) {
        this.mFirstName = mFirstName;
    }

    public String getmLastName() {
        return mLastName;
    }

    public void setmLastName(String mLastName) {
        this.mLastName = mLastName;
    }

    public String getmPassword() {
        return mPassword;
    }

    public void setmPassword(String mPassword) {
        this.mPassword = mPassword;
    }

    public String getmEmail() {
        return mEmail;
    }

    public void setmEmail(String mEmail) {
        this.mEmail = mEmail;
    }

    public String getmProfilePicture() {
        return mProfilePicture;
    }

    public void setmProfilePicture(String mProfilePicture) {
        this.mProfilePicture = mProfilePicture;
    }

    public String getmGender() {
        return mGender;
    }

    public void setmGender(String mGender) {
        this.mGender = mGender;
    }

    public String getmDateOfBirth() {
        return mDateOfBirth;
    }

    public void setmDateOfBirth(String mDateOfBirth) {
        this.mDateOfBirth = mDateOfBirth;
    }

    public String getmDateOfRegistration() {
        return mDateOfRegistration;
    }

    public void setmDateOfRegistration(String mDateOfRegistration) {
        this.mDateOfRegistration = mDateOfRegistration;
    }

    public String getmDateOfLastSignIn() {
        return mDateOfLastSignIn;
    }

    public void setmDateOfLastSignIn(String mDateOfLastSignIn) {
        this.mDateOfLastSignIn = mDateOfLastSignIn;
    }

    public String getmState() {
        return mState;
    }

    public void setmState(String mState) {
        this.mState = mState;
    }

    public String getmCity() {
        return mCity;
    }

    public void setmCity(String mCity) {
        this.mCity = mCity;
    }


}


