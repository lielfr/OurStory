package org.tsofen.ourstory.UserModel;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;

import org.tsofen.ourstory.R;
import org.tsofen.ourstory.StoryTeam.CreateStory;
import org.tsofen.ourstory.StoryTeam.SearchStory;


public class HomeFragment extends Fragment {

    AppHomePage parent;

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
                startActivity(intent);
            }
        });

        final Button createStory = view.findViewById(R.id.create_story_button);
        createStory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Create = new Intent(getActivity(), CreateStory.class);
                Intent Login = new Intent(getActivity(), LogIn.class);
                Login.putExtra("tybe", "ComingFromCreate");
                if (UserStatusCheck.getUserStatus().equals("visitor")) {
                    Toast.makeText(getContext(), "you need to Log in In Order to Create Story ", Toast.LENGTH_SHORT).show();
                    startActivity(Login);
                } else {
                    Create.putExtra("tybe", UserStatusCheck.getUserStatus());
                    startActivity(Create);
                }
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
