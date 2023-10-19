package com.example.logbook3new;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import java.util.List;

public class PersonAdapter extends ArrayAdapter<Person> {
    public PersonAdapter(Context context, List<Person> personList) {
        super(context, 0, personList);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        Person person = getItem(position);

        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item_person, parent, false);
        }

        // Lookup views within item layout
        ImageView imageView = convertView.findViewById(R.id.imageView);
        TextView textViewName = convertView.findViewById(R.id.textViewName);
        TextView textViewDob = convertView.findViewById(R.id.textViewDob);
        TextView textViewEmail = convertView.findViewById(R.id.textViewEmail);
        TextView nameDisplay = convertView.findViewById(R.id.nameDisplay);
        TextView dobDisplay = convertView.findViewById(R.id.dobDisplay);
        TextView emailDisplay = convertView.findViewById(R.id.emailDisplay);

        Button buttonDelete = convertView.findViewById(R.id.buttonDelete);
        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deletePerson(person.getName());
            }
        });


        // Populate the data into the template view using the data object
        imageView.setImageResource(person.getImageResourceId());
        nameDisplay.setText(person.getName());
        dobDisplay.setText(person.getDob());
        emailDisplay.setText(person.getEmail());

        return convertView;

    }

    public void deletePerson(String name){
        ConnectDB dbhelp = new ConnectDB(getContext());
        dbhelp.deletePerson(name);
            Toast.makeText(getContext(), "Delete new hike successful", Toast.LENGTH_SHORT).show();
    }

}

