

package org.tsofen.ourstory.StoryTeam;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import org.tsofen.ourstory.EditCreateMemory.CreateEditMemoryActivity;
import org.tsofen.ourstory.MemoriesOfStoryActivity;
import org.tsofen.ourstory.R;
import org.tsofen.ourstory.model.api.FullViewStory;
import org.tsofen.ourstory.model.api.Story;
import org.tsofen.ourstory.model.api.Tags;
import org.tsofen.ourstory.model.api.User;
import org.tsofen.ourstory.web.OurStoryService;
import org.tsofen.ourstory.web.WebFactory;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ViewStory extends AppCompatActivity implements Serializable {

    ImageButton ib;
    ImageButton share;
    Story target_story;
    Object imageView_profile;
    Tags tag;
    String date;
    private final LinkedList<Story> mStoryList = new LinkedList<>();

    private RecyclerView mRecyclerView;
    private ViewStoryAdapter mAdapter;

    private OurStoryService story_api;
    private OurStoryService story_api2;
    //Intent intent = getIntent() ;
    long id;
    FullViewStory story_full;
    Date dob, dod;
    Intent intent;
    User user;

    @Override
    public void onRestart() {
        super.onRestart();
        finish();
        startActivity(getIntent());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_story);

    }

    @Override
    protected void onResume() {
        super.onResume();
        Boolean flag = Boolean.FALSE;

        Activity aa = this;
        if (intent == null)
            intent = getIntent();
        if (intent.getStringExtra("id") != null && id == 0)
            id = Long.parseLong(intent.getStringExtra("id"));

        if (intent.getStringExtra("Button") != null && intent.getStringExtra("Button").equals("createandadd")) {
            intent.removeExtra("Button"); // Make sure we don't go back
            AddMemoryLive((Story) intent.getSerializableExtra("result"));
            Toast.makeText(aa, "backfromadd memory", Toast.LENGTH_SHORT).show();
        }

        user = (User) intent.getSerializableExtra("user");

        story_api = WebFactory.getService();
        story_api.GetFullViewStoryById(id)
                .enqueue(new Callback<FullViewStory>() {
            @Override
            public void onResponse(Call<FullViewStory> call, Response<FullViewStory> response) {
                story_full = response.body();
                if (story_full == null) {
                    Toast.makeText(getApplicationContext(), "there is no story like this null ", Toast.LENGTH_LONG).show();
                } else {

                    String personName = story_full.getStory().getNameOfPerson();

                    TextView textView1 = (findViewById(R.id.textView));
                    textView1.setText(personName);
                    String date1 = story_full.getStory().getDateOfBirth();
                    String date2 = story_full.getStory().getDateOfDeath();
                    String[] parts = date1.split("-");
                    String year = parts[0];
                    String month = parts[1];
                    String day = parts[2];
                    day = day.substring(0, 2);

                    date1 = day + "/" + month + "/" + year;
                    DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
                    DateFormat df1 = new SimpleDateFormat("yyyy/MM/dd");

                    try {
                        if (story_full.getStory().getDateOfBirth() != null && story_full.getStory().getDateOfDeath() != null) {
                            dob = df.parse(story_full.getStory().getDateOfBirth());

                            dod = df.parse(story_full.getStory().getDateOfDeath());
                        }
                        date = df1.format(dob) + "-" + df1.format(dod);

                    } catch (ParseException e) {
                        e.printStackTrace();
                    }

                    parts = date2.split("-");
                    year = parts[0];
                    month = parts[1];
                    day = parts[2];
                    day = day.substring(0, 2);

                    date2 = day + "/" + month + "/" + year;

                    TextView textView2 = (findViewById(R.id.textView2));
                    textView2.setText(date);

                    ImageView pic = findViewById(R.id.imageView3);

                    imageView_profile = story_full.getStory().getPicture();//TODO check if there is no profile pic MARYAM
                    if (imageView_profile != null) {
                        String st = imageView_profile.toString();
                        Uri uri = Uri.parse(st);
                        RequestOptions options = new RequestOptions()
                                .override(375, 192)
                                .centerCrop()
                                .placeholder(R.drawable.nopicyet)
                                .error(R.drawable.nopicyet);

                        Glide.with(getApplicationContext()).load(uri).apply(options).into(pic);
                    }


                    if (story_full != null && story_full.getMemories().size() == 0) {
                        LinearLayout linearLayout = findViewById(R.id.linearLayout3);
                        linearLayout.setVisibility(View.INVISIBLE);

                        ConstraintLayout constraintLayout = findViewById(R.id.constrainlayout2);
                        constraintLayout.setVisibility(View.VISIBLE);
                        Toast.makeText(aa, "dsflkhjvfkd", Toast.LENGTH_SHORT).show();

                    } else {
                        LinearLayout linearLayout = findViewById(R.id.linearLayout3);
                        linearLayout.setVisibility(View.VISIBLE);

                        ConstraintLayout constraintLayout = findViewById(R.id.constrainlayout2);
                        constraintLayout.setVisibility(View.INVISIBLE);


                        TextView textView = findViewById(R.id.textView4);
                        if (story_full != null && story_full.getTop3tags().size() > 0) {
                            textView.setText(story_full.getTop3tags().get(0));
                            Log.d("sss", story_full.getTop3tags().get(0));
                            if (story_full.getTop3tags().size() > 1) {
                                textView = findViewById(R.id.textView5);
                                textView.setText(story_full.getTop3tags().get(1));
                            }
                            if (story_full.getTop3tags().size() > 2) {
                                textView = findViewById(R.id.textView6);
                                textView.setText(story_full.getTop3tags().get(2));
                            }

                        }


                        // Get a handle to the RecyclerView.
                        mRecyclerView = findViewById(R.id.recyclerview);
                        // Create an adapter and supply the data to be displayed.
                        mAdapter = new ViewStoryAdapter(ViewStory.this, story_full.getMemories(), story_full.getStory().getNameOfPerson(), story_full.getStory().getStoryId(), user);
                        //     Toast.makeText(aa, mAdapter.mStoryList.get(7).(), Toast.LENGTH_SHORT).show();
                        // Get a handle to the RecyclerView.
                        mRecyclerView = findViewById(R.id.recyclerview);
                        // Create an adapter and supply the data to be displayed.
                        mAdapter = new ViewStoryAdapter(ViewStory.this, story_full.getMemories(), story_full.getStory().getNameOfPerson(), story_full.getStory().getStoryId(), user);
                        //     Toast.makeText(aa, mAdapter.mStoryList.get(7).(), Toast.LENGTH_SHORT).show();


                        // Connect the adapter with the RecyclerView.
                        if (mRecyclerView != null) {
                            mRecyclerView.setAdapter(mAdapter);
                            // Give the RecyclerView a default layout manager.
                            mRecyclerView.setLayoutManager(new LinearLayoutManager(aa));
                        } else {
                            Toast.makeText(getApplicationContext(), " the recycle View is null ", Toast.LENGTH_LONG).show();
                        }


                    }
                }
            }

            @Override
            public void onFailure(Call<FullViewStory> call, Throwable t) {
                Log.d("Failure", t.toString());

            }

        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        intent = data;
    }

    public void launchSearchActivity(View view) {
        Intent i = new Intent(ViewStory.this, SearchStory.class);
        startActivity(i);
    }

    public void launchShare(View view) {
//        share = findViewById(R.id.sharebtn);

//        Intent myIntent = new Intent(Intent.ACTION_SEND);
//        myIntent.setType("text/plain");
//        String shareBody = "The Story of Pini Cohen";
//        String sharesub = "Your subject here";
//        myIntent.putExtra(Intent.EXTRA_SUBJECT, sharesub);
//        myIntent.putExtra(Intent.EXTRA_TEXT, shareBody);
//        startActivity(Intent.createChooser(myIntent, "Share using: "));
    }

    public void EditProfile(View view) {
//        Intent intent= new Intent(this,EditStoryProfile.class);
//        startActivity(intent);

    }

    public void back(View view) {
        finish();
    }

    public void AddMemory(View view) {
        Intent intent = new Intent(this, CreateEditMemoryActivity.class);
        intent.putExtra(CreateEditMemoryActivity.KEY_CREATE, story_full.getStory());
        intent.putExtra("user", getIntent().getSerializableExtra("user"));
        startActivityForResult(intent, 0);
    }


    public void ShowMemoryByTag1(View view) {
        Intent intent = new Intent(this, MemoriesOfStoryActivity.class);
        intent.putExtra("flag", 1);
        intent.putExtra("storyId", story_full.getStory().getStoryId());
        intent.putExtra("storyName", story_full.getStory().getNameOfPerson());
        TextView textView = findViewById(R.id.textView4);
        intent.putExtra("tag", textView.getText().toString());
        intent.putExtra("user", user);
        startActivity(intent);
    }

    public void ShowMemoryByTag2(View view) {
        Intent intent = new Intent(this, MemoriesOfStoryActivity.class);
        intent.putExtra("flag", 1);
        if (story_full.getStory() != null) {
            intent.putExtra("storyId", story_full.getStory().getStoryId());
            intent.putExtra("storyName", story_full.getStory().getNameOfPerson());
            intent.putExtra("user", user);
        } else
            Toast.makeText(getApplicationContext(), " in tag the story null ", Toast.LENGTH_LONG).show();
        TextView textView = findViewById(R.id.textView5);
        intent.putExtra("tag", textView.getText().toString());
        startActivity(intent);
    }

    public void ShowMemoryByTag3(View view) {
        Intent intent = new Intent(this, MemoriesOfStoryActivity.class);
        intent.putExtra("flag", 1);
        intent.putExtra("storyId", story_full.getStory().getStoryId());
        intent.putExtra("storyName", story_full.getStory().getNameOfPerson());
        TextView textView = findViewById(R.id.textView6);
        intent.putExtra("tag", textView.getText().toString());
        intent.putExtra("user", user);
        startActivity(intent);
    }


    public void AddMemoryLive(Story story) {
        Intent intent = new Intent(this, CreateEditMemoryActivity.class);
        intent.putExtra(CreateEditMemoryActivity.KEY_CREATE, story);
        intent.putExtra("user", getIntent().getSerializableExtra("user"));
        startActivityForResult(intent, 0);
    }

}