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


        // Todos llaman a escribirDigito que evita los ceros a la izquierda
        boton0.setOnClickListener(e -> escribirDigito(textoNumero, "0"));
        boton1.setOnClickListener(e -> {
            Log.d("CALC", "Pulsado el 1");
            escribirDigito(textoNumero, "1");
        });

        //Hacemos los setOnClickListener de cada botón de la calculadora
        boton2.setOnClickListener(e -> escribirDigito(textoNumero, "2"));
        boton3.setOnClickListener(e -> escribirDigito(textoNumero, "3"));
        boton4.setOnClickListener(e -> escribirDigito(textoNumero, "4"));
        boton5.setOnClickListener(e -> escribirDigito(textoNumero, "5"));
        boton6.setOnClickListener(e -> escribirDigito(textoNumero, "6"));
        boton7.setOnClickListener(e -> escribirDigito(textoNumero, "7"));
        boton8.setOnClickListener(e -> escribirDigito(textoNumero, "8"));
        boton9.setOnClickListener(e -> escribirDigito(textoNumero, "9"));

        //Hacemos los operadores: Solo añadimos el símbolo como texto, por ahora no calculan nada
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

            /* Con esto sacamos el número que se está escribiendo actualmente, es decir,
            *Si esribes 34+35, 35 es el número actual.
            *Esto lo hacemos para asegurarnos que la comprobación de la coma se haga solo
            *sobre el número actual
             */
            String numeroActual = texto.substring(ultimoOperador + 1);



            if (numeroActual.contains(",")) {
                /*
                 *Esto se hace para que si el número ya tiene
                 * coma no se pueda añadir otra.
                 */
                return;
            }
            if (numeroActual.isEmpty()) {
                // Si no hay numero todavia se escribe un cero antes de la coma
                textoNumero.append("0,");
            } else {
                textoNumero.append(",");
            }
        });

        /*
         *Con este setOnClikListener calculamos el resultado
         *de lo que hay en pantalla
         */
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
                // Se llega aqui si hay division entre cero, o la operación esta incompleta
                textoNumero.setText("Error");
            }
        });

        // Boton AC: Limpia la pantalla y el número cero
        botonAc.setOnClickListener(e -> textoNumero.setText("0"));

        // Boton C: Borra el último carácter
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

    /*
     * Este metodo sirve para escribir los números en la pantalla
     * de la calculadora.
     */
    private void escribirDigito(TextView pantalla, String digito) {

        // Obtenemos el texto que aparece actualmente en la pantalla.
        String texto = pantalla.getText().toString();

        // Si la pantalla muestra un error, empezamos una operación
        // nueva desde cero.
        if (texto.equals("Error")) {
            texto = "0";
            pantalla.setText("0");
        }

        /*
         * Buscamos la posición del último operador matemático.
         *
         * lastIndexOf() devuelve la última posición en la que aparece
         * un símbolo. Si no encuentra el símbolo, devuelve -1.
         *
         * Math.max() permite quedarnos con la posición más alta,
         * es decir, con el operador que aparece más a la derecha.
         */
        int ultimoOperador = Math.max(
                Math.max(texto.lastIndexOf('+'), texto.lastIndexOf('-')),
                Math.max(texto.lastIndexOf('*'), texto.lastIndexOf('/'))
        );

        /*
         *Con esto obtenemos el número que estamos escribiendo actualmente.
         *
         */
        String numeroActual = texto.substring(ultimoOperador + 1);

        /*
         * Comprobamos si el número actual es exactamente cero.
         * Esto permite evitar los ceros innecesarios a la izquierda.
         */
        if (numeroActual.equals("0")) {

            // Si ya hay un cero y se pulsa otro cero, no hacemos nada.
            if (digito.equals("0")) {
                return;
            }

            /*
             * Si el número actual es cero y se pulsa otro dígito,
             * sustituimos el cero por el nuevo número.
             */
            pantalla.setText(
                    texto.substring(0, texto.length() - 1) + digito
            );

        } else {

            /*
             * Si el número actual no es solamente cero,
             * añadimos el nuevo dígito al final del texto.
             *
             * Por ejemplo, si aparece "12" y se pulsa "3",
             * la pantalla mostrará "123".
             */
            pantalla.append(digito);
        }
    }


    /*

     *
     * Este metodo recorre la operación escrita en la pantalla
     * y calcula su resultado.
     */
    private double calcularResultado(String texto) {

        /*
        *Sustituimos las comas por puntos
         */
        texto = texto.replace(",", ".") + "+";

        // Variable donde iremos acumulando el resultado.
        double resultado = 0;

        char operador = '+';

        String numero = "";

        /*
         * Recorremos todos los caracteres de la operación
         * desde la primera posición hasta la última.
         */
        for (int i = 0; i < texto.length(); i++) {

            // Obtenemos el carácter que estamos leyendo.
            char simbolo = texto.charAt(i);

            /*
             * Comprobamos si el carácter es un operador matemático.
             *
             * Cuando encontramos un operador, significa que hemos
             * terminado de leer el número anterior.
             */
            if (simbolo == '+' || simbolo == '-' ||
                    simbolo == '*' || simbolo == '/') {

                /*
                 * Convertimos el número que hemos formado en un
                 * valor decimal para poder realizar operaciones.
                 *
                 * Si el texto no contiene un número válido,
                 * parseDouble() lanzará una excepción.
                 */
                double segundo = Double.parseDouble(numero);

                /*
                 * Aplicamos al número el operador que teníamos
                 * guardado de la iteración anterior.
                 */

                // Si el operador anterior era una suma.
                if (operador == '+') {
                    resultado = resultado + segundo;

                    // Si el operador anterior era una resta.
                } else if (operador == '-') {
                    resultado = resultado - segundo;

                    // Si el operador anterior era una multiplicación.
                } else if (operador == '*') {
                    resultado = resultado * segundo;

                    // En cualquier otro caso, realizamos una división.
                } else {
                    if (segundo == 0) {
                        throw new ArithmeticException(
                                "Division entre 0"
                        );
                    }

                    // Realizamos la división.
                    resultado = resultado / segundo;
                }

                /*
                 * Guardamos el operador que acabamos de encontrar.
                 * Se utilizará cuando terminemos de leer el siguiente
                 * número.
                 */
                operador = simbolo;

                /*
                 * Vaciamos la variable para empezar a construir
                 * el siguiente número.
                 */
                numero = "";

            } else {

                /*
                 * Si el carácter no es un operador, lo añadimos
                 * al número que estamos formando.
                 * Puede ser un dígito o el punto decimal.
                 */
                numero = numero + simbolo;
            }
        }

        /*
         * Cuando termina el recorrido, devolvemos el resultado
         * calculado.
         */
        return resultado;
    }
}