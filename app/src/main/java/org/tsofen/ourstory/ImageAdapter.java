package org.tsofen.ourstory;

import android.content.Context;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ViewHolder> {
    private ArrayList<ImgItem> images = new ArrayList<>();
    Context context;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
     /*   Context context=parent.getContext();
        LayoutInflater inflater= LayoutInflater.from(context);

        View imgView = inflater.inflate(R.layout.memory_img_item,parent,false);*/
        View imgView = LayoutInflater.from(parent.getContext()).inflate(R.layout.memory_img_item,parent,false);

        ViewHolder viewHolder = new ViewHolder(imgView);
        return viewHolder;
    }

    public ImageAdapter(Context context, ArrayList<ImgItem> images) {
        this.context = context;
        this.images = images;
    }

    @Override
    public int getItemCount() {
        return images.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView imgView;


        public ViewHolder(View itemView) {
            super(itemView);
            imgView = itemView.findViewById(R.id.memory_img);


        }
    }

    @Override
    public void onBindViewHolder(@NonNull ImageAdapter.ViewHolder holder, int position) {
        //holder.imgView.setImageResource(images.get(position).getImg());
        ImgItem mCurrent = images.get(position);   //current image
        String st = mCurrent.getImg();
        Uri uri = Uri.parse(st);
        RequestOptions options = new RequestOptions()
                .override(650, 600)
                .centerCrop()
                .placeholder(R.drawable.nopicyet)
                .error(R.drawable.nopicyet);
        Glide.with(context).load(uri).apply(options).into(holder.imgView);
    }

}
