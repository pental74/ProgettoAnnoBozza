package com.example.progettoannobozza;

import android.os.Bundle;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.gson.Gson;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Gson gson = new Gson();

        // Ricevi la stringa JSON dall'Intent
        String utenteJson = getIntent().getStringExtra("utente");

        // Converti la stringa JSON in un oggetto Utente
        if (!utenteJson.isEmpty()) {
            Log.d("utente", utenteJson);
            // da modificare  utente = gson.fromJson(utenteJson, Utente.class);
            // Log.d("utente", utente.toString());
        }
        else Log.d("utente", "utente vuoto");
    }
}
