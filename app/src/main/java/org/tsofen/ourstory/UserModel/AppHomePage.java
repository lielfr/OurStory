package org.tsofen.ourstory.UserModel;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.tsofen.ourstory.MyMemories;
import org.tsofen.ourstory.R;


public class AppHomePage extends AppCompatActivity {

    public static final String KEY_SELECTED = "OurStorySelected";
    public int selected;
    FragmentManager fragmentManager;
    Fragment currentFragment;
    BottomNavigationView nav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        Intent currIntent = getIntent();
        selected = R.id.nav_home;
        final TextView upText = findViewById(R.id.upText);
        // Fixing the icon tinting of the bottom navigation bar.

        nav = findViewById(R.id.nav_main);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            nav.setItemIconTintList(getColorStateList(R.color.navbar));
        }

        currentFragment = new HomeFragment();
        currentFragment.setArguments(getIntent().getExtras());


        fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .add(R.id.main_container2, currentFragment)
                .commit();

        nav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                Fragment targetFragment;
                switch (menuItem.getItemId()) {
                    case R.id.nav_home:
                        if(UserStatusCheck.getUserStatus()=="visitor") {
                            targetFragment = new HomeFragment();
                            upText.setText("Home");
                        }
                        else
                        {
                            targetFragment = new MyMemories();
                            upText.setText("My Memories");
                        }
                        break;
                    case R.id.nav_profile:
                       /* if(UserStatusCheck.getUserStatus()=="not a visitor")
                        {targetFragment = new UserProfile();
                        upText.setText("My Profile");
                        break;}*/

                        targetFragment = new UserProfile();
                            upText.setText("My Profile");
                            break;
                    default:
                        return true;
                }
                fragmentManager.beginTransaction()
                        .replace(R.id.main_container2, targetFragment)
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

    public void closeActivity(View view) {
    }
}
