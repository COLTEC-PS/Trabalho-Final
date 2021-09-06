package view;

import java.io.PrintStream;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.ArrayList;
import java.util.Date;
import java.text.SimpleDateFormat;
import controller.Entrada;
import controller.Utilitaria;


public class MenuPrincipal {

    private int option;
    private ArrayList<Entrada> entradas;
    

    public MenuPrincipal(){
        this.option = 0;
        entradas = new ArrayList<Entrada>();
    }
    
    public void telaInicial(){
        boolean k = true;
        while(k){
            PrintStream out = System.out;
            out.println("Seja bem vindo ao Journaling." + "\n");
            out.println("Selecione uma das opções abaixo:");
            out.println("1. Nova entrada");
            out.println("2. Filtrar entradas");
            out.println("3. Exportar entradas");
            out.println("4. Sair" + "\n");
            k = executeOption();
        }
    }

    public void readOption(){
        Scanner scan = new Scanner(System.in);
        int i = scan.nextInt();
        this.option = i;
    }

    public boolean executeOption(){
        readOption();
        Scanner input = new Scanner(System.in);

        switch (this.option){
            case 1:
                novaEntrada(input);
                break;
            case 2:
                filtrarEntradas();
                break;
            case 3:
                exportarEntradas();
                break;
            case 4:
                input.close();
                return false;
            default:
                return true;
        }
        return true;
    }

    public void novaEntrada(Scanner input){
        
        // A data será escrita na entrada como "dd/mm/aaaa"
        
        String data;
        data = input.nextLine();
        Entrada newEntrada = new Entrada(data);

        String txt;
        txt = input.nextLine();
        newEntrada.add_word(txt);
        newEntrada.adiciona_categoria(txt);

        System.out.println("debug");
        this.entradas.add(newEntrada);
    }

    public void exportarEntradas(){
        for (Entrada e : this.entradas){
            e.exportarEntrada();
        }
        this.entradas.clear();
    }


    public void filtrarEntradas(){
        return;
    }

}
