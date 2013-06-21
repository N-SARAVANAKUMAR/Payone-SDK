/*
 * $Id$
 * 
 * Copyright 2012 Exozet Games GmbH
 */
/**
 * 
 */
package com.payone.sdkdemoapp.gui;

import java.util.Map.Entry;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.payone.lib.builder.ParameterCollection;
import com.payone.lib.parameter.PayoneParameters;
import com.payone.lib.utils.PayoneRequestFactory;
import com.payone.lib.utils.RequestListener;
import com.payone.sdkdemoapp.R;
import com.payone.sdkdemoapp.gui.base.CenteredTitleActivity;
import com.payone.sdkdemoapp.model.PayoneRequestData;

/**
 * @author hendrik.apel
 * @version $Rev$ $Date$
 *
 */
public class PaymentRequestActivity extends CenteredTitleActivity implements RequestListener
{
    public static PayoneRequestData REQUEST_DATA;

    private TextView                mTextResultOutput;
    private ProgressBar             mProgressBar;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_paymentrequest);

        mTextResultOutput = (TextView) this.findViewById(R.id.textResultOutput);
        mProgressBar = (ProgressBar) this.findViewById(R.id.progressBarResult);

        sendRequest();
    }

    private void sendRequest()
    {
        if (REQUEST_DATA == null)
        {
            mTextResultOutput.setText(R.string.keyMessageRequestDataMissing);
        }
        else
        {
            switch (REQUEST_DATA.getRequestType())
            {
                case CREDITCARD_AUTHORIZATION:
                    sendCreditcardRequest();
                    break;
                case DEBIT_AUTHORIZATION:
                    sendAuthorizationRequest();
                    break;
                case DEBIT_PREAUTHORIZATION:
                    sendPreAuthorizationRequest();
                    break;
            }
        }
    }

    private void sendPreAuthorizationRequest()
    {
        mTextResultOutput.setText(R.string.keyMessageRequestStartDebitPreAuth);
        try
        {
            PayoneRequestFactory.createPreAuthorizationRequest(this, REQUEST_DATA.getM5Key(),
                    REQUEST_DATA.getRequestParameters());
            mProgressBar.setVisibility(View.VISIBLE);
        }
        catch (Exception e)
        {
            addOutput("Error: ".concat(e.getMessage()));
        }
    }

    private void sendAuthorizationRequest()
    {
        mTextResultOutput.setText(R.string.keyMessageRequestStartDebitAuth);
        try
        {
            PayoneRequestFactory.createAuthorizationRequest(this, REQUEST_DATA.getM5Key(),
                    REQUEST_DATA.getRequestParameters());
            mProgressBar.setVisibility(View.VISIBLE);
        }
        catch (Exception e)
        {
            addOutput("Error: ".concat(e.getMessage()));
        }
    }

    private void sendCreditcardRequest()
    {
        mTextResultOutput.setText(R.string.keyMessageRequestStartCreditCard);
        try
        {
            PayoneRequestFactory.createCreditCardCheckRequest(this, REQUEST_DATA.getM5Key(),
                    REQUEST_DATA.getRequestParameters());
            mProgressBar.setVisibility(View.VISIBLE);
        }
        catch (Exception e)
        {
            addOutput("Error: ".concat(e.getMessage()));
        }
    }

    /* (non-Javadoc)
     * @see com.payone.lib.utils.RequestListener#request(com.payone.lib.builder.ParameterCollection)
     */
    @Override
    public void onRequestResult(ParameterCollection result)
    {
        mProgressBar.setVisibility(View.GONE);
        addOutput(getString(R.string.keyMessageRequestComplete));

        String output;

        // Check status
        if (result.getCollection().containsKey(PayoneParameters.STATUS))
        {
            int color;
            String status = result.getCollection().get(PayoneParameters.STATUS);

            if (status.equals(PayoneParameters.ResponseErrorCodes.APPROVED)
                    || status.equals(PayoneParameters.ResponseErrorCodes.VALID)
                    || status.equals(PayoneParameters.ResponseErrorCodes.REDIRECT))
            {
                output = getString(R.string.keyResultStatusSuccess);
                color = this.getResources().getColor(R.color.green);
            }
            else if (status.equals(PayoneParameters.ResponseErrorCodes.INVALID))
            {
                output = getString(R.string.keyResultStatusInvalid);
                color = this.getResources().getColor(R.color.yellow);
            }
            else
            {
                output = getString(R.string.keyResultStatusError);
                color = this.getResources().getColor(R.color.red);
            }

            Spannable coloredText = new SpannableString(output);
            coloredText.setSpan(new ForegroundColorSpan(color), 0, coloredText.length(),
                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            addOutput(coloredText);
        }

        // show result details
        output = "";

        for (Entry<String, String> entry : result.getCollection().entrySet())
        {
            output += String.format("%s: %s\n", entry.getKey(), entry.getValue());
        }

        addOutput(output);
    }

    private void addOutput(CharSequence text)
    {
        mTextResultOutput.append("\n");
        mTextResultOutput.append(text);
    }

    /**
     * button listener - shows a dialog with all request parameters as they are sent to the server
     * @param view - generated by the system
     */
    public void onRequestDetails(View view)
    {
        String message = getString(R.string.keyMessageRequestDataMissing);
        if (REQUEST_DATA != null)
        {
            message = REQUEST_DATA.getRequestParameters().toPostBodyParameter(REQUEST_DATA.getM5Key());
            message = message.replace("&", "\n");
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(message);
        builder.setNeutralButton(R.string.keyButtonOk, new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int id)
            {
                dialog.dismiss();
            }
        });
        builder.create().show();
    }

    /**
     * button listener
     * @param view - generated by the system
     */
    public void onBack(View view)
    {
        Intent intent = new Intent(this, PaymentOptionsActivity.class);
        startActivity(intent);
    }
}
