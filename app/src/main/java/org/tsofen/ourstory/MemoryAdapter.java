package org.tsofen.ourstory;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.tsofen.ourstory.model.api.MemoryA;

import java.util.ArrayList;
import java.util.Calendar;

import static java.util.Calendar.getInstance;

public class MemoryAdapter extends RecyclerView.Adapter<MemoryAdapter.ViewHolder> {
    Context ctx;
    public ArrayList<MemoryA> mMemories;

    public MemoryAdapter(Context ctx,ArrayList<MemoryA> memories) {
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

        // inflate the custom layout
        View contactView = inflater.inflate(R.layout.memory_item, parent, false);



        // return a new holder instance
        ctx=parent.getContext();
        ViewHolder viewHolder = new ViewHolder(contactView, this);


        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        MemoryA memory = mMemories.get(position);



        ///////////////////////////////


        //ArrayList<ImgItem> images=Memory.getPictures();
        ArrayList<ImgItem> images=new ArrayList<>();


        //////////////////////////////////// fill images

        // images in memories example
        ImgItem i1=new ImgItem("alex",R.drawable.alex);
        ImgItem i2=new ImgItem("alex",R.drawable.pic);
        ImgItem i3=new ImgItem("alex",R.drawable.alex);

        images.add(i1);
        images.add(i2);
        // end of example




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

    public void filterList(ArrayList<MemoryA> filteredList) {
        mMemories = filteredList;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        RecyclerView rvMemory;
        public TextView name, mem_date, create_date, descr, num_of_likes, num_of_comments, num_of_shares;
        public ImageView pic;
        public MemoryAdapter adapter;

        public ViewHolder(@NonNull View itemView, MemoryAdapter memoryAdapter) {
            super(itemView);
            name = itemView.findViewById(R.id.name_txt_person);
            mem_date = itemView.findViewById(R.id.memory_date);
//            create_date = itemView.findViewById(R.id.posted_date);
            descr = itemView.findViewById(R.id.descr);
            pic = itemView.findViewById(R.id.picture_person);
           /* num_of_comments = itemView.findViewById(R.id.commentNum);
            num_of_likes = itemView.findViewById(R.id.likesNum);
            num_of_shares = itemView.findViewById(R.id.shareNum);*/
            adapter = memoryAdapter;


            rvMemory=(RecyclerView)itemView.findViewById(R.id.memory_pic);
        }
    }
}
