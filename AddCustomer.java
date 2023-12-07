package com.example.lab4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class AddCustomer extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_customer);
        //setting the spinner
        String[] options = { "Male", "Female" };
        final Spinner genderSpinner =(Spinner) findViewById(R.id.spinner_Gender);
        ArrayAdapter<String> objGenderArr = new ArrayAdapter<>(this,android.R.layout.simple_spinner_item, options);
        genderSpinner.setAdapter(objGenderArr);
        Button button = (Button)findViewById(R.id.button_Add_Customer);
        button.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View view) {
                int flag=1;// 0 for not ready to save, 1 ready to save
                EditText id=(EditText)findViewById(R.id.id);
                EditText name=(EditText)findViewById(R.id.name);
                EditText phone=(EditText)findViewById(R.id.phone);
                //validate emptiness
                if (id.getText().toString().isEmpty() || name.getText().toString().isEmpty() || phone.getText().toString().isEmpty()){
                    Toast.makeText(AddCustomer.this, "some fields are empty", Toast.LENGTH_SHORT).show();
                    flag=0;
                }
                //validate phone if it is long and only numbers with 10 digits
                String phoneText = phone.getText().toString().trim();
                int length = 10;
                if (!phoneText.matches("\\d+") && phoneText.length() != length) {
                    Toast.makeText(AddCustomer.this, "Invalid phone number", Toast.LENGTH_SHORT).show();
                    flag=0;
                }
                //validate id
                long idNumber=0;
                try {
                    idNumber = Long.parseLong(id.getText().toString());
                } catch (NumberFormatException e) {
                    Toast.makeText(AddCustomer.this, "Invalid id", Toast.LENGTH_SHORT).show();
                    flag=0;
                }
                if (flag==1){
                    Customer customer=new Customer(idNumber,name.getText().toString(),phoneText,genderSpinner.getSelectedItem().toString());
                    DataBaseHelper dataBaseHelper = new DataBaseHelper(AddCustomer.this,"EXP4",null,1);
                    dataBaseHelper.insertCustomer(customer);
                    Toast.makeText(AddCustomer.this, "Customer added successfully", Toast.LENGTH_SHORT).show();
                    //opening another ui
                    Intent intent = new Intent(AddCustomer.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        }
        );
    }
}