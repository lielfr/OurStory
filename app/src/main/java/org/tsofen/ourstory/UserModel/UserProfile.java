package org.tsofen.ourstory.UserModel;

import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import org.tsofen.ourstory.R;
import org.tsofen.ourstory.model.api.User;
import org.tsofen.ourstory.web.OurStoryService;
import org.tsofen.ourstory.web.WebFactory;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


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
    String userEm;
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
        if (savedInstanceState != null) {
           userEm= savedInstanceState.getString("email");
          // // userP=savedInstanceState.getParcelable("user");

                    }
        fName = getView().findViewById(R.id.showFirst);
        lName = getView().findViewById(R.id.showLast);
        dOfBirth = getView().findViewById(R.id.showState);
        gender = getView().findViewById(R.id.showGender);
        state = getView().findViewById(R.id.showState);
        city = getView().findViewById(R.id.showCity);
        pic = getView().findViewById(R.id.profilePictureImageView);
        email = getView().findViewById(R.id.showEmail);


        OurStoryService ss = WebFactory.getService();
        ss.GetUserByEmail(userEm).enqueue(new Callback<org.tsofen.ourstory.model.api.User>() {
            @Override
            public void onResponse(Call<org.tsofen.ourstory.model.api.User> call, Response<org.tsofen.ourstory.model.api.User> response) {
                profileUser = response.body();
                Toast.makeText(getContext(),profileUser.getUserId()+"",
                        Toast.LENGTH_SHORT).show();
                if (profileUser.getEmail() == null) {
                    Toast.makeText(getContext(),"This email address is invalid. Please tru a different one.",Toast.LENGTH_LONG);

                } else {
                    if(profileUser.getFirstName()!=null)
                        fName.setText(profileUser.getFirstName());
                    if(profileUser.getLastName()!=null)
                        lName.setText(profileUser.getLastName());
                    if(profileUser.getDateOfBirth()!=null)
                        dOfBirth.setText(profileUser.getDateOfBirth());
                    if(profileUser.getGender()!=null)
                        gender.setText(profileUser.getGender());
                    if(profileUser.getState()!=null)
                        state.setText(profileUser.getState());
                    if(profileUser.getState()!=null)
                        city.setText(profileUser.getState());

                        email.setText(userEm);}




                }


            @Override
            public void onFailure(Call<org.tsofen.ourstory.model.api.User> call, Throwable t) {
             Toast.makeText(getContext(),"This email address is invalid. Please enter a different one.",Toast.LENGTH_LONG);
            }


        });

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
