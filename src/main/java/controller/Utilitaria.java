package controller;

import java.util.Date;
import java.util.ArrayList;
import java.text.SimpleDateFormat;
import java.text.ParseException; 


public class Utilitaria {
    
    // Método que guarda as categorias padrão que serão 
    // usadas no método "adiciona_categoria" da classe Entrada
    // para determinar as categorias de um texto de entrada

    private static ArrayList<String> categoriasPadrao = new ArrayList<>();

    private Utilitaria(){}

    public static ArrayList<String> getCategoriasPadrao(){
        categoriasPadrao.clear();
        categoriasPadrao.add("Java"); 
        categoriasPadrao.add("arquitetura"); 
        categoriasPadrao.add("software"); 
        categoriasPadrao.add("programação");
        categoriasPadrao.add("trabalho"); 
        categoriasPadrao.add("universidade"); 
        categoriasPadrao.add("estudante");
        categoriasPadrao.add("professor");
        categoriasPadrao.add("pesquisa"); 
        categoriasPadrao.add("curso"); 
        categoriasPadrao.add("técnico"); 
        categoriasPadrao.add("graduação"); 
        categoriasPadrao.add("mestrado");
        categoriasPadrao.add("doutorado"); 
        categoriasPadrao.add("UFMG"); 
        categoriasPadrao.add("Coltec");
        categoriasPadrao.add("algoritmos");
        categoriasPadrao.add("dados"); 
        categoriasPadrao.add("paradigmas"); 
        categoriasPadrao.add("mercado");
        categoriasPadrao.add("Linkedin");

        return categoriasPadrao;
    }

    // Método que converte uma string para o tipo date

    public static Date convertStringToDate(String s){
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date();
        try {
            date = formato.parse(s);
           }
           catch (ParseException e) {
            return date;
           }
        return date;
    }

     
}
