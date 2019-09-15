package org.tsofen.ourstory.UserModel;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;

import org.tsofen.ourstory.R;
import org.tsofen.ourstory.model.api.User;
import org.tsofen.ourstory.web.OurStoryService;
import org.tsofen.ourstory.web.WebFactory;

import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class LogIn extends AppCompatActivity {
    public static SharedPreferences mPrefs;
    public String emailString;
    public String firstNameString;
    public String lastNameString;
    public String passwordString;
    public String stateString;
    public String cityString;
    public String genderString = "Null";
    public String profilePictureString = "#FTGFHFJJY";
    public Date dateOfBirth;
    public String dateOfRegistration = "0/0/00";
    public String dateOfLastSignIn = "0/0/00";
    EditText email;
    EditText password;
    TextView passErr;
    TextView emailErr;
    int flag1 = 1;
    int flag2 = 1;
    String userPass;
    Long userId;
    int year;
    int month;
    int day;
org.tsofen.ourstory.model.api.User myUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        email = findViewById(R.id.showEmail);
        password = findViewById(R.id.Password);
        passErr = findViewById(R.id.PassError);
        emailErr = findViewById(R.id.emailErr);
        Intent currIntent = getIntent();
        emailString = currIntent.getStringExtra("email");
        firstNameString = currIntent.getStringExtra("first_name");
        lastNameString = currIntent.getStringExtra("last_name");
        passwordString = currIntent.getStringExtra("password");
        stateString = currIntent.getStringExtra("state");
        cityString = currIntent.getStringExtra("city");
       month=currIntent.getIntExtra("month",0);
       day=currIntent.getIntExtra("day",0);
       year=currIntent.getIntExtra("year",0);
       dateOfBirth=new Date(year,month,day);
        genderString = currIntent.getStringExtra("gender");
        profilePictureString = currIntent.getStringExtra("profilePicture");
        OurStoryService saveUser = WebFactory.getService();
        User  newUser= new User();
        newUser.setFirstName(firstNameString);
        newUser.setLastName(lastNameString);
        newUser.setProfilePicture(profilePictureString);
        newUser.setDateOfBirth(dateOfBirth);
        newUser.setEmail(emailString);
        newUser.setPassword(passwordString);
        newUser.setCity(cityString);
        newUser.setState(stateString);
        newUser.setGender(genderString);
        saveUser.CreateUser(newUser).enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                Toast.makeText(LogIn.this,"UserSaved Check Database",Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast.makeText(LogIn.this,"Saving user Failed",Toast.LENGTH_LONG).show();

            }
        });




    }


    public void goLogin(View view) {
        //int index = 0;
        // String userIn;

        String inputEmail = String.valueOf(email.getText());
        String inputPassword = String.valueOf(password.getText());

        OurStoryService ss = WebFactory.getService();
        ss.GetUserByEmail(inputEmail).enqueue(new Callback<org.tsofen.ourstory.model.api.User>() {
            @Override
            public void onResponse(Call<org.tsofen.ourstory.model.api.User> call, Response<org.tsofen.ourstory.model.api.User> response) {
                myUser = response.body();
                Toast.makeText(getApplicationContext(), myUser.getUserId() + "",
                        Toast.LENGTH_SHORT).show();
                userId = myUser.getUserId();
                if (myUser.getEmail() == null) {
                    emailErr.setText("This email address is invalid. Please tru a different one.");

                } else {

                    userPass = myUser.getPassword();
                    if (userPass.equals(inputPassword)) {
                        UserStatusCheck.setUserStatus("not a visitor"); //TODO move the user id to the home page and then move it to create story intent (move it under name=("userId"))
                        //
                        Intent signInDone = new Intent(getApplicationContext(), AppHomePage.class);
                        signInDone.putExtra("email", inputEmail);
                        signInDone.putExtra("userId", userId);
                        signInDone.putExtra("user", myUser);
                        //signInDone.putExtra("index", index);
                        mPrefs= getPreferences(MODE_PRIVATE);
                        SharedPreferences.Editor prefsEditor = mPrefs.edit();
                        Gson gson = new Gson();
                        String json = gson.toJson(myUser);
                        prefsEditor.putString("myUser", json);
                        prefsEditor.commit();
                        startActivity(signInDone);
                    } else {
                        passErr.setText("Incorrect password!!");
                    }


                }
            }

            @Override
            public void onFailure(Call<org.tsofen.ourstory.model.api.User> call, Throwable t) {
                emailErr.setText("Failed");
            }


        });


    }
    public void goRegist(View view) {
        Intent registNow = new Intent(this, RegistrationPage1.class);
        startActivity(registNow);
    }

    public void goChangePassword(View view) {


    }

    public void closeActivity(View view) {
        Intent home = new Intent(this, AppHomePage.class);
        startActivity(home);
    }
}
