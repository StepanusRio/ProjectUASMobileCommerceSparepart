package com.example.projectuasmobilecommercesparepart.API.Routes;

import com.example.projectuasmobilecommercesparepart.model.Value;

import retrofit2.Call;
import retrofit2.http.GET;

public interface GetProduct {
    @GET("sparepart")
    Call<Value> get();
}
