package org.tsofen.ourstory;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;


import org.tsofen.ourstory.model.Comment;
import org.tsofen.ourstory.model.api.CommentA;
import org.tsofen.ourstory.model.api.User;
import org.tsofen.ourstory.web.OurStoryService;
import org.tsofen.ourstory.web.WebFactory;

public class AddComment extends Fragment {

    User user;

    public AddComment(User user) {
        // Required empty public constructor
        this.user = user;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.add_comment, container, false);
    }


    public void SendCmnt(View view) {

        Activity activity = getActivity();
        Comment comment = new Comment();
        TextView txtview = activity.findViewById(R.id.AddComment);
        comment.setText(txtview.getText().toString());
        comment.setUser(user);
        OurStoryService service = WebFactory.getService();
        /* service.newComment(comment);*/

    }
}
