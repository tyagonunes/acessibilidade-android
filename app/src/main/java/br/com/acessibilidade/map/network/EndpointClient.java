package br.com.acessibilidade.map.network;;

import java.util.HashMap;

import br.com.acessibilidade.map.models.Local;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by Tyago on 26/03/2018.
 */

public interface EndpointClient {

    @GET("locais")
    Call<Response<Local>> listarLocais();

    @POST("locais")
    Call<Response<Local>> criarLocal(@Body HashMap<String, Object> data);

    @POST("usuarios/login")
    Call<Response<String>> login(@Body HashMap<String, Object> data);

    @POST("usuarios/cadastrar")
    Call<Response<String>> sigin(@Body HashMap<String, Object> data);

}
