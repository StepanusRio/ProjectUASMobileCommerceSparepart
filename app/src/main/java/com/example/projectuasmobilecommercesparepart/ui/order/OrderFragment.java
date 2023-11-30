package com.example.projectuasmobilecommercesparepart.ui.order;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projectuasmobilecommercesparepart.R;
import com.example.projectuasmobilecommercesparepart.adapter.OrderAdapter;
import com.example.projectuasmobilecommercesparepart.model.OrderProduct;
import com.google.gson.Gson;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class OrderFragment extends Fragment {
    private RecyclerView rvOrder;
    private TextView tvTotal;
    private  List<OrderProduct> orderProducts = new ArrayList<>();
    private OrderAdapter orderAdapter;
    private SharedPreferences sharedPreferences;
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dashboard,container,false);
        setUp(view);
        loadOrder();
        return view;
    }

    private void loadOrder() {
        rvOrder.setLayoutManager(new LinearLayoutManager(getActivity()));
        sharedPreferences = requireActivity().getSharedPreferences("OrderPreference",Context.MODE_PRIVATE);
        if (sharedPreferences.contains("orderProduct")){
            if (!orderProducts.isEmpty()){
                orderProducts.clear();
            }
            Gson gson = new Gson();
            String jsonText = sharedPreferences.getString("orderProduct", null);
            Integer total = 0;
            OrderProduct[] products = gson.fromJson(jsonText,OrderProduct[].class);
            for (OrderProduct product:products){
                orderProducts.add(product);
                total += product.getTotal();
                tvTotal.setText("Rp. "+total);
            }
            orderAdapter = new OrderAdapter(this, getActivity(),orderProducts);
            rvOrder.setAdapter(orderAdapter);
        }
    }

    private void setUp(View view){
        rvOrder = view.findViewById(R.id.rvOrderList);
        tvTotal = view.findViewById(R.id.tvTotalHarga);
    }
    public void deleteProduct(int position) {
        if (sharedPreferences.contains("orderProduct")){
            orderProducts.remove(position);
            orderAdapter.notifyItemRemoved(position);
            Gson gson = new Gson();
            String jsonText = gson.toJson(orderProducts);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("orderProduct",jsonText);
            editor.apply();
            Integer total = 0;
            OrderProduct[] products = gson.fromJson(jsonText,OrderProduct[].class);
            for (OrderProduct product:products){
                total = product.getTotal();
            }
            tvTotal.setText("Rp. "+total);
        }
    }
}