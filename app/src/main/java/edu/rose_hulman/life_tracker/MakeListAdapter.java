package edu.rose_hulman.life_tracker;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Billy York on 7/28/2015.
 */
public class MakeListAdapter {

    private static final String DATABASE_NAME = "lists.db";
    private static final String TABLE_NAME = "lists";
    private static final int DATABASE_VERSION = 1;
    public static final String KEY_ID = "_id";
    public static final String KEY_LIST = "list";
    private SQLiteOpenHelper mOpenHelper;
    private SQLiteDatabase mDatabase;

    private static String DROP_STATEMENT = "DROP TABLE IF EXISTS " + TABLE_NAME;
    private static String CREATE_STATEMENT;
    static {
        StringBuilder sb = new StringBuilder();
        sb.append("CREATE TABLE " + TABLE_NAME + " (");
        sb.append(KEY_ID + " integer primary key autoincrement, ");
        sb.append(KEY_LIST + " text");
        sb.append(")");
        CREATE_STATEMENT = sb.toString();
    }

    public MakeListAdapter(Context context){
        mOpenHelper = new ListDbHelper(context);
    }

    public void open(){
        mDatabase = mOpenHelper.getWritableDatabase();
    }

    public void close(){
        mDatabase.close();
    }

    private ContentValues getContentValueFromList(List list){
        ContentValues row = new ContentValues();
        row.put(KEY_LIST, list.getName());
        return row;
    }

    public long addList(List list){
        ContentValues row = getContentValueFromList(list);
        long rowId = mDatabase.insert(TABLE_NAME, null, row);
        list.setId(rowId);
        return rowId;
    }

    public Cursor getListsCursor(){
        String[] projection = new String[] {KEY_ID, KEY_LIST};
        return mDatabase.query(TABLE_NAME, projection, null, null, null, null, KEY_LIST + " DESC");
    }

    public List getList(long id){
        String[] projectioin = new String[] {KEY_ID, KEY_LIST};
        String selection = KEY_ID + " = " + id;
        Cursor c = mDatabase.query(TABLE_NAME, projectioin, selection, null, null, null, null, null);
        if(c != null && c.moveToFirst()){
            return getListFromCursor(c);
        }
        return null;
    }

    private List getListFromCursor(Cursor c){
        List l = new List();
        l.setId(c.getInt(c.getColumnIndexOrThrow(KEY_ID)));
        l.setName(c.getString(c.getColumnIndexOrThrow(KEY_LIST)));
        return l;
    }

    public void updateList(List list){
        ContentValues row = getContentValueFromList(list);
        String selection = KEY_ID + " = " + list.getId();
        mDatabase.update(TABLE_NAME, row, selection, null);
    }

    public boolean removeList(long id){
        return mDatabase.delete(TABLE_NAME, KEY_ID + " = " + id, null) > 0;
    }

    public boolean removeList(List l){
        return removeList(l.getId());
    }

    private static class ListDbHelper extends SQLiteOpenHelper{

        public ListDbHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(CREATE_STATEMENT);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL(DROP_STATEMENT);
            onCreate(db);
        }
    }
}
