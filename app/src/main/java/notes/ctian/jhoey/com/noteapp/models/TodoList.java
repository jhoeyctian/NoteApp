package notes.ctian.jhoey.com.noteapp.models;

/**
 * Created by jhoey on 4/7/2017.
 */

/** 1 TodoList = multiple todoItem */
public class TodoList {

    int id;
    public String title;
    public String date;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

}
