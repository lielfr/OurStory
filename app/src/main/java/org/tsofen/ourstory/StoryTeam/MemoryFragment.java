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

import org.tsofen.ourstory.R;
import org.tsofen.ourstory.SearchMemoryAdapter;
import org.tsofen.ourstory.model.Memory;
import org.tsofen.ourstory.web.OurStoryService;
import org.tsofen.ourstory.web.WebFactory;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MemoryFragment extends Fragment {
    public static ArrayList<Memory> memories = new ArrayList<>();
    public static RecyclerView mRecyclerView;
    public static SearchMemoryAdapter mAdapter;
    static View inflatedView;
    OurStoryService wb;
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
        memories.clear();
        inflatedView = getLayoutInflater().inflate(R.layout.activity_search_story, null);
        mRecyclerView = getView().findViewById(R.id.recyclerview1);
        if (mRecyclerView == null) {
            Log.d("fragment", " on memoryfragment view created nulll");
        } else {
            Log.d("fragment", " on memoryfragment view created notnull");

        }

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setAdapter(mAdapter);
        mAdapter = new SearchMemoryAdapter(inflatedView.getContext(), memories);
        mAdapter.notifyDataSetChanged();
    }

    public void CommitSearch(String searchinput) {
        Log.i("fragment", "commited : searching for " + searchinput);
        wb = WebFactory.getService();
        wb.GetMemoriesByKeyword(searchinput).enqueue(new Callback<ArrayList<Memory>>() {
            @Override
            public void onResponse(Call<ArrayList<Memory>> call, Response<ArrayList<Memory>> response) {
                    memories = response.body();
                if (memories == null) {
                    Log.d("fragment", "No Memories");
                } else {
                    Log.d("fragment", " on response number of memories " + memories.size() + " ");
                    mAdapter = new SearchMemoryAdapter(inflatedView.getContext(), memories);
                    mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                    mRecyclerView.setAdapter(mAdapter);
                    mAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Memory>> call, Throwable t) {
                Log.d("Error", t.toString());
            }
        });
//        int MemoryListSize = memories.size();
//        mRecyclerView.getAdapter().notifyItemInserted(MemoryListSize);
//        mRecyclerView.smoothScrollToPosition(MemoryListSize);
    }

}



