package org.tsofen.ourstory;

import android.content.Context;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.tsofen.ourstory.UserModel.User;
import org.tsofen.ourstory.model.Comment;
import org.tsofen.ourstory.model.api.CommentA;
import org.tsofen.ourstory.model.api.Owner;
import org.tsofen.ourstory.web.OurStoryService;
import org.tsofen.ourstory.web.WebFactory;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.ViewHolder> {

    public ArrayList<CommentA> comments;
    OurStoryService user_comment;
    Owner user;
    @NonNull
    @Override
    public CommentAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext(); // getting the main activity
        LayoutInflater inflater = LayoutInflater.from(context); // put layout of main activity in layout inflater

        // inflate the custom layout
        View contactView = inflater.inflate(R.layout.comment_item, parent, false);


        // return a new holder instance
        ViewHolder viewHolder = new ViewHolder(contactView, this);


        return viewHolder;
    }

    public CommentAdapter(ArrayList<CommentA> comments) {
        this.comments = comments;
    }

    @Override
    public void onBindViewHolder(@NonNull CommentAdapter.ViewHolder holder, int position) {
        CommentA comment = comments.get(position);
        user_comment = WebFactory.getService();
        user_comment.GetUserById((Long) comment.getUser()).enqueue(new Callback<Owner>() {
            @Override
            public void onResponse(Call<Owner> call, Response<Owner> response) {
                user = response.body();
                holder.name.setText(user.getFirstName() + " " + user.getLastName());
                holder.profile.setImageURI((Uri) user.getProfilePicture());

            }

            @Override
            public void onFailure(Call<Owner> call, Throwable t) {
                Log.d("Error", t.toString());
            }
        });

        holder.comment.setText(comment.getText());

    }

    @Override
    public int getItemCount() {
        return 0;
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