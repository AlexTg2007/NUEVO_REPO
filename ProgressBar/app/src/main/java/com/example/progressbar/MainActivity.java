package com.example.progressbar;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.widget.ProgressBar;

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
        setContentView(R.layout.activity_main);
        ProgressBar pb = findViewById(R.id.pbDet);
        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            int v=0;
            public void run() {
                if(v<=100){
                    pb.setProgress(v+5);
                    new Handler(Looper.getMainLooper()).postDelayed(this, 200);
                }
            }
        });
    }
}