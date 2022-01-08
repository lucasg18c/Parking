package com.slavik.parking.util;

import android.text.Editable;
import android.text.TextWatcher;

public abstract class TextWatcherLite implements TextWatcher {
    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        onTextChanged(charSequence);
    }

    protected abstract void onTextChanged(CharSequence nuevoTexto);

    @Override
    public void afterTextChanged(Editable editable) {

    }
}
