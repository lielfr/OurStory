package org.tsofen.ourstory.UserModel;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import org.tsofen.ourstory.R;


public class UserProfile extends Fragment {

    public String userIndex;
    AppHomePage parent;
    int userIn;

    public UserProfile() {
        super();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        parent = (AppHomePage) getActivity();
        return inflater.inflate(R.layout.activity_user_profile, container, false);


    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // This is a little trick to make the search bar clickable and not just the icon in it.
        if (savedInstanceState != null) {
            int index = savedInstanceState.getInt("index");

            userIn = index;
        }
        TextView fName = getView().findViewById(R.id.showFirst);
        fName.setText(UsersList.usersList.get(userIn).getmFirstName());
        TextView lName = getView().findViewById(R.id.showLast);
        lName.setText(UsersList.usersList.get(userIn).getmLastName());
        TextView dOfBirth = getView().findViewById(R.id.showState);
        dOfBirth.setText(UsersList.usersList.get(userIn).getmDateOfBirth());
        TextView gender = getView().findViewById(R.id.showGender);
        gender.setText(UsersList.usersList.get(userIn).getmGender());
        TextView state = getView().findViewById(R.id.showState);
        state.setText(UsersList.usersList.get(userIn).getmState());
        TextView city = getView().findViewById(R.id.showCity);
        city.setText(UsersList.usersList.get(userIn).getmCity());
        TextView email = getView().findViewById(R.id.showEmail);
        email.setText(UsersList.usersList.get(userIn).getmEmail());
        TextView date = getView().findViewById(R.id.showDate);
        date.setText(UsersList.usersList.get(userIn).getmDateOfBirth());


    }
}
