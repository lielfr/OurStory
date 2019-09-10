package org.tsofen.ourstory.StoryTeam;

import android.os.Bundle;
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

public class StoryFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private StoryAdapter mAdapter;
    private EditText editText;
    private SearchView searchView;
    private ArrayList<ListOfStory> arr = new ArrayList<>();

    public StoryFragment() {
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        View inflatedView = getLayoutInflater().inflate(R.layout.activity_search_story, null);
        mRecyclerView = getView().findViewById(R.id.recyclerview);
        editText = (EditText) inflatedView.findViewById(R.id.editText);

        OurStoryService wepengine = WebFactory.getService();
        String n = editText.getText().toString(); /// please dont delete this

        wepengine.GetStoriesByName("mali").enqueue(new Callback<ArrayList<ListOfStory>>() {
            @Override
            public void onResponse(Call<ArrayList<ListOfStory>> call, Response<ArrayList<ListOfStory>> response) {
                arr = response.body();
                mAdapter = new StoryAdapter(inflatedView.getContext(), arr);
                mRecyclerView.setAdapter(mAdapter);
                mRecyclerView.setLayoutManager(new LinearLayoutManager(StoryFragment.this.getContext()));
                mAdapter.notifyDataSetChanged();

                if (arr != null) {
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

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        return inflater.inflate(R.layout.fragment_story, container, false);

    }
}

