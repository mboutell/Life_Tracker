package edu.rose_hulman.life_tracker;

import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

/**
 * Created by Billy York on 7/19/2015.
 */
public class NewItemDialog extends DialogFragment{
    private HashSet<String> activeAttributes = new HashSet<String>();
    private HashMap<Attribute.AttributeType, String> dataMap = new HashMap<>();

    public Dialog onCreateDialog(Bundle b){
        AlertDialog.Builder newItemBuilder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        newItemBuilder.setView(inflater.inflate(R.layout.dialog_new_item, null));
        ListView listView = (ListView) inflater.inflate(R.layout.dialog_new_item, null).findViewById(R.id.new_item_list_view);
        ArrayList<String> attributeList = new ArrayList<>();
        activeAttributes.add(new Attribute(Attribute.AttributeType.DESCRIPTION).toString());
        attributeList.addAll(activeAttributes);
        ArrayAdapter<String> listAdapter = new ArrayAdapter<String>(getActivity(), R.layout.basic_row, attributeList);
        listView.setAdapter(listAdapter);
        newItemBuilder.setNegativeButton(R.string.item_dialog_cancel_button, new ClickListener());
        newItemBuilder.setPositiveButton(R.string.item_dialog_save_button, new ClickListener());

        return newItemBuilder.create();
    }

    private class ClickListener implements DialogInterface.OnClickListener{

        @Override
        public void onClick(DialogInterface dialog, int which) {
            if(which == DialogInterface.BUTTON_NEGATIVE){
                dismiss();
            } else if (which == DialogInterface.BUTTON_POSITIVE){
                String data = "";
                //Item newItem = new Item(data);
            }
        }
    }
}
