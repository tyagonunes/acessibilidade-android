package br.com.acessibilidade.map;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import br.com.acessibilidade.map.models.Local;
import br.com.acessibilidade.map.network.EndpointClient;
import br.com.acessibilidade.map.network.Response;
import br.com.acessibilidade.map.network.ServiceGenerator;
import retrofit2.Call;
import retrofit2.Callback;

public class MapsFragment extends SupportMapFragment implements OnMapReadyCallback,
        GoogleMap.OnMarkerClickListener {

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
        getMarkers();
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng fortaleza = new LatLng(-3.786359, -38.503355);


        mMap.moveCamera(CameraUpdateFactory.newLatLng(fortaleza));
        mMap.animateCamera(CameraUpdateFactory.zoomTo(10), 3000, null);
        mMap.setOnMarkerClickListener(this);

    }

    private void getMarkers() {
        EndpointClient endpointClient = ServiceGenerator
                .createService(EndpointClient.class, null);

        endpointClient.listarLocais().enqueue(new Callback<Response<Local>>() {
            @Override
            public void onResponse(Call<Response<Local>> call, retrofit2.Response<Response<Local>> response) {
               // Log.d("Sucesso", response.body().getData().toString());

                for(Local local: response.body().getData()) {
                    setAllLocations(local);
                }

            }

            @Override
            public void onFailure(Call<Response<Local>> call, Throwable t) {
                Log.d("Falha", t.getLocalizedMessage());
            }
        });
    }

    private void setAllLocations(Local local) {

        LatLng latLng = new LatLng(local.getLatitude(), local.getLongitude());
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(latLng);

        switch (local.getTipo()) {
            case 1:
                markerOptions.icon(BitmapDescriptorFactory.fromResource(R.mipmap.ic_marker_green));
                break;

            case 2:
                markerOptions.icon(BitmapDescriptorFactory.fromResource(R.mipmap.ic_marker_red));
                break;

            case 3:
                markerOptions.icon(BitmapDescriptorFactory.fromResource(R.mipmap.ic_marker_black));
                break;

            default:
                markerOptions.icon(BitmapDescriptorFactory.fromResource(R.mipmap.ic_marker_black));
                break;
        }

        Marker marker = mMap.addMarker(markerOptions);
        marker.setTag(local);

    }

    @Override
    public boolean onMarkerClick(Marker marker) {

        Local local = (Local) marker.getTag();
        final Dialog dialog = new Dialog(getActivity());
        dialog.setContentView(R.layout.dialog_local);
        dialog.setCancelable(true);

        // set the custom dialog components - text, image and button
        TextView name = (TextView) dialog.findViewById(R.id.title_local);
        name.setText(local.getNome());

        TextView description = (TextView) dialog.findViewById(R.id.description_local);
        description.setText(local.getDescricao() != null && !local.getDescricao().isEmpty() ?
                local.getDescricao() : "Nenhuma descição");

        TextView acessos = (TextView) dialog.findViewById(R.id.acessos_local);
        acessos.setText(local.getAcessos());

        dialog.show();
        return true;
    }
}
