package org.tsofen.ourstory;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
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
import org.tsofen.ourstory.model.api.Story;

import java.util.ArrayList;
import java.util.Calendar;

public class MyMemoriesAdapter extends RecyclerView.Adapter<MyMemoriesAdapter.ViewHolder> {

    private static final String LOG_TAG = CommentActivity.class.getSimpleName();
    public static final String EXTRA_MESSAGE = "org.tsofen.ourstory.extra.MESSAGE";
    public ArrayList<MemoryA> mMemories;
    Context ctx;
    LayoutInflater mInflater;

    public MyMemoriesAdapter(Context context,ArrayList<MemoryA> memories) {
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
        ctx=parent.getContext();
        ViewHolder viewHolder = new ViewHolder(contactView, this);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        MemoryA memory = mMemories.get(position);
        holder.commentbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(ctx.getApplicationContext(), CommentActivity.class);
                intent.putExtra("memory", memory);
                ctx.startActivity(intent);

            }
        });
    if(memory.getDescription()!=null) {
        holder.descr.setText(memory.getDescription());
    }
    /*   Story story = (Story) memory.getStory();
     holder.name.setText(story.getNameOfPerson());*/
        String[] monthNames = {" ", "January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
        if(memory.getMemoryDate()!=null) {
       String memDate = monthNames[memory.getMemoryDate().getMonth()] + " " + memory.getMemoryDate().getDay()+ " , " + (memory.getMemoryDate().getYear());
            holder.mem_date.setText(memDate);
        }
        else
            holder.mem_date.setVisibility(View.GONE);

        if(memory.getLikes().isEmpty())
            holder.num_of_likes.setText("");
        else
            holder.num_of_likes.setText(memory.getLikes().size());
        if(memory.getComments().isEmpty())
            holder.num_of_comments.setVisibility(View.GONE);
        else
            holder.num_of_comments.setText(memory.getComments().size());
       if(memory.getLocation()!=null)
           holder.location.setText((String) memory.getLocation());
       else
           holder.location.setVisibility(View.GONE);
        if(memory.getFeeling()!=null)
            holder.feeling.setText(memory.getFeeling().toString());
        else
            holder.feeling.setVisibility(View.GONE);
    }
    @Override
    public int getItemCount() {
        return mMemories.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView feeling, name, mem_date, descr, num_of_likes, num_of_comments, location;
        public ImageView profile;
        ImageButton sharebtn,commentbtn;
        public MyMemoriesAdapter adapter;


        public ViewHolder(@NonNull View itemView, MyMemoriesAdapter MyMemoriesAdapter) {
            super(itemView);
            ctx = itemView.getContext();

            sharebtn = itemView.findViewById(R.id.sharebtn);
            commentbtn = itemView.findViewById(R.id.commentbtn2);
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
