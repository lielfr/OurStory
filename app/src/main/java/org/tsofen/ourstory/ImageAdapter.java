package org.tsofen.ourstory;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ViewHolder> {
    private ArrayList<ImgItem> images;
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

    @Override
    public void onBindViewHolder(@NonNull ImageAdapter.ViewHolder holder, int position) {
        holder.imgView.setImageResource(images.get(position).getImg());
    }

    @Override
    public int getItemCount() {
        return images.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public ImageView imgView;


        public ViewHolder(View itemView){
            super(itemView);
            imgView= itemView.findViewById(R.id.memory_img);


        }
    }
    public ImageAdapter(Context context,ArrayList<ImgItem> images){
        this.context=context;
        this.images=images;
    }

}
