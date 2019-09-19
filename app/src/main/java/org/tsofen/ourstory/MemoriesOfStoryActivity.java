package org.tsofen.ourstory;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ShareCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.tsofen.ourstory.StoryTeam.SearchStory;
import org.tsofen.ourstory.StoryTeam.ViewStory;
import org.tsofen.ourstory.model.Memory;
import org.tsofen.ourstory.model.api.MemoryA;
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
    int year,flag;
    String storyName,tag;
    private ArrayList<Memory> memories;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memories);
        Intent intent = getIntent();
        tag = intent.getStringExtra("tag");
        storyId= intent.getLongExtra("storyId",storyId);
        memoryId= intent.getLongExtra("memoryId",memoryId);
        storyName= intent.getStringExtra("storyName");
        year= intent.getIntExtra("year",year);
        flag = intent.getIntExtra("flag",flag);
        rv = findViewById(R.id.recycler_mem);
        story_name = findViewById(R.id.storyname);
        story_name.setText(storyName);
        MemoryAService = WebFactory.getService();
        if(flag==0) {
            MemoryAService.GetMemoriesByYear(storyId, year).enqueue(new Callback<ArrayList<Memory>>() {
                @Override
                public void onResponse(Call<ArrayList<Memory>> call, Response<ArrayList<Memory>> response) {
                    memories = response.body();
                    adapter = new MemoryAdapter(MemoriesOfStoryActivity.this,memories);
                    rv.setAdapter(adapter);
                    rv.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                    adapter.notifyDataSetChanged();
                }

                @Override
                public void onFailure(Call<ArrayList<Memory>> call, Throwable t) {

                }
            });
        }

        else if(flag==1){
            MemoryAService.GetMemoriesByTag(storyId, tag).enqueue(new Callback<ArrayList<Memory>>() {
                @Override
                public void onResponse(Call<ArrayList<Memory>> call, Response<ArrayList<Memory>> response) {
                    memories = response.body();
                    adapter = new MemoryAdapter(getApplicationContext(), memories);
                    rv.setAdapter(adapter);
                    rv.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                    adapter.notifyDataSetChanged();
                }

                @Override
                public void onFailure(Call<ArrayList<Memory>> call, Throwable t) {

                }
            });
        }
        else
        {
            MemoryAService.GetMemoryById(memoryId).enqueue(new Callback<Memory>() {
                @Override
                public void onResponse(Call<Memory> call, Response<Memory> response) {
                    memory = response.body();
                    ArrayList<Memory> memoryOne = new ArrayList<>();
                    memoryOne.add(memory);
                    adapter = new MemoryAdapter(MemoriesOfStoryActivity.this,memoryOne );
                    rv.setAdapter(adapter);
                    rv.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                    adapter.notifyDataSetChanged();
                }

                @Override
                public void onFailure(Call<Memory> call, Throwable t) {

                }
            });

        }

        // search button
        ImageButton btn = findViewById(R.id.searchview);
        btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent myIntent = new Intent(MemoriesOfStoryActivity.this, SearchStory.class);
                MemoriesOfStoryActivity.this.startActivity(myIntent);
            }
        });

    }
}
