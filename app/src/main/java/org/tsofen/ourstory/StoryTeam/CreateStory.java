package org.tsofen.ourstory.StoryTeam;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.UploadTask;

import org.tsofen.ourstory.EditCreateMemory.CreateEditMemoryActivity;
import org.tsofen.ourstory.FirebaseImageWrapper;
import org.tsofen.ourstory.R;
import org.tsofen.ourstory.UserModel.LogIn;
import org.tsofen.ourstory.model.api.Owner;
import org.tsofen.ourstory.model.api.Story;
import org.tsofen.ourstory.web.OurStoryService;
import org.tsofen.ourstory.web.WebFactory;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CreateStory extends AppCompatActivity implements Serializable {

    Bitmap bitmap;
    Uri filePath;

    Owner owner ;
    Story result;

    ImageView image;
    EditText firstName, lastName;
    TextView error1, error2, error3, error4;
    boolean f1 = false, f2 = false, f3=false, f4=false;

    TextView showBirthDate, showDeathDate;
    private int year1, month1=1, day1=1, year2, month2=1, day2=1;
    CheckBox monthChckBx1, monthChckBx2, dayChckBx1, dayChckBx2;
    boolean checked1=false, checked2=false, checked3=false, checked4=false;
    String d1s="", d2s="", m1s="", m2s="", y1s="", y2s="";
    String BirthDate, DeathDate;
    DatePicker birthDatePicker, deathDatePicker;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_createstory);

        firstName = findViewById(R.id.firstNameEditText);
        lastName = findViewById(R.id.lastNameEditText);

        error1=findViewById(R.id.error1);
        error2=findViewById(R.id.error2);
        error3=findViewById(R.id.error3);
        error4=findViewById(R.id.error4);

        monthChckBx1=findViewById(R.id.monthChckBx1);
        monthChckBx1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checked1 = ((CheckBox) v).isChecked();
                int monthSpinnerI1 = Resources.getSystem().getIdentifier("month", "id", "android");
                View monthSpinnerV1 = birthDatePicker.findViewById(monthSpinnerI1);

                if(checked1) {
                    if (monthSpinnerI1 != 0) {
                        if (monthSpinnerV1 != null) {
                            monthSpinnerV1.setVisibility(View.GONE);
                        }
                    }
                }
                else
                {
                    if (monthSpinnerI1 != 0) {
                        if (monthSpinnerV1 != null) {
                            monthSpinnerV1.setVisibility(View.VISIBLE);
                        }
                    }
                }
            }
        });
        monthChckBx2=findViewById(R.id.monthChckBx2);
        monthChckBx2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checked3 = ((CheckBox) v).isChecked();
                int monthSpinnerI2 = Resources.getSystem().getIdentifier("month", "id", "android");
                View monthSpinnerV2 = deathDatePicker.findViewById(monthSpinnerI2);

                if(checked3) {
                    if (monthSpinnerI2 != 0) {
                        if (monthSpinnerV2 != null) {
                            monthSpinnerV2.setVisibility(View.GONE);
                        }
                    }
                }
                else
                {
                    if (monthSpinnerI2 != 0) {
                        if (monthSpinnerV2 != null) {
                            monthSpinnerV2.setVisibility(View.VISIBLE);
                        }
                    }
                }
            }
        });
        dayChckBx1=findViewById(R.id.dayChckBx1);
        dayChckBx1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checked2 = ((CheckBox) v).isChecked();
                int daySpinnerI1 = Resources.getSystem().getIdentifier("day", "id", "android");
                View daySpinnerV1 = birthDatePicker.findViewById(daySpinnerI1);

                if(checked2) {
                    if (daySpinnerI1 != 0) {
                        if (daySpinnerV1 != null) {
                            daySpinnerV1.setVisibility(View.GONE);
                        }
                    }
                }
                else
                {
                    if (daySpinnerI1 != 0) {
                        if (daySpinnerV1 != null) {
                            daySpinnerV1.setVisibility(View.VISIBLE);
                        }
                    }
                }
            }
        });
        dayChckBx2=findViewById(R.id.dayChckBx2);
        dayChckBx2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checked4 = ((CheckBox) v).isChecked();
                int daySpinnerI2 = Resources.getSystem().getIdentifier("day", "id", "android");
                View daySpinnerV2 = deathDatePicker.findViewById(daySpinnerI2);

                if(checked4) {
                    if (daySpinnerI2 != 0) {
                        if (daySpinnerV2 != null) {
                            daySpinnerV2.setVisibility(View.GONE);
                        }
                    }
                }
                else
                {
                    if (daySpinnerI2 != 0) {
                        if (daySpinnerV2 != null) {
                            daySpinnerV2.setVisibility(View.VISIBLE);
                        }
                    }
                }
            }
        });



        showBirthDate=findViewById(R.id.showBirthDate);
        Calendar birthCal = Calendar.getInstance();
        birthCal.setTimeZone(TimeZone.getTimeZone("Asia/Jerusalem"));
        year1 = birthCal.get(Calendar.YEAR);
        month1 = birthCal.get(Calendar.MONTH);
        day1 = birthCal.get(Calendar.DAY_OF_MONTH);

        birthDatePicker = findViewById(R.id.birthDatePicker);
        birthDatePicker.init(year1 - 50, month1, day1 , new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker birthDatePicker, int year, int month, int day) {

                CreateStory.this.year1=year;
                CreateStory.this.month1=month;
                CreateStory.this.day1=day;

                Birth();

            }
        });


        showDeathDate=findViewById(R.id.showDeathDate);
        Calendar deathCal = Calendar.getInstance();
        deathCal.setTimeZone(TimeZone.getTimeZone("Asia/Jerusalem"));
        year2 = deathCal.get(Calendar.YEAR);
        month2 = deathCal.get(Calendar.MONTH);
        day2 = deathCal.get(Calendar.DAY_OF_MONTH);

        deathDatePicker = findViewById(R.id.deathDatePicker);
        deathDatePicker.init(year2, month2, day2, new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker deathDatePicker, int year, int month, int day) {

                CreateStory.this.year2=year;
                CreateStory.this.month2=month;
                CreateStory.this.day2=day;

                Death();

            }
        });



        Intent intent = getIntent();
        if (intent.getStringExtra("tybe").equals("visitor")){
//            AlertDialog alertDialog = new AlertDialog.Builder(getApplicationContext()).create();
//            alertDialog.setTitle("log in isnt detected") ;
//            alertDialog.setMessage("you need to log in before creating a new story ");
//            alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "SIGN IN",
//                    new DialogInterface.OnClickListener() {
//                        public void onClick(DialogInterface dialog, int which) {
//                            Intent movetocreateaccount = new Intent(CreateStory.this, LogIn.class);
//                            startActivity(movetocreateaccount);
//                        }
//                    });
//            alertDialog.show();
            Toast.makeText(this, "you need to log in before creating an new Story ", Toast.LENGTH_SHORT).show();
            Intent movetocreateaccount = new Intent(CreateStory.this, LogIn.class);
            startActivity(movetocreateaccount);
        }




    }


    private void Birth() {

        StringBuffer strBuffer = new StringBuffer();
        strBuffer.append("Birth Date: ");
        if(!checked1 && !checked2){
            // three fields are shown
            strBuffer.append(this.year1);
            strBuffer.append("/");
            strBuffer.append(this.month1+1);
            strBuffer.append("/");
            strBuffer.append(this.day1);
            error3.setVisibility(View.GONE);
        }else if(!checked1 && checked2){
            day1=0;
            // two fields are shown
            strBuffer.append(this.year1);
            strBuffer.append("/");
            strBuffer.append(this.month1+1);
            error3.setVisibility(View.GONE);
        }else if(checked1 && !checked2){
            error3.setText("You must enter month of birth");
            error3.setVisibility(View.VISIBLE);
            f3=false;
        }else{
            month1=0; day1=0;
            // 1 field is shown
            strBuffer.append(this.year1);
            error3.setVisibility(View.GONE);
        }
        f3=true;
        showBirthDate.setText(strBuffer.toString());
        showBirthDate.setTextColor(getResources().getColor(R.color.colorGrayHint));
        showBirthDate.setGravity(Gravity.CENTER);
        showBirthDate.setTextSize(getResources().getDimension(R.dimen.textSize));
    }
    private void Death(){
        StringBuffer strBuffer = new StringBuffer();

        strBuffer.append("Death Date: ");
        if(!checked3 && !checked4){
            // three fields are shown
            strBuffer.append(this.year2);
            strBuffer.append("/");
            strBuffer.append(this.month2+1);
            strBuffer.append("/");
            strBuffer.append(this.day2);
            error4.setVisibility(View.GONE);
        }else if(!checked3 && checked4){
            day2=0;
            // two fields are shown
            strBuffer.append(this.year2);
            strBuffer.append("/");
            strBuffer.append(this.month2+1);
            error4.setVisibility(View.GONE);
        }else if(checked3 && !checked4){
            error4.setText("You must enter month of death");
            error4.setVisibility(View.VISIBLE);
            f4=false;
        }else{
            month2=0; day2=0;
            // 1 field is shown
            strBuffer.append(this.year2);
            error4.setVisibility(View.GONE);
        }
        f4=true;
        showDeathDate.setText(strBuffer.toString());
        showDeathDate.setTextColor(getResources().getColor(R.color.colorGrayHint));
        showDeathDate.setGravity(Gravity.CENTER);
        showDeathDate.setTextSize(getResources().getDimension(R.dimen.textSize));
    }



    private boolean validateName(EditText edtTxt, String str, int i) {

        String errorText="";
        int flag=1;

        str = str.trim();
        if (str.length()==0)
        {
            errorText="Field cannot be empty!";
            flag=0;
        } else if (!str.matches("[a-zA-Z]+")) {
            errorText="Only Alphabetical characters allowed!";
            flag=0;
        }

        if(flag==0)
        {
            if(i==1){
                error1.setText(errorText);
                error1.setVisibility(View.VISIBLE);
                return false;
            }else if(i==2){
                error2.setText(errorText);
                error2.setVisibility(View.VISIBLE);
                return false;
            }
        }else{
            if(i==1){
                error1.setVisibility(View.GONE);
                return true;
            }else if(i==2){
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
                        Toast.makeText(CreateStory.this, "upload image has been completed", Toast.LENGTH_SHORT).show();
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
        f1 = validateName(firstName, fns,1);
        String lns = lastName.getText().toString();
        f2 = validateName(lastName, lns,2);


        // dates validation

        if(day1==0) d1s="";
        if(day2==0) d2s="";
        if(month1==0) m1s="";
        if(month2==0) m2s="";
        if(year1>year2){
            error4.setText("Set valid dates!");
            error3.setVisibility(View.VISIBLE);
            error3.setText("Set valid dates!");
            error4.setVisibility(View.VISIBLE);
        }else{ // all good
            error3.setVisibility(View.GONE);
            error4.setVisibility(View.GONE);
        }
        y1s=String.valueOf(year1);
        y2s=String.valueOf(year2);


        int tag1 = R.drawable.family_vs, tag2 = R.drawable.sports_vs, tag3 = R.drawable.vacation_vs;
        i.putExtra("tag1", tag1);
        i.putExtra("tag2", tag2);
        i.putExtra("tag3", tag3);
        String ttag1 = "Sports", ttag2 = "family", ttag3 = "Vacations";
        i.putExtra("ttag3", ttag3);
        i.putExtra("ttag1", ttag1);
        i.putExtra("ttag2", ttag2);



        ImageView iv = findViewById(R.id.profilePic); //pass the profile image

        Date dsds = new Date();
        Toast.makeText(this,dsds.toString(),Toast.LENGTH_LONG).show();
        Date d1 = new Date(year1-1900,month1,day1);
        Date d2 = new Date(year2-1900,month2,day2);
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
        DateFormat dfForIntent = new SimpleDateFormat("yyyy/MM/dd");
        BirthDate  = df.format(d1);
        DeathDate =  df.format(d2);
        if(d2.after(dsds)){
            error4.setText("Please enter a valid date");
            error4.setVisibility(View.VISIBLE);
            f4=false;
        }

        if (f1 && f2 && f3 && f4) {
            // Send data to next activity / creating local Story object and building a custom made dates
            String nameofperson = fns + " " + lns ; // name is done

            //adapting months and days
//            if(m1s!="" && Integer.valueOf(m1s)<10){m1s="0"+m1s;}
//            if(m2s!="" && Integer.valueOf(m2s)<10){m2s="0"+m2s;}
//            if(d1s!="" && Integer.valueOf(d1s)<10){d1s="0"+d1s;}
//            if(d2s!="" && Integer.valueOf(d2s)<10){d2s="0"+d2s;}
//
//            if(!m1s.equals("") && !m2s.equals("") && !d1s.equals("") && !d2s.equals("")){
//                // three fields in date
//                BirthDate = y1s + "/" + m1s + "/" + d1s + "T14:17:53.763+0000" ;
//                DeathDate = y2s + "/" + m2s + "/" + d2s + "T14:17:53.763+0000" ; //dates has been updated successfully
//            }else if(!m1s.equals("") && !m2s.equals("") && d1s.equals("") && d2s.equals("")){
//                // two fields in date
//                BirthDate = y1s + "/" + m1s + "T14:17:53.763+0000" ;
//                DeathDate = y2s + "/" + m2s + "T14:17:53.763+0000" ;
//            }else if(m1s.equals("") && m2s.equals("") && d1s.equals("") && d2s.equals("")){
//                // one field in date
//                BirthDate = y1s + "T14:17:53.763+0000" ;
//                DeathDate = y2s + "T14:17:53.763+0000" ;
//            }


            OurStoryService Wepengine = WebFactory.getService();
            Story story = new Story(owner, nameofperson, BirthDate, DeathDate, null);
            Wepengine.CreateStory(story).enqueue(new Callback<Story>() {
                                                     @Override
                                                     public void onResponse(Call<Story> call, Response<Story> response) {
                                                         result = response.body();
                                                         if (result != null) {
                                                             Toast.makeText(CreateStory.this, "the story "+ result.getNameOfPerson() +" was created succefully", Toast.LENGTH_SHORT).show();
                                                             i.putExtra("date1", dfForIntent.format(d1)); // pass it as a Date
                                                             i.putExtra("date2", dfForIntent.format(d2));
                                                             i.putExtra("name", nameofperson);
                                                             if(view.getId()==R.id.create){
                                                                 i.putExtra("Button","just_create");
                                                             }else{
                                                                 i.putExtra("Button","createandadd");
                                                                 i.putExtra("id", result);
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
                                                 }
            );
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
        }

    }

    public void closeActivity(View view) {
        finish();
    }


}