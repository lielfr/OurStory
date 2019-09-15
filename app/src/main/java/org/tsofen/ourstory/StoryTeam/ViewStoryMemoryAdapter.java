package org.tsofen.ourstory.StoryTeam;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.tsofen.ourstory.R;

import java.util.ArrayList;
import java.util.List;

public class ViewStoryMemoryAdapter extends RecyclerView.Adapter<ViewStoryMemoryAdapter.MemoryViewHolder> {
    List<MemoryItem> mItems;
    Context c;
    private LayoutInflater mInflater;

    public ViewStoryMemoryAdapter(Context context, List<MemoryItem> mItems) {
        this.mItems = mItems;
        this.mInflater = LayoutInflater.from(context);
        this.c = context;
    }

    @NonNull
    @Override
    public ViewStoryMemoryAdapter.MemoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = mInflater.inflate(R.layout.memory_item_story, parent, false);
        MemoryViewHolder viewHolder = new MemoryViewHolder(v, this);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewStoryMemoryAdapter.MemoryViewHolder holder, int position) {
        MemoryItem itemInMenu = mItems.get(position);
        holder.tv_memory_year.setText(itemInMenu.getmName());
        ArrayList<Integer> arr = itemInMenu.getmImage();
        for (Integer i : arr) {
            holder.layoutParams.setMargins(20, 20, 20, 20);
            holder.layoutParams.gravity = Gravity.CENTER;
            ImageView imageView = new ImageView(c);
            imageView.setImageResource(i);
            //imageView.setOnClickListener(documentImageListener);
            imageView.setLayoutParams(holder.layoutParams);
            holder.ll_memory_images.addView(imageView);
        }
        //holder.IV_category_image.setImageResource(itemInMenu.getmImage());
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    public class MemoryViewHolder extends RecyclerView.ViewHolder implements
            View.OnClickListener {

        public TextView tv_memory_year;
        public LinearLayout ll_memory_images;
        ViewStoryMemoryAdapter mAdapter;
        LinearLayout.LayoutParams layoutParams;

        public MemoryViewHolder(@NonNull View itView, ViewStoryMemoryAdapter mAdapter) {
            super(itView);
            tv_memory_year = itView.findViewById(R.id.memory_date);
            this.mAdapter = mAdapter;
            ll_memory_images = itView.findViewById(R.id.ll_memory_images);
            layoutParams = new LinearLayout.LayoutParams(400, 400);
            itView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
        }
    }
}
