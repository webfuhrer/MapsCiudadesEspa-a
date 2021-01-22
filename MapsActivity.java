package com.example.ciudadesespaagooglemaps;

import androidx.fragment.app.FragmentActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback,
ClasePeticion.IRellenarSpinner{

    private GoogleMap mMap;
    Spinner spn_ciudades;
    Button btn_mostrar;
    Marker marcador;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_mapa_spinner);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        spn_ciudades=findViewById(R.id.spn_ciudades);
        btn_mostrar=findViewById(R.id.btn_mostrar);
        View.OnClickListener oyente_boton= new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            mostrarMarcador();
            }
        };
        btn_mostrar.setOnClickListener(oyente_boton);

    }

    private void mostrarMarcador() {

        //Mostrar el marcador.
        if(marcador!=null)
        {
            marcador.remove();
        }
        Ciudad c=(Ciudad)spn_ciudades.getSelectedItem();
        Double lat=Double.parseDouble(c.getLat());
        Double lng=Double.parseDouble(c.getLng());
        LatLng posicion=new LatLng(lat, lng);
        MarkerOptions opciones_marcador=new MarkerOptions().position(posicion).title(c.getCity()+" "+c.getPopulation());
        marcador=mMap.addMarker(opciones_marcador);

        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(posicion, 5.5f));

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
        ClasePeticion.pedirJSON(this);

    }

    @Override
    public void rellenarSpinner(List<Ciudad> ciudades) {
        ArrayAdapter<Ciudad> adaptador=new ArrayAdapter<>(this, R.layout.support_simple_spinner_dropdown_item, ciudades);
        spn_ciudades.setAdapter(adaptador);
    }
}