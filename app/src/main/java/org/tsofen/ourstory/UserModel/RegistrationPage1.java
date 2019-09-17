package org.tsofen.ourstory.UserModel;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.tsofen.ourstory.R;
import org.tsofen.ourstory.model.api.User;
import org.tsofen.ourstory.web.OurStoryService;
import org.tsofen.ourstory.web.WebFactory;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class RegistrationPage1 extends AppCompatActivity {

    public EditText EditText1;
    public EditText EditText2;
    public EditText EditText3;
    public EditText EditText4;
    public EditText EditText5;

    public TextView TextViewInvs1;
    public TextView TextViewInvs2;
    public TextView TextViewInvs3;
    public TextView TextViewInvs4;
    public TextView TextViewInvs5;

    public String emailString;
    public String firstNameString;
    public String lastNameString;
    public String passwordString;
    public String repeatPasswordString;
    User validUser=null;
    String mess1="Field cannot be empty!";
    String mess2 = "ENTER only alphabetic characters";
    String mess3="Please enter a valid email for example alexey19@gmail.com";
    String mess4 = "Weak password";
    //String mess5 = "Medium password";
    String mess6 = "Password cannot be longer than 20 characters";
   // String mess7 = "Strong password";
    String mess8 = "Please write your password again";
    String mess9="Passwords must match!";
    int f1 = 0, f2 = 0, f3 = 0, f4 = 0, f5 = 0;  // flags

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_page1);
        TextViewInvs1 = findViewById(R.id.emptyView1);
        TextViewInvs2 = findViewById(R.id.emptyView2);
        TextViewInvs3 = findViewById(R.id.emptyView3);
        TextViewInvs4 = findViewById(R.id.emptyView4);
        TextViewInvs5 = findViewById(R.id.emptyView5);

        EditText1 = findViewById(R.id.showEmail);
        EditText2 = findViewById(R.id.showFirst);
        EditText3 = findViewById(R.id.showLast);
        EditText4 = findViewById(R.id.password_editText);
        EditText5 = findViewById(R.id.repeatpassword_editText);

        Intent currIntent = getIntent();
        emailString = currIntent.getStringExtra("email");
        firstNameString = currIntent.getStringExtra("first_name");
        lastNameString = currIntent.getStringExtra("last_name");
        passwordString = currIntent.getStringExtra("password");

        EditText1.setText(emailString);
        EditText2.setText(firstNameString);
        EditText3.setText(lastNameString);
        EditText4.setText(passwordString);
        EditText5.setText(passwordString);


    }

    public void Go2RegistrationPage2(View view)
    {
        Intent regIntent2 = new Intent(this, RegistrationPage2.class);



        emailString = EditText1.getText().toString();
        firstNameString = EditText2.getText().toString();
        lastNameString = EditText3.getText().toString();
        passwordString = EditText4.getText().toString();
        repeatPasswordString = EditText5.getText().toString();
        Log.d("log2", "values sent to registrationPage1:"
                + emailString + " " + firstNameString + " "
                + lastNameString + " " + passwordString + " " + repeatPasswordString);

        // Names Validation
        f1 = verifyName(TextViewInvs1, firstNameString);
        f2 = verifyName(TextViewInvs2, lastNameString);
        // Email Validation
        f3 = verifyEmail(TextViewInvs3, emailString);
        // password Validation
        f4 = verifyPassword(TextViewInvs4, passwordString);
        f5 = verifyREPassword(TextViewInvs5, repeatPasswordString,passwordString);

        OurStoryService ss = WebFactory.getService();
        ss.GetUserByEmail(emailString).enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                User currUser = response.body();
                    Log.d("checktag", "the email is taken");
                    TextViewInvs3.setVisibility(View.VISIBLE);
                    TextViewInvs3.setText("The email is taken.Try another.");

            }
            @Override
            public void onFailure(Call<User> call, Throwable t) {
                if ((f1 == 1) && (f2 == 1) && (f3 == 1) && (f4 == 1) && (f5 == 1)) {
                    regIntent2.putExtra("email", emailString);
                    regIntent2.putExtra("first_name", firstNameString);
                    regIntent2.putExtra("last_name", lastNameString);
                    regIntent2.putExtra("password", passwordString);
                    Log.d("log2", "values passed from registrationPage1:"
                            + emailString + " " + firstNameString + " "
                            + lastNameString + " " + passwordString);
                    startActivity(regIntent2);
                }
                }

        });


    }
    private int verifyName(TextView edtTxt, String str)
    {
        // first and last name validation
        if (str.length() == 0)
        {
            edtTxt.requestFocus();
            edtTxt.setText(mess1);
            edtTxt.setVisibility(View.VISIBLE);
        } else if (!str.matches("[a-zA-Z ]+"))
        {
            edtTxt.setText(mess2);
            edtTxt.setVisibility(View.VISIBLE);
        }
        else
        { //all good
            return 1;
        }

        return 0;
    }
    private int verifyEmail(TextView edtTxt, String str)
    {
        if (str.length() == 0)
        {
            edtTxt.setText(mess1);
            edtTxt.setVisibility(View.VISIBLE);
        }
        else if (!str.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$"))
        {
            edtTxt.setText(mess3);
            edtTxt.setVisibility(View.VISIBLE);
        }

        else
        {   //all good
            return 1;
        }

        return 0;
    }
    private int verifyPassword(TextView edtTxt1, String str1)
    {
        if (str1.length() == 0) {
            edtTxt1.setText(mess1);
            edtTxt1.setVisibility(View.VISIBLE);
            return 0;
        } else if (str1.length() < 6) {
            edtTxt1.setText(mess4);
            edtTxt1.setVisibility(View.VISIBLE);

       return 0;
        } else if (str1.length() > 20) {
            edtTxt1.setText(mess6);
            edtTxt1.setVisibility(View.VISIBLE);
            return 0;
        }
        return 1;

    }

    private int verifyREPassword(TextView edtTxt2, String str2, String str1)
    {

        if (str2.length() == 0)
        {
            edtTxt2.setText(mess8);
            edtTxt2.setVisibility(View.VISIBLE);
        }
        else if (str1.equals(str2))
        {
            return 1;
        }
        else
        {
            edtTxt2.setText(mess9);
            edtTxt2.setVisibility(View.VISIBLE);
        }
        return 0;
    }
    public void closeActivity(View view)
    {
        Intent back = new Intent(this, AppHomePage.class);
        startActivity(back);
    }


}


