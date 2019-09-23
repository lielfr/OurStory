package org.tsofen.ourstory.EditCreateMemory;

import android.content.Context;
import android.graphics.Rect;
import android.text.InputType;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.tsofen.ourstory.R;
import org.tsofen.ourstory.model.Tag;
import org.tsofen.ourstory.web.OurStoryService;
import org.tsofen.ourstory.web.WebFactory;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddMemoryTagAdapter extends RecyclerView.Adapter<AddMemoryTagAdapter.ViewHolder> {

    List<Tag> tags = new ArrayList<>();
    Context ctx;
    RecyclerView rv;

    public AddMemoryTagAdapter(List<Tag> tags, RecyclerView rv) {
        super();
        this.tags = tags;
        this.rv = rv;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ctx = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(ctx);
        View view = inflater.inflate(R.layout.addmemory_tags_rv_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    // Unfortunately we need to calculate the text width manually for the autocompletetextview.
    public int calculateWidth(String text) {
        Rect bounds = new Rect();
        TextView textView = new TextView(ctx);
        textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 24);
        textView.getPaint().getTextBounds(text, 0, text.length(), bounds);
        return bounds.width();
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ImageView closeButton = holder.itemView.findViewById(R.id.imageView_tags_rv);
        AutoCompleteTextView editText = holder.itemView.findViewById(R.id.cememory_rv_text);
        if (position == tags.size()) {
            // The last view holder allows the user to enter a new tag
            editText.setInputType(InputType.TYPE_CLASS_TEXT); // Enables input
            closeButton.setVisibility(View.GONE);
            editText.setOnEditorActionListener((textView, i, keyEvent) -> {
                // This actually adds the tag whenever the user presses Done on the soft keyboard.
                // TODO: This needs more fine tuning, like maybe allowing other events to trigger it
                if ((i == EditorInfo.IME_ACTION_DONE && keyEvent == null)) {
                    Tag t = new Tag();
                    t.setLabel(editText.getText().toString());
                    tags.add(t);
                    notifyItemInserted(tags.size() - 1);
                    editText.setText("");
                    rv.scrollToPosition(tags.size());

                }
                return true;
            });

            /* While this one may seem unnecessary, it actually isn't.
             * This is because whenever the text box is already selected when a new tag was
             * just added, the user may want to click on it to open the suggestions menu again.*/
            editText.setOnClickListener(view -> editText.showDropDown());
            editText.setOnFocusChangeListener((view, b) -> {

                if (!b) {
                    Tag t = new Tag();
                    t.setLabel(editText.getText().toString());
                    tags.add(t);
                    notifyItemInserted(tags.size() - 1);
                    editText.setText("");
                    rv.scrollToPosition(tags.size());
                }
            });
            // TODO: Replace this with using a suggestion API
//            final String[] suggestions = new String[]{"sunset", "beach", "water", "sky", "beer"};
            OurStoryService service = WebFactory.getService();
            service.GetAllTags().enqueue(new Callback<List<Tag>>() {
                @Override
                public void onResponse(Call<List<Tag>> call, Response<List<Tag>> response) {
                    if (response.code() != 200) return;
                    List<Tag> suggestionsList = response.body();
                    if (suggestionsList == null) return;
                    List<String> suggestionsStrList = new LinkedList<>();
                    int i = 0;
                    for (Tag tag : suggestionsList)
                        suggestionsStrList.add(tag.getLabel());
                    ArrayAdapter<String> adapter = new ArrayAdapter<>(ctx,
                            R.layout.tags_dropdown_item, suggestionsStrList);
                    editText.setAdapter(adapter);
                }

                @Override
                public void onFailure(Call<List<Tag>> call, Throwable t) {

                }
            });


//            editText.showDropDown();
        } else {
            String text = tags.get(position).getLabel();
            editText.setText(text);
            editText.setInputType(InputType.TYPE_NULL);
            editText.setWidth(calculateWidth(text));
            closeButton.setVisibility(View.VISIBLE);
            closeButton.setOnClickListener(view -> {
                tags.remove(position);
                notifyItemRemoved(position);
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
