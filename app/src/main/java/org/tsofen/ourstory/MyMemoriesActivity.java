package org.tsofen.ourstory;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyMemoriesActivity extends AppCompatActivity {

    Memory mem;
    RecyclerView rv;
    ArrayList<MemoryA> memories;
    OurStoryService MemoryAService;
    MyMemoriesAdapter adapter;
    TextView storyName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_memories);
        /*Intent intent = getIntent();*/
       //TODO int user_id = intent.getStringExtra(AppHomePage.EXTRA_MESSAGE);
        rv = findViewById(R.id.recycler);
        MemoryAService = WebFactory.getService();
        MemoryAService.GetMemoriesByUser(12).enqueue(new Callback<ArrayList<MemoryA>>() {
            @Override
            public void onResponse(Call<ArrayList<MemoryA>> call, Response<ArrayList<MemoryA>> response) {
                memories = response.body();
                Toast.makeText(getApplicationContext(), memories.size() + "", Toast.LENGTH_LONG).show();
                adapter = new MyMemoriesAdapter(MyMemoriesActivity.this, memories);

                rv.setAdapter(adapter);
                rv.setLayoutManager(new LinearLayoutManager(MyMemoriesActivity.this));
            }

            @Override
            public void onFailure(Call<ArrayList<MemoryA>> call, Throwable t) {
                Log.d("Error", t.toString());
            }
        });

        ImageButton btn = (ImageButton) findViewById(R.id.searchview);
        btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent myIntent = new Intent(MyMemoriesActivity.this, SearchStory.class);
                MyMemoriesActivity.this.startActivity(myIntent);
            }
        });



    }

    public void shareMyMemories(View view)
    {
        String mimeType = "text/plain"; // For the share func to know which type is the sharing
        // content so it can offer the right apps
        ShareCompat.IntentBuilder
                .from(this)
                .setType(mimeType)
                .setChooserTitle("Share this MemoryA with: ")
                .setText("This is a filler until we can integrate a MemoryA object")
                .startChooser();

    }


    public void editMemory(View view){
        Intent i = new Intent();
        i.putExtra("CEMemoryEdit", mem);

    }
}
