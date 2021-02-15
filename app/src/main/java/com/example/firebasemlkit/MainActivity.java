package com.example.firebasemlkit;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.ml.naturallanguage.FirebaseNaturalLanguage;
import com.google.firebase.ml.naturallanguage.languageid.FirebaseLanguageIdentification;

public class MainActivity extends AppCompatActivity {

    //creating variables for our image view, text view and two buttons.
    private EditText edtLanguage;
    private TextView languageCodeTV;
    private Button detectLanguageBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //on below line we are initializing our variables.
        edtLanguage = findViewById(R.id.idEdtLanguage);
        languageCodeTV = findViewById(R.id.idTVDetectedLanguageCode);
        detectLanguageBtn = findViewById(R.id.idBtnDetectLanguage);
        //adding on click listener for button
        detectLanguageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //getting string from our edit text.
                String edt_string = edtLanguage.getText().toString();
                //calling method to detect language.
                detectLanguage(edt_string);
            }
        });
    }

    private void detectLanguage(String string) {
        //initializing our firebase language detection.
        FirebaseLanguageIdentification languageIdentifier =
                FirebaseNaturalLanguage.getInstance().getLanguageIdentification();
        //adding method to detect language using identify language method.
        languageIdentifier.identifyLanguage(string).addOnSuccessListener(new OnSuccessListener<String>() {
            @Override
            public void onSuccess(String s) {
                //below line we are setting our language code to our text view.
                languageCodeTV.setText(s);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                //handling error method and displaying a toast message.
                Toast.makeText(MainActivity.this, "Fail to detect language : \n" + e, Toast.LENGTH_SHORT).show();
            }
        });
    }
}