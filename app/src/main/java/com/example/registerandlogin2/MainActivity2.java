package com.example.registerandlogin2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Calendar;

public class MainActivity2 extends AppCompatActivity {

    //Initialize the EditText values and button.
    EditText firstNameEtv,lastNameEtv,birthdayEtv,emailEtv,passwordEtv;
    Button sendRegisterBtn;

    // Bundle containing resources/compiled code.
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);


        // findViewById function used to specify view set IDs.
        firstNameEtv = findViewById(R.id.firstNameEtv);
        lastNameEtv = findViewById(R.id.lastNameEtv);
        birthdayEtv = (EditText)findViewById(R.id.birthdayEtv);
        emailEtv = findViewById(R.id.emailEtv);
        passwordEtv = findViewById(R.id.passwordEtv);

        //Format B-day input to MM/DD/YYYY.
        //Citation: Format template used from techprogrammingideas.blogspot.com
        birthdayEtv.addTextChangedListener(new TextWatcher() {
            private String current = "";
            private String ddmmyyyy = "DDMMYYYY";
            private Calendar cal = Calendar.getInstance();

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!s.toString().equals(current)) {
                    String clean = s.toString().replaceAll("[^\\d.]", "");
                    String cleanC = current.replaceAll("[^\\d.]", "");

                    int cl = clean.length();
                    int sel = cl;
                    for (int i = 2; i <= cl && i < 6; i += 2) {
                        sel++;
                    }

                    if (clean.equals(cleanC)) sel--;

                    if (clean.length() < 8){
                        clean = clean + ddmmyyyy.substring(clean.length());
                    }else{

                        //Correct the date
                        int day  = Integer.parseInt(clean.substring(0,2));
                        int mon  = Integer.parseInt(clean.substring(2,4));
                        int year = Integer.parseInt(clean.substring(4,8));

                        if(mon > 12) mon = 12;
                        cal.set(Calendar.MONTH, mon-1);

                        year = (year<1900)?1900:(year>2100)?2100:year;
                        cal.set(Calendar.YEAR, year);

                        day = (day > cal.getActualMaximum(Calendar.DATE))? cal.getActualMaximum(Calendar.DATE):day;
                        clean = String.format("%02d%02d%02d",day, mon, year);
                    }

                    clean = String.format("%s/%s/%s", clean.substring(0, 2),
                            clean.substring(2, 4),
                            clean.substring(4, 8));

                    sel = sel < 0 ? 0 : sel;
                    current = clean;
                    birthdayEtv.setText(current);
                    birthdayEtv.setSelection(sel < current.length() ? sel : current.length());

                }
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void afterTextChanged(Editable s) {}
        });

        // findViewById function used to specify button set IDs.
        sendRegisterBtn = findViewById(R.id.sendRegisterBtn);

        // setOnClickListener function called to set HTTP request to HTTP response.
        sendRegisterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Set values to Strings.
                String firstname=firstNameEtv.getText().toString();
                String lastname=lastNameEtv.getText().toString();
                String birthday=birthdayEtv.getText().toString();
                String email=emailEtv.getText().toString();
                String password=passwordEtv.getText().toString();

                // Validation Function. Checks if inputs are acceptable.
                boolean verify = Validation(firstname,lastname,birthday,email,password);
                // If verified, a Toast message appears notifying the user that the registration was successful.
                // Once verified, an Intent is used to switch to a different Activity(login or register page).
                // If the verification is failed, a Toast message is displayed notifying the user.
                if(verify==true){
                    Toast.makeText(getApplicationContext(),"Registration successful",Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(MainActivity2.this,MainActivity.class);
                    startActivity(intent);
                }
                else{
                    Toast.makeText(getApplicationContext(),"Registration failed",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    // Validation function is set up.
    private Boolean Validation(String firstname, String lastname, String birthday, String email, String password) {
        // Constraints are put in to organize the registration process.
        // All fields are checked to see if they are left empty.
        // If so, prompt to re-enter information is displayed.

        // First name should be at least 3 characters long and no more than 30 characters.
        if(firstname.length()==0){
            firstNameEtv.requestFocus();
            firstNameEtv.setError("Please input first name.");
            return false;
        }
        else if(firstname.length()<=2){
            firstNameEtv.requestFocus();
            firstNameEtv.setError("First name must be at least 3 characters.");
            return false;
        }
        else if(firstname.length()>=31){
            firstNameEtv.requestFocus();
            firstNameEtv.setError("First name cannot exceed 30 characters.");
            return false;
        }
        else if(lastname.length()==0){
            firstNameEtv.requestFocus();
            firstNameEtv.setError("Please input last name.");
            return false;
        }
        else if(birthday.length()==0){
            firstNameEtv.requestFocus();
            firstNameEtv.setError("Please input birthday.");
            return false;
        }
        // Birthday input should be 8 characters long.
        else if(birthday.length()<=7){
            firstNameEtv.requestFocus();
            firstNameEtv.setError("Please input proper birthday.");
            return false;
        }
        else if(email.length()==0){
            emailEtv.requestFocus();
            emailEtv.setError("Please input E-mail.");
            return false;
        }
        else if(password.length()==0){
            emailEtv.requestFocus();
            emailEtv.setError("Please input Password.");
            return false;
        }
        else {
            return true;
        }
    }
}