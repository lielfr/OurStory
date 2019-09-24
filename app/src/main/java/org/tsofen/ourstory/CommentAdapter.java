package org.tsofen.ourstory;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import org.tsofen.ourstory.model.Comment;
import org.tsofen.ourstory.model.api.User;
import org.tsofen.ourstory.web.OurStoryService;

import java.util.List;

public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.ViewHolder> {

    public List<Comment> comments;
    OurStoryService user_comment;
    Context ctx;
    LayoutInflater mInflater;
    User user;

    public CommentAdapter(Context context, List<Comment> comments) {
        this.comments = comments;
        mInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public CommentAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext(); // getting the main activity
        LayoutInflater inflater = LayoutInflater.from(context); // put layout of main activity in layout inflater

        // inflate the custom layout
        View contactView = inflater.inflate(R.layout.comment_item, parent, false);


        // return a new holder instance
        ViewHolder viewHolder = new ViewHolder(contactView, this);
        ctx = parent.getContext();

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CommentAdapter.ViewHolder holder, int position) {
        Comment comment = comments.get(position);
//        user_comment = WebFactory.getService();
//        user_comment.GetUserById(comment.getUser().getUserId()).enqueue(new Callback<Owner>() {
//            @Override
//            public void onResponse(Call<Owner> call, Response<Owner> response) {
//                user = response.body();
//                if(user.getLastName()!=null && user.getFirstName()!=null){
//                holder.name.setText(user.getFirstName() + " " + user.getLastName());}
//                if(user.getProfilePicture()!=null)
//                {holder.profile.setImageURI((Uri) user.getProfilePicture());}
//
//            }
//
//            @Override
//            public void onFailure(Call<Owner> call, Throwable t) {
//                Log.d("Error", t.toString());
//            }
//        });


        if(comment.getText()!=null){
            holder.comment.setText(comment.getText());
        }
        if (comment.getUser().getFullName() != null) {
            holder.name.setText((comment.getUser().getFullName()));
        }
        if (comment.getUser().getProfilePicture() != null) {
            Uri uri = Uri.parse(comment.getUser().getProfilePicture().toString());
            RequestOptions options = new RequestOptions()
                    .override(300, 300)
                    .centerCrop()
                    .placeholder(R.drawable.nopicyet)
                    .error(R.drawable.nopicyet);
            Glide.with(this.mInflater.getContext()).load(uri).apply(options).into(holder.profile);
        }
        else
        {
            holder.profile.setImageResource(R.drawable.defaultprofilepicture);
        }
    }

    @Override
    public int getItemCount() {
        return comments.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView name,comment;
        ImageView profile;
        public ViewHolder(@NonNull View itemView, CommentAdapter commentAdapter) {
            super(itemView);
            comment = itemView.findViewById(R.id.comment_to_show);
            name = itemView.findViewById(R.id.name_comment);
            profile = itemView.findViewById(R.id.profile_comment);

        }
    }
}