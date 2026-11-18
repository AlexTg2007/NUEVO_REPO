package com.example.tema5_baseadapter;

import android.os.Bundle;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView listView;
    private CustomAdapter adapter;
    private List<Datos> items;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.listView);

        // Llenar la lista con datos de ejemplo
        items = new ArrayList<>();
        items.add(new Datos(R.drawable.coche, "Título 1", "Contenido del elemento 1"));
        items.add(new Datos(R.drawable.italia, "Título 2", "Contenido del elemento 2"));
        items.add(new Datos(R.drawable.coche, "Título 3", "Contenido del elemento 3"));
        items.add(new Datos(R.drawable.italia, "Título 4", "Contenido del elemento 4"));

        // Configurar el adaptador personalizado
        adapter = new CustomAdapter(this, items);
        listView.setAdapter(adapter);

    }
}



