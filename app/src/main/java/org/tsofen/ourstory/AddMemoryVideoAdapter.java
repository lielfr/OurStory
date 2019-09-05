package org.tsofen.ourstory;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.LinkedList;
import java.util.List;

public class AddMemoryVideoAdapter extends RecyclerView.Adapter<AddMemoryVideoAdapter.ViewHolder> {
    Context ctx;
    Activity parent;
    List<String> videos;

    static final int ADDMEMORY_VIDEO = 959;

    public AddMemoryVideoAdapter(Activity parent) {
        super();
        videos = new LinkedList<>();
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
                    Intent i = new Intent(Intent.ACTION_GET_CONTENT);
                    i.setType("video/*");
                    i.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
                    parent.startActivityForResult(Intent.createChooser(i, "Choose Video"),
                            ADDMEMORY_VIDEO);
                }
            });
        } else {
            String uri = videos.get(position - 1);
            Glide.with(holder.itemView)
                    .load(uri)
                    .into(imageView);
        }
    }

    @Override
    public int getItemCount() {
        return videos.size() + 1;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
