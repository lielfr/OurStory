package org.tsofen.ourstory.StoryTeam;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.tsofen.ourstory.R;

public class MainActivity extends AppCompatActivity {

    public static final String KEY_SELECTED = "OurStorySelected";
    public int selected;
    FragmentManager fragmentManager;
    Fragment currentFragment;
    BottomNavigationView nav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        selected = R.id.nav_home;

        // Fixing the icon tinting of the bottom navigation bar.

        nav = findViewById(R.id.nav_main);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            nav.setItemIconTintList(getColorStateList(R.color.navbar));
        }

        currentFragment = new HomeFragment();
        currentFragment.setArguments(getIntent().getExtras());


        fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .add(R.id.main_container, currentFragment)
                .commit();

        nav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                Fragment targetFragment;
                switch (menuItem.getItemId()) {
                    case R.id.nav_home:
                        targetFragment = new HomeFragment();
                        break;
                    case R.id.nav_stories:
//                        selected = R.id.nav_home;
//                        Intent intent = new Intent(MainActivity.this, ViewStory.class);
//                        startActivity(intent);
                        return true;
                    default:
                        return true;
                }
                fragmentManager.beginTransaction()
                        .replace(R.id.main_container, targetFragment)
                        .commit();
                currentFragment = targetFragment;
                return true;
            }
        });

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(KEY_SELECTED, selected);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        selected = savedInstanceState.getInt(KEY_SELECTED, R.id.nav_home);
    }

    @Override
    protected void onResume() {
        super.onResume();
        nav.setSelectedItemId(selected);
    }

    public void launchSearchActivity(View view) {
        Intent i = new Intent(this, SearchStory.class);
        startActivity(i);
    }
}
