package edu.rose_hulman.life_tracker;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

/**
 * Created by Billy York on 7/19/2015.
 */
public class ChooseAttribtuteDialog extends DialogFragment{

    private boolean[] activeAttributes = new boolean[10];
    private static final String KEY_ACTIVE_ATTRIBUTES = "KEY_ACTIVE_ATTRIBUTES";
//    private List mList;

    public static ChooseAttribtuteDialog newInstance(boolean[] activeAttributes){
        ChooseAttribtuteDialog chooseAttribtuteDialog = new ChooseAttribtuteDialog();
        Bundle args = new Bundle();
        chooseAttribtuteDialog.setArguments(args);
        args.putBooleanArray(KEY_ACTIVE_ATTRIBUTES, activeAttributes);
        return chooseAttribtuteDialog;
    }

    public Dialog onCreateDialog(Bundle b){
        activeAttributes = getArguments().getBooleanArray(KEY_ACTIVE_ATTRIBUTES);
        AlertDialog.Builder chooseAttributeBuilder = new AlertDialog.Builder(getActivity());
        chooseAttributeBuilder.setMultiChoiceItems(R.array.attributes_array, activeAttributes, new MultiClickListener());
        chooseAttributeBuilder.setNegativeButton(R.string.attribute_dialog_cancel_button, new ClickListener());
        chooseAttributeBuilder.setPositiveButton(R.string.attribute_dialog_save_button, new ClickListener());
        return chooseAttributeBuilder.create();
    }

    private class MultiClickListener implements DialogInterface.OnMultiChoiceClickListener{

        @Override
        public void onClick(DialogInterface dialog, int which, boolean isChecked) {
            switch (which){
                case 0://name
                    activeAttributes[0] = isChecked;
                    break;
                case 1://description
                    activeAttributes[1] = isChecked;
                    break;
                case 2://price
                    activeAttributes[2] = isChecked;
                    break;
                case 3://quantity
                    activeAttributes[3] = isChecked;
                    break;
                case 4://priority
                    activeAttributes[4] = isChecked;
                    break;
                case 5://voice
                    activeAttributes[5] = isChecked;
                    break;
                case 6://image
                    activeAttributes[6] = isChecked;
                    break;
                case 7://reminder
                    activeAttributes[7] = isChecked;
                    break;
                case 8://location
                    activeAttributes[8] = isChecked;
                    break;
                case 9://web-source
                    activeAttributes[9] = isChecked;
                    break;
                default:
                    dismiss();
                    break;
            }
        }
    }

    private class ClickListener implements DialogInterface.OnClickListener {

        @Override
        public void onClick(DialogInterface dialog, int which) {
            if(which == DialogInterface.BUTTON_NEGATIVE){
                dismiss();
            }
            else if (which == DialogInterface.BUTTON_POSITIVE){
//                mList.saveActiveAttributes(activeAttributes);
                ((ListEdit)getActivity()).getList().saveActiveAttributes(activeAttributes);
                dismiss();
            }
        }
    }
}
