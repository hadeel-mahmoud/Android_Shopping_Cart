package edu.cs.birzeit.android_group_assignment_1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //test
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void Login_onClick(View view) {
        Intent intent = new Intent(this, MainActivity2.class);
        startActivity(intent);
    }
}