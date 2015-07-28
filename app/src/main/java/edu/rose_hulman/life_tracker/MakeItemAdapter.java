package edu.rose_hulman.life_tracker;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Billy York on 7/28/2015.
 */
public class MakeItemAdapter {

    private static final String DATABASE_NAME = "items.db";
    private static final String TABLE_NAME = "items";
    private static final int DATABASE_VERSION = 1;
    public static final String KEY_ID = "_id";
    public static final String KEY_NAME = "name";
    public static final String KEY_DESCRIPTION = "description";
    public static final String KEY_PRICE = "price";
    public static final String KEY_QUANTITY = "quantity";
    private SQLiteOpenHelper mOpenHelper;
    private SQLiteDatabase mDatabase;

    private static String DROP_STATEMENT = "DROP TABLE IF EXISTS " + TABLE_NAME;
    private static String CREATE_STATEMENT;
    static {
        StringBuilder sb = new StringBuilder();
        sb.append("CREATE TABLE " + TABLE_NAME + " (");
        sb.append(KEY_ID + " integer primary key autoincrement, ");
        sb.append(KEY_NAME + " text, ");
        sb.append(KEY_DESCRIPTION + " text, ");
        sb.append(KEY_PRICE + " float, ");
        sb.append(KEY_QUANTITY + " integer");
        sb.append(")");
        CREATE_STATEMENT = sb.toString();
    }


    public MakeItemAdapter(Context context){
        mOpenHelper = new ItemDbHelper(context);
    }

    private ContentValues getContentValuesFromItem(Item item){
        ContentValues row = new ContentValues();
        row.put(KEY_NAME, item.getName());
        row.put(KEY_DESCRIPTION, item.getDescription());
        row.put(KEY_PRICE, item.getPrice());
        row.put(KEY_QUANTITY, item.getQuantity());
        return row;
    }

    public long addItem(Item item){
        ContentValues row = getContentValuesFromItem(item);
        long rowId = mDatabase.insert(TABLE_NAME, null, row);
        item.setId(rowId);
        return rowId;
    }

    public Cursor getItemsCursor(){
        String[] projection = new String[] {KEY_ID, KEY_NAME, KEY_DESCRIPTION, KEY_PRICE, KEY_QUANTITY};
        return mDatabase.query(TABLE_NAME, projection, null, null, null, null, KEY_NAME + " DESC");
    }

    public Item getItem(long id) {
        String[] projection = new String[] {KEY_ID, KEY_NAME, KEY_DESCRIPTION, KEY_PRICE, KEY_QUANTITY};
        String selection = KEY_ID + " = " + id;
        Cursor c = mDatabase.query(TABLE_NAME, projection, selection, null, null, null, null, null);
        if (c != null && c.moveToFirst()){
            return getItemFromCursor(c);
        }

        return null;
    }

    private Item getItemFromCursor(Cursor c){
        Item i = new Item();
        i.setId(c.getInt(c.getColumnIndexOrThrow(KEY_ID)));
        i.setName(c.getString(c.getColumnIndexOrThrow(KEY_NAME)));
        i.setDescription(c.getString(c.getColumnIndexOrThrow(KEY_DESCRIPTION)));
        i.setPrice(c.getFloat(c.getColumnIndexOrThrow(KEY_PRICE)));
        i.setQuantity(c.getInt(c.getColumnIndexOrThrow(KEY_QUANTITY)));
        return i;
    }

    public void updateItem(Item item){
        ContentValues row = getContentValuesFromItem(item);
        String selection = KEY_ID + " = " + item.getId();
        mDatabase.update(TABLE_NAME, row, selection, null);
    }

    public boolean removeItem(long id) {
        return mDatabase.delete(TABLE_NAME, KEY_ID + " = " + id, null) > 0;
    }
    public boolean removeItem(Item item){
        return removeItem(item.getId());
    }

    public void open(){
        mDatabase = mOpenHelper.getWritableDatabase();
    }

    public void close(){
        mDatabase.close();
    }

    private class ItemDbHelper extends SQLiteOpenHelper{

        public ItemDbHelper(Context context){
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
