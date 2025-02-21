package com.example.progettoannobozza;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.progettoannobozza.Utente;
import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.gson.Gson;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_register);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Collegamento alle risorse del layout
        EditText nome = findViewById(R.id.editTextName);
        EditText cognome = findViewById(R.id.editTextSurname);
        EditText mail = findViewById(R.id.editTextEmail);
        EditText password = findViewById(R.id.editTextPassword);
        Button registerButton = findViewById(R.id.buttonRegister);

        registerButton.setOnClickListener(v -> {
            // Salvataggio dati inseriti in un oggetto Utente
            String nomeUtente = nome.getText().toString();
            String cognomeUtente = cognome.getText().toString();
            String mailUtente = mail.getText().toString();
            String passwordUtente = password.getText().toString();

            if(TextUtils.isEmpty(nomeUtente) || TextUtils.isEmpty(cognomeUtente) || TextUtils.isEmpty(mailUtente) || TextUtils.isEmpty(passwordUtente)){
                // Gestione caso di campi vuoti, si potrebbe visualizzare un messaggio d'errore
                return;
            }

            // Creazione dell'oggetto Utente
            com.example.progettoannobozza.Utente utente = new com.example.progettoannobozza.Utente(nomeUtente,cognomeUtente,mailUtente,passwordUtente);

            // Converti l'oggetto Utente in JSON
            Gson gson = new Gson();
            String utenteJson = gson.toJson(utente);

            // invio dei dati dell'utente alla main activity
            Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
            intent.putExtra("utente", utenteJson);
            startActivity(intent);

        });
    }
}