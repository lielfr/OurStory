package org.tsofen.ourstory.EditCreateMemory;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.tsofen.ourstory.R;
import org.tsofen.ourstory.model.Feeling;
import org.tsofen.ourstory.model.Memory;
import org.tsofen.ourstory.model.api.Story;
import org.tsofen.ourstory.web.OurStoryService;
import org.tsofen.ourstory.web.WebFactory;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class CreateEditMemoryActivity extends AppCompatActivity implements View.OnClickListener {

    boolean dateFlag = false;
    AddMemoryImageAdapter imageAdapter;
    AddMemoryVideoAdapter videoAdapter;
    AddMemoryTagAdapter tagAdapter;
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
    private ImageButton loveb;
    private Button svbtn;
    private Button cnslbtn;
    private EditText DescriptionText;
    private EditText locationText;
    public static final String KEY_EDIT = "CEMemoryEdit";
    public static final String KEY_CREATE = "CEMemoryCreate";
    public static final String KEY_MEMID = "CEMemoryMemoryID";
    private Memory memory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_edit_memory);

        Intent intent = getIntent();
        memory = (Memory) intent.getSerializableExtra(KEY_EDIT);
        TextView pageTitle = findViewById(R.id.text_cememory);
        editTextDescription = findViewById(R.id.memDescription_cememory);
        editTextLocation = findViewById(R.id.memLocation_cememory);
        smileb = findViewById(R.id.smilebtn_cememory);
        sadb = findViewById(R.id.sadbtn_cememory);
        loveb = findViewById(R.id.lovebtn_cememory);
        svbtn = findViewById(R.id.Savebtn_cememory);
        cnslbtn = findViewById(R.id.Cancelbtn_cememory);
        TextView dayDate = findViewById(R.id.day_text_cememory);
        TextView monthDate = findViewById(R.id.month_text_cememory);
        TextView yearDate = findViewById(R.id.year_text_cememory);

        if (memory == null) {
            pageTitle.setText("Add Memory");
            memory = new Memory();
        } else {
            pageTitle.setText("Edit Memory");
            editTextDescription.setText(memory.getDescription());
            editTextLocation.setText(memory.getLocation());
            dayDate.setText(memory.getMemoryDate().get(Calendar.DAY_OF_MONTH));
            monthDate.setText(memory.getMemoryDate().get(Calendar.DAY_OF_MONTH));
            yearDate.setText(memory.getMemoryDate().get(Calendar.YEAR));
            selectEmoji(memory.getFeeling());

            imageAdapter.data.addAll(memory.getPictures());
            imageAdapter.notifyDataSetChanged();
            videoAdapter.data.addAll(memory.getVideos());
            videoAdapter.notifyDataSetChanged();
            tagAdapter.tags.addAll(memory.getTags());
            tagAdapter.notifyDataSetChanged();
        }

        Story story = (Story) intent.getSerializableExtra(KEY_CREATE);
        if (story != null) {
            memory.setStory(story);
        }

        smileb.setOnClickListener(this);
        sadb.setOnClickListener(this);
        loveb.setOnClickListener(this);

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
        tagAdapter = new AddMemoryTagAdapter(new LinkedList<>(), tagsRV);
        tagsRV.setAdapter(tagAdapter);
        tagsRV.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,
                false));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.smilebtn_cememory:
                SelectedEmoji = Feeling.HAPPY;
                selectEmoji(SelectedEmoji);

                break;
            case R.id.sadbtn_cememory:
                SelectedEmoji = Feeling.SAD;
                selectEmoji(SelectedEmoji);
                break;

            case R.id.lovebtn_cememory:
                SelectedEmoji = Feeling.LOVE;
                selectEmoji(SelectedEmoji);
                break;

            case R.id.Savebtn_cememory:
                if (CheckValidation(v)) {
                    this.svbtn.setEnabled(true);
                    saveMemory(v);
                } else {
                    displayToast("Error , Please try filling out the fields again");
                }
                break;
            case R.id.Cancelbtn_cememory:
                finish();
                break;
            case R.id.back_button_cememory:
                finish();
                break;

        }
    }

    public boolean CheckValidation(View v) {        //(Memory m) {
        if ((editTextDescription.getText().toString().equals("")) && (imageAdapter.data.isEmpty()) && (videoAdapter.data.isEmpty())) {
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
                    imageAdapter.data.add(currentUri.toString());
                }
            } else if (data.getData() != null) {
                imageAdapter.data.add(data.getData().toString());
            }
            imageAdapter.notifyDataSetChanged();
        } else if (requestCode == AddMemoryVideoAdapter.ADDMEMORY_VIDEO) {
            if (data.getClipData() != null) {
                int count = data.getClipData().getItemCount();
                for (int i = 0; i < count; i++) {
                    Uri currentUri = data.getClipData().getItemAt(i).getUri();
                    videoAdapter.data.add(currentUri.toString());
                }
            } else if (data.getData() != null) {
                videoAdapter.data.add(data.getData().toString());
            }
            videoAdapter.notifyDataSetChanged();
        }
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
        Calendar c = Calendar.getInstance();
        c.set(year, month + 1, day);
        MemDate = c.getTime();

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
        boolean create = (memory == null);
        if (memory == null)
            memory = new Memory();
        locationText = findViewById(R.id.memLocation_cememory);
        memory.setLocation(locationText.getText().toString());

        memory.setDescription(editTextDescription.getText().toString());
        memory.setFeeling(SelectedEmoji);
        memory.setMemoryDate(MemDate);
        displayToast("Data saved.");
        OurStoryService service = WebFactory.getService();
        Intent intent = new Intent();

        if (create) {
            service.CreateMemory(memory).enqueue(new Callback<Memory>() {
                @Override
                public void onResponse(Call<Memory> call, Response<Memory> response) {
                    if (response.code() != 200) {
                        displayToast("Error " + response.code() + " : " + response.message());
                        return;
                    }
                    Memory responseMem = response.body();
                    long memId = responseMem.getId();
                    intent.putExtra(KEY_MEMID, memId);
                    setResult(RESULT_OK, intent);
                    finish();
                }

                @Override
                public void onFailure(Call<Memory> call, Throwable t) {

                }
            });
        } else {
            service.EditMemory(memory).enqueue(new Callback<Memory>() {
                @Override
                public void onResponse(Call<Memory> call, Response<Memory> response) {
                    finish();
                }

                @Override
                public void onFailure(Call<Memory> call, Throwable t) {

                }
            });
        }
    }

    public void selectEmoji(Feeling selected) {
        switch (selected) {
            case HAPPY:
                findViewById(R.id.smiley_back2).setVisibility(View.INVISIBLE);
                findViewById(R.id.smiley_back3).setVisibility(View.INVISIBLE);
                findViewById(R.id.smiley_back1).setVisibility(View.VISIBLE);
                break;

            case SAD:
                findViewById(R.id.smiley_back1).setVisibility(View.INVISIBLE);
                findViewById(R.id.smiley_back3).setVisibility(View.INVISIBLE);
                findViewById(R.id.smiley_back2).setVisibility(View.VISIBLE);
                break;

            case LOVE:
                findViewById(R.id.smiley_back1).setVisibility(View.INVISIBLE);
                findViewById(R.id.smiley_back2).setVisibility(View.INVISIBLE);
                findViewById(R.id.smiley_back3).setVisibility(View.VISIBLE);
                break;
        }
    }
}


