package com.example.projectuasmobilecommercesparepart.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.projectuasmobilecommercesparepart.R;
import com.example.projectuasmobilecommercesparepart.model.OrderProduct;
import com.example.projectuasmobilecommercesparepart.ui.order.OrderFragment;

import java.util.List;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.ViewHolder> {

    private OrderFragment fragment;
    private Context context;
    private List<OrderProduct> dataOrderProducts;
    public OrderAdapter(OrderFragment fragment, Context context, List<OrderProduct> dataOrderProducts) {
        this.fragment = fragment;
        this.context = context;
        this.dataOrderProducts = dataOrderProducts;
    }

    @NonNull
    @Override
    public OrderAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view  = LayoutInflater.from(context).inflate(R.layout.order_list,parent,false);
        return new OrderAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderAdapter.ViewHolder holder, int position) {
        OrderProduct order = dataOrderProducts.get(position);
        Glide.with(context).load(order.getImageUrl()).into(holder.ProductImageOrder);
        holder.tvOrderName.setText(order.getName());
        holder.tvOrderQty.setText(order.getJumlah()+"");
        holder.tvOrderHargaJual.setText("Rp. "+order.getHargaJual());
        holder.tvTotalOrder.setText("Rp. "+order.getTotal());
        holder.DeleteItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (fragment !=null){
                    fragment.deleteProduct(position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataOrderProducts.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView ProductImageOrder;
        TextView tvOrderName,tvOrderHargaJual,tvOrderQty,tvTotalOrder;
        ImageButton DeleteItem;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvOrderName = itemView.findViewById(R.id.tvOrderName);
            tvOrderHargaJual = itemView.findViewById(R.id.tvOrderHargaJual);
            tvOrderQty = itemView.findViewById(R.id.tvOrderQty);
            tvTotalOrder = itemView.findViewById(R.id.tvTotalOrder);
            ProductImageOrder = itemView.findViewById(R.id.ProductImageOrder);
            DeleteItem = itemView.findViewById(R.id.DeleteItem);
        }
    }
}
