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
import kmlpars.beans.Lang;
import kmlpars.beans.Title;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author dmitry
 */
public class KmlParseLang {

    public static void getAllLang() throws SAXException, IOException, ParserConfigurationException {

        File langFile = new File(Constants.FILE_LANG);
        DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = builderFactory.newDocumentBuilder();
        Document document = builder.parse(langFile);

        NodeList langs = document.getElementsByTagName(Constants.TAG_UNIT);
        for (int i = 0; i < langs.getLength(); i++) {
            if (langs.item(i).getNodeType() == Node.ELEMENT_NODE) {
                Lang lang = new Lang();
                lang.setId(Integer.valueOf(langs.item(i).getAttributes().getNamedItem(Constants.ATR_ID).getTextContent()));

                Title title = new Title();
                NodeList titles = langs.item(i).getChildNodes();
                for (int j = 0; j < titles.getLength(); j++) {
                    if (titles.item(j).getNodeType() == Node.ELEMENT_NODE) {
                        title.setLang(titles.item(j).getAttributes().getNamedItem(Constants.ART_LANG).getTextContent());
                        title.setName(titles.item(j).getTextContent());
                    }
                }
                lang.setTitle(title);
                Constants.LANGS.add(lang);
            }
        }
    }

    public static void writeDocument() throws SAXException, IOException, ParserConfigurationException, TransformerException, TransformerException {
        DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = builderFactory.newDocumentBuilder();
        Document document = builder.newDocument();

        Element scope = document.createElement("scope");

        for (int i = 0; i < Constants.LANGS.size(); i++) {
            Lang lang = Constants.LANGS.get(i);
            Element unit = document.createElement("unit");
            unit.setAttribute("id", String.valueOf(lang.getId()));

            Element title = document.createElement("title");
            title.setAttribute("lang", lang.getTitle().getLang());
            title.setTextContent(lang.getTitle().getName());

            unit.appendChild(title);

            scope.appendChild(unit);
        }

        document.appendChild(scope);
        Transformer tr = TransformerFactory.newInstance().newTransformer();
        DOMSource source = new DOMSource(document);
        FileOutputStream fos = new FileOutputStream(Constants.FILE_LANG);
        StreamResult result = new StreamResult(fos);
        tr.transform(source, result);
    }
}
