package com.example.tema4_tooglebutton;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.ToggleButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);


    }
    protected void onStart(){
        super.onStart();
        TextView miTexto = findViewById(R.id.miTexto);
        ToggleButton toggleButton1 = findViewById(R.id.miToggleButton);
        toggleButton1.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                miTexto.setText("Se ha pulsado el botón");

            }


        });
    }

}