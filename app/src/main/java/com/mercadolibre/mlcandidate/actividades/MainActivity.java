package com.mercadolibre.mlcandidate.actividades;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.mercadolibre.mlcandidate.R;

public class MainActivity extends AppCompatActivity {
    private Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                finish();
                goToConsultarProductos();
            }
        }, 1500);
    }

    private void goToConsultarProductos() {
        Intent pantallaInicio = new Intent(MainActivity.this,
                ConsultarProductos.class);
        pantallaInicio.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(pantallaInicio);
        finish();
    }
}