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

}
