package com.mercadolibre.mlcandidate.modelos;

import java.io.Serializable;
import java.util.ArrayList;

public class Producto implements Serializable {
    private String title;
    private Integer price;
    private String currency_id;
    private Integer available_quantity;
    private String condition;
    private String thumbnail;
    private ArrayList<CaracteristicasProducto> attributes;

    public Producto() {
    }

    public Producto(String title, Integer price, String currency_id, Integer available_quantity, String condition, String thumbnail, ArrayList<CaracteristicasProducto> attributes) {
        this.title = title;
        this.price = price;
        this.currency_id = currency_id;
        this.available_quantity = available_quantity;
        this.condition = condition;
        this.thumbnail = thumbnail;
        this.attributes = attributes;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getCurrency_id() {
        return currency_id;
    }

    public void setCurrency_id(String currency_id) {
        this.currency_id = currency_id;
    }

    public Integer getAvailable_quantity() {
        return available_quantity;
    }

    public void setAvailable_quantity(Integer available_quantity) {
        this.available_quantity = available_quantity;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public ArrayList<CaracteristicasProducto> getAttributes() {
        return attributes;
    }

    public void setAttributes(ArrayList<CaracteristicasProducto> attributes) {
        this.attributes = attributes;
    }
}
