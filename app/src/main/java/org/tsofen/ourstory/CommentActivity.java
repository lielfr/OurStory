package org.tsofen.ourstory;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import org.tsofen.ourstory.model.Comment;
import org.tsofen.ourstory.model.Memory;
import org.tsofen.ourstory.model.api.CommentA;
import org.tsofen.ourstory.model.api.MemoryA;
import org.tsofen.ourstory.model.api.User;
import org.tsofen.ourstory.web.OurStoryService;
import org.tsofen.ourstory.web.WebFactory;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CommentActivity extends Activity {

    User user;
    RecyclerView rv;
    MemoryA memoryA;
    CommentAdapter adapter;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);
        Intent i = getIntent();
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int width = dm.widthPixels;
        int height = dm.heightPixels;
        getWindow().setLayout((int) (width * .8), (int) (height * .8));
        memoryA = (MemoryA) i.getSerializableExtra("memory");
        user = (User) i.getSerializableExtra("user");
        rv = findViewById(R.id.recycler_comment);
        adapter = new CommentAdapter(this, memoryA.getComments());
        rv.setAdapter(adapter);
        rv.setLayoutManager(new LinearLayoutManager(CommentActivity.this));
        adapter.notifyDataSetChanged();

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