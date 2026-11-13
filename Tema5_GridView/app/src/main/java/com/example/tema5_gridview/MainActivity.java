package com.example.tema5_gridview;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    TextView textoSeleccionado;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.linear);

        List<String> data = Arrays.asList("Elemento 1", "Elemento 2", "Elemento 3", "Element 4");
        GridView listado = findViewById(R.id.gridView);
        ArrayAdapter <String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, data);
        listado.setAdapter(adapter);

        textoSeleccionado = findViewById(R.id.textoSeleccionado);

        listado.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            public void onItemClick(AdapterView <?> parent, View view, int posicion, long l){
                textoSeleccionado.setText(parent.getItemAtPosition(posicion).toString());
            }
        });
    }
}