package com.example.registerandlogin2;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
public class MainActivity extends AppCompatActivity {

    // Two buttons are set up to represent login and registration options.
    Button button;
    Button button2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = (Button) findViewById(R.id.btn_register);
        button2 = (Button) findViewById(R.id.btn_login);

        // Register button brings user to registration Activity page.
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view){
                MainActivity3();

            }
        });
        // Login button brings user to Login Activity page.
        button2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view){
                MainActivity4();

            }
        });
    }
    //Intents are used to switch between Activities.
    public void MainActivity3(){
        // Switch from MainActivity to MainActivity2.
        Intent intent = new Intent(this, MainActivity2.class);
        startActivity(intent);
    }
    public void MainActivity4(){
        // Switch from MainActivity2 to MainActivity3.
        Intent intent = new Intent(this, MainActivity3.class);
        startActivity(intent);
    }
}