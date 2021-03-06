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
import android.widget.EditText;

import com.payone.sdkdemoapp.R;
import com.payone.sdkdemoapp.gui.base.InputActivity;
import com.payone.sdkdemoapp.util.AppUtils;

/**
 * @author hendrik.apel
 * @version $Rev$ $Date$
 *
 */
public class UserDataActivity extends InputActivity
{
    public enum PAYMENT_TYPE
    {
        CREDITCARD, DEBIT
    };

    private PAYMENT_TYPE mPaymentType;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_userdata);

        addHideKeyboardListener((EditText) findViewById(R.id.editTextUserDataCurrency));
    }

    @Override
    protected void onResume()
    {
        super.onResume();

        int paymentTypeInt = getIntent().getIntExtra(getString(R.string.keyIntentUserDataExtraPayment),
                PAYMENT_TYPE.CREDITCARD.ordinal());
        mPaymentType = PAYMENT_TYPE.values()[paymentTypeInt];
    }

    /**
     * button listener
     * @param view - generated by the system
     */
    public void onContinue(View view)
    {
        // gather data given in activity
        String firstName = readInput(R.id.editTextUserDataFirstName);
        String lastName = readInput(R.id.editTextUserDataLastName);
        String country = readInput(R.id.editTextUserDataCountry);
        String currency = readInput(R.id.editTextUserDataCurrency);

        if (!AppUtils.isStringValue(firstName) || !AppUtils.isStringValue(lastName) || !AppUtils.isStringValue(country)
                || !AppUtils.isStringValue(currency))
        {
            // show message
            MessageViewActivity.showMessage(getString(R.string.keyPaymentInputError), this);
        }
        else
        {
            Intent intent = null;
            switch (mPaymentType)
            {
                case CREDITCARD:
                {
                    intent = new Intent(this, PaymentDetailsCreditCardActivity.class);
                    break;
                }
                case DEBIT:
                {
                    intent = new Intent(this, PaymentDetailsDebitActivity.class);
                    break;
                }
                default:
                {
                    break;
                }
            }

            if (intent == null)
            {
                MessageViewActivity.showMessage(
                        String.format("Error starting next activity: unknown PAYMENT_TYPE (%s)",
                                mPaymentType.toString()), this);
            }
            else
            {
                startActivity(intent);
            }
        }
    }

    /**
     * Store the given input in special preferences.
     */
    @Override
    protected void saveInput()
    {
        Editor editor = mData.edit();
        editor.putString(getString(R.string.idPreferencesInputUserDataFirstName),
                readInput(R.id.editTextUserDataFirstName));
        editor.putString(getString(R.string.idPreferencesInputUserDataLastName),
                readInput(R.id.editTextUserDataLastName));
        editor.putString(getString(R.string.idPreferencesInputUserDataCountry), readInput(R.id.editTextUserDataCountry));
        editor.putString(getString(R.string.idPreferencesInputUserDataCurrency),
                readInput(R.id.editTextUserDataCurrency));
        editor.commit();
    }

    /**
     * Restore the previous input from special preferences.
     */
    @Override
    protected void restoreInput()
    {
        setInput(R.id.editTextUserDataFirstName,
                mData.getString(getString(R.string.idPreferencesInputUserDataFirstName), ""));
        setInput(R.id.editTextUserDataLastName,
                mData.getString(getString(R.string.idPreferencesInputUserDataLastName), ""));
        setInput(R.id.editTextUserDataCountry,
                mData.getString(getString(R.string.idPreferencesInputUserDataCountry), ""));
        setInput(R.id.editTextUserDataCurrency,
                mData.getString(getString(R.string.idPreferencesInputUserDataCurrency), ""));
    }

    /**
     * Reset all input fields.
     */
    @Override
    public void clearInputFields()
    {
        ((EditText) findViewById(R.id.editTextUserDataFirstName)).setText("");
        ((EditText) findViewById(R.id.editTextUserDataLastName)).setText("");
        ((EditText) findViewById(R.id.editTextUserDataCountry)).setText("");
        ((EditText) findViewById(R.id.editTextUserDataCurrency)).setText("");
    }
}
