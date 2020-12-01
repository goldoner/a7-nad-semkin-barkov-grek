package Grek;

public class Book extends Ware {

    public int tabs = 1;
    protected String id;

    public Book(int price, String name, String id) {
        super(name, price);
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        String a = "\t".repeat(tabs);
        return a + "Book : " + name + " price : " + price + " ID : " + id;
    }
}
