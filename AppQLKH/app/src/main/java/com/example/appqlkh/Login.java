package com.example.appqlkh;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends AppCompatActivity {
    EditText etPassword;
    Button btnLogin,btnCreatePassword;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        // Liên kết các thành phần giao diện với mã nguồn Java
        etPassword = findViewById(R.id.etPassword);
        btnLogin = findViewById(R.id.btnLogin);
        btnCreatePassword = findViewById(R.id.btnCreatePassword);

        // Xử lý sự kiện khi click vào nút "Đăng nhập"
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String password = etPassword.getText().toString();
                // Kiểm tra mật khẩu nhập vào với mật khẩu đã lưu trữ trong SharedPreferences
                if (password.equals(SharedPreferencesManager.getPassword(Login.this))) {
                    // Mật khẩu đúng, thực hiện hành động đăng nhập
                    Toast.makeText(Login.this, "Đăng nhập thành công!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(Login.this, costomer_list.class);
                    startActivity(intent);
                    finish();
                } else {
                        // Mật khẩu sai
                    Toast.makeText(Login.this, "Mật khẩu không đúng!", Toast.LENGTH_SHORT).show();
                }
            }
        });
        // Kiểm tra xem mật khẩu đã được tạo hay chưa
        boolean isPasswordCreated = SharedPreferencesManager.isPasswordCreated(Login.this);

        // Nếu mật khẩu đã được tạo, ẩn nút "Tạo mật khẩu"
        if (isPasswordCreated) {
            btnCreatePassword.setVisibility(View.GONE);
        }
        // Nếu chưa được tạo, hiển thị nút "Tạo mật khẩu"
        else {
            btnCreatePassword.setVisibility(View.VISIBLE);
        }

        // Xử lý sự kiện khi click vào nút "Tạo mật khẩu"
        btnCreatePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Chuyển đến hoạt động tạo mật khẩu
                Intent intent = new Intent(Login.this, create_password.class);
                startActivity(intent);
                finish();
            }
        });
    }
}