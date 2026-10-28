package com.example.calculadorabsica_alejandrotorresgonzlez;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.grid);

        // Enlazar las vistas del layout con variables de Java
        TextView textoNumero = findViewById(R.id.miTextView);
        Button boton0 = findViewById(R.id.boton0);
        Button boton1 = findViewById(R.id.boton1);
        Button boton2 = findViewById(R.id.boton2);
        Button boton3 = findViewById(R.id.boton3);
        Button boton4 = findViewById(R.id.boton4);
        Button boton5 = findViewById(R.id.boton5);
        Button boton6 = findViewById(R.id.boton6);
        Button boton7 = findViewById(R.id.boton7);
        Button boton8 = findViewById(R.id.boton8);
        Button boton9 = findViewById(R.id.boton9);
        Button botonBarra = findViewById(R.id.botonBarra);
        Button botonAste = findViewById(R.id.botonAste);
        Button botonComa = findViewById(R.id.botonComa);
        Button botonMenos = findViewById(R.id.botonMenos);
        Button botonMas = findViewById(R.id.botonMas);
        Button botonIgual = findViewById(R.id.botonIgual);
        Button botonAc = findViewById(R.id.botonAc);
        Button botonC = findViewById(R.id.botonC);

        // Botones numericos
        // Todos llaman a escribirDigito que evita los ceros a la izquierda
        boton0.setOnClickListener(e -> escribirDigito(textoNumero, "0"));
        boton1.setOnClickListener(e -> {
            Log.d("CALC", "Pulsado el 1");
            escribirDigito(textoNumero, "1");
        });
        boton2.setOnClickListener(e -> escribirDigito(textoNumero, "2"));
        boton3.setOnClickListener(e -> escribirDigito(textoNumero, "3"));
        boton4.setOnClickListener(e -> escribirDigito(textoNumero, "4"));
        boton5.setOnClickListener(e -> escribirDigito(textoNumero, "5"));
        boton6.setOnClickListener(e -> escribirDigito(textoNumero, "6"));
        boton7.setOnClickListener(e -> escribirDigito(textoNumero, "7"));
        boton8.setOnClickListener(e -> escribirDigito(textoNumero, "8"));
        boton9.setOnClickListener(e -> escribirDigito(textoNumero, "9"));

        // Operadores
        // Solo añaden el simbolo al final del texto y no calculan nada todavia
        botonBarra.setOnClickListener(e -> textoNumero.append("/"));
        botonAste.setOnClickListener(e -> textoNumero.append("*"));
        botonMenos.setOnClickListener(e -> textoNumero.append("-"));
        botonMas.setOnClickListener(e -> textoNumero.append("+"));

        // Coma decimal solo una por cada numero
        botonComa.setOnClickListener(e -> {
            String texto = textoNumero.getText().toString();

            // Si la pantalla muestra un error se empieza de cero
            if (texto.equals("Error")) {
                texto = "0";
                textoNumero.setText("0");
            }

            // Posicion del ultimo operador o menos 1 si no hay ninguno
            int ultimoOperador = Math.max(
                    Math.max(texto.lastIndexOf('+'), texto.lastIndexOf('-')),
                    Math.max(texto.lastIndexOf('*'), texto.lastIndexOf('/')));

            // Numero que se esta escribiendo que es lo que hay detras del ultimo operador
            String numeroActual = texto.substring(ultimoOperador + 1);

            if (numeroActual.contains(",")) {
                // Ese numero ya tiene coma y no se añade otra
                return;
            }
            if (numeroActual.isEmpty()) {
                // Si no hay numero todavia se escribe un cero antes de la coma
                textoNumero.append("0,");
            } else {
                textoNumero.append(",");
            }
        });

        // Boton igual calcula el resultado de lo que hay en pantalla
        botonIgual.setOnClickListener(e -> {
            String texto = textoNumero.getText().toString();
            try {
                double resultado = calcularResultado(texto);

                // Si el resultado es entero se quitan los decimales y si no se usa coma
                if (resultado == Math.rint(resultado)) {
                    textoNumero.setText(String.valueOf((long) resultado));
                } else {
                    textoNumero.setText(String.valueOf(resultado).replace(".", ","));
                }
            } catch (Exception ex) {
                // Se llega aqui si hay division entre cero o la operacion esta incompleta
                textoNumero.setText("Error");
            }
        });

        // Boton AC limpia la pantalla y la deja en cero
        botonAc.setOnClickListener(e -> textoNumero.setText("0"));

        // Boton C borra el ultimo caracter
        botonC.setOnClickListener(e -> {
            String texto = textoNumero.getText().toString();
            if (texto.length() > 1 && !texto.equals("Error")) {
                // Se queda con el texto sin el ultimo caracter
                textoNumero.setText(texto.substring(0, texto.length() - 1));
            } else {
                // Si solo habia un caracter o un error la pantalla queda en cero
                textoNumero.setText("0");
            }
        });
    }

    // Metodo 1 añade un digito a la pantalla sin permitir ceros a la izquierda
    private void escribirDigito(TextView pantalla, String digito) {
        String texto = pantalla.getText().toString();

        // Si venimos de un error empezamos de cero
        if (texto.equals("Error")) {
            texto = "0";
            pantalla.setText("0");
        }

        // Posicion del ultimo operador o menos 1 si no hay ninguno
        int ultimoOperador = Math.max(
                Math.max(texto.lastIndexOf('+'), texto.lastIndexOf('-')),
                Math.max(texto.lastIndexOf('*'), texto.lastIndexOf('/')));

        // Numero que se esta escribiendo ahora que es lo que hay tras el ultimo operador
        String numeroActual = texto.substring(ultimoOperador + 1);

        if (numeroActual.equals("0")) {
            if (digito.equals("0")) {
                // Ya hay un cero y no se permite escribir otro
                return;
            }
            // El numero es solo un cero y se pulsa otro digito asi que se sustituye
            pantalla.setText(texto.substring(0, texto.length() - 1) + digito);
        } else {
            // Caso normal se añade el digito al final
            pantalla.append(digito);
        }
    }

    // Metodo 2 recorre el texto de izquierda a derecha y va calculando
    private double calcularResultado(String texto) {
        // Java usa punto decimal y no coma
        // Se añade un mas final para que el ultimo numero tambien se calcule
        texto = texto.replace(",", ".") + "+";

        double resultado = 0;
        char operador = '+';
        String numero = "";

        for (int i = 0; i < texto.length(); i++) {
            char simbolo = texto.charAt(i);

            if (simbolo == '+' || simbolo == '-' || simbolo == '*' || simbolo == '/') {
                // Termina un numero y se aplica el operador anterior
                double segundo = Double.parseDouble(numero);

                if (operador == '+') {
                    resultado = resultado + segundo;
                } else if (operador == '-') {
                    resultado = resultado - segundo;
                } else if (operador == '*') {
                    resultado = resultado * segundo;
                } else {
                    // Si se divide entre cero se lanza una excepcion y el boton igual muestra el error
                    if (segundo == 0) {
                        throw new ArithmeticException("Division entre 0");
                    }
                    resultado = resultado / segundo;
                }

                operador = simbolo;
                numero = "";
            } else {
                // Es un digito o un punto y se va formando el numero
                numero = numero + simbolo;
            }
        }
        return resultado;
    }
}