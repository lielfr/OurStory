package org.tsofen.ourstory;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ShareCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.tsofen.ourstory.StoryTeam.SearchStory;
import org.tsofen.ourstory.model.api.MemoryA;

import java.util.ArrayList;
import java.util.Calendar;

public class MemoriesOfStoryActivity extends AppCompatActivity {
    Context ctx;
    RecyclerView rv;
    ArrayList<MemoryA> data;
    MemoryAdapter adapter;
    TextView storyName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memories);
        Intent intent = getIntent();
        String message = intent.getStringExtra(YearActivity.EXTRA_MESSAGE);
        String[] m = message.split(" ");
        String name = m[1] + " " + m[2];
        int year = Integer.parseInt(m[0]);
        rv = findViewById(R.id.recycler);
        storyName = findViewById(R.id.storyname);
       // data = MemoryA.createContactsList();
        adapter = new MemoryAdapter(data);
        rv.setAdapter(adapter);
        rv.setLayoutManager(new LinearLayoutManager(this));
        filter(year);
        storyName.setText(name);


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

    private void filter(int text) {
        ArrayList<MemoryA> filteredList = new ArrayList<>();
        for (MemoryA memory : data) {

            int year2 = memory.getMemoryDate().get(Calendar.YEAR);
            if (year2 == text)
                filteredList.add(memory);
        }

        adapter.filterList(filteredList);
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
