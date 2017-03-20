package com.aimewexample.pacman.utils;

import android.util.Log;

import com.aimewexample.pacman.models.Nodo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by aimew on 16/03/2017.
 */

public class Profundidad {

    public static Nodo[] profundidad;
    public static int tamaño;
    public static int numeroNodos;
    public static Nodo[] NodoVisitados;
    public static int contadorVisitados=0;
    public static List<Nodo> recorridoFinal = new ArrayList<Nodo>();

    public static void crearArbolProfundidad()
    {
        Log.i("PROFUNDIDAD:", "Creando Arbol");
        contadorVisitados=0;
        tamaño = 8;
        numeroNodos = tamaño*tamaño;
        profundidad = new Nodo[numeroNodos];
        NodoVisitados = new Nodo[numeroNodos+1];
        CrearNodos();
        for(int i=0;i<profundidad.length;i++)
        {
            int izq = 0,der = 0;
            if(!lateralesIzquierda(i+1))
            {
                profundidad[i].setIzquierda(buscaNodo((i+1)+tamaño));
                izq = (i+1)+tamaño;
            }
            if(!lateralesDerecho(i+1))
            {
                profundidad[i].setDerecha(buscaNodo((i+1)+1));
                der = (i+1)+1;
            }
        }
        busquedaProfundidad();
    }

    public static Nodo buscaNodo(int num)
    {
        for(int i=0;i<profundidad.length;i++)
        {
            if(profundidad[i].getNumero() == num)
            {
                return profundidad[i];
            }
        }
        return null;
    }

    public static void CrearNodos()
    {
        for(int i=0;i<profundidad.length; i++)
        {
            Nodo x = new Nodo(i+1,null,null);
            profundidad[i] = x;
        }
    }

    public static boolean lateralesDerecho(int i)
    {
        boolean band = false;
        int[] numeros = new int[tamaño];
        for(int j=0;j<tamaño;j++)
        {
            numeros[j] = tamaño*(j+1);
        }
        for(int j=0;j<numeros.length;j++)
        {
            if(numeros[j]==i)
            {
                band = true;
            }
        }
        return band;
    }

    public static boolean lateralesIzquierda(int i)
    {
        boolean band = false;
        int[] numeros = new int[tamaño];
        for(int j=0;j<tamaño;j++)
        {
            numeros[j] = numeroNodos-j;
        }
        for(int j=0;j<numeros.length;j++)
        {
            if(numeros[j]==i)
            {
                band = true;
            }
        }
        return band;
    }

    public static void busquedaProfundidad()
    {
         int meta = 51;
        coreProfundiad(meta,profundidad[0]);
        Log.i("BUSCAR:", String.valueOf(meta));
    }

    public static void coreProfundiad(int meta,Nodo nodoActual)
    {
        //Log.i("CORE PROFUNDIDAD:", "Entró");
        boolean band = false;
        if(nodoActual == null)
        {
            return;
        }
        recorridoFinal.add(nodoActual);
        if(nodoActual.getIzquierda()!=null && nodoActual.getDerecha() == null){
            band= false;
        }
        if(nodoActual.getNumero() == meta)
        {
            band = true;
            Log.i("ARRAY RECORRIDO FINAL :", recorridoFinal.toString());
            crearArbolProfundidad();
        }
        if(nodoActual.getIzquierda() != null && nodoActual.getDerecha()!= null)
        {
            if(!buscaVisitadocore(nodoActual.getIzquierda()))
            {
                coreProfundiad(meta,nodoActual.getIzquierda());
            }
        }
        NodoVisitados[contadorVisitados] = nodoActual;
        contadorVisitados++;

        coreProfundiad(meta,nodoActual.getDerecha());
        recorridoFinal.add(nodoActual);
        Log.i("RECORRIDO FINAL:", String.valueOf(nodoActual.getNumero()));
    }

    public static boolean buscaVisitadocore(Nodo x)
    {
        boolean band = false;
        for(Nodo i : NodoVisitados)
        {
            if(i == null)
            {

            }
            else if(i.getNumero()==x.getNumero())
            {
                band = true;
            }
        }
        return band;
    }
}
