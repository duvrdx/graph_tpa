import java.util.List;

public class GraphController<T>{
    private Graph<T> graph;

    public GraphController(Graph<T> graph){
        this.graph = graph;
    }

    /*
     * getNeighborhoodString --> Retorna uma string formatada para ser impressa, a partir de um índice
    */
    public String getNeighborhoodString(T value){
        // Instanciamos uma lista com o retorno da função da classe "Graph"
        // temos o índice de entrada subtraindo 1, para que o usuário
        // consiga entrar com o código da cidade
        String output = "";
        List<Integer> idList = graph.getNeighborhoodList(value);

        for(Integer id : idList){
            output += String.format("ID: %02d | %-25s\t | Wheight: %.2f\n", id + 1, graph.getVertices().get(id).getValue(), graph.getEdges()[graph.getVerticeIndex(value)][id]);
        }

        return output;
    }

    /*
     * getBreadthFirstSearch --> Retorna uma string formatada para ser impressa, a partir de um índice
    */
    public String getBreadthFirstSearch(T value){
        // Instanciamos uma lista com o retorno da função da classe "Graph"
        // temos o índice de entrada subtraindo 1, para que o usuário
        // consiga entrar com o código da cidade
        String output = "";
        List<T> itensList = graph.breadthFirstSearch(value);

        for(T item : itensList){
            output += item + "\n";
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
        float totalWheight = 0;
        T currentOrigin, currentDestination;

        // Percorremos o grafo adicionando à string apenas onde existem aresta
        // ou seja, o peso sendo maior que 0
        for(int i = 0; i < graph.getVerticesNum(); i++){
            for(int j = 0; j < graph.getVerticesNum(); j++){
                if(mstGraph.getEdges()[i][j] > 0){
                    currentOrigin = mstGraph.getVertices().get(i).getValue();
                    currentDestination = mstGraph.getVertices().get(j).getValue();

                    originDestination = currentOrigin +" --> "+ currentDestination;
                    output += String.format("%-50s | Wheight: %.2f\n", originDestination, mstGraph.getEdges()[i][j]);
                    totalWheight += mstGraph.getEdges()[i][j];
                }
            }
        }
        // Adicionando peso total
        output += "-----------------------------------------------------------------++>\n";
        output += String.format("Total Wheight: %.2f\n", totalWheight);
        return output;
    }
    

}
