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

import static org.tsofen.ourstory.StoryTeam.StoryFragment.inflatedView;


public class MemoryFragment extends Fragment {
    public static ArrayList<MemoryA> memories = new ArrayList<>();
    public static RecyclerView mRecyclerView;
    public static MemoryAdapter mAdapter;
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
        // View inflatedView = getLayoutInflater().inflate(R.layout.activity_search_story, null);
        mRecyclerView =getView().findViewById(R.id.recyclerview1);
        //getView().findViewById(R.id.recyclerview);
        if(mRecyclerView==null) {
            Log.d("sss", " on view created nulll");
        }
        else
        {
            Log.d("sss", " on view created notnull");
            mAdapter = new MemoryAdapter(getActivity(), memories);
            mRecyclerView.setAdapter(mAdapter);
            mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            mAdapter.notifyDataSetChanged();
        }
    }

    public void CommitSearch( String searchinput){
        OurStoryService wepengine = WebFactory.getService();
        wepengine.GetMemoriesByKeyword(searchinput).enqueue(new Callback<ArrayList<MemoryA>>()
        {
            @Override
            public void onResponse(Call<ArrayList<MemoryA>> call, Response<ArrayList<MemoryA>> response)
            {
                memories = response.body();

                //
                if(memories==null) {
                    Log.d("error", "No Memories");
                }

                else
                    {

                    Log.d(" no error", memories.size()+" ");




                    mAdapter.notifyDataSetChanged();




                }
            }
            @Override
            public void onFailure(Call<ArrayList<MemoryA>> call, Throwable t) {
                Log.d("Error", t.toString());
            }
        });
//        int MemoryListSize = memories.size();
//        mRecyclerView.getAdapter().notifyItemInserted(MemoryListSize);
//        mRecyclerView.smoothScrollToPosition(MemoryListSize);
    }

}



