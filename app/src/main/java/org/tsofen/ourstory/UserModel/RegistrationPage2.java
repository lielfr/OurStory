package org.tsofen.ourstory.UserModel;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import org.tsofen.ourstory.R;

import java.io.IOException;
import java.util.UUID;


public class RegistrationPage2 extends AppCompatActivity {

    public String emailString;
    public String firstNameString;
    public String lastNameString;
    public String passwordString;

    public String stateString;
    public String cityString;

    public String dateOfBirth;
    public String dateOfSignIn;
    public String dateOfLastSignIn;
    public String profilePicture;
    public String gender;
    //will be later used as date format.

    // public Date dateOfBirth;
    // public Date dateOfSignIn;
    // public Date dateOfLastSignIn;

    public EditText EditText6;
    public EditText EditText7;
    public EditText EditText8;
    public EditText DateOfB;

    private final int PICK_IMAGE_REQUEST = 71;
    //Firebase
    FirebaseStorage storage;
    StorageReference storageReference;
    //profile picture part
    private Button chooseButton;
    private ImageView profileImageView;
    private Uri filePath;
    public static String profileImagePathUriString = null;

    Intent regIntent3;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_page2);
        Intent currIntent = getIntent();
        emailString = currIntent.getStringExtra("email");
        firstNameString = currIntent.getStringExtra("first_name");
        lastNameString = currIntent.getStringExtra("last_name");
        passwordString = currIntent.getStringExtra("password");
        DateOfB = findViewById(R.id.showDate);
        Log.d("log4", "values received from registrationPage1:"
                + emailString + " " + firstNameString + " "
                + lastNameString + " " + passwordString);

        //Initialize proifle picture Views
        chooseButton = findViewById(R.id.choose_button);
        profileImageView = findViewById(R.id.profileImageView);
        chooseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chooseImage();
            }
        });

        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();

    }


    public void showDatePicker(View view) {
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getSupportFragmentManager(), "datePicker");
    }

    public void processDatePickerResult(int year, int month, int day) {
        String month_string = Integer.toString(month + 1);
        String day_string = Integer.toString(day);
        String year_string = Integer.toString(year);
        String dateMessage = (month_string + "/" + day_string + "/" + year_string);


        TextView year1 = findViewById(R.id.year);
        year1.setText(year_string);
        TextView day1 = findViewById(R.id.day);
        day1.setText(day_string);
        TextView month1 = findViewById(R.id.month);
        month1.setText(month_string);



    }


    public void Go2RegistrationPage3andSave(View view) {
        regIntent3 = new Intent(this, LogIn.class);
        EditText6 = findViewById(R.id.showState);
        EditText7 = findViewById(R.id.showCity);
        stateString = EditText6.getText().toString();
        cityString = EditText7.getText().toString();

        regIntent3.putExtra("email", emailString);
        regIntent3.putExtra("first_name", firstNameString);
        regIntent3.putExtra("last_name", lastNameString);
        regIntent3.putExtra("password", passwordString);
        regIntent3.putExtra("state", stateString);
        regIntent3.putExtra("city", cityString);
        regIntent3.putExtra("dateOfBirth", dateOfBirth);
        regIntent3.putExtra("gender", gender);

        //Uploading the image to Firebase + passing Uri to next activity
        uploadImage(); ///goes to upload image. Next activity is started from there.


    }

    public void Go2RegistrationPage3andDontSave(View view) {
        Intent regIntent3 = new Intent(this, LogIn.class);
        regIntent3.putExtra("email", emailString);
        regIntent3.putExtra("first_name", firstNameString);
        regIntent3.putExtra("last_name", lastNameString);
        regIntent3.putExtra("password", passwordString);

        Log.d("log-not saved", "values sent to registrationPage3:"
                + emailString + " " + firstNameString + " "
                + lastNameString + " " + passwordString + " ");
        startActivity(regIntent3);
    }

    public void UploadPicture(View view) {
        //still null
    }

    public void closeActivity(View view) {
        Intent back = new Intent(this, LogIn.class);
        startActivity(back);
    }

    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();
        // Check which radio button was clicked.
        switch (view.getId()) {
            case R.id.MaleRB:
                if (checked)
                    gender = "Male";

                break;
            case R.id.FemaleRB:
                if (checked)
                    gender = "Female";
                break;

            default:
                // Do nothing.
                break;
        }
    }


    private void chooseImage() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK
                && data != null && data.getData() != null) {
            filePath = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
                profileImageView.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void uploadImage() {

        if (filePath != null) {
            ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setTitle("Uploading...");
            progressDialog.show();

            StorageReference ref = storageReference.child("images/" + UUID.randomUUID().toString());
            ref.putFile(filePath)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            Uri downloadUrl = taskSnapshot.getDownloadUrl();

                            Log.d("uri log", "the uri is " + downloadUrl.toString());
                            profileImagePathUriString = downloadUrl.toString();
                            Log.d("uri log", "the uri string is " + profileImagePathUriString);
                            progressDialog.dismiss();

                            Toast.makeText(RegistrationPage2.this, "Uploaded", Toast.LENGTH_SHORT).show();

                            profilePicture = profileImagePathUriString;
                            Log.d("profileString", "profile string is " + profilePicture);
                            regIntent3.putExtra("profilePicture", profilePicture);


                            Log.d("log-saved", "values sent to registrationPage3:"
                                    + emailString + " " + firstNameString + " "
                                    + lastNameString + " " + passwordString + " "
                                    + stateString + " " + cityString + " " + dateOfBirth + " " + gender + " " + profilePicture);
                            startActivity(regIntent3);
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            progressDialog.dismiss();
                            Toast.makeText(RegistrationPage2.this, "Failed " + e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                            double progress = (100.0 * taskSnapshot.getBytesTransferred() / taskSnapshot
                                    .getTotalByteCount());
                            progressDialog.setMessage("Uploaded " + (int) progress + "%");
                        }
                    });

        }


    }//end of upload method


}
