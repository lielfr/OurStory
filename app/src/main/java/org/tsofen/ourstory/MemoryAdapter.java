package org.tsofen.ourstory;

import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.net.Uri;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import org.tsofen.ourstory.model.Memory;
import org.tsofen.ourstory.model.Tag;
import org.tsofen.ourstory.model.api.Contributer;
import org.tsofen.ourstory.model.api.MemoryA;
import org.tsofen.ourstory.model.api.User;

import java.util.ArrayList;
import java.util.Calendar;

public class MemoryAdapter extends RecyclerView.Adapter<MemoryAdapter.ViewHolder> {

    public static final String EXTRA_MESSAGE = "org.tsofen.ourstory.extra.MESSAGE";
    public final ArrayList<Memory> mMemories;
    Memory memoryA;
    Context ctx;
    LayoutInflater mInflater;
    Memory mem;

    public MemoryAdapter(Context context,ArrayList<Memory> memories)
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
        User user = memory.getUser();
        if (user != null && memory.getUser().getProfilePicture() != null) {
            Uri uri = Uri.parse(memory.getUser().getProfilePicture());
            RequestOptions options = new RequestOptions()
                    .override(300, 300)
                    .centerCrop()
                    .placeholder(R.drawable.nopicyet)
                    .error(R.drawable.nopicyet);
            Glide.with(this.mInflater.getContext()).load(uri).apply(options).into(holder.pic);
            holder.name.setText(memory.getUser().getFullName());
        } else {
            holder.pic.setImageResource(R.drawable.defaultprofilepicture);
        }
        holder.commentbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(ctx.getApplicationContext(), CommentActivity.class);
                intent.putExtra("memory", memory);
                ctx.startActivity(intent);

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
     }
     if(memory.getLocation()!=null)
     {
         holder.location.setWidth(calculateWidth(memory.getLocation()));
         holder.location.setText(memory.getLocation());
     }
     if(memory.getFeeling()!=null)
     {
         holder.feeling.setWidth(calculateWidth("#"+memory.getFeeling()));
         holder.feeling.setText("#"+memory.getFeeling());
     }
        String[] monthNames = {" ", "January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
        if (memory.getMemoryDate() != null) {
            String memDate = monthNames[memory.getMemoryDate().get(Calendar.MONTH)+1] + " " + memory.getMemoryDate().get(Calendar.DAY_OF_MONTH) + " , " + (memory.getMemoryDate().get(Calendar.YEAR));
           holder.mem_date.setWidth(calculateWidth(memDate));
            holder.mem_date.setText(memDate);
        } else
            holder.mem_date.setVisibility(View.INVISIBLE);
        if (memory.getLikes() != null) {
           holder.num_of_likes.setText(memory.getLikes().size()+"");
        } else {
            holder.num_of_likes.setVisibility(View.INVISIBLE);
        }
       if(memory.getComments()!=null) {
           holder.num_of_comments.setText(memory.getComments().size()+"");
       } else {
           holder.num_of_comments.setVisibility(View.INVISIBLE);
       }
        if (memory.getTags() != null) {
            String s = "";
            for (Tag tag : memory.getTags()) {
                s += "#" +tag.getLabel();
            }
            holder.tags.setText(s);
        } else
            holder.tags.setVisibility(View.INVISIBLE);

       /* ArrayList<ImgItem> images=new ArrayList<>();
        if(memory.getPictures()!=null) {
            images.add((ImgItem) memory.getPictures());
            ImageAdapter imgAdapter = new ImageAdapter(ctx, images);
            holder.rvMemory.setHasFixedSize(true);
            holder.rvMemory.setLayoutManager(new LinearLayoutManager(ctx, LinearLayoutManager.HORIZONTAL, false));
            holder.rvMemory.setAdapter(imgAdapter);
        }
        else
        {

            holder.rvMemory.setVisibility(View.INVISIBLE);
        }*/

    }

    @Override
    public int getItemCount() {
        return mMemories.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        RecyclerView rvMemory;
        public TextView tags, feeling, location, name, mem_date, descr, num_of_likes, num_of_comments;
        public ImageView pic;
        public ImageButton commentbtn,sharebtn;
        public MemoryAdapter adapter;

        public ViewHolder(@NonNull View itemView, MemoryAdapter memoryAdapter) {
            super(itemView);
            rvMemory = itemView.findViewById(R.id.memory_pic);
            commentbtn = itemView.findViewById(R.id.commentbtn2);
            sharebtn=itemView.findViewById(R.id.sharebtn2);
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
