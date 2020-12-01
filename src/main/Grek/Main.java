package Grek;


import java.io.File;


public class Main {

    public static void main(String[] args) throws Exception {

        MyXMLData myXMLData = new MyXMLData();
        myXMLData.loadXml(new File("src/test/input.xml"));
        System.out.println(myXMLData.getPrice("L1").get());


    }
}
