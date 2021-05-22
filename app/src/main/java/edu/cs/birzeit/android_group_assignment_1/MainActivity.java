package edu.cs.birzeit.android_group_assignment_1;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;

public class MainActivity extends AppCompatActivity {
    JSONArray jsonArray=null;

    EditText editEmail, editPassword;
    String URL = "https://10.0.2.2/rest/login.php?email=nez.hadeel@gmail.com&userPassword=123456789";
    String finalResult=null;
    Intent intent;

    //192.168.0.102
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editEmail = (EditText) findViewById(R.id.editEmail);
        editPassword = (EditText) findViewById(R.id.editPassword);

    }

    public void signIn_onClick(View view) {

        String url =  "http://192.168.0.102:80/rest/login.php?email="+editEmail.getText()+"&userPassword="+editPassword.getText();
//                "https://10.0.2.2/rest/login.php?email=" +editEmail.getText()+"&userPassword="+editPassword.getText();
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.INTERNET)
                != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.INTERNET},
                    123);

        } else{
            login runner = new login();
             intent= new Intent(this, MainActivity2.class);
            runner.execute(url);
        }

    }
    private InputStream OpenHttpConnection(String urlString) throws IOException
    {
        InputStream in = null;
        int response = -1;

        URL url = new URL(urlString);
        URLConnection conn = url.openConnection();
        System.out.println("urlString");

        System.out.println(urlString);

        if (!(conn instanceof HttpURLConnection))
            throw new IOException("Not an HTTP connection");
        try{
            System.out.println("HTTP connection");
            HttpURLConnection httpConn = (HttpURLConnection) conn;
            httpConn.setAllowUserInteraction(false);
            httpConn.setInstanceFollowRedirects(true);
            httpConn.setRequestMethod("GET");
            httpConn.connect();
            response = httpConn.getResponseCode();
            if (response == HttpURLConnection.HTTP_OK) {
                System.out.println("IF HTTP connection");

                in = httpConn.getInputStream();

            }
        }
        catch (Exception ex)
        {
            System.out.println("entered ex"+ex.getMessage());

            Log.d("Networking", ex.getLocalizedMessage());
            throw new IOException("Error connecting");
        }
        System.out.println("in");

        System.out.println(in.toString());

        return in;
    }
    private String DownloadText(String URL)
    {
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
            while ((charRead = isr.read(inputBuffer))>0) {
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
//
//    public void btnOpenOnClick(View view) {
//        Intent intent = new Intent(this, SecondActivity.class);
//        startActivity(intent);
//    }

    private class login extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... urls) {
            return DownloadText(urls[0]);
        }
        @Override
        protected void onPostExecute(String result) {
            finalResult=result;
            System.out.println("result");

            if(result.equals("true")){
                startActivity(intent);
            }else{
                Toast.makeText(getBaseContext(), "Wrong credentials ", Toast.LENGTH_LONG).show();

            }

            System.out.println(result);

        }
    }}