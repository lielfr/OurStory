package org.tsofen.ourstory;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.tsofen.ourstory.StoryTeam.MainActivity;
import org.tsofen.ourstory.UserModel.AppHomePage;
import org.tsofen.ourstory.model.Memory;
import org.tsofen.ourstory.web.OurStoryService;
import org.tsofen.ourstory.web.WebFactory;

import java.time.Year;
import java.util.ArrayList;
import java.util.Calendar;

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
}
