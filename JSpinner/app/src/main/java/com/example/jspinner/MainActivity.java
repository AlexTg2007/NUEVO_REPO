package com.example.jspinner;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

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

            //setContentView(R.id.spinner);
            Spinner miSpinner = (Spinner) findViewById(R.id.miSpinner);
            String [] valores = {"Star Wars", "Reservoir dogs", "El Club de la lucha", "Snach: Cerdos y Diamantes"};

            miSpinner.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, valores));


    }
    miSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){


    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l ){
        System.out.println("Has seleccionado el valor:" + adapterView.getItemAtPosition(position));

    }

    public void onNothingSelected(AdapterView<?> adapterView){
        System.out.println("No has seleccionado nada");
    }
}

}