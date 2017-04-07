package notes.ctian.jhoey.com.noteapp.models;

/**
 * Created by jhoey on 4/6/2017.
 */

public class TodoItem {

    int id;
    String todo;
    String status;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTodo() {
        return todo;
    }

    public void setTodo(String todo) {
        this.todo = todo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
