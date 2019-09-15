package org.tsofen.ourstory;

import android.content.Context;
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
    private ArrayList<MemoryA> memories;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memories);
       /* Intent intent = getIntent();
        String message = intent.getStringExtra(YearActivity.EXTRA_MESSAGE);
        String[] m = message.split(" ");
        String name = m[1] + " " + m[2];
        int year = Integer.parseInt(m[0]);*/
        rv = findViewById(R.id.recycler);
       /* storyName = findViewById(R.id.storyname);
        data = Memory.createContactsList();
        adapter = new MemoryAdapter(data);
        rv.setAdapter(adapter);
        rv.setLayoutManager(new LinearLayoutManager(this));
        filter(year);
        storyName.setText(name);*/


        MemoryAService = WebFactory.getService();

        Intent call = getIntent();
        String userCall = call.getStringExtra("UserCall");
        String[] stringExtra = userCall.split(" ");

        // String form [ flag, storyId, Tag/Year, userId ]
        //               flag 0 == by Year |||| flag 1 == by Tag
        if(stringExtra[0].equals("0")) {

            MemoryAService.GetMemoriesByYear(10, 2000).enqueue(new Callback<ArrayList<MemoryA>>() {
                @Override
                public void onResponse(Call<ArrayList<MemoryA>> call, Response<ArrayList<MemoryA>> response) {
                    memories = response.body();
                    Toast.makeText(getApplicationContext(), memories.size() + "", Toast.LENGTH_LONG).show();
                    adapter = new MemoryAdapter(MemoriesOfStoryActivity.this,memories);
                }

                @Override
                public void onFailure(Call<ArrayList<MemoryA>> call, Throwable t) {

                }
            });
        }

        else {
            MemoryAService.GetMemoriesByTag(10, "happy").enqueue(new Callback<ArrayList<MemoryA>>() {
                @Override
                public void onResponse(Call<ArrayList<MemoryA>> call, Response<ArrayList<MemoryA>> response) {
                    memories = response.body();
                    Toast.makeText(getApplicationContext(), memories.size() + "", Toast.LENGTH_LONG).show();
                    adapter = new MemoryAdapter(MemoriesOfStoryActivity.this,memories);
                }

                @Override
                public void onFailure(Call<ArrayList<MemoryA>> call, Throwable t) {

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
