package br.com.acessibilidade.map.network;



import android.content.Context;

import java.io.IOException;

import okhttp3.*;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Tyago on 26/03/2018.
 */

public class ServiceGenerator {

    //public static final String API_BASE_URL = "http://10.8.70.218:5000/";
    public static final String API_BASE_URL = "http://10.54.129.190:5000/";
    public Context ctx;

    private static OkHttpClient.Builder httpClient;
    private  static Retrofit retrofit;


    ServiceGenerator(Context context){
        this.ctx = context;
    }

    public static <S> S createService(Class<S> serviceClass, final String authToken){
        httpClient = new OkHttpClient.Builder();

        if(authToken != null) {

        } else {
//            httpClient.addInterceptor(new Interceptor() {
//                @Override
//                public Response intercept(Chain chain) throws IOException {
//
//
//                    return null;
//                }
//            });
        }

        retrofit = new Retrofit.Builder()
                .baseUrl(API_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient.build())
                .build();

        return  retrofit.create(serviceClass);
    }


}
