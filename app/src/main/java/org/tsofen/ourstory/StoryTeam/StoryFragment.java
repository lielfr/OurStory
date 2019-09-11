package org.tsofen.ourstory.StoryTeam;

import android.os.Bundle;
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
         int flag;
         int op=0;
        TextView D1=inflatedView.findViewById(R.id.Day);
            String D2 = D1.getText().toString();
            int D = Integer.parseInt(D2);
            Toast.makeText(getActivity(), "the value is " + D, Toast.LENGTH_SHORT).show();

        TextView M1=inflatedView.findViewById(R.id.Month);
        String M2=M1.getText().toString();
        int M=Integer.parseInt(M2);

        TextView Y1=inflatedView.findViewById(R.id.Year);
        String Y2=Y1.getText().toString();
        int Y=Integer.parseInt(Y2);

        TextView cat = inflatedView.findViewById(R.id.catg);
        String option =cat.getText().toString();
        if(option=="BirthDay: ")
         op=1;
        else if(option=="Death Day: ")
            op=2;
        //OurStoryService wepengine = WebFactory.getService();
        String n = editText.getText().toString(); /// please dont delete this

        OurStoryService wb = WebFactory.getService();

         if(editText==null ) flag=0;
         else if(n=="Story/Memory name")  flag=0;
         else flag=1;


        if( D!=0  && flag==0 ) {//search by date and year



            if (op == 1) {
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
                                Toast.makeText(getActivity(), "size =" + arr.size(), Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(getActivity(), "getting was failed", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }


                    @Override
                    public void onFailure(Call<ArrayList<ListOfStory>> call, Throwable t) {
                        Toast.makeText(getActivity(), "getting was failed", Toast.LENGTH_SHORT).show();

                    }
                });

            }


            else if(op==2) {
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
                                Toast.makeText(getActivity(), "size =" + arr.size(), Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(getActivity(), "getting was failed", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }





            @Override
            public void onFailure(Call<ArrayList<ListOfStory>> call, Throwable t) {
                Toast.makeText(getActivity(), "getting was failed", Toast.LENGTH_SHORT).show();

            }
        });
                }

    }





        else  if(D==0 && flag==1)
        {

            wb.GetStoriesByName(n).enqueue(new Callback<ArrayList<ListOfStory>>() {
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
                            Toast.makeText(getActivity(), "size =" + arr.size(), Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getActivity(), "getting was failed", Toast.LENGTH_SHORT).show();
                        }
                    }
                }


                @Override
                public void onFailure(Call<ArrayList<ListOfStory>> call, Throwable t) {
                    Toast.makeText(getActivity(), "getting was failed", Toast.LENGTH_SHORT).show();

                }
            });




        }



        else {

            if(D!=0 && flag==1) {
                wb.GetStoriesByDateOfBirth(D,M,Y,n).enqueue(new Callback<ArrayList<ListOfStory>>() {
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
                                Toast.makeText(getActivity(), "size =" + arr.size(), Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(getActivity(), "getting was failed", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }


                    @Override
                    public void onFailure(Call<ArrayList<ListOfStory>> call, Throwable t) {
                        Toast.makeText(getActivity(), "getting was failed", Toast.LENGTH_SHORT).show();

                    }
                });


            }

        }








    }













    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        return inflater.inflate(R.layout.fragment_story, container, false);

    }
}






