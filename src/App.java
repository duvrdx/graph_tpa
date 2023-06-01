import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class App {
    public static Graph<City> readFromFile(String path){
        try (BufferedReader buffer = new BufferedReader(new FileReader(path))) {

            Integer length = Integer.parseInt(buffer.readLine());
            Graph<City> graph = new Graph<City>(length);

            String line;

            for(int i = 0; i < length; i++){
                line = buffer.readLine();
                String[] fields = line.split(",");

                Integer code = Integer.parseInt(fields[0]);
                String name = fields[1];

                graph.addVertice(new City(code, name));
            }

            for(int i = 0; i < length; i++){
                line = buffer.readLine();
                String[] fields = line.split(",");
                for(int j = 0; j < length; j++){
                    graph.addEdge(graph.getVertices().get(i).getValue(), graph.getVertices().get(j).getValue(), Float.parseFloat(fields[j]));
                }
            }
            return graph;           
        } catch (IOException e){
        }
        return null;
    }
    
    public static void printOptions(){
        System.out.println("\033[1m" +"\033[31m" + "==================================================================================+++---");
        System.out.println("Escolha uma das opções abaixo");
        System.out.println("==================================================================================+++---");
        System.out.println("\033[0;0m1. Obter cidades vizinhas | 2. Obter todos os caminhos | 3. Obter Árvore Geradora Mínima\n4. Sair");
    }

    public static void printLogo(){
        System.out.println("\033[1m" +"\033[31m"
        +"\n ▄████  ██▀███   ▄▄▄       ██▓███   ██░ ██   ██████ "
        +"\n ██▒ ▀█▒▓██ ▒ ██▒▒████▄    ▓██░  ██▒▓██░ ██▒▒██    ▒" 
        +"\n▒██░▄▄▄░▓██ ░▄█ ▒▒██  ▀█▄  ▓██░ ██▓▒▒██▀▀██░░ ▓██▄ "  
        +"\n░▓█  ██▓▒██▀▀█▄  ░██▄▄▄▄██ ▒██▄█▓▒ ▒░▓█ ░██   ▒   ██▒"
        +"\n░▒▓███▀▒░██▓ ▒██▒ ▓█   ▓██▒▒██▒ ░  ░░▓█▒░██▓▒██████▒▒"
        +"\n ░▒   ▒ ░ ▒▓ ░▒▓░ ▒▒   ▓▒█░▒▓▒░ ░  ░ ▒ ░░▒░▒▒ ▒▓▒ ▒ ░"
        +"\n  ░   ░   ░▒ ░ ▒░  ▒   ▒▒ ░░▒ ░      ▒ ░▒░ ░░ ░▒  ░ ░"
        +"\n░ ░   ░   ░░   ░   ░   ▒   ░░        ░  ░░ ░░  ░  ░  "
        +"\n      ░    ░           ░  ░          ░  ░  ░      ░  ");
        System.out.println("\n\033[0;0mby: Eduardo Próspero\n");
    }

    public static void clearTerminal(){
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        String path = "./input.txt";

        printLogo();
        Boolean isRunning = true;
        Graph<City> graph = readFromFile(path);
        GraphController<City> controller = new GraphController<City>(graph);
        Integer input;
        City dummieCity;

        while(isRunning){
            App.printOptions();
            // graph.printAdjacencyMatrix();

            switch(scanner.nextInt()){
                case 1:

                    System.out.println("Digite o codigo da cidade:\n");
                    input = scanner.nextInt();
                    clearTerminal();

                    dummieCity = new City(input, "Dummie");
                
                    if(input <= graph.getVerticesNum()){
                        System.out.println("\033[1m" + "Vizinhos de " + graph.getVertice(dummieCity).getValue() + ":\n");
                        System.out.println("\033[0;0m\033[31m" +  controller.getNeighborhoodString(dummieCity) + "\033[0;0m");
                    }else{
                        System.out.println("\033[0;0m\033[31m" +"Esse código de cidade não existe!" + "\033[0;0m");
                    }
                    
                    scanner.nextLine();
                    scanner.nextLine();
                    clearTerminal();

                    break;

                case 2:

                    System.out.println("\033[1m" + "Digite o codigo da cidade:\n");
                    input = scanner.nextInt();
                    clearTerminal();

                    dummieCity = new City(input, "Dummie");

                    if(input <= graph.getVerticesNum()){
                        System.out.println("\033[1m" + "Caminhos a partir de " + graph.getVertice(dummieCity).getValue() + ":\n");
                        System.out.println("\033[0;0m\033[31m" + controller.getBreadthFirstSearch(dummieCity) + "\033[0;0m");
                    }else{
                        System.out.println("\033[0;0m\033[31m" + "Esse código de cidade não existe!" + "\033[0;0m");
                    }
                    
                    scanner.nextLine();
                    scanner.nextLine();
                    clearTerminal();
                    break;

                case 3:
                    clearTerminal();
                    System.out.println("\033[1m" + "Árvore Geradora Mínima do Grafo com " + graph.getVerticesNum() + " itens:\n");
                    System.out.println("\033[0;0m\033[31m" + controller.getMinimumSpanningTree() + "\033[0;0m");
                    scanner.nextLine();
                    scanner.nextLine();
                    clearTerminal();
                    break;

                case 4:
                    isRunning = false;
                    scanner.close();
                    clearTerminal();
                    break;
                default:
                    break;
            }
        }
    }
}
