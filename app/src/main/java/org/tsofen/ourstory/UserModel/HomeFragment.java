package org.tsofen.ourstory.UserModel;

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
import androidx.fragment.app.Fragment;

import com.google.gson.Gson;

import org.tsofen.ourstory.R;
import org.tsofen.ourstory.StoryTeam.CreateStory;
import org.tsofen.ourstory.StoryTeam.SearchStory;
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
        Gson gson = new Gson();

        SharedPreferences pr = getContext().getSharedPreferences(getString(R.string.shared_pref_key), MODE_PRIVATE);
        String userJsonString = pr.getString(AppHomePage.USER, "ERROR");
        userObj = userJsonString.equals("ERROR") ?
                gson.fromJson(AppHomePage.user2, User.class) :
                gson.fromJson(userJsonString, User.class);
        // This is a little trick to make the search bar clickable and not just the icon in it.

        final SearchView searchView = view.findViewById(R.id.searchView);
        searchView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), SearchStory.class);
                startActivity(intent);
            }
        });

        final Button createStory = view.findViewById(R.id.create_story_button);
        createStory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Create = new Intent(getActivity(), CreateStory.class);

                startActivity(Create);
                }

        });
        final Button login = view.findViewById(R.id.login_button);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), LogIn.class);
                startActivity(intent);
            }
        });
        final Button registration = view.findViewById(R.id.registration);
        registration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), RegistrationPage1.class);
                startActivity(intent);
            }
        });

    }


}
