import java.util.List;

public class GraphController<T>{
    private Graph<T> graph;

    public GraphController(Graph<T> graph){
        this.graph = graph;
    }

    /*
     * getNeighborhoodString --> Retorna uma string formatada para ser impressa, a partir de um índice
    */
    public String getNeighborhoodString(Integer idVertice){
        // Instanciamos uma lista com o retorno da função da classe "Graph"
        // temos o índice de entrada subtraindo 1, para que o usuário
        // consiga entrar com o código da cidade
        String output = "";
        List<Integer> idList = graph.getNeighborhoodList(idVertice - 1);

        for(Integer id : idList){
            output += String.format("ID: %02d | %-25s\t | Wheight: %.2f\n", id + 1, graph.getVertice(id).getValue(), graph.getEdges()[idVertice - 1][id]);
        }

        return output;
    }

    /*
     * getBreadthFirstSearch --> Retorna uma string formatada para ser impressa, a partir de um índice
    */
    public String getBreadthFirstSearch(Integer idVertice){
        // Instanciamos uma lista com o retorno da função da classe "Graph"
        // temos o índice de entrada subtraindo 1, para que o usuário
        // consiga entrar com o código da cidade
        String output = "";
        List<T> itensList = graph.breadthFirstSearch(idVertice - 1);

        for(T value : itensList){
            output += String.format("ID: %02d | %s\n", graph.getVerticeIndex(value) + 1, value);
        }

        return output;
    }

}
