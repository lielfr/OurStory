package org.tsofen.ourstory;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
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
import com.example.ourstory.R;

import org.tsofen.ourstory.model.Feeling;
import org.tsofen.ourstory.model.Memory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class CreateEditMemoryActivity extends AppCompatActivity implements View.OnClickListener {

    TextView txdate;
    ImageView iv;
    int flag = -1;
    boolean dateFlag = false;
    AddMemoryImageAdapter adapter;
    Feeling SelectedEmoji;
    String currentDate;
    Date MemDate = new Date();
    Date BirthDate = new Date();
    Date DeathDate = new Date();
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
    private EditText DescriptionText;
    private EditText locationText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_edit_memory);
        editTextDescription = findViewById(R.id.memDescription_cememory);
        editTextLocation = findViewById(R.id.memLocation_cememory);
        smileb = findViewById(R.id.smilebtn_cememory);
        sadb = findViewById(R.id.sadbtn_cememory);
        happyb = findViewById(R.id.happybtn_cememory);
        loveb = findViewById(R.id.lovebtn_cememory);
        happy1b = findViewById(R.id.happy1btn_cememory);
        untitledb = findViewById(R.id.untitledbtn_cememory);
        svbtn = findViewById(R.id.Savebtn_cememory);
        cnslbtn = findViewById(R.id.Cancelbtn_cememory);

        smileb.setOnClickListener(this);
        sadb.setOnClickListener(this);
        happyb.setOnClickListener(this);
        loveb.setOnClickListener(this);
        happy1b.setOnClickListener(this);
        untitledb.setOnClickListener(this);

        svbtn.setOnClickListener(this);
        cnslbtn.setOnClickListener(this);

        RecyclerView rv = findViewById(R.id.add_pictures_rv_cememory);
        adapter = new AddMemoryImageAdapter(this);
        rv.setAdapter(adapter);
        rv.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,
                false));

        //   editTextDescription.addTextChangedListener(SaveTextWatcher);
        // editTextLocation.addTextChangedListener(SaveTextWatcher);

    }

    @Override
    public void onClick(View v) {
       /* switch (flag)
        {
            case (0):

                break;
        }*/
        switch (v.getId()) {
            case R.id.smilebtn_cememory:
                displayToast("You have selected smile Emoji.");
                SelectedEmoji = Feeling.HAPPY;
                //  smileb.setBackgroundColor(Color.parseColor("#C3D7EB"));
                // smileb.getLayoutParams().height(34);
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(35, 35);
                smileb.setLayoutParams(params);
                flag = 0;
                break;
            case R.id.sadbtn_cememory:
                displayToast("You have selected pensive-face Emoji.");
                SelectedEmoji = Feeling.SAD;
                flag = 1;
                break;
            case R.id.happybtn_cememory:
                displayToast("You have selected happy Emoji.");
                SelectedEmoji = Feeling.BLESSED;
                flag = 2;
                break;
            case R.id.lovebtn_cememory:
                displayToast("You have selected heart-eyes Emoji.");
                SelectedEmoji = Feeling.LOVE;
                flag = 3;
                break;
            case R.id.happy1btn_cememory:
                displayToast("You have selected very-happy Emoji.");
                SelectedEmoji = Feeling.HAHA;
                flag = 4;
                break;
            case R.id.untitledbtn_cememory:
                displayToast("You have selected sunglasses Emoji.");
                SelectedEmoji = Feeling.COOL;
                flag = 5;
                break;
            case R.id.Savebtn_cememory:
                if (CheckValidation(v)) {
                    this.svbtn.setEnabled(true);
                    saveMemory(v);
                } else {
                    displayToast("Error , Please try filling out the fields again");
                    //this.svbtn.setEnabled(false);
                }
                break;
            case R.id.Cancelbtn_cememory:
                finish();
                break;
        }
    }

    public void HiglightEmoji(ImageButton Selected) {

    }

    public boolean CheckValidation(View v) {        //(Memory m) {
        if ((editTextDescription.getText().toString().equals("")) && (adapter.getItemCount() == 1)) {
            {
                displayToast("You should either enter an image or a description for your memory!");
                return false;
            }
        }
        if (today.before(MemDate)) {
            displayToast("You have selected invalid date , please choose valid date again ");
            return false;
        }
        if (MemDate.before(BirthDate)) {
            displayToast("You have selected invalid date ,Memory can't occur before birth date, please choose valid date again ");
            return false;
        }
        if (MemDate.after(DeathDate)) {
            displayToast("You have selected invalid date ,Memory can't occur after Death date, please choose valid date again ");
            return false;
        } else
            dateFlag = true;
        return true;
    }


    public void displayToast(String message) {
        Toast.makeText(getApplicationContext(), message,
                Toast.LENGTH_SHORT).show();
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

    /* private TextWatcher SaveTextWatcher = new TextWatcher() {
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
 */
    public void upvid(View view) {
        Intent i = new Intent(Intent.ACTION_GET_CONTENT);
        i.setType("video/*");
        startActivityForResult(Intent.createChooser(i, "Upload a video"), 1);

    }


    public void showDatePicker(View view) {
        DialogFragment newFragment = new DatePickerFragmentCEMemory();
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

        TextView dayDate = findViewById(R.id.day_text_cememory);
        TextView monthDate = findViewById(R.id.month_text_cememory);
        TextView yearDate = findViewById(R.id.year_text_cememory);

        dayDate.setText(day_string);
        monthDate.setText(month_string);
        yearDate.setText(year_string);
    }

    public void closeActivity(View view) {
        finish();
    }

    public void saveMemory(View view) {

        Memory mem = new Memory();

        mem.setLocation(locationText.getText().toString());

        mem.setDescription(DescriptionText.getText().toString());
        mem.setFeeling(SelectedEmoji);
        mem.setMemoryDate(MemDate);

        displayToast("Your memory data has been successfully saved");


    }
}
