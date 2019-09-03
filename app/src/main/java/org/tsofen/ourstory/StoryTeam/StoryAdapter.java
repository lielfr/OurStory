package org.tsofen.ourstory.StoryTeam;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.tsofen.ourstory.R;
import org.tsofen.ourstory.model.Memory;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;

public class StoryAdapter extends RecyclerView.Adapter<StoryAdapter.StoryViewHolder> {
    private final LinkedList<Story> mStoryList;
    private LayoutInflater mInflater;

    public StoryAdapter(Context context, LinkedList<Story> storyList) {
        mInflater = LayoutInflater.from(context);
        storyList.add(new Story(new ArrayList<Memory>(), "Malik", "Mr3e", new Date(), new Date(), R.drawable.ic_launcher_foreground, 1, new Date(), "stam", 2));
        storyList.add(new Story(new ArrayList<Memory>(), "Somebody", "Mr3e", new Date(), new Date(), R.drawable.ic_launcher_foreground, 1, new Date(), "stam", 2));
        storyList.add(new Story(new ArrayList<Memory>(), "Memo", "Mr3e", new Date(), new Date(), R.drawable.ic_launcher_foreground, 1, new Date(), "stam", 2));
        storyList.add(new Story(new ArrayList<Memory>(), "Lolo", "Mr3e", new Date(), new Date(), R.drawable.ic_launcher_foreground, 1, new Date(), "stam", 2));
        storyList.add(new Story(new ArrayList<Memory>(), "Soso", "Mr3e", new Date(), new Date(), R.drawable.ic_launcher_foreground, 1, new Date(), "stam", 2));
        storyList.add(new Story(new ArrayList<Memory>(), "Flamengo", "Mr3e", new Date(), new Date(), R.drawable.ic_launcher_foreground, 1, new Date(), "stam", 2));
        storyList.add(new Story(new ArrayList<Memory>(), "Sandoo", "fadvadf", new Date(), new Date(), R.drawable.ic_launcher_foreground, 1, new Date(), "stam", 2));
        storyList.add(new Story(new ArrayList<Memory>(), "Laston", "Mgsdh", new Date(), new Date(), R.drawable.ic_launcher_foreground, 1, new Date(), "stam", 2));
        storyList.add(new Story(new ArrayList<Memory>(), "Farnsis", "Mksd", new Date(), new Date(), R.drawable.ic_launcher_foreground, 1, new Date(), "stam", 2));
        storyList.add(new Story(new ArrayList<Memory>(), "Tnoyt", "Mrb", new Date(), new Date(), R.drawable.ic_launcher_foreground, 1, new Date(), "stam", 2));
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
        Story mCurrent = mStoryList.get(position);
        holder.firstName.setText(mCurrent.getFirstName());
        holder.lastName.setText(mCurrent.getLastName());
        holder.profilePic.setImageResource(mCurrent.getImg());
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
            Story element = mStoryList.get(mPosition);
            mStoryList.set(mPosition, element);
            mAdapter.notifyDataSetChanged();
        }
    }
}

