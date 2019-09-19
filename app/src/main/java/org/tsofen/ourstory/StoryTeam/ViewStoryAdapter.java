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
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;

import org.tsofen.ourstory.MemoriesOfStoryActivity;
import org.tsofen.ourstory.R;
import org.tsofen.ourstory.model.api.ListOfStory;
import org.tsofen.ourstory.model.api.Memory;
import org.tsofen.ourstory.model.api.Story;
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




    public ViewStoryAdapter(Context context, List<VSMemories> StoryList,String storyName,long storyId) {
        mInflater = LayoutInflater.from(context);
        this.mStoryList = StoryList;
        this.context = context ;
        this.storyName=storyName;
        this.storyId=storyId;
    }


    @Override
    public void onBindViewHolder(@NonNull ViewStoryAdapter.StoryViewHolder holder, int position) {
        VSMemories mCurrent = mStoryList.get(position);
        Log.d("fadi", mCurrent.getPics().get(0).get(1));
        holder.year.setText(mCurrent.getYear() + "");
        String st = mCurrent.getPics().get(0).get(1);
        Uri uri = Uri.parse(st);
        int radius = context.getResources().getDimensionPixelSize(R.dimen.corner_radius);

        RequestOptions options = new RequestOptions()
                .transform(new CenterCrop(), new RoundedCorners(radius))
                .placeholder(R.drawable.nopicyet)
                .error(R.drawable.nopicyet);

        Glide.with(this.mInflater.getContext()).load(uri).apply(options).into(holder.img1);
        st = mCurrent.getPics().get(1).get(1);
        uri = Uri.parse(st);


        Glide.with(this.mInflater.getContext()).load(uri).apply(options).into(holder.img2);
        st = mCurrent.getPics().get(2).get(1);
        uri = Uri.parse(st);


        Glide.with(this.mInflater.getContext()).load(uri).apply(options).into(holder.img3);

    }


    @NonNull
    @Override
    public ViewStoryAdapter.StoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View mItemView = mInflater.inflate(R.layout.item_memory_view, parent, false);
        return new StoryViewHolder(mItemView, this);

    }

    class StoryViewHolder extends RecyclerView.ViewHolder {
        public final TextView year;
        public final ImageView img1;
        public final ImageView img2;
        public final ImageView img3;

        final ViewStoryAdapter mAdapter;
        public StoryViewHolder(View itemView, ViewStoryAdapter adapter) {
            super(itemView);
            year = itemView.findViewById(R.id.textView13);
            img1 = itemView.findViewById(R.id.item_memory_img_1);
            img2 = itemView.findViewById(R.id.item_memory_img_2);
            img3 = itemView.findViewById(R.id.item_memory_img_3);
            this.mAdapter = adapter;


            img1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int mposition = getLayoutPosition();
                    Toast.makeText(view.getContext(), "img1clicked", Toast.LENGTH_SHORT).show();
                    VSMemories mCurrent = mStoryList.get(mposition);
                    Intent intent = new Intent(view.getContext(), MemoriesOfStoryActivity.class);
                    intent.putExtra("flag", 2);
                    intent.putExtra("storyId", storyId);
                    intent.putExtra("storyName", storyName);
                    intent.putExtra("memoryId", mCurrent.getPics().get(mposition).get(2));
                    Toast.makeText(view.getContext(), mCurrent.getPics().get(mposition).get(2), Toast.LENGTH_SHORT).show();
                   context.startActivity(intent);
                }

            });


            img2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int mposition = getLayoutPosition();
                    Toast.makeText(view.getContext(), "img2clicked", Toast.LENGTH_SHORT).show();
                    VSMemories mCurrent = mStoryList.get(mposition);
                    Intent intent = new Intent(view.getContext(), MemoriesOfStoryActivity.class);
                    intent.putExtra("flag", 2);
                    intent.putExtra("storyId", storyId);
                    intent.putExtra("storyName", storyName);
                    intent.putExtra("memoryId", mCurrent.getPics().get(mposition).get(2));
                    Toast.makeText(view.getContext(), mCurrent.getPics().get(mposition).get(2), Toast.LENGTH_SHORT).show();
                    context.startActivity(intent);


                }

            });
            img3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int mposition = getLayoutPosition();
                    Toast.makeText(view.getContext(), "img3clicked", Toast.LENGTH_SHORT).show();
                    VSMemories mCurrent = mStoryList.get(mposition);
                    Intent intent = new Intent(view.getContext(), MemoriesOfStoryActivity.class);
                    intent.putExtra("flag", 2);
                    intent.putExtra("storyId", storyId);
                    intent.putExtra("storyName", storyName);
                    intent.putExtra("memoryId", mCurrent.getPics().get(mposition).get(2));
                    Toast.makeText(view.getContext(), mCurrent.getPics().get(mposition).get(2), Toast.LENGTH_SHORT).show();
                   context.startActivity(intent);

                }

            });
            year.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int mposition = getLayoutPosition();
                    Toast.makeText(view.getContext(), "yearclicked", Toast.LENGTH_SHORT).show();
                    VSMemories mCurrent = mStoryList.get(mposition);
                    Intent intent = new Intent(view.getContext(), MemoriesOfStoryActivity.class);
                    intent.putExtra("flag", 0);
                    intent.putExtra("storyId", storyId);
                    intent.putExtra("storyName", storyName);
                    intent.putExtra("year", mCurrent.getYear());
                    Toast.makeText(view.getContext(), mCurrent.getYear().toString(), Toast.LENGTH_SHORT).show();
                   context.startActivity(intent);

                }

            });
        }
    }


    @Override
    public int getItemCount() {
        return mStoryList.size();
    }
}
