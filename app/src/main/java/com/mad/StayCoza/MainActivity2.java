package com.mad.StayCoza;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class MainActivity2 extends AppCompatActivity {

    RadioButton oneWayRadioButton, roundTripRadioButton;
    Button searchFlightsButton;
    EditText departureDateEditText, travelersEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        oneWayRadioButton = findViewById(R.id.oneWayRadioButton);
        roundTripRadioButton = findViewById(R.id.roundTripRadioButton);
        searchFlightsButton = findViewById(R.id.searchFlightsButton);
        departureDateEditText = findViewById(R.id.departureDateEditText);
        travelersEditText = findViewById(R.id.travelersEditText);

        oneWayRadioButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity2.this, "One-way selected", Toast.LENGTH_SHORT).show();
            }
        });

        roundTripRadioButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity2.this, "Round-trip selected", Toast.LENGTH_SHORT).show();
            }
        });

        searchFlightsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String travelers = travelersEditText.getText().toString();
                if (!TextUtils.isEmpty(travelers)) {
                    Intent intent = new Intent(MainActivity2.this, Flight2.class);
                    intent.putExtra("numTravelers", Integer.parseInt(travelers));
                    startActivity(intent);
                } else {
                    Toast.makeText(MainActivity2.this, "Please select number of travelers", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    // Method to show DatePickerDialog for departure date selection
    public void showDatePickerDialog(View view) {
        final Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                        String date = day + "/" + (month + 1) + "/" + year;
                        departureDateEditText.setText(date);
                    }
                }, year, month, day);
        datePickerDialog.show();
    }

    // Method to show dialog for selecting number of travelers
    public void showTravelersDialog(View view) {
        final CharSequence[] items = {"1", "2", "3", "4", "5"};

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Select Number of Travelers");
        builder.setItems(items, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int item) {
                // Do something with the selected item
                String selectedTravelers = items[item].toString();
                travelersEditText.setText(selectedTravelers);
                dialog.dismiss(); // Dismiss the dialog
            }
        });
        AlertDialog alert = builder.create();
        alert.show();
    }
}
