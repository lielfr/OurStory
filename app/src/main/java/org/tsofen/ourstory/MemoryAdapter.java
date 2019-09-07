package org.tsofen.ourstory;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.tsofen.ourstory.model.Memory;

import java.util.ArrayList;
import java.util.Calendar;

public class MemoryAdapter extends RecyclerView.Adapter<MemoryAdapter.ViewHolder> {

    public ArrayList<Memory> mMemories;

    public MemoryAdapter(ArrayList<Memory> memories) {
        this.mMemories = memories;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext(); // getting the main activity
        LayoutInflater inflater = LayoutInflater.from(context); // put layout of main activity in layout inflater

        // inflate the custom layout
        View contactView = inflater.inflate(R.layout.memory_item, parent, false);


        // return a new holder instance
        ViewHolder viewHolder = new ViewHolder(contactView, this);


        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Memory memory = mMemories.get(position);
        holder.descr.setText(memory.getDescription());
        holder.name.setText(memory.getCreatorName());

        String createDate = memory.getCreateDate().get(Calendar.DAY_OF_MONTH) + "/" + (memory.getCreateDate().get(Calendar.MONTH)) +
                "/" + (memory.getCreateDate().get(Calendar.YEAR));
        String[] monthNames = {" ", "January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
        String memDate = monthNames[memory.getMemoryDate().get(Calendar.MONTH)] + " " + memory.getMemoryDate().get(Calendar.DAY_OF_MONTH) + " , " + (memory.getMemoryDate().get(Calendar.YEAR));
       /* holder.num_of_shares.setText(memory.getLikes().size());
        holder.num_of_shares.setText(memory.getShares().size());
        holder.num_of_comments.setText(memory.getComments().size());*/
//        holder.create_date.setText(createDate);
        holder.mem_date.setText(memDate);

    }

    @Override
    public int getItemCount() {
        return mMemories.size();
    }

    public void filterList(ArrayList<Memory> filteredList) {
        mMemories = filteredList;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
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
        }
    }
}
