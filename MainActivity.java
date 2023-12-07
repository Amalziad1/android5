package com.example.lab4;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.view.ViewGroup;
import android.widget.Button;
import android.view.View;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    LinearLayout secondLinearLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //defined layouts here and not in xml file because they may dynamically change due to user's inputs
        LinearLayout firstLinearLayout=new LinearLayout(this);
        Button addButton =new Button(this);
        secondLinearLayout=new LinearLayout(this);
        //to display list of customers
        ScrollView scrollView=new ScrollView(this);
        //setting the orientation of the 2 layouts to vertical
        firstLinearLayout.setOrientation(LinearLayout.VERTICAL);
        secondLinearLayout.setOrientation(LinearLayout.VERTICAL);
        addButton.setText("Add Customer");
        addButton.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT));
        firstLinearLayout.addView(addButton);  //adding button to first layout
        scrollView.addView(secondLinearLayout);//adding second linear layout to scroll view
        firstLinearLayout.addView(scrollView);//add scrollview to the first linear layout
        setContentView(firstLinearLayout);//set the first linear layout as the main one
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,AddCustomer.class);
                MainActivity.this.startActivity(intent);
                finish();
            }
        });
    }
    /* getting all database
    @Override
    protected void onResume() {
        super.onResume();
        DataBaseHelper dataBaseHelper =new DataBaseHelper(MainActivity.this,"EXP4",null,1);
        Cursor allCustomersCursor = dataBaseHelper.getAllCustomers();
        secondLinearLayout.removeAllViews();
        while (allCustomersCursor.moveToNext()){
            TextView textView =new TextView(MainActivity.this);
            textView.setText( "Id= "+allCustomersCursor.getString(0)
                    +"\nName= "+allCustomersCursor.getString(1)
                    +"\nPhone= "+allCustomersCursor.getString(2)
                    +"\nGender= "+allCustomersCursor.getString(3) +"\n\n" );
            secondLinearLayout.addView(textView); }
    }*/

    @Override
    protected void onResume() {
        super.onResume();
        DataBaseHelper dataBaseHelper = new DataBaseHelper(MainActivity.this,"EXP4",null,1);
        //query that gets customers' names starting with B -part 1-
        Cursor bCustomersCursor = dataBaseHelper.getCustomersWithNameStartingWithB();

        secondLinearLayout.removeAllViews();
        //checking if there are any results
        if (bCustomersCursor != null && bCustomersCursor.moveToLast()) {
            //getting the phone number from the last result -part 2+3 -
            @SuppressLint("Range") String phoneNumber = bCustomersCursor.getString(bCustomersCursor.getColumnIndex("PHONE"));

            //adding textview to display phone number -part 4-
            TextView phoneNumberTextView = new TextView(MainActivity.this);
            phoneNumberTextView.setText("Phone number from customer with name starting with 'B': " + phoneNumber);

            secondLinearLayout.addView(phoneNumberTextView);
        } else {
            // Handle case when there are no customers with names starting with 'B'
            TextView noResultsTextView = new TextView(MainActivity.this);
            noResultsTextView.setText("No customers found with names starting with 'B'");
            secondLinearLayout.addView(noResultsTextView);
        }

        if (bCustomersCursor != null) {
            bCustomersCursor.close();
        }
    }

}