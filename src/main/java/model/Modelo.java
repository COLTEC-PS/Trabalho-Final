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


    public Modelo(ArrayList<String> texto, Date data, ArrayList<String> categorias){
        this.categorias = categorias;
        this.data = data;
        this.texto = texto;
    }

    public ArrayList<String> getTexto(){
        return this.texto;
    }

    public void setPalavraTexto(String palavra){
        this.texto.add(palavra);
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

    public String toString() {
        String str = "";
        str += this.data;
        str += "\n";
        
        for (String s : this.texto){
            str += s;
            str += " ";
        }
        str += "\n";
        for (String s : this.categorias) {
            str += s;
            str += " ";
        } 
        str += "\n";
        return str;
    }
}
