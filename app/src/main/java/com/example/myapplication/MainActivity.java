package com.example.myapplication;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.api.Status;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.api.net.PlacesClient;
import com.google.android.libraries.places.widget.AutocompleteSupportFragment;
import com.google.android.libraries.places.widget.listener.PlaceSelectionListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    String TAG = "placeautocomplete";
    TextView txtView, SignIn;

    FirebaseAuth mAuth;


    //    FireBase Connection
//    FirebaseAuth.AuthStateListener mAuthListner;

//    @Override
//    public void onStart() {
//        super.onStart();
//        mAuth.addAuthStateListener(mAuthListner);
//    }
//    // FireBase Connection ends

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtView = findViewById(R.id.txtView);
        SignIn = findViewById(R.id.log_in_sign_up);
        mAuth = FirebaseAuth.getInstance();

        // Initialize Places.
        Places.initialize(getApplicationContext(), "AIzaSyDNwe7GZuCtGmXnotWZFDfu5uoluDUlV6I");
        // Create a new Places client instance.
        PlacesClient placesClient = Places.createClient(this);

        // Initialize the AutocompleteSupportFragment.
        AutocompleteSupportFragment autocompleteFragment = (AutocompleteSupportFragment)
                getSupportFragmentManager().findFragmentById(R.id.autocomplete_fragment);

        // Specify the types of place data to return.
        assert autocompleteFragment != null;
        autocompleteFragment.setPlaceFields(Arrays.asList(Place.Field.ID, Place.Field.NAME));
// Set up a PlaceSelectionListener to handle the response.
        autocompleteFragment.setOnPlaceSelectedListener(new PlaceSelectionListener() {
            @Override
            public void onPlaceSelected(@NonNull Place place) {
                // TODO: Get info about the selected place.
                txtView.setText(place.getName() + "," + place.getId());
                Log.i(TAG, "Place: " + place.getName() + ", " + place.getId());
            }

            @Override
            public void onError(@NonNull Status status) {
                // TODO: Handle the error.
                Log.i(TAG, "An error occurred: " + status);
            }
        });
    }

    public void ContinueButtonClicked(View view) {

        if (txtView.getText().toString().trim().isEmpty()) {
            Toast.makeText(this, R.string.select_location, Toast.LENGTH_LONG).show();


        } else {

            Intent intent = new Intent(this, HomePage.class);
            startActivity(intent);
        }

    }

//    @Override
//    public void onStart() {
//        super.onStart();
//        // Check if user is signed in (non-null) and update UI accordingly.
//        FirebaseUser currentUser = mAuth.getCurrentUser();
//        if (currentUser == null)
//            startActivity(new Intent(this, Login.class));
//    }

    public void LogInSignUpClicked(View view) {

        Intent i = new Intent(this, Login.class);
        startActivity(i);
    }
//        mAuthListner = new FirebaseAuth.AuthStateListener() {
//            @Override
//            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {



//        };
//    }

//    }
}
