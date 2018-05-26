package kmlpars.beans;

/**
 *
 * @author dmitry
 */
public class Lang {

    @Override
    public String toString() {
        return "Lang{" + "id=" + id + ", title=" + title + '}';
    }
    
    private int id;
    private Title title = new Title();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Title getTitle() {
        return title;
    }

    public void setTitle(Title title) {
        this.title = title;
    }
    
}
