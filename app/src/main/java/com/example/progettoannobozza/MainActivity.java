package com.example.progettoannobozza;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.gson.Gson;

public class MainActivity extends AppCompatActivity {

    String utenteJson= null;

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

        // Click listener per il pulsante di LOGIN
        loginButton.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, LoginActivity.class);

            // Controllo se l'utente ha fatto la registrazione
            // Sarà presente utenteJson con i dati dell'utente registrato
            if (utenteJson != null) {
                // invio dei dati dell'utente se registrato
                intent.putExtra("utente", utenteJson);
            }
            startActivity(intent);
        });

        // Click listener per il pulsante di REGISTRAZIONE
        registerButton.setOnClickListener(v -> {
            // Avvio dell'Activity RegisterActivity
            startActivity(new Intent(MainActivity.this, RegisterActivity.class));
        });

        // Ricevi la stringa JSON dall'Intent
        utenteJson = getIntent().getStringExtra("utente");

        // Converti la stringa JSON in un oggetto Utente
        if (utenteJson != null) {
            Gson gson = new Gson();
            Utente utente = gson.fromJson(utenteJson, Utente.class);

            // Visibile nel logcat l'oggetto Utente
            System.out.println("Nome: " + utente.getNome());
            System.out.println("Cognome: " + utente.getCognome());
            System.out.println("Mail: " + utente.getMail());
            System.out.println("Password: " + utente.getPassword());

            registerButton.setVisibility(Button.GONE);
            registerButton.setEnabled(false); // Disabilita il pulsante di registrazione
        }
    }
}
