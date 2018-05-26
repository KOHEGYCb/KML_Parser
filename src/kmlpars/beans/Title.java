package kmlpars.beans;

/**
 *
 * @author dmitry
 */
public class Title {

    private String lang;
    private String name;
    private boolean isOriginal;

    public String getLang() {
        return lang;
    }

    @Override
    public String toString() {
        return "Title{" + "lang=" + lang + ", name=" + name + ", isOriginal=" + isOriginal + '}';
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isIsOriginal() {
        return isOriginal;
    }

    public void setIsOriginal(boolean isOriginal) {
        this.isOriginal = isOriginal;
    }

}
