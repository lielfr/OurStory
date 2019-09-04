package org.tsofen.ourstory;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class YearActivity extends AppCompatActivity {

    // Unique tag required for the intent extra
    public static final String EXTRA_MESSAGE = "org.tsofen.ourstory.memories.extra.MESSAGE";
    private static final String LOG_TAG = MemoriesOfStoryActivity.class.getSimpleName();
    EditText year, name, creator_name;

    // Unique tag for the intent reply
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_year);
        year = findViewById(R.id.year);
        name = findViewById(R.id.nametxt);
        creator_name = findViewById(R.id.name_memory);

    }

    public void launchMemoriesActivity(View view) {
        if (year.getText().toString().length() == 0 || name.getText().toString().length() == 0) {
            {
                AlertDialog.Builder myAlertBuilder = new
                        AlertDialog.Builder(YearActivity.this);
                myAlertBuilder.setTitle(getString(R.string.alert_title));
                myAlertBuilder.setMessage(getString(R.string.alert_message));
                // Add the dialog buttons.
                myAlertBuilder.setPositiveButton("OK", new
                        DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // User clicked OK button.
                                Toast.makeText(getApplicationContext(), "Pressed OK",
                                        Toast.LENGTH_SHORT).show();
                            }
                        });
                myAlertBuilder.setNegativeButton("Cancel", new
                        DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // User cancelled the dialog.
                                Toast.makeText(getApplicationContext(), "Pressed Cancel",
                                        Toast.LENGTH_SHORT).show();
                            }
                        });
                myAlertBuilder.show();
            }

        } else {
            Log.d(LOG_TAG, "Button clicked!");

            Intent intent = new Intent(this, MemoriesOfStoryActivity.class);
            String message = year.getText().toString();
            message += " " + name.getText().toString();

            intent.putExtra(EXTRA_MESSAGE, message);
            startActivity(intent);
        }
    }

    public void launchMyMermoriesActivity(View view) {
        Log.d(LOG_TAG, "Button clicked!");

        Intent intent = new Intent(this, MyMemoriesActivity.class);
        String message = creator_name.getText().toString();
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
    }
}
