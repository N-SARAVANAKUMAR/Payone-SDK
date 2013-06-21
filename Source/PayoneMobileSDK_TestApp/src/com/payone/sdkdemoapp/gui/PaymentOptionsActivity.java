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
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.ToggleButton;

import com.payone.sdkdemoapp.R;
import com.payone.sdkdemoapp.gui.base.InputActivity;

/**
 * @author hendrik.apel
 * @version $Rev$ $Date$
 *
 */
public class PaymentOptionsActivity extends InputActivity
{
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_paymentoptions);
    }

    public void onPaymentDetails(View view)
    {
        RadioGroup rg = (RadioGroup) findViewById(R.id.paymentOption);
        Intent intent = new Intent(this, UserDataActivity.class);

        switch (rg.getCheckedRadioButtonId())
        {
            case R.id.paymentOptionCreditCard:
            {
                intent.putExtra(getString(R.string.keyIntentUserDataExtraPayment),
                        UserDataActivity.PAYMENT_TYPE.CREDITCARD.ordinal());
                break;
            }
            case R.id.paymentOptionDebit:
            {
                intent.putExtra(getString(R.string.keyIntentUserDataExtraPayment),
                        UserDataActivity.PAYMENT_TYPE.DEBIT.ordinal());
                break;
            }
            default:
            {
                intent = null;
                MessageViewActivity.showMessage(getString(R.string.keyMessagePaymentSelection), this);
                break;
            }
        }

        if (intent != null)
        {
            startActivity(intent);
        }
    }

    /**
     * Store the given input in special preferences.
     */
    @Override
    protected void saveInput()
    {
        Editor editor = mData.edit();
        ToggleButton toggleButton = (ToggleButton) findViewById(R.id.paymentRequestMode);
        editor.putBoolean(getString(R.string.idPreferencesInputRequestMode), toggleButton.isChecked()).commit();
    }

    /**
     * Restore the previous input from special preferences.
     */
    @Override
    protected void restoreInput()
    {
        ToggleButton toggleButton = (ToggleButton) findViewById(R.id.paymentRequestMode);
        toggleButton.setChecked(mData.getBoolean(getString(R.string.idPreferencesInputRequestMode), false));
    }

    /**
     * Reset all input fields.
     */
    @Override
    public void clearInputFields()
    {
        ToggleButton toggleButton = (ToggleButton) findViewById(R.id.paymentRequestMode);
        toggleButton.setChecked(false);
    }
}
