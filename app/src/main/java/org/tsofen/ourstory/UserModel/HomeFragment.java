package org.tsofen.ourstory.UserModel;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;

import org.tsofen.ourstory.R;


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

            }
        });

        final Button createStory = view.findViewById(R.id.create_story_button);
        createStory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder myAlertBuilder = new
                        AlertDialog.Builder(getActivity());
                // Set the dialog title and message.
                myAlertBuilder.setTitle("OOPS!!");
                myAlertBuilder.setMessage("Hello visitor; You must login to create a story for a dear one!!");
                // Add the dialog buttons.
                myAlertBuilder.setPositiveButton("Login", new
                        DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {

                                Intent intent = new Intent(getActivity(), LogIn.class);
                                startActivity(intent);
                            }
                        });
                myAlertBuilder.setNegativeButton("Cancel", new
                        DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {


                            }
                        });
                //Create and show the AlertDialog.
                myAlertBuilder.show();

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
