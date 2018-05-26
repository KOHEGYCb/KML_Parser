package kmlpars.service;

import kmlpars.beans.Word;

/**
 *
 * @author dmitry
 */
public class WordWorker {

    public static Word getWordByName(String name) {
        for (int i = 0; i < Constants.WORDS.size(); i++){
            if (Constants.WORDS.get(i).getName().equals(name)){
                return Constants.WORDS.get(i);
            }
        }
        return null;
    }
    
}
