package com.mercadolibre.mlcandidate.modelos;

public class CaracteristicasProducto {
    private String name;
    private String value_name;

    public CaracteristicasProducto() {
    }

    public CaracteristicasProducto(String name, String value_name) {
        this.name = name;
        this.value_name = value_name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue_name() {
        return value_name;
    }

    public void setValue_name(String value_name) {
        this.value_name = value_name;
    }
}
