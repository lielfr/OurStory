package org.tsofen.ourstory.UserModel;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.tsofen.ourstory.R;


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

    String mess1="Field cannot be empty!";
    String mess2="ENTER ONLY ALPHABETICAL CHARACTER";
    String mess3="Please enter a valid email for example alexey19@gmail.com";
    String mess4="WEAK";
    String mess5="MEDIUM";
    String mess6="Password Max Length less than 20";
    String mess7="STRONG";
    String mess8="Please write your password again!";
    String mess9="Passwords must match!";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_page1);
        TextViewInvs1 = findViewById(R.id.emptyView1);
        TextViewInvs2 = findViewById(R.id.emptyView2);
        TextViewInvs3 = findViewById(R.id.emptyView3);
        TextViewInvs4 = findViewById(R.id.emptyView4);
        TextViewInvs5 = findViewById(R.id.emptyView5);

    }

    public void Go2RegistrationPage2(View view)
    {
        Intent regIntent2 = new Intent(this, RegistrationPage2.class);
        int f1 = 0, f2 = 0, f3 = 0, f4 = 0, f5 = 0;  // flags

        EditText1 = findViewById(R.id.showEmail);
        EditText2 = findViewById(R.id.showFirst);
        EditText3 = findViewById(R.id.showLast);
        EditText4 = findViewById(R.id.password_editText);
        EditText5 = findViewById(R.id.repeatpassword_editText);

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
        } else if (str1.length() < 6) {
            edtTxt1.setText(mess4);
            edtTxt1.setVisibility(View.VISIBLE);
        } else if (str1.length() < 10) {
            edtTxt1.setText(mess5);
            edtTxt1.setVisibility(View.VISIBLE);
        } else if (str1.length() > 20) {
            edtTxt1.setText(mess6);
            edtTxt1.setVisibility(View.VISIBLE);
        } else
        {
            edtTxt1.setText(mess7);
            edtTxt1.setVisibility(View.VISIBLE);
            return 1;
        }
        return 0;
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


