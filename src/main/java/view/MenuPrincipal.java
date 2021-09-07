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

    public void readOption(Scanner scan){
        int i = scan.nextInt();
        this.option = i;
    }

    public boolean executeOption(){
        Scanner scan = new Scanner(System.in);
        readOption(scan);
        switch (this.option){
            case 1:
                novaEntrada();
                break;
            case 2:
                filtrarEntradas();
                break;
            case 3:
                exportarEntradas();
                break;
            case 4:
                scan.close();
                return false;
            default:
                return true;
        }
        return true;
    }

    public void novaEntrada(){

        Scanner input = new Scanner(System.in);

        System.out.println("\nDigite a nova entrada no seguinte formato: primeiro escreva a data e depois dê 'enter' e digite o texto.\n");
        
        // A data será escrita na entrada como "dd/mm/aaaa"
        
        String data;
        data = input.nextLine();
        Entrada newEntrada = new Entrada(data);

        // Lê palavra por palavra e vai adicionando à entrada
        // A leitura para quando o usuário digita um ponto final '.'
        // Ou seja, para que o programa pare de ler uma entrada, é necessário que
        // o usuário dê enter e depois digite ponto final na nova linha 

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


    public void filtrarEntradas(){
        System.out.println("\n\nEscolha uma opção de filtragem para as entradas:");
        System.out.println("\t(1) Por texto");
        System.out.println("\t(2) Por data");
        System.out.println("\t(3) Por categoria");

        Scanner input = new Scanner(System.in);

        int i = input.nextInt();

        switch (i){
            case 1:
                Filtragem f1 = new FiltragemTexto();
                filtrarTexto(f1);
                break;
            case 2:
                Filtragem f2 = new FiltragemData();
                filtrarData(f2);
                break;
            case 3:
                FiltragemCategoria f3 = new FiltragemCategoria();
                filtrarCategoria(f3);
                break;
            case 12:
                Filtragem f12 = new FiltragemTexto(new FiltragemData());
                filtrarTexto(f12);
                filtrarData(f12);
                break;
            case 21:
                Filtragem f21 = new FiltragemData(new FiltragemTexto());
                filtrarData(f21);
                filtrarTexto(f21);
                break;
            case 13:
                Filtragem f13 = new FiltragemTexto(new FiltragemCategoria());
                filtrarTexto(f13);
                filtrarCategoria(f13);
                break;
            case 31:
                Filtragem f31 = new FiltragemCategoria(new FiltragemTexto());
                filtrarTexto(f31);
                filtrarCategoria(f31);
                break;
            case 23:
                Filtragem f23 = new FiltragemData(new FiltragemCategoria());
                filtrarCategoria(f23);
                filtrarData(f23);
                break;
            case 32:
                Filtragem f32 = new FiltragemCategoria(new FiltragemData());
                filtrarCategoria(f32);
                filtrarData(f32);
                break;
            case 123:
                Filtragem f123 = new FiltragemTexto(new FiltragemData(new FiltragemCategoria()));
                filtrarCategoria(f123);
                filtrarData(f123);
                filtrarTexto(f123);
                break;
            case 132:
                Filtragem f132 = new FiltragemTexto(new FiltragemData(new FiltragemCategoria()));
                filtrarCategoria(f132);
                filtrarData(f132);
                filtrarTexto(f132);
                break;
            case 321:
                Filtragem f321 = new FiltragemTexto(new FiltragemData(new FiltragemCategoria()));
                filtrarCategoria(f321);
                filtrarData(f321);
                filtrarTexto(f321);
                break;
            case 312:
                Filtragem f312 = new FiltragemTexto(new FiltragemData(new FiltragemCategoria()));
                filtrarCategoria(f312);
                filtrarData(f312);
                filtrarTexto(f312);
                break;
            case 213:
                Filtragem f213 = new FiltragemTexto(new FiltragemData(new FiltragemCategoria()));
                filtrarCategoria(f213);
                filtrarData(f213);
                filtrarTexto(f213);
                break;
            case 231:
                Filtragem f231 = new FiltragemTexto(new FiltragemData(new FiltragemCategoria()));
                filtrarCategoria(f231);
                filtrarData(f231);
                filtrarTexto(f231);
                break;
            default:
                System.out.println("Opção inválida, tente novamente.");
                break;
        }
    }

    public void filtrarTexto(Filtragem f){
        ArrayList<Modelo> listaDados = new ArrayList<>();

        System.out.println("\n\nEscreva o texto que será o filtro para as entradas: ");

        Scanner scan = new Scanner(System.in);
        // Lê string referente ao texto que será usado como filtro
        String subtexto = scan.nextLine();
        
        Entrada instanciaAuxiliar = new Entrada();

        instanciaAuxiliar.filtraEntradasPorTextoController(listaDados, f, subtexto);
        System.out.println("\n\n\t Resultados do(s) filtro(s): \n");
        imprimeArrayEntradas(listaDados);
    }

    public void filtrarData(Filtragem f){
        ArrayList<Modelo> listaDados = new ArrayList<>();

        System.out.println("\n\nEscreva a data que será usada como filtro: ");
        Scanner scan = new Scanner(System.in);

        // Lê a data
        String data = scan.nextLine();
        Entrada instanciaAuxiliar = new Entrada();
        instanciaAuxiliar.filtraEntradasPorDataController(listaDados, f, data);
        System.out.println("\n\n\tResultados do(s) filtro(s): \n");
        imprimeArrayEntradas(listaDados);
    }

    public void filtrarCategoria(Filtragem f){
        ArrayList<Modelo> listaDados = new ArrayList<>();

        System.out.println("\n\nEscreva a categoria que será usada como filtro: ");
        Scanner scan = new Scanner(System.in);
        
        // Lê a categoria
        String categoria = scan.nextLine();
        Entrada instanciaAuxiliar = new Entrada();
        instanciaAuxiliar.filtraEntradasPorCategoriaController(listaDados, f, categoria);
        System.out.println("\n\n\t Resultados do(s) filtro(s): \n");
        imprimeArrayEntradas(listaDados);
    }


    public void imprimeArrayEntradas(ArrayList<Modelo> listaDados){
        for (Modelo m : listaDados) {
            m.imprimeModelo();
        }
    }
}
