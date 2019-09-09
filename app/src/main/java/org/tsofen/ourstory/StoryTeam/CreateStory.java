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
import org.tsofen.ourstory.model.api.Owner;
import org.tsofen.ourstory.model.api.Story;
import org.tsofen.ourstory.web.OurStoryService;
import org.tsofen.ourstory.web.WebFactory;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import java.util.ArrayList;
import androidx.annotation.IntegerRes;
import android.content.*;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import androidx.core.content.ContextCompat;
import com.google.gson.annotations.JsonAdapter;
import org.json.JSONObject;
import org.tsofen.ourstory.model.api.ListOfStory;
import org.tsofen.ourstory.model.api.Search;

public class CreateStory extends AppCompatActivity implements Serializable {

    int flag = 1;

    Bitmap bitmap;
    String filepathS = null;
    Uri filePath;

    Owner owner ;
    Story result;

    ImageView image;
    EditText firstName, lastName;
    TextView error1, error2, error3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_createstory);

        firstName = findViewById(R.id.firstNameEditText);
        lastName = findViewById(R.id.lastNameEditText);

        error1=findViewById(R.id.error1);
        error2=findViewById(R.id.error2);
        error3=findViewById(R.id.error3);

    }




    private boolean validateName(EditText edtTxt, String str, int i) {
        ///////////////////////////// User Team /////////////////////////////////////
        // code by Ether
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
                error1.setVisibility(View.INVISIBLE);
                return true;
            }else if(i==2){
                error2.setVisibility(View.INVISIBLE);
                return true;
            }
        }
        return false;
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


        boolean f1 = false, f2 = false, f3=false;
        Date date1D, date2D, todayD;

        Intent i = new Intent(this, ViewStory.class);

        // Names Validation
        String fns = firstName.getText().toString();
        f1 = validateName(firstName, fns,1);
        String lns = lastName.getText().toString();
        f2 = validateName(lastName, lns,2);

        // Dates Validation
        EditText d1 = findViewById(R.id.day_1);
        EditText d2 = findViewById(R.id.day_2);
        String d1s = d1.getText().toString();              //check that out
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

            if(d1s.length()==0 || d2s.length()==0 || m1s.length()==0 || m2s.length()==0
            || y1s.length()==0 || y2s.length()==0){
                error3.setText("Dates cannot be empty!");
                error3.setVisibility(View.VISIBLE);
                f3 = false;
            } else if (date1D.after(date2D)) {
                error3.setText("Invalid dates!");
                error3.setVisibility(View.VISIBLE);
                f3 = false;
            } else if (todayD.before(date1D)) {
                error3.setText("Invalid birth date!");
                error3.setVisibility(View.VISIBLE);
                f3 = false;
            }else if (todayD.before(date2D)) {
                error3.setText("Invalid death date!");
                error3.setVisibility(View.VISIBLE);
                f3 = false;
            } else { // all good
                error3.setText("");
                error3.setVisibility(View.INVISIBLE);
                f3 = true;
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
        if (f1 && f2 && f3) {
            // Send data to next activity / creating local Story object and building a custom made dates
            String nameofperson = fns + lns ; // name is done
            //adapting months and days
            if(Integer.valueOf(m1s)<10){m1s="0"+m1s;}
            if(Integer.valueOf(m2s)<10){m2s="0"+m2s;}
            if(Integer.valueOf(d1s)<10){d1s="0"+d1s;}
            if(Integer.valueOf(d2s)<10){d2s="0"+d2s;}
            String BirthDate = y1s+"-"+ m1s + "-"+d1s+"T14:17:53.763+0000" ;
            String DeathDate = y2s+"-"+ m2s + "-"+d2s+"T14:17:53.763+0000" ; //dates has been updated succefuly

            OurStoryService Wepengine = WebFactory.getService();
            Story story = new Story(123, owner, nameofperson, BirthDate, DeathDate, null);

            Wepengine.CreateStory(story).enqueue(new Callback<Story>() {
                @Override
                public void onResponse(Call<Story> call, Response<Story> response) {
                    result = response.body();
                    if (result != null) {
                        Toast.makeText(CreateStory.this, "the story "+ result.getNameOfPerson() +" was created succefully", Toast.LENGTH_SHORT).show();
                        i.putExtra("date1", date1);
                        i.putExtra("date2", date2);
                        i.putExtra("name", nameofperson);
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