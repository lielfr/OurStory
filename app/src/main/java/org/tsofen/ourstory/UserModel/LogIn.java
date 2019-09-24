package org.tsofen.ourstory.UserModel;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;

import org.tsofen.ourstory.R;
import org.tsofen.ourstory.web.OurStoryService;
import org.tsofen.ourstory.web.WebFactory;

import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class LogIn extends AppCompatActivity {
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
    String userPass;
    Long userId;
    int year;
    int month;
    int day;
    SharedPreferences mPrefs ;
    SharedPreferences.Editor prefsEditor;
    CheckBox keeplog ;
    org.tsofen.ourstory.model.api.User myUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        mPrefs = getSharedPreferences(getString(R.string.shared_pref_key), MODE_PRIVATE);
        prefsEditor = mPrefs.edit();
        keeplog = findViewById(R.id.checkBoxRememberMe);
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
        month = currIntent.getIntExtra("month", 0);
        day = currIntent.getIntExtra("day", 0);
        year = currIntent.getIntExtra("year", 0);
        dateOfBirth = new Date(year, month, day);
        genderString = currIntent.getStringExtra("gender");
        profilePictureString = currIntent.getStringExtra("profilePicture");


    }


    public void goLogin(View view) {

        String inputEmail = String.valueOf(email.getText());
        String inputPassword = String.valueOf(password.getText());

        if (inputEmail.equals("")||(inputPassword.equals(""))) {
            emailErr.setText("Please enter your email to login");
            passErr.setText("Please enter your password to login");
        }
        else {

            OurStoryService ss = WebFactory.getService();
            ss.login(inputEmail,inputPassword).enqueue(new Callback<org.tsofen.ourstory.model.api.User>() {
                @Override
                public void onResponse(Call<org.tsofen.ourstory.model.api.User> call,
                                       Response<org.tsofen.ourstory.model.api.User> response) {
                    myUser = response.body();
                    if(myUser==null)

                   Log.d("incorrect","incorrect");
                    else {
                        userId = myUser.getUserId();

//                        userPass = myUser.getPassword();
//                        if (userPass.equals(inputPassword)) {
                            UserStatusCheck.setUserStatus("not a visitor");
                            //TODO move the user id to the home page and then move it to create story intent (move it under name=("userId"))
                            //
                            Intent signInDone = new Intent(getApplicationContext(), AppHomePage.class);
                            signInDone.putExtra("email", inputEmail);
                            signInDone.putExtra("userId", userId);
                            signInDone.putExtra("user", myUser);
                            //signInDone.putExtra("index", index);
                            Gson gson = new Gson();
                            String json = gson.toJson(myUser);

                            if (keeplog.isChecked()) {
                                Log.d("what", mPrefs.getString(AppHomePage.USER, ""));
                                prefsEditor.putString(AppHomePage.USER, json);
                                prefsEditor.commit();
                            }
                            else
                                {
                                signInDone.putExtra("myUserJson", json);
                            }
                            UserStatusCheck.setUserStatus("not a visitor");
                            AppHomePage.user2=json;
                            passErr.setVisibility(View.INVISIBLE);
                            startActivity(signInDone);

                        }
                    }
                //end of onResponse method
                @Override
                public void onFailure(Call<org.tsofen.ourstory.model.api.User> call, Throwable t) {
                    passErr.setVisibility(View.VISIBLE);
                    passErr.setText("The email or password you entered is incorrect!");
                }// end of onFailure method
            }   );// end of enque
        }//end of first else

    }//end of goLogin method




    public void goRegist(View view) {
        Intent registNow = new Intent(this, RegistrationPage1.class);
        startActivity(registNow);
    }

    public void goChangePassword(View view) {
Intent forgotPass=new Intent(LogIn.this,ForgotPassword.class);
startActivity(forgotPass);

    }

    public void closeActivity(View view) {
        Intent home = new Intent(this, AppHomePage.class);
        startActivity(home);
    }
}
