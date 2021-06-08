package edu.cs.birzeit.android_group_assignment_1;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RecyclerViewActivity extends AppCompatActivity {

    private final List<Student> studentsList = new ArrayList<>();
    RecyclerviewItemAdapter recyclerviewItemAdapter;
    RecyclerView recyclerView;
    EditText searchText;

    Student[]  studentsArray = new Student[2];

    List<Student> list = Arrays.asList(studentsArray);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        studentsArray[0] = new Student("HADEEL", "NEZ", "@gmail","DOB", "ADDRESS", "20", "FEMALE");
        studentsArray[1] = new Student("LENA", "NEZ", "@gmail","DOB", "ADDRESS", "20", "FEMALE");

        prepareItems(false,"");

    }

//    public void onClickAdd(View view) {
//
//        Intent intent = new Intent(this, addStudent.class);
//        startActivity(intent);
//
//
//    }

    public void searchClick(View view) {
        searchText = (EditText) findViewById(R.id.searchEdt);
//        System.out.println(searchText + "SENT");
        prepareItems( true,searchText.getText().toString());
    }

    private void prepareItems( boolean search,String searchContent) {
        int i;

        if (!search) {

            recyclerviewItemAdapter = new RecyclerviewItemAdapter(list);
            recyclerView = findViewById(R.id.recycleView);
            recyclerView.setHasFixedSize(true);
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
            recyclerView.setLayoutManager(layoutManager);
        }
        else {
            List<Student> searchStudentsList = new ArrayList<>();

            for (Student student : list) {
                if (student.getFirstName().toLowerCase().contains(searchContent.toLowerCase())) {
                    System.out.println(student.getFirstName() + "NAME");
                    Student searchedStudent = new Student(student.getFirstName(), student.getLastName(), student.getEmail(), student.getDateOfBirth(), student.getAddress(), student.getGrade(), student.getGender());
                    searchStudentsList.add(searchedStudent);
                    System.out.println(searchedStudent + "ADDED");

                }
            }
            recyclerviewItemAdapter = new RecyclerviewItemAdapter(searchStudentsList);
            recyclerView = findViewById(R.id.recycleView);
            recyclerView.setHasFixedSize(true);
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
            recyclerView.setLayoutManager(layoutManager);

        }
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(recyclerviewItemAdapter);


    }
}