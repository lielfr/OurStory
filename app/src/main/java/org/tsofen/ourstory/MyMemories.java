package org.tsofen.ourstory;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;

import org.tsofen.ourstory.StoryTeam.CreateStory;
import org.tsofen.ourstory.StoryTeam.SearchStory;
import org.tsofen.ourstory.UserModel.AppHomePage;
import org.tsofen.ourstory.model.Memory;
import org.tsofen.ourstory.model.api.User;
import org.tsofen.ourstory.web.OurStoryService;
import org.tsofen.ourstory.web.WebFactory;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.Context.MODE_PRIVATE;

public class MyMemories extends Fragment {
    private static final String LOG_TAG = CommentActivity.class.getSimpleName();
    public static final String EXTRA_MESSAGE = "org.tsofen.ourstory.extra.MESSAGE";
    AppHomePage parent;
    RecyclerView rv;
    Long user_id;
    ArrayList<Memory> memories;
    OurStoryService MemoryAService;
    MyMemoriesAdapter adapter;
    TextView storyName;
    SharedPreferences pr;
    boolean sendUserIntent = false;

    public MyMemories() {
        super();
    }

    @Nullable
    @Override
    public View onCreateView( @Nullable LayoutInflater inflater, @Nullable ViewGroup container,
                              @Nullable Bundle savedInstanceState) {
        parent = (AppHomePage) getActivity();
        return inflater.inflate(R.layout.fragment_my_memories, container, false);

    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (getActivity() == null) return;
        Gson gson = new Gson();
        pr = getContext().getSharedPreferences(getString(R.string.shared_pref_key), MODE_PRIVATE);
        String userJsonString = pr.getString(AppHomePage.USER, "ERROR");
        sendUserIntent = userJsonString.equals("ERROR");
        if (!userJsonString.equals("ERROR") || AppHomePage.user2 != null && AppHomePage.user2.length() > 0) {
            User userObj = userJsonString.equals("ERROR") ?
                    gson.fromJson(AppHomePage.user2, User.class) :
                    gson.fromJson(userJsonString, User.class);
            user_id = userObj.getUserId();
            rv = view.findViewById(R.id.recycler);
            MemoryAService = WebFactory.getService();
            MemoryAService.GetMemoriesByUser(user_id).enqueue(new Callback<ArrayList<Memory>>() {
                @Override
                public void onResponse(Call<ArrayList<Memory>> call, Response<ArrayList<Memory>> response) {
                    if (getActivity() == null) return;
                    memories = response.body();
                    adapter = new MyMemoriesAdapter(getActivity().getApplicationContext(), memories, userObj, rv);
                    rv.setAdapter(adapter);
                    rv.setLayoutManager(new LinearLayoutManager(getContext()));
                    adapter.notifyDataSetChanged();

                }

                @Override
                public void onFailure(Call<ArrayList<Memory>> call, Throwable t) {
                    Log.d("Error", t.toString());
                }
            });

            final Button create_story = view.findViewById(R.id.createStroyBtn);
            create_story.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent Create = new Intent(getActivity(), CreateStory.class);
                    Create.putExtra("userId", (userObj.getUserId()).toString());
                    startActivity(Create);
                }
            });
            ImageButton btn = view.findViewById(R.id.searchview2);
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent myIntent = new Intent(getActivity(), SearchStory.class);
                    if (sendUserIntent)
                        myIntent.putExtra("user", userObj);
                    startActivity(myIntent);
                }
            });

        }
    }
    }


