package org.tsofen.ourstory.UserModel;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.tsofen.ourstory.R;
import org.tsofen.ourstory.model.api.User;
import org.tsofen.ourstory.web.OurStoryService;
import org.tsofen.ourstory.web.WebFactory;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ForgotPassword extends AppCompatActivity {

    public String emailString;
    public String newPass;
    public EditText EditText1;
    public EditText EditText2;
    public TextView TextViewInvs1;
    public TextView TextViewInvs2;
    public  TextView ErrorText;
    public View holderpass;
    public Button save;
    public User currUser;
    boolean flag=true;
    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate( Bundle savedInstanceState ) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        TextViewInvs1 = findViewById(R.id.textView1);
        TextViewInvs1.setVisibility(View.VISIBLE);
        EditText1 = findViewById(R.id.email);
        TextViewInvs2 = findViewById(R.id.textView2);
        TextViewInvs1 = findViewById(R.id.textView1);
        ErrorText=findViewById(R.id.EmailError);
        holderpass=findViewById(R.id.holder3);
        save=findViewById(R.id.savechange);
    }
    public void getPass(View view)
    {
        emailString = EditText1.getText().toString();
        if(emailString.length()==0)
        {
            ErrorText.setText("You must enter an email to get a new password..");
        }

        else {
            OurStoryService findUser = WebFactory.getService();
            findUser.GetUserByEmail(emailString).enqueue(new Callback<User>() {
                @Override
                public void onResponse(Call<User> call, Response<User> response) {
                    OurStoryService forgetPass = WebFactory.getService();
                    forgetPass.resetPassword(emailString).enqueue(new Callback<Object>() {
                        @Override
                        public void onResponse( Call<Object> call, Response<Object> response ) {


                        }

                        @Override
                        public void onFailure( Call<Object> call, Throwable t ) {


                        }
                    });

                }

                @Override
                public void onFailure(Call<User> call, Throwable t) {
                    ErrorText.setText("Enter a valid email!");
                    flag=false;

                }
            });


        }
        if(flag==true){
        TextViewInvs2.setVisibility(View.VISIBLE);
        holderpass.setVisibility(View.VISIBLE);
        save.setVisibility(View.VISIBLE);}

    }
    public void gotoLogIn(View view)
    {
        EditText2 = findViewById(R.id.NewPass);
        newPass = EditText2.getText().toString();
        Intent intent=new Intent(this,LogIn.class);
        startActivity(intent);
    }

    public void closeActivity( View view)
    {
        Intent intent=new Intent(this,LogIn.class);
        startActivity(intent);
    }
}
