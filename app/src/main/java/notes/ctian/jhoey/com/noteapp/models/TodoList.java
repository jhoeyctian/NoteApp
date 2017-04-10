package notes.ctian.jhoey.com.noteapp.models;

/**
 * Created by jhoey on 4/7/2017.
 */

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/** 1 TodoList = multiple todoItem */
public class TodoList {

    long id;
    public String title;
    public String date;

    public long getId() {
        return id;
    }

    public void setId(long id) {
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

    public String getReadableModifiedDate(){
        SimpleDateFormat sdf = new SimpleDateFormat("MMM dd, yyyy - h:mm a", Locale.getDefault());
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(Long.parseLong(date));
        return sdf.format(cal.getTime());
    }

}
