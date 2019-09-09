package org.tsofen.ourstory;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import org.tsofen.ourstory.model.Comment;
import org.tsofen.ourstory.model.api.CommentA;
import org.tsofen.ourstory.web.OurStoryService;
import org.tsofen.ourstory.web.WebFactory;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CommentActivity extends AppCompatActivity {

    long userId;
    RecyclerView rv;
    ArrayList<CommentA> comments;
    OurStoryService commentService;
    CommentAdapter adapter;int mem_id;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);
       /* Intent intent = getIntent();
        mem_id = intent.getStringExtra(MemoriesOfStoryActivity.EXTRA_MESSAGE);*/
       commentService = WebFactory.getService();
       commentService.GetCommentbyId(mem_id).enqueue(new Callback<ArrayList<CommentA>>() {
           @Override
           public void onResponse(Call<ArrayList<CommentA>> call, Response<ArrayList<CommentA>> response) {
               comments = response.body();
               adapter = new CommentAdapter(comments);
               rv.setAdapter(adapter);
               rv.setLayoutManager(new LinearLayoutManager(CommentActivity.this));

           }

           @Override
           public void onFailure(Call<ArrayList<CommentA>> call, Throwable t) {
               Log.d("Error", t.toString());
           }
       });


    }
    public void SendCmnt(View view) {

        CommentA comment = new CommentA();
        TextView txtview = findViewById(R.id.AddComment);
        comment.setText(txtview.getText().toString());
        comment.setUser(userId);
        OurStoryService service = WebFactory.getService();
        service.newComment(comment);

    }
}
