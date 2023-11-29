package com.example.projectuasmobilecommercesparepart.ui.order;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.projectuasmobilecommercesparepart.R;
import com.example.projectuasmobilecommercesparepart.databinding.FragmentDashboardBinding;

public class OrderFragment extends Fragment {
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        OrderViewModel orderViewModel = new ViewModelProvider(this).get(OrderViewModel.class);
        View view = inflater.inflate(R.layout.fragment_dashboard,container,false);

        return view;
    }
}