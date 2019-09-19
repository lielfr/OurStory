package org.tsofen.ourstory.StoryTeam;

import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.tsofen.ourstory.R;
import org.tsofen.ourstory.model.api.ListOfStory;
import org.tsofen.ourstory.web.OurStoryService;
import org.tsofen.ourstory.web.WebFactory;

import java.util.ArrayList;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StoryFragment extends Fragment {

    static RecyclerView mRecyclerView;
    static StoryAdapter mAdapter;
    static EditText editText;
    static ArrayList<ListOfStory> arr = new ArrayList<>();
    int flag = 0;
    int op = 0;
    OurStoryService wb;
    static View inflatedView;

    public StoryFragment() {
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        inflatedView = getLayoutInflater().inflate(R.layout.activity_search_story, null);
        mRecyclerView = getView().findViewById(R.id.recyclerview);
        Log.i("fragment", "hello from onViewCreated !!1");

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.i("fragment", "hello from onCreatedView");
        return inflater.inflate(R.layout.fragment_story, container, false);

    }

    void CommitSearch(Context context, String searchinput, int flag, int op, int D, int M, int Y, int SearchBy) {
//        Toast.makeText(context, "the Value from fragment is " + searchinput, Toast.LENGTH_SHORT).show();
        wb = WebFactory.getService();

        if (SearchBy == 0) {//this mean that the user want to Search by name

            //  Toast.makeText(getContext(), "search by name", Toast.LENGTH_SHORT).show();

            wb.GetStoriesByName(searchinput).enqueue(new Callback<ArrayList<ListOfStory>>() {
                @Override
                public void onResponse(Call<ArrayList<ListOfStory>> call, Response<ArrayList<ListOfStory>> response) {

                    arr = response.body();
                    if (arr == null) {
                        Log.d("rrr", "arr is null");
                    } else {
                        mAdapter = new StoryAdapter(inflatedView.getContext(), arr);
                        mRecyclerView.setAdapter(mAdapter);
                        mRecyclerView.setLayoutManager(new LinearLayoutManager(StoryFragment.this.getContext()));
                        mAdapter.notifyDataSetChanged();

                        if (arr != null) {
//                            Toast.makeText(context, "size =" + arr.size(), Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(context, "getting was failed", Toast.LENGTH_SHORT).show();
                        }
                    }
                }


                @Override
                public void onFailure(Call<ArrayList<ListOfStory>> call, Throwable t) {
                    Toast.makeText(context, "getting was failed", Toast.LENGTH_SHORT).show();

                }
            });


        } else if (flag == 0) {//this mean that the user want to search by DATE
            if (op == 1) {//search by birthday
                Toast.makeText(context, "search by Birthday", Toast.LENGTH_SHORT).show();

                wb.GetStoriesByDobFull(D, M, Y).enqueue(new Callback<ArrayList<ListOfStory>>() {
                    @Override
                    public void onResponse(Call<ArrayList<ListOfStory>> call, Response<ArrayList<ListOfStory>> response) {

                        arr = response.body();
                        if (arr == null) {
                            Log.d("rrr", "arr is null");
                        } else {
                            mAdapter = new StoryAdapter(inflatedView.getContext(), arr);
                            mRecyclerView.setAdapter(mAdapter);
                            mRecyclerView.setLayoutManager(new LinearLayoutManager(StoryFragment.this.getContext()));
                            mAdapter.notifyDataSetChanged();

                            if (arr != null) {
//                                Toast.makeText(context, "size =" + arr.size(), Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(context, "getting was failed", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }


                    @Override
                    public void onFailure(Call<ArrayList<ListOfStory>> call, Throwable t) {
                        Toast.makeText(context, "getting was failed", Toast.LENGTH_SHORT).show();

                    }
                });

            } else if (op == 2) { //search by Deathday

                Toast.makeText(context, "search by Deathday from API", Toast.LENGTH_SHORT).show();

                wb.GetStoriesByDodFull(D, M, Y).enqueue(new Callback<ArrayList<ListOfStory>>() {
                    @Override
                    public void onResponse(Call<ArrayList<ListOfStory>> call, Response<ArrayList<ListOfStory>> response) {

                        arr = response.body();
                        if (arr == null) {
                            Log.d("rrr", "arr is null");
                        } else {
                            mAdapter = new StoryAdapter(inflatedView.getContext(), arr);
                            mRecyclerView.setAdapter(mAdapter);
                            mRecyclerView.setLayoutManager(new LinearLayoutManager(StoryFragment.this.getContext()));
                            mAdapter.notifyDataSetChanged();

                            if (arr != null) {
//                                Toast.makeText(context, "size =" + arr.size(), Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(context, "getting was failed", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<ArrayList<ListOfStory>> call, Throwable t) {
                        Toast.makeText(context, "getting was failed", Toast.LENGTH_SHORT).show();

                    }
                });
            }


        } else { //search by both!!! activating the search by name

            if (op == 1) {//search by birthday and name
                Toast.makeText(context, "search by BirthDay and name from API", Toast.LENGTH_SHORT).show();

                wb.GetStoriesByDateOfBirth(D, M, Y, searchinput).enqueue(new Callback<ArrayList<ListOfStory>>() {
                    @Override
                    public void onResponse(Call<ArrayList<ListOfStory>> call, Response<ArrayList<ListOfStory>> response) {

                        arr = response.body();
                        if (arr == null) {
                            Log.d("rrr", "arr is null");
                        } else {
                            mAdapter = new StoryAdapter(inflatedView.getContext(), arr);
                            mRecyclerView.setAdapter(mAdapter);
                            mRecyclerView.setLayoutManager(new LinearLayoutManager(StoryFragment.this.getContext()));
                            mAdapter.notifyDataSetChanged();

                            if (arr != null) {
//                                Toast.makeText(context, "size =" + arr.size(), Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(context, "getting was failed", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }


                    @Override
                    public void onFailure(Call<ArrayList<ListOfStory>> call, Throwable t) {
                        Toast.makeText(context, "getting was failed", Toast.LENGTH_SHORT).show();

                    }
                });

            } else if (op == 2) { //search by Deathday and activating seachByName

                Toast.makeText(context, "search by Deathday and Name from API", Toast.LENGTH_SHORT).show();

                wb.GetStoriesByDateOfDeath(D, M, Y, searchinput).enqueue(new Callback<ArrayList<ListOfStory>>() {
                    @Override
                    public void onResponse(Call<ArrayList<ListOfStory>> call, Response<ArrayList<ListOfStory>> response) {

                        arr = response.body();
                        if (arr == null) {
                            Log.d("rrr", "arr is null");
                        } else {
                            mAdapter = new StoryAdapter(inflatedView.getContext(), arr);
                            mRecyclerView.setAdapter(mAdapter);
                            mRecyclerView.setLayoutManager(new LinearLayoutManager(StoryFragment.this.getContext()));
                            mAdapter.notifyDataSetChanged();

                            if (arr != null) {
//                                Toast.makeText(context, "size =" + arr.size(), Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(context, "getting was failed", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }


                    @Override
                    public void onFailure(Call<ArrayList<ListOfStory>> call, Throwable t) {
                        Toast.makeText(context, "getting was failed", Toast.LENGTH_SHORT).show();

                    }
                });
            }

        }


    }
}