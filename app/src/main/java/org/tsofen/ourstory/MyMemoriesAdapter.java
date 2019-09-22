package org.tsofen.ourstory;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Rect;
import android.net.Uri;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ShareCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import org.tsofen.ourstory.EditCreateMemory.CreateEditMemoryActivity;
import org.tsofen.ourstory.StoryTeam.MainActivity;
import org.tsofen.ourstory.model.Memory;
import org.tsofen.ourstory.model.Tag;
import org.tsofen.ourstory.model.api.Story;
import org.tsofen.ourstory.model.api.User;
import org.tsofen.ourstory.web.OurStoryService;
import org.tsofen.ourstory.web.WebFactory;

import java.util.ArrayList;
import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyMemoriesAdapter extends RecyclerView.Adapter<MyMemoriesAdapter.ViewHolder> {

    private static final String LOG_TAG = CommentActivity.class.getSimpleName();
    public static final String EXTRA_MESSAGE = "org.tsofen.ourstory.extra.MESSAGE";
    public ArrayList<Memory> mMemories;
    Context ctx;
    User user;
    LayoutInflater mInflater;
    Memory mem;



    public MyMemoriesAdapter(Context context, ArrayList<Memory> memories, User userObj) {
        this.mMemories = memories;
        mInflater = LayoutInflater.from(context);
        this.user = userObj;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext(); // getting the main activity
        LayoutInflater inflater = LayoutInflater.from(context); // put layout of main activity in layout inflater
        View contactView = inflater.inflate(R.layout.memory_item_my_memories, parent, false);
        ctx = parent.getContext();
        ViewHolder viewHolder = new ViewHolder(contactView, this);


        return viewHolder;


    }
    public int calculateWidth(String text) {
        Rect bounds = new Rect();
        TextView textView = new TextView(ctx);
        textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 24);
        textView.getPaint().getTextBounds(text, 0, text.length(), bounds);
        return bounds.width();
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Memory memory = mMemories.get(position);
        holder.commentbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(ctx.getApplicationContext(), CommentActivity.class);
                intent.putExtra("memory",memory);
                intent.putExtra("user",user);
                ctx.startActivity(intent);

            }
        });

        holder.editbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(ctx.getApplicationContext(), CreateEditMemoryActivity.class);
                mem = memory;
                i.putExtra("CEMemoryEdit", mem);
                ctx.startActivity(i);

            }
        });

        holder.deletebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder myAlertBuilder = new
                        AlertDialog.Builder(ctx);
                myAlertBuilder.setTitle("Delete Memory");
                myAlertBuilder.setMessage("Are you sure you want to delete this memory?");
                // Set the dialog title and message.
                myAlertBuilder.setPositiveButton("Yes", new
                        DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                OurStoryService deleteMemory;
                        deleteMemory = WebFactory.getService();
                        deleteMemory.DeleteMemory(((memory).getId())).enqueue(new Callback<Object>() {

                            @Override
                            public void onResponse(Call<Object> call, Response<Object> response) {

                            }

                            @Override
                            public void onFailure(Call<Object> call, Throwable t) {

                            }
                        });
                            }
                        });
                myAlertBuilder.setNegativeButton("No", new
                        DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // User cancelled the dialog.

                            }
                        });
                myAlertBuilder.show();

                    }
        });

        holder.sharebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, "This is my text to send.");
                sendIntent.setType("text/plain");

                Intent shareIntent = Intent.createChooser(sendIntent,null);

                ctx.startActivity(shareIntent);
            }
        });

    if(memory.getDescription()!=null) {
        holder.descr.setText(memory.getDescription());
    } else
        holder.descr.setVisibility(View.INVISIBLE);
        if (memory.getTags() != null) {
            String s = "";
            for (Tag tag : memory.getTags()) {
                s += "#"+tag.getLabel();
            }
            holder.tags.setText(s);
        } else
            holder.tags.setVisibility(View.INVISIBLE);
        Story story = memory.getStory();
        holder.name.setText(story.getNameOfPerson());
        String[] monthNames = {" ", "January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
        if(memory.getMemoryDate()!=null) {
            String memDate = monthNames[memory.getMemoryDate().get(Calendar.MONTH) + 1] + " " + memory.getMemoryDate().get(Calendar.DAY_OF_MONTH) + " , " + (memory.getMemoryDate().get(Calendar.YEAR));
            holder.mem_date.setText(memDate);
            holder.mem_date.setWidth(calculateWidth(memDate));
        }
        else
            holder.mem_date.setVisibility(View.INVISIBLE);

        if (memory.getStory().getPicture() != null) {

            Uri uri = Uri.parse(memory.getStory().getPicture().toString());
            RequestOptions options = new RequestOptions()
                    .override(300, 300)
                    .centerCrop()
                    .placeholder(R.drawable.nopicyet)
                    .error(R.drawable.nopicyet);
            Glide.with(this.mInflater.getContext()).load(uri).apply(options).into(holder.profile);
        }
        else {
            holder.profile.setImageResource(R.drawable.defaultprofilepicture);
        }
        if(memory.getLikes().isEmpty())
            holder.num_of_likes.setVisibility(View.INVISIBLE);
        else
            holder.num_of_likes.setText(memory.getLikes().size() + "");
        if(memory.getComments().isEmpty())
            holder.num_of_comments.setVisibility(View.INVISIBLE);
        else
            holder.num_of_comments.setText(memory.getComments().size()+"");
       if(memory.getLocation()!=null) {
           holder.location.setText(memory.getLocation());
           holder.location.setWidth(calculateWidth(memory.getLocation()));
       }
       else
           holder.location.setVisibility(View.INVISIBLE);
        if(memory.getFeeling()!=null) {
            holder.feeling.setText("#" + memory.getFeeling());
            holder.feeling.setWidth(calculateWidth("#"+memory.getFeeling()));
        }
        else
            holder.feeling.setVisibility(View.INVISIBLE);
       /* ArrayList<ImgItem> images=new ArrayList<>();
        if(memory.getPictures()!=null) {
            images.add((ImgItem) memory.getPictures());
            ImageAdapter imgAdapter = new ImageAdapter(ctx, images);
            holder.imagesrv.setHasFixedSize(true);
            holder.imagesrv.setLayoutManager(new LinearLayoutManager(ctx, LinearLayoutManager.HORIZONTAL, false));
            holder.imagesrv.setAdapter(imgAdapter);
        }
        else
        {
            holder.imagesrv.setVisibility(View.INVISIBLE);
        }*/
    }
    @Override
    public int getItemCount() {
        return mMemories.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tags, feeling, name, mem_date, descr, num_of_likes, num_of_comments, location;
        public ImageView profile;
        ImageButton sharebtn,commentbtn, editbtn,deletebtn;
        public MyMemoriesAdapter adapter;
        //RecyclerView imagesrv;


        public ViewHolder(@NonNull View itemView, MyMemoriesAdapter MyMemoriesAdapter) {
            super(itemView);
            ctx = itemView.getContext();
            // imagesrv = itemView.findViewById(R.id.my_memoriesRv);
            deletebtn =itemView.findViewById(R.id.deletebtn);
            tags = itemView.findViewById(R.id.tags_text);
            sharebtn = itemView.findViewById(R.id.sharebtn);
            commentbtn = itemView.findViewById(R.id.commentbtn2);
            editbtn = itemView.findViewById(R.id.editbtn);
            name = itemView.findViewById(R.id.name_txt_person);
            mem_date = itemView.findViewById(R.id.memory_date);
            num_of_comments = itemView.findViewById(R.id.commentNum);
            num_of_likes = itemView.findViewById(R.id.likesNum);
            descr = itemView.findViewById(R.id.descr);
            location = itemView.findViewById(R.id.locationtxt_mymemories);
            feeling = itemView.findViewById(R.id.feelingtxt_mymemories);
            profile = itemView.findViewById(R.id.picture_person);
            adapter = MyMemoriesAdapter;


        }
    }


}
