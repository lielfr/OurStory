package com.example.addmemory;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import java.io.FileNotFoundException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView txdate;
    ImageView iv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        iv = findViewById(R.id.imgclcable);


        ImageButton smileb=findViewById(R.id.smilebtn);
        ImageButton sadb=findViewById(R.id.sadbtn);
        ImageButton happyb=findViewById(R.id.happybtn);
        ImageButton loveb=findViewById(R.id.lovebtn);
        ImageButton happy1b=findViewById(R.id.happy1btn);
        ImageButton untitledb=findViewById(R.id.untitledbtn);
        Button svbtn=findViewById(R.id.Savebtn);
        Button cnslbtn=findViewById(R.id.Cancelbtn);

        smileb.setOnClickListener(this);
        sadb.setOnClickListener(this);
        happyb.setOnClickListener(this);
        loveb.setOnClickListener(this);
        happy1b.setOnClickListener(this);
        untitledb.setOnClickListener(this);
        svbtn.setOnClickListener(this);
        cnslbtn.setOnClickListener(this);



    }



    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.smilebtn:
                displayToast("You have selected smile Emoji.");
                break;
            case R.id.sadbtn:
                displayToast("You have selected pensive-face Emoji.");
                break;
            case R.id.happybtn:
                displayToast("You have selected happy Emoji.");
                break;
            case R.id.lovebtn:
                displayToast("You have selected heart-eyes Emoji.");
                break;
            case R.id.happy1btn:
                displayToast("You have selected very-happy Emoji.");
                break;
            case R.id.untitledbtn:
                displayToast("You have selected sunglasses Emoji.");
                break;
            case R.id.Savebtn:
                displayToast("You have selected Save Button. Liel was here.");
                break;
            case R.id.Cancelbtn:
                finish();
                break;
            case R.id.xbtn:
                //delete the selected image.
                break;
        }
    }


    public void displayToast(String message) {
        Toast.makeText(getApplicationContext(), message,
                Toast.LENGTH_SHORT).show();
    }

    public void pickpic(View view) {
        Intent i= new Intent(Intent.ACTION_GET_CONTENT);
        i.setType("image/*");
        startActivityForResult(Intent.createChooser(i,"Pick an image"),1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode== RESULT_OK && requestCode == 1){
            try {
                InputStream inputStream = getContentResolver().openInputStream(data.getData());
                Bitmap bitmap= BitmapFactory.decodeStream(inputStream);
                iv.setImageBitmap(bitmap);

            }catch (FileNotFoundException e){
                e.printStackTrace();
            }
        }
    }

    public void upvid(View view) {
        Intent i= new Intent(Intent.ACTION_GET_CONTENT);
        i.setType("video/*");
        startActivityForResult(Intent.createChooser(i,"Upload a video"),1);

    }


    public void showDatePicker(View view) {
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getSupportFragmentManager(),"datePicker");
    }

    public void processDatePickerResult(int year, int month, int day) {

        String month_string = Integer.toString(month+1);
        String day_string = Integer.toString(day);
        String year_string = Integer.toString(year);
        String dateMessage = ("     "+day_string +
                "     .     " + month_string + "     .     " + year_string);
        txdate=findViewById(R.id.date);
        txdate.setText(dateMessage);

    }
}
