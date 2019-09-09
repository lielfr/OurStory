package org.tsofen.ourstory.UserModel;

import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import org.tsofen.ourstory.R;
import org.tsofen.ourstory.model.api.User;


public class UserProfile extends Fragment {

    public String userIndex;
    AppHomePage parent;
    int userIn;
    ImageView pic;
    TextView fName;
    TextView lName;
    TextView dOfBirth;
    TextView gender;
    TextView state;
    TextView city;
    TextView email;
    Uri pictureUri;
    User userP;
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
         //   userIn = savedInstanceState.getInt("index");
            //userP=savedInstanceState.getParcelable("user");

                    }

         fName = getView().findViewById(R.id.showFirst);
        lName = getView().findViewById(R.id.showLast);
        dOfBirth = getView().findViewById(R.id.showState);
        gender = getView().findViewById(R.id.showGender);
        state = getView().findViewById(R.id.showState);
        city = getView().findViewById(R.id.showCity);
        pic = getView().findViewById(R.id.profilePictureImageView);
        email = getView().findViewById(R.id.showEmail);
     /*  if(userP.getFirstName()!=null)
        fName.setText(userP.getFirstName());
        if(userP.getLastName()!=null)
        lName.setText(userP.getLastName());
        if(userP.getDateOfBirth()!=null)
        dOfBirth.setText(userP.getDateOfBirth());
        if(userP.getGender()!=null)
        gender.setText(userP.getGender());
        if(userP.getState()!=null)
        state.setText(userP.getState());
        if(userP.getState()!=null)
        city.setText(userP.getState());
        if(userP.getEmail()!=null)
        email.setText(userP.getEmail());
        /*fName.setText(UsersList.usersList.get(userIn).getmFirstName());

        lName.setText(UsersList.usersList.get(userIn).getmLastName());

        dOfBirth.setText(UsersList.usersList.get(userIn).getmDateOfBirth());

        gender.setText(UsersList.usersList.get(userIn).getmGender());

        state.setText(UsersList.usersList.get(userIn).getmState());
        city.setText(UsersList.usersList.get(userIn).getmCity());

        email.setText(UsersList.usersList.get(userIn).getmEmail());

         pictureUri = Uri.parse(UsersList.usersList.get(userIn).getmProfilePicture());
        RequestOptions options = new RequestOptions()
                .centerCrop()
                .placeholder(R.drawable.defaultprofilepicture)
                .error(R.drawable.defaultprofilepicture);


        Glide.with(this).load(pictureUri).apply(options).into(pic);*/


    }
}
