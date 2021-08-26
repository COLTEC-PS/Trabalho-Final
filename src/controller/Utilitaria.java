import java.util.ArrayList;


public class Utilitaria {
    
    // Método que guarda as categorias padrão que serão 
    // usadas no método "adiciona_categoria" da classe Entrada
    // para determinar as categorias de um texto de entrada

    public static ArrayList<String> CATEGORIAS_PADRAO = new ArrayList<>(
    "Java", "arquitetura", "software", "programação",
    "trabalho", "universidade", "estudante", "professor",
    "pesquisa", "curso técnico", "graduação", "mestrado",
    "doutorado", "UFMG", "Coltec", "orientação a objeto",
    "algoritmos", "estruturas de dados", "banco de dados",
    "paradigmas de programação", "mercado", "Linkedin");

    // Método que converte uma string para o tipo date

    public static Date CONVERT_STRING_TO_DATE(String s){
        Date date;
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        formato.parse(date);
        return date;
    }

     
}
