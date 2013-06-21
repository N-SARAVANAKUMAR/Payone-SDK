package com.payone.sdkdemoapp;

import java.util.Map.Entry;
import java.util.Random;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.payone.lib.builder.ParameterCollection;
import com.payone.lib.parameter.PayoneParameters;
import com.payone.lib.utils.PayoneRequestFactory;
import com.payone.lib.utils.RequestListener;

public class PayoneTestActivity extends Activity implements RequestListener
{
    private static final String TAG = PayoneTestActivity.class.getName();
    
    private TextView resultTitleTextView;
    private TextView resultOutputTextView;
    private ProgressBar progressIndicator;

    /* (non-Javadoc)
     * @see android.app.Activity#onCreate(android.os.Bundle)
     */
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        resultTitleTextView = (TextView)this.findViewById(R.id.textResultTitle);
        resultOutputTextView = (TextView)this.findViewById(R.id.textResultOutput);
        progressIndicator = (ProgressBar)this.findViewById(R.id.progressbar);
    }

    /**
     * Util method to create a unique reference id string
     * @return
     */
    private static String CreateUniqueReferenceIdString()
    {
        // Array with all alphanumeric characters:
        char[] chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789".toCharArray();
        int targetKeyLength = 20;
        
        Random random = new Random();
        
        // Create a random id string with 20 alpha numeric characters
        StringBuffer result = new StringBuffer();
        for (int i = 0; i < targetKeyLength; i++)
        {
            result.append(chars[random.nextInt(chars.length)]);
        }

        Log.i(TAG, "Reference id: '" + result.toString() + "' - " + result.length());
        
        return result.toString();
    }

    /**
     * Request method for the button onClick.
     * @param view
     */
    public void onCreditCardCheck(View view)
    {
        Log.i(TAG, "onCreditCardCheck");
        
        ParameterCollection parameters = new ParameterCollection();
        parameters.put(PayoneParameters.AID, "18628");
        parameters.put(PayoneParameters.MID, "18613");
        parameters.put(PayoneParameters.PORTALID, "2013349");
        parameters.put(PayoneParameters.MODE, PayoneParameters.ModeParameter.TEST);
        parameters.put(PayoneParameters.REQUEST, PayoneParameters.RequestParameter.CREDITCARDCHECK);
        parameters.put(PayoneParameters.RESPONSETYPE, PayoneParameters.ResponseTypeParameter.JSON);
        parameters.put(PayoneParameters.CARDPAN, "5500000000000004");
        parameters.put(PayoneParameters.CARDTYPE, PayoneParameters.CreditCardVariations.MASTERCARD);
        parameters.put(PayoneParameters.CARDEXPIREDATE, "1401");
        parameters.put(PayoneParameters.CARDCVC2, "1234");
        parameters.put(PayoneParameters.ENCODING, PayoneParameters.EncodingParameter.UTF_8);
        parameters.put(PayoneParameters.STORECARDDATA, PayoneParameters.StoreCardDataParameter.YES);
        parameters.put(PayoneParameters.LANGUAGE, "de");

        String m5_key = "TuyuotaaNgaeboaWaithohD4ohKooz";
        
        try
        {
            PayoneRequestFactory.createCreditCardCheckRequest(this, m5_key, parameters);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        
        progressIndicator.setVisibility(View.VISIBLE);
    }

    /**
     * Request method for the button onClick.
     * @param view
     */
    public void onPreAuthorization(View view)
    {
        Log.i(TAG, "onPreAuthorization");
        
        ParameterCollection parameters = new ParameterCollection();

        parameters.put(PayoneParameters.MID, "18613");
        parameters.put(PayoneParameters.PORTALID, "2013349");
        parameters.put(PayoneParameters.MODE, PayoneParameters.ModeParameter.TEST);
        parameters.put(PayoneParameters.REQUEST, PayoneParameters.RequestParameter.PREAUTHORIZATION);
        parameters.put(PayoneParameters.RESPONSETYPE, PayoneParameters.ResponseTypeParameter.JSON);
        parameters.put(PayoneParameters.ENCODING, PayoneParameters.EncodingParameter.UTF_8);

        parameters.put(PayoneParameters.AID, "18628");
        parameters.put(PayoneParameters.CLEARINGTYPE, PayoneParameters.ClearingTypeParameter.DEBIT);
        parameters.put(PayoneParameters.REFERENCE, CreateUniqueReferenceIdString());
        parameters.put(PayoneParameters.AMOUNT, "1");
        parameters.put(PayoneParameters.CURRENCY, "EUR");
        parameters.put(PayoneParameters.FIRSTNAME, "Max");
        parameters.put(PayoneParameters.LASTNAME, "Mustermann");
        parameters.put(PayoneParameters.COUNTRY, "DE");

        parameters.put(PayoneParameters.BANKCOUNTRY, PayoneParameters.BankCountryParameter.DE);
        parameters.put(PayoneParameters.BANKACCOUNT, "123456789");
        parameters.put(PayoneParameters.BANKCODE, "13070024");

        String m5_key = "TuyuotaaNgaeboaWaithohD4ohKooz";
        
        try
        {
            PayoneRequestFactory.createPreAuthorizationRequest(this, m5_key, parameters);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        
        progressIndicator.setVisibility(View.VISIBLE);
    }

    /**
     * Request method for the button onClick.
     * @param view
     */
    public void onAuthorization(View view)
    {
        Log.i(TAG, "onAuthorization");
        
        ParameterCollection parameters = new ParameterCollection();

        parameters.put(PayoneParameters.MID, "18613");
        parameters.put(PayoneParameters.PORTALID, "2013349");
        parameters.put(PayoneParameters.MODE, PayoneParameters.ModeParameter.TEST);
        parameters.put(PayoneParameters.REQUEST, PayoneParameters.RequestParameter.AUTHORIZATION);
        parameters.put(PayoneParameters.RESPONSETYPE, PayoneParameters.ResponseTypeParameter.JSON);
        parameters.put(PayoneParameters.ENCODING, PayoneParameters.EncodingParameter.UTF_8);

        parameters.put(PayoneParameters.AID, "18628");
        parameters.put(PayoneParameters.CLEARINGTYPE, PayoneParameters.ClearingTypeParameter.DEBIT);
        parameters.put(PayoneParameters.REFERENCE, CreateUniqueReferenceIdString());
    
        parameters.put(PayoneParameters.CURRENCY, "EUR");
        parameters.put(PayoneParameters.FIRSTNAME, "Max");
        parameters.put(PayoneParameters.LASTNAME, "Mustermann");
        parameters.put(PayoneParameters.COUNTRY, "DE");
                

        for (int i = 1; i < 2; i++ )
        {
            String id = String.format(PayoneParameters.ID_PLACEHOLDER, i);
            String pr = String.format(PayoneParameters.PR_PLACEHOLDER, i);
            String no = String.format(PayoneParameters.NO_PLACEHOLDER, i);
            String de = String.format(PayoneParameters.DE_PLACEHOLDER, i);
            String va = String.format(PayoneParameters.VA_PLACEHOLDER, i);

            parameters.put(id, "123-3456");
            parameters.put(pr, "5900");
            parameters.put(no, "1");
            parameters.put(de, "Hamburger Royal");
            parameters.put(va, "19");
        }

        parameters.put(PayoneParameters.AMOUNT, "5900");

        parameters.put(PayoneParameters.BANKCOUNTRY, PayoneParameters.BankCountryParameter.DE);
        parameters.put(PayoneParameters.BANKACCOUNT, "123456789");
        parameters.put(PayoneParameters.BANKCODE, "13070024");

        String m5_key = "TuyuotaaNgaeboaWaithohD4ohKooz";
        
        try
        {
            PayoneRequestFactory.createAuthorizationRequest(this, m5_key, parameters);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        
        progressIndicator.setVisibility(View.VISIBLE);
    }

    /* (non-Javadoc)
     * @see com.payone.lib.utils.RequestListener#request(com.payone.lib.builder.ParameterCollection)
     */
    public void onRequestResult(ParameterCollection result)
    {
        progressIndicator.setVisibility(View.GONE);
        
        Log.d(TAG, "onRequestResult: parameter collection " + result);
        
        String output = "";

        for (Entry<String, String> entry : result.getCollection().entrySet())
        {
            output += String.format("Key(%s) - Value(%s)\n", entry.getKey(), entry.getValue());
        }
        
        Log.i(TAG, output);
        
        resultOutputTextView.setText(output);
        
        
        // Check status
        if (result.getCollection().containsKey(PayoneParameters.STATUS))
        {
            String status = result.getCollection().get(PayoneParameters.STATUS);

            if (
                    status.equals(PayoneParameters.ResponseErrorCodes.APPROVED) ||
                    status.equals(PayoneParameters.ResponseErrorCodes.VALID) ||
                    status.equals(PayoneParameters.ResponseErrorCodes.REDIRECT))
            {
                resultTitleTextView.setTextColor(this.getResources().getColor(R.color.green));
            }
            else if(status.equals(PayoneParameters.ResponseErrorCodes.INVALID))
            {
                resultTitleTextView.setTextColor(this.getResources().getColor(R.color.yellow));
            }
            else
            {
                resultTitleTextView.setTextColor(this.getResources().getColor(R.color.red));
            }
        }
        
    }
}