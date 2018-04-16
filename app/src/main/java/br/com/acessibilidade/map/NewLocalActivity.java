package br.com.acessibilidade.map;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.location.places.ui.PlaceAutocompleteFragment;
import com.google.android.gms.location.places.ui.PlaceSelectionListener;

import br.com.acessibilidade.map.models.Local;

public class NewLocalActivity extends AppCompatActivity implements PlaceSelectionListener,
        GoogleApiClient.OnConnectionFailedListener, AdapterView.OnItemSelectedListener {

    private GoogleApiClient mGoogleApiClient;
    private EditText local_name;
    private Local local;
    private Double latitude;
    private Double longitude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_local);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mGoogleApiClient = new GoogleApiClient
                .Builder(this)
                .addApi(Places.GEO_DATA_API)
                .addApi(Places.PLACE_DETECTION_API)
                .enableAutoManage(this, this)
                .build();

        PlaceAutocompleteFragment autocompleteFragment = (PlaceAutocompleteFragment)
                getFragmentManager().findFragmentById(R.id.place_autocomplete_fragment);

        autocompleteFragment.setOnPlaceSelectedListener(this);
        autocompleteFragment.setHint("Encontre o local aqui");


        local_name = (EditText) findViewById(R.id.local_name);


        Spinner spinner = (Spinner) findViewById(R.id.types_spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.types_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(this);

        local = new Local();
    }


    @Override
    public void onPlaceSelected(Place place) {
        local_name.setText(place.getName());

        local.setLatitude( place.getLatLng().latitude);
        local.setLongitude( place.getLatLng().longitude);
        local.setNome( place.getName().toString());

        Log.d("Successo", "Place Selected: " + local.toString());

    }

    @Override
    public void onError(Status status) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
            parent.getItemAtPosition(pos);

            if(parent.getItemIdAtPosition(pos) == 0) {
                local.setTipo(1);
            }
            else if (parent.getItemIdAtPosition(pos) == 1) {
                local.setTipo(2);
            }
            else if(parent.getItemIdAtPosition(pos) == 2) {
                local.setTipo(3);
            }

        Log.d("Successo", "Type Selected: " + parent.getItemIdAtPosition(pos));
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
