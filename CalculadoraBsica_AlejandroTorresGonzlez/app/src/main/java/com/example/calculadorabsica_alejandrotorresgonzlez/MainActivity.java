package com.example.calculadorabsica_alejandrotorresgonzlez;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

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
        setContentView(R.layout.grid);

        TextView textoNumero = findViewById(R.id.miTextView);
        Button boton1 = findViewById(R.id.boton1);
        Button boton2 = findViewById(R.id.boton2);
        Button boton3 = findViewById(R.id.boton3);
        Button botonBarra = findViewById(R.id.botonBarra);
        Button botonAste = findViewById(R.id.botonAste);
        Button botonComa = findViewById(R.id.botonComa);
        Button botonMenos = findViewById(R.id.botonMenos);
        Button botonMas = findViewById(R.id.botonMas);
        Button botonIgual = findViewById(R.id.botonIgual);
        Button boton4 = findViewById(R.id.boton4);
        Button boton5 = findViewById(R.id.boton5);
        Button boton6 = findViewById(R.id.boton6);
        Button boton7 = findViewById(R.id.boton7);
        Button boton8 = findViewById(R.id.boton8);
        Button boton9 = findViewById(R.id.boton9);
        Button boton0 = findViewById(R.id.boton0);
        Button botonAc = findViewById(R.id.botonAc);
        Button botonC = findViewById(R.id.botonC);

        boton1.setOnClickListener(e -> {
            Log.d("CALC", "Pulsado el 1");
            textoNumero.append("1");
        });
        boton2.setOnClickListener(e -> {
            textoNumero.append("2");
        });
        boton3.setOnClickListener(e -> {
            textoNumero.append("3");
        });
        boton4.setOnClickListener(e -> {
            textoNumero.append("4");
        });
        boton5.setOnClickListener(e -> {
            textoNumero.append("5");
        }); boton6.setOnClickListener(e -> {
            textoNumero.append("6");
        });
        boton7.setOnClickListener(e -> {
            textoNumero.append("7");
        });
        boton8.setOnClickListener(e -> {
            textoNumero.append("8");
        }); boton9.setOnClickListener(e -> {
            textoNumero.append("9");
        });
        botonBarra.setOnClickListener(e -> {
            textoNumero.append("/");
        });
        botonAste.setOnClickListener(e -> {
            textoNumero.append("*");
        });
        botonMenos.setOnClickListener(e -> {
            textoNumero.append("-");
        });
        botonMas.setOnClickListener(e -> {
            textoNumero.append("+");
        });
        botonAc.setOnClickListener(e -> {
            textoNumero.setText("");
        });

        botonC.setOnClickListener(e -> {
            String texto = textoNumero.getText().toString();
            if(texto.length()>1){
                textoNumero.setText(texto.substring(0, texto.length()-1));
            }else{
                textoNumero.setText("");
            }
        });


    }
    private void escribirDigito(TextView tv, String digito) {
        String texto = tv.getText().toString();

        // Posición del último operador (-1 si no hay ninguno)
        int idx = Math.max(
                Math.max(texto.lastIndexOf('+'), texto.lastIndexOf('-')),
                Math.max(texto.lastIndexOf('*'), texto.lastIndexOf('/')));

        // Número que se está escribiendo ahora
        String actual = texto.substring(idx + 1);

        if (actual.equals("0")) {
            if (digito.equals("0")) {
                return; // no permite 00, 000...
            }
            // Sustituye el 0 por el nuevo dígito (05 -> 5)
            tv.setText(texto.substring(0, texto.length() - 1) + digito);
        } else {
            tv.append(digito);
        }
    }
}