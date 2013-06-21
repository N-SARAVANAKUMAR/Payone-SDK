/*
 * $Id$
 * 
 * Copyright 2012 Exozet Games GmbH
 */
/**
 * 
 */
package com.payone.sdkdemoapp.gui;

import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.payone.sdkdemoapp.R;

/**
 * @author hendrik.apel
 * @version $Rev$ $Date$
 *
 */
public class SettingsActivity extends PreferenceActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        // center the title text
        ViewGroup decorView = (ViewGroup) getWindow().getDecorView();
        LinearLayout root = (LinearLayout) decorView.getChildAt(0);
        FrameLayout titleContainer = (FrameLayout) root.getChildAt(0);
        TextView title = (TextView) titleContainer.getChildAt(0);
        title.setGravity(Gravity.CENTER);

        // Load the preferences from an XML resource
        addPreferencesFromResource(R.xml.preferences);
    }
}
