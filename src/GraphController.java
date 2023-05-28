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
    
    /*
     * getBreadthFirstSearch --> Retorna uma string formatada para ser impressa, a partir de um índice
    */
    public String getMinimumSpanningTree(){
        // Instanciamos um grafo contendo a árvore geradora minima
        Graph<T> mstGraph = graph.getMST();
        String output = "";
        String originDestination = "";

        // Percorremos o grafo adicionando à string apenas onde existem aresta
        // ou seja, o peso sendo maior que 0
        for(int i = 0; i < graph.getVerticesNum(); i++){
            for(int j = 0; j < graph.getVerticesNum(); j++){
                if(mstGraph.getEdges()[i][j] > 0){
                    originDestination = mstGraph.getVertice(i).getValue() +" --> "+ mstGraph.getVertice(j).getValue();
                    output += String.format("%-50s | Wheight: %.2f\n", originDestination, mstGraph.getEdges()[i][j]);
                }
            }
            
        }
        return output;
    }
    

}
