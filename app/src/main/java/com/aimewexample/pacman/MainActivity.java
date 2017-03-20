package com.aimewexample.pacman;

import android.app.Dialog;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Build;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import com.aimewexample.pacman.adapters.PacmanGridAdapter;
import com.aimewexample.pacman.models.Nodo;
import com.aimewexample.pacman.models.Nodos;
import com.aimewexample.pacman.utils.HandlerGrid;
import com.aimewexample.pacman.utils.MetodoEnProfundidad;
import com.aimewexample.pacman.utils.Profundidad;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private ArrayList<Nodo> nodes;
    private GridView gridView;
    private PacmanGridAdapter adapter;
    private HandlerGrid handlerGrid;

    private Dialog dialog;
    private Button buttonDialogPlayAgain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        hideElements();
        setContentView(R.layout.activity_main);

        //inicialzar lista
        initArray();

        //mostrar cuadro de opciones
        showOptions();

        //configurar gridView
        gridView = (GridView)findViewById(R.id.grid_view);
        adapter = new PacmanGridAdapter(this, nodes, getScreenSize());
        gridView.setAdapter(adapter);

        //desactivar el scroll del gridView
        gridView.setOnTouchListener(new android.view.View.OnTouchListener(){
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return event.getAction() == MotionEvent.ACTION_MOVE;
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.button_dialog_play_again:
                dialog.dismiss();
                ArrayList<Nodo> recorrido = new ArrayList<>();
                recorrido.add(new Nodo(1));
                recorrido.add(new Nodo(20));
                recorrido.add(new Nodo(46));
                recorrido.add(new Nodo(38));
                handlerGrid = new HandlerGrid(this, gridView, recorrido);
                handlerGrid.start();
                break;
        }
    }

    //metodo que calcula el tamaño de la pantalla del dispositivo
    public int getScreenSize() {
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        int width = metrics.widthPixels;
        int height = metrics.heightPixels;
        return height;
    }

    public void showOptions(){
        dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_options);

        //referenciar elementos
        buttonDialogPlayAgain = (Button)dialog.findViewById(R.id.button_dialog_play_again);
        buttonDialogPlayAgain.setOnClickListener(this);

        dialog.show();
    }

    //metodo que oculta la statusBar y navigationBar
    public void hideElements() {
        if(Build.VERSION.SDK_INT < 19){
            View v = this.getWindow().getDecorView();
            v.setSystemUiVisibility(android.view.View.GONE);
        } else {
            //for higher api versions.
            View decorView = getWindow().getDecorView();
            int uiOptions = android.view.View.SYSTEM_UI_FLAG_HIDE_NAVIGATION|android.view.View.SYSTEM_UI_FLAG_FULLSCREEN;
            decorView.setSystemUiVisibility(uiOptions);
        }
    }

    public void initArray(){
        nodes = new ArrayList();
        for (int i = 0; i <64 ; i++) {
            nodes.add(new Nodo(i));
        }
    }
}
