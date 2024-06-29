package apresentacao;

import dados.Entrada;

import java.util.List;

public class Diario {
    public static void mostrarEntradas(List<Entrada> entradas) {
        for (Entrada entrada : entradas) {
            System.out.println("------------------------------");
            System.out.println("Texto da Entrada: " + entrada.getTexto());
            System.out.println("Data: " + entrada.getData());
            System.out.println("Categorias: " + String.join(", ", entrada.getCategorias()));
            System.out.println("------------------------------");
        }
    }
}
