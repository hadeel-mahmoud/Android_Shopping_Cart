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
import java.util.List;

public class MainActivity2 extends AppCompatActivity {

    private final List<Student> studentsList = new ArrayList<>();
    RecyclerviewItemAdapter recyclerviewItemAdapter;
    RecyclerView recyclerView;
    EditText searchText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        String url = "http://192.168.0.102/rest/get_all.php";
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.INTERNET)
                != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.INTERNET},
                    123);

        } else {
            MainActivity2.getStudents runner = new MainActivity2.getStudents();
            runner.execute(url);
        }

    }

    public void onClickAdd(View view) {

        Intent intent = new Intent(this, addStudent.class);
        startActivity(intent);


    }

    public void searchClick(View view) {

//        Intent intent=new Intent(this, addStudentMain.class);
//        startActivity(intent);
        searchText = (EditText) findViewById(R.id.searchEdt);
        System.out.println(searchText + "SENT");

        prepareItems(searchText.getText().toString(), true);
        System.out.println();


    }

    private InputStream OpenHttpConnection(String urlString) throws IOException {
        InputStream in = null;
        int response = -1;

        URL url = new URL(urlString);
        URLConnection conn = url.openConnection();

        if (!(conn instanceof HttpURLConnection))
            throw new IOException("Not an HTTP connection");
        System.out.println("Not an HTTP connection");
        try {
            HttpURLConnection httpConn = (HttpURLConnection) conn;
            httpConn.setAllowUserInteraction(false);
            httpConn.setInstanceFollowRedirects(true);
            httpConn.setRequestMethod("GET");
            httpConn.connect();
            response = httpConn.getResponseCode();
            if (response == HttpURLConnection.HTTP_OK) {
                in = httpConn.getInputStream();
                System.out.println("connected");
            }
            System.out.println("GOOD");

        } catch (Exception ex) {
            System.out.println("Error connecting" + ex.getLocalizedMessage() + ex.getMessage());


            Log.d("Networking", ex.getLocalizedMessage());
            throw new IOException("Error connecting");
        }

        return in;
    }

    private String DownloadText(String URL) {
        int BUFFER_SIZE = 2000;
        InputStream in = null;
        try {
            in = OpenHttpConnection(URL);
        } catch (IOException e) {
            Log.d("Networking", e.getLocalizedMessage());
            return "";
        }

        InputStreamReader isr = new InputStreamReader(in);
        int charRead;
        String str = "";
        char[] inputBuffer = new char[BUFFER_SIZE];
        try {
            while ((charRead = isr.read(inputBuffer)) > 0) {
                //---convert the chars to a String---
                String readString =
                        String.copyValueOf(inputBuffer, 0, charRead);
                str += readString;
                inputBuffer = new char[BUFFER_SIZE];
            }
            in.close();
        } catch (IOException e) {
            Log.d("Networking", e.getLocalizedMessage());
            return "";
        }
        return str;
    }

    private class getStudents extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... urls) {
            return DownloadText(urls[0]);
        }

        @Override
        protected void onPostExecute(String result) {
            System.out.println("result" + result);
            System.out.println(result);


            prepareItems(result, false);


        }
    }

    private void prepareItems(String jsonObject, boolean search) {
        int i;

        if (!search) {
            Gson gson = new Gson();
            Type type = new TypeToken<List<Student>>() {
            }.getType();
            List<Student> contactList = gson.fromJson(jsonObject, type);
            for (Student students : contactList) {
                Student student = new Student(students.getFirstName(), students.getLastName(), students.getEmail(), students.getDateOfBirth(), students.getAddress(), students.getGrade(), students.getGender());
                studentsList.add(student);
            }
            recyclerviewItemAdapter = new RecyclerviewItemAdapter(studentsList);
            recyclerView = findViewById(R.id.recycleView);
            recyclerView.setHasFixedSize(true);
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
            recyclerView.setLayoutManager(layoutManager);
            recyclerView.setItemAnimator(new DefaultItemAnimator());
            recyclerView.setAdapter(recyclerviewItemAdapter);
        } else {
            List<Student> searchStudentsList = new ArrayList<>();
            System.out.println(jsonObject + "Initial List");
            for (Student student : studentsList) {
                if (student.getFirstName().contains(jsonObject)) {
                    System.out.println(student.getFirstName() + "NAME");
                    Student searchedStudent = new Student(student.getFirstName(), student.getLastName(), student.getEmail(), student.getDateOfBirth(), student.getAddress(), student.getGrade(), student.getGender());
                    searchStudentsList.add(searchedStudent);
                    System.out.println(searchedStudent + "ADDED");

                }
            }
            System.out.println(searchStudentsList.toString() + "SEARCH LIST");
//            studentsList.removeAll(studentsList);
//            studentsList=searchStudentsList;
            recyclerviewItemAdapter = new RecyclerviewItemAdapter(searchStudentsList);
            recyclerView = findViewById(R.id.recycleView);
            recyclerView.setHasFixedSize(true);
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
            recyclerView.setLayoutManager(layoutManager);
            recyclerView.setItemAnimator(new DefaultItemAnimator());
            recyclerView.setAdapter(recyclerviewItemAdapter);

        }


    }
}