/*
 * $Id$
 * 
 * Copyright 2012 Exozet Games GmbH
 */
/**
 * 
 */
package com.payone.sdkdemoapp.gui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.payone.sdkdemoapp.R;
import com.payone.sdkdemoapp.gui.base.CenteredTitleActivity;

/**
 * @author hendrik.apel
 * @version $Rev$ $Date$
 *
 */
public class MessageViewActivity extends CenteredTitleActivity
{
    private static final String PARAMETER_TEXT = "MessageViewActivity.PARAMETER_TEXT";

    public static void showMessage(String message, Context context)
    {
        Intent intent = new Intent(context, MessageViewActivity.class);
        intent.putExtra(PARAMETER_TEXT, message);
        context.startActivity(intent);
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_messageview);

        TextView textView = (TextView) findViewById(R.id.messageTextView);
        textView.setText(getIntent().getStringExtra(PARAMETER_TEXT));
    }

    public void onButtonOk(View view)
    {
        finish();
    }
}
