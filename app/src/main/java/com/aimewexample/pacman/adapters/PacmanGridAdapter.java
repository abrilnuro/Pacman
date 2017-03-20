package com.aimewexample.pacman.adapters;

import android.content.Context;
import android.graphics.Color;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.aimewexample.pacman.R;
import com.aimewexample.pacman.models.Nodo;
import com.aimewexample.pacman.models.Nodos;

import java.util.ArrayList;

/**
 * Created by aimew on 16/03/2017.
 */

public class PacmanGridAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<Nodo> nodes;
    private ImageView imageItemPacman;
    private int heightScreen;

    @Override
    public int getCount() {
        return nodes.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }


    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        //inflar el view
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(R.layout.item_grid_pacman, null);

        //ajustar los view al tamaño de la pantalla
        view.setMinimumHeight(heightScreen/9);

        //referenciar elementos
        imageItemPacman = (ImageView) view.findViewById(R.id.image_item_pacman);

        return view;
    }

    public PacmanGridAdapter(Context context, ArrayList<Nodo> nodes, int heightScreen) {
        this.context = context;
        this.nodes = nodes;
        this.heightScreen = heightScreen;
    }
}
