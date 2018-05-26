package kmlpars.service;

import java.util.ArrayList;
import kmlpars.beans.Lang;
import kmlpars.beans.Usage;
import kmlpars.beans.Word;

/**
 *
 * @author dmitry
 */
public class Constants {
    
    public static final ArrayList<Lang> LANGS = new ArrayList<Lang>();
    public static final ArrayList<Usage> USAGES = new ArrayList<Usage>();
    public static final ArrayList<Word> WORDS = new ArrayList<Word>();
    
    public static final String FILE_LANG = "lang.kml";
    public static final String FILE_USAGE = "usage.kml";
    public static final String FILE_WORDS = "words.kml";
    
    public static final String TAG_UNIT = "unit";
    public static final String TAG_SCOPE = "scope";
    public static final String TAG_CONTENT = "content";
    public static final String TAG_TITLE = "title";
    
    public static final String ART_LANG = "lang";
    public static final String ATR_ID = "id";
    public static final String ATR_ORIGINAL = "orignal";
    public static final String ATR_USAGE = "usage";
    
    public static final String ATR_ORIGINAL_TRUE = "true";
    
}
