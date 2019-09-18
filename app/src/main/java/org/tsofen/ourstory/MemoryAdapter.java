package org.tsofen.ourstory;

import android.app.Activity;
import android.content.Context;
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

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import org.tsofen.ourstory.model.Memory;
import org.tsofen.ourstory.model.Picture;
import org.tsofen.ourstory.model.Tag;
import org.tsofen.ourstory.model.api.Contributer;
import org.tsofen.ourstory.model.api.MemoryA;
import org.tsofen.ourstory.model.api.Story;
import org.tsofen.ourstory.model.api.User;

import java.util.ArrayList;
import java.util.Calendar;

public class MemoryAdapter extends RecyclerView.Adapter<MemoryAdapter.ViewHolder> {

    public static final String EXTRA_MESSAGE = "org.tsofen.ourstory.extra.MESSAGE";
    public final ArrayList<Memory> mMemories;
    Memory memoryA;
    Context ctx;
    LayoutInflater mInflater;
    User user;

    public MemoryAdapter(Context context,ArrayList<Memory> memories)
    {
        Log.d( "fragment","adapter created");
        this.mMemories = memories;
        Log.d( "fragment","memories in adapter: "+memories.size());
        this.mInflater = LayoutInflater.from(context);
        //this.user = userObj;
        //    example
       /* Calendar d3 = getInstance();
        Calendar d4 = getInstance();

        d3.set(2004, 11, 1);
        d4.set(2000, 10, 1);
        MemoryA testMemory=(new MemoryA("orwah",null,"Wish you were here.", d3.getTime(), d4.getTime()));

        mMemories=testMemory.createContactsList();*/
        // end example
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext(); // getting the main activity
        LayoutInflater inflater = LayoutInflater.from(context); // put layout of main activity in layout inflater
        View contactView = inflater.inflate(R.layout.memory_item, parent, false);
        ctx=parent.getContext();
        ViewHolder viewHolder = new ViewHolder(contactView, this);
        Log.d( "fragment","holder created");
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
        Log.d("fragment", "binded : "+position + " ");
        Memory memory = mMemories.get(position);
        holder.commentbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(ctx.getApplicationContext(), CommentActivity.class);
                intent.putExtra("memory", memory);
                intent.putExtra("user", user);
                ctx.startActivity(intent);

            }
        });


//        holder.sharebtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent sendIntent = new Intent();
//                sendIntent.setAction(Intent.ACTION_SEND);
//                sendIntent.putExtra(Intent.EXTRA_TEXT, "This is my text to send.");
//                sendIntent.setType("text/plain");
//
//                Intent shareIntent = Intent.createChooser(sendIntent,null);
//
//                ctx.startActivity(shareIntent);
//            }
//        });

        if (memory.getDescription() != null) {
            holder.descr.setText(memory.getDescription());
        } else
            holder.descr.setVisibility(View.INVISIBLE);
        if (memory.getTags() != null) {
            String s = "";
            for (Tag tag : memory.getTags()) {
                s += "#" + tag.getLabel();
            }
            holder.tags.setText(s);
        } else
            holder.tags.setVisibility(View.INVISIBLE);
        Story story = memory.getStory();
        holder.name.setText(story.getNameOfPerson());
        String[] monthNames = {" ", "January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
        if (memory.getMemoryDate() != null) {
            String memDate = monthNames[memory.getMemoryDate().get(Calendar.MONTH) + 1] + " " + memory.getMemoryDate().get(Calendar.DAY_OF_MONTH) + " , " + (memory.getMemoryDate().get(Calendar.YEAR));
            holder.mem_date.setText(memDate);
            holder.mem_date.setWidth(calculateWidth(memDate));
        } else
            holder.mem_date.setVisibility(View.INVISIBLE);

        if (memory.getStory().getPicture() != null) {

            Uri uri = Uri.parse(memory.getStory().getPicture().toString());
            RequestOptions options = new RequestOptions()
                    .override(300, 300)
                    .centerCrop()
                    .placeholder(R.drawable.nopicyet)
                    .error(R.drawable.nopicyet);
            Glide.with(this.mInflater.getContext()).load(uri).apply(options).into(holder.profile);
        } else {
            holder.profile.setImageResource(R.drawable.defaultprofilepicture);
        }
        if (memory.getLikes().isEmpty())
            holder.num_of_likes.setVisibility(View.INVISIBLE);
        else
            holder.num_of_likes.setText(memory.getLikes().size() + "");
        if (memory.getComments().isEmpty())
            holder.num_of_comments.setVisibility(View.INVISIBLE);
        else
            holder.num_of_comments.setText(memory.getComments().size() + "");
        if (holder.location !=null && memory.getLocation() != null) {
            holder.location.setText(memory.getLocation());
            holder.location.setWidth(calculateWidth(memory.getLocation()));
        } else
            if( holder.location!=null && memory.getLocation()==null)
            holder.location.setVisibility(View.INVISIBLE);

        if (holder.feeling != null && memory.getFeeling() != null) {
            holder.feeling.setText("#" + memory.getFeeling());
            holder.feeling.setWidth(calculateWidth("#" + memory.getFeeling()));
        } else
            if(holder.feeling!=null && memory.getFeeling()==null)
            holder.feeling.setVisibility(View.INVISIBLE);       /* ArrayList<ImgItem> images=new ArrayList<>();*/



            if (memory.getPictures() != null) {
                ArrayList<ImgItem> images = new ArrayList<>();
                ArrayList<Picture> pictures = new ArrayList<>();
                pictures = memory.getPictures();
                for (int i = 0; i < pictures.size(); i++) {
                    images.add(new ImgItem(" ", pictures.get(i).getLink().toString()));
                }
                ImageAdapter imgAdapter = new ImageAdapter(ctx, images);
                holder.rvMemory.setHasFixedSize(true);
                holder.rvMemory.setLayoutManager(new LinearLayoutManager(ctx, LinearLayoutManager.HORIZONTAL, false));
                holder.rvMemory.setAdapter(imgAdapter);
            } else
            {
                holder.rvMemory.setVisibility(View.INVISIBLE);
             }

        ///////////////////////////////

        //ArrayList<ImgItem> images=Memory.getPictures();
       // ArrayList<ImgItem> images=new ArrayList<>();


        //////////////////////////////////// fill images

        // images in memories example
        /*ImgItem i1=new ImgItem("alex",R.drawable.alex);
        ImgItem i2=new ImgItem("alex",R.drawable.pic);
        ImgItem i3=new ImgItem("alex",R.drawable.alex);

        images.add(i1);
        images.add(i2);*/
        // end of example




        ///////////////////////////////////
       /* ImageAdapter imgAdapter=new ImageAdapter(ctx,images);
        holder.rvMemory.setHasFixedSize(true);
        holder.rvMemory.setLayoutManager(new LinearLayoutManager(ctx, LinearLayoutManager.HORIZONTAL,false));
        holder.rvMemory.setAdapter(imgAdapter);*/



    }

    @Override
    public int getItemCount() {
        return mMemories.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        RecyclerView rvMemory;
        public TextView tags, feeling, location, name, mem_date, descr, num_of_likes, num_of_comments;
        public ImageView profile;
        public ImageButton commentbtn,sharebtn;
        public MemoryAdapter adapter;

        public ViewHolder(@NonNull View itemView, MemoryAdapter memoryAdapter) {
            super(itemView);
            rvMemory = itemView.findViewById(R.id.memory_pic);
            //deletebtn =itemView.findViewById(R.id.deletebtn);
            tags = itemView.findViewById(R.id.tags_text);
            sharebtn = itemView.findViewById(R.id.sharebtn);
            commentbtn = itemView.findViewById(R.id.commentbtn2);
           // editbtn = itemView.findViewById(R.id.editbtn);
            name = itemView.findViewById(R.id.name_txt_person);
            mem_date = itemView.findViewById(R.id.memory_date);
            num_of_comments = itemView.findViewById(R.id.commentNum);
            num_of_likes = itemView.findViewById(R.id.likesNum);
            descr = itemView.findViewById(R.id.descr);
            location = itemView.findViewById(R.id.locationtxt_mymemories);
            feeling = itemView.findViewById(R.id.feelingtxt_mymemories);
            profile = itemView.findViewById(R.id.picture_person);
            this.adapter = memoryAdapter;

        }
    }
}
