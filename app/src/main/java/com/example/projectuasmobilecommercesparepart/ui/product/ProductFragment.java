package com.example.projectuasmobilecommercesparepart.ui.product;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projectuasmobilecommercesparepart.API.Routes.GetProduct;
import com.example.projectuasmobilecommercesparepart.API.ServerAPI;
import com.example.projectuasmobilecommercesparepart.R;
import com.example.projectuasmobilecommercesparepart.adapter.ProductAdapter;
import com.example.projectuasmobilecommercesparepart.databinding.FragmentHomeBinding;
import com.example.projectuasmobilecommercesparepart.model.OrderProduct;
import com.example.projectuasmobilecommercesparepart.model.Product;
import com.example.projectuasmobilecommercesparepart.model.Value;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ProductFragment extends Fragment {
    private List<Product> listProduct = new ArrayList<>();
    private  List<OrderProduct> orderProducts = new ArrayList<>();
    private ProductAdapter viewAdapter;
    RecyclerView recyclerView;
    SharedPreferences sharedPreferences;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home,container,false);
        viewAdapter = new ProductAdapter(getContext(),this,listProduct);
        recyclerView = view.findViewById(R.id.rvProductList);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getContext(),2);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(viewAdapter);
        loadData();
        return view;
    }
    private void loadData(){
        ServerAPI urlApi = new ServerAPI();
        String URL = urlApi.BASE_URL;
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        GetProduct api = retrofit.create(GetProduct.class);
        Call<Value> call = api.get();
        Toast.makeText(getContext(), "Siap Masuk Load Product", Toast.LENGTH_SHORT).show();
        call.enqueue(new Callback<Value>() {
            @Override
            public void onResponse(Call<Value> call, Response<Value> response) {
                String value = response.body().getValue();
                listProduct = response.body().getResult();
                Toast.makeText(getContext(), "MASUK ADAPTER", Toast.LENGTH_SHORT).show();
                viewAdapter = new ProductAdapter(getContext(),ProductFragment.this,listProduct);
                recyclerView.setAdapter(viewAdapter);
            }

            @Override
            public void onFailure(Call<Value> call, Throwable t) {
                Toast.makeText(getContext(), "GAGAL: "+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void addToCart(Product listProduct) {
        sharedPreferences = getActivity().getSharedPreferences("OrderPreference", Context.MODE_PRIVATE);
        sharedPreferences.contains("orderProduct");
        if (sharedPreferences.contains("orderProduct")){
            Gson gson = new Gson();
            String jsonText = sharedPreferences.getString("orderProduct", null);
            OrderProduct[] products = gson.fromJson(jsonText, OrderProduct[].class);
            orderProducts.clear();
            for (OrderProduct product : products) {
                orderProducts.add(product);
            }
        }
        boolean isHasItem = false;
        for (OrderProduct products:orderProducts){
            if (products.getId().equals(listProduct.getId())){
                int Items = products.getJumlah()+1;
                int Total = products.getTotal()+listProduct.getHargaJual();
                if (listProduct.getStok()>=Items){
                    products.setJumlah(Items);
                    products.setTotal(Total);
                }
                isHasItem = true;
                break;
            }
        }
        if (!isHasItem){
            orderProducts.add(new OrderProduct(
                    listProduct.getId(),
                    listProduct.getName(),
                    listProduct.getImageUrl(),
                    listProduct.getHargaJual(),
                    listProduct.getStok(),
                    1,
                    listProduct.getHargaJual()
            ));
        }
        Gson gson = new Gson();
        String jsonText = gson.toJson(orderProducts);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("orderProduct",jsonText);
        editor.apply();
        Toast.makeText(getActivity(), "Berhasil Simpan ke Preferences", Toast.LENGTH_SHORT).show();
    }
}