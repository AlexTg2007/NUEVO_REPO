package com.example.gestio_cuentas_bar;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.frame_layout);
        EditText textoCuenta = findViewById(R.id.textoCuenta);
        textoCuenta.setY(300f);
        TextView textoPropina = findViewById(R.id.textoPropina);
        textoPropina.setY(-600f);
        CheckBox propina = findViewById(R.id.propina);
        propina.setY(-510f);
        SeekBar barraPropina = findViewById(R.id.barraPropina);
        barraPropina.setY(-400f);

        //Ponemos esto para cuando inicia la aplicación
        barraPropina.setVisibility(
                propina.isChecked() ? View.VISIBLE : View.GONE
        );
        TextView textoPorcentaje = findViewById(R.id.textoPorcentaje);
        textoPorcentaje.setY(-340f);
        textoPorcentaje.setVisibility(propina.isChecked() ? View.VISIBLE : View.GONE);

        /*
        *Esto hace que una vez hayas seleccionado por
        * primera vez la casilla, luego puedas
        * desseleccionarla y haciendo que el seekbar
        * se vuelva invisible de nuevo
         */
        propina.setOnCheckedChangeListener(((buttonView, isChecked) -> {
            barraPropina.setVisibility(
                    isChecked ? View.VISIBLE : View.GONE

            );

            textoPorcentaje.setVisibility(isChecked ? View.VISIBLE : View.GONE);
            int porcentaje = barraPropina.getProgress();
            textoPorcentaje.setText("Porcentaje:" + porcentaje + "%" );



            // Actualizar el porcentaje cuando se mueve el SeekBar
            barraPropina.setOnSeekBarChangeListener(
                    new SeekBar.OnSeekBarChangeListener() {

                        @Override
                        public void onProgressChanged(
                                SeekBar seekBar, int progress, boolean fromUser) {

                            textoPorcentaje.setText(
                                    "Porcentaje: " + progress + "%"
                            );
                        }

                        @Override
                        public void onStartTrackingTouch(SeekBar seekBar) {
                        }

                        @Override
                        public void onStopTrackingTouch(SeekBar seekBar) {
                        }
                    }
            );
        }));



    }
}