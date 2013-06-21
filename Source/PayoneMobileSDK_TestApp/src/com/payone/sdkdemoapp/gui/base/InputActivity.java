/*
 * $Id$
 * 
 * Copyright 2012 Exozet Games GmbH
 */
/**
 * 
 */
package com.payone.sdkdemoapp.gui.base;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import com.payone.sdkdemoapp.R;

/**
 * @author hendrik.apel
 * @version $Rev$ $Date$
 *
 */
public class InputActivity extends SettingsMenuActivity
{
    protected SharedPreferences mData;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        mData = getSharedPreferences(getString(R.string.idPreferencesInputId), MODE_PRIVATE);
    }

    @Override
    protected void onPause()
    {
        saveInput();
        super.onPause();
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        restoreInput();
    }

    /**
     * Returns the SharedPreferences used to store the input.
     * @return
     */
    public SharedPreferences getData()
    {
        return mData;
    }

    /**
     * Called whenever the activity gets paused by the system.
     * Override in subclass with concrete logic.
     */
    protected void saveInput()
    {
        // override in subclass
    }

    /**
     * Called whenever the activity gets resumed by the system.
     * Override in subclass with concrete logic.
     */
    protected void restoreInput()
    {
        // override in subclass
    }

    /**
     * Adds an OnEditorActionListener that will hide the soft keyboard.
     * @param editText
     */
    protected void addHideKeyboardListener(EditText editText)
    {
        // create textfield enter listener
        TextView.OnEditorActionListener textListener = new TextView.OnEditorActionListener()
        {
            @Override
            public boolean onEditorAction(TextView textView, int actionId, KeyEvent event)
            {
                // hide keyboard, check the key event to avoid double execution on down and on up 
                if (event.getAction() == KeyEvent.ACTION_DOWN)
                {
                    InputMethodManager inputManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),
                            InputMethodManager.HIDE_NOT_ALWAYS);
                    return true;
                }

                return false;
            }
        };

        editText.setOnEditorActionListener(textListener);
    }

    /**
     * Returns the content of the EditText with the given ID.
     * @param inputId - ID of the EditText
     * @return
     */
    protected String readInput(int inputId)
    {
        EditText textField = (EditText) findViewById(inputId);
        return textField.getText().toString();
    }

    /**
     * Sets the content of the EditText with the given ID.
     * @param inputId - ID of the EditText
     * @param inputValue - the content String
     */
    protected void setInput(int inputId, String inputValue)
    {
        EditText textField = (EditText) findViewById(inputId);
        textField.setText(inputValue);
    }
}
