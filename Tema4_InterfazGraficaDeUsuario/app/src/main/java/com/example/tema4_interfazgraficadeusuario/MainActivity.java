package com.example.tema4_interfazgraficadeusuario;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    TextView miTexto;
    Animation miAnimacion;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        Button boton2 = findViewById(R.id.button1);
        Button boton3 = findViewById(R.id.button2);
        Button boton4= findViewById(R.id.button3);
        boton2.setOnClickListener(this);
        boton3.setOnClickListener(this);
        boton4.setOnClickListener(this);
        miTexto = findViewById(R.id.texto);
    }
    protected void onStart(){
        super.onStart();

        miTexto.setText("Nuevo texto a mostrar");

        miTexto.setTextColor(Color.RED);
        miTexto.setTextSize(24);
        miTexto.setTypeface(Typeface.SANS_SERIF, Typeface.ITALIC);

    }

    public void onClick(View view){
        int id = view.getId();
        if(id == R.id.button1){
          AnimationUtils.loadAnimation(this,R.anim.animation);
            miAnimacion.setRepeatMode(Animation.RESTART);
            miAnimacion.setRepeatCount(20);
            miTexto.startAnimation(miAnimacion);
        }
        if (id == R.id.button2){
            miAnimacion= AnimationUtils.loadAnimation(this,R.anim.rotacion);
            miAnimacion.setRepeatMode(Animation.RESTART);
            miAnimacion.setRepeatCount(20);
            miTexto.startAnimation(miAnimacion);
        }
        if(id == R.id.button3){
            //Animation animacionActual =miTexto.getAnimation();
            miTexto.clearAnimation();
            miAnimacion.cancel();
            miAnimacion = null;

        }
    }
;}