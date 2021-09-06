package controller; 

import java.util.Vector;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import model.Exportacao;
import model.Modelo;


public class Entrada {      

    Modelo model;

    public Entrada(String _date){
        Date date = Utilitaria.CONVERT_STRING_TO_DATE(_date);  
        this.model = new Modelo(new ArrayList<String>(), date, new ArrayList<String>());      
    }

    public void add_word(String word){
        ArrayList<String> aux = this.model.getTexto();
        aux.add(word);
        this.model.setTexto(aux);
    }

    public void adiciona_categoria(String word){
        // método que recebe uma palavra do texto da entrada e 
        // determina se ela se encaixa em uma das categorias
        // padrão, cuja lista foi determinada na classe
        // Utilitaria 

        Utilitaria u = new Utilitaria();
        ArrayList <String> array = u.GET_CATEGORIAS_PADRAO();

        for (String s : array){
            if (word.equalsIgnoreCase(s)){
                ArrayList<String> aux = this.model.getCategorias();
                aux.add(word);
                this.model.setCategorias(aux);
            }
        }

    }

    public void exportarEntrada(){
        Exportacao.getInstance().exportaObjeto(this.model);
    }


    public String toString() {
        String str = "";
        str += this.model.getDate();
        str += "\n";
        
        for (String s : this.model.getTexto()){
            str += s;
            str += " ";
        }

        str += "\n";
        for (String s : this.model.getCategorias()) {
            str += s;
            str += " ";
        } 
        str += "\n";

        return str;
    }
}
