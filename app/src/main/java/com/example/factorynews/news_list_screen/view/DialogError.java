package com.example.factorynews.news_list_screen.view;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;

import com.example.factorynews.R;

public class DialogError extends DialogFragment {

    public DialogError(){}

    public static DialogError newInstance() {
        DialogError dialogError = new DialogError();
        return dialogError;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());
        alertDialogBuilder.setTitle(R.string.error_title);
        alertDialogBuilder.setMessage(R.string.error_message);
        alertDialogBuilder.setPositiveButton(R.string.error_button_text, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        return alertDialogBuilder.create();

    }




}
