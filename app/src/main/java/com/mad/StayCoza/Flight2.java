package com.mad.StayCoza;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Flight2 extends AppCompatActivity {

    Button bookNowButton1, bookNowButton2, bookNowButton3;
    FlightDbHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.flight2);

        dbHelper = new FlightDbHelper(this);

        bookNowButton1 = findViewById(R.id.book_now_button);
        bookNowButton2 = findViewById(R.id.book_now_button2);
        bookNowButton3 = findViewById(R.id.book_now_button3);

        View.OnClickListener bookNowClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Flight flight = getFlightDetails(v); // Retrieve flight details
                addFlightToDatabase(flight); // Add flight to database
                int numTravelers = getIntent().getIntExtra("numTravelers", 1); // Get number of travelers
                double totalPrice = flight.getPrice() * numTravelers; // Calculate total price
                navigateToContact1(totalPrice); // Navigate to contact1 with total price
            }
        };

        bookNowButton1.setOnClickListener(bookNowClickListener);
        bookNowButton2.setOnClickListener(bookNowClickListener);
        bookNowButton3.setOnClickListener(bookNowClickListener);
    }

    private Flight getFlightDetails(View v) {
        // Retrieve flight details from the respective views
        TextView flightPrice = v.getRootView().findViewById(R.id.flight_price2);
        String priceString = flightPrice.getText().toString();
        // Extract numerical value from price string
        String[] priceParts = priceString.split("₹");
        double price = Double.parseDouble(priceParts[1]);
        // Create and return Flight object
        return new Flight("Mumbai", "Delhi", "10:00 AM", "12:30 PM", "2h 30m", "2024-05-02", price, "Air India");
    }

    private void addFlightToDatabase(Flight flight) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(FlightContract.FlightEntry.COLUMN_DEPARTURE, flight.getDeparture());
        values.put(FlightContract.FlightEntry.COLUMN_ARRIVAL, flight.getArrival());
        values.put(FlightContract.FlightEntry.COLUMN_DEPARTURE_TIME, flight.getDepartureTime());
        values.put(FlightContract.FlightEntry.COLUMN_ARRIVAL_TIME, flight.getArrivalTime());
        values.put(FlightContract.FlightEntry.COLUMN_DURATION, flight.getDuration());
        values.put(FlightContract.FlightEntry.COLUMN_DATE, flight.getDate());
        values.put(FlightContract.FlightEntry.COLUMN_PRICE, flight.getPrice());
        values.put(FlightContract.FlightEntry.COLUMN_AIRLINE, flight.getAirline());

        // Insert the new row
        db.insert(FlightContract.FlightEntry.TABLE_NAME, null, values);
    }

    private void navigateToContact1(double flightPrice) {
        Intent intent = new Intent(Flight2.this, contact1.class);
        intent.putExtra("flight_price", flightPrice); // Pass flight price as extra
        startActivity(intent);
    }
}
