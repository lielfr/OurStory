package org.tsofen.ourstory.EditCreateMemory;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.Tasks;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;
import com.google.gson.Gson;

import org.tsofen.ourstory.FirebaseImageWrapper;
import org.tsofen.ourstory.R;
import org.tsofen.ourstory.StoryTeam.CreateStory;
import org.tsofen.ourstory.UserModel.AppHomePage;
import org.tsofen.ourstory.UserModel.LogIn;
import org.tsofen.ourstory.model.Feeling;
import org.tsofen.ourstory.model.Memory;
import org.tsofen.ourstory.model.Picture;
import org.tsofen.ourstory.model.Tag;
import org.tsofen.ourstory.model.Video;
import org.tsofen.ourstory.model.api.Story;
import org.tsofen.ourstory.model.api.User;
import org.tsofen.ourstory.web.OurStoryService;
import org.tsofen.ourstory.web.WebFactory;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.TimeZone;

import io.reactivex.rxjava3.schedulers.Schedulers;


public class CreateEditMemoryActivity extends AppCompatActivity implements View.OnClickListener {
    int AUTOCOMPLETE_REQUEST_CODE = 1;


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
    //    public static final String KEY_USER = "CEMemoryUser";
    private Memory memory;
    private boolean create = true;
    private TextView MemError;
    private LinearLayout imageLiner;
    private ScrollView ourScroller;
    private User user;
    private Story story;
    TextView AddPicTxV;

    TextView error3;
    TextView showMemDate;
    private int year1 = 1, month1 = 1, day1 = 1;
    CheckBox dayChckBx1,monthChckBx1,yearChckBx1;
    boolean checked1 = false, checked2 = false, checked3 = false;
    DatePicker memoryDatePicker;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        SharedPreferences preferences = getSharedPreferences(getString(R.string.shared_pref_key), MODE_PRIVATE);
        Gson gson = new Gson();
        String userStr = preferences.getString("myUser", "ERR");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_edit_memory);

        imageAdapter = new AddMemoryImageAdapter(this);
        videoAdapter = new AddMemoryVideoAdapter(this);
        RecyclerView tagsRV = findViewById(R.id.tagsLayout_cememory);
        tagAdapter = new AddMemoryTagAdapter(new LinkedList<>(), tagsRV);

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
        MemError = findViewById(R.id.error_cememory);
        imageLiner = findViewById(R.id.LinerForImage);
        ourScroller = findViewById(R.id.scrollView_cememory);
        AddPicTxV = findViewById(R.id.AddPicTV_cememory);
        error3 = findViewById(R.id.error3);

        if (userStr.equals("ERR") && intent.getSerializableExtra("user") == null) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("You are not registered.")
                    .setCancelable(false)
                    .setPositiveButton("login", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            Intent i = new Intent(getApplicationContext(), LogIn.class);
                            startActivity(i);
                        }
                    })
                    .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            finish();
                        }
                    });
            AlertDialog alert = builder.create();
            alert.show();
        }

        yearChckBx1=findViewById(R.id.yearChckBx1);
        yearChckBx1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                checked1 =((CheckBox) v).isChecked();
                int yearSpinnerI1 = Resources.getSystem().getIdentifier("year", "id", "android");
                View yearSpinnerV1 = memoryDatePicker.findViewById(yearSpinnerI1);

                if(checked1) {
                    if (yearSpinnerV1 != null) {
                        yearSpinnerV1.setVisibility(View.GONE);
                    }
                } else {
                    if (yearSpinnerV1 != null) {
                        yearSpinnerV1.setVisibility(View.VISIBLE);
                    }
                }
            }
        });

        monthChckBx1 = findViewById(R.id.monthChckBx1);
        monthChckBx1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checked2 = ((CheckBox) v).isChecked();
                int monthSpinnerI1 = Resources.getSystem().getIdentifier("month", "id", "android");
                View monthSpinnerV1 = memoryDatePicker.findViewById(monthSpinnerI1);

                if (checked2) {
                    if (monthSpinnerI1 != 0) {
                        if (monthSpinnerV1 != null) {
                            monthSpinnerV1.setVisibility(View.GONE);
                        }
                    }
                } else {
                    if (monthSpinnerI1 != 0) {
                        if (monthSpinnerV1 != null) {
                            monthSpinnerV1.setVisibility(View.VISIBLE);
                        }
                    }
                }
            }
        });

        dayChckBx1 = findViewById(R.id.dayChckBx1);
        dayChckBx1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checked3 = ((CheckBox) v).isChecked();
                int daySpinnerI1 = Resources.getSystem().getIdentifier("day", "id", "android");
                View daySpinnerV1 = memoryDatePicker.findViewById(daySpinnerI1);

                if (checked3) {
                    if (daySpinnerI1 != 0) {
                        if (daySpinnerV1 != null) {
                            daySpinnerV1.setVisibility(View.GONE);
                        }
                    }
                } else {
                    if (daySpinnerI1 != 0) {
                        if (daySpinnerV1 != null) {
                            daySpinnerV1.setVisibility(View.VISIBLE);
                        }
                    }
                }
            }
        });

        showMemDate = findViewById(R.id.showMemDate);
        Calendar MemCal = Calendar.getInstance();
        MemCal.setTimeZone(TimeZone.getTimeZone("Asia/Jerusalem"));
        year1 = MemCal.get(Calendar.YEAR);
        month1 = MemCal.get(Calendar.MONTH);
        day1 = MemCal.get(Calendar.DAY_OF_MONTH);

        memoryDatePicker = findViewById(R.id.memoryDatePicker);
        memoryDatePicker.setMaxDate(new Date().getTime()); // set today to be the maximum date
        memoryDatePicker.init(year1, month1, day1, (memoryDatePicker, year, month, day) -> DateOfMem());


        if (memory == null) {
            pageTitle.setText("Add Memory");
            memory = new Memory();
//            user = (User) intent.getSerializableExtra(KEY_USER);

            //Log.d("MOO", "got User: " + userStr);
            if (userStr != "ERR") {
                user = gson.fromJson(userStr, User.class);
                memory.setUser(user);
            } else {
                user = (User) intent.getSerializableExtra("user");
            }
        } else {
            create = false;
            tagAdapter.tags.clear();
            tagAdapter.notifyDataSetChanged();
            pageTitle.setText("Edit Memory");
            user = memory.getUser();
            editTextDescription.setText(memory.getDescription());
            editTextLocation.setText(memory.getLocation());
            if (memory.getMemoryDate() != null) {
                MemDate = memory.getMemoryDate().getTime();
                memoryDatePicker.updateDate(memory.getMemoryDate().get(Calendar.YEAR),
                        memory.getMemoryDate().get(Calendar.MONTH),
                        memory.getMemoryDate().get(Calendar.DAY_OF_MONTH));
            }

            if (memory.getFeeling() != null)
                selectEmoji(memory.getFeeling());

            List<Uploadable> image_uris = new ArrayList();
            List<Uploadable> video_uris = new ArrayList<>();
            for (Picture p : memory.getPictures()) {
                Uploadable u = new Uploadable(p.getLink());
                u.setUploaded(true);
                image_uris.add(u);
//                imageAdapter.upload_start++;
            }
            for (Video v : memory.getVideos()) {
                Uploadable u = new Uploadable(v.getLink());
                u.setUploaded(true);
                video_uris.add(u);
//                videoAdapter.upload_start++;
            }

            imageAdapter.data.addAll(image_uris);
            imageAdapter.notifyDataSetChanged();
            videoAdapter.data.addAll(video_uris);
            videoAdapter.notifyDataSetChanged();
            tagAdapter.tags.addAll(memory.getTags());
            tagAdapter.notifyDataSetChanged();
        }

        Story story = (Story) intent.getSerializableExtra(KEY_CREATE);
        if (story != null) {
            memory.setStory(story);
        }
        memory.setUser(user);

        smileb.setOnClickListener(this);
        sadb.setOnClickListener(this);
        loveb.setOnClickListener(this);

        svbtn.setOnClickListener(this);
        cnslbtn.setOnClickListener(this);

        RecyclerView rvp = findViewById(R.id.add_pictures_rv_cememory);

        rvp.setAdapter(imageAdapter);
        rvp.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,
                false));

        RecyclerView rvv = findViewById(R.id.add_videos_rv_cememory);

        rvv.setAdapter(videoAdapter);
        rvv.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,
                false));

        //   editTextDescription.addTextChangedListener(SaveTextWatcher);
        // editTextLocation.addTextChangedListener(SaveTextWatcher);


        tagsRV.setAdapter(tagAdapter);
        tagsRV.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,
                false));
    }

    private void DateOfMem(){
        if ((!checked1 && checked2 && !checked3) || (checked1 && checked2 && !checked3) ) {
            //only day is shown or only day and year are shown
            error3.setText("invalid date");
            error3.setVisibility(View.VISIBLE);
            return;
        }
        if (!checked1){
            //year is changeable
            cal.set(Calendar.YEAR,memoryDatePicker.getYear());
        }
        if (!checked2){
            //month is changeable
            cal.set(Calendar.MONTH,memoryDatePicker.getMonth());

        }
        if (!checked3){
            //day is changeable
            cal.set(Calendar.DAY_OF_MONTH,memoryDatePicker.getDayOfMonth());
        }
        MemDate=cal.getTime();
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        showMemDate.setText(dateFormat.format(MemDate));
        error3.setVisibility(View.GONE);
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
                    // imageLiner.setBackground(getResources().getDrawable(R.drawable.error_image_background));
                    //  imageLiner.removeAllViews();
                    this.svbtn.setEnabled(true);
                    saveMemory(v);
                } else {
                    TextView addPicTV = findViewById(R.id.AddPicTV_cememory);
                    addPicTV.setTextColor(getResources().getColor(R.color.colorError));
                    TextView addVidTV = findViewById(R.id.AddVidTV_cememory);
                    addVidTV.setTextColor(getResources().getColor(R.color.colorError));
                    TextView addDesc = findViewById(R.id.AddDescriptionTV_cememory);
                    addDesc.setTextColor(getResources().getColor(R.color.colorError));
                    // displayToast("Error , Please try filling out the fields again");
                }
                break;
            case R.id.Cancelbtn_cememory:
                ShowAlertDialog(this, "", "Are you sure you want to cancel ?");
                // finish();
                break;
            case R.id.back_button_cememory:
                ShowAlertDialog(this, "", "Are you sure you want to leave ?");
                // finish();
                break;

        }
    }

    public void ShowAlertDialog(Activity activity, String title, CharSequence message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(message).setCancelable(false).setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        }).setPositiveButton("YES", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                activity.finish();
            }
        });
        AlertDialog alert = builder.create();
        alert.show();
    }

    /**
     * AlertDialog.Builder builder = new AlertDialog.Builder(this);
     * builder.setMessage("Are you sure you want to exit?")
     * .setCancelable(false)
     * .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
     * public void onClick(DialogInterface dialog, int id) {
     * MyActivity.this.finish();
     * }
     * })
     * .setNegativeButton("No", new DialogInterface.OnClickListener() {
     * public void onClick(DialogInterface dialog, int id) {
     * dialog.cancel();
     * }
     * });
     * AlertDialog alert = builder.create();
     * alert.show();
     **/
    public boolean CheckValidation(View v) {        //(Memory m) {
        if ((editTextDescription.getText().toString().equals("")) && (imageAdapter.data.isEmpty()) &&
                (videoAdapter.data.isEmpty())) {
            MemError.setText("Enter at Least one of The above!");
            MemError.setVisibility(View.VISIBLE);
            ourScroller.fullScroll(ScrollView.FOCUS_UP);// .fullScroll(ScrollView.FOCUS_UP);
            //return false;
            // GradientDrawable gradientDrawable=new GradientDrawable();
            //gradientDrawable.setStroke(4,getResources().getColor(R.color.colorError));
            //Drawable d = getResources().getDrawable(R.drawable.error_image_background);
            //imageLiner.setBackground(gradientDrawable);
            // imageLiner.setBackground(getResources().getDrawable(R.drawable.error_image_background));
            return false;
        }
        if ((!checked1 && checked2 && !checked3) || (checked1 && checked2 && !checked3)) {
            return false;
        }
        if (editTextLocation.toString() != null) {

        }
        /**displayToast("You should either enter an image or a video or description for your memory!");
         return false;
         }
         }
         /* if (today.before(MemDate)) {
         displayToast("You have selected invalid date , please choose valid date again ");
         return false;
         }           RecycleImage.setBackground(d);*/
        //  editTextDescription.setHintTextColor(@);

        /**   if (MemDate.before(BirthDate)) {
         displayToast("You have selected invalid date ,Memory can't occur before birth date, please choose valid date again ");
         return false;
         }
         if (MemDate.after(DeathDate)) {
         displayToast("You have selected invalid date ,Memory can't occur after Death date, please choose valid date again ");
         return false;
         } else
         dateFlag = true;*/
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
                    imageAdapter.data.add(new Uploadable(currentUri.toString()));
                    // imageLiner.removeView(getResources().getDrawable(R.drawable.error_image_background));

                    /****/

                }
            } else if (data.getData() != null) {
                imageAdapter.data.add(new Uploadable(data.getData().toString()));


            }
            imageAdapter.notifyDataSetChanged();
        } else if (requestCode == AddMemoryVideoAdapter.ADDMEMORY_VIDEO) {
            if (data.getClipData() != null) {
                int count = data.getClipData().getItemCount();
                for (int i = 0; i < count; i++) {
                    Uri currentUri = data.getClipData().getItemAt(i).getUri();
                    videoAdapter.data.add(new Uploadable(currentUri.toString()));
                }
            } else if (data.getData() != null) {
                videoAdapter.data.add(new Uploadable(data.getData().toString()));
            }
            videoAdapter.notifyDataSetChanged();
        }
    }

//    public void showDatePicker(View view) {
//        DialogFragment newFragment = new DatePickerFragmentCEMemory();
//        newFragment.show(getSupportFragmentManager(), "datePicker");
//    }
//
//    public void processDatePickerResult(int year, int month, int day) {
//
//        String month_string = Integer.toString(month + 1);
//        String day_string = Integer.toString(day);
//        String year_string = Integer.toString(year);
////
//
//        currentDate = day_string + "/" + month_string + "/" + year_string;
//        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
//        Calendar c = Calendar.getInstance();
//        c.set(year, month, day);
//        MemDate = c.getTime();
//
//        TextView dayDate = findViewById(R.id.day_text_cememory);
//        TextView monthDate = findViewById(R.id.month_text_cememory);
//        TextView yearDate = findViewById(R.id.year_text_cememory);
//
//        dayDate.setText(day_string);
//        monthDate.setText(month_string);
//        yearDate.setText(year_string);
//    }

    /** public void ShowAlertDialog(Activity activity, String title, CharSequence message) {
     AlertDialog.Builder builder = new AlertDialog.Builder(this);
     builder.setMessage(message).setCancelable(false).setNegativeButton("No", new DialogInterface.OnClickListener() {
    @Override
    public void onClick(DialogInterface dialogInterface, int i) {
    dialogInterface.cancel();
    }
    }).setPositiveButton("YES", new DialogInterface.OnClickListener() {
    @Override
    public void onClick(DialogInterface dialogInterface, int i) {
    activity.finish();
    }
    });
     AlertDialog alert = builder.create();
     alert.show();
     }
     **/
    public void closeActivity(View view) {
        finish();
    }


    public void saveMemory(View view) {
        locationText = findViewById(R.id.memLocation_cememory);
        memory.setLocation(locationText.getText().toString());

        memory.setDescription(editTextDescription.getText().toString());
        memory.setFeeling(SelectedEmoji);
        memory.setMemoryDate(MemDate);
        displayToast("Data saved.");
        OurStoryService service = WebFactory.getService();
        Intent intent = new Intent();

        ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Uploading media...");
        progressDialog.show();

        double[] progress = new double
                [imageAdapter.data.size() + videoAdapter.data.size()];

        for (int i = 0; i < progress.length; i++) {
            progress[i] = 0;
        }

        FirebaseImageWrapper wrapper = new FirebaseImageWrapper("memory_media");
        List<StorageTask<UploadTask.TaskSnapshot>> tasks = new LinkedList<>();

        for (int i = 0; i < imageAdapter.data.size(); i++) {
            Uploadable u = imageAdapter.data.get(i);
            if (u.isUploaded()) continue;
            String uri = u.getUrl();
            int finalI = i;
            tasks.add(wrapper.uploadImg(Uri.parse(uri)).addOnSuccessListener(taskSnapshot -> {
                Uploadable uploadable = imageAdapter.data.get(finalI);
                uploadable.setUploaded(true);
                uploadable.setUrl(taskSnapshot.getDownloadUrl().toString());
                progress[finalI] = 100;
            }).addOnProgressListener(taskSnapshot -> {
                // We need to set the progress relative to both the current file and the other files
                double currentFileProgress = taskSnapshot.getBytesTransferred() /
                        taskSnapshot.getTotalByteCount();
                progress[finalI] = currentFileProgress * 100;
                double progressAvg = 0;
                for (double p : progress)
                    progressAvg += p;
                progressAvg /= progress.length;
                progressDialog.setTitle("Uploading media: " + (int) Math.ceil(progressAvg) + "%");
            }));
        }

        // TODO: Maybe we can avoid this code duplication
        for (int i = imageAdapter.data.size(); i < progress.length; i++) {
            Uploadable u = videoAdapter.data.get(i - imageAdapter.data.size());
            if (u.isUploaded()) continue;
            String uri = u.getUrl();
            int finalI = i;
            int offset = imageAdapter.data.size();
            tasks.add(wrapper.uploadImg(Uri.parse(uri)).addOnSuccessListener(taskSnapshot -> {
                u.setUrl(taskSnapshot.getDownloadUrl().toString());
                u.setUploaded(true);
//                videoAdapter.data.set(finalI, uploadable);
                progress[finalI] = 100;
            }).addOnProgressListener(taskSnapshot -> {
                double currentFileProgress = taskSnapshot.getBytesTransferred() /
                        taskSnapshot.getTotalByteCount();
                progress[finalI] = currentFileProgress * 100;
                double progressAvg = 0;
                for (double p : progress)
                    progressAvg += p;
                progressAvg /= progress.length;
                progressDialog.setTitle("Uploading media: " + (int) Math.ceil(progressAvg) + "%");
            }));
        }


        Tasks.whenAll(tasks).addOnSuccessListener(aVoid -> {
            progressDialog.dismiss();
            ArrayList<String> pictures = new ArrayList<>();
            ArrayList<String> videos = new ArrayList<>();
            for (Uploadable u : imageAdapter.data) {
                pictures.add(u.getUrl());
            }
            for (Uploadable u : videoAdapter.data) {
                videos.add(u.getUrl());
            }

            ArrayList<String> tags = new ArrayList<>();
            for (Tag t : tagAdapter.tags) {
                tags.add(t.getLabel());
            }
            if (create) {
                service.CreateMemory(memory)
                        .subscribeOn(Schedulers.newThread())
                        .flatMap(mem -> {
                            if (mem == null) return null;
                            long memId = mem.getId();
                            HashMap<String, List<String>> hm = new HashMap<>();
                            hm.put("pictures", pictures);
                            hm.put("videos", videos);
                            hm.put("tags", tags);

                            return service.SetMediaToMemory(memId, hm);
                        })
                        .subscribe(finalResult -> {
                            intent.putExtra(KEY_MEMID, finalResult.getId());
                            setResult(RESULT_OK, intent);
                            finish();
                        });

            } else {
                memory.getPictures().clear();
                memory.getVideos().clear();
                memory.getTags().clear();
                service.EditMemory(memory.getId(), memory)
                        .subscribeOn(Schedulers.newThread())
                        .flatMap(mem -> {
                            if (mem == null) return null;
                            long memId = mem.getId();
                            HashMap<String, List<String>> hm = new HashMap<>();
                            hm.put("pictures", pictures);
                            hm.put("videos", videos);
                            hm.put("tags", tags);

                            return service.SetMediaToMemory(memId, hm);
                        })
                        .subscribe(finalResult -> {
                            intent.putExtra(KEY_MEMID, finalResult.getId());
                            setResult(RESULT_OK, intent);
                            finish();
                        });
//                service.EditMemory(memory.getId(), memory).enqueue(new Callback<Memory>() {
//                    @Override
//                    public void onResponse(Call<Memory> call, Response<Memory> response) {
//                        HashMap<String, List<String>> hm = new HashMap<>();
//                        hm.put("pictures", pictures);
//                        hm.put("videos", videos);
//                        hm.put("tags", tags);
//
////                        service.SetMediaToMemory(memory.getId(), hm).enqueue(new Callback<Memory>() {
////                            @Override
////                            public void onResponse(Call<Memory> call, Response<Memory> response) {
////                                intent.putExtra(KEY_MEMID, memory.getId());
////                                setResult(RESULT_OK, intent);
////                                finish();
////                            }
////
////                            @Override
////                            public void onFailure(Call<Memory> call, Throwable t) {
////
////                            }
////                        });
//                    }
//
//                    @Override
//                    public void onFailure(Call<Memory> call, Throwable t) {
//
//                    }
//                });
            }
        });


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