package kmlpars.service;

import kmlpars.beans.Lang;

/**
 *
 * @author dmitry
 */
public class LangWorker {

    public static void addLang(String name) {
        Lang lang = new Lang();
        lang.setId(Constants.LANGS.get(Constants.LANGS.size() - 1).getId() + 1);
        lang.getTitle().setName(name);
        lang.getTitle().setLang("ru");
        Constants.LANGS.add(lang);

        for (Lang LANGS : Constants.LANGS) {
            System.out.println(LANGS);
        }
    }

    static Lang getLangById(int id) {
        Lang lang = null;

        for (int i = 0; i < Constants.LANGS.size(); i++) {
            if (Constants.LANGS.get(i).getId() == id) {
                lang = Constants.LANGS.get(i);
                break;
            }
        }

        return lang;
    }

    public static Lang getLangByName(String string) {
        Lang lang = null;

        for (int i = 0; i < Constants.LANGS.size(); i++) {
            if (Constants.LANGS.get(i).getTitle().getName().equals(string)) {
                lang = Constants.LANGS.get(i);
                break;
            }
        }

        return lang;
    }

}
