package org.tsofen.ourstory;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.app.ShareCompat;
import androidx.recyclerview.widget.RecyclerView;

import org.tsofen.ourstory.model.Comment;
import org.tsofen.ourstory.model.Tag;
import org.tsofen.ourstory.model.api.MemoryA;
import org.tsofen.ourstory.model.Memory;
import org.tsofen.ourstory.model.api.Story;
import org.tsofen.ourstory.model.api.Tags;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Calendar;

public class MyMemoriesAdapter extends RecyclerView.Adapter<MyMemoriesAdapter.ViewHolder> {

    private static final String LOG_TAG = CommentActivity.class.getSimpleName();
    public static final String EXTRA_MESSAGE = "org.tsofen.ourstory.extra.MESSAGE";
    public ArrayList<Memory> mMemories;
    Context ctx;
    LayoutInflater mInflater;
    Memory mem;

    public MyMemoriesAdapter(Context context, ArrayList<Memory> memories) {
        this.mMemories = memories;
        mInflater = LayoutInflater.from(context);
    }

    public MyMemoriesAdapter(Context context) {
        this.ctx = context;
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

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Memory memory = mMemories.get(position);
        holder.commentbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(ctx.getApplicationContext(), CommentActivity.class);
                intent.putExtra("memory", memory);
                ctx.startActivity(intent);

            }
        });

        holder.editbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent();
                mem = memory;
                i.putExtra("CEMemoryEdit", mem);

            }
        });



    if(memory.getDescription()!=null) {
        holder.descr.setText(memory.getDescription());
    } else
        holder.descr.setVisibility(View.INVISIBLE);
        if (memory.getTags() != null) {
            String s = "#";
            for (Tag tag : memory.getTags()) {
                s += tag.getLabel();
            }
            holder.tags.setText(s);
        } else
            holder.tags.setVisibility(View.INVISIBLE);
        Story story = memory.getStory();
        holder.name.setText(story.getNameOfPerson());
        String[] monthNames = {" ", "January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
        if(memory.getMemoryDate()!=null) {
            String memDate = monthNames[memory.getMemoryDate().get(Calendar.MONTH+1)] + " " + memory.getMemoryDate().get(Calendar.DAY_OF_MONTH) + " , " + (memory.getMemoryDate().get(Calendar.YEAR));
            holder.mem_date.setText(memDate);
        }
        else
            holder.mem_date.setVisibility(View.INVISIBLE);

        if (memory.getStory().getPicture() != null) {
                holder.profile.setImageURI(Uri.parse(memory.getStory().getPicture().toString()));
        }
        else {
            holder.profile.setImageLevel(R.drawable.defaultprofilepicture);
        }
        if(memory.getLikes().isEmpty())
            holder.num_of_likes.setVisibility(View.INVISIBLE);
        else
            holder.num_of_likes.setText(memory.getLikes().size() + "");
        if(memory.getComments().isEmpty())
            holder.num_of_comments.setVisibility(View.INVISIBLE);
        else
            holder.num_of_comments.setText(memory.getComments().size()+"");
       if(memory.getLocation()!=null)
           holder.location.setText(memory.getLocation());
       else
           holder.location.setVisibility(View.INVISIBLE);
        if(memory.getFeeling()!=null)
            holder.feeling.setText(memory.getFeeling().toString());
        else
            holder.feeling.setVisibility(View.INVISIBLE);
    }
    @Override
    public int getItemCount() {
        return mMemories.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tags, feeling, name, mem_date, descr, num_of_likes, num_of_comments, location;
        public ImageView profile;
        ImageButton sharebtn,commentbtn, editbtn;
        public MyMemoriesAdapter adapter;


        public ViewHolder(@NonNull View itemView, MyMemoriesAdapter MyMemoriesAdapter) {
            super(itemView);
            ctx = itemView.getContext();

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
