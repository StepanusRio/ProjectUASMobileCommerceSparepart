package com.example.projectuasmobilecommercesparepart.adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.projectuasmobilecommercesparepart.R;
import com.example.projectuasmobilecommercesparepart.model.Product;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {
    SharedPreferences sharedPreferences;
    Context context;
    List<Product> listProduct;
    public ProductAdapter(Context context, List<Product> listProduct) {
        this.context = context;
        this.listProduct = listProduct;
        this.sharedPreferences = context.getSharedPreferences("OrderPreference",context.MODE_PRIVATE);
        sharedPreferences.contains("orderProduct");
    }

    @NonNull
    @Override
    public ProductAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_product_card,parent,false);
        ViewHolder holder = new ViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ProductAdapter.ViewHolder holder, int position) {
        final Product listProduct = this.listProduct.get(position);
        holder.tvName.setText(this.listProduct.get(position).getName());
        String hargaJual = "Rp. " + this.listProduct.get(position).getHargaJual();
        holder.tvHargaJual.setText(hargaJual);
        String stock = "Stok: " + this.listProduct.get(position).getStok();
        holder.tvStok.setText(stock);
        Glide.with(context).load(this.listProduct.get(position).getImageUrl()).into(holder.ProductImage);
        holder.AddToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<Product> orderProduct = new ArrayList<>();
                Product order = new Product(listProduct.getId(),listProduct.getName(),listProduct.getImageUrl(),listProduct.getHargaJual(),listProduct.getStok());
                orderProduct.add(order);
                Gson gson = new Gson();
                String jsonText = gson.toJson(orderProduct);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("orderProduct",jsonText);
                editor.apply();
                Toast.makeText(context, "Simpan Ke SharedPreferences", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return listProduct.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        public TextView tvName,tvStok,tvHargaJual;
        public CardView layoutItem;
        public ImageView ProductImage;
        public ImageButton AddToCart,DetailProduct;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.tvName = (TextView) itemView.findViewById(R.id.tvName);
            this.tvStok = (TextView) itemView.findViewById(R.id.tvStok);
            this.tvHargaJual = (TextView) itemView.findViewById(R.id.tvHargaJual);
            this.layoutItem = (CardView) itemView.findViewById(R.id.layoutItem);
            this.ProductImage = (ImageView) itemView.findViewById(R.id.ProductImage);
            this.AddToCart = (ImageButton) itemView.findViewById(R.id.AddToCartButton);
            this.DetailProduct = (ImageButton) itemView.findViewById(R.id.DetailProduct);
        }
    }

}
