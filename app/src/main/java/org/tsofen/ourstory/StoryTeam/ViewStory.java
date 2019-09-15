package org.tsofen.ourstory.StoryTeam;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import org.tsofen.ourstory.EditCreateMemory.CreateEditMemoryActivity;
import org.tsofen.ourstory.R;
import org.tsofen.ourstory.UserModel.LogIn;
import org.tsofen.ourstory.model.api.Story;

import java.io.Serializable;

public class ViewStory extends AppCompatActivity implements Serializable {

    ImageButton ib;
    ImageButton share;;
    Story target_story ;
    //Intent intent = getIntent() ;
    TextView searchtext ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_story);

        LinearLayout linearLayout = findViewById(R.id.linearLayout3);
        linearLayout.setVisibility(View.INVISIBLE);

        ConstraintLayout constraintLayout = findViewById(R.id.constrainlayout2);
        constraintLayout.setVisibility(View.VISIBLE);
        searchtext = (EditText) findViewById(R.id.edit);
        Intent intent = getIntent() ;

        Log.i("oncreat","onCreate has been access");
        Log.i("oncreat","extra key is"+ intent.getStringExtra("button"));
        if(intent.getStringExtra("Button").equals("createandadd")){
            Log.i("oncreat","int if");
            Intent i = new Intent(this, CreateEditMemoryActivity.class);
            i.putExtra(CreateEditMemoryActivity.KEY_CREATE, intent.getSerializableExtra("id"));
            startActivity(i);
        }


        String fName = intent.getStringExtra("name");
        String date1 = intent.getStringExtra("date1");
        String tag1 = intent.getStringExtra("ttag3");
        String tag2 = intent.getStringExtra("ttag2");
        String tag3 = intent.getStringExtra("ttag1");
        String date2 = intent.getStringExtra("date2");
        int iv = intent.getIntExtra("image", 0);
        int ic1 = intent.getIntExtra("tag1", 0);
        int ic2 = intent.getIntExtra("tag2", 0);
        int ic3 = intent.getIntExtra("tag3", 0);
        String nameofperson = intent.getStringExtra("name");
        TextView textView = (findViewById(R.id.textView));
        textView.setText(nameofperson);



        ImageView image4 = findViewById(R.id.imageView3);
        image4.setImageResource(R.drawable.nopicyet);

        String date;
        date = date1 + "-" + date2;
        TextView textView2 = (findViewById(R.id.textView2));
        textView2.setText(date);

//        TextView textView4 = (findViewById(R.id.textView4));
//        textView4.setText(story.getTag1());
//
//        TextView textView5 = (findViewById(R.id.textView5));
//        textView5.setText(story.getTag2());
//
//        TextView textView6 = (findViewById(R.id.textView6));
//        textView6.setText(story.getTag3());
//
//        ImageView image1 = findViewById(R.id.imageView5);
//        image1.setImageResource(story.getTag_icon1());
//
//        ImageView image2 = findViewById(R.id.imageView7);
//        image2.setImageResource(story.getTag_icon2());
//
//        ImageView image3 = findViewById(R.id.imageView6);
//        image3.setImageResource(story.getTag_icon3());


        searchtext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ViewStory.this, "search is "+ searchtext.getText().toString() , Toast.LENGTH_SHORT).show();
                Intent i = new Intent(ViewStory.this, SearchStory.class);
                i.putExtra("searchinpute", searchtext.getText().toString());
                startActivity(i);
            }
        });
    }

    public void launchSearchActivity(View view) {

    }

    public void launchShare(View view) {
//        share = findViewById(R.id.sharebtn);

//        Intent myIntent = new Intent(Intent.ACTION_SEND);
//        myIntent.setType("text/plain");
//        String shareBody = "The Story of Pini Cohen";
//        String sharesub = "Your subject here";
//        myIntent.putExtra(Intent.EXTRA_SUBJECT, sharesub);
//        myIntent.putExtra(Intent.EXTRA_TEXT, shareBody);
//        startActivity(Intent.createChooser(myIntent, "Share using: "));
    }
}
