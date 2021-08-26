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
    
    public void tela_inicial(){
        boolean k = true;
        while(k){
            PrintStream out = System.out;
            out.println("Seja bem vindo ao Journaling." + "\n");
            out.println("Selecione uma das opções abaixo:");
            out.println("1. Nova entrada");
            out.println("2. Filtrar entradas");
            out.println("3. Exportar entradas");
            out.println("4. Sair" + "\n");
            k = execute_option();
        }
    }

    public void read_option(){
        Scanner scan = new Scanner(System.in);
        int i = scan.nextInt();
        this.option = i;
    }

    public boolean execute_option(){
        read_option();
        Scanner input = new Scanner(System.in);

        switch (this.option){
            case 1:
                nova_entrada(input);
                break;
            case 2:
                filtrar_entradas();
                break;
            case 3:
                exportar_entradas();
                break;
            case 4:
                input.close();
                return false;
            default:
                return true;
        }
        return true;
    }

    public void nova_entrada(Scanner input){
        
        // A data será escrita na entrada como "dd/mm/aa"
        
        String data;
        data = input.nextLine();
        Entrada new_entrada = new Entrada(data);

        String txt;
        txt = input.nextLine();
        new_entrada.add_word(txt);
        new_entrada.adiciona_categoria(txt);

        System.out.println("debug");
        this.entradas.add(new_entrada);
    }

    public void exportar_entradas(){
        for (Entrada e : this.entradas){
            e.exportar_entrada();
        }
    }


    public void filtrar_entradas(){
        return;
    }

}
