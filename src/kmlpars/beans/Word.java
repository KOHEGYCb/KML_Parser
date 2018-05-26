package kmlpars.beans;

/**
 *
 * @author dmitry
 */
public class Word {
    
    int id;
    String name;
    String o_name;
    Lang lang = new Lang();

    @Override
    public String toString() {
        return "Word{" + "id=" + id + ", name=" + name + ", o_name=" + o_name + ", usage=" + usage + ", description=" + description + '}';
    }

    public String getO_name() {
        return o_name;
    }

    public void setO_name(String o_name) {
        this.o_name = o_name;
    }
    Usage usage = new Usage();
    String description;

    public Lang getLang() {
        return lang;
    }

    public void setLang(Lang lang) {
        this.lang = lang;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Usage getUsage() {
        return usage;
    }

    public void setUsage(Usage usage) {
        this.usage = usage;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    
}
