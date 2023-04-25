package com.example.appqlkh;

import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Add_customers extends AppCompatActivity {

    EditText EdtName, EdtAddress, EdtPhone, EdtEmail;
    private Button btnSave, btnReturn;
    DBHandle dbHandle;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_customers);

        EdtName = findViewById(R.id.edit_text_name);
        EdtAddress = findViewById(R.id.edit_text_address);
        EdtPhone = findViewById(R.id.edit_text_phone);
        EdtEmail = findViewById(R.id.edit_text_email);
        btnSave = findViewById(R.id.button_save);
        btnReturn = findViewById(R.id.button_return);

        dbHandle = new DBHandle(Add_customers.this);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String edtName = EdtName.getText().toString();
                String edtAddress = EdtAddress.getText().toString();
                String edtPhone = EdtPhone.getText().toString();
                String edtEmail = EdtEmail.getText().toString();

                if (edtName.isEmpty()  || edtAddress.isEmpty() || edtPhone.isEmpty() ||edtEmail.isEmpty()) {
                    Toast.makeText(Add_customers.this, "Nhập đủ đủ thông tin", Toast.LENGTH_SHORT).show();
                    return;
                }
                dbHandle.addNewCus(edtName,edtAddress,edtPhone,edtEmail);

                Toast.makeText(Add_customers.this, "Lưu thành công", Toast.LENGTH_SHORT).show();
                EdtName.setText("");
                EdtAddress.setText("");
                EdtPhone.setText("");
                EdtEmail.setText("");
                EdtName.requestFocus();
            }
        });
        btnReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Add_customers.this, costomer_list.class);
                startActivity(i);
                finish();
            }
        });
    }
}