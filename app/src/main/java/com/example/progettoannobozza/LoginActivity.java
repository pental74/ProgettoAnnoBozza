package com.example.progettoannobozza;

import android.os.Bundle;
import android.widget.Toast;

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

        // Ricevi la stringa JSON dall'Intent
        String utenteJson = getIntent().getStringExtra("utente");

        // Converti la stringa JSON in un oggetto Utente
        if (utenteJson != null) {
            Gson gson = new Gson();
            Utente utente = gson.fromJson(utenteJson, Utente.class);

            Toast.makeText(LoginActivity.this, utente.getNome(), Toast.LENGTH_LONG).show();
            // Visibile nel logcat l'oggetto Utente
            System.out.println("Nome: " + utente.getNome());
            System.out.println("Cognome: " + utente.getCognome());
            System.out.println("Mail: " + utente.getMail());
            System.out.println("Password: " + utente.getPassword());
        }
    }
}