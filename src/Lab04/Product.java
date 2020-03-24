package Lab04;

public class Product {

    @JsonField(name= "code")
    public int id;

    @JsonField
    public String name;

    @JsonField(lengthToTrimString = 20)
    public String description;

    public String storeName;

    public Product(int id, String name, String description, String storeName){
        this.id = id;
        this.name = name;
        this.description = description;
        this.storeName = storeName;
    }

}