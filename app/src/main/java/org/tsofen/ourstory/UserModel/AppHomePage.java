package org.tsofen.ourstory.UserModel;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.PopupMenu;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.tsofen.ourstory.MyMemories;
import org.tsofen.ourstory.R;
import org.tsofen.ourstory.TeamsHomePage.TeamsHomePg;


public class AppHomePage extends AppCompatActivity {

    public static final String KEY_SELECTED = "OurStorySelected";
    public static final String USER = "user";
    public int selected;
    FragmentManager fragmentManager;
    Fragment currentFragment;
    BottomNavigationView nav;
    SharedPreferences sh;
    public static String user1;
    public static String user2=null;
    SharedPreferences.Editor prefsEditor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        Intent currIntent = getIntent();
        selected = R.id.nav_home;
        final TextView upText = findViewById(R.id.upText);
        // Fixing the icon tinting of the bottom navigation bar.

        sh = getSharedPreferences(getString(R.string.shared_pref_key), MODE_PRIVATE);
        prefsEditor = sh.edit();
        user1=sh.getString(AppHomePage.USER,"");
        user2=currIntent.getStringExtra("myUserJson");

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

//                        if (user1==""&&user2==null) {
//                            Log.d("users",user1+"!"+user2);
//                            targetFragment = new HomeFragment();
//                            upText.setText("Home");
//                        }
                        if (user1!=""||user2!=null) {
                            Log.d("users",user1+"?"+user2);
                            targetFragment = new MyMemories();
                            upText.setText("My Memories");
                        }
                        else
                        {
                            Log.d("users",user1+"@"+user2);
                            targetFragment = new HomeFragment();
                            upText.setText("Home");
                        }
                        break;
                    case R.id.nav_profile:{
                        if (user1==""&&user2==null) {
                            Intent login = new Intent(getApplicationContext(), LogIn.class);
                            startActivity(login);
                            return true;
                        } else {
                            targetFragment = new UserProfile();
                            upText.setText("My Profile");
                            break;
                        }}
                    case R.id.nav_more:
                    {
//                        if (user1!=""||user2!=null) {
                            PopupMenu popup = new PopupMenu(AppHomePage.this, findViewById(R.id.nav_more));
                            MenuInflater inflater = popup.getMenuInflater();
                            popup.setOnMenuItemClickListener(AppHomePage.this::onMenuItemClick);
                            inflater.inflate(R.menu.more_menu, popup.getMenu());
                            popup.show();
                        if (user1 == "" && user2 == null) {
                            popup.getMenu().findItem(R.id.logout).setEnabled(false);
                        }
//                        }

                    }
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

    public void movetoteamsactivity() {
        Intent intent = new Intent(AppHomePage.this , TeamsHomePg.class);
        startActivity(intent);
    }

    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.logout:
                if (user1 != "" || user2 != null) {
                    prefsEditor.clear();
                    prefsEditor.commit();

                    Intent login = new Intent(getApplicationContext(), AppHomePage.class);
                    startActivity(login);

                }
                return true;

            case R.id.teams_menu:
                movetoteamsactivity();
                return true;

            default:
                return false;
        }}
}
