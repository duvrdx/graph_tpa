public class Vertice<T>{
    private T value;

    public Vertice(T value){
        this.value = value;
    }

    public T getValue() {
        return this.value;
    }

    public void setValue(T value) {
        this.value = value;
    }
}