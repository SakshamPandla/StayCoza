package com.mad.StayCoza;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import com.razorpay.Checkout;
import com.razorpay.PaymentResultListener;
import org.json.JSONObject;
import androidx.appcompat.app.AppCompatActivity;

public class seat extends AppCompatActivity implements PaymentResultListener {

    private CheckBox addFood;
    private Spinner foodType;
    private TextView subtotal;
    private Button makePaymentButton;
    private RadioGroup seatClassGroup;

    private int total = 0;
    private int seatAndFoodCost = 0;
    private static final int RAZORPAY_PAYMENT_REQUEST_CODE = 1234;
    private static final int RAZORPAY_PAYMENT_CANCELLED_CODE = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.seat);

        Intent intent = getIntent();
        total = intent.getIntExtra("total_with_baggage_insurance", 0);

        seatClassGroup = findViewById(R.id.seat_class_group);
        addFood = findViewById(R.id.add_food);
        foodType = findViewById(R.id.food_type);
        subtotal = findViewById(R.id.subtotal);
        makePaymentButton = findViewById(R.id.make_payment);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.food_types, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        foodType.setAdapter(adapter);

        seatClassGroup.setOnCheckedChangeListener((group, checkedId) -> {
            if (checkedId == R.id.economy_class) {
                seatAndFoodCost = 0; // replace with actual cost of economy class
            } else if (checkedId == R.id.business_class) {
                seatAndFoodCost = 1799; // replace with actual cost of business class
            }
            updateSubtotal();
        });

        addFood.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                seatAndFoodCost += 499;
                foodType.setVisibility(View.VISIBLE);
            } else {
                seatAndFoodCost -= 499;
                foodType.setVisibility(View.GONE);
            }
            updateSubtotal();
        });

        // Setting up onClick functionality for Make Payment button
        makePaymentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Call the method to initiate payment with the total amount
                initiatePayment(total + seatAndFoodCost);
            }
        });
    }

    private void updateSubtotal() {
        subtotal.setText("Subtotal: ₹" + (total + seatAndFoodCost));
    }

    private void initiatePayment(int totalAmount) {
        Checkout checkout = new Checkout();
        checkout.setKeyID("YOUR_RAZORPAY_KEY_ID"); // Replace with your Razorpay Key ID

        try {
            JSONObject options = new JSONObject();
            options.put("name", "Your App Name");
            options.put("description", "Payment for flight seat booking");
            options.put("currency", "INR");
            options.put("amount", totalAmount * 100); // Amount is in paise, so multiply by 100

            checkout.open(this, options);
        } catch (Exception e) {
            Toast.makeText(this, "Error in payment: " + e.getMessage(), Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }

    @Override
    public void onPaymentSuccess(String razorpayPaymentID) {
        Toast.makeText(this, "Payment successful", Toast.LENGTH_SHORT).show();
        // Handle successful payment
    }

    @Override
    public void onPaymentError(int code, String response) {
        if (code == RAZORPAY_PAYMENT_CANCELLED_CODE) {
            Toast.makeText(this, "Payment canceled", Toast.LENGTH_SHORT).show();
            // Handle payment cancellation
        } else {
            Toast.makeText(this, "Payment failed: " + response, Toast.LENGTH_SHORT).show();
            // Handle payment failure
        }
    }
}