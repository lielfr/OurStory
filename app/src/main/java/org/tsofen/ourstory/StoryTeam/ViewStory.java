package org.tsofen.ourstory.StoryTeam;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ourstory.R;

import java.io.Serializable;

public class ViewStory extends AppCompatActivity implements Serializable {

    ImageButton ib;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_story);

        Intent intent = getIntent();

        String fName = intent.getStringExtra("first name");
        String lName = intent.getStringExtra("last name");
        String date1 = intent.getStringExtra("date1");
        String tag1 = intent.getStringExtra("ttag3");
        String tag2 = intent.getStringExtra("ttag2");
        String tag3 = intent.getStringExtra("ttag1");
        String date2 = intent.getStringExtra("date2");
        int iv = intent.getIntExtra("image",0);
        int ic1 = intent.getIntExtra("tag1",0);
        int ic2= intent.getIntExtra("tag2",0);
        int ic3= intent.getIntExtra("tag3",0);

        Story story = new Story(fName,lName,date1,date2,iv,tag1,tag2,tag3,ic1,ic2,ic3);

        String FL_name;
        FL_name=story.getFirstName()+ " " +story.getLastName();
        TextView textView=(findViewById(R.id.textView));
        textView.setText(FL_name);
        ImageView image4=findViewById(R.id.imageView3);
        image4.setImageResource(story.getImg());

        String date;
        date=story.getDate1()+" - "+story.getDate2();
        TextView textView2=(findViewById(R.id.textView2));
        textView2.setText(date);

        TextView textView4=(findViewById(R.id.textView4));
        textView4.setText(story.getTag1());

        TextView textView5=(findViewById(R.id.textView5));
        textView5.setText(story.getTag2());

        TextView textView6=(findViewById(R.id.textView6));
        textView6.setText(story.getTag3());

        ImageView image1=findViewById(R.id.imageView5);
        image1.setImageResource(story.getTag_icon1());

        ImageView image2=findViewById(R.id.imageView7);
        image2.setImageResource(story.getTag_icon2());

        ImageView image3=findViewById(R.id.imageView6);
        image3.setImageResource(story.getTag_icon3());
    }

    public void launchSearchActivity(View view) {
        Intent i = new Intent(ViewStory.this, SearchStory.class);
        startActivity(i);
    }
}
