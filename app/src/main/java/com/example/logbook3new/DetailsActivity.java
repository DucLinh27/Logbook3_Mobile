package com.example.logbook3new;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ListView;
import java.util.List;

public class DetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        ListView listView = findViewById(R.id.infoListView);

        // Retrieve the list of persons from the database and create an adapter
        ConnectDB dbHelper = new ConnectDB(this);
        List<Person> personList = dbHelper.getAllPersons(); // You need to implement getAllPersons()
        ImageView backIcon = findViewById(R.id.detailBackIcon);

        backIcon.setOnClickListener(v -> {
            Intent i = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(i);
        });

        PersonAdapter adapter = new PersonAdapter(this, personList);
        listView.setAdapter(adapter);
    }
}

