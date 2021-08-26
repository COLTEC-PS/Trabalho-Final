import java.io.PrintStream;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.ArrayList;

public class MenuPrincipal {

    private int option;
    private ArrayList<Entrada> entradas;

    MenuPrincipal(){
        this.option = 0;
        entradas = new ArrayList<>();
    }
    
    public void tela_inicial(){
        int k = 1;
        while(k){
            PrintStream out = System.out;
            out.println("Seja bem vindo ao Journaling." + "\n");
            out.println("Selecione uma das opções abaixo:");
            out.println("1. Nova entrada");
            out.println("2. Filtrar entradas");
            out.println("3. Exportar entradas");
            out.println("4. Sair" + "\n");
            execute_option();
        }
    }

    public void read_option(){
        Scanner scan = new Scanner(System.in);
        int i = scan.nextInt();
        this.option = i;
        scan.close();
    }

    public int execute_option(){
        read_option();

        switch (this.option){
            case 1:
                nova_entrada();
                break;
            case 2:
                filtrar_entradas();
                break;
            case 3:
                exportar_entradas();
                break;
            case 4:
                return 0;
            default:
                return 1;
        }
    }

    public void nova_entrada(){

        Scanner le = new Scanner(System.in);

        // A data será escrita na entrada como "dd/mm/aa: "
        // por isso o ":" deve ser retirado

        StringTokenizer date = new StringTokenizer(le.next());
        date = date.nextToken(":");
        Entrada new_entrada = new Entrada(String(date));
        String palavra = "";
        while(palavra != "\n"){
            palavra = " ";         
            palavra = le.nextLine();
            new_entrada.add_word(palavra);
            new_entrada.adiciona_categoria(palavra);
        } 
        this.entradas.add(new_entrada);
        new_entrada.insere_entrada_no_arquivo();
        le.close();
    }

    public void exportar_entradas(){
        return;
    }

}
