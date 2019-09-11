package org.tsofen.ourstory;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.tsofen.ourstory.model.api.MemoryA;

import java.util.ArrayList;

public class SearchMemoryAdapter extends RecyclerView.Adapter<SearchMemoryAdapter.MemoryViewHolder> {
    private final ArrayList<MemoryA> mMemoryList;
    private LayoutInflater mInflater;

    public SearchMemoryAdapter( ArrayList<MemoryA> storyList) {

        this.mMemoryList = storyList;
    }

    @NonNull
    @Override
    public MemoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mItemView = mInflater.inflate(R.layout.memory_search_item, parent, false);
        return new MemoryViewHolder(mItemView, this);
    }

    @Override
    public void onBindViewHolder(@NonNull MemoryViewHolder holder, int position) {
        MemoryA mCurrent = mMemoryList.get(position);
        //holder.firstName.setText(mCurrent.getFirstName());
        //holder.lastName.setText(mCurrent.getLastName());
        //holder.profilePic.setImageResource(mCurrent.getImg());
    }

    @Override
    public int getItemCount() {
        return mMemoryList.size();
    }

    public class MemoryViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public final TextView firstName;
        public final TextView lastName;
        public final ImageView profilePic;
        final SearchMemoryAdapter mAdapter;


        public MemoryViewHolder(@NonNull View itemView, SearchMemoryAdapter adapter) {
            super(itemView);
            firstName = itemView.findViewById(R.id.profile_name_id);
            lastName = itemView.findViewById(R.id.last_name_id);
            profilePic = itemView.findViewById(R.id.profile_pic);
            this.mAdapter = adapter;
            itemView.setOnClickListener(this);
        }
        @Override
        public void onClick(View view) {
            int mPosition = getLayoutPosition();
            MemoryA element = mMemoryList.get(mPosition);
            mMemoryList.set(mPosition, element);
            mAdapter.notifyDataSetChanged();
        }

    }
}
