package org.tsofen.ourstory.StoryTeam;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.core.app.ActivityOptionsCompat;
import androidx.fragment.app.Fragment;

import com.google.gson.Gson;

import org.tsofen.ourstory.R;
import org.tsofen.ourstory.UserModel.AppHomePage;
import org.tsofen.ourstory.model.api.User;

import static android.content.Context.MODE_PRIVATE;


public class HomeFragment extends Fragment {

    AppHomePage parent;
    User userObj;

    public HomeFragment() {
        super();

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        parent = (AppHomePage) getActivity();
        return inflater.inflate(R.layout.fragment_main_visitor, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // This is a little trick to make the search bar clickable and not just the icon in it.

        final SearchView searchView = view.findViewById(R.id.searchView);
        searchView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), SearchStory.class);

                intent.putExtra("user", userObj);
                ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(
                        getActivity(), view.findViewById(R.id.searchView),
                        "search_story");
                startActivity(intent, options.toBundle());
            }
        });

        final Button createStory = view.findViewById(R.id.create_story_button);
        createStory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), CreateStory.class);
                intent.putExtra("user", userObj);
                startActivity(intent);
            }
        });
    }


}
