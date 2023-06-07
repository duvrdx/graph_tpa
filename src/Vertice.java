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

    @Override
    public boolean equals(Object o){
        if(this == o) {
            return true;
        }

        if(!(o instanceof Vertice)) {
            return false;
        }

        return this.value.equals(((Vertice<T>) o).getValue());
    }
}