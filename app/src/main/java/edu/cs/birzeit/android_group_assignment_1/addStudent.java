package edu.cs.birzeit.android_group_assignment_1;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;


public class addStudent extends AppCompatActivity {

    EditText firstName,lastName, email,grade,DateOfBirth ;

    Spinner spinner;
    CheckBox courses;
    private CheckBox math, science, arabic,english;
    private RadioGroup radioGroup;
    private RadioButton radioButton;
    String firstNameTemp,lastNameTemp, emailTemp,gradeTemp,DateOfBirthTemp,genderTemp,spinnerTemp;
    String coursesTemp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student);
        setUpViews();


    }

    private void setUpViews() {
        firstName = (EditText)findViewById(R.id.ed_firstname);
        lastName = (EditText)findViewById(R.id.ed_lastname);
        email = (EditText)findViewById(R.id.ed_email);
        grade = (EditText)findViewById(R.id.grade);
        DateOfBirth = (EditText)findViewById(R.id.ed_dateOfbirth);

        radioGroup = (RadioGroup) findViewById(R.id.radio);
        int selectedId = radioGroup.getCheckedRadioButtonId();
        radioButton = (RadioButton) findViewById(selectedId);

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


    private String processRequest(String restUrl) throws UnsupportedEncodingException {
        firstNameTemp = firstName.getText().toString();
        lastNameTemp = lastName.getText().toString();
        emailTemp = email.getText().toString();
        gradeTemp = grade.getText().toString();
        DateOfBirthTemp = DateOfBirth.getText().toString();
        genderTemp=radioButton.getText().toString();
        spinnerTemp= spinner.getSelectedItem().toString();
        System.out.println("DATA:"+firstNameTemp+lastNameTemp+emailTemp+gradeTemp+DateOfBirthTemp+genderTemp+spinnerTemp);

        String data = URLEncoder.encode("firstName", "UTF-8")
                + "=" + URLEncoder.encode(firstNameTemp, "UTF-8");

        data += "&" + URLEncoder.encode("lastName", "UTF-8") + "="
                + URLEncoder.encode(lastNameTemp, "UTF-8");

        data += "&" + URLEncoder.encode("email", "UTF-8")
                + "=" + URLEncoder.encode(emailTemp, "UTF-8");

        data += "&" + URLEncoder.encode("grade", "UTF-8")
                + "=" + URLEncoder.encode(gradeTemp, "UTF-8");


        data += "&" + URLEncoder.encode("DateOfBirth", "UTF-8")
                + "=" + URLEncoder.encode(DateOfBirthTemp, "UTF-8");

        data += "&" + URLEncoder.encode("gender", "UTF-8")
                + "=" + URLEncoder.encode(genderTemp, "UTF-8");

        data += "&" + URLEncoder.encode("address", "UTF-8")
                + "=" + URLEncoder.encode(spinnerTemp, "UTF-8");
        System.out.println("ENCODED DATA:"+data);
        String text = "";
        BufferedReader reader=null;

        // Send data
        try
        {

            // Defined URL  where to send data
            URL url = new URL(restUrl);

            // Send POST data request

            URLConnection conn = url.openConnection();
            conn.setDoOutput(true);
            OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
            wr.write( data );
            wr.flush();

            // Get the server response

            reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder sb = new StringBuilder();
            String line = "";

            // Read Server Response
            while((line = reader.readLine()) != null)
            {
                // Append server response in string
                sb.append(line + "\n");
            }

            System.out.println("IN TRY STMT");

            text = sb.toString();
        }
        catch(Exception ex)
        {
            System.out.println("IN CATCH"+ex.getMessage());

            ex.printStackTrace();
        }
        finally
        {
            try
            {

                reader.close();
            }

            catch(Exception ex) {
                ex.printStackTrace();
            }
        }

        // Show response on activity
        return text;



    }

    private class SendPostRequest extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... urls) {
            try {
                return processRequest(urls[0]);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
                System.out.println("NOT SUCCESSFULL REQUEST:"+e.getMessage());

            }
            return "";
        }
        @Override
        protected void onPostExecute(String result) {

            Toast.makeText(addStudent.this, result, Toast.LENGTH_SHORT).show();
        }
    }

    public void addStd_onclick(View view) {

        String restUrl = "http://192.168.0.102/rest/students_table2.php";
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.INTERNET)
                != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.INTERNET},
                    123);

        } else{
            SendPostRequest runner = new SendPostRequest();
            runner.execute(restUrl);
        }

    }
}