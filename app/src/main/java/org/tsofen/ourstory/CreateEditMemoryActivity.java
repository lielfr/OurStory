package org.tsofen.ourstory;

import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
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

import com.vatsal.imagezoomer.ZoomAnimation;

import org.tsofen.ourstory.model.Feeling;
import org.tsofen.ourstory.model.Memory;
import org.tsofen.ourstory.model.Tag;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;


public class CreateEditMemoryActivity extends AppCompatActivity implements View.OnClickListener {

    TextView txdate;
    ImageView iv;
    int flag = -1;
    boolean dateFlag = false;
    AddMemoryImageAdapter imageAdapter;
    AddMemoryVideoAdapter videoAdapter;
    AddMemoryTagAdapter tagAdapter;
    Feeling SelectedEmoji;
    String currentDate;
    Date MemDate = new Date();
    //    Date BirthDate = new Date(1990, 8, 10);
//    Date DeathDate = new Date(2000, 5, 23);
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

        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("AAA");
        TextView pageTitle = findViewById(R.id.text_cememory);
        if (bundle == null)
            pageTitle.setText("Add Memory");
        else
            pageTitle.setText("Edit Memory");

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

        RecyclerView rvp = findViewById(R.id.add_pictures_rv_cememory);
        imageAdapter = new AddMemoryImageAdapter(this);
        rvp.setAdapter(imageAdapter);
        rvp.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,
                false));

        RecyclerView rvv = findViewById(R.id.add_videos_rv_cememory);
        videoAdapter = new AddMemoryVideoAdapter(this);
        rvv.setAdapter(videoAdapter);
        rvv.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,
                false));

        //   editTextDescription.addTextChangedListener(SaveTextWatcher);
        // editTextLocation.addTextChangedListener(SaveTextWatcher);

        RecyclerView tagsRV = findViewById(R.id.tagsLayout_cememory);
        tagAdapter = new AddMemoryTagAdapter(new LinkedList<Tag>(), tagsRV);
        tagsRV.setAdapter(tagAdapter);
        tagsRV.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL,
                false));

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

                smileb.requestLayout();
//
                HiglightEmoji(v);
//
                flag = 0;
                break;
            case R.id.sadbtn_cememory:
                displayToast("You have selected pensive-face Emoji.");
                SelectedEmoji = Feeling.SAD;
                HiglightEmoji(v);
                flag = 1;
                break;
            case R.id.happybtn_cememory:
                displayToast("You have selected happy Emoji.");
                SelectedEmoji = Feeling.BLESSED;
                HiglightEmoji(v);
                flag = 2;
                break;
            case R.id.lovebtn_cememory:
                displayToast("You have selected heart-eyes Emoji.");
                SelectedEmoji = Feeling.LOVE;
                HiglightEmoji(v);
                flag = 3;
                break;
            case R.id.happy1btn_cememory:
                displayToast("You have selected very-happy Emoji.");
                SelectedEmoji = Feeling.HAHA;
                HiglightEmoji(v);
                flag = 4;
                break;
            case R.id.untitledbtn_cememory:
                displayToast("You have selected sunglasses Emoji.");
                SelectedEmoji = Feeling.COOL;
                HiglightEmoji(v);
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

    public void HiglightEmoji(View v) {
        Resources r = getResources();
        ZoomAnimation zoomAnimation = new ZoomAnimation(this);
        zoomAnimation.zoomReverse(v, 250);

    }

    public boolean CheckValidation(View v) {        //(Memory m) {
        if ((editTextDescription.getText().toString().equals("")) && (imageAdapter.images.isEmpty()) && (videoAdapter.videos.isEmpty())) {
            {
                displayToast("You should either enter an image or a viedeo or description for your memory!");
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
        if (data == null) return;
        if (requestCode == AddMemoryImageAdapter.ADDMEMORY_IMAGE) {
            if (data.getClipData() != null) {
                int count = data.getClipData().getItemCount();
                for (int i = 0; i < count; i++) {
                    Uri currentUri = data.getClipData().getItemAt(i).getUri();
                    imageAdapter.images.add(currentUri.toString());
                }
            } else if (data.getData() != null) {
                imageAdapter.images.add(data.getData().toString());
            }
            imageAdapter.notifyDataSetChanged();
        } else if (requestCode == AddMemoryVideoAdapter.ADDMEMORY_VIDEO) {
            if (data.getClipData() != null) {
                int count = data.getClipData().getItemCount();
                for (int i = 0; i < count; i++) {
                    Uri currentUri = data.getClipData().getItemAt(i).getUri();
                    videoAdapter.videos.add(currentUri.toString());
                }
            } else if (data.getData() != null) {
                videoAdapter.videos.add(data.getData().toString());
            }
            videoAdapter.notifyDataSetChanged();
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
        locationText = findViewById(R.id.memLocation_cememory);
        mem.setLocation(locationText.getText().toString());

        mem.setDescription(editTextDescription.getText().toString());
        mem.setFeeling(SelectedEmoji);
        Calendar c = Calendar.getInstance();
        c.setTime(MemDate);
        mem.setMemoryDate(c);
        displayToast("Data saved.");

    }
}
