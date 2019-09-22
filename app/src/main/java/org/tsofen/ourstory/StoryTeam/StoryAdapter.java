package org.tsofen.ourstory.StoryTeam;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import org.tsofen.ourstory.R;
import org.tsofen.ourstory.model.api.ListOfStory;

import java.util.List;

public class StoryAdapter extends RecyclerView.Adapter<StoryAdapter.StoryViewHolder> {
    private final List<ListOfStory> mStoryList;
    private LayoutInflater mInflater;
    public Context context ;
    Uri uri;
    StoryFragment fragment;

    public StoryAdapter(Context context, List<ListOfStory> storyList, StoryFragment fragment) {
        mInflater = LayoutInflater.from(context);
        //Log.i("story ",storyList.get(0).getNameOfPerson());
        this.mStoryList = storyList;
        this.context = context ;
        this.fragment = fragment;
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
        holder.dates.setText("From " + mCurrent.getDateOfBirth().substring(0, 10) + " To " + mCurrent.getDateOfDeath().substring(0, 10));
        Object p = mCurrent.getPicture();
        if (p == null) return;
        String SP = p.toString();
        uri = Uri.parse(SP);
        RequestOptions options = new RequestOptions()
                .override(300, 300)
                .centerCrop()
                .placeholder(R.drawable.nopicyet)
                .error(R.drawable.nopicyet);
        Glide.with(this.mInflater.getContext()).load(uri).apply(options).into(holder.profilePic);

   //     holder.profilePic.setImageResource((int)mCurrent.getPicture()); //just need to set the pic from the firebase!!
    }

    @Override
    public int getItemCount() {
        return mStoryList.size();
    }

    public class StoryViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public final TextView firstName;
        public final TextView dates;
        public final ImageView profilePic;
        final StoryAdapter mAdapter;


        public StoryViewHolder(@NonNull View itemView, StoryAdapter adapter) {
            super(itemView);
            firstName = itemView.findViewById(R.id.profile_name_id);
            dates = itemView.findViewById(R.id.dates);
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
            Intent showStory = new Intent(view.getContext(), ViewStory.class);
            if (showStory!=null) {
                showStory.putExtra("id", element.getStoryId().toString());
                //Toast.makeText(context, "Condratolation  remember Story Adapter ", Toast.LENGTH_SHORT).show();
                showStory.putExtra("user", fragment.parent.user);
                context.startActivity(showStory);                                                 //TODO NEED to Activate this Intent
            }else{
                Toast.makeText(context, "Warning intent is null ", Toast.LENGTH_SHORT).show();
            }
        }
    }
}

