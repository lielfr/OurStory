package org.tsofen.ourstory.StoryTeam;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.tsofen.ourstory.R;

import java.util.LinkedList;

public class StoryFragment extends Fragment {

    public LinkedList<Story> linkedList;
    private RecyclerView mRecyclerView;
    private StoryAdapter mAdapter;

    public StoryFragment() {
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

        mRecyclerView = getView().findViewById(R.id.recyclerview);
        linkedList = new LinkedList<>();
        mAdapter = new StoryAdapter(this.getContext(), linkedList);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));

        int StoryListSize = linkedList.size();
        mRecyclerView.getAdapter().notifyItemInserted(StoryListSize);
        mRecyclerView.smoothScrollToPosition(StoryListSize);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_story, container, false);
    }
}
