package org.tsofen.ourstory;

import android.content.Context;
import android.text.InputType;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ourstory.R;

import org.tsofen.ourstory.model.Tag;

import java.util.List;

public class AddMemoryTagAdapter extends RecyclerView.Adapter<AddMemoryTagAdapter.ViewHolder> {

    List<Tag> tags;

    public AddMemoryTagAdapter(List<Tag> tags) {
        super();
        this.tags = tags;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context ctx = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(ctx);
        View view = inflater.inflate(R.layout.addmemory_tags_rv_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ImageView closeButton = holder.itemView.findViewById(R.id.imageView_tags_rv);
        EditText editText = holder.itemView.findViewById(R.id.cememory_rv_text);
        if (position == tags.size()) {
            editText.setInputType(InputType.TYPE_CLASS_TEXT); // Enables input
            closeButton.setVisibility(View.INVISIBLE);
            editText.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // TODO: Add the suggestions menu here
                }
            });
            editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
                @Override
                public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                    // This actually adds the tag whenever the user presses the enter.
                    if (i == EditorInfo.IME_NULL && keyEvent.getAction() == KeyEvent.ACTION_DOWN) {
                        Tag t = new Tag();
                        t.setLabel(editText.getText().toString());
                        tags.add(t);
                        notifyDataSetChanged();
                    }
                    return true;
                }
            });
        } else {
            editText.setText(tags.get(position).getLabel());
            closeButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    tags.remove(position);
                    notifyItemRemoved(position);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return tags.size() + 1;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
