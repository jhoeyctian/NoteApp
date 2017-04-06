package notes.ctian.jhoey.com.noteapp.models;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by jhoey on 4/4/2017.
 */

public class Note {

    private Long id;
    private String title;
    private String content;
    private Long dateCreated;
    private Long dataModified;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Long dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Long getDataModified() {
        return dataModified;
    }

    public void setDataModified(Long dataModified) {
        this.dataModified = dataModified;
    }

    public String getReadableModifiedDate(){
        SimpleDateFormat sdf = new SimpleDateFormat("MMM dd, yyyy - h:mm a", Locale.getDefault());
        Date modifiedDate = new Date();
        return sdf.format(modifiedDate);
    }
}
