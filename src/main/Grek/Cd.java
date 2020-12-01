package Grek;

public class Cd extends Ware{


    public int tabs = 1;

    public Cd(String name, int price) {
        super(name, price);
    }

    @Override
    public String toString() { String a = "\t".repeat(tabs);
        return a + "CD : " + name + " price : " + price;
    }
}
