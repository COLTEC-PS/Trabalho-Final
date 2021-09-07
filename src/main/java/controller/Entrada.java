package controller; 

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import model.Exportacao;
import model.Filtragem;
import model.Modelo;


public class Entrada {     
    
    Modelo model;

    public Entrada(String dateParametro){
        Date date = Utilitaria.convertStringToDate(dateParametro);  
        this.model = new Modelo(new ArrayList<>(), date, new ArrayList<>());      
    }

    public Entrada(){}

    public void addTxt(String word){
        this.model.setPalavraTexto(word);
    }

    public void adicionaCategoria(String word){
        // método que recebe uma palavra do texto da entrada
        // e determina se ela se encaixa em uma das categorias
        // padrão, cuja lista foi determinada na classe
        // Utilitaria 

        ArrayList<String> array = Utilitaria.getCategoriasPadrao();
        for (String s : array){
            if (word.equalsIgnoreCase(s)){
                this.model.addCategoria(s);
            }
        }

    }

    public void filtraEntradasPorTextoController(ArrayList<Modelo> listaDados, Filtragem f, String texto){
        f.filtraEntradasPorTexto(listaDados, texto);
    }

    public void filtraEntradasPorDataController(ArrayList<Modelo> listaDados, Filtragem f, String data){
        f.filtraEntradasPorData(listaDados, data);
    }

    public void filtraEntradasPorCategoriaController(ArrayList<Modelo> listaDados, Filtragem f, String categoria){
        f.filtraEntradasPorCategoria(listaDados, categoria);
    }


    public void exportarEntrada(){
        Exportacao ex = new Exportacao();
        ex.exportaObjeto(this.model);
    }

}
