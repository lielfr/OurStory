package org.tsofen.ourstory;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ourstory.R;

import org.tsofen.ourstory.model.Memory;

import java.util.ArrayList;
import java.util.Calendar;

public class MemoriesOfStoryActivity extends AppCompatActivity {

    RecyclerView rv;
    ArrayList<Memory> data;
    MemoryAdapter adapter;
    TextView storyName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memories);
        Intent intent = getIntent();
        String message =intent.getStringExtra(YearActivity.EXTRA_MESSAGE);
        String[] m = message.split(" ");
        String name = m[1]+" " + m[2];
        int year = Integer.parseInt(m[0]);
        rv = findViewById(R.id.recycler);
        storyName = findViewById(R.id.storyname);
        data = Memory.createContactsList();
        adapter = new MemoryAdapter(data);
        rv.setAdapter(adapter);
        rv.setLayoutManager(new LinearLayoutManager(this));
        filter(year);
        storyName.setText(name);

    }
    private void filter(int text)
    {
        ArrayList<Memory> filteredList = new ArrayList<>();
        for (Memory memory: data){

            int year2 = memory.getMemoryDate().get(Calendar.YEAR);
            Log.d("tagrrr", "m d " + year2);
            if(year2 == text) {
                Log.d("tag", "added to filter " + year2);
                filteredList.add(memory);
            }
        }

        adapter.filterList(filteredList);
    }
}
