package org.tsofen.ourstory.TeamsHomePage;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import org.tsofen.ourstory.EditCreateMemory.CreateEditMemoryActivity;
import org.tsofen.ourstory.R;
import org.tsofen.ourstory.StoryTeam.CreateStory;
import org.tsofen.ourstory.UserModel.AppHomePage;
import org.tsofen.ourstory.YearActivity;

public class TeamsHomePg extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teams_home_pg);
    }

    public void showUserPart(View view) {
        Intent userMainIntent = new Intent(this, AppHomePage.class);
        startActivity(userMainIntent);
    }

    public void showMem1Part(View view) {
        Intent intent = new Intent(this, CreateEditMemoryActivity.class);
        startActivity(intent);
    }

    public void showMem2Part(View view) {
        Intent intent = new Intent(this, YearActivity.class);
        startActivity(intent);
    }

    public void showStoryPart(View view) {
        Intent intent = new Intent(TeamsHomePg.this, CreateStory.class);
        startActivity(intent);
    }
}