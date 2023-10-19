package com.example.logbook3new;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import java.time.LocalDate;

public class MainActivity extends AppCompatActivity {

    TextView dobControl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dobControl = findViewById(R.id.dob_control);

        dobControl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFragment newFragment = new DatePickerFragment();
                newFragment.show(getSupportFragmentManager(), "datePicker");
            }
        });
        Button saveDetailsButton = findViewById(R.id.saveDetailBtn);
        Button detailBtn = findViewById(R.id.detailBtn);

        saveDetailsButton.setOnClickListener(view -> saveDetails());

        detailBtn.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, DetailsActivity.class);
            startActivity(intent);
        });
    }

    // DatePicker Fragment inside MainActivity
    public static class DatePickerFragment extends DialogFragment implements
            DatePickerDialog.OnDateSetListener {
        @NonNull
        @Override
        public Dialog onCreateDialog(@Nullable Bundle savedInstanceState)
        {
            LocalDate d = LocalDate.now();
            int year = d.getYear();
            int month = d.getMonthValue();
            int day = d.getDayOfMonth();
            return new DatePickerDialog(getActivity(), this, year, --month, day);}
        @Override
        public void onDateSet(DatePicker datePicker, int year, int month, int day){
            LocalDate dob = LocalDate.of(year, ++month, day);
            ((MainActivity)getActivity()).updateDOB(dob);
        }

    }
    public void updateDOB(LocalDate dob){
        TextView dobControl = findViewById(R.id.dob_control);
        dobControl.setText(dob.toString());
    }



    private void saveDetails() {
        // Creates an object of our helper class
        ConnectDB dbHelper = new ConnectDB(this);

        // Get references to the EditText views and read their content
        EditText nameTxt = findViewById(R.id.nameInput);
        TextView dobTxt = findViewById(R.id.dob_control);
        EditText emailTxt = findViewById(R.id.emailInput);
        RadioGroup radioGroupImages = findViewById(R.id.radioGroupImages);




        // get the input values
        String name = nameTxt.getText().toString();
        String dob = dobTxt.getText().toString();
        String email = emailTxt.getText().toString();
        int selectedImgId = radioGroupImages.getCheckedRadioButtonId();
        int imageResourceId = R.drawable.ic_launcher_foreground; // default image

        if (name.isEmpty() || dob.isEmpty() || email.isEmpty()) {
            Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        // validate image selection (radio button)
        if (selectedImgId == -1) {
            // No radio button selected
            Toast.makeText(this, "Please select an image", Toast.LENGTH_SHORT).show();
            return;
        }

        if (selectedImgId == R.id.radioButton1) {
            imageResourceId = R.drawable.info_icon;
        } else if (selectedImgId == R.id.radioButton2) {
            imageResourceId = R.drawable.android_icon;
        } else if (selectedImgId == R.id.radioButton3) {
            imageResourceId = R.drawable.ios_icon;
        }

        // Calls the insertDetails method we created
        long personId = dbHelper.insertDetails(name, dob, email, imageResourceId);

        // Shows a toast with the automatically generated id
        Toast.makeText(this, "Person has been created with id: " + personId,
                Toast.LENGTH_LONG
        ).show();

        // Launch Details Activity
        Intent intent = new Intent(this, DetailsActivity.class);
        startActivity(intent);

    }
}