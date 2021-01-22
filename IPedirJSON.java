package com.example.ciudadesespaagooglemaps;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;


public interface IPedirJSON {
    //http://simplemaps.com/static/data/country-cities/es/es.json
    @GET("static/data/country-cities/es/es.json")
    Call<List<Ciudad>> listarCiudades();
}
