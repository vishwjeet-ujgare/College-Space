package com.example.common.dialog;

import android.content.Context;

import androidx.appcompat.app.AlertDialog;

public class CommonAlertDialog {
    public void showMsgDialog(Context context, String msg, String title) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(title).setCancelable(false).setMessage(msg);
        builder.setPositiveButton("OK", null);
        AlertDialog dialog = builder.create();
        dialog.show();


    }



    public void showErrorDialog(Context context,String errorMessage,String title) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(title).setCancelable(false).setMessage(errorMessage);
        builder.setPositiveButton("OK", null);
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}
