package com.mad.StayCoza;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.app.Activity;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class contact1 extends AppCompatActivity {

    private Button addButton;
    private Button selectSeatButton;
    private CheckBox travelInsuranceCheckbox;
    private TextView baggageOption;
    private TextView totalCostLabel;
    private EditText emailEditText;
    private EditText phoneNumberEditText;
    private TextView emailText;
    private TextView phoneNumberText;
    private EditText nameEditText;

    private int selectedKg = 0;
    private boolean isInsuranceChecked = false;
    private int total = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contact1);

        addButton = findViewById(R.id.addButton);
        selectSeatButton = findViewById(R.id.selectSeatButton);
        selectSeatButton.setBackgroundColor(Color.parseColor("#4CAF50"));
        travelInsuranceCheckbox = findViewById(R.id.travelInsuranceCheckbox);

        baggageOption = findViewById(R.id.baggageOption);
        totalCostLabel = findViewById(R.id.totalCostLabel);
        emailEditText = findViewById(R.id.email);
        phoneNumberEditText = findViewById(R.id.phone_number1);
        emailText = findViewById(R.id.email_text);
        phoneNumberText = findViewById(R.id.phone_number);
        nameEditText = findViewById(R.id.name);
        Button editButton = findViewById(R.id.edit_button);

        // Retrieve flight price passed from Flight2 activity
        double flightPrice = getIntent().getDoubleExtra("flight_price", 0);

        // Add flight price to subtotal and update total cost label
        total += flightPrice;
        updateSubtotal();

        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(contact1.this, contact2.class);
                startActivity(intent);
            }
        });

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showBaggageDialog();
            }
        });

        ActivityResultLauncher<Intent> seatActivityResultLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        Intent data = result.getData();
                        total = data.getIntExtra("result", 0); // Retrieve the final total
                        updateSubtotal();

                    }
                });

        selectSeatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int baggagePrice = selectedKg * 100;
                int totalWithBaggageAndInsurance = total + baggagePrice + (isInsuranceChecked ? 350 : 0);
                Intent intent = new Intent(contact1.this, seat.class);
                intent.putExtra("total_with_baggage_insurance", totalWithBaggageAndInsurance);
                seatActivityResultLauncher.launch(intent);
            }
        });


        travelInsuranceCheckbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                isInsuranceChecked = isChecked;
                updateSubtotal();
            }
        });

    }

    private void showBaggageDialog() {
        final String[] options = {"1 KG", "2 KG", "3 KG", "4 KG", "5 KG"};
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Choose Baggage");
        builder.setItems(options, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                selectedKg = which + 1;
                baggageOption.setText("Baggage - " + options[which]);
                updateSubtotal();
            }
        });
        builder.show();
    }

    private void updateSubtotal() {
        int subtotal = selectedKg * 100; // Base price per kg is 100
        if (isInsuranceChecked) {
            subtotal += 350; // Add 350 for travel insurance
        }
        subtotal += total; // Add total from seat selection
        totalCostLabel.setText("Total: ₹" + subtotal);
    }
}