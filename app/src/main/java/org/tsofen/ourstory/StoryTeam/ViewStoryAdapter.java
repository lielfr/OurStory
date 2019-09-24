package org.tsofen.ourstory.StoryTeam;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import org.tsofen.ourstory.MemoriesOfStoryActivity;
import org.tsofen.ourstory.R;
import org.tsofen.ourstory.ViewMemory;
import org.tsofen.ourstory.model.api.ListOfStory;
import org.tsofen.ourstory.model.api.Memory;
import org.tsofen.ourstory.model.api.Story;
import org.tsofen.ourstory.model.api.User;
import org.tsofen.ourstory.model.api.VSMemories;

import java.util.LinkedList;
import java.util.List;

import static androidx.core.content.ContextCompat.startActivity;

public class ViewStoryAdapter extends RecyclerView.Adapter<ViewStoryAdapter.StoryViewHolder> {
    public final List<VSMemories> mStoryList;
    private LayoutInflater mInflater;
    public Context context;
    public long storyId;
    public String storyName;
    VSMemories mCurrent;
    User user;


    public ViewStoryAdapter(ViewStory context, List<VSMemories> StoryList, String storyName, long storyId, User user) {
        mInflater = LayoutInflater.from(context);
        this.mStoryList = StoryList;
        this.context = context.getApplicationContext();
        this.storyName = storyName;
        this.storyId = storyId;
        this.user = user;
    }

    @NonNull
    @Override
    public ViewStoryAdapter.StoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mItemView = mInflater.inflate(R.layout.item_memory_view, parent  , false);
        return new StoryViewHolder(mItemView, this);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewStoryAdapter.StoryViewHolder holder, int position) {

        mCurrent = mStoryList.get(position);
        Log.d("fadi", mCurrent.getPics().get(0).get(1));
        //   Bitmap bitmap = BitmapFactory.decodeStream(mCurrent.getPics().get(0).get(1).toString());
        holder.year.setText(mCurrent.getYear()+"");
        String st = mCurrent.getPics().get(0).get(1);
        Uri uri = Uri.parse(st);
        RequestOptions options = new RequestOptions()
                .override(300, 300)
                .centerCrop()
                .placeholder(R.drawable.nopicyet)
                .error(R.drawable.nopicyet);

        Glide.with(this.mInflater.getContext()).load(uri).apply(options).into(holder.img1);         st = mCurrent.getPics().get(1).get(1);
        uri = Uri.parse(st);
        options = new RequestOptions()
                .override(300, 300)
                .centerCrop()
                .placeholder(R.drawable.nopicyet)
                .error(R.drawable.nopicyet);

        Glide.with(this.mInflater.getContext()).load(uri).apply(options).into(holder.img2);         st = mCurrent.getPics().get(2).get(1);
        uri = Uri.parse(st);
        options = new RequestOptions()
                .override(300, 300)
                .centerCrop()
                .placeholder(R.drawable.nopicyet)
                .error(R.drawable.nopicyet);

        Glide.with(this.mInflater.getContext()).load(uri).apply(options).into(holder.img3);
//        holder.img1.setImageURI((Uri.parse(mCurrent.getPics().get(0).get(1))));
//        holder.img2.setImageURI((Uri.parse(mCurrent.getPics().get(1).get(1))));
//        holder.img3.setImageURI((Uri.parse(mCurrent.getPics().get(2).get(1))));

    }

    class StoryViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener {
        public final TextView year;
        public final ImageView img1;
        public final ImageView img2;
        public final ImageView img3;
        int mposition = 0;

        final ViewStoryAdapter mAdapter;

        public StoryViewHolder(View itemView, ViewStoryAdapter adapter) {
            super(itemView);
            year = itemView.findViewById(R.id.textView13);
            img1 = itemView.findViewById(R.id.item_memory_img_1);
            img2 = itemView.findViewById(R.id.item_memory_img_2);
            img3 = itemView.findViewById(R.id.item_memory_img_3);
            this.mAdapter = adapter;
            img1.setOnClickListener(this);
            img2.setOnClickListener(this);
            img3.setOnClickListener(this);
            year.setOnClickListener(this);
        }


        @Override
        public void onClick(View view) {
            Log.d("hh", "hh");
            int YearId = R.id.textView13;
            int img1Id = R.id.item_memory_img_1;
            int img2Id = R.id.item_memory_img_2;
            int img3Id = R.id.item_memory_img_3;
            mposition = getLayoutPosition();
            Toast.makeText(view.getContext(), Integer.toString(mposition), Toast.LENGTH_SHORT).show();
            Toast.makeText(view.getContext(), storyId + "", Toast.LENGTH_LONG).show();
            Toast.makeText(view.getContext(), view.getId() + "", Toast.LENGTH_LONG).show();
            if (view.getId() == YearId) {
                Toast.makeText(view.getContext(), "yearclicked", Toast.LENGTH_SHORT).show();
                //mCurrent = mStoryList.get(mposition);
                Intent intent = new Intent(view.getContext(), MemoriesOfStoryActivity.class);
                intent.putExtra("flag", 0);
                intent.putExtra("storyId", storyId);
                intent.putExtra("storyName", storyName);
                intent.putExtra("year", mCurrent.getYear());
                intent.putExtra("user", user);
                Toast.makeText(view.getContext(), mCurrent.getYear().toString(), Toast.LENGTH_SHORT).show();
                view.getContext().startActivity(intent);

            } else if (view.getId() == img1Id) {
                Toast.makeText(view.getContext(), "img1clicked", Toast.LENGTH_SHORT).show();
                mCurrent = mStoryList.get(mposition);
                Intent intent = new Intent(view.getContext(), MemoriesOfStoryActivity.class);
                intent.putExtra("flag", 0);
                intent.putExtra("storyId", storyId);
                intent.putExtra("storyName", storyName);
                intent.putExtra("year", mCurrent.getYear());
                intent.putExtra("user", user);
                Toast.makeText(view.getContext(), mCurrent.getPics().get(mposition).get(2) + "hhhh", Toast.LENGTH_LONG).show();
                view.getContext().startActivity(intent);

            } else if (view.getId() == img2Id) {
                Toast.makeText(view.getContext(), "img2clicked", Toast.LENGTH_SHORT).show();
                mCurrent = mStoryList.get(mposition);
                Intent intent = new Intent(view.getContext(), MemoriesOfStoryActivity.class);
                intent.putExtra("flag", 0);
                intent.putExtra("storyId", storyId);
                intent.putExtra("storyName", storyName);
                intent.putExtra("year", mCurrent.getYear());
                intent.putExtra("user", user);
                Toast.makeText(view.getContext(), Long.toString( storyId ) /*mCurrent.getPics().get(mposition).get(2)*/, Toast.LENGTH_SHORT).show();
                view.getContext().startActivity(intent);

            } else if (view.getId() == img3Id) {
                Toast.makeText(view.getContext(), "img3clicked", Toast.LENGTH_SHORT).show();
                mCurrent = mStoryList.get(mposition);
                Intent intent = new Intent(view.getContext(), MemoriesOfStoryActivity.class);
                intent.putExtra("flag", 0);
                intent.putExtra("storyId", storyId);
                intent.putExtra("storyName", storyName);
                intent.putExtra("year", mCurrent.getYear());
                intent.putExtra("user", user);
                view.getContext().startActivity(intent);

            }
        }
    }


    @Override
    public int getItemCount() {
        return mStoryList.size();
    }
}
