package br.com.acessibilidade.map;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import br.com.acessibilidade.map.models.Local;
import br.com.acessibilidade.map.network.EndpointClient;
import br.com.acessibilidade.map.network.Response;
import br.com.acessibilidade.map.network.ServiceGenerator;
import retrofit2.Call;
import retrofit2.Callback;

public class MapsFragment extends SupportMapFragment implements OnMapReadyCallback {

    private GoogleMap mMap;

    private Context context;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getMapAsync(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        EndpointClient endpointClient = ServiceGenerator
                .createService(EndpointClient.class, null);

        endpointClient.listarLocais().enqueue(new Callback<Response<Local>>() {
            @Override
            public void onResponse(Call<Response<Local>> call, retrofit2.Response<Response<Local>> response) {
                Log.d("", response.body().getData().toString());

            }

            @Override
            public void onFailure(Call<Response<Local>> call, Throwable t) {
                Log.d("", t.getLocalizedMessage());
            }
        });

    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
//        mMap.getUiSettings().setZoomControlsEnabled(true);


        // Add a marker in Sydney and move the camera
        LatLng fortaleza = new LatLng(-3.786359, -38.503355);
        //MarkerOptions marker = new MarkerOptions();
        //marker.position(fortaleza);
        //marker.title("Fortaleza");


        //mMap.addMarker(marker);

        mMap.moveCamera(CameraUpdateFactory.newLatLng(fortaleza));
        mMap.animateCamera(CameraUpdateFactory.zoomTo(10), 3000, null);
    }
}
