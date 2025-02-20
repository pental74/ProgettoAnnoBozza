package com.example.progettoannobozza;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

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
            // Avvio dell'Activity LoginActivity
            startActivity(new Intent(MainActivity.this, LoginActivity.class));
        });

        // Click listener per il pulsante di REGISTRAZIONE
        registerButton.setOnClickListener(v -> {
            // Avvio dell'Activity RegisterActivity
            startActivity(new Intent(MainActivity.this, RegisterActivity.class));
        });


    }
}
