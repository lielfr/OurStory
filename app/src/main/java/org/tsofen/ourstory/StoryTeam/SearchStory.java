package org.tsofen.ourstory.StoryTeam;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

import org.tsofen.ourstory.R;

import java.util.ArrayList;
import java.util.List;


public class SearchStory extends AppCompatActivity {

    String s = "Story";
    String m = "Memory";
    private Spinner spinner;
   String item;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_story);
        spinner = findViewById(R.id.spinner);
        final Button S = findViewById(R.id.show);

        List<String> categories = new ArrayList<String>();
        categories.add(0, "BD:--/DD:---");
        categories.add("BirthDay: ");
        categories.add("Death Day: ");

        ArrayAdapter<String> dataAdapter;
        dataAdapter = new ArrayAdapter<String>(this, R.layout.spinner_item, categories);
        dataAdapter.setDropDownViewResource(R.layout.spinner_item);
        spinner.setAdapter(dataAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                TextView cat = findViewById(R.id.catg);

                if (adapterView.getItemAtPosition(i).equals("BD:--/DD:---")) {
                    cat.setText("BD:/DD:");

                } else {
                    item = adapterView.getItemAtPosition(i).toString();
                    cat.setText(item);
                    Toast.makeText(adapterView.getContext(), "Selected:" + item, Toast.LENGTH_SHORT).show();
                }
// here we have the selected item
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }

        });
        TabLayout tabLayout = findViewById(R.id.tab_layout);
        // Set the text for each tab.
        tabLayout.addTab(tabLayout.newTab().setText(s));
        tabLayout.addTab(tabLayout.newTab().setText(m));
        // Set the tabs to fill the entire layout.
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        // Use PagerAdapter to manage page views in fragments.

        // Use PagerAdapter to manage page views in fragments.
        // Each page is represented by its own fragment.
        final ViewPager viewPager = findViewById(R.id.pager);
        final PageAdapter adapter = new PageAdapter(getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(adapter);
        // Setting a listener for clicks.

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });
        S.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View view){
                StoryFragment testS=(StoryFragment) getSupportFragmentManager().findFragmentByTag("InStory");
                MemoryFragment testM=(MemoryFragment) getSupportFragmentManager().findFragmentByTag("InMemory");

                if(testS!=null && testS.isVisible()) {
                    Log.d("Check","shcek");
                    Fragment fragment = new StoryFragment();

                    FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                    transaction.replace(R.id.pager, fragment).commit();


                    }
                if(testM!=null && testM.isVisible())
                    {
                    Fragment fragment = new MemoryFragment();
                    FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                    transaction.replace(R.id.pager, fragment).commit();

                }
            }
        });
    }


    public void finish(View view) {
        finish();
    }

    public void ShowDatePicker(View view) {
        DialogFragment newFragment = new DatePickerFragment2();
        newFragment.show(getSupportFragmentManager(), "datePicker");

    }

    public void processDatePickerResult1(int year, int month, int day) {
        String month_string = Integer.toString(month + 1);
        String day_string = Integer.toString(day);
        String year_string = Integer.toString(year);


        String Date = day_string + "/" + month_string + "/" + year_string;
        // Toast.makeText(this, currentDate,Toast.LENGTH_SHORT).show();

        TextView ShowDate = findViewById(R.id.ShowDate);
        ShowDate.setText(Date);
        TextView Day = findViewById(R.id.Day);
        TextView Month = findViewById(R.id.Month);
        TextView Year = findViewById(R.id.Year);

        Day.setText(day_string);
        Month.setText(month_string);
        Year.setText(year_string);

    }





}