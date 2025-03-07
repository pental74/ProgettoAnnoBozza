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

    String utenteJson;

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
        utenteJson = getIntent().getStringExtra("Loginutente");

        if (!utenteJson.equals("vuoto")) {
            Log.d("Login utente jason", utenteJson);
            Utente utente = gson.fromJson(utenteJson, Utente.class);  // utenteJason è una stringa per questo errore
            Log.d("Login utente classe utente", utente.toString());
        }
        else {
            utenteJson = "";
            Log.d("Login utente jason", "utente vuoto");
        }
    }
}
