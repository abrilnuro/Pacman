package com.aimewexample.pacman.utils;

import android.content.Context;
import android.os.Handler;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import com.aimewexample.pacman.R;
import com.aimewexample.pacman.models.Nodos;

import java.util.ArrayList;

/**
 * Created by aimew on 19/03/2017.
 */

public class HandlerGrid {

    private boolean started = false;
    private Handler handler = new Handler();
    private int position = 0;

    private GridView gridView;
    private ArrayList<Nodos> nodes;
    private Context context;
    private View rowView;

    public HandlerGrid(Context context, GridView gridView, ArrayList<Nodos> nodes) {
        this.context = context;
        this.gridView = gridView;
        this.nodes = nodes;
    }

    public void initHandler(){
        start();
    }

    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            rowView = gridView.getChildAt(nodes.get(position).getNumero());
            ImageView image = (ImageView) rowView.findViewById(R.id.image_item_pacman);
            image.setVisibility(View.VISIBLE);
            image.animate().alpha(0f).setDuration(800);
            if(started) {
                position = position + 1;
                start();
            }
            if (position == nodes.size()){
                stop();
                Toast.makeText(context, "FINISH", Toast.LENGTH_SHORT).show();

            }
        }
    };

    public void start() {
        started = true;
        handler.postDelayed(runnable, 800);
    }

    public void stop() {
        started = false;
        handler.removeCallbacks(runnable);
    }
}
