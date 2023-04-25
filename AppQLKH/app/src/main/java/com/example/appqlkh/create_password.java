package com.example.appqlkh;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class create_password extends AppCompatActivity {
    EditText textPW,TextPW_again;
    Button btnCreatePassword;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_password);
        textPW = findViewById(R.id.txtnhapMK);
        btnCreatePassword = findViewById(R.id.btnCreatePassword);
        TextPW_again = findViewById(R.id.txtnhaplaiMK);
        // Xử lý sự kiện khi click vào nút "Tạo mật khẩu"
        btnCreatePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Tạo mật khẩu ngẫu nhiên
                //String password = generatePassword();
                String password = textPW.getText().toString();
                String confirmPassword = TextPW_again.getText().toString();

                if (password.isEmpty()|| confirmPassword.isEmpty()){
                    Toast.makeText(create_password.this, "Vui lòng nhập đủ thông tin", Toast.LENGTH_SHORT).show();
                } else if (!password.equals(confirmPassword)){
                    Toast.makeText(create_password.this, "Mật khẩu không khớp", Toast.LENGTH_SHORT).show();
                } else {
                    // Lưu mật khẩu vào SharedPreferences
                    SharedPreferencesManager.setPassword(create_password.this, password);
                    //danh dau mk đã dc tạo
                    SharedPreferencesManager.setPasswordCreated(create_password.this, true);
                    // Hiển thị mật khẩu đã tạo
                    Toast.makeText(create_password.this, "Tạo thành công vui lòng đăng nhập!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(create_password.this, Login.class);
                    startActivity(intent);
                    finish();
                }
            }
        });
    }
    /*private String generatePassword() {
        // Mã nguồn để tạo mật khẩu ngẫu nhiên
        // Đây là ví dụ, bạn có thể thay đổi cách tạo mật khẩu phù hợp với yêu cầu của bạn
        String password = textPW.getText().toString();
        //String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        return password.toString();
    }*/
}