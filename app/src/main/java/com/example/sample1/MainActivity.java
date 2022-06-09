package com.example.sample1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.google.android.material.textfield.TextInputEditText;

public class MainActivity extends AppCompatActivity {

    TextInputEditText etName, etMobileNumber;
    Button btnNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etName = findViewById(R.id.etName);
        etMobileNumber = findViewById(R.id.etMobileNumber);
        btnNext = findViewById(R.id.btnNext);

        btnNext.setOnClickListener(v -> {
            if(etName.getText().toString().isEmpty() || etName.getText().toString().length()<3) {
                etName.setError("Enter Valid Name");
            } else if(etMobileNumber.getText().toString().isEmpty() || etMobileNumber.getText().toString().length() != 10) {
                etMobileNumber.setError("Enter Valid Mobile Number");
            } else {
                startActivity(new Intent(this, NextActivity.class).putExtra("Name",etName.getText().toString()).putExtra("Mobile",etMobileNumber.getText().toString()));
                etName.setText("");
                etMobileNumber.setText("");
            }
        });

    }
}