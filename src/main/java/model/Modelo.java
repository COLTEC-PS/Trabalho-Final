package model;

import java.util.ArrayList;
import controller.Entrada;
import controller.Utilitaria;
import view.MenuPrincipal;
import java.util.Date;


public class Modelo {

    private ArrayList<String> texto; // texto fornecido para entrada do diário
    private Date data; // data de registro da entrada no diário
    private ArrayList<String> categorias; // lista de categorias associadas a entrada do diário


    public Modelo(ArrayList<String> _texto, Date _data, ArrayList<String> _categorias){
        this.categorias = _categorias;
        this.data = _data;
        this.texto = _texto;
    }

    public ArrayList<String> getTexto(){
        return this.texto;
    }

    public void setTexto(ArrayList<String> texto){
        // apaga o array atual para substituí-lo
        this.texto.clear();
        this.texto = texto;
    }

    public Date getDate(){
        return this.data;
    }

    public void setDate(Date data){
        this.data = data;
    }

    public ArrayList<String> getCategorias(){
        this.texto.clear();
        return this.categorias;
    }

    public void setCategorias(ArrayList<String> categorias){
        this.categorias = categorias;
    }

}
