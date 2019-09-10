package org.tsofen.ourstory;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;


import org.tsofen.ourstory.model.api.CommentA;
import org.tsofen.ourstory.web.OurStoryService;
import org.tsofen.ourstory.web.WebFactory;

public class AddComment extends Fragment {

    long userId;

    public AddComment(long userId) {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.add_comment, container, false);
    }


    public void SendCmnt(View view) {

        Activity activity = getActivity();
        CommentA comment = new CommentA();
        TextView txtview = activity.findViewById(R.id.AddComment);
        comment.setText(txtview.getText().toString());
        comment.setUser(userId);
        OurStoryService service = WebFactory.getService();
        service.newComment(comment);

    }
}
