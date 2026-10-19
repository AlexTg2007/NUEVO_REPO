package com.example.jradiobutton;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.view.View;
import android.widget.RadioButton;
public class MainActivity extends AppCompatActivity {

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        RadioGroup radioGroup = findViewById(R.id.radioGroup);
       Button buttonCheck = findViewById(R.id.button_check);
       TextView textViewResult = findViewById(R.id.textViewResult);

       buttonCheck.setOnClickListener(new View.OnClickListener(){
           public void onClick(View v){
               int selectedId = radioGroup.getCheckedRadioButtonId();

               if(selectedId != -1){
                   RadioButton selectedRadioButton = findViewById(selectedId);
                   String selectedOption = selectedRadioButton.getText().toString();
                   textViewResult.setText("Seleccionado: " +selectedOption);
               }
           }
       });
    }
}