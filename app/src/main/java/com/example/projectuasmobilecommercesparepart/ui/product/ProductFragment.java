package com.example.projectuasmobilecommercesparepart.ui.product;

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
import com.example.projectuasmobilecommercesparepart.model.Product;
import com.example.projectuasmobilecommercesparepart.model.Value;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ProductFragment extends Fragment {
    private List<Product> listProduct = new ArrayList<>();
    private ProductAdapter viewAdapter;
    RecyclerView recyclerView;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home,container,false);
        viewAdapter = new ProductAdapter(getContext(),listProduct);
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
                viewAdapter = new ProductAdapter(getContext(),listProduct);
                recyclerView.setAdapter(viewAdapter);
            }

            @Override
            public void onFailure(Call<Value> call, Throwable t) {
                Toast.makeText(getContext(), "GAGAL: "+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}