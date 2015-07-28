package edu.rose_hulman.life_tracker;

import android.app.DialogFragment;
import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import java.util.Currency;
import java.util.HashMap;
import java.util.HashSet;


public class MainActivity extends ListActivity {

    private MakeListAdapter mMakeListAdapter;
    private SimpleCursorAdapter mCursorAdapter;

    private static final String KEY_LIST_NAME = "KEY_LIST_NAME";
    private static final long NO_ID_SELECTED = -1;

    private long mSelectedId = NO_ID_SELECTED;

    private HashSet<List> listOfLists;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listOfLists = new HashSet<>();

        mMakeListAdapter = new MakeListAdapter(this);
        mMakeListAdapter.open();

        Cursor cursor = mMakeListAdapter.getListsCursor();
        String[] fromColumns = new String[] {MakeListAdapter.KEY_LIST};
        int[] toTextViews = new int[] {R.id.list_name_text_view};
        mCursorAdapter = new SimpleCursorAdapter(this, R.layout.list_row, cursor, fromColumns, toTextViews, 0);
        setListAdapter(mCursorAdapter);

       registerForContextMenu(getListView());
    }

    @Override
    protected void onListItemClick(ListView listView, View selectedView, int position, long id){
        super.onListItemClick(listView, selectedView, position, id);
        mSelectedId = id;
        Intent listIntent = new Intent(this, ListEdit.class);
        listIntent.putExtra(KEY_LIST_NAME, mMakeListAdapter.getList(id).getName());
        this.startActivity(listIntent);
    }

    public void addList(List l){
        mMakeListAdapter.addList(l);
        listOfLists.add(l);
        Cursor cursor = mMakeListAdapter.getListsCursor();
        mCursorAdapter.changeCursor(cursor);
    }

    public List getList(long id){
        return mMakeListAdapter.getList(id);
    }

    public void editList(List l){
        l.setId((int) mSelectedId);
        mMakeListAdapter.updateList(l);
        Cursor cursor = mMakeListAdapter.getListsCursor();
        mCursorAdapter.changeCursor(cursor);
    }

    public void removeList(long id){
        listOfLists.remove(mMakeListAdapter.getList(id));
        mMakeListAdapter.removeList(id);
        Cursor cursor = mMakeListAdapter.getListsCursor();
        mCursorAdapter.changeCursor(cursor);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        switch (id){
            case R.id.settings:
                break;
            case R.id.new_list:
                DialogFragment df = new NewListDialog();
                df.show(getFragmentManager(), null);
                break;
            case R.id.search:
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View view, ContextMenu.ContextMenuInfo menuInfo){
        super.onCreateContextMenu(menu, view, menuInfo);
        menu.add(0, R.id.settings, 0, getResources().getString(R.string.action_settings));
        menu.add(0, R.id.new_list, 1, getResources().getString(R.string.action_new_list));
        menu.add(0, R.id.search, 2, getResources().getString(R.string.action_search));
        MenuInflater inflater = getMenuInflater();
        if(view == getListView()){
            inflater.inflate(R.menu.menu_main, menu);
        }
    }

    @Override
    public boolean onContextItemSelected(MenuItem item){
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        switch (item.getItemId()){
            case R.id.settings:
                break;
            case R.id.new_list:
                DialogFragment df = new NewListDialog();
                df.show(getFragmentManager(), null);
                break;
            case R.id.search:
                break;
        }
        return super.onContextItemSelected(item);
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
    }
}
