package org.tsofen.ourstory.UserModel;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


import org.tsofen.ourstory.R;

import java.util.ArrayList;

import static org.tsofen.ourstory.UserModel.UsersList.usersList;


public class LogIn extends AppCompatActivity {
    public String emailString;
    public String firstNameString;
    public String lastNameString;
    public String passwordString;
    public String stateString;
    public String cityString;
    public String genderString = "Null";
    public String profilePictureString = "#FTGFHFJJY";
    public String dateOfBirth;
    public String dateOfRegistration = "0/0/00";
    public String dateOfLastSignIn = "0/0/00";
    EditText email;
    EditText password;
    int flag1 = 0;
    int flag2 = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        email = findViewById(R.id.showEmail);
        password = findViewById(R.id.Password);
        Intent currIntent = getIntent();
        emailString = currIntent.getStringExtra("email");
        firstNameString = currIntent.getStringExtra("first_name");
        lastNameString = currIntent.getStringExtra("last_name");
        passwordString = currIntent.getStringExtra("password");
        stateString = currIntent.getStringExtra("state");
        cityString = currIntent.getStringExtra("city");
        dateOfBirth = currIntent.getStringExtra("dateOfBirth");
        genderString = currIntent.getStringExtra("gender");
        User tempUser = new User(firstNameString, lastNameString, passwordString,
                emailString, profilePictureString, genderString, dateOfBirth,
                dateOfRegistration, dateOfLastSignIn, stateString, cityString);
        UsersList.usersList = new ArrayList<User>();
        UsersList.usersList.add(tempUser);
        String text = "User Created with:" + usersList.get(0).getmFirstName() + " "
                + usersList.get(0).getmLastName() + " " + usersList.get(0).getmPassword() + " "
                + usersList.get(0).getmEmail() + " " + usersList.get(0).getmProfilePicture() + " "
                + usersList.get(0).getmGender() + " " + usersList.get(0).getmDateOfBirth() + " "
                + usersList.get(0).getmDateOfRegistration() + " " + usersList.get(0).getmDateOfLastSignIn() + " "
                + usersList.get(0).getmState() + " " + usersList.get(0).getmCity() + " ";
        Log.d("tag", text);

    }


    public void goLogin(View view) {
        int index = 0;
        String userIn;
        String inputEmail = String.valueOf(email.getText());
        String inputPassword = String.valueOf(password.getText());
        for (int i = 0; i < UsersList.usersList.size(); i++) {
            if (inputEmail.equals(UsersList.usersList.get(i).getmEmail()) && (inputPassword.equals(usersList.get(i).getmPassword()))) {
                flag1 = 1;
                flag2 = 1;
                index = i;
            }
        }

        if (flag1 == 1 && flag2 == 1) {
            Intent signInDone = new Intent(this, AppHomePage.class);
            signInDone.putExtra("email", inputEmail);
            signInDone.putExtra("index", index);


            startActivity(signInDone);
        } else {
            Toast.makeText(this, "Check email or password and make sure that you are registered",
                    Toast.LENGTH_SHORT).show();
        }
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
