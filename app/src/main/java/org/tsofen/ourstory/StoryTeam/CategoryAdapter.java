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

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {
    List<CategoryItem> mItems;
    private LayoutInflater mInflater;

    public CategoryAdapter(Context context, List<CategoryItem> mItems) {
        this.mItems = mItems;
        this.mInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public CategoryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = mInflater.inflate(R.layout.category_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(v, this);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryAdapter.ViewHolder holder, int position) {
        CategoryItem itemInMenu = mItems.get(position);
        holder.TV_category_name.setText(itemInMenu.getmName());
        holder.IV_category_image.setImageResource(itemInMenu.getmImage());
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements
            View.OnClickListener {

        public TextView TV_category_name;
        public ImageView IV_category_image;
        CategoryAdapter mAdapter;

        public ViewHolder(@NonNull View itView, CategoryAdapter mAdapter) {
            super(itView);
            TV_category_name = itView.findViewById(R.id.TV_category_name);
            this.mAdapter = mAdapter;
            IV_category_image = itView.findViewById(R.id.IV_category_image);
            itView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {

        }
    }
}
