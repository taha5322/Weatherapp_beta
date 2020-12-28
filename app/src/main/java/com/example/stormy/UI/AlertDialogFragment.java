package com.example.stormy.UI;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.os.Bundle;

import com.example.stormy.R;

// Created by Taha Siddiqui
// 2020-03-26
public class AlertDialogFragment extends DialogFragment {
//used to alert users for errors

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState){
        Context context = getActivity();
        AlertDialog.Builder builder = new AlertDialog.Builder(context);

        //sets the top msg on top of the dialog box
        builder.setTitle(R.string.error_title);

        //sets the error msg
        builder.setMessage(R.string.error_message);

        //null on click listener closes the dialogue
        builder.setPositiveButton("Ok", null);

        //creates the alert dialog box
        return builder.create();

    }
}
