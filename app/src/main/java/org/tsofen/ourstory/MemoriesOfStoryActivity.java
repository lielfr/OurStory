package org.tsofen.ourstory;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.text.InputType;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ShareCompat;
import androidx.core.content.res.ResourcesCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.tsofen.ourstory.StoryTeam.SearchStory;
import org.tsofen.ourstory.StoryTeam.ViewStory;
import org.tsofen.ourstory.model.Memory;
import org.tsofen.ourstory.model.api.MemoryA;
import org.tsofen.ourstory.model.api.User;
import org.tsofen.ourstory.web.OurStoryService;
import org.tsofen.ourstory.web.WebFactory;

import java.util.ArrayList;
import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MemoriesOfStoryActivity extends AppCompatActivity {

    OurStoryService MemoryAService;
    RecyclerView rv;
    ArrayList<Memory> data;
    MemoryAdapter adapter;
    TextView story_name;
    long storyId, memoryId;
    Memory memory;
    int year, flag;
    String storyName, tag;
    private ArrayList<Memory> memories;
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memories);
        Intent intent = getIntent();
        user = (User) intent.getSerializableExtra("user");
        tag = intent.getStringExtra("tag");
        storyId = intent.getLongExtra("storyId", storyId);
        // memoryId = intent.getLongExtra("memoryId", memoryId);
        storyName = intent.getStringExtra("storyName");
        year = intent.getIntExtra("year", year);
        flag = intent.getIntExtra("flag", flag);
        rv = findViewById(R.id.recycler_mem);
        story_name = findViewById(R.id.memoriestxt);
        int story_name_color = ResourcesCompat.getColor(getResources(), R.color.colorLogin, getTheme());
        Spannable spannable = new SpannableString(story_name.getText() + " " + storyName);
        spannable.setSpan(new ForegroundColorSpan(story_name_color),
                story_name.getText().length(),
                story_name.getText().length() + 1 + storyName.length(),
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        story_name.setText(spannable, TextView.BufferType.SPANNABLE);
        MemoryAService = WebFactory.getService();
        if (flag == 0) {
            MemoryAService.GetMemoriesByYear(storyId, year).enqueue(new Callback<ArrayList<Memory>>() {
                @Override
                public void onResponse(Call<ArrayList<Memory>> call, Response<ArrayList<Memory>> response) {
                    memories = response.body();
                    adapter = new MemoryAdapter(MemoriesOfStoryActivity.this, memories, MemoriesOfStoryActivity.this);
                    rv.setAdapter(adapter);
                    rv.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                    adapter.notifyDataSetChanged();
                }

                @Override
                public void onFailure(Call<ArrayList<Memory>> call, Throwable t) {

                }
            });
        } else if (flag == 1) {
            MemoryAService.GetMemoriesByTag(storyId, tag).enqueue(new Callback<ArrayList<Memory>>() {
                @Override
                public void onResponse(Call<ArrayList<Memory>> call, Response<ArrayList<Memory>> response) {
                    memories = response.body();
                    adapter = new MemoryAdapter(getApplicationContext(), memories, MemoriesOfStoryActivity.this);
                    rv.setAdapter(adapter);
                    rv.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                    adapter.notifyDataSetChanged();
                }

                @Override
                public void onFailure(Call<ArrayList<Memory>> call, Throwable t) {

                }
            });
        }

        // search button
        ImageButton btn = findViewById(R.id.searchview);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(MemoriesOfStoryActivity.this, SearchStory.class);
                MemoriesOfStoryActivity.this.startActivity(myIntent);
            }
        });

    }

    public void closeActivity(View view) {
        finish();
    }
}
