package com.example.addmemory;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.esafirm.imagepicker.features.ImagePicker;
import com.esafirm.imagepicker.model.Image;
import com.example.addmemory.model.Memory;

import org.w3c.dom.Text;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView txdate;
    ImageView iv;
    AddMemoryImageAdapter adapter;

    String currentDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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

        RecyclerView rv = findViewById(R.id.add_pictures_rv);
        adapter = new AddMemoryImageAdapter(this);
        rv.setAdapter(adapter);
        rv.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,
                false));

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

        if (ImagePicker.shouldHandle(requestCode, resultCode, data)) {
            List<Image> images = ImagePicker.getImages(data);
            for (Image img : images) {
                adapter.images.add(img.getPath());
            }
            adapter.notifyDataSetChanged();
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
//
        currentDate = day_string + "/" + month_string + "/" + year_string;
        TextView dayDate = findViewById(R.id.day_text);
        TextView monthDate = findViewById(R.id.month_text);
        TextView yearDate = findViewById(R.id.year_text);

        dayDate.setText(day_string);
        monthDate.setText(month_string);
        yearDate.setText(year_string);
    }

    public void closeActivity(View view) {
        finish();
    }

    public void saveMemory(View view) {

        Memory mem = new Memory();
        EditText locationText = findViewById(R.id.editText2);
        mem.setLocation(locationText.getText().toString());
    }
}
