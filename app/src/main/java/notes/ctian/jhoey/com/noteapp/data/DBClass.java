package notes.ctian.jhoey.com.noteapp.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import notes.ctian.jhoey.com.noteapp.models.Note;

/**
 * Created by jhoey on 4/4/2017.
 */

public class DBClass {

    public static final String KEY_NOTE_ROWID = "_id";
    public static final String KEY_NOTE_TITLE = "notetitle";
    public static final String KEY_NOTE_CONTENT = "notecontent";
    public static final String KEY_NOTE_DATE = "notedate";

    public static final String KEY_TODO_ROWID = "_id";
    public static final String KEY_TODO_TITLE = "todotitle";
    public static final String KEY_TODO_DATE = "tododate";

    public static final String KEY_TODO_ITEM_ROWID = "_id";
    public static final String KEY_TODO_ID = "_task_id";
    public static final String KEY_TODO_ITEM_TASK = "todoitemtask";
    public static final String KEY_TODO_ITEM_STATUS = "todoitemtaskstatus";

    private static final String DATABASE_NAME = "notedb";
    private static final String DATABASE_NOTE_TABLE = "notetable";
    private static final String DATABASE_TODO_TABLE = "todotable";
    private static final String DATABASE_TODO_ITEM_TABLE = "todoitemtable";
    private static final int DATABASE_VERSION = 5;

    private DBHelper dbHelper;
    private final Context context;
    private SQLiteDatabase noteDatabase;

    public DBClass(Context c){
        context = c;
    }

    public DBClass open(){
        dbHelper = new DBHelper(context);
        noteDatabase = dbHelper.getWritableDatabase();
        return this;
    }

    public void close(){
        dbHelper.close();
    }

    public List<Note> getNotes() {

        List<Note> notes = new ArrayList<>();

        String columns[] = new String[]{KEY_NOTE_ROWID, KEY_NOTE_TITLE, KEY_NOTE_CONTENT, KEY_NOTE_DATE};

        Cursor cursor = noteDatabase.query(DATABASE_NOTE_TABLE, columns, null , null, null, null, null);

        int iRow = cursor.getColumnIndex(KEY_NOTE_ROWID);
        int iTitle = cursor.getColumnIndex(KEY_NOTE_TITLE);
        int iContent = cursor.getColumnIndex(KEY_NOTE_CONTENT);
        int iDate = cursor.getColumnIndex(KEY_NOTE_DATE);

        for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()){
            Note obj = new Note();
            obj.setId(Long.parseLong(cursor.getString(iRow)));
            obj.setTitle(cursor.getString(iTitle));
            obj.setContent(cursor.getString(iContent));
            obj.setDateCreated(Long.parseLong(cursor.getString(iDate)));
            notes.add(obj);
        }

        return notes;
    }

    public void deleteEntry(long dataId, String type) {
        if(type.equalsIgnoreCase("note")){
            noteDatabase.delete(DATABASE_NOTE_TABLE, KEY_NOTE_ROWID + "=" + dataId, null);
        }
    }

    public long updateEntry(String id, String title, String content, String type) {
        if(type.equalsIgnoreCase("note")){
            ContentValues cv = new ContentValues();
            cv.put(KEY_NOTE_ROWID, id);
            cv.put(KEY_NOTE_TITLE, title);
            cv.put(KEY_NOTE_CONTENT, content);
            cv.put(KEY_NOTE_DATE, Calendar.getInstance().getTimeInMillis()+"");
            return noteDatabase.update(DATABASE_NOTE_TABLE, cv, KEY_NOTE_ROWID + "=" + id, null);
        }
        return 0l;
    }

    public long createEntry(String title, String content, String type) {
        if(type.equalsIgnoreCase("note")){
            ContentValues cv = new ContentValues();
            cv.put(KEY_NOTE_TITLE, title);
            cv.put(KEY_NOTE_CONTENT, content);
            cv.put(KEY_NOTE_DATE, Calendar.getInstance().getTimeInMillis()+"");
            return noteDatabase.insert(DATABASE_NOTE_TABLE, null, cv);
        }
        return 0l;
    }

    private static class DBHelper extends SQLiteOpenHelper {

        public DBHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL("CREATE  TABLE IF NOT EXISTS " + DATABASE_NOTE_TABLE + " ( "
                    + KEY_NOTE_ROWID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, "
                    + KEY_NOTE_TITLE + " TEXT NOT NULL, "
                    + KEY_NOTE_CONTENT + " TEXT NOT NULL, "
                    + KEY_NOTE_DATE + " TEXT NOT NULL); ");

            db.execSQL("CREATE  TABLE IF NOT EXISTS " + DATABASE_TODO_TABLE + " ( "
                    + KEY_TODO_ROWID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, "
                    + KEY_TODO_TITLE + " TEXT NOT NULL, "
                    + KEY_TODO_DATE + " TEXT NOT NULL); ");

            db.execSQL("CREATE  TABLE IF NOT EXISTS " + DATABASE_TODO_ITEM_TABLE + " ( "
                    + KEY_TODO_ITEM_ROWID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, "
                    + KEY_TODO_ITEM_TASK + " TEXT NOT NULL, "
                    + KEY_TODO_ITEM_STATUS + " TEXT NOT NULL, "
                    + KEY_TODO_ID + " INTEGER NOT NULL, "
                    + "FOREIGN KEY("+KEY_TODO_ID+") REFERENCES "+DATABASE_TODO_TABLE+"("+KEY_TODO_ROWID+") "
                    + "); ");
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + DATABASE_NAME);
            onCreate(db);
        }
    }

}
