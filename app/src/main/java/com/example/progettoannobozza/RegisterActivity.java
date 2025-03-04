package com.example.progettoannobozza;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Button;

import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

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
        EditText nome = findViewById(R.id.editTextNome);
        EditText cognome = findViewById(R.id.editTextCognome);
        EditText numeroTelefono = findViewById(R.id.editTextTelefono);
        EditText username = findViewById(R.id.editUsername);
        EditText email = findViewById(R.id.editTextEmail);
        EditText password = findViewById(R.id.editTextPassword);
        EditText confirmPassword = findViewById(R.id.editTextConfirmPassword);
        DatePicker dataNascita = findViewById(R.id.datePickerBirthDate);
        Spinner genere = findViewById(R.id.spinnerGender);
        CheckBox checkBoxTerms = findViewById(R.id.checkBoxTerms);
        Button registerButton = findViewById(R.id.buttonRegister);

        // Consente la visualizzazione di ciò che si scrive nelle ultime EditText
        // Riferimento allo ScrollView
        final ScrollView scrollView = findViewById(R.id.scrollView);

        // Array di EditText per semplificare il ciclo
        EditText[] editTexts = {
                numeroTelefono, username,
                password, confirmPassword, email
        };

        // Imposta il listener per ogni EditText
        for (final EditText editText : editTexts) {
            editText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View v, boolean hasFocus) {
                    if (hasFocus) {
                        // Usa un post per assicurarti che la UI sia aggiornata
                        scrollView.post(new Runnable() {
                            @Override
                            public void run() {
                                scrollView.smoothScrollTo(0, editText.getBottom());
                            }
                        });
                    }
                }
            });
        }

        // Clicklistener per il pulsante di REGISTRAZIONE
        registerButton.setOnClickListener(v -> {

            // Salvataggio dati inseriti in un oggetto Utente
            String nomeUtente = nome.getText().toString();
            String cognomeUtente = cognome.getText().toString();
            String mailUtente = email.getText().toString();
            String passwordUtente = password.getText().toString();
            String confirmPasswordUtente = confirmPassword.getText().toString();
            String dataNascitaUtente = dataNascita.getDayOfMonth() + "/" + (dataNascita.getMonth() + 1) + "/" + dataNascita.getYear();
            String genereUtente = genere.getSelectedItem().toString();
            String numeroTelefonoUtente = numeroTelefono.getText().toString();

            // Dati di prova
            nome.setText("Marco");
            cognome.setText("Aquilanti");
            password.setText("MarcoMarco");
            confirmPassword.setText("MarcoMarco");
            numeroTelefono.setText("3333333333");
            email.setText("marco.aquilanti@mgail.com");
            genere.setSelection(1);
            dataNascita.updateDate(2000, 1, 1);
            username.setText("marcoaquilanti");

            if (TextUtils.isEmpty(nomeUtente) || TextUtils.isEmpty(cognomeUtente) || TextUtils.isEmpty(mailUtente) || TextUtils.isEmpty(passwordUtente) ||
                    TextUtils.isEmpty(confirmPasswordUtente) || TextUtils.isEmpty(dataNascitaUtente) || TextUtils.isEmpty(genereUtente) ||
                    TextUtils.isEmpty(numeroTelefonoUtente)){
                // Gestione caso di campi vuoti, si potrebbe visualizzare un messaggio d'errore
                Toast.makeText(RegisterActivity.this, "Inserisci tutti i dati", Toast.LENGTH_LONG).show();

                return; // esce ritorna, non effettua registrazione
            }

            // Controlli su password e checkbox termini di accettazione
            boolean password_adeguata = password.getText().toString().length() >= 8;
            boolean password_uguali = password.getText().toString().equals(confirmPassword.getText().toString());
            boolean check_selezionato = checkBoxTerms.isChecked();

            if (password_uguali && password_adeguata && check_selezionato) {

                // Visualizzazione dei dati in un toast
                String datiUtente = "Username: " + " registrato!\n Effettua il LOGIN";
                Toast.makeText(RegisterActivity.this, datiUtente, Toast.LENGTH_LONG).show();

                // Creazione dell'oggetto Utente
                Utente utente = new Utente(nomeUtente, cognomeUtente, mailUtente, passwordUtente, dataNascitaUtente, genereUtente, numeroTelefonoUtente);

                // Converti l'oggetto Utente in JSON
                Gson gson = new Gson();
                String utenteJson = gson.toJson(utente);

                // invio dei dati dell'utente alla main activity
                Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                intent.putExtra("utente", utenteJson);
                startActivity(intent);

            }
            else if (!password_uguali) {
                Toast.makeText(RegisterActivity.this, "Password non coincidenti", Toast.LENGTH_LONG).show();
            }
            else if (!password_adeguata) {
                Toast.makeText(RegisterActivity.this, "Password troppo corta", Toast.LENGTH_LONG).show();
            }
            else {
                Toast.makeText(RegisterActivity.this, "Devi accettare i termini e le condizioni", Toast.LENGTH_LONG).show();
            }
        });
    }
}