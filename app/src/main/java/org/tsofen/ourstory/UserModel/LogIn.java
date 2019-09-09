package org.tsofen.ourstory.UserModel;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.tsofen.ourstory.R;
import org.tsofen.ourstory.web.OurStoryService;
import org.tsofen.ourstory.web.WebFactory;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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

    int flag1 = 1;
    int flag2 = 1;
    String userPass;
    long userId;
org.tsofen.ourstory.model.api.User myUser;
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
        profilePictureString = currIntent.getStringExtra("profilePicture");

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

        //int index = 0;
       // String userIn;
        OurStoryService ss = WebFactory.getService();
        String inputEmail = String.valueOf(email.getText());
        String inputPassword = String.valueOf(password.getText());


        ss.GetUserByEmail(inputEmail).enqueue(new Callback<org.tsofen.ourstory.model.api.User>() {


            @Override
            public void onResponse(Call<org.tsofen.ourstory.model.api.User> call, Response<org.tsofen.ourstory.model.api.User> response) {
                myUser = response.body();
                if (myUser == null) {
                    flag1=0;



                }
                else {

                    userId=myUser.getUserId();
                    userPass=myUser.getPassword();

                    if (!(userPass.equals(inputPassword)))
                    {
                        Toast.makeText(getApplicationContext(), "Incorrect password", Toast.LENGTH_LONG).show();
                        flag2 = 0;
                    }


                }
            }

            @Override
            public void onFailure(Call<org.tsofen.ourstory.model.api.User> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "failed", Toast.LENGTH_LONG).show();

            }
        });




        if (flag1==0 ){
            Toast.makeText(this, "This email address is invalid. Please tru a different one",
                    Toast.LENGTH_SHORT).show();}

          else  if (flag1 == 1 && flag2 == 1) {
               UserStatusCheck.setUserStatus("not a visitor");
                Intent signInDone = new Intent(this, AppHomePage.class);
                signInDone.putExtra("email", inputEmail);
                signInDone.putExtra("userId",userId);
                signInDone.putExtra("user",myUser);
               //signInDone.putExtra("index", index);

                startActivity(signInDone);
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
