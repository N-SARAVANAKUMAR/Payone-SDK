/*
 * $Id$
 *
 * Copyright 2012 Exozet Games GmbH
 */
package com.payone.lib.responses;

import org.json.JSONException;
import org.json.JSONObject;

import com.payone.lib.builder.ParameterCollection;
import com.payone.lib.parameter.PayoneParameters;
import com.payone.lib.utils.Utils;

public class CreditCardCheckResponse extends JSONObject
{
    /** Response status (mandatory property)*/
    private String mStatus;
    private String mPseudoCardpan;
    private String mTruncatedCardpan;
    private String mErrorCode;
    private String mErrorMessage;
    private String mCustomerMessage;
    
    public CreditCardCheckResponse(String json) throws JSONException
    {
        super(json);
        
        try
        {
            mStatus = getString(PayoneParameters.STATUS);
        }
        catch (JSONException e)
        {
            mStatus = "";
        }
        
        try
        {
            mPseudoCardpan = getString(PayoneParameters.PSEUDOCARDPAN);
        }
        catch (JSONException e)
        {
            mPseudoCardpan = "";
        }
        
        try
        {
            mTruncatedCardpan = getString(PayoneParameters.TRUNCATEDCARDPAN);
        }
        catch (JSONException e)
        {
            mTruncatedCardpan = "";
        }
        
        try
        {
            mErrorCode = getString(PayoneParameters.ERRORCODE);
        }
        catch (JSONException e)
        {
            mErrorCode = "";
        }
        
        try
        {
            mErrorMessage = getString(PayoneParameters.ERRORMESSAGE);
        }
        catch (JSONException e)
        {
            mErrorMessage = "";
        }
        
        try
        {
            mCustomerMessage = getString(PayoneParameters.CUSTOMERMESSAGE);
        }
        catch (JSONException e)
        {
           mCustomerMessage = "";
        }
    }
    
    public ParameterCollection GetResponseAsDictionary()
    {
        ParameterCollection result = new ParameterCollection();
        
        result.put(PayoneParameters.STATUS, mStatus);
        
        if(mStatus.equalsIgnoreCase(PayoneParameters.ResponseErrorCodes.VALID))
        {
            if(!Utils.isNullOrEmpty(mPseudoCardpan))
            {
                result.put(PayoneParameters.PSEUDOCARDPAN, mPseudoCardpan);
            }

            if(!Utils.isNullOrEmpty(mTruncatedCardpan))
            {
                result.put(PayoneParameters.TRUNCATEDCARDPAN, mTruncatedCardpan);
            }
        }
        else
        {
            if(!Utils.isNullOrEmpty(mErrorCode))
            {
                result.put(PayoneParameters.ERRORCODE, mErrorCode);
            }

            if(!Utils.isNullOrEmpty(mErrorMessage))
            {
                result.put(PayoneParameters.ERRORMESSAGE, mErrorMessage);
            }

            if(!Utils.isNullOrEmpty(mCustomerMessage))
            {
                result.put(PayoneParameters.CUSTOMERMESSAGE, mCustomerMessage);
            }
        }
        
        return result;
    }
}
