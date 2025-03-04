package com.example.progettoannobozza;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.gson.Gson;

public class MainActivity extends AppCompatActivity {

    // Creazione dell'oggetto Gson per la conversione JSON
    Gson gson = new Gson();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Collegamento alle risorse del layout
        Button loginButton = findViewById(R.id.loginButton);
        Button registerButton = findViewById(R.id.registerButton);

        // Click listener per il pulsante di REGISTRAZIONE
        registerButton.setOnClickListener(v -> {
            // Avvio dell'Activity RegisterActivity
            startActivity(new Intent(MainActivity.this, RegisterActivity.class));
        });

        // Ricevi la stringa JSON dall'Intent
        String utenteJson = getIntent().getStringExtra("utente");

        // Converti la stringa JSON in un oggetto Utente
        if (utenteJson != null) {
            Log.d("utente jason", utenteJson);

            Utente utente = gson.fromJson(utenteJson, Utente.class);  // utenteJason è una stringa per questo errore
            Log.d("utente classe utente", utente.toString());
        }

        // Click listener per il pulsante di LOGIN
        loginButton.setOnClickListener(v -> {
            // Avvio dell'Activity LoginActivity

            // invio dei dati dell'utente alla main activity
            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            if (!utenteJson.isEmpty()) intent.putExtra("utente", utenteJson);
            else intent.putExtra("utente", "");
            startActivity(intent);
            //startActivity(new Intent(MainActivity.this, LoginActivity.class));
        });
    }
}
