package org.tsofen.ourstory.StoryTeam;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.tsofen.ourstory.R;
import org.tsofen.ourstory.model.api.ListOfStory;

import java.util.List;

public class StoryAdapter extends RecyclerView.Adapter<StoryAdapter.StoryViewHolder> {
    private final List<ListOfStory> mStoryList;
    private LayoutInflater mInflater;

    public StoryAdapter(Context context, List<ListOfStory> storyList) {
        mInflater = LayoutInflater.from(context);
        Log.i("story ",storyList.get(0).getNameOfPerson());
        this.mStoryList = storyList;
    }

    @NonNull
    @Override
    public StoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mItemView = mInflater.inflate(R.layout.story_item, parent, false);
        return new StoryViewHolder(mItemView, this);
    }

    @Override
    public void onBindViewHolder(@NonNull StoryViewHolder holder, int position) {
        ListOfStory mCurrent = mStoryList.get(position);
        holder.firstName.setText(mCurrent.getNameOfPerson());
   //     holder.profilePic.setImageResource((int)mCurrent.getPicture());
    }

    @Override
    public int getItemCount() {
        return mStoryList.size();
    }

    public class StoryViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public final TextView firstName;
        public final TextView lastName;
        public final ImageView profilePic;
        final StoryAdapter mAdapter;


        public StoryViewHolder(@NonNull View itemView, StoryAdapter adapter) {
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
            ListOfStory element = mStoryList.get(mPosition);
            mStoryList.set(mPosition, element);
            mAdapter.notifyDataSetChanged();
        }
    }
}

