package br.com.acessibilidade.map;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import br.com.acessibilidade.map.models.Local;
import br.com.acessibilidade.map.network.EndpointClient;
import br.com.acessibilidade.map.network.Response;
import br.com.acessibilidade.map.network.ServiceGenerator;
import retrofit2.Call;
import retrofit2.Callback;

public class SobreActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sobre);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    @Override
    public void onResume() {
        super.onResume();  // Always call the superclass method first

        EndpointClient endpointClient = ServiceGenerator
                .createService(EndpointClient.class, null);

        endpointClient.listarLocais().enqueue(new Callback<Response<Local>>() {
            @Override
            public void onResponse(Call<Response<Local>> call, retrofit2.Response<Response<Local>> response) {
                Log.d("Response", response.body().getData().toString());

            }

            @Override
            public void onFailure(Call<Response<Local>> call, Throwable t) {
                Log.d("Erro", t.getLocalizedMessage());
            }
        });

    }

}
