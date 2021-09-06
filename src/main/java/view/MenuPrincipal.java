package view;

import java.io.PrintStream;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.ArrayList;
import java.util.Date;
import java.text.SimpleDateFormat;
import controller.Entrada;
import controller.Utilitaria;
import model.*;


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
            out.println("\nSeja bem vindo ao Journaling." + "\n");
            out.println("Selecione uma das opções abaixo:");
            out.println("\t 1. Nova entrada");
            out.println("\t 2. Filtrar entradas");
            out.println("\t 3. Exportar entradas");
            out.println("\t 4. Sair" + "\n");
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
                filtrarEntradas(input);
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

        // Lê palavra por palavra e vai adicionando à entrada
        // A leitura para quando o usuário digita um ponto final '.'

        while(true){
            String word;
            word = input.next();
            if (word.equals(".")){
                break;
            }
            newEntrada.addTxt(word);
            newEntrada.adicionaCategoria(word);

        }
        this.entradas.add(newEntrada);
    }

    public void exportarEntradas(){
        for (Entrada e : this.entradas){
            e.exportarEntrada();
        }
        this.entradas.clear();
    }


    public void filtrarEntradas(Scanner input){
        System.out.println("Escolha uma opção de filtragem para as entradas:");
        System.out.println("\t(1) Por texto");
        System.out.println("\t(2) Por data");
        System.out.println("\t(3) Por categoria");

        int i = input.nextInt();

        System.out.println("/t/tResultados do(s) filtro(s): ");

        ArrayList<Modelo> listaDados = new ArrayList<>();

        switch (i){
            case 1:
                Filtragem f1 = new FiltragemTexto();
                filtrarTexto(listaDados, f1, input);
                break;
            case 2:
                Filtragem f2 = new FiltragemData();
                filtrarData(listaDados, f2, input);
                break;
            case 3:
                FiltragemCategoria f3 = new FiltragemCategoria();
                filtrarCategoria(listaDados, f3, input);
                break;
            case 12:
                Filtragem f12 = new FiltragemTexto(new FiltragemData());
                filtrarTexto(listaDados, f12, input);
                filtrarData(listaDados, f12, input);
                break;
            case 21:
                Filtragem f21 = new FiltragemData(new FiltragemTexto());
                filtrarData(listaDados, f21, input);
                filtrarTexto(listaDados, f21, input);
                break;
            case 13:
                Filtragem f13 = new FiltragemTexto(new FiltragemCategoria());
                filtrarTexto(listaDados, f13, input);
                filtrarCategoria(listaDados, f13, input);
                break;
            case 31:
                Filtragem f31 = new FiltragemCategoria(new FiltragemTexto());
                filtrarTexto(listaDados, f31, input);
                filtrarCategoria(listaDados, f31, input);
                break;
            case 23:
                Filtragem f23 = new FiltragemData(new FiltragemCategoria());
                filtrarCategoria(listaDados, f23, input);
                filtrarData(listaDados, f23, input);
                break;
            case 32:
                Filtragem f32 = new FiltragemCategoria(new FiltragemData());
                filtrarCategoria(listaDados, f32, input);
                filtrarData(listaDados, f32, input);
                break;
            case 123:
                Filtragem f123 = new FiltragemTexto(new FiltragemData(new FiltragemCategoria()));
                filtrarCategoria(listaDados, f123, input);
                filtrarData(listaDados, f123, input);
                filtrarTexto(listaDados, f123, input);
                break;
            case 132:
                Filtragem f132 = new FiltragemTexto(new FiltragemData(new FiltragemCategoria()));
                filtrarCategoria(listaDados, f132, input);
                filtrarData(listaDados, f132, input);
                filtrarTexto(listaDados, f132, input);
                break;
            case 321:
                Filtragem f321 = new FiltragemTexto(new FiltragemData(new FiltragemCategoria()));
                filtrarCategoria(listaDados, f321, input);
                filtrarData(listaDados, f321, input);
                filtrarTexto(listaDados, f321, input);
                break;
            case 312:
                Filtragem f312 = new FiltragemTexto(new FiltragemData(new FiltragemCategoria()));
                filtrarCategoria(listaDados, f312, input);
                filtrarData(listaDados, f312, input);
                filtrarTexto(listaDados, f312, input);
                break;
            case 213:
                Filtragem f213 = new FiltragemTexto(new FiltragemData(new FiltragemCategoria()));
                filtrarCategoria(listaDados, f213, input);
                filtrarData(listaDados, f213, input);
                filtrarTexto(listaDados, f213, input);
                break;
            case 231:
                Filtragem f231 = new FiltragemTexto(new FiltragemData(new FiltragemCategoria()));
                filtrarCategoria(listaDados, f231, input);
                filtrarData(listaDados, f231, input);
                filtrarTexto(listaDados, f231, input);
                break;
            default:
                System.out.println("Opção invĺida, tente novamente.");
                break;
        }
        imprimeArrayEntradas(listaDados);
    }

    public void filtrarTexto(ArrayList<Modelo> listaDados, Filtragem f, Scanner input){
        System.out.println("Escreva o texto que será o filtro para as entradas: ");

        // Lê string referente ao texto que será usado como filtro
        String subtexto = input.nextLine();

        Entrada.instanciaAuxiliar.filtraEntradasPorTextoController(listaDados, f, subtexto);
    }

    public void filtrarData(ArrayList<Modelo> listaDados, Filtragem f, Scanner input){
        System.out.println("Escreva a data que será usada como filtro: ");

        // Lê a data
        String data = input.nextLine();

        Entrada.instanciaAuxiliar.filtraEntradasPorDataController(listaDados, f, data);
    }

    public void filtrarCategoria(ArrayList<Modelo> listaDados, Filtragem f, Scanner input){
        System.out.println("Escreva a categoria que será usada como filtro: ");
        
        // Lê a categoria
        String categoria = input.nextLine();

        Entrada.instanciaAuxiliar.filtraEntradasPorCategoriaController(listaDados, f, categoria);
    }


    public void imprimeArrayEntradas(ArrayList<Modelo> listaDados){
        for (Modelo m : listaDados) {
            System.out.println(m);
        }
    }
}
