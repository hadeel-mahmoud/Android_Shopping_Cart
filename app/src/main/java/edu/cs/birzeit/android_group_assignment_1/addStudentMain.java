package edu.cs.birzeit.android_group_assignment_1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;

public class addStudentMain extends AppCompatActivity {
    Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student_main);
        spinner = findViewById(R.id.spinner);
        spinner();
    }

    private void spinner() {

        ArrayList<String> cities = new ArrayList<>();
        cities.add("Ramallah");
        cities.add("Jenin");
        cities.add("Nablus");
        cities.add("Hebron");
        cities.add("Bethlehem");
        cities.add("Deir al-Balah");
        cities.add("Gaza");
        cities.add("Rafah");
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, cities);
        spinner.setAdapter(adapter);
    }
}