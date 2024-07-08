package com.mad.StayCoza;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class contact2 extends AppCompatActivity {

    private EditText fullName, emailAddress, phoneNumber, idCardNumber;
    private Spinner countryCode;
    private RadioGroup genderGroup;
    private Button saveChanges;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contact2); // Replace with your actual layout file name

        fullName = findViewById(R.id.full_name);
        emailAddress = findViewById(R.id.email_address);
        phoneNumber = findViewById(R.id.phone_number);
        idCardNumber = findViewById(R.id.id_card_number);
        countryCode = findViewById(R.id.country_code);
        genderGroup = findViewById(R.id.gender_group);
        saveChanges = findViewById(R.id.save_changes);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.country_codes, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        countryCode.setAdapter(adapter);

        saveChanges.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = fullName.getText().toString();
                String email = emailAddress.getText().toString();
                String country = String.valueOf(countryCode.getSelectedItem());
                String phone = country + phoneNumber.getText().toString();
                String idCard = idCardNumber.getText().toString();
                int selectedGenderId = genderGroup.getCheckedRadioButtonId();
                RadioButton selectedGender = findViewById(selectedGenderId);
                String gender = selectedGender.getText().toString();

                // You can now use the above variables for further processing
                // For example, you can save them to a database or send them to a server

                Toast.makeText(contact2.this, "Changes saved: Name - " + name + ", Email - " + email + ", Phone - " + phone + ", ID Card - " + idCard + ", Country - " + country + ", Gender - " + gender, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
