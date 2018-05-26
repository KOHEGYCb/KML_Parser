package kmlpars;

import java.io.IOException;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import kmlpars.gui.Main;
import kmlpars.gui.NewLang;
import kmlpars.gui.NewUsage;
import kmlpars.service.Constants;
import kmlpars.service.KmlParseLang;
import kmlpars.service.KmlParseUsage;
import kmlpars.service.KmlParseWord;
import org.xml.sax.SAXException;

/**
 *
 * @author dmitry
 */
public class KMLPars {

    public static Main MAIN;
    public static final JFrame NEW_LANG = new NewLang();
    public static final JFrame NEW_USAGE = new NewUsage();

    /**
     * @param args the command line arguments
     * @throws org.xml.sax.SAXException
     * @throws java.io.IOException
     * @throws javax.xml.parsers.ParserConfigurationException
     * @throws javax.xml.transform.TransformerException
     */
    public static void main(String[] args) throws SAXException, IOException, ParserConfigurationException, TransformerException {

        KmlParseLang.getAllLang();
        KmlParseUsage.getAllUsage();
        KmlParseWord.getAllWords();
        MAIN = new Main();
        refresh();
        MAIN.setVisible(true);

    }
    
    public static void refresh(){
        DefaultListModel defaultListModel = new DefaultListModel();
        String list[] = new String[Constants.WORDS.size()];
        for (int i = 0; i < Constants.WORDS.size(); i++) {
            list[i] = Constants.WORDS.get(i).getName();
            defaultListModel.addElement(Constants.WORDS.get(i).getName());
        }

        MAIN.getjList1().setModel(defaultListModel);
    }

}
