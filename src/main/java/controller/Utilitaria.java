package controller;

import java.util.Date;
import java.util.ArrayList;
import java.text.SimpleDateFormat;
import java.text.ParseException; 


public class Utilitaria {
    
    // Método que guarda as categorias padrão que serão 
    // usadas no método "adiciona_categoria" da classe Entrada
    // para determinar as categorias de um texto de entrada

    private static ArrayList<String> CATEGORIAS_PADRAO = new ArrayList<String>();

    public static ArrayList<String> GET_CATEGORIAS_PADRAO(){

        CATEGORIAS_PADRAO.add("Java"); 
        CATEGORIAS_PADRAO.add("arquitetura"); 
        CATEGORIAS_PADRAO.add("software"); 
        CATEGORIAS_PADRAO.add("programação");
        CATEGORIAS_PADRAO.add("trabalho"); 
        CATEGORIAS_PADRAO.add("universidade"); 
        CATEGORIAS_PADRAO.add("estudante");
        CATEGORIAS_PADRAO.add("professor");
        CATEGORIAS_PADRAO.add("pesquisa"); 
        CATEGORIAS_PADRAO.add("curso técnico"); 
        CATEGORIAS_PADRAO.add("graduação"); 
        CATEGORIAS_PADRAO.add("mestrado");
        CATEGORIAS_PADRAO.add("doutorado"); 
        CATEGORIAS_PADRAO.add("UFMG"); 
        CATEGORIAS_PADRAO.add("Coltec");
        CATEGORIAS_PADRAO.add("orientação a objeto");
        CATEGORIAS_PADRAO.add("algoritmos");
        CATEGORIAS_PADRAO.add("estruturas de dados");
        CATEGORIAS_PADRAO.add("banco de dados"); 
        CATEGORIAS_PADRAO.add("dados");
        CATEGORIAS_PADRAO.add("paradigmas de programação"); 
        CATEGORIAS_PADRAO.add("mercado"); 
        CATEGORIAS_PADRAO.add("Linkedin");

        return CATEGORIAS_PADRAO;
    }

    // Método que converte uma string para o tipo date

    public static Date CONVERT_STRING_TO_DATE(String s){
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
