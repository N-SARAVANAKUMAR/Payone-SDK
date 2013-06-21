/*
 * $Id$
 * 
 * Copyright 2012 Exozet Games GmbH
 */
/**
 * 
 */
package com.payone.sdkdemoapp.gui;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import com.payone.sdkdemoapp.R;
import com.payone.sdkdemoapp.gui.base.CenteredTitleActivity;
import com.payone.sdkdemoapp.util.AppUtils;

/**
 * @author hendrik.apel
 * @version $Rev$ $Date$
 *
 */
public class PasswordActivity extends CenteredTitleActivity
{
    private enum MODE
    {
        ENTER_PASSWORD, CREATE_PASSWORD
    };

    private MODE                            mMode;

    //    private TextView mTextView1;
    //    private TextView mTextView2;
    private EditText                        mEditTextPassword1;
    private EditText                        mEditTextPassword2;

    private TextView.OnEditorActionListener mEditTextListener;

    private String                          mNextActivityClassName;
    private boolean                         mIsResetRequest;

    /* (non-Javadoc)
     * @see android.app.Activity#onCreate(android.os.Bundle)
     */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_password);

        // store references to child views
        //        mTextView1 = (TextView) this.findViewById(R.id.textViewPassword1);
        //        mTextView2 = (TextView) this.findViewById(R.id.textViewPassword2);
        mEditTextPassword1 = (EditText) this.findViewById(R.id.editTextPassword1);
        mEditTextPassword2 = (EditText) this.findViewById(R.id.editTextPassword2);

        // create textfield enter listener
        mEditTextListener = new TextView.OnEditorActionListener()
        {
            @Override
            public boolean onEditorAction(TextView textView, int actionId, KeyEvent event)
            {
                onContinue(null);
                return true;
            }
        };
    }

    @Override
    protected void onResume()
    {
        super.onResume();

        // store next activity given in the intent
        mNextActivityClassName = getIntent().getStringExtra(getString(R.string.keyIntentPasswordExtraNextActivity));
        // store if password is to be reset
        mIsResetRequest = getIntent().getBooleanExtra(getString(R.string.keyIntentPasswordExtraReset), false);

        // set mode to password creation if no password exists or if reset is requested
        if (AppUtils.getStringSetting(R.string.idPreferencePassword, this) == null || mIsResetRequest)
        {
            mMode = MODE.CREATE_PASSWORD;
        }
        else
        {
            mMode = MODE.ENTER_PASSWORD;
        }

        // if mode is password creation, show both textfields and setup for password creation
        if (mMode == MODE.CREATE_PASSWORD)
        {
            mEditTextPassword1.setHint(R.string.keyEditTextPasswordSetPassword);
            mEditTextPassword1.setImeOptions(EditorInfo.IME_ACTION_NEXT);
            mEditTextPassword1.setOnEditorActionListener(null);
            mEditTextPassword2.setVisibility(View.VISIBLE);
            mEditTextPassword2.setHint(R.string.keyEditTextPasswordConfirmPassword);
            mEditTextPassword2.setOnEditorActionListener(mEditTextListener);
        }
        // if mode is password input, show just one textfield
        else
        {
            mEditTextPassword1.setHint(R.string.keyEditTextPasswordEnterPassword);
            mEditTextPassword1.setImeOptions(EditorInfo.IME_ACTION_UNSPECIFIED);
            mEditTextPassword1.setOnEditorActionListener(mEditTextListener);
            mEditTextPassword2.setVisibility(View.INVISIBLE);
        }
    }

    // button click and textfield enter handler
    public void onContinue(View view)
    {
        if (mMode == MODE.ENTER_PASSWORD)
        {
            onPasswordEnter();
        }
        else
        {
            onPasswordSet();
        }
    }

    private void onPasswordSet()
    {
        // check input
        String text1 = mEditTextPassword1.getText().toString();
        String text2 = mEditTextPassword2.getText().toString();
        if (text1.equals(text2))
        {
            // save to preferences and start next activity
            // of course you wouldn't store a password like this in a real world app
            AppUtils.setStringSetting(R.string.idPreferencePassword, text1, this);
            gotoNextActivity();
        }
        else
        {
            // show error message
            MessageViewActivity.showMessage(getString(R.string.keyPasswordConfirmError), this);
        }
    }

    private void onPasswordEnter()
    {
        // check input
        String text1 = mEditTextPassword1.getText().toString();
        String password = AppUtils.getStringSetting(R.string.idPreferencePassword, this);
        if (text1.equals(password))
        {
            gotoNextActivity();
        }
        else
        {
            // error show message
            MessageViewActivity.showMessage(getString(R.string.keyPasswordEnterError), this);
        }
    }

    private void gotoNextActivity()
    {
        if (mNextActivityClassName != null)
        {
            try
            {
                Intent intent = new Intent(this, Class.forName(mNextActivityClassName));
                startActivity(intent);
            }
            catch (ClassNotFoundException e)
            {
                MessageViewActivity.showMessage("Error starting activity: ".concat(e.getMessage()), this);
            }
        }

        finish();
    }
}
