package Model.Entities;

public class Service {
    private Integer id;
    private String  name;
    private Integer price;

    public Service(Integer id, String name, Integer price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public Integer getId() {
        return id;
    }

    public Service setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Service setName(String name) {
        this.name = name;
        return this;
    }

    public Integer getPrice() {
        return price;
    }

    public Service setPrice(Integer price) {
        this.price = price;
        return this;
    }
}
