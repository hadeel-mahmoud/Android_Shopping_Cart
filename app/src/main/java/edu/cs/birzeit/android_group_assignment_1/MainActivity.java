package edu.cs.birzeit.android_group_assignment_1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    EditText editEmail, editPassword;
    Button btnSignIn;

   // String URL = "https://10.0.3.2/rest/users.php";



    int i = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editEmail = (EditText) findViewById(R.id.editEmail);
        editPassword = (EditText) findViewById(R.id.editPassword);



    }

    public void signIn_onClick(View view) {

        Intent intent = new Intent(this, MainActivity2.class);
        startActivity(intent);
    }
}
