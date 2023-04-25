package com.example.appqlkh;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class customer_detail extends AppCompatActivity {
    TextView tvID, tvTextName, tvTextAddress, tvTextPhone, tvTextEmail;
    DBHandle dbHandle;
    String id_detail, name_detail, address_detail, phone_detail, email_detail;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_detail);

        tvID = findViewById(R.id.tvID);
        tvTextName = findViewById(R.id.tvName);
        tvTextAddress = findViewById(R.id.tvAddress);
        tvTextPhone = findViewById(R.id.tvPhone);
        tvTextEmail = findViewById(R.id.tvEmail);

        dbHandle = new DBHandle(customer_detail.this);

        id_detail = getIntent().getStringExtra("id_cus");
        name_detail = getIntent().getStringExtra("name_cus");
        address_detail = getIntent().getStringExtra("address_cus");
        phone_detail = getIntent().getStringExtra("phone_cus");
        email_detail = getIntent().getStringExtra("email_cus");

        tvID.setText(id_detail);
        tvTextName.setText(name_detail);
        tvTextAddress.setText(address_detail);
        tvTextPhone.setText(phone_detail);
        tvTextEmail.setText(email_detail);
    }
}