package com.example.tema5_adapatadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class Adaptador extends ArrayAdapter<Datos> {
    private Datos[] datos;

    public Adaptador(Context context, Datos[] datos){
        super(context,R.layout.linear , datos);
        this.datos=datos;
    }

    public View getView(int position, View convertView, ViewGroup parent){
        LayoutInflater mostrado = LayoutInflater.from(getContext());
        View elemento = mostrado.inflate(R.layout.linear, parent, false);

        TextView text1 = elemento.findViewById(R.id.text1);
        TextView text2 = elemento.findViewById(R.id.text2);

        text1.setText(datos[position].getTexto1());
        text2.setText(datos[position].getTexto2());
        return elemento;
    }
}
