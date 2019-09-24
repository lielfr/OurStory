package org.tsofen.ourstory;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import org.tsofen.ourstory.model.Memory;
import org.tsofen.ourstory.model.Picture;
import org.tsofen.ourstory.model.Tag;
import org.tsofen.ourstory.model.api.Like;
import org.tsofen.ourstory.model.api.User;
import org.tsofen.ourstory.web.OurStoryService;
import org.tsofen.ourstory.web.WebFactory;

import java.util.ArrayList;
import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ViewMemory extends AppCompatActivity {

    public TextView nameS,tags, feeling, location, name, mem_date, descr, num_of_likes, num_of_comments;
    public ImageView pic;
    public ImageButton commentbtn,sharebtn,likebtn;
    OurStoryService MemoryAService;
    long id;
    RecyclerView imagesrv;
    Memory memory;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_memory);
        Intent intent = getIntent();
        id = intent.getLongExtra("memoryId" , id);
        nameS = findViewById(R.id.nameS);
        imagesrv= findViewById(R.id.memory_pic);
        commentbtn =  findViewById(R.id.commentbtn2);
        likebtn=  findViewById(R.id.likebtn);
        sharebtn = findViewById(R.id.sharebtn2);
        feeling = findViewById(R.id.feelingtxt);
        location = findViewById(R.id.locationtxt);
        name = findViewById(R.id.name_txt_person);
        mem_date = findViewById(R.id.memory_date);
        descr = findViewById(R.id.descr);
        pic = findViewById(R.id.picture_person);
        num_of_comments = findViewById(R.id.commentNum);
        num_of_likes = findViewById(R.id.likesNum);
        tags = findViewById(R.id.tags_text);
        MemoryAService = WebFactory.getService();
        MemoryAService.GetMemoryById(id).enqueue(new Callback<Memory>() {
            @Override
            public void onResponse(Call<Memory> call, Response<Memory> response) {
                memory = response.body();
              if(memory!=null) {
                  if(memory.getStory()!=null && memory.getStory().getNameOfPerson()!=null)
                  {
                      nameS.setText(memory.getStory().getNameOfPerson());
                      nameS.setWidth(calculateWidth(memory.getStory().getNameOfPerson()));
                  }
                  else
                      nameS.setVisibility(View.INVISIBLE);
                  User user = memory.getUser();
                if (user != null) {
                    if (memory.getUser().getProfilePicture() != null) {
                        Uri uri = Uri.parse(memory.getUser().getProfilePicture());
                        RequestOptions options = new RequestOptions()
                                .override(300, 300)
                                .centerCrop()
                                .placeholder(R.drawable.nopicyet)
                                .error(R.drawable.nopicyet);
                        Glide.with(getApplicationContext()).load(uri).apply(options).into(pic);
                    }
                    else {
                        pic.setImageResource(R.drawable.defaultprofilepicture);}

                      if(memory.getUser().getFullName()!=null)
                      {name.setText(memory.getUser().getFullName());
                    }
                      else
                          name.setVisibility(View.INVISIBLE);
                }
                  if (user != null) {
                      likebtn.setOnClickListener(new View.OnClickListener() {
                          @Override
                          public void onClick(View view) {
                              OurStoryService Like = WebFactory.getService();
                              Like like = new Like();
                              like.setUser(user);
                              Like.addLike(memory.getId(), like).enqueue(new Callback<org.tsofen.ourstory.model.api.Like>() {

                                  @Override
                                  public void onResponse(Call<org.tsofen.ourstory.model.api.Like> call, Response<org.tsofen.ourstory.model.api.Like> response) {
                                      Toast.makeText(getApplicationContext(), "like added", Toast.LENGTH_LONG).show();
                                      OurStoryService service = WebFactory.getService();
                                      service.GetMemoryById(memory.getId()).enqueue(new Callback<Memory>() {
                                          @Override
                                          public void onResponse(Call<Memory> call, Response<Memory> response) {
                                              if (response.code() == 200) {
                                                  Memory memorya = response.body();
                                                  num_of_likes.setText(memorya.getLikes().size() + "");
                                                  //notifyDataSetChanged();
                                              }
                                          }

                                          @Override
                                          public void onFailure(Call<Memory> call, Throwable t) {

                                          }
                                      });

                                  }

                                  @Override
                                  public void onFailure(Call<org.tsofen.ourstory.model.api.Like> call, Throwable t) {

                                  }
                              });

                          }
                      });
                  } else {
                      AlertDialog.Builder myAlertBuilder = new
                              AlertDialog.Builder(getApplicationContext());
                      myAlertBuilder.setTitle("Error");
                      myAlertBuilder.setMessage("Please Sign in to like this memory.");
                      // Set the dialog title and message.
                      myAlertBuilder.setPositiveButton("Ok", new
                              DialogInterface.OnClickListener() {
                                  public void onClick(DialogInterface dialog, int which) {

                                  }
                              });
                      myAlertBuilder.show();
                  }
                  commentbtn.setOnClickListener(new View.OnClickListener() {
                      @Override
                      public void onClick(View view) {

                          Intent intent = new Intent(getApplicationContext(), CommentActivity.class);
                          intent.putExtra("memory", memory);
                          intent.putExtra("user", user);
                          startActivity(intent);

                      }
                  });
                  sharebtn.setOnClickListener(new View.OnClickListener() {
                      @Override
                      public void onClick(View view) {
                          Intent sendIntent = new Intent();
                          sendIntent.setAction(Intent.ACTION_SEND);
                          sendIntent.putExtra(Intent.EXTRA_TEXT, "This is my text to send.");
                          sendIntent.setType("text/plain");
                          Intent shareIntent = Intent.createChooser(sendIntent, null);
                          startActivity(shareIntent);
                      }
                  });
                  if (memory.getDescription() != null) {
                      descr.setText(memory.getDescription());
                  } else if (descr != null) {
                      descr.setVisibility(View.INVISIBLE);
                  }
                  if (memory.getLocation() != null) {
                      location.setWidth(calculateWidth(memory.getLocation()));
                      location.setText(memory.getLocation());
                  } else {
                      location.setVisibility(View.INVISIBLE);
                  }
                  if (memory.getFeeling() != null) {
                      feeling.setWidth(calculateWidth("#" + memory.getFeeling()));
                      feeling.setText("#" + memory.getFeeling());
                  } else {
                      feeling.setVisibility(View.INVISIBLE);
                  }
                  String[] monthNames = {" ", "January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
                  if (memory.getMemoryDate() != null) {
                      String memDate = monthNames[memory.getMemoryDate().get(Calendar.MONTH) + 1] + " " + memory.getMemoryDate().get(Calendar.DAY_OF_MONTH) + ", " + (memory.getMemoryDate().get(Calendar.YEAR));
                      mem_date.setWidth(calculateWidth(memDate));
                      mem_date.setText(memDate);
                  } else
                      mem_date.setVisibility(View.INVISIBLE);
                  if (memory.getLikes() != null && memory.getLikes().size()!=0) {
                      num_of_likes.setText(memory.getLikes().size() + "");
                  } else {
                      num_of_likes.setVisibility(View.INVISIBLE);
                  }
                  if (memory.getComments() != null && memory.getComments().size()!=0) {
                      num_of_comments.setText(memory.getComments().size() + "");
                  } else {
                      num_of_comments.setVisibility(View.INVISIBLE);
                  }
                  if (memory.getTags() != null) {
                      String s = "";
                      for (Tag tag : memory.getTags()) {
                          s += "#" + tag.getLabel();
                      }
                      tags.setText(s);
                  }
                  else
                  {
                     tags.setVisibility(View.INVISIBLE);
                  }
                  if (memory.getPictures() != null) {
                      ArrayList<ImgItem> images = new ArrayList<>();
                      ArrayList<Picture> pictures = new ArrayList<>();
                      pictures = memory.getPictures();
                      for (int i = 0; i < pictures.size(); i++) {
                          images.add(new ImgItem(" ", pictures.get(i).getLink()));
                      }
                      ImageAdapter imgAdapter = new ImageAdapter(getApplicationContext(), images );
                      imagesrv.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false));
                      imagesrv.setAdapter(imgAdapter);
                  } else {
                      imagesrv.setVisibility(View.INVISIBLE);
                  }

              }}
            @Override
            public void onFailure(Call<Memory> call, Throwable t) {
//                Toast.makeText(getApplicationContext(),"I'm null",Toast.LENGTH_LONG);
            }
        });
    }

    public int calculateWidth(String text) {
        Rect bounds = new Rect();
        TextView textView = new TextView(getApplicationContext());
        textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 24);
        textView.getPaint().getTextBounds(text, 0, text.length(), bounds);
        return bounds.width();
    }

    public void closeActivity(View view) {
        finish();
    }
}
