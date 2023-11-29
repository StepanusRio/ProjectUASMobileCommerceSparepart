package com.example.projectuasmobilecommercesparepart.model;

import java.util.List;

public class Value {
    public String getValue() {
        return value;
    }

    public String getMassage() {
        return massage;
    }

    public List<Product> getResult() {
        return result;
    }

    String value;
    String massage;
    List<Product> result;
}
