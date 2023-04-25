package com.example.appqlkh;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomersRVAdapter extends RecyclerView.Adapter<CustomersRVAdapter.ViewHolder> {

    // variable for our array list and context
    ArrayList<CustomersModel> customersModalArrayList;
    ArrayList<CustomersModel> mFilteredCustomers;
    Context context;
    OnItemClickListener listener;


    // constructor
    public CustomersRVAdapter(ArrayList<CustomersModel> customersModalArrayList, Context context) {
        this.customersModalArrayList = customersModalArrayList;
        mFilteredCustomers = new ArrayList<>(customersModalArrayList);
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // on below line we are inflating our layout
        // file for our recycler view items.
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.customers_list_item, parent, false);
        return new ViewHolder(view);

    }

    //lam moi apdater của filter sau khi tim kiem
    @SuppressLint("NotifyDataSetChanged")
    public void updateData(ArrayList<CustomersModel> filteredCustomers) {
        this.customersModalArrayList = filteredCustomers;
        notifyDataSetChanged();
    }

    /*@SuppressLint("NotifyDataSetChanged")
    public void updateDataOrigin(ArrayList<CustomersModel> OriginCustomers) {
        this.customersModalArrayList = OriginCustomers;
        notifyDataSetChanged();
    }*/

    //dung de click
    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        // on below line we are setting data
        // to our views of recycler view item.
        CustomersModel modal = customersModalArrayList.get(position);
        holder.itemid.setText(modal.getId());
        holder.itemname.setText(modal.getCusName());
        holder.itemaddress.setText(modal.getCusAddress());
        holder.itemphone.setText(modal.getCusPhone());
        holder.itememail.setText(modal.getCusEmail());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Gọi phương thức onItemClick() của Interface khi itemView được click
                Intent i = new Intent(context, customer_detail.class);
                // below we are passing all our values.
                i.putExtra("id_cus", modal.getId());
                i.putExtra("name_cus", modal.getCusName());
                i.putExtra("address_cus", modal.getCusAddress());
                i.putExtra("phone_cus", modal.getCusPhone());
                i.putExtra("email_cus", modal.getCusEmail());
                // starting our activity.
                context.startActivity(i);
            }
        });

        holder.itemView.setOnCreateContextMenuListener(new View.OnCreateContextMenuListener(){
            @Override
            public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
                menu.setHeaderTitle("Chọn");
                menu.add(0, R.id.update, 0, "Cập Nhật").setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(@NonNull MenuItem menuItem) {
                        Intent i = new Intent(context, Update_customers.class);
                        // below we are passing all our values.
                        i.putExtra("id_cus", modal.getId());
                        i.putExtra("name_cus", modal.getCusName());
                        i.putExtra("address_cus", modal.getCusAddress());
                        i.putExtra("phone_cus", modal.getCusPhone());
                        i.putExtra("email_cus", modal.getCusEmail());
                        // starting our activity.
                        context.startActivity(i);
                        return false;
                    }
                });
                menu.add(0, R.id.delete, 0, "Xóa").setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                    @SuppressLint("NotifyDataSetChanged")
                    @Override
                    public boolean onMenuItemClick(@NonNull MenuItem menuItem) {
//                        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuItem.getMenuInfo();
//                        int position = info.position;
                        DBHandle dbHandle = new DBHandle(context);
                        dbHandle.deleteCus(modal.getId());
                        customersModalArrayList.remove(holder.getAdapterPosition());
                        mFilteredCustomers.remove(holder.getAdapterPosition());
                        Toast.makeText(context.getApplicationContext(), "Đã xóa", Toast.LENGTH_SHORT).show();
                        notifyDataSetChanged();
                        return true;
                    }
                });
            }
        });


    }

    //tra ve do dai cua mang recyleview
    @Override
    public int getItemCount() {
        // returning the size of our array list
        return customersModalArrayList.size();
    }

    //Tra ve do dai cua mang fiter
    public int size() {
        if (customersModalArrayList == null) {
            return 0; // Trả về 0 nếu danh sách là null
        }
        return customersModalArrayList.size(); // Trả về kích thước của danh sách nếu không null
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        // creating variables for our text views.
        TextView itemid, itemname, itemaddress, itemphone, itememail;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            // initializing our text views
            itemid = itemView.findViewById(R.id.itemId);
            itemname = itemView.findViewById(R.id.itemName);
            itemaddress = itemView.findViewById(R.id.itemAddress);
            itemphone = itemView.findViewById(R.id.itemPhone);
            itememail = itemView.findViewById(R.id.itemEmail);

        }
    }
}
