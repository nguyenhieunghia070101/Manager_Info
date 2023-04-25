package com.example.appqlkh;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Update_customers extends AppCompatActivity {
    EditText editTextName, editTextAddress, editTextPhone, editTextEmail;
    TextView textViewId;
    Button btnUpdate;
    DBHandle dbHandle;
    String id, name, address, phone, email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_customers);
        textViewId = findViewById(R.id.edit_text_id);
        editTextName = findViewById(R.id.edit_text_name);
        editTextAddress = findViewById(R.id.edit_text_address);
        editTextPhone = findViewById(R.id.edit_text_phone);
        editTextEmail = findViewById(R.id.edit_text_email);
        btnUpdate = findViewById(R.id.button_update);

        dbHandle = new DBHandle(Update_customers.this);

        id = getIntent().getStringExtra("id_cus");
        name = getIntent().getStringExtra("name_cus");
        address = getIntent().getStringExtra("address_cus");
        phone = getIntent().getStringExtra("phone_cus");
        email = getIntent().getStringExtra("email_cus");

        // setting data to edit text
        // of our update activity.
        textViewId.setText(id);
        editTextName.setText(name);
        editTextAddress.setText(address);
        editTextPhone.setText(phone);
        editTextEmail.setText(email);

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            dbHandle.updateCus(id,editTextName.getText().toString(),editTextAddress.getText().toString(),
                                editTextPhone.getText().toString(),editTextEmail.getText().toString());
                Toast.makeText(Update_customers.this, "Thành công!", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(Update_customers.this, costomer_list.class);
                startActivity(i);
                finish();
            }
        });
    }
}