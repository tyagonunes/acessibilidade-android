package br.com.acessibilidade.map;

import br.com.acessibilidade.map.models.UdacityCatalog;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Tyago on 19/02/2018.
 */

public interface UdacityService {
    public  static final String BASE_URL = "https://www.udacity.com/public-api/v0/";

    @GET("courses")
    Call<UdacityCatalog> ListCatalog();
}
