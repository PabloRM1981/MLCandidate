package com.mercadolibre.mlcandidate.interfaces;

import com.mercadolibre.mlcandidate.modelos.Productos;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MercadoLibreAPI {

    @GET("search")
    Call<Productos> find(
            @Query("q") String q);

    //Motorola%20G6
    @GET("search?q=Moto")
    Call<Productos> getProductos();
}
