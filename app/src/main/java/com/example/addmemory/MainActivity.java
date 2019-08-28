package com.example.addmemory;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
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
//import com.example.addmemory.model.Feeling;
import com.example.addmemory.model.Feeling;
import com.example.addmemory.model.Memory;

import org.w3c.dom.Text;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView txdate;
    ImageView iv;
    boolean flag = false;
    boolean dateFlag = false;
    AddMemoryImageAdapter adapter;
    Feeling SelectedEmoji;
    String currentDate;
    Date MemDate = new Date();
    Calendar cal = Calendar.getInstance();
    Date today = cal.getTime();
    private EditText editTextDescription;
    private EditText editTextLocation;
    private ImageButton smileb;
    private ImageButton sadb;
    private ImageButton happyb;
    private ImageButton loveb;
    private ImageButton happy1b;
    private ImageButton untitledb;
    private Button svbtn;
    private Button cnslbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editTextDescription = findViewById(R.id.memDescription);
        editTextLocation = findViewById(R.id.memLocation);
        smileb = findViewById(R.id.smilebtn);
        sadb = findViewById(R.id.sadbtn);
        happyb = findViewById(R.id.happybtn);
        loveb = findViewById(R.id.lovebtn);
        happy1b = findViewById(R.id.happy1btn);
        untitledb = findViewById(R.id.untitledbtn);
        svbtn = findViewById(R.id.Savebtn);
        cnslbtn = findViewById(R.id.Cancelbtn);

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

        editTextDescription.addTextChangedListener(SaveTextWatcher);
        editTextLocation.addTextChangedListener(SaveTextWatcher);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.smilebtn:
                displayToast("You have selected smile Emoji.");
                SelectedEmoji = Feeling.HAPPY;
                flag = true;
                break;
            case R.id.sadbtn:
                displayToast("You have selected pensive-face Emoji.");
                SelectedEmoji = Feeling.SAD;
                flag = true;
                break;
            case R.id.happybtn:
                displayToast("You have selected happy Emoji.");
                SelectedEmoji = Feeling.BLESSED;
                flag = true;
                break;
            case R.id.lovebtn:
                displayToast("You have selected heart-eyes Emoji.");
                SelectedEmoji = Feeling.LOVE;
                flag = true;
                break;
            case R.id.happy1btn:
                displayToast("You have selected very-happy Emoji.");
                SelectedEmoji = Feeling.HAHA;
                flag = true;
                break;
            case R.id.untitledbtn:
                displayToast("You have selected sunglasses Emoji.");
                SelectedEmoji = Feeling.COOL;
                flag = true;
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
        Intent i = new Intent(Intent.ACTION_GET_CONTENT);
        i.setType("image/*");
        startActivityForResult(Intent.createChooser(i, "Pick an image"), 1);
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

    private TextWatcher SaveTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            String DescriptionInput = editTextDescription.getText().toString().trim();
            String LocationInput = editTextLocation.getText().toString().trim();
            Feeling ChoosenEmoji = SelectedEmoji;

            svbtn.setEnabled(!DescriptionInput.isEmpty() && !LocationInput.isEmpty() && flag && dateFlag);
        }
    };

    public void upvid(View view) {
        Intent i = new Intent(Intent.ACTION_GET_CONTENT);
        i.setType("video/*");
        startActivityForResult(Intent.createChooser(i, "Upload a video"), 1);

    }


    public void showDatePicker(View view) {
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getSupportFragmentManager(), "datePicker");
    }

    public void processDatePickerResult(int year, int month, int day) {

        String month_string = Integer.toString(month + 1);
        String day_string = Integer.toString(day);
        String year_string = Integer.toString(year);
//

        currentDate = day_string + "/" + month_string + "/" + year_string;
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        try {
            MemDate = dateFormat.parse(currentDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if (MemDate == null)
            displayToast("Plese Enter Memory date ");
        else if (today.before(MemDate))
            displayToast("You have selected invalid date , please choose valid date again ");

        else
            dateFlag = true;


        //dateFormat.parse(currentDate);
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
        EditText locationText = findViewById(R.id.memLocation);
        mem.setLocation(locationText.getText().toString());
        EditText DescriptionText = findViewById(R.id.memDescription);
        mem.setDescription(DescriptionText.getText().toString());
        mem.setFeeling(SelectedEmoji);
        mem.setMemoryDate(MemDate);


    }
}
