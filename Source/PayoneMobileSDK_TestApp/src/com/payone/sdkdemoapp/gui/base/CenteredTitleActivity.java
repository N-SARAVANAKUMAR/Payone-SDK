/*
 * $Id$
 * 
 * Copyright 2012 Exozet Games GmbH
 */
/**
 * 
 */
package com.payone.sdkdemoapp.gui.base;

import android.app.Activity;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.LinearLayout;

/**
 * @author hendrik.apel
 * @version $Rev$ $Date$
 *
 */
public class CenteredTitleActivity extends Activity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        // center the title text
        ViewGroup decorView = (ViewGroup) getWindow().getDecorView();
        LinearLayout root = (LinearLayout) decorView.getChildAt(0);
        //        FrameLayout titleContainer = (FrameLayout) root.getChildAt(0);
        //        TextView title = (TextView) titleContainer.getChildAt(0);
        //        title.setGravity(Gravity.CENTER);
    }

}
