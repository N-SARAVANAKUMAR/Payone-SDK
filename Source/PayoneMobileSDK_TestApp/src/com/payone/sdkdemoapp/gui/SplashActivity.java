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
import android.os.Handler;
import android.preference.PreferenceManager;

import com.payone.sdkdemoapp.R;
import com.payone.sdkdemoapp.gui.base.CenteredTitleActivity;

/**
 * @author hendrik.apel
 * @version $Rev$ $Date$
 *
 */
public class SplashActivity extends CenteredTitleActivity
{
    /** Display duration of the splash screen in milliseconds. */
    public static final long MIN_SPLASH_TIME    = 3000;
    /** Flag to fasten up the splash screen while developing (waiting sucks) */
    public static boolean    DISABLE_SPLASH     = false;

    /** Entrypoint to the task-thread's message queue */
    protected Handler        mHandler;

    /**
     * Indicates whether the main activity should still be launched after
     * onPostExecute() is called when the background-task has finished - or if
     * the user pressed back or home or something else happened in the meantime
     */
    protected boolean        mStillLaunchIntent = true;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.layout_splash);

        PreferenceManager.setDefaultValues(this, R.xml.preferences, false);

        // Start a new thread that will prepare all the data
        // and launch a new intent when finished
        mHandler = new Handler();

        mHandler.postDelayed(new Runnable()
        {
            @Override
            public void run()
            {
                if (mStillLaunchIntent)
                {
                    // Launch the main activity, pop the splash activity off
                    // the stack,
                    // so it's not again displayed by navigating back
                    Intent intent = new Intent(SplashActivity.this, PasswordActivity.class);

                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    intent.putExtra(getString(R.string.keyIntentPasswordExtraNextActivity),
                            PaymentOptionsActivity.class.getName());

                    startActivity(intent);

                    // Finish this activity
                    finish();
                }
            }
        }, MIN_SPLASH_TIME);
    }

    @Override
    protected void onPause()
    {
        super.onPause();
        if (!mStillLaunchIntent)
        {
            stopIntentLauch();
        }
    }

    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
        stopIntentLauch();
    }

    /**
     * Stops the queued launch of an intent.
     */
    private void stopIntentLauch()
    {
        mStillLaunchIntent = false;

        finish();
    }
}
