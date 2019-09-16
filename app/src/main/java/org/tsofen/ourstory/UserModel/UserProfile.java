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

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.gson.Gson;

import org.tsofen.ourstory.R;
import org.tsofen.ourstory.model.api.User;

import java.util.Date;


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

    User profileUser;

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

        fName = getView().findViewById(R.id.showFirst);
        lName = getView().findViewById(R.id.showLast);
        dOfBirth = getView().findViewById(R.id.showDate);
        gender = getView().findViewById(R.id.showGender);
        state = getView().findViewById(R.id.showState);
        city = getView().findViewById(R.id.showCity);
        pic = getView().findViewById(R.id.profilePictureImageView);
        email = getView().findViewById(R.id.showEmail);

        Gson gson = new Gson();
        String userJsonString = LogIn.mPrefs.getString("myUser","");
        User profileUser = gson.fromJson(userJsonString,User.class);


        if (profileUser.getFirstName() != null)
            fName.setText(profileUser.getFirstName());
        if (profileUser.getLastName() != null)
            lName.setText(profileUser.getLastName());
        if (profileUser.getDateOfBirth() != null) {
           Date date = profileUser.getDateOfBirth();
            dOfBirth.setText(profileUser.getDateOfBirth().toString());
        }
        if (profileUser.getGender() != null)
            gender.setText(profileUser.getGender());
        if (profileUser.getState() != null)
            state.setText(profileUser.getState());
        if (profileUser.getState() != null)
            city.setText(profileUser.getCity());
        if (profileUser.getEmail() != null)
            email.setText(profileUser.getEmail());
        if(profileUser.getProfilePicture() != null)
        {pictureUri = Uri.parse(profileUser.getProfilePicture());
        RequestOptions options = new RequestOptions()
                .centerCrop()
                .placeholder(R.drawable.defaultprofilepicture)
                .error(R.drawable.defaultprofilepicture);


        Glide.with(this).load(pictureUri).apply(options).into(pic);}
    }
}



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
