package com.example.exempleemail;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context = this;

        //On recupere les widgets
        final EditText txtObjet = findViewById(R.id.txtObjet);
        final EditText txtMessage = findViewById(R.id.txtMessage);
        final EditText txtDestinataire = findViewById(R.id.txtDestinataire);

        Button btnSendEmail = findViewById(R.id.btnSendEmail);

        btnSendEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //On instancie les widgets dans le btn
                String objet = txtObjet.getText().toString().trim();
                String message = txtMessage.getText().toString().trim();
                String destinataire = txtDestinataire.getText().toString().trim();

                if(!objet.isEmpty() && !message.isEmpty() && !destinataire.isEmpty())
                {
                    sendEmail(objet,message,destinataire);
                }

                else
                {
                    //Toast
                }
            }
        });
    }

    private void sendEmail(String objet, String message,String destinataire)
    {
        Intent emailIntent = new Intent(Intent.ACTION_SEND);
        emailIntent.setData(Uri.parse("mailto: " + destinataire));
        emailIntent.setType("test/plain");

        //On passe nos paramètres
        emailIntent.putExtra(Intent.EXTRA_EMAIL,destinataire);
        emailIntent.putExtra(Intent.EXTRA_SUBJECT,objet);
        emailIntent.putExtra(Intent.EXTRA_TEXT,message);

        try {
            startActivity(emailIntent);
            Toast.makeText(context,"Email Envoyé",Toast.LENGTH_LONG).show();
        }
        catch (Exception ex)
        {
            String messageError = ex.getMessage();
        }
    }

}
