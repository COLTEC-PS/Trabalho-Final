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

        switch (i){

            case 1:
                Filtragem f1 = new FiltragemTexto();
                filtrarTexto(f1, input);
                break;
            case 2:
                Filtragem f2 = new FiltragemData();
                filtrarData(f2, input);
                break;
            case 3:
                FiltragemCategoria f3 = new FiltragemCategoria();
                filtrarCategoria(f3, input);
                break;
            case 12:
                Filtragem f12 = new FiltragemTexto(new FiltragemData());
                break;
            case 21:
                Filtragem f21 = new FiltragemData(new FiltragemTexto());
                break;
            case 13:
                Filtragem f13 = new FiltragemTexto(new FiltragemCategoria());
                break;
            case 31:
                Filtragem f31 = new FiltragemCategoria(new FiltragemTexto());
                break;
            case 23:
                Filtragem f23 = new FiltragemData(new FiltragemCategoria());
                break;
            case 32:
                Filtragem f32 = new FiltragemCategoria(new FiltragemData());
                break;
            case 123:
                Filtragem f123 = new FiltragemTexto(new FiltragemData(new FiltragemCategoria()));
                break;
            case 132:
                Filtragem f132 = new FiltragemTexto(new FiltragemData(new FiltragemCategoria()));
                break;
            case 321:
                Filtragem f321 = new FiltragemTexto(new FiltragemData(new FiltragemCategoria()));
                break;
            case 312:
                Filtragem f312 = new FiltragemTexto(new FiltragemData(new FiltragemCategoria()));
                break;
            case 213:
                Filtragem f213 = new FiltragemTexto(new FiltragemData(new FiltragemCategoria()));
                break;
            case 231:
                Filtragem f231 = new FiltragemTexto(new FiltragemData(new FiltragemCategoria()));
                break;
            default:
                System.out.println("Opção invĺida, tente novamente.");
                break;
        }
    }

    public void atualizaArrayEntradas(ArrayList <Entrada> auxArray){
        this.entradas.clear();
        for (Entrada e : auxArray) {
            this.entradas.add(e);
        }

    }

    public void filtrarTexto(Filtragem f, Scanner input){
        System.out.println("Escreva o texto que será o filtro para as entradas: ");

        // Lê string referente ao texto que será usado como filtro
        String subtexto = input.nextLine();

        // Atualiza o atributo entradas e deixa o array com apenas as entradas que passaram no filtro

        ArrayList<Entrada> auxArray = new ArrayList<>();
        for (Entrada e : this.entradas) {
            if (e.filtraTextoEntrada(subtexto)){
                auxArray.add(e);
            }
        }
        atualizaArrayEntradas(auxArray);    
    }

    public void filtrarData(Filtragem f, Scanner input){
        System.out.println("Escreva a data que será usada como filtro: ");

        // Lê a data
        String data = input.nextLine();

        ArrayList<Entrada> auxArray = new ArrayList<>();
        for (Entrada e : this.entradas) {
            if (e.filtraDataEntrada(data)){
                auxArray.add(e);
            }
        }
        atualizaArrayEntradas(auxArray);    
    }

    public void filtrarCategoria(Filtragem f, Scanner input){
        System.out.println("Escreva a categoria que será usada como filtro: ");
        
        // Lê a categoria
        String categoria = input.nextLine();

        ArrayList<Entrada> auxArray = new ArrayList<>();
        for (Entrada e : this.entradas) {
            if (e.filtraCategoriaEntrada(categoria)){
                auxArray.add(e);
            }
        }
        atualizaArrayEntradas(auxArray);    
    }
}
