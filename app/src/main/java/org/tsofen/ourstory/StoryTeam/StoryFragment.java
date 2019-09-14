package org.tsofen.ourstory.StoryTeam;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.tsofen.ourstory.R;
import org.tsofen.ourstory.model.api.ListOfStory;
import org.tsofen.ourstory.web.OurStoryService;
import org.tsofen.ourstory.web.WebFactory;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static org.tsofen.ourstory.R.*;
import static org.tsofen.ourstory.R.layout.*;

public class StoryFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private StoryAdapter mAdapter;
    private EditText editText;
    private SearchView searchView ;
    private ArrayList<ListOfStory> arr = new ArrayList<>();

    public StoryFragment() {
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        mRecyclerView = view.findViewById(id.recyclerview);
        if (mRecyclerView==null){
            Log.i("checknull", "the mrecycleview is null ");
        }
             Log.i("checknull", "the mrecycleview isnt null");
        View inflatedView = getLayoutInflater().inflate(R.layout.activity_search_story,null);
        editText = (EditText) inflatedView.findViewById(id.editText);

        OurStoryService wepengine = WebFactory.getService();
        String n = editText.getText().toString(); /// please dont delete this
        //Toast.makeText(getActivity() , "the extra name is :"+ getString("searchinpute"), Toast.LENGTH_SHORT).show();

        Toast.makeText(getActivity() , "the extra name is : null ", Toast.LENGTH_SHORT).show();

        wepengine.GetStoriesByName("mali").enqueue(new Callback<ArrayList<ListOfStory>>() {
            @Override
            public void onResponse(Call<ArrayList<ListOfStory>> call, Response<ArrayList<ListOfStory>> response) {


                if (arr != null) {
                    arr = response.body();
                    mAdapter = new StoryAdapter(inflatedView.getContext() , arr);
                    mRecyclerView.setAdapter(mAdapter);
                    mRecyclerView.setLayoutManager(new LinearLayoutManager(StoryFragment.this.getContext()));
                    mAdapter.notifyDataSetChanged();
                    Toast.makeText(getActivity(), "size =" + arr.size(), Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getActivity(), "getting was failed", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ArrayList<ListOfStory>> call, Throwable t) {
                Toast.makeText(getActivity(), "getting was failed", Toast.LENGTH_SHORT).show();

            }
        });


        editText.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity() , "the edit text is .. ", Toast.LENGTH_SHORT).show();





            }
        });


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        return inflater.inflate(fragment_story, container, false);

    }
}

