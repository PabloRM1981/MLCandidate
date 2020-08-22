package com.mercadolibre.mlcandidate.utilidades;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.mercadolibre.mlcandidate.R;

public class Utilidades {

    public static final ProgressDialog showProgress(Context context) {
        String message = context.getResources().getString(R.string.mensajeEspere);
        return showProgress(context, message);
    }

    public static final ProgressDialog showProgress(Context context, String message) {
        String title = context.getResources().getString(R.string.tituloProcesando);
        return showProgress(context, title, message);
    }

    public static final ProgressDialog showProgress(Context context, String title, String message) {
        return ProgressDialog.show(context, title, message, true, false);
    }



}
