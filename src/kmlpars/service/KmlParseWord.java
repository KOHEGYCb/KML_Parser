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
public class KmlParseWord {

    public static void getAllWords() throws SAXException, ParserConfigurationException, IOException {
        File wordFile = new File(Constants.FILE_WORDS);
        DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = builderFactory.newDocumentBuilder();
        Document document = builder.parse(wordFile);

        NodeList words = document.getElementsByTagName(Constants.TAG_UNIT);

        for (int i = 0; i < words.getLength(); i++) {
            if (words.item(i).getNodeType() == Node.ELEMENT_NODE) {
                Node word = words.item(i);
                Word new_word = new Word();

                new_word.setId(Integer.valueOf(word.getAttributes().getNamedItem(Constants.ATR_ID).getTextContent()));

                NodeList wordParams = word.getChildNodes();

                for (int j = 0; j < wordParams.getLength(); j++) {

                    if (wordParams.item(j).getNodeType() == Node.ELEMENT_NODE) {
                        Node parameter = wordParams.item(j);
                        switch (parameter.getNodeName()) {
                            case Constants.TAG_CONTENT:
                                new_word.setDescription(parameter.getTextContent());
                                break;
                            case Constants.TAG_TITLE:
                                if (parameter.getAttributes().getNamedItem(Constants.ATR_ORIGINAL) != null) {
                                    if (Constants.ATR_ORIGINAL_TRUE.equals(parameter.getAttributes().getNamedItem(Constants.ATR_ORIGINAL).getTextContent())) {
                                        new_word.setLang(LangWorker.getLangById(Integer.valueOf(parameter.getAttributes().getNamedItem(Constants.ART_LANG).getTextContent())));
                                        new_word.setO_name(parameter.getTextContent());
                                    }
                                } else {
                                    new_word.setName(parameter.getTextContent());
                                }
                                break;
                            case Constants.TAG_SCOPE:
                                if (parameter.getAttributes().getNamedItem(Constants.ATR_USAGE) != null) {
                                    new_word.setUsage(UsageWorker.getUsageById(Integer.valueOf(parameter.getAttributes().getNamedItem(Constants.ATR_USAGE).getTextContent())));
                                }
                        }

                    }

                }
                System.out.println(new_word);
                Constants.WORDS.add(new_word);
            }
        }
    }

    public static void writeDocument() throws SAXException, IOException, ParserConfigurationException, TransformerException, TransformerException {
        DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = builderFactory.newDocumentBuilder();
        Document document = builder.newDocument();

        Element scope = document.createElement(Constants.TAG_SCOPE);

        for (int i = 0; i < Constants.WORDS.size(); i++) {
            Word word = Constants.WORDS.get(i);
            Element unit = document.createElement(Constants.TAG_UNIT);
            unit.setAttribute(Constants.ATR_ID, String.valueOf(word.getId()));

            Element title = document.createElement(Constants.TAG_TITLE);
            title.setAttribute(Constants.ART_LANG, "5");
            title.setTextContent(word.getName());

            Element title_o = document.createElement(Constants.TAG_TITLE);
            title_o.setAttribute(Constants.ART_LANG, word.getLang().getId() + "");
            title_o.setAttribute(Constants.ATR_ORIGINAL, Constants.ATR_ORIGINAL_TRUE);
            title_o.setTextContent(word.getO_name());

            Element usage = document.createElement(Constants.TAG_SCOPE);
            usage.setAttribute(Constants.ATR_USAGE, word.getUsage().getId() + "");

            Element description = document.createElement(Constants.TAG_CONTENT);
            description.setAttribute(Constants.ART_LANG, "5");
            description.setTextContent(word.getDescription());

            unit.appendChild(title_o);
            unit.appendChild(title);
            unit.appendChild(usage);
            unit.appendChild(description);

            scope.appendChild(unit);
        }

        document.appendChild(scope);
        Transformer tr = TransformerFactory.newInstance().newTransformer();
        DOMSource source = new DOMSource(document);
        FileOutputStream fos = new FileOutputStream(Constants.FILE_WORDS);
        StreamResult result = new StreamResult(fos);
        tr.transform(source, result);
    }

}
