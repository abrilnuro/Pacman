package com.aimewexample.pacman;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.aimewexample.pacman.utils.Profundidad;

public class ProfundidadActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profundidad);

        Profundidad.crearArbolProfundidad();
    }
}
