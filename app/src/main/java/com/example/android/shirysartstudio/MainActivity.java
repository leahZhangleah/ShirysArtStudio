package com.example.android.shirysartstudio;

import android.content.Intent;
import android.net.Uri;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.Map;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback {
    ViewPager photoGallery;
    GoogleMap mMap;
    CameraPosition studio;
    MarkerOptions marker;
    TextView phone;
    TextView email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //set studio's position and marker
        studio = CameraPosition.builder()
                .target(new LatLng(31.3048, 121.5113))
                .zoom(18)
                .build();

        marker = new MarkerOptions()
                .position(new LatLng(31.3048, 121.5113))
                .title("Shiry's Art Studio");

        //photo gallery
        photoGallery = (ViewPager) findViewById(R.id.photo_gallery);
        PhotoPagerAdapter adapter = new PhotoPagerAdapter(this);
        photoGallery.setAdapter(adapter);

        //google map
        MapFragment fragment = (MapFragment) getFragmentManager().findFragmentById(R.id.map);
        fragment.getMapAsync(this);

        //email and phone intent
        phone = (TextView) findViewById(R.id.phone_view);
        phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                String phoneNumber = getResources().getString(R.string.phone);
                intent.setData( Uri.parse("tel:" + phoneNumber));
                if (intent.resolveActivity(getPackageManager())!= null){
                    startActivity(intent);
                }



            }
        });

        email = (TextView) findViewById(R.id.email_view);
        final String[] addresses = {getResources().getString(R.string.email)};
        email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_SENDTO);
                intent.setData(Uri.parse("mailto:"));
                intent.putExtra(Intent.EXTRA_EMAIL,addresses);
                if (intent.resolveActivity(getPackageManager())!= null){
                    startActivity(intent);
                }
            }
        });

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.moveCamera(CameraUpdateFactory.newCameraPosition(studio));
        mMap.addMarker(marker);
    }
}
