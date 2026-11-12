package com.example.tema5_listadosymenus;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.linear);
       //Datos de ejemplo para la lista
        List<String> data = Arrays.asList("España", "Francia",
                "Alemania", "Inglaterra", "Irlanda", "Belgica",
                "Dinamarca", "Noruega", "Italia", "Grecia"
        );

        ListView listView = findViewById(R.id.listview);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, data);
        listView.setAdapter(adapter);

        textoSeleccionado = findViewById(R.id.textoSeleccionado);


        listView.setOnItemClickListener((parent, view, position, id)-> {
            textoSeleccionado.setText(parent.getItemAtPosition(position).toString());
        });
    }
}