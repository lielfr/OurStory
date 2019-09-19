package org.tsofen.ourstory.StoryTeam;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

import org.tsofen.ourstory.R;
import org.tsofen.ourstory.web.OurStoryService;
import org.tsofen.ourstory.web.WebFactory;

import java.util.ArrayList;
import java.util.List;


public class SearchStory extends AppCompatActivity {
    String s = "Story";
    String m = "Memory";
    private Spinner spinner;
    String item ;
    TextView searchresult ;
    StoryFragment CurrentFragment = null ;
    int flag = 0;
    int op = 0;
    int D;
    int M ;
    int Y ;
    int Taplayout;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_story);
        spinner = findViewById(R.id.sp);
        final Button S = findViewById(R.id.show);

        List<String> categories = new ArrayList<String>();
        categories.add(0, "BD:--/DD:---");
        categories.add("BirthDay: ");
        categories.add("Death Day: ");

        ArrayAdapter<String> dataAdapter;
        dataAdapter = new ArrayAdapter<String>(this, R.layout.spinner_item, categories);
        dataAdapter.setDropDownViewResource(R.layout.spinner_item);
        spinner.setAdapter(dataAdapter);
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
        Intent intent = getIntent() ;
        FragmentManager fragmentManager = getSupportFragmentManager();
        if (fragmentManager==null) {
            Log.i("fragment", "you have found it the fragment manager is null........................... ");
        }


        final PageAdapter adapter = new PageAdapter(fragmentManager, tabLayout.getTabCount());
        viewPager.setAdapter(adapter);

       CurrentFragment = (StoryFragment) adapter.getItem(0);
        if (CurrentFragment!=null){
            Log.i("fragment", "fragment has been attached !!!!!!!!!!!!1");
        }else{
            Log.i("fragment", "fragment is not attached FROM SEARCH ACTIVITY!!! ");

        }



        // Setting a listener for clicks.

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
                Taplayout = tab.getPosition();
                Button B = findViewById(R.id.button3);
                TextView ShowDate = findViewById(R.id.ShowDate);
                TextView cat = findViewById(R.id.catg);

                if(Taplayout==1) {
                    B.setVisibility(View.INVISIBLE);
                    spinner.setVisibility(View.GONE);
                     ShowDate.setVisibility(View.INVISIBLE);
                      cat.setVisibility(View.INVISIBLE);
                }
                else {

                    B.setVisibility(View.VISIBLE);
                    spinner.setVisibility(View.VISIBLE);
                    ShowDate.setVisibility(View.VISIBLE);
                    cat.setVisibility(View.VISIBLE);

                }
//                if (adapter.getItem(0)!=null){
//                    CurrentFragment =adapter.getItem(0);
//                    Log.i("fragment", "fragment has been attached !!!!!!!!!!!!1");
//                }else{
//                    Log.i("fragment", "cant get fragment from adapter");
//                }
//                if(getSupportFragmentManager().findFragmentById(R.id.FrameLayoutStory)==null){
//                    Log.i("fragment", "cant get the fragment from get support fragment manager ");
//                }else{
//                    CurrentFragment =getSupportFragmentManager().findFragmentById(R.id.FrameLayoutStory);
//                }
//                //Log.i("fragment", "CurrentFragment has been choosen");
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });
        if(CurrentFragment == null) {
            //CurrentFragment = adapter.getItem(0);
        }else{
//            Toast.makeText(this, "the current Fragmen is null ", Toast.LENGTH_SHORT).show();
        }
        searchresult = findViewById(R.id.searchresult);
        searchresult.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                // if (tabLayout)
                SendSearchResToFragment(searchresult.getText().toString(),0,CurrentFragment);

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        OurStoryService wb = WebFactory.getService();

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                TextView cat = findViewById(R.id.catg);

                if (adapterView.getItemAtPosition(i).equals("BD:--/DD:---")) {
                    cat.setText("BD:/DD:");

                } else {
                    item = adapterView.getItemAtPosition(i).toString();
                    cat.setText(item);
//                    Toast.makeText(adapterView.getContext(), "Selected:" + item, Toast.LENGTH_SHORT).show();
                    //here you want to add the parameters thar you need to send to the fragment in order to ake search by date
                    EditText editText = findViewById(R.id.searchresult);

                    TextView D1 = findViewById(R.id.Day);
                    String D2 = D1.getText().toString();
                    D = Integer.parseInt(D2);
//                    Toast.makeText(getApplicationContext(), "the value is " + D, Toast.LENGTH_SHORT).show();

                    TextView M1 = findViewById(R.id.Month);
                    String M2 = M1.getText().toString();
                    M = Integer.parseInt(M2);

                    TextView Y1 = findViewById(R.id.Year);
                    String Y2 = Y1.getText().toString();
                    Y = Integer.parseInt(Y2);
                    String option = cat.getText().toString();

                    if (option == "BirthDay: ") {
                        op = 1;
                    }
                    if (option == "Death Day: ") {
                        op = 2;
                    }
                    if (editText.getText().toString().equals("")||editText.getText().toString().equals("Story/Memory name")){
                        flag = 0 ;
                    }else{
                        flag = 1 ;
                    }

                    SendSearchResToFragment(searchresult.getText().toString(),1,CurrentFragment);

                }

                // here we have the selected item
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                Toast.makeText(SearchStory.this, "please choose deathDay NOR birthDay ", Toast.LENGTH_SHORT).show();
            }

        });

//        S.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick (View view){
//                StoryFragment testS=(StoryFragment) getSupportFragmentManager().findFragmentByTag("InStory");
//                MemoryFragment testM=(MemoryFragment) getSupportFragmentManager().findFragmentByTag("InMemory");
//
//                if(testS!=null && testS.isVisible()) {
//                    Log.d("Check","shcek");
//                    Fragment fragment = new StoryFragment();
//
//                    FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
//                    transaction.replace(R.id.pager, fragment).commit();
//
//
//                    }
//                if(testM!=null && testM.isVisible())
//                    {
//                    Fragment fragment = new MemoryFragment();
//                    FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
//                    transaction.replace(R.id.pager, fragment).commit();
//
//                }
//            }
//        });
    }

    public void SendSearchResToFragment(String searchresult,int SearchBy,StoryFragment fragment) {

        Log.i("fragment","trying to catch fragment");

        if (fragment != null) { //TODO MEMORYTEAM Please check if the fragment parameter is story fragment Or MEMORYFRAGMENT AND ACT ACCORDINGLY
            //this section for STORYTEAM Use
            fragment.CommitSearch(getApplicationContext(), searchresult, flag, op, D, M, Y, SearchBy);
        }else{
            Log.i("fragment","fragment is null");
        }

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