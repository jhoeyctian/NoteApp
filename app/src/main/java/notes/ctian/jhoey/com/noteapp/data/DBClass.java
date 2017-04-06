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

    public static final String KEY_ROWID = "_id";
    public static final String KEY_TITLE = "notetitle";
    public static final String KEY_CONTENT = "notecontent";
    public static final String KEY_DATE = "notedate";

    private static final String DATABASE_NAME = "notedb";
    private static final String DATABASE_TABLE = "notetable";
    private static final int DATABASE_VERSION = 1;

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

    public List<Note> getData() {

        List<Note> notes = new ArrayList<>();

        String columns[] = new String[]{KEY_ROWID, KEY_TITLE, KEY_CONTENT, KEY_DATE};

        Cursor cursor = noteDatabase.query(DATABASE_TABLE, columns, null , null, null, null, null);

        int iRow = cursor.getColumnIndex(KEY_ROWID);
        int iTitle = cursor.getColumnIndex(KEY_TITLE);
        int iContent = cursor.getColumnIndex(KEY_CONTENT);
        int iDate = cursor.getColumnIndex(KEY_DATE);

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

    public void deleteEntry(long dataId) {
        noteDatabase.delete(DATABASE_TABLE, KEY_ROWID + "=" + dataId, null);
    }

    public long updateEntry(String id, String title, String content) {
        ContentValues cv = new ContentValues();
        cv.put(KEY_ROWID, id);
        cv.put(KEY_TITLE, title);
        cv.put(KEY_CONTENT, content);
        cv.put(KEY_DATE, Calendar.getInstance().getTimeInMillis()+"");
        return noteDatabase.update(DATABASE_TABLE, cv, KEY_ROWID + "=" + id, null);
    }

    public long createEntry(String title, String content) {
        ContentValues cv = new ContentValues();
        cv.put(KEY_TITLE, title);
        cv.put(KEY_CONTENT, content);
        cv.put(KEY_DATE, Calendar.getInstance().getTimeInMillis()+"");
        return noteDatabase.insert(DATABASE_TABLE, null, cv);
    }

    private static class DBHelper extends SQLiteOpenHelper {

        public DBHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL("CREATE TABLE " + DATABASE_TABLE + " ( "
                    + KEY_ROWID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, "
                    + KEY_TITLE + " TEXT NOT NULL, "
                    + KEY_CONTENT + " TEXT NOT NULL, "
                    + KEY_DATE + " TEXT NOT NULL); ");
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + DATABASE_NAME);
            onCreate(db);
        }
    }

}
