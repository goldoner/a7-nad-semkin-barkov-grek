package Grek;

import assignment7_int.XMLData;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.Optional;


public class MyXMLData implements XMLData {


    protected WareList mainList;


    @Override
    public void loadXml(File input) throws ParserConfigurationException, IOException, SAXException {

        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        Document doc = db.parse(input);
        Element root = doc.getDocumentElement();

        //TODO: find a good way to access the elements and their attributes (helper methods)

        NodeList nl = root.getChildNodes();

        WareList rootList = new WareList(root.getAttribute("name"), 0);
        rootList.countPrice();
        rootList.accumulateNodes(nl);
        rootList.represent();
        mainList = rootList;


    }

    @Override
    public Optional<Double> getPrice(String item) throws Exception {
        Optional<Double> out;
        if (mainList == null) {
            throw new Exception("File was not read!!! -_- ");
        } else if (mainList.getName().equals(item)) {
            return Optional.of((double) mainList.getPrice());
        } else {
            out = Optional.of((double) lookFor(mainList, item));
        }

        return out;
    }


    private Integer lookFor(WareList list, String item) {

        Integer out = -1;

        for (Ware element : list.getWareList()) {

            if (element.getName().contains("L") && !element.getName().equals(item)) {

                WareList wareList = (WareList) element;
                wareList.countPrice();

                out = lookFor(wareList, item);

            }

            if (element.getName().equals(item)) {
                return element.getPrice();
            }

            if (out != -1) {
                return out;
            }

        }
        return out;

    }


}
