package org.tsofen.ourstory.UserModel;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import org.tsofen.ourstory.R;


public class RegistrationPage2 extends AppCompatActivity {

    public String emailString;
    public String firstNameString;
    public String lastNameString;
    public String passwordString;

    public String stateString;
    public String cityString;

    public String dateOfBirth;
    public String dateOfSignIn;
    public String dateOfLastSignIn;
    public String profilePicture;
    public String gender;
    //will be later used as date format.

    // public Date dateOfBirth;
    // public Date dateOfSignIn;
    // public Date dateOfLastSignIn;

    public EditText EditText6;
    public EditText EditText7;
    public EditText EditText8;
    public EditText DateOfB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_page2);
        Intent currIntent = getIntent();
        emailString = currIntent.getStringExtra("email");
        firstNameString = currIntent.getStringExtra("first_name");
        lastNameString = currIntent.getStringExtra("last_name");
        passwordString = currIntent.getStringExtra("password");
        DateOfB = findViewById(R.id.showDate);
        Log.d("log4", "values received from registrationPage1:"
                + emailString + " " + firstNameString + " "
                + lastNameString + " " + passwordString);
    }


    public void showDatePicker(View view) {
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getSupportFragmentManager(), "datePicker");
    }

    public void processDatePickerResult(int year, int month, int day) {
        String month_string = Integer.toString(month + 1);
        String day_string = Integer.toString(day);
        String year_string = Integer.toString(year);
        String dateString = (month_string +
                "/" + day_string + "/" + year_string);
        dateOfBirth = dateString;
        DateOfB.setText(dateOfBirth);
    }


    public void Go2RegistrationPage3andSave(View view) {
        Intent regIntent3 = new Intent(this, LogIn.class);
        EditText6 = findViewById(R.id.showState);
        EditText7 = findViewById(R.id.showCity);
        stateString = EditText6.getText().toString();
        cityString = EditText7.getText().toString();


        regIntent3.putExtra("email", emailString);
        regIntent3.putExtra("first_name", firstNameString);
        regIntent3.putExtra("last_name", lastNameString);
        regIntent3.putExtra("password", passwordString);
        regIntent3.putExtra("state", stateString);
        regIntent3.putExtra("city", cityString);
        regIntent3.putExtra("dateOfBirth", dateOfBirth);
        regIntent3.putExtra("gender", gender);
        Log.d("log-saved", "values sent to registrationPage3:"
                + emailString + " " + firstNameString + " "
                + lastNameString + " " + passwordString + " "
                + stateString + " " + cityString + " " + dateOfBirth + " " + gender);
        startActivity(regIntent3);
    }

    public void Go2RegistrationPage3andDontSave(View view) {
        Intent regIntent3 = new Intent(this, LogIn.class);
        regIntent3.putExtra("email", emailString);
        regIntent3.putExtra("first_name", firstNameString);
        regIntent3.putExtra("last_name", lastNameString);
        regIntent3.putExtra("password", passwordString);

        Log.d("log-not saved", "values sent to registrationPage3:"
                + emailString + " " + firstNameString + " "
                + lastNameString + " " + passwordString + " ");
        startActivity(regIntent3);
    }

    public void UploadPicture(View view) {
        //still null
    }

    public void closeActivity(View view) {
        Intent back = new Intent(this, LogIn.class);
        startActivity(back);
    }

    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();
        // Check which radio button was clicked.
        switch (view.getId()) {
            case R.id.MaleRB:
                if (checked)
                    gender = "Male";

                break;
            case R.id.FemaleRB:
                if (checked)
                    gender = "Female";
                break;

            default:
                // Do nothing.
                break;
        }
    }

}
