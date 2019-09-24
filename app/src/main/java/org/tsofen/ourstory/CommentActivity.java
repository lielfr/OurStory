package org.tsofen.ourstory;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.tsofen.ourstory.UserModel.AppHomePage;
import org.tsofen.ourstory.model.Comment;
import org.tsofen.ourstory.model.Memory;
import org.tsofen.ourstory.model.api.User;
import org.tsofen.ourstory.web.OurStoryService;
import org.tsofen.ourstory.web.WebFactory;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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

//        Button btn = findViewById(R.id.sendBtn2);
//        btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//
//            }
//        });



    }

    private void updateComments() {
        OurStoryService service = WebFactory.getService();
        service.GetMemoryById(memoryA.getId()).enqueue(new Callback<Memory>() {
            @Override
            public void onResponse(Call<Memory> call, Response<Memory> response) {
                if (response.code() == 200) {
                    memoryA = response.body();
                    adapter.comments = memoryA.getComments();
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<Memory> call, Throwable t) {

            }
        });
    }

    public void SendCmnt(View view) {

        Comment comment = new Comment();
        TextView txtview = findViewById(R.id.AddComment);
        comment.setText(txtview.getText().toString());
        comment.setUser(user);
        OurStoryService service = WebFactory.getService();
        if (user != null) {
            service.newComment(memoryA.getId(), comment).enqueue(new Callback<Comment>() {
                @Override
                public void onResponse(Call<Comment> call, Response<Comment> response) {
                    //Toast.makeText(getApplicationContext(), "added", Toast.LENGTH_LONG).show();
                    updateComments();
                }

                @Override
                public void onFailure(Call<Comment> call, Throwable t) {
                    Toast.makeText(getApplicationContext(), "failed", Toast.LENGTH_LONG).show();

                }
            });
            adapter.notifyDataSetChanged();
        }
        else
        {
            AlertDialog.Builder myAlertBuilder = new
                    AlertDialog.Builder(this);
            myAlertBuilder.setTitle("Error");
            myAlertBuilder.setMessage("Please Sign in to add a comment.");
            // Set the dialog title and message.
            myAlertBuilder.setPositiveButton("Ok", new
                    DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });
            myAlertBuilder.show();

        }
        }
}
