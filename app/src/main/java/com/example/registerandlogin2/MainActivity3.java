package com.example.registerandlogin2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity3 extends AppCompatActivity {

    EditText emailLi,passwordLi;
    Button loginButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        emailLi = findViewById(R.id.emailLi);
        passwordLi = findViewById(R.id.passwordLi);

        loginButton = findViewById(R.id.loginButton);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email=emailLi.getText().toString();
                String password=passwordLi.getText().toString();

                // Validation Function. Checks if inputs are acceptable.
                boolean verify = Validation(email,password);
                // If verified, a Toast message appears notifying the user that the registration was successful.
                // Once verified, an Intent is used to switch to a different Activity(login or register page).
                // If the verification is failed, a Toast message is displayed notifying the user.
                if(verify==true){
                    Toast.makeText(getApplicationContext(),"Registration successful",Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(MainActivity3.this,MainActivity.class);
                    startActivity(intent);
                }
                else{
                    Toast.makeText(getApplicationContext(),"Registration failed",Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
    // Validation function is used to ensure proper input format.
    private boolean Validation(String email, String password) {
        if(email.length()==0){
            emailLi.requestFocus();
            emailLi.setError("Please input proper E-mail.");
            return false;
        }
        else if(password.length()==0){
            passwordLi.requestFocus();
            passwordLi.setError("Please input E-mail.");
            return false;
        }
        else {
            return true;
        }
    }

}