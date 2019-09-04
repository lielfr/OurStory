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

    public String emailString;
    public String firstNameString;
    public String lastNameString;
    public String passwordString;
    public String repeatPasswordString;

    public boolean emailFlag = false;
    public boolean firstNameFlag ;
    public boolean lastNameFlag;
    public boolean passwordFlag = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_page1);
        TextViewInvs1 = findViewById(R.id.emptyView1);
        TextViewInvs2 = findViewById(R.id.emptyView2);
        TextViewInvs3 = findViewById(R.id.emptyView4);
        TextViewInvs4 = findViewById(R.id.emptyView4);

    }

    public void Go2RegistrationPage2(View view) {
        Intent regIntent2 = new Intent(this, RegistrationPage2.class);

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

        if (verifyEmail(emailString)) {
            Log.d("verification tag1", "email verified");
            TextViewInvs2.setVisibility(View.INVISIBLE);
            emailFlag = true;

            // add code to verify if the email already exists

        } else {
            TextViewInvs2.setVisibility(View.VISIBLE);
            emailFlag = false;
        }



       firstNameFlag=verifyName(firstNameString);
       lastNameFlag=verifyName(lastNameString);
       if (firstNameFlag==false )
       {
           if ( lastNameFlag==false) {
               TextViewInvs1.setVisibility(View.VISIBLE);
               firstNameFlag=false;
               lastNameFlag=false;
           }
           else
           {
               TextViewInvs1.setVisibility(View.VISIBLE);
               firstNameFlag=false;
               lastNameFlag=true;
           }
      }
       else
       {
           if ( lastNameFlag==false) {
               TextViewInvs1.setVisibility(View.VISIBLE);
               firstNameFlag=true;
               lastNameFlag=false;
           }
           else
           {
               TextViewInvs1.setVisibility(View.INVISIBLE);
               firstNameFlag=true;
               lastNameFlag=true;
           }
       }

        if ((passwordString.length()) > 10) {
            TextViewInvs4.setVisibility(View.VISIBLE);
            passwordFlag = false;
        } else {
            TextViewInvs4.setVisibility(View.INVISIBLE);
            passwordFlag = true;
        }
        if (passwordString.equals(repeatPasswordString)) {
            Log.d("verification tag3", "last name verified");
            TextViewInvs4.setVisibility(View.INVISIBLE);
            passwordFlag = true;
        } else {
            TextViewInvs4.setVisibility(View.VISIBLE);
            passwordFlag = false;
        }


        if (passwordFlag && emailFlag && firstNameFlag && lastNameFlag) {
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


    public boolean verifyName(String nameString) {
        firstNameString = firstNameString.trim();

        if (nameString == null || nameString.equals(""))
            return false;

        return nameString.matches("[a-zA-Z]*");
    }


    private boolean verifyEmail(String email) {
        email = email.trim();

        if (email == null || email.equals(""))
            return false;

        return email.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$");
    }

    public void closeActivity(View view) {
        Intent back = new Intent(this, AppHomePage.class);
        startActivity(back);
    }

}


