package org.tsofen.ourstory;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.tsofen.ourstory.model.Comment;
import org.tsofen.ourstory.model.Memory;
import org.tsofen.ourstory.model.api.User;
import org.tsofen.ourstory.web.OurStoryService;
import org.tsofen.ourstory.web.WebFactory;

public class CommentActivity extends Activity {

    User user;
    RecyclerView rv;
    Memory memoryA;
    CommentAdapter adapter;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);
        Intent i = getIntent();
//        DisplayMetrics dm = new DisplayMetrics();
//        getWindowManager().getDefaultDisplay().getMetrics(dm);
//        int width = dm.widthPixels;
//        int height = dm.heightPixels;
//        getWindow().setLayout((int) (width * .8), (int) (height * .8));
        memoryA = (Memory) i.getSerializableExtra("memory");
        user = (User) i.getSerializableExtra("user");
        rv = findViewById(R.id.recycler_comment);
        adapter = new CommentAdapter(this, memoryA.getComments());
        rv.setAdapter(adapter);
        rv.setLayoutManager(new LinearLayoutManager(CommentActivity.this));
        adapter.notifyDataSetChanged();

        Button btn = findViewById(R.id.sendBtn2);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


            }
        });



    }
    public void SendCmnt(View view) {

        Comment comment = new Comment();
        TextView txtview = findViewById(R.id.AddComment);
        comment.setText(txtview.getText().toString());
        comment.setUser(user);
        OurStoryService service = WebFactory.getService();
        service.newComment(comment);


    }
}
