package br.com.acessibilidade.map.network;

import br.com.acessibilidade.map.models.Local;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Tyago on 26/03/2018.
 */

public interface EndpointClient {
    @GET("locais")
    Call<Response<Local>> listarLocais();

    @GET("locais/{id}")
    Call<Response<Local>> localPorId(
            @Path("id") String idLocal,
            @Query("ativo") boolean valor
    );

}
