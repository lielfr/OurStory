package org.tsofen.ourstory.StoryTeam;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;


import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.Log;

import android.view.Gravity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.UploadTask;

import org.tsofen.ourstory.EditCreateMemory.CreateEditMemoryActivity;
import org.tsofen.ourstory.FirebaseImageWrapper;
import org.tsofen.ourstory.R;

import org.tsofen.ourstory.UserModel.LogIn;
import org.tsofen.ourstory.UserModel.RegistrationPage1;

import org.tsofen.ourstory.UserModel.UserStatusCheck;
import org.tsofen.ourstory.model.api.Owner;
import org.tsofen.ourstory.model.api.Story;
import org.tsofen.ourstory.web.OurStoryService;
import org.tsofen.ourstory.web.WebFactory;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CreateStory extends AppCompatActivity implements Serializable {

    ScrollView sv;

    Bitmap bitmap;
    Uri filePath;
    String fileURI;

    Owner owner;
    Story result;

    ImageView image;
    EditText firstName, lastName;
    TextView error1, error2, error3, error4;
    boolean f1 = false, f2 = false, f3 = false, f4 = false;

    TextView showBirthDate, showDeathDate;
    private int year1, month1 = 1, day1 = 1, year2, month2 = 1, day2 = 1;
    CheckBox monthChckBx1, monthChckBx2, dayChckBx1, dayChckBx2;
    boolean checked1 = false, checked2 = false, checked3 = false, checked4 = false;
    String d1s = "", d2s = "", m1s = "", m2s = "", y1s = "", y2s = "";
    String BirthDate, DeathDate;
    DatePicker birthDatePicker, deathDatePicker;
    int birthDateFields = 3, deathDateFields = 3;
    Date today = new Date();


    OurStoryService Wepengine;

    Long userid;
    Long Storyid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_createstory);

        sv = findViewById(R.id.sv);

        Wepengine = WebFactory.getService();

        firstName = findViewById(R.id.firstNameEditText);
        firstName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (!Character.isUpperCase(editable.charAt(0))) {
                    char c = Character.toUpperCase(editable.charAt(0));
                    String str = editable.replace(0, 1, c + "").toString();

                    firstName.setText(str);
                }
            }
        });

        lastName = findViewById(R.id.lastNameEditText);
        lastName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (!Character.isUpperCase(editable.charAt(0))) {
                    char c = Character.toUpperCase(editable.charAt(0));
                    String str = editable.replace(0, 1, c + "").toString();

                    lastName.setText(str);
                }
            }
        });

        error1 = findViewById(R.id.error1);
        error2 = findViewById(R.id.error2);
        error3 = findViewById(R.id.error3);
        error4 = findViewById(R.id.error4);

        monthChckBx1 = findViewById(R.id.monthChckBx1);
        monthChckBx1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checked1 = ((CheckBox) v).isChecked();
                int monthSpinnerI1 = Resources.getSystem().getIdentifier("month", "id", "android");
                View monthSpinnerV1 = birthDatePicker.findViewById(monthSpinnerI1);

                if (checked1) {
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
        monthChckBx2 = findViewById(R.id.monthChckBx2);
        monthChckBx2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checked3 = ((CheckBox) v).isChecked();
                int monthSpinnerI2 = Resources.getSystem().getIdentifier("month", "id", "android");
                View monthSpinnerV2 = deathDatePicker.findViewById(monthSpinnerI2);

                if (checked3) {
                    if (monthSpinnerI2 != 0) {
                        if (monthSpinnerV2 != null) {
                            monthSpinnerV2.setVisibility(View.GONE);
                        }
                    }
                } else {
                    if (monthSpinnerI2 != 0) {
                        if (monthSpinnerV2 != null) {
                            monthSpinnerV2.setVisibility(View.VISIBLE);
                        }
                    }
                }
            }
        });
        dayChckBx1 = findViewById(R.id.dayChckBx1);
        dayChckBx1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checked2 = ((CheckBox) v).isChecked();
                int daySpinnerI1 = Resources.getSystem().getIdentifier("day", "id", "android");
                View daySpinnerV1 = birthDatePicker.findViewById(daySpinnerI1);

                if (checked2) {
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
        dayChckBx2 = findViewById(R.id.dayChckBx2);
        dayChckBx2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checked4 = ((CheckBox) v).isChecked();
                int daySpinnerI2 = Resources.getSystem().getIdentifier("day", "id", "android");
                View daySpinnerV2 = deathDatePicker.findViewById(daySpinnerI2);

                if (checked4) {
                    if (daySpinnerI2 != 0) {
                        if (daySpinnerV2 != null) {
                            daySpinnerV2.setVisibility(View.GONE);
                        }
                    }
                } else {
                    if (daySpinnerI2 != 0) {
                        if (daySpinnerV2 != null) {
                            daySpinnerV2.setVisibility(View.VISIBLE);
                        }
                    }
                }
            }
        });


        showBirthDate = findViewById(R.id.showBirthDate);
        Calendar birthCal = Calendar.getInstance();
        birthCal.setTimeZone(TimeZone.getTimeZone("Asia/Jerusalem"));
        year1 = birthCal.get(Calendar.YEAR);
        month1 = birthCal.get(Calendar.MONTH);
        day1 = birthCal.get(Calendar.DAY_OF_MONTH);

        birthDatePicker = findViewById(R.id.birthDatePicker);
        birthDatePicker.setMaxDate(new Date().getTime()); // set today to be the maximum date
        birthDatePicker.init(year1 - 50, month1, day1, new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker birthDatePicker, int year, int month, int day) {

                CreateStory.this.year1 = year;
                CreateStory.this.month1 = month;
                CreateStory.this.day1 = day;

                Birth();

            }
        });


        showDeathDate = findViewById(R.id.showDeathDate);
        Calendar deathCal = Calendar.getInstance();
        deathCal.setTimeZone(TimeZone.getTimeZone("Asia/Jerusalem"));
        year2 = deathCal.get(Calendar.YEAR);
        month2 = deathCal.get(Calendar.MONTH);
        day2 = deathCal.get(Calendar.DAY_OF_MONTH);

        deathDatePicker = findViewById(R.id.deathDatePicker);
        deathDatePicker.setMaxDate(new Date().getTime()); // set today to be the maximum date
        deathDatePicker.init(year2, month2, day2, new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker deathDatePicker, int year, int month, int day) {

                CreateStory.this.year2 = year;
                CreateStory.this.month2 = month;
                CreateStory.this.day2 = day;

                Death();

            }
        });


        Intent intent = getIntent();  //getting the user from the server
        if (UserStatusCheck.getUserStatus().equals("not a visitor")) {
            if (intent.getStringExtra("userId") != null) {
                userid = Long.parseLong(intent.getStringExtra("userId"));
                Wepengine.GetUserById(userid).enqueue(new Callback<Owner>() {
                    @Override
                    public void onResponse(Call<Owner> call, Response<Owner> response) {
                        if (response.body() != null) {
                            owner = response.body();
                            Toast.makeText(CreateStory.this, "Owner name is " + owner.getFirstName(), Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(CreateStory.this, "Owner By API is null !!", Toast.LENGTH_SHORT).show();
                        }

                    }

                    @Override
                    public void onFailure(Call<Owner> call, Throwable t) {
                        Toast.makeText(CreateStory.this, "Cant connect to Server In order ro get the user", Toast.LENGTH_SHORT).show();
                    }
                });
            } else {
                Toast.makeText(this, "DIDNT catch the userID from the intent !!", Toast.LENGTH_SHORT).show();
            }
        } else if (UserStatusCheck.getUserStatus().equals("visitor")) {
            // show a pop up with a log in option
            AlertDialog.Builder myAlertBuilder = new AlertDialog.Builder(CreateStory.this);
            // Set the dialog title and message
            myAlertBuilder.setTitle("Alert");
            myAlertBuilder.setMessage("You need to Log In / Register first in order to create a new story.");
            // Add the dialog log in button
            myAlertBuilder.setPositiveButton("Log In", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    // User clicked Log In button.
                    Intent i = new Intent(CreateStory.this, LogIn.class);
                    //startActivity(i);
                    i.putExtra("logtocreate", "logToCreate");
                    startActivityForResult(i, 1);
                }
            });
            myAlertBuilder.setNegativeButton("Register", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    // User clicked Log In button.
                    Intent i = new Intent(CreateStory.this, RegistrationPage1.class);
                    //startActivity(i);
                    i.putExtra("registertocreate", "registerToCreate");
                    startActivityForResult(i, 1);
                }
            });
            // Create and show the AlertDialog.
            myAlertBuilder.show();
        }
    }


    private void Birth() {

        StringBuffer strBuffer = new StringBuffer();
        strBuffer.append("Birth Date: ");

        if (!checked1 && !checked2) {
            birthDateFields = 3;
            // three fields are shown
            strBuffer.append(this.year1);
            strBuffer.append("/");
            strBuffer.append(this.month1 + 1);
            strBuffer.append("/");
            strBuffer.append(this.day1);
            error3.setVisibility(View.GONE);
        } else if (!checked1 && checked2) {
            day1 = 1;
            birthDateFields = 2;
            // two fields are shown
            strBuffer.append(this.year1);
            strBuffer.append("/");
            strBuffer.append(this.month1 + 1);
            error3.setVisibility(View.GONE);
        } else if (checked1 && !checked2) {
            birthDateFields = 0;
            error3.setText("You must enter month of birth");
            error3.setVisibility(View.VISIBLE);
            f3 = false;
        } else {
            birthDateFields = 1;
            month1 = 1;
            day1 = 1;
            // 1 field is shown
            strBuffer.append(this.year1);
            error3.setVisibility(View.GONE);
        }
        f3 = true;
        showBirthDate.setText(strBuffer.toString());
        showBirthDate.setTextColor(getResources().getColor(R.color.colorGrayHint));
        showBirthDate.setGravity(Gravity.CENTER);
        showBirthDate.setTextSize(getResources().getDimension(R.dimen.textSize));
    }

    private void Death() {

        StringBuffer strBuffer = new StringBuffer();
        strBuffer.append("Death Date: ");

        if (!checked3 && !checked4) {
            deathDateFields = 3;
            // three fields are shown
            strBuffer.append(this.year2);
            strBuffer.append("/");
            strBuffer.append(this.month2 + 1);
            strBuffer.append("/");
            strBuffer.append(this.day2);
            error4.setVisibility(View.GONE);
        } else if (!checked3 && checked4) {
            day2 = 1;
            deathDateFields = 2;
            // two fields are shown
            strBuffer.append(this.year2);
            strBuffer.append("/");
            strBuffer.append(this.month2 + 1);
            error4.setVisibility(View.GONE);
        } else if (checked3 && !checked4) {
            deathDateFields = 0;
            error4.setText("You must enter month of death");
            error4.setVisibility(View.VISIBLE);
            f4 = false;
        } else {
            deathDateFields = 1;
            month2 = 1;
            day2 = 1;
            // 1 field is shown
            strBuffer.append(this.year2);
            error4.setVisibility(View.GONE);
        }
        f4 = true;
        showDeathDate.setText(strBuffer.toString());
        showDeathDate.setTextColor(getResources().getColor(R.color.colorGrayHint));
        showDeathDate.setGravity(Gravity.CENTER);
        showDeathDate.setTextSize(getResources().getDimension(R.dimen.textSize));
    }


    private boolean validateName(EditText edtTxt, String str, int i) {

        String errorText = "";
        int flag = 1;

        str = str.trim();
        if (str.length() == 0) {
            errorText = "Field cannot be empty!";
            flag = 0;
        } else if (!str.matches("[a-zA-Z]+")) {
            errorText = "Only Alphabetical characters allowed!";
            flag = 0;
        }

        if (flag == 0) {
            if (i == 1) {
                error1.setText(errorText);
                error1.setVisibility(View.VISIBLE);
                return false;
            } else if (i == 2) {
                error2.setText(errorText);
                error2.setVisibility(View.VISIBLE);
                return false;
            }
        } else {
            if (i == 1) {
                error1.setVisibility(View.GONE);
                return true;
            } else if (i == 2) {
                error2.setVisibility(View.GONE);
                return true;
            }
        }
        return false;
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
                FirebaseImageWrapper imageup = new FirebaseImageWrapper();
                imageup.uploadImg(filePath).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        fileURI = taskSnapshot.getDownloadUrl().toString();
                        Toast.makeText(CreateStory.this, "upload image and save the download URI Succeeded", Toast.LENGTH_SHORT).show();

                    }

                });


            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }


    // launch ViewStoryActivity
    public void viewCreatedStory(View view) {


       /* Owner owner = new Owner(21);      //IN this section we will check the user/visitor and act accordingly Dont touch IT!!!!!
        t.GetUserById(2).enqueue(new Callback<Owner>() {
            @Override
            public void onResponse(Call<Owner> call, Response<Owner> response) {

               owner=response.body() ;
                if (owner!=null) {
                    //   Toast.makeText(CreateStory.this, "the story name is " + owner.getFirstName(), Toast.LENGTH_SHORT).show();
                }else{
                    // Toast.makeText(CreateStory.this, "creating story was failed", Toast.LENGTH_SHORT).show();
                }
                //Toast.makeText(CreateStory.this, "creating story was successful", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onFailure(Call<Owner> call, Throwable t) {
                //   Toast.makeText(CreateStory.this, "creating story was failed", Toast.LENGTH_SHORT).show();

            }
        });*/


        Intent i = new Intent(this, ViewStory.class);

        Intent cm = new Intent(this, CreateEditMemoryActivity.class);

        // Names Validation
        String fns = firstName.getText().toString();
        f1 = validateName(firstName, fns, 1);
        String lns = lastName.getText().toString();
        f2 = validateName(lastName, lns, 2);


        if (day1 == 1) d1s = "";
        if (day2 == 1) d2s = "";
        if (month1 == 1) m1s = "";
        if (month2 == 1) m2s = "";

        y1s = String.valueOf(year1);
        y2s = String.valueOf(year2);


        int tag1 = R.drawable.family_vs, tag2 = R.drawable.sports_vs, tag3 = R.drawable.vacation_vs;
        i.putExtra("tag1", tag1);
        i.putExtra("tag2", tag2);
        i.putExtra("tag3", tag3);
        String ttag1 = "Sports", ttag2 = "family", ttag3 = "Vacations";
        i.putExtra("ttag3", ttag3);
        i.putExtra("ttag1", ttag1);
        i.putExtra("ttag2", ttag2);


        ImageView iv = findViewById(R.id.profilePic); //pass the profile image


        //Toast.makeText(this, today.toString(), Toast.LENGTH_LONG).show();
        Date d1 = new Date(year1 - 1900, month1, day1);
        Date d2 = new Date(year2 - 1900, month2, day2);
        // df is the date format for the server, we MUST send a full date to the server
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
        // df[i] is the date for the next activity
        DateFormat df3 = new SimpleDateFormat("yyyy/MM/dd"); // send three fields to the next activity
        DateFormat df2 = new SimpleDateFormat("yyyy/MM"); // two fields
        DateFormat df1 = new SimpleDateFormat("yyyy"); // one field

        // send to the server
        BirthDate = df.format(d1);
        DeathDate = df.format(d2);

        validateDates(d1, d2, today);

        if ((f1 && f2 && f3 && f4)) {

            // Send data to next activity / creating local Story object and building a custom made dates
            String nameofperson = fns + " " + lns; // name is done

            Story story;
            if (fileURI == null) {
                story = new Story(owner, nameofperson, BirthDate, DeathDate, null);
            } else {
                story = new Story(owner, nameofperson, BirthDate, DeathDate, fileURI);
            }

            Wepengine.CreateStory(story).enqueue(new Callback<Story>() {
                @Override
                public void onResponse(Call<Story> call, Response<Story> response) {
                    result = response.body();
                    if (result != null) {
                        Toast.makeText(CreateStory.this, "the story " + result.getNameOfPerson() + " was created succefully", Toast.LENGTH_SHORT).show();

                        // pass birth date to the next activity
                        if (birthDateFields == 3) {
                            i.putExtra("date1", df3.format(d1));
                        } else if (birthDateFields == 2) {
                            i.putExtra("date1", df2.format(d1));
                        } else if (birthDateFields == 1) {
                            i.putExtra("date1", df1.format(d1));
                        }

                        // pass death date to the next activity
                        if (deathDateFields == 3) {
                            i.putExtra("date2", df3.format(d2)); // pass it as a Date to thr next activity
                        } else if (deathDateFields == 2) {
                            i.putExtra("date2", df2.format(d2));
                        } else if (deathDateFields == 1) {
                            i.putExtra("date2", df1.format(d2));
                        }

                        i.putExtra("name", nameofperson);
                        if (view.getId() == R.id.create) {
                            i.putExtra("id", String.valueOf(result.getStoryId()));
                            i.putExtra("Button", "justcreate");
                            //startActivity(i);
                        } else {
                            // i.putExtra("id", String.valueOf(result.getStoryId()));
                            i.putExtra("result", result);
                            i.putExtra("Button", "createandadd");
                        }
                        startActivity(i);
                    } else {
                        Toast.makeText(CreateStory.this, "creating story was failed please try again later", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<Story> call, Throwable t) {
                    Toast.makeText(CreateStory.this, "onFailure story was failed", Toast.LENGTH_SHORT).show();
                }
            });

//        String n = "mali"; /// please dont delete this
//            t.GetStoriesByName(n).enqueue(new Callback<ArrayList<ListOfStory>>() {
//                @Override
//                public void onResponse(Call<ArrayList<ListOfStory>> call, Response<ArrayList<ListOfStory>> response) {
//                    ArrayList<ListOfStory> linked = response.body();
//                    if (linked!=null) {
//                          Toast.makeText(CreateStory.this, "size =" + linked.size(), Toast.LENGTH_SHORT).show();
//                    }else{
//                        Toast.makeText(CreateStory.this, "getting was failed", Toast.LENGTH_SHORT).show();
//                    }
//                }
//
//                @Override
//                public void onFailure(Call<ArrayList<ListOfStory>> call, Throwable t) {
//
//                }
//            });
            // startActivity(intent);

        } else {
            // f1==false || f2==false || f3==false || f4==false
            sv.fullScroll(ScrollView.FOCUS_UP);
        }
    }

    private void validateDates(Date d1, Date d2, Date today) {
        // dates validation
        if (d2.after(today)) {
            error4.setText("Please enter a valid date");
            error4.setVisibility(View.VISIBLE);
            f4 = false;
            f3 = false;
        }
        if (d1.after(d2)) {
            error4.setText("Please enter valid dates!");
            error3.setVisibility(View.VISIBLE);
            error3.setText("Please enter valid dates!");
            error4.setVisibility(View.VISIBLE);
            f4 = false;
            f3 = false;
        } else if (d2.before(d1)) { //redundant
            error4.setText("Please enter valid dates!");
            error3.setVisibility(View.VISIBLE);
            error3.setText("Please enter valid dates!");
            error4.setVisibility(View.VISIBLE);
            f4 = false;
            f3 = false;
        } else { // all good
            error3.setVisibility(View.GONE);
            error4.setVisibility(View.GONE);
            f4 = true;
            f3 = true;
        }
    }

    public void closeActivity(View view) {
        finish();
    }
}