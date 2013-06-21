/*
 * $Id$
 * 
 * Copyright 2012 Exozet Games GmbH
 */
package com.payone.sdkdemoapp.gui.base;

import android.content.Intent;
import android.content.SharedPreferences;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.payone.sdkdemoapp.R;
import com.payone.sdkdemoapp.gui.SettingsActivity;

/**
 * @author hendrik.apel
 * @version $Rev$ $Date$
 *
 * base class for all activities that feature access to the Preferences via the menu button
 * contains all the menu handling code
 */
public class SettingsMenuActivity extends CenteredTitleActivity
{
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        // handle menu item selection - the one and only menu item starts the SettingsActivity
        switch (item.getItemId())
        {
            case R.id.menuItemSettings:
            {
                Intent intent = new Intent(this, SettingsActivity.class);
                startActivity(intent);
                return true;
            }
            case R.id.menuItemReset:
            {
                SharedPreferences data = getSharedPreferences(getString(R.string.idPreferencesInputId), MODE_PRIVATE);
                data.edit().clear().commit();
                clearInputFields();
                return true;
            }
            default:
            {
                return super.onOptionsItemSelected(item);
            }
        }
    }

    /**
     * Method is called when the "Reset input" menu is selected. Override in subclasses to handle the reset.
     */
    public void clearInputFields()
    {
        // override in subclass
    }
}
