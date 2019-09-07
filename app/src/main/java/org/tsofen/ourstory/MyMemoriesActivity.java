package org.tsofen.ourstory;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ShareCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.tsofen.ourstory.model.Memory;
import org.tsofen.ourstory.web.OurStoryService;
import org.tsofen.ourstory.web.WebFactory;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyMemoriesActivity extends AppCompatActivity {

    RecyclerView rv;
    ArrayList<Memory> memories;
    OurStoryService memoryService;
    MyMemoriesAdapter adapter;
    TextView storyName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_memories);
        Intent intent = getIntent();
       //TODO int user_id = intent.getStringExtra(AppHomePage.EXTRA_MESSAGE);
        rv = findViewById(R.id.recycler);
        memoryService = WebFactory.getService();
        memoryService.GetMemoriesByUser(3).enqueue(new Callback<ArrayList<Memory>>() {
            @Override
            public void onResponse(Call<ArrayList<Memory>> call, Response<ArrayList<Memory>> response) {
                memories = response.body();
                adapter = new MyMemoriesAdapter(memories);
                rv.setAdapter(adapter);
                rv.setLayoutManager(new LinearLayoutManager(MyMemoriesActivity.this));
            }

            @Override
            public void onFailure(Call<ArrayList<Memory>> call, Throwable t) {
                Log.d("Error", t.toString());
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
                .setChooserTitle("Share this memory with: ")
                .setText("This is a filler until we can integrate a memory object")
                .startChooser();

    }
}
