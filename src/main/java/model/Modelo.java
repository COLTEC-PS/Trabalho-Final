package model;

import java.util.ArrayList;
import controller.Entrada;
import controller.Utilitaria;
import view.MenuPrincipal;
import java.util.Date;


public class Modelo {

    private ArrayList<String> texto;
    private Date data;
    private ArrayList<String> categorias;


    public Modelo(ArrayList<String> _texto, Date _data, ArrayList<String> _categorias){
        this.categorias = _categorias;
        this.data = _data;
        this.texto = _texto;
    }

    public ArrayList<String> getTexto(){
        return this.texto;
    }

    public Date getDate(){
        return this.data;
    }

    public ArrayList<String> getCategorias(){
        return this.categorias;
    }


}
