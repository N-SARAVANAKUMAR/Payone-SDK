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

public class AuthorizationResponse  extends JSONObject
{
    /** Response status (mandatory property)*/
    private String mStatus;
    private String mTxId;
    private String mUserId;
    private String mRedirectId;
    private String mClearingBankAccountHolder;
    private String mClearingBankCountry;
    private String mClearingBankAccount;
    private String mClearingBankCode;
    private String mClearingBankIBAN;
    private String mClearingBankBIC;
    private String mClearingBankCity;
    private String mClearingBankName;
    private String mErrorCode;
    private String mErrorMessage;
    private String mCustomerMessage;
    
    public AuthorizationResponse(String json) throws JSONException
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
            mTxId = getString(PayoneParameters.TXID);
        }
        catch (JSONException e)
        {
            mTxId = "";
        }
        
        try
        {
            mUserId = getString(PayoneParameters.USERID);
        }
        catch (JSONException e)
        {
            mUserId = "";
        }
        
        try
        {
            mRedirectId = getString(PayoneParameters.REDIRECTURL);
        }
        catch (JSONException e)
        {
            mRedirectId = "";
        }
        
        try
        {
            mClearingBankAccountHolder = getString(PayoneParameters.CLEARING_BANKACCOUNTHOLDER);
        }
        catch (JSONException e)
        {
            mClearingBankAccountHolder = "";
        }
        
        try
        {
            mClearingBankCountry = getString(PayoneParameters.CLEARING_BANKCOUNTRY);
        }
        catch (JSONException e)
        {
           mClearingBankCountry = "";
        }

        try
        {
            mClearingBankAccount = getString(PayoneParameters.CLEARING_BANKACCOUNT);
        }
        catch (JSONException e)
        {
            mClearingBankAccount = "";
        }
        
        try
        {
            mClearingBankCode = getString(PayoneParameters.CLEARING_BANKCODE);
        }
        catch (JSONException e)
        {
            mClearingBankCode = "";
        }
        
        try
        {
            mClearingBankIBAN = getString(PayoneParameters.CLEARING_BANKIBAN);
        }
        catch (JSONException e)
        {
            mClearingBankIBAN = "";
        }
        
        try
        {
            mClearingBankBIC = getString(PayoneParameters.CLEARING_BANKBIC);
        }
        catch (JSONException e)
        {
            mClearingBankBIC = "";
        }
        
        try
        {
            mClearingBankCity = getString(PayoneParameters.CLEARING_BANKCITY);
        }
        catch (JSONException e)
        {
            mClearingBankCity = "";
        }

        try
        {
            mClearingBankName = getString(PayoneParameters.CLEARING_BANKNAME);
        }
        catch (JSONException e)
        {
            mClearingBankName = "";
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
        
        if(mStatus.equalsIgnoreCase(PayoneParameters.ResponseErrorCodes.APPROVED) ||
           mStatus.equalsIgnoreCase(PayoneParameters.ResponseErrorCodes.REDIRECT))
        {
            if(!Utils.isNullOrEmpty(mTxId))
            {
                result.put(PayoneParameters.TXID, mTxId);
            }

            if(!Utils.isNullOrEmpty(mUserId))
            {
                result.put(PayoneParameters.USERID, mUserId);
            }

            if(mStatus.equalsIgnoreCase(PayoneParameters.ResponseErrorCodes.REDIRECT))
            {
                if(!Utils.isNullOrEmpty(mRedirectId))
                {
                    result.put(PayoneParameters.REDIRECTURL, mRedirectId);
                }
            }

            if(!Utils.isNullOrEmpty(mClearingBankAccountHolder))
            {
                result.put(PayoneParameters.CLEARING_BANKACCOUNTHOLDER, mClearingBankAccountHolder);
            }
            
            if(!Utils.isNullOrEmpty(mClearingBankCountry))
            {
                result.put(PayoneParameters.CLEARING_BANKCOUNTRY, mClearingBankCountry);
            }

            if(!Utils.isNullOrEmpty(mClearingBankAccount))
            {
                result.put(PayoneParameters.CLEARING_BANKACCOUNT, mClearingBankAccount);
            }
            
            if(!Utils.isNullOrEmpty(mClearingBankCode))
            {
                result.put(PayoneParameters.CLEARING_BANKCODE, mClearingBankCode);
            }
            
            if(!Utils.isNullOrEmpty(mClearingBankIBAN))
            {
                result.put(PayoneParameters.CLEARING_BANKIBAN, mClearingBankIBAN);
            }
            
            if(!Utils.isNullOrEmpty(mClearingBankBIC))
            {
                result.put(PayoneParameters.CLEARING_BANKBIC, mClearingBankBIC);
            }
            
            if(!Utils.isNullOrEmpty(mClearingBankCity))
            {
                result.put(PayoneParameters.CLEARING_BANKCITY, mClearingBankCity);
            }
            
            if(!Utils.isNullOrEmpty(mClearingBankName))
            {
                result.put(PayoneParameters.CLEARING_BANKNAME, mClearingBankName);
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
