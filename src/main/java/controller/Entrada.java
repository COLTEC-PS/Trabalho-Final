package controller; 

import java.util.Vector;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import model.Exportacao;
import model.Modelo;


public class Entrada {      

    private ArrayList<String> text; // texto fornecido para entrada do diário
    private Date date; // data de registro da entrada no diário
    private ArrayList<String> categorias; // lista de categorias associadas a entrada do diário

    public Entrada(String _date){
        this.text = new ArrayList<String>();
        this.categorias = new ArrayList<String>();
        this.date = Utilitaria.CONVERT_STRING_TO_DATE(_date);        
    }

    public void add_word(String word){
        this.text.add(word);

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
                this.categorias.add(word);
            }
        }

    }

    public void exportarEntrada(){
        Modelo novaEntrada = new Modelo(this.text, this.date, this.categorias);
        Exportacao.getInstance().exportaObjeto(novaEntrada);
    }


    public String toString() {
        String str = "";
        str += this.date;
        str += "\n";
        
        for (String s : this.text){
            str += s;
            str += " ";
        }

        str += "\n";
        for (String s : categorias) {
            str += s;
            str += " ";
        } 
        str += "\n";

        return str;
    }
}