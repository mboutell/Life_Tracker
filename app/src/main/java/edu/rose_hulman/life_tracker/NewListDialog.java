package edu.rose_hulman.life_tracker;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

/**
 * Created by Billy York on 7/19/2015.
 */
public class NewListDialog extends DialogFragment{

    private String name;
    private EditText editText;
    private List list;
    private View view;
    static String KEY_LIST = "KEY_LIST";

    public Dialog onCreateDialog(Bundle b){
        AlertDialog.Builder newListBuilder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        view = inflater.inflate(R.layout.dialog_new_list, null);
        newListBuilder.setView(view);
        editText = (EditText)view.findViewById(R.id.new_list_name);
        newListBuilder.setNegativeButton(R.string.list_dialog_cancel_button, new ClickListener());
        newListBuilder.setPositiveButton(R.string.list_dialog_create_button, new ClickListener());
        return newListBuilder.create();
    }

    private class ClickListener implements DialogInterface.OnClickListener{

        @Override
        public void onClick(DialogInterface dialog, int which) {
            if(which == DialogInterface.BUTTON_NEGATIVE){
              dismiss();
            } else if (which == DialogInterface.BUTTON_POSITIVE){
                name = editText.getText().toString();
                dismiss();
                ((MainActivity)getActivity()).addList(new List(name));
                Intent intent = new Intent(getActivity().getBaseContext(), ListEdit.class);
                intent.putExtra(KEY_LIST, name);
                startActivity(intent);
            }
        }
    }
}
