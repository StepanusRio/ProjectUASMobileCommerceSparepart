package com.example.projectuasmobilecommercesparepart.model;

public class Product {
    private String id;
    private String name;
    private String imageUrl;
    private Integer hargaJual;
    private Integer stok;

    public Product(String id, String name, String imageUrl, Integer hargaJual, Integer stok) {
        this.id = id;
        this.name = name;
        this.imageUrl = imageUrl;
        this.hargaJual = hargaJual;
        this.stok = stok;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Integer getHargaJual() {
        return hargaJual;
    }

    public void setHargaJual(Integer hargaJual) {
        this.hargaJual = hargaJual;
    }

    public Integer getStok() {
        return stok;
    }

    public void setStok(Integer stok) {
        this.stok = stok;
    }
}
