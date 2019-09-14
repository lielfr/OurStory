package org.tsofen.ourstory;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import org.tsofen.ourstory.UserModel.AppHomePage;
import org.tsofen.ourstory.model.Memory;
import org.tsofen.ourstory.model.Tag;
import org.tsofen.ourstory.model.api.Contributer;
import org.tsofen.ourstory.model.api.MemoryA;
import org.tsofen.ourstory.model.api.User;

import java.util.ArrayList;
import java.util.Calendar;

public class MemoryAdapter extends RecyclerView.Adapter<MemoryAdapter.ViewHolder> {

    public static final String EXTRA_MESSAGE = "org.tsofen.ourstory.extra.MESSAGE";
    public final ArrayList<MemoryA> mMemories;
    MemoryA memoryA;
    Context ctx;
    LayoutInflater mInflater;
    public MemoryAdapter(Context context,ArrayList<MemoryA> memories)
    {
        this.mMemories = memories;
        mInflater = LayoutInflater.from(context);
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext(); // getting the main activity
        LayoutInflater inflater = LayoutInflater.from(context); // put layout of main activity in layout inflater
        View contactView = inflater.inflate(R.layout.memory_item, parent, false);
        ctx=parent.getContext();
        ViewHolder viewHolder = new ViewHolder(contactView, this);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        MemoryA memory = mMemories.get(position);
        Contributer contributer = memory.getContributer();
        if(memory.getContributer().getProfilePicture()!=null)
        {
            holder.pic.setImageURI((Uri) memory.getContributer().getProfilePicture());
        }
        else {
            holder.pic.setImageLevel(R.drawable.defaultprofilepicture);
        }
        holder.name.setText(memory.getContributer().getFullName());
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
     if(memory.getLocation()!=null)
     {
         holder.location.setText(memory.getLocation());
     }
     if(memory.getFeeling()!=null)
     {
         holder.feeling.setText(memory.getFeeling());
     }
        String[] monthNames = {" ", "January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
        if(memory.getMemoryDate()!=null) {
            String memDate = monthNames[memory.getMemoryDate().getMonth()] + " " + memory.getMemoryDate().getDay()+ " , " + (memory.getMemoryDate().getYear());
            holder.mem_date.setText(memDate);
        }
        else
            holder.mem_date.setVisibility(View.GONE);
        if(memory.getLikes()!=null) {
           holder.num_of_likes.setText(memory.getLikes().size());
       }
        else
        {
            holder.num_of_likes.setVisibility(View.INVISIBLE);
        }
       if(memory.getComments()!=null) {
           holder.num_of_comments.setText(memory.getComments().size());
       }
       else
       {
           holder.num_of_comments.setVisibility(View.INVISIBLE);
       }
        if(memory.getTags()!=null)
        {
            String s = "#";
            for(Tag tag : memory.getTags() )
            {
                s +=tag.getLabel();
            }
            holder.tags.setText(s);
        }
        else
            holder.tags.setVisibility(View.INVISIBLE);


        ///////////////////////////////

        //ArrayList<ImgItem> images=Memory.getPictures();
        ArrayList<ImgItem> images=new ArrayList<>();


        //////////////////////////////////// fill images
        ImgItem i1=new ImgItem("alex",R.drawable.alex);
        ImgItem i2=new ImgItem("alex",R.drawable.pic);
        ImgItem i3=new ImgItem("alex",R.drawable.alex);

        images.add(i1);
        images.add(i2);





        ///////////////////////////////////
        ImageAdapter imgAdapter=new ImageAdapter(ctx,images);
        holder.rvMemory.setHasFixedSize(true);
        holder.rvMemory.setLayoutManager(new LinearLayoutManager(ctx, LinearLayoutManager.HORIZONTAL,false));
        holder.rvMemory.setAdapter(imgAdapter);



    }

    @Override
    public int getItemCount() {
        return mMemories.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        RecyclerView rvMemory;
        public TextView tags,feeling,location,name, mem_date, descr, num_of_likes, num_of_comments;
        public ImageView pic;
        public ImageButton commentbtn;
        public MemoryAdapter adapter;

        public ViewHolder(@NonNull View itemView, MemoryAdapter memoryAdapter) {
            super(itemView);

            commentbtn = itemView.findViewById(R.id.commentbtn2);
            feeling = itemView.findViewById(R.id.feelingtxt);
            location = itemView.findViewById(R.id.locationtxt);
            name = itemView.findViewById(R.id.name_txt_person);
            mem_date = itemView.findViewById(R.id.memory_date);
            descr = itemView.findViewById(R.id.descr);
            pic = itemView.findViewById(R.id.picture_person);
            num_of_comments = itemView.findViewById(R.id.commentNum);
            num_of_likes = itemView.findViewById(R.id.likesNum);
            tags = itemView.findViewById(R.id.tags_text);
            this.adapter = memoryAdapter;

        }
    }
}
