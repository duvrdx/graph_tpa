public class City {
    Integer code;
    String name;

    public Integer getCode() {
        return this.code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public City(Integer code, String name){
        this.code = code;
        this.name = name;
    }

    public String toString(){
        return String.format(name);
    }

    @Override
    public boolean equals(Object o){
        if(this == o) {
            return true;
        }

        if(!(o instanceof City)) {
            return false;
        }

        return this.code.equals(((City) o).getCode());
    }
}
