package org.tsofen.ourstory;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.app.ShareCompat;
import androidx.recyclerview.widget.RecyclerView;

import org.tsofen.ourstory.model.Memory;
import org.tsofen.ourstory.model.Tag;

import java.util.ArrayList;
import java.util.Calendar;

public class MyMemoriesAdapter extends RecyclerView.Adapter<MyMemoriesAdapter.ViewHolder> {

    public ArrayList<Memory> mMemories;

    public MyMemoriesAdapter(ArrayList<Memory> memories) {
        this.mMemories = memories;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext(); // getting the main activity
        LayoutInflater inflater = LayoutInflater.from(context); // put layout of main activity in layout inflater

        // inflate the custom layout
        View contactView = inflater.inflate(R.layout.memory_item_my_memories, parent, false);


        // return a new holder instance
        ViewHolder viewHolder = new ViewHolder(contactView, this);


        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Memory memory = mMemories.get(position);
        String tags="#";
        if(memory.getDescription()!=null && !(memory.getTags().isEmpty())) {
                for(Tag tag: memory.getTags())
                {
                    tags += "#"+ tag.getLabel();
                }
                holder.descr.setText(memory.getDescription() + tags);
            }
        else if(memory.getTags().isEmpty() && memory.getDescription()!=null)
                holder.descr.setText(memory.getDescription());
        else if(!memory.getTags().isEmpty()&&memory.getDescription()==null)
        {
            for(Tag tag: memory.getTags())
            {
                tags += "#"+ tag.getLabel();
            }
            holder.descr.setText(tags);
        }
        else {
            holder.descr.setVisibility(View.GONE);
        }
     //Todo get story name by id .   holder.name.setText(memory.getStoryId());
        String[] monthNames = {" ", "January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
        if(memory.getMemoryDate()!=null) {
            String memDate = monthNames[memory.getMemoryDate().get(Calendar.MONTH)] + " " + memory.getMemoryDate().get(Calendar.DAY_OF_MONTH) + " , " + (memory.getMemoryDate().get(Calendar.YEAR));
            holder.mem_date.setText(memDate);
        }
        else
            holder.mem_date.setVisibility(View.GONE);
        if(memory.getLikes().isEmpty())
            holder.num_of_likes.setVisibility(View.GONE);
        else
            holder.num_of_likes.setText(memory.getLikes().size());
        if(memory.getComments().isEmpty())
            holder.num_of_comments.setVisibility(View.GONE);
        else
            holder.num_of_comments.setText(memory.getComments().size());
       if(memory.getLocation()!=null)
           holder.location.setText(memory.getLocation());
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
        public TextView feeling,name, mem_date, descr ,num_of_likes, num_of_comments,location;
        public ImageView profile;
        public MyMemoriesAdapter adapter;


        public ViewHolder(@NonNull View itemView, MyMemoriesAdapter MyMemoriesAdapter) {
            super(itemView);
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
