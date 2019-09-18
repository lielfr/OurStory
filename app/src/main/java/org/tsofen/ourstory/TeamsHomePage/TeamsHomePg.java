package org.tsofen.ourstory.TeamsHomePage;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import org.tsofen.ourstory.EditCreateMemory.CreateEditMemoryActivity;
import org.tsofen.ourstory.R;
import org.tsofen.ourstory.StoryTeam.CreateStory;
import org.tsofen.ourstory.StoryTeam.ViewStory;
import org.tsofen.ourstory.UserModel.AppHomePage;
import org.tsofen.ourstory.model.Memory;
import org.tsofen.ourstory.model.api.Story;
import org.tsofen.ourstory.web.OurStoryService;
import org.tsofen.ourstory.web.WebFactory;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
        OurStoryService service = WebFactory.getService();
        service.GetMemoryById(161).enqueue(new Callback<Memory>() {
            @Override
            public void onResponse(Call<Memory> call, Response<Memory> response) {
                if (response.code() != 200) return;
                Memory mem = response.body();
                if (mem == null) return;
                intent.putExtra(CreateEditMemoryActivity.KEY_EDIT, mem);
                startActivity(intent);
            }


            @Override
            public void onFailure(Call<Memory> call, Throwable t) {

            }
        });

//        service.GetStoryById(36).enqueue(new Callback<Story>() {
//            @Override
//            public void onResponse(Call<Story> call, Response<Story> response) {
//                if (response.code() != 200) return;
//                Story story = response.body();
//                if (story == null) return;
//
//                intent.putExtra(CreateEditMemoryActivity.KEY_CREATE, story);
//                startActivity(intent);
//            }
//
//            @Override
//            public void onFailure(Call<Story> call, Throwable t) {
//
//            }
//        });
    }

/*    public void showMem2Part(View view) {
        Intent intent = new Intent(this, YearActivity.class);
        startActivity(intent);
    }*/

    public void showStoryPart(View view) {
        Intent intent = new Intent(TeamsHomePg.this, ViewStory.class);
        intent.putExtra("tybe","fvisitor");
        startActivity(intent);
    }
}