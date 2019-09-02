package org.tsofen.ourstory;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.ourstory.R;

import org.tsofen.ourstory.model.Memory;

import java.util.ArrayList;
import java.util.Calendar;

public class MyMemoriesActivity extends AppCompatActivity {

    RecyclerView rv;
    ArrayList<Memory> data;
    MyMemoriesAdapter adapter;
    TextView storyName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_memories);
        Intent intent = getIntent();
        String message =intent.getStringExtra(YearActivity.EXTRA_MESSAGE);
        String[] m = message.split(" ");
        String name = m[1]+" " + m[2];
        int year = Integer.parseInt(m[0]);
        rv = findViewById(R.id.recycler);
        storyName = findViewById(R.id.storyname);
        data = Memory.createContactsListMyMemories();
        adapter = new MyMemoriesAdapter(data);
        rv.setAdapter(adapter);
        rv.setLayoutManager(new LinearLayoutManager(this));

        storyName.setText(name);

    }

}
