package org.tsofen.ourstory;

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
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import org.tsofen.ourstory.StoryTeam.ViewStory;
import org.tsofen.ourstory.model.Memory;
import org.tsofen.ourstory.model.Picture;
import org.tsofen.ourstory.model.Tag;
import org.tsofen.ourstory.model.api.Story;
import org.tsofen.ourstory.model.api.User;

import java.util.ArrayList;
import java.util.Calendar;

public class SearchMemoryAdapter extends RecyclerView.Adapter<SearchMemoryAdapter.ViewHolder> {
    public static final String EXTRA_MESSAGE = "org.tsofen.ourstory.extra.MESSAGE";
    public final ArrayList<Memory> mMemories;
    Context ctx;
    LayoutInflater mInflater;
    ImageView button;

    public SearchMemoryAdapter(Context context, ArrayList<Memory> memories) {
        this.mMemories = memories;
        mInflater = LayoutInflater.from(context);

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext(); // getting the main activity
        LayoutInflater inflater = LayoutInflater.from(context); // put layout of main activity in layout inflater
        View contactView = inflater.inflate(R.layout.memory_search_item, parent, false);

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
        if(memory != null) {
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
            } else {
                holder.tags.setVisibility(View.INVISIBLE);
            }
            if (memory.getUser() != null) {
                if(memory.getUser().getFullName()!=null) {
                    Story story = memory.getStory();
                    holder.name.setText(memory.getUser().getFullName());
                    holder.name.setWidth(calculateWidth(memory.getUser().getFullName()));
                }


                if (memory.getUser().getProfilePicture() != null) {

                    Uri uri = Uri.parse(memory.getUser().getProfilePicture());
                    RequestOptions options = new RequestOptions()
                            .override(300, 300)
                            .centerCrop()
                            .placeholder(R.drawable.nopicyet)
                            .error(R.drawable.nopicyet);
                    Glide.with(this.ctx).load(uri).apply(options).into(holder.profile);
                } else {
                    holder.profile.setImageResource(R.drawable.defaultprofilepicture);
                }
            } else {
//                Toast toast = Toast.makeText(ctx, "there is no memories", Toast.LENGTH_SHORT);

            }
        }
    }


    @Override
    public int getItemCount() {
        return mMemories.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        //RecyclerView rvMemory;
        public TextView tags, feeling, location, name, mem_date, descr;
        public ImageView profile;
        public SearchMemoryAdapter adapter;

        public ViewHolder(@NonNull View itemView, SearchMemoryAdapter memoryAdapter) {
            super(itemView);
            tags = itemView.findViewById(R.id.tags_text);
            name = itemView.findViewById(R.id.name_txt_person);
            //mem_date = itemView.findViewById(R.id.memory_date);
            descr = itemView.findViewById(R.id.descr);
            profile = itemView.findViewById(R.id.picture_person);
            this.adapter = memoryAdapter;
            itemView.setOnClickListener(this);


        }

        @Override
        public void onClick(View view) {
            int mPosition = getLayoutPosition();
            Memory element = mMemories.get(mPosition);
            mMemories.set(mPosition, element);
            adapter.notifyDataSetChanged();
            Intent showMemory = new Intent(view.getContext(), ViewMemory.class);
            if (showMemory != null) {
                showMemory.putExtra("memoryId", element.getId());
                //Toast.makeText(context, "Condratolation  remember Story Adapter ", Toast.LENGTH_SHORT).show();
                ctx.startActivity(showMemory);                                                 //TODO NEED to Activate this Intent
            } else {
//                Toast.makeText(ctx, "Warning intent is null ", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
