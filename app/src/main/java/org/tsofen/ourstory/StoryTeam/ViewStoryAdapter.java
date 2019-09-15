package org.tsofen.ourstory.StoryTeam;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import org.tsofen.ourstory.R;
import org.tsofen.ourstory.model.api.ListOfStory;
import org.tsofen.ourstory.model.api.Memory;
import org.tsofen.ourstory.model.api.Story;
import org.tsofen.ourstory.model.api.VSMemories;

import java.util.LinkedList;
import java.util.List;

public class ViewStoryAdapter extends RecyclerView.Adapter<ViewStoryAdapter.StoryViewHolder> {
    public final List<VSMemories> mStoryList;
    private LayoutInflater mInflater;



    public ViewStoryAdapter(Context context, List<VSMemories> StoryList) {
        mInflater = LayoutInflater.from(context);
        this.mStoryList = StoryList;
    }


    class StoryViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener{
        public final TextView year;
        public final ImageButton img1;
        public final ImageButton img2;
        public final ImageButton img3;

        final ViewStoryAdapter mAdapter;
        public StoryViewHolder(View itemView, ViewStoryAdapter adapter) {
            super(itemView);
            year = itemView.findViewById(R.id.textView13);
            img1 = itemView.findViewById(R.id.imageButton3);
            img2 = itemView.findViewById(R.id.imageButton4);
            img3 = itemView.findViewById(R.id.imageButton5);
            this.mAdapter = adapter;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {

        }
    }

    @NonNull
    @Override
    public ViewStoryAdapter.StoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View mItemView = mInflater.inflate(R.layout.item_memory_view, parent  , false);
            return new StoryViewHolder(mItemView, this);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewStoryAdapter.StoryViewHolder holder, int position) {
        VSMemories mCurrent = mStoryList.get(position);
        Log.d("fadi",mCurrent.getPics().get(0).get(1).toString());
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

    @Override
    public int getItemCount() {
        return mStoryList.size();
    }
}
