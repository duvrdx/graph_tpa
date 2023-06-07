import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Graph<T>{
    private List<Vertice<T>> vertices;
    private float edges[][];
    private int verticesNum;

    /*
     * Graph --> Construtor que recebe apenas o número de vertices
    */
    public Graph(int verticesNum){
        // No construtor instanciamos uma matriz de adjacencias N x N,
        // sendo N o número de vertices. Também é instânciada uma lista
        // de vértices vazia
        this.edges = new float [verticesNum][verticesNum];
        this.vertices = new ArrayList<Vertice<T>>();
        this.verticesNum = verticesNum;
    }

    /*
     * addVertice --> Adiciona um novo vertice a lista de vertices retornando o novo vértice ao final
    */
    public Vertice<T> addVertice(T value){
        // Nessa função apenas instanciamos um objeto de Vértice com o valor 
        // passado pelo parametro e adicionamos a nossa lista de vertices
        Vertice<T> newVertice = new Vertice<T>(value);
        vertices.add(newVertice);

        // Retornamos o objeto instanciado
        return newVertice;
    }

    /*
     * getVerticeIndex --> Retorna o índice de um vértice a partir do valor associado
    */
    public int getVerticeIndex(T value){

        // Percorremos nossa lista de vertices utilizando um laço de repetição
        for(int i = 0; i < vertices.size(); i++){
            // Aqui realizamos um comparação de valores
            // Caso seja verdadeiro: Retornamos o índice
            // Caso não: Não fazemos nada
            if(vertices.get(i).getValue().equals(value)) return i;
        }

        // Caso saia do laço de repetição sem encontrar um valor igual
        // retornamos -1 indicando que o número não foi encontrado
        return -1;
    }

    /*
     * addEdge --> Adicionamos uma nova aresta à nossa matriz de adjacência
    */
    public void addEdge(T origin, T destination, float weight){
        Vertice<T> verticeOrigin, verticeDestination; 
        int indexOrigin = getVerticeIndex(origin);
        int indexDestination = getVerticeIndex(destination);

        // Aqui verificamos se o vértice de origem existe no nosso Array
        // - Caso não exista: Adicionamos ele à lista e atualizamos
        //                    a variável que contém o índice de origem
        // - Caso exista: Não fazemos nada
        if(indexOrigin == -1){
            verticeOrigin = addVertice(origin);
            indexOrigin = this.vertices.indexOf(verticeOrigin);
        }

        // Aqui verificamos se o vértice de destino existe no nosso Array
        // - Caso não exista: Adicionamos ele à lista e atualizamos
        //                    a variável que contém o índice de destino
        // - Caso exista: Não fazemos nada
        if(indexDestination == -1){
            verticeDestination = addVertice(destination);
            indexDestination = this.vertices.indexOf(verticeDestination);
        }

        // Por fim adicionamos o peso à nossa matriz de adjacência
        // utilizando os índices de origem e destino
        this.edges[indexOrigin][indexDestination] = weight;
    }

    /*
     * breadthFirstSearch --> Retorna uma lista contendo o resultado de uma busca em largura a partir de um vértice
    */
    public List<T> breadthFirstSearch(T value){
        boolean marked[] = new boolean[this.verticesNum];
        List<T> itensList = new ArrayList<T>();
        List<Integer> queue = new ArrayList<Integer>();
        Integer current = getVerticeIndex(value);

        // Utilizamos uma fila adicionando o índice de entrada
        queue.add(current);

        // Entramos em um laço de repetição até que a fila esteja vazia
        while(queue.size() != 0){
            // Guardamos o primeiro item da fila na variavel "current"
            // após isso o removemos da fila
            current = queue.get(0);
            queue.remove(0);

            // Marcamos esse índice e adicionamos o valor do vértice associado a uma lista 
            marked[current] = true;
            itensList.add(vertices.get(current).getValue());

            // Utilizamos mais um laço de repetição para percorremos os destinos desse índice atual
            for(int dest = 0; dest < this.verticesNum; dest++){
                // Realizamos uma vericação a fim de saber se o destino atual tem peso maior que zero
                // Caso seja: Realizaremos mais uma verificação
                // Caso não: Não fazemos nada
                if(this.edges[current][dest] > 0){
                    // Nessa verificação verificamos se esse destino não foi marcado e não esta na fila
                    // Caso seja verdadeiro:  Adicionamos esse destino à fila
                    // Caso não: Não fazemos nada
                    if(!marked[dest] && !queue.contains(dest)) queue.add(dest);

                }
            }
        }

        // Ao fim retornamos uma lista de itens
        return itensList;
    }

    /*
     * getNeighborhoodList --> Retorna uma lista contendo a "vizinhança", a função retorna apenas o índice dos vértices
    */
    public List<Integer> getNeighborhoodList(T value){
        List<Integer> idList = new ArrayList<Integer>();
        
        // Laço de repetição que itera sobre j enquanto o mesmo é menor que 
        // o número de itens do nosso grafo.
        for(Integer j=0; j<this.verticesNum; j++){
            // A cada iteração buscamos a posição edgeId, j na nossa matriz de adjacencias
            // e realizamos a verificação se o peso associado é maior que zero:
            // - Caso seja maior: Adicionamos o índice à lista
            // - Caso não seja: Não fazemos nada
            if(edges[getVerticeIndex(value)][j] > 0) idList.add(j);
        }

        // Ao fim do programa retornamos a lista
        return idList;
    }

    /*
     * getWeightsMap --> Retorna um HashMap contendo a "vizinhança" tendo o índice como chave e o peso como valor associado 
    */
    public Map<Integer, Float> getWeightsMap(T value){
        Map<Integer, Float> weightsMap = new HashMap<Integer, Float>();

        // Laço de repetição que itera sobre j enquanto o mesmo é menor que 
        // o número de itens do nosso grafo.
        for(Integer j=0; j<this.verticesNum; j++){
            // A cada iteração buscamos a posição edgeId, j na nossa matriz de adjacencias
            // e realizamos a verificação se o peso associado é maior que zero:
            // - Caso seja maior: Adicionamos um novo item ao nosso HashMap
            // - Caso não seja: Não fazemos nada
            if(edges[getVerticeIndex(value)][j] > 0) weightsMap.put(j, edges[getVerticeIndex(value)][j]);
        }

        // Ao fim do programa retornamos o HashMap
        return weightsMap;
    }

    /*
     * getMST --> Retorna um grafo contendo a Árvore Geradora Mínima
    */
    public Graph<T> getMST(){
        Integer origin[] = new Integer[this.verticesNum];
        float weight[] = new float[this.verticesNum];
        Boolean mstSet[] = new Boolean[this.verticesNum];
        Graph<T> graph = new Graph<T>(verticesNum);

        // Preenche os arrays com valores "infinitos" e falsos para que 
        // nossas comparações ocorram corretamente
        Arrays.fill(weight, 999999999); 
        Arrays.fill(mstSet, false);

        // Setamos a chave do vértice 0 como 0 para que possamos iniciar com ele
        // e setamos sua origem como -1 já que ele é o primeiro
        weight[0] = 0;
        origin[0] = -1;

        // Entramos em um laço de repetição para percorremos os vertices
        for(int count = 0; count < this.verticesNum - 1; count++){

            // Buscamos o índice de menor chave com nossa função privada
            // e setamos esse índice como true para marcarmos que 
            // ele já esta presente na nossa árvore
            Integer u = getMinWeight(weight, mstSet);
            mstSet[u] = true;

            // Entramos em mais um laço de repeticação iterando sobre o número de vertices
            for (int v = 0; v < this.verticesNum; v++) {
                // Verificamos primeiro se existe uma aresta entre os vértices,
                // em seguida garantimos que o vértice não está na nossa árvore geradora mínima e
                // que o peso da aresta entre os dois vértices é menor que o peso associado ao vertice v
                if (getEdges()[u][v] != 0 && !mstSet[v] && getEdges()[u][v] < weight[v]) {
                    origin[v] = u; // Define o vértice u como pai do vértice v
                    weight[v] = getEdges()[u][v]; // Atualiza a chave do vértice v
                }
            }

        }
        
        // Laço de repetição preenchendo um grafo contendo a árvore geradora minima
        for (int i = 1; i < this.verticesNum; i++) {
            if (origin[i] != null) {
                graph.addEdge(vertices.get(origin[i]).getValue(), vertices.get(i).getValue(), getEdges()[i][origin[i]]);
                graph.addEdge(vertices.get(i).getValue(), vertices.get(i).getValue(), getEdges()[i][origin[i]]);

            } else {
                // Adicionar tratamento para vértices desconexos (opcional)
                // Por exemplo, você pode querer criar um vértice isolado no grafo
                graph.addVertice(vertices.get(i).getValue());
            }
        }
        // Retorno do grafo com a árvore geradora minima
        return graph;
    }

    // getMinweight --> Retorna o índice do menor peso que ainda não foi visitada
    private Integer getMinWeight(float weight[], Boolean mstSet[]){
        float min = 999999999;
        Integer minIndex = -1;

        // Percorre todos os vértices
        for(int v = 0; v < this.verticesNum; v++){
            // Verifica se o vértice não está na árvore geradora minima e
            // se o peso dele é o menor que o minimo
            // - Caso verdadeiro: Setamos um novo minimo e o índice do valor minimo
            // - Caso não seja: Não fazemos nada
            if(!mstSet[v] && weight[v] < min){
                min = weight[v];
                minIndex = v;
            }
        }

        // Ao fim do programa retornamos o índice do menor peso encontrado
        return minIndex;
    }

    public HashMap<T, T> getDijkstra(T origin){

        float distances[] = new float[verticesNum];
        List<T> queueVertice = new ArrayList<T>();
        List<Float> queueDistance = new ArrayList<Float>();
        float current_distance, aux_distance;
        T current_vertice;

        HashMap<T, T> predecessors = new HashMap<T, T>();

        for(int i = 0; i < verticesNum; i++){
            distances[i] = 999999999;
        }

        distances[getVerticeIndex(origin)] = 0;

        queueVertice.add(origin);
        queueDistance.add((float) 0);

        while(queueDistance.size() > 0){
            current_distance = queueDistance.get(0);
            current_vertice = queueVertice.get(0);

            queueDistance.remove(0);
            queueVertice.remove(0);

            if(current_distance <= distances[getVerticeIndex(current_vertice)]){
                for(Map.Entry<Integer, Float> neighbor : getWeightsMap(current_vertice).entrySet()){
                    aux_distance = current_distance + neighbor.getValue();
                    if(aux_distance < distances[neighbor.getKey()]){
                        distances[neighbor.getKey()] = aux_distance;
                        predecessors.put(this.vertices.get(neighbor.getKey()).getValue(), current_vertice);
                        queueDistance.add(aux_distance);
                        queueVertice.add(this.vertices.get(neighbor.getKey()).getValue());
                    }
                }
            }

        }

        return predecessors;
    }

    public ArrayList<T> minPath(Map<T, T> predecessors, T start, T end){
        ArrayList<T> path = new ArrayList<T>();
        T currentVertice = getVertice(end).getValue();
        
        while(!currentVertice.equals(getVertice(start).getValue())){
            path.add(currentVertice);
            currentVertice = predecessors.get(currentVertice);
        }

        path.add(getVertice(start).getValue());
        Collections.reverse(path);
        return path;
    }

    // printAdjacencyMatrix --> Imprime a matriz de adjacencias do grafo
    // Obs.: Visualição fica esquisita para grafos com mais de 6 itens
    public void printAdjacencyMatrix(){
        // Configura a largura da coluna
        int colWidth = 5;

        // Imprime o cabeçalho
        System.out.print("|");
        for (int i = 0; i < this.verticesNum + 1; i++) {
            if(i > 0) System.out.printf("\t%s\t|", i-1);
            else System.out.printf("\t%s\t|", "X" );
        }
        System.out.println();

        // Imprime a linha horizontal separadora
        for (int i = 0; i < this.verticesNum * 4; i++) {
            System.out.print("+");
            for (int j = 0; j < colWidth-1; j++) {
                System.out.print("-");
            }
        }
        System.out.println("--+");

        // Imprime a matriz de adjacência
        for (int i = 0; i < this.verticesNum; i++) {
            System.out.printf("|\t%d\t|", i);
            for (int j = 0; j < this.verticesNum; j++) {
                if(this.edges[i][j] > 0)
                    System.out.printf("\t\u001b[1m\u001b[33m%.2f\t\u001b[m|", this.edges[i][j]);
                else
                    System.out.printf("\t%.2f\t|", this.edges[i][j]);

            }
            System.out.println();

            // for (int c = 0; c < this.verticesNum + 16; c++) {
            //     System.out.print("-");
            //     for (int j = 0; j < colWidth-1; j++) {
            //         System.out.print("-");
            //     }
            // }
            // System.out.println("-->");
        
        }

        // Imprime a linha horizontal separadora
        for (int i = 0; i < this.verticesNum * 4; i++) {
            System.out.print("+");
            for (int j = 0; j < colWidth-1; j++) {
                System.out.print("-");
            }
        }
        System.out.println("--+");

    }

    /*
     * getVertice --> Retornamos o vértice a partir de um valor
     */  
    public Vertice<T> getVertice(T value){
        // Iteramos sobre nossa lista de vértices comparando com o valor de entrada
        for(Vertice<T> item : vertices){
            if(item.getValue().equals(value)) return item;
        }

        return null;
    }

    /*
     * Getters & Setters --> Retornam e Modificam atributos gerais da nossa classe
     */    
	public List<Vertice<T>> getVertices() {
		return this.vertices;
	}

	public void setVertices(List<Vertice<T>> vertices) {
		this.vertices = vertices;
	}

	public float [][] getEdges() {
		return this.edges;
	}

	public void setEdges(float edges[][]) {
		this.edges = edges;
	}

	public int getVerticesNum() {
		return this.verticesNum;
	}

	public void setVerticesNum(int verticesNum) {
		this.verticesNum = verticesNum;
	}

    
}