package com.example.projectuasmobilecommercesparepart.model;

public class OrderProduct {
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

    public Integer getJumlah() {
        return jumlah;
    }

    public void setJumlah(Integer jumlah) {
        this.jumlah = jumlah;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public OrderProduct(String id, String name, String imageUrl, Integer hargaJual, Integer stok, Integer jumlah, Integer total) {
        this.id = id;
        this.name = name;
        this.imageUrl = imageUrl;
        this.hargaJual = hargaJual;
        this.stok = stok;
        this.jumlah = jumlah;
        this.total = total;
    }

    private String id;
    private String name;
    private String imageUrl;
    private Integer hargaJual;
    private Integer stok;
    private Integer jumlah;
    private Integer total;
}
