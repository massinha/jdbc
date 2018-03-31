package Model;

public class Movie {

    private Integer id;
    private String name;

    public Movie(){}

    public Movie(String name){
        this.name = name;
    }

    public Movie(Integer id, String name){
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
