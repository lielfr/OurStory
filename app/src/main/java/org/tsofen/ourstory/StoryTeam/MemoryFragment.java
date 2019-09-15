package org.tsofen.ourstory.StoryTeam;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.tsofen.ourstory.MemoryAdapter;
import org.tsofen.ourstory.R;
import org.tsofen.ourstory.model.api.MemoryA;
import org.tsofen.ourstory.web.OurStoryService;
import org.tsofen.ourstory.web.WebFactory;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MemoryFragment extends Fragment {
    public ArrayList<MemoryA> memories;
    private RecyclerView mRecyclerView;
    private MemoryAdapter mAdapter;
    OurStoryService MemoryAService;
    Context ctx;


    public MemoryFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_memory, container, false);

    }
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        View inflatedView = getLayoutInflater().inflate(R.layout.activity_search_story, null);
        mRecyclerView = getView().findViewById(R.id.recyclerview);

        OurStoryService wepengine = WebFactory.getService();
        wepengine.GetMemoriesByKeyword("description").enqueue(new Callback<ArrayList<MemoryA>>() {
            @Override
            public void onResponse(Call<ArrayList<MemoryA>> call, Response<ArrayList<MemoryA>> response) {
                memories = response.body();
                mAdapter = new MemoryAdapter(inflatedView.getContext(), memories);
                mRecyclerView.setAdapter(mAdapter);
                mRecyclerView.setLayoutManager(new LinearLayoutManager(MemoryFragment.this.getContext()));
                mAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<ArrayList<MemoryA>> call, Throwable t) {
                Log.d("Error", t.toString());
            }
        });
        mRecyclerView = getView().findViewById(R.id.recyclerview);
        memories = new ArrayList<>();

        mAdapter = new MemoryAdapter(ctx,memories);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));

        int MemoryListSize = memories.size();
        mRecyclerView.getAdapter().notifyItemInserted(MemoryListSize);
        mRecyclerView.smoothScrollToPosition(MemoryListSize);

    }


}
