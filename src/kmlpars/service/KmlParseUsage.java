package kmlpars.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import kmlpars.beans.Title;
import kmlpars.beans.Usage;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author dmitry
 */
public class KmlParseUsage {

    public static void getAllUsage() throws SAXException, IOException, ParserConfigurationException {

        File usageFile = new File(Constants.FILE_USAGE);
        DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = builderFactory.newDocumentBuilder();
        Document document = builder.parse(usageFile);

        NodeList usages = document.getElementsByTagName(Constants.TAG_UNIT);
        for (int i = 0; i < usages.getLength(); i++) {
            if (usages.item(i).getNodeType() == Node.ELEMENT_NODE) {
                Usage usage = new Usage();
                usage.setId(Integer.valueOf(usages.item(i).getAttributes().getNamedItem(Constants.ATR_ID).getTextContent()));

                Title title = new Title();
                NodeList titles = usages.item(i).getChildNodes();
                for (int j = 0; j < titles.getLength(); j++) {
                    if (titles.item(j).getNodeType() == Node.ELEMENT_NODE) {
                        title.setLang(titles.item(j).getAttributes().getNamedItem(Constants.ART_LANG).getTextContent());
                        title.setName(titles.item(j).getTextContent());
                    }
                }
                usage.setTitle(title);
                System.out.println(usage);
                Constants.USAGES.add(usage);
            }
        }
    }

    public static void writeDocument() throws SAXException, IOException, ParserConfigurationException, TransformerException, TransformerException {
        DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = builderFactory.newDocumentBuilder();
        Document document = builder.newDocument();

        Element scope = document.createElement("scope");

        for (int i = 0; i < Constants.USAGES.size(); i++) {
            Usage usage = Constants.USAGES.get(i);
            Element unit = document.createElement("unit");
            unit.setAttribute("id", String.valueOf(usage.getId()));

            Element title = document.createElement("title");
            title.setAttribute("lang", usage.getTitle().getLang());
            title.setTextContent(usage.getTitle().getName());

            unit.appendChild(title);

            scope.appendChild(unit);
        }

        document.appendChild(scope);
        Transformer tr = TransformerFactory.newInstance().newTransformer();
        DOMSource source = new DOMSource(document);
        FileOutputStream fos = new FileOutputStream(Constants.FILE_USAGE);
        StreamResult result = new StreamResult(fos);
        tr.transform(source, result);
    }
}
