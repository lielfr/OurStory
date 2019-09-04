package org.tsofen.ourstory.StoryTeam;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;


import org.tsofen.ourstory.R;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class CreateStory extends AppCompatActivity implements Serializable {

    ImageView image;
    int flag = 1;
    Bitmap bitmap;
    String filepathS = null;
    Uri filePath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_createstory);
    }

    private int validateName(EditText edtTxt, String str) {
        // first and last name validation
        if (str.length() == 0) {
            edtTxt.requestFocus();
            edtTxt.setError("Field cannot be empty!");
        } else if (!str.matches("[a-zA-Z ]+")) {
            edtTxt.requestFocus();
            edtTxt.setError("ENTER ONLY ALPHABETICAL CHARACTER");
        } else { // all good
            return 1;
        }
        return 0;
    }


    public void showDatePicker1(View view) {
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getSupportFragmentManager(), "datePicker");
        flag = 1;
    }

    public void showDatePicker2(View view) {
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getSupportFragmentManager(), "datePicker");
        flag = 2;
    }

    public int processDatePickerResult(int year, int month, int day) {
        String month_string = Integer.toString(month + 1);
        String day_string = Integer.toString(day);
        String year_string = Integer.toString(year);
        String dateMessage = (month_string + "/" + day_string + "/" + year_string);

        if (flag == 1) {
            TextView year1 = findViewById(R.id.year_1);
            year1.setText(year_string);
            TextView day1 = findViewById(R.id.day_1);
            day1.setText(day_string);
            TextView month1 = findViewById(R.id.month_1);
            month1.setText(month_string);

            return 1;
        } else {
            TextView year1 = findViewById(R.id.year_2);
            year1.setText(year_string);
            TextView day1 = findViewById(R.id.day_2);
            day1.setText(day_string);
            TextView month1 = findViewById(R.id.month_2);
            month1.setText(month_string);

            return 1;
        }
    }


    public void pickPic(View view) {
        Intent i = new Intent(Intent.ACTION_GET_CONTENT);
        i.setType("image/*");
        startActivityForResult(Intent.createChooser(i, "Pick an image"), 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        image = findViewById(R.id.profilePic);
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK && requestCode == 1) {
            try {
                filePath = data.getData();
                InputStream inputStream = getContentResolver().openInputStream(data.getData());
                bitmap = BitmapFactory.decodeStream(inputStream);
                image.setImageBitmap(bitmap); // setting a new image

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }


    // launch ViewStoryActivity
    public void viewCreatedStory(View view) {
        int f1 = 0, f2 = 0, f3 = 0;  // flags
        Date date1D, date2D, todayD;

        Intent i = new Intent(this, ViewStory.class);

        // Names Validation
        EditText firstName = findViewById(R.id.firstNameEditText);
        String fns = firstName.getText().toString();
        f1 = validateName(firstName, fns);
        EditText lastName = findViewById(R.id.lastNameEditText);
        String lns = lastName.getText().toString();
        f2 = validateName(lastName, lns);

        // Dates Validation
        EditText d1 = findViewById(R.id.day_1);
        EditText d2 = findViewById(R.id.day_2);
        String d1s = d1.getText().toString();
        String d2s = d2.getText().toString();

        EditText m1 = findViewById(R.id.month_1);
        EditText m2 = findViewById(R.id.month_2);
        String m1s = m1.getText().toString();
        String m2s = m2.getText().toString();

        EditText y1 = findViewById(R.id.year_1);
        EditText y2 = findViewById(R.id.year_2);
        String y1s = y1.getText().toString();
        String y2s = y2.getText().toString();

        String date1 = d1s + "/" + m1s + "/" + y1s;
        String date2 = d2s + "/" + m2s + "/" + y2s;

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            date1D = sdf.parse(y1s + "-" + m1s + "-" + d1s);
            date2D = sdf.parse(y2s + "-" + m2s + "-" + d2s);

            Calendar cal = Calendar.getInstance();
            todayD = cal.getTime();

            if (date1D.after(date2D)) {
                f3 = 0;
                Toast.makeText(getApplicationContext(), "Invalid Dates", Toast.LENGTH_LONG).show();
            } else if (todayD.before(date2D)) {
                f3 = 0;
                Toast.makeText(getApplicationContext(), "Invalid death date!", Toast.LENGTH_LONG).show();
            } else { // all good
                f3 = 1;
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }

        int tag1 = R.drawable.family_vs, tag2 = R.drawable.sports_vs, tag3 = R.drawable.vacation_vs;
        i.putExtra("tag1", tag1);
        i.putExtra("tag2", tag2);
        i.putExtra("tag3", tag3);
        String ttag1 = "Sports", ttag2 = "family", ttag3 = "Vacations";
        i.putExtra("ttag3", ttag3);
        i.putExtra("ttag1", ttag1);
        i.putExtra("ttag2", ttag2);

        ImageView iv = findViewById(R.id.profilePic); //pass the profile image

        if (f1 == 1 && f2 == 1 && f3 == 1) {
            // Send data to next activity
            i.putExtra("first name", fns);
            i.putExtra("last name", lns);

            i.putExtra("date1", date1);
            i.putExtra("date2", date2);

            //i.putExtra("image", (Serializable)  iv);

            startActivity(i);
        }
    }
}
