package org.tsofen.ourstory.StoryTeam;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.tsofen.ourstory.MemoryAdapter;
import org.tsofen.ourstory.R;
import org.tsofen.ourstory.model.Memory;

import java.util.ArrayList;


public class MemoryFragment extends Fragment {
    public ArrayList<Memory> arrayList;
    private RecyclerView mRecyclerView;
    private MemoryAdapter mAdapter;

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
        if (mAdapter!=null) {
            mRecyclerView = getView().findViewById(R.id.recyclerview);
            arrayList = new ArrayList<Memory>();
            mAdapter = new MemoryAdapter(getContext(), arrayList);
            mRecyclerView.setAdapter(mAdapter);
            mRecyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
            int MemoryListSize = arrayList.size();
            mRecyclerView.getAdapter().notifyItemInserted(MemoryListSize);
            mRecyclerView.smoothScrollToPosition(MemoryListSize);
        }else {
//            Toast.makeText(getContext(), "the adapter is null", Toast.LENGTH_SHORT).show();
        }
    }


}
