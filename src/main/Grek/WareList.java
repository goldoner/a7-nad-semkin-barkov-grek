package Grek;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.ArrayList;
import java.util.List;

public class WareList extends Ware {

    public int tabs = 1;
    public static int t = 1;


    protected String name;
    protected List<Ware> wareList;

    public WareList(String name, Integer price) {
        super(name, price);
        this.name = name;
        wareList = new ArrayList<>();
    }


    public void accumulateNodes(NodeList nodeList) {


        for (int i = 0; i < nodeList.getLength(); i++) {


            Node anotherNode = nodeList.item(i);
            int anotherType = anotherNode.getNodeType();


            if (anotherType == Node.ELEMENT_NODE) {
                Element anotherElement = (Element) anotherNode;

                switch (anotherElement.getTagName()) {
                    case "book" -> {
                        Book book = new Book(Integer.parseInt(anotherElement.getAttribute("price")),
                                anotherElement.getAttribute("name"),
                                anotherElement.getAttribute("isbn"));
                        book.tabs = t;
                        this.addWareToTheList(book);

                    }
                    case "cd" -> {
                        Cd cd = new Cd(anotherElement.getAttribute("name"),
                                Integer.parseInt(anotherElement.getAttribute("price")));
                        this.addWareToTheList(cd);
                        cd.tabs = t;

                    }
                    case "list" -> {
                        WareList anotherList = new WareList(anotherElement.getAttribute("name"), 0);
                        anotherList.tabs = t++;
                        anotherList.accumulateNodes(anotherElement.getChildNodes());
                        this.addWareToTheList(anotherList);

                    }
                }

            }

        }

        countPrice();


        t--;
    }


    public void represent() {
        if (name.contains("root")) {
            System.out.println(this.toString().replaceAll("\\t", ""));
        }
        for (Object a : wareList) {

            Book book = a instanceof Book ? ((Book) a) : null;
            Cd cd = a instanceof Cd ? ((Cd) a) : null;
            WareList wareList = a instanceof WareList ? ((WareList) a) : null;

            if (book != null) {
                System.out.println(book);
            } else if (cd != null) {
                System.out.println(cd);
            } else if (wareList != null) {
                System.out.println(wareList);
                wareList.represent();
            }
        }

    }

    public void countPrice() {

        int counter = 0;

        for (Ware a : wareList) {
            counter += a.getPrice();
        }
        price = counter;

    }


    public List<Ware> getWareList() {
        return wareList;
    }

    public void addWareToTheList(Ware a) {
        wareList.add(a);
    }

    @Override
    public String toString() {
        String a = "\t".repeat(tabs);
        return a + "List : " + name + " total child items" + getWareList().size();
    }


}
