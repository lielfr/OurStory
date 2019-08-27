package com.example.addmemory;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.esafirm.imagepicker.features.ImagePicker;

import java.util.LinkedList;
import java.util.List;

public class AddMemoryImageAdapter extends RecyclerView.Adapter<AddMemoryImageAdapter.ViewHolder> {
    Context ctx;
    Activity parent;
    List<String> images;

    public AddMemoryImageAdapter(Activity parent) {
        super();
        images = new LinkedList<>();
        this.parent = parent;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ctx = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(ctx);
        View view = inflater.inflate(R.layout.addmemory_rv_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ImageView imageView = holder.itemView.findViewById(R.id.imageViewAMRV);
        if (position == 0) {
            Glide.with(holder.itemView)
                    .load(R.drawable.ic_plus_cube)
                    .into(imageView);
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ImagePicker.create(parent)
                            .includeVideo(false)
                            .start();
                }
            });
        } else {
            String uri = images.get(position - 1);
            Glide.with(holder.itemView)
                    .load(uri)
                    .into(imageView);
        }
    }

    @Override
    public int getItemCount() {
        return images.size() + 1;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
