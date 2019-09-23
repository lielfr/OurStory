package org.tsofen.ourstory.UserModel;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
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

import java.text.SimpleDateFormat;
import java.util.Date;

import static android.content.Context.MODE_PRIVATE;


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
    Activity ac;
    Intent in;
    User profileUser;
    String is_checked;
    String user="";
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

        ac=getActivity();
        in=ac.getIntent();
        Gson gson = new Gson();
        SharedPreferences pr = getContext().getSharedPreferences(getString(R.string.shared_pref_key), MODE_PRIVATE);
        if(pr.getString(AppHomePage.USER,"")!="")
        {
            user=pr.getString(AppHomePage.USER,"");
        }
        else {
            user=in.getStringExtra("myUserJson");

        }
        //SharedPreferences pr=getContext().getSharedPreferences(AppHomePage.KEY_SELECTED,MODE_PRIVATE);
        //String userJsonString = pr.getString(AppHomePage.USER,"");
        if(user!=""){
        User profileUser = gson.fromJson(user,User.class);


        if (profileUser.getFirstName() != null)
            fName.setText(profileUser.getFirstName());
        if (profileUser.getLastName() != null)
            lName.setText(profileUser.getLastName());
        if (profileUser.getDateOfBirth() != null) {
            Date date = profileUser.getDateOfBirth();
            String pattern = "yyyy-MM-dd";
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
            String userDate = simpleDateFormat.format(date);
            dOfBirth.setText(userDate);
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
    }}
}



