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
    TextView storyName;
    Long storyId;
    Memory memory;
    private ArrayList<MemoryA> memories;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memories);
        Intent intent = getIntent();
        String message = intent.getStringExtra("UserCall");
        String[] m = message.split(" ");
        int flag = Integer.parseInt(m[0]);
        storyId = Long.valueOf((m[1]));
        rv = findViewById(R.id.recycler);
        storyName = findViewById(R.id.storyname);
       /* storyName.setText(name);*/
        MemoryAService = WebFactory.getService();
        // String form [ flag, storyId, Tag/Year, story name ]
        //               flag 0 == by Year |||| flag 1 == by Tag
        if(flag==0) {

            MemoryAService.GetMemoriesByYear(storyId, Integer.parseInt(m[2])).enqueue(new Callback<ArrayList<MemoryA>>() {
                @Override
                public void onResponse(Call<ArrayList<MemoryA>> call, Response<ArrayList<MemoryA>> response) {
                    memories = response.body();
                    Toast.makeText(getApplicationContext(), memories.size() + "", Toast.LENGTH_LONG).show();
                    adapter = new MemoryAdapter(MemoriesOfStoryActivity.this,memories);
                    rv.setAdapter(adapter);
                    rv.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                    adapter.notifyDataSetChanged();
                }

                @Override
                public void onFailure(Call<ArrayList<MemoryA>> call, Throwable t) {

                }
            });
        }

        else if(flag==1){
            MemoryAService.GetMemoriesByTag(storyId, m[2]).enqueue(new Callback<ArrayList<MemoryA>>() {
                @Override
                public void onResponse(Call<ArrayList<MemoryA>> call, Response<ArrayList<MemoryA>> response) {
                    memories = response.body();
                    adapter = new MemoryAdapter(MemoriesOfStoryActivity.this,memories);
                    rv.setAdapter(adapter);
                    rv.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                    adapter.notifyDataSetChanged();
                }

                @Override
                public void onFailure(Call<ArrayList<MemoryA>> call, Throwable t) {

                }
            });
        }
        else
        {
            MemoryAService.GetMemoryById(Long.parseLong(m[2])).enqueue(new Callback<Memory>() {
                @Override
                public void onResponse(Call<Memory> call, Response<Memory> response) {
                    memory = response.body();
                    ArrayList<Memory> memoryOne;
                 /*   memoryOne.add(memory);
                    adapter = new MemoryAdapter(MemoriesOfStoryActivity.this,memoryOne );*/
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
        ImageButton btn = (ImageButton) findViewById(R.id.searchview);
        btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent myIntent = new Intent(MemoriesOfStoryActivity.this, SearchStory.class);
                MemoriesOfStoryActivity.this.startActivity(myIntent);
            }
        });

    }

    public void shareMemory(View view)
    {
        String mimeType = "text/plain"; // For the share func to know which type is the sharing
        // content so it can offer the right apps
        ShareCompat.IntentBuilder
                .from(this)
                .setType(mimeType)
                .setChooserTitle("Share this memory with: ")
                .setText("This is a filler until we can integrate a memory object")
                .startChooser();

    }
}
