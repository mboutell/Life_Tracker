package edu.rose_hulman.life_tracker;

import android.app.DialogFragment;
import android.app.ListActivity;
import android.database.Cursor;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SimpleCursorAdapter;

import java.util.HashMap;


public class ListEdit extends ListActivity{

    private MakeItemAdapter mMakeItemAdapter;
    private SimpleCursorAdapter mCursorAdapter;
    public static final long NO_ID_SELECTED = -1;
    private long mSelectedId = NO_ID_SELECTED;
    private String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_edit);

        mMakeItemAdapter = new MakeItemAdapter(this);
        mMakeItemAdapter.open();

        Cursor cursor = mMakeItemAdapter.getItemsCursor();
        String[] fromColumns = new String[] {MakeItemAdapter.KEY_NAME, MakeItemAdapter.KEY_DESCRIPTION, MakeItemAdapter.KEY_PRICE, MakeItemAdapter.KEY_QUANTITY};
        int[] toTextViews = new int[] {R.id.item_name_text_view, R.id.item_description_text_view, R.id.item_price_text_view, R.id.item_quantity_text_view};
        mCursorAdapter = new SimpleCursorAdapter(this, R.layout.item_row, cursor, fromColumns, toTextViews, 0);
        setListAdapter(mCursorAdapter);

        registerForContextMenu(getListView());
    }

    private void addItem(Item item){
        mMakeItemAdapter.addItem(item);
        Cursor cursor = mMakeItemAdapter.getItemsCursor();
        mCursorAdapter.changeCursor(cursor);
    }

    private Item getItem(long id){
        return mMakeItemAdapter.getItem(id);
    }

    private void editItem(Item item){
        item.setId((int) mSelectedId);
        mMakeItemAdapter.updateItem(item);
        Cursor cursor = mMakeItemAdapter.getItemsCursor();
        mCursorAdapter.changeCursor(cursor);
    }

    private void removeItem(long id){
        mMakeItemAdapter.removeItem(id);
        Cursor cursor = mMakeItemAdapter.getItemsCursor();
        mCursorAdapter.changeCursor(cursor);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_list_edit, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        DialogFragment df;

        switch (id){
            case R.id.edit:
                df = new ChooseAttribtuteDialog();
                df.show(getFragmentManager(), null);
                break;
            case R.id.add_item:
                df = new NewItemDialog();
                df.show(getFragmentManager(), null);
                break;
            case R.id.delete:
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    public void updateView(){

    }
}
