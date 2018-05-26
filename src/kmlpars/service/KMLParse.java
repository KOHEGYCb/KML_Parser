package kmlpars.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import kmlpars.beans.Word;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author dmitry
 */
public class KMLParse {

    public static ArrayList<Word> getWords () throws SAXException, IOException, IOException, ParserConfigurationException{
        ArrayList<Word> words = new ArrayList<>();
        
        File file = new File("words.kml");
        DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = builderFactory.newDocumentBuilder();
        Document document = builder.parse(file);
        
        NodeList elements = document.getElementsByTagName("unit");
        
        for (int i = 0; i < elements.getLength(); i++){
            if (elements.item(i).getNodeType() == Node.ELEMENT_NODE){
                Element e = (Element) elements.item(i);
                
                Word word = new Word();
                
                NodeList wordPar = e.getChildNodes();
                for (int j = 0; j < wordPar.getLength(); j++){
                    if (wordPar.item(j).getNodeType() == Node.ELEMENT_NODE){
                        Element param = (Element) wordPar.item(j);
                        switch (param.getTagName()){
                            case "title":
                                if ("true".equals(param.getAttribute("orignal"))){
                                    word.setName(param.getTextContent());
                                }
                                break;
                        }
                    }
                }
                words.add(word);
            }
//            System.out.println("id:" + e.getAttribute("di"));
        }
        add(document);
        return words;
    } 
    
    private static void add(Document document){
        Node root = document.getDocumentElement();
        
        Element book = document.createElement("unit");

        Element title = document.createElement("title");
        title.setTextContent("epure");
        title.setAttribute("lang",  "2");
        title.setAttribute("original", "true");

        Element title_ru = document.createElement("title");
        title_ru.setTextContent("Эпюр");
        title.setAttribute("lang",  "5");

        Element scope = document.createElement("scope");
        scope.setAttribute("usage", "2");

        Element content = document.createElement("content");
        content.setTextContent("чертёж, на котором пространственная фигура изображена методом нескольких (по ГОСТу трёх, но не всегда) плоскостей.");
        content.setAttribute("lang",  "5");
        

        book.appendChild(title);
        book.appendChild(title_ru);
        book.appendChild(scope);
        book.appendChild(content);

        root.appendChild(book);
        
        // Записываем XML в файл
        writeDocument(document);
    }
    
    private static void writeDocument(Document document) throws TransformerFactoryConfigurationError {
        try {
            Transformer tr = TransformerFactory.newInstance().newTransformer();
            DOMSource source = new DOMSource(document);
            FileOutputStream fos = new FileOutputStream("other.xml");
            StreamResult result = new StreamResult(fos);
            tr.transform(source, result);
        } catch (TransformerException | IOException e) {
            e.printStackTrace(System.out);
        }
    }
    
}
