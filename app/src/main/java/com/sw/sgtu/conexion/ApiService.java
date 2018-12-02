package com.sw.sgtu.conexion;

import com.sw.sgtu.modelo.BusLine;
import com.sw.sgtu.modelo.BusStop;
import com.sw.sgtu.modelo.Pasaje;
import com.sw.sgtu.modelo.Queja;
import com.sw.sgtu.modelo.Usuario;
import com.sw.sgtu.request.UserRequest;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ApiService {

    @GET("api/busstop/{id_ruta}")
    @Headers({"Accept: application/json"})
    Call<List<BusStop>> getParaderoByIdRoute(@Path("id_ruta") int id_ruta);

    @GET("api/busline/all/")
    Call<List<BusLine>> getBusLines();

    @POST("api/pasaje/")
    @Headers({"Accept: application/json"})
    Call<Pasaje> comprarPasaje(@Body Pasaje request);

    @POST("api/user/validar")
    @Headers({"Accept: application/json"})
    Call<Usuario> validarUsuario(@Body UserRequest request);

    @POST("api/user/")
    @Headers({"Accept: application/json"})
    Call<Usuario> registrarUsuario(@Body Usuario request);

    @POST("api/queja/")
    @Headers({"Accept: application/json"})
    Call<Queja> enviarQueja(@Body Queja request);

    @GET("api/queja/{id_queja}")
    @Headers({"Accept: application/json"})
    Call<List<Queja>> getListQueja(@Path("id_queja") int id_queja);


}
