package com.example.appqlkh;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Collections;

public class costomer_list extends AppCompatActivity {
    ArrayList<CustomersModel> customersModalArrayList;
    DBHandle dbHandle;
    CustomersRVAdapter customersRVAdapter;
    RecyclerView recyclerView;
    //Button add_customer_button;
    TextView tvemty, refesh, count;
    EditText searchView;
    FloatingActionButton add_customer_button;

    //private SearchView searchView;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_costomer_list);

        customersModalArrayList = new ArrayList<>();
        dbHandle = new DBHandle(costomer_list.this);
        customersModalArrayList = dbHandle.readCustomers();

        //sap xep mang
        Collections.reverse(customersModalArrayList);
        customersRVAdapter = new CustomersRVAdapter(customersModalArrayList, costomer_list.this);

        recyclerView =findViewById(R.id.RecyclerView);
        add_customer_button = findViewById(R.id.add_customer_button);
        tvemty = findViewById(R.id.textViewEmpty);
        refesh = findViewById(R.id.refesh);
        //refesh.setVisibility(View.GONE);
        //count = findViewById(R.id.count);

        LinearLayoutManager linearLayoutManager= new LinearLayoutManager(costomer_list.this, RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(customersRVAdapter);
        add_customer_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(costomer_list.this, Add_customers.class);
                startActivity(intent);
                finish();
            }
        });

        // dem so thong tin
        /*int demso = customersRVAdapter.getItemCount();
        String countString = Integer.toString(demso);
        count.setText(countString);*/

        //search
        TextWatcher searchWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }
            @SuppressLint("SetTextI18n")
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                // Thực hiện logic lọc dữ liệu dựa trên nội dung tìm kiếm
                String searchKeyword = charSequence.toString().toLowerCase();
                ArrayList<CustomersModel> filteredCustomers = new ArrayList<>();
                for (CustomersModel customerposition : customersModalArrayList) {
                    if (customerposition.getCusName().toLowerCase().contains(searchKeyword)) {
                        filteredCustomers.add(customerposition);
                    }
                }
                // Cập nhật dữ liệu trong Adapter
                customersRVAdapter.updateData(filteredCustomers);

                if (filteredCustomers.size() == 0) {
                    tvemty.setText("Không tìm thấy!");
                } else {
                    tvemty.setText("");
                }

            }

            @Override
            public void afterTextChanged(Editable editable) {
                // Không cần xử lý
            }
        };

        //dau X
        EditText searchEditText = findViewById(R.id.searchView);
        searchEditText.addTextChangedListener(searchWatcher);
        refesh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ArrayList<CustomersModel> OriginCustomers = new ArrayList<>();
                searchEditText.setText("");
                customersModalArrayList = dbHandle.readCustomers();
                customersRVAdapter = new CustomersRVAdapter(customersModalArrayList, costomer_list.this);
                LinearLayoutManager linearLayoutManager= new LinearLayoutManager(costomer_list.this, RecyclerView.VERTICAL, false);
                recyclerView.setLayoutManager(linearLayoutManager);
                recyclerView.setAdapter(customersRVAdapter);
            }
        });

    }

        @Override
        public boolean onCreateOptionsMenu(Menu menu) {
            MenuInflater inflater = getMenuInflater();
            inflater.inflate(R.menu.main, menu);
            return true;
        }

        public boolean onOptionsItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.refreshList:
                    //Toast.makeText(this, "Test", Toast.LENGTH_SHORT).show();
                    ArrayList<CustomersModel> OriginCustomers = new ArrayList<>();
                    searchView = findViewById(R.id.searchView);
                    searchView.getText().clear();
                    customersModalArrayList = dbHandle.readCustomers();
                    customersRVAdapter = new CustomersRVAdapter(customersModalArrayList, costomer_list.this);
                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(costomer_list.this, RecyclerView.VERTICAL, false);
                    recyclerView.setLayoutManager(linearLayoutManager);
                    recyclerView.setAdapter(customersRVAdapter);
                }
                return super.onOptionsItemSelected(item);
            }

}