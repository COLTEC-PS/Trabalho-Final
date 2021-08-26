package model;

import java.util.ArrayList;
import controller.Entrada;
import controller.Utilitaria;
import view.MenuPrincipal;
import java.util.Date;


public class Modelo {

    private String texto;
    private Date data;
    private ArrayList<String> categorias;


    public Modelo(String _texto, Date _data, ArrayList<String> _categorias){
        this.categorias = _categorias;
        this.data = _data;
        this.texto = _texto;
    }



}
