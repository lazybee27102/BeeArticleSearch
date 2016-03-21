package com.coderschool.beeiscoding.beearticlesearch.FilterResult;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;

import com.coderschool.beeiscoding.beearticlesearch.R;

import java.util.ArrayList;

/**
 * Created by beeiscoding on 21/03/2016.
 */
public class SectionChooseFragment extends DialogFragment {
    ArrayList<String> arrayListSections = new ArrayList<>();

    public SectionChooseFragment() {
    }

    public static SectionChooseFragment newInstance() {
        SectionChooseFragment frag = new SectionChooseFragment();
        return frag;
    }

    @Override

    public Dialog onCreateDialog(final Bundle savedInstanceState) {

        final String[] sections = getResources().getStringArray(R.array.sectionArray);
        final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());

        alertDialogBuilder.setTitle("Choose section").setMultiChoiceItems(R.array.sectionArray, null, new DialogInterface.OnMultiChoiceClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                if (isChecked) {
                    arrayListSections.add(sections[which]);
                } else {
                    if (arrayListSections.contains(sections[which])) {
                        arrayListSections.remove(sections[which]);
                    }
                }
            }
        });
        alertDialogBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String stringBuilder = "";
                if(arrayListSections.size()!=0)
                {
                    for (int i = 0;i<arrayListSections.size();i++)
                    {
                        if (i==0)
                            stringBuilder+=(arrayListSections.get(i));
                        else
                            stringBuilder+=(" " + arrayListSections.get(i));
                    }
                }

                getResponseFromDialog delegate = (getResponseFromDialog) getActivity();
                delegate.handleResult(stringBuilder.toString());
            }
        });

        alertDialogBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                dialog.dismiss();

            }
        });
        return alertDialogBuilder.create();

    }

    public interface getResponseFromDialog {
        void handleResult(String s);
    }
}
