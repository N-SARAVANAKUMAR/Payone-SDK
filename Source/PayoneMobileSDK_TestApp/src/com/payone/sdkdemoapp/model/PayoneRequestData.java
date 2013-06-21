/*
 * $Id$
 * 
 * Copyright 2012 Exozet Games GmbH
 */
/**
 * 
 */
package com.payone.sdkdemoapp.model;

import java.util.Random;

import com.payone.lib.builder.ParameterCollection;
import com.payone.lib.parameter.PayoneParameters;

/**
 * @author hendrik.apel
 * @version $Rev$ $Date$
 *
 */
public class PayoneRequestData
{
    public enum RequestType
    {
        CREDITCARD_AUTHORIZATION, DEBIT_PREAUTHORIZATION, DEBIT_AUTHORIZATION
    };

    public enum RequestMode
    {
        TEST, LIVE
    };

    ////////////////////////////////////////////////////////////////////////////
    // PARAMETERS FOR THE REQUEST
    ////////////////////////////////////////////////////////////////////////////
    private String      mAccountId              = "";
    private String      mMId                    = "";
    private String      mPortalId               = "";

    private String      mCardNumber             = "";
    private String      mCardExpireDate         = "";
    private String      mCardVerificationNumber = "";
    private String      mCardType               = "";

    private String      mPaymentAmount          = "";
    private String      mPaymentBankAccount     = "";
    private String      mPaymentBankCode        = "";

    private String      mUserFirstName          = "";
    private String      mUserLastName           = "";
    private String      mUserCurrency           = "";
    private String      mUserCountry            = "";

    private String      mReferenceId            = "";
    ////////////////////////////////////////////////////////////////////////////
    // MISC PARAMETERS
    ////////////////////////////////////////////////////////////////////////////
    private String      mM5Key                  = "";
    private RequestType mRequestType;
    private RequestMode mRequestMode;

    //////////////////////////////////////////////////////////////////////////////
    // STATIC METHODS
    //////////////////////////////////////////////////////////////////////////////    

    public static PayoneRequestData createCreditCardRequest(String cardNumber, String cardVerification,
            String cardExpireDate, String cardType)
    {
        PayoneRequestData result = new PayoneRequestData();

        result.mRequestType = RequestType.CREDITCARD_AUTHORIZATION;

        result.mCardNumber = cardNumber;
        result.mCardVerificationNumber = cardVerification;
        result.mCardExpireDate = cardExpireDate;
        result.mCardType = cardType;

        return result;
    }

    public static PayoneRequestData createDebitRequest(String bankAccount, String bankCode, String paymentAmount,
            boolean isPreAuthorization)
    {
        PayoneRequestData result = new PayoneRequestData();

        result.mRequestType = isPreAuthorization ? RequestType.DEBIT_PREAUTHORIZATION : RequestType.DEBIT_AUTHORIZATION;

        result.mPaymentBankAccount = bankAccount;
        result.mPaymentBankCode = bankCode;
        result.mPaymentAmount = paymentAmount;

        result.mReferenceId = createUniqueReferenceIdString();

        return result;
    }

    /**
     * Util method to create a unique reference id string
     * @return
     */
    private static String createUniqueReferenceIdString()
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

        return result.toString();
    }

    //////////////////////////////////////////////////////////////////////////////
    // INSTANCE METHODS
    //////////////////////////////////////////////////////////////////////////////    

    public void setCredentials(String portalId, String mId, String accountId, String key)
    {
        mPortalId = portalId;
        mMId = mId;
        mAccountId = accountId;
        mM5Key = key;
    }

    public void setUserData(String firstName, String lastName, String country, String paymentCurrency)
    {
        mUserFirstName = firstName;
        mUserLastName = lastName;
        mUserCountry = country;
        mUserCurrency = paymentCurrency;
    }

    public void setRequestMode(RequestMode mode)
    {
        mRequestMode = mode;
    }

    public String getM5Key()
    {
        return mM5Key;
    }

    public RequestType getRequestType()
    {
        return mRequestType;
    }

    /**
     * Returns a ParameterCollection with all relevant data set for the selected request type. The result can be used by PayoneRequestFactory.
     * @return ParameterCollection
     */
    public ParameterCollection getRequestParameters()
    {
        ParameterCollection parameters;

        switch (mRequestType)
        {
            case DEBIT_PREAUTHORIZATION:
                parameters = getPreAuthorizationRequestParameters();
                break;
            case DEBIT_AUTHORIZATION:
                parameters = getAuthorizationRequestParameters();
                break;
            case CREDITCARD_AUTHORIZATION:
                parameters = getCreditCardRequestParameters();
                break;
            default:
                parameters = null;
                break;
        }

        if (parameters != null)
        {
            if (mRequestMode == RequestMode.LIVE)
            {
                parameters.put(PayoneParameters.MODE, PayoneParameters.ModeParameter.LIVE);
            }
            else
            {
                parameters.put(PayoneParameters.MODE, PayoneParameters.ModeParameter.TEST);
            }
        }

        return parameters;
    }

    private ParameterCollection getCreditCardRequestParameters()
    {
        ParameterCollection parameters = createDefaultRequestParameters();

        parameters.put(PayoneParameters.CARDPAN, mCardNumber);
        parameters.put(PayoneParameters.CARDEXPIREDATE, mCardExpireDate);
        parameters.put(PayoneParameters.CARDCVC2, mCardVerificationNumber);
        parameters.put(PayoneParameters.CARDTYPE, mCardType);

        parameters.put(PayoneParameters.REQUEST, PayoneParameters.RequestParameter.CREDITCARDCHECK);
        parameters.put(PayoneParameters.STORECARDDATA, PayoneParameters.StoreCardDataParameter.YES);

        return parameters;
    }

    private ParameterCollection getPreAuthorizationRequestParameters()
    {
        ParameterCollection parameters = createDefaultRequestParameters();
        addDebitParameters(parameters);

        parameters.put(PayoneParameters.REQUEST, PayoneParameters.RequestParameter.PREAUTHORIZATION);

        return parameters;
    }

    private ParameterCollection getAuthorizationRequestParameters()
    {
        ParameterCollection parameters = createDefaultRequestParameters();
        addDebitParameters(parameters);
        addHashParameters(parameters);

        parameters.put(PayoneParameters.REQUEST, PayoneParameters.RequestParameter.AUTHORIZATION);

        return parameters;
    }

    private ParameterCollection createDefaultRequestParameters()
    {
        ParameterCollection parameters = new ParameterCollection();

        parameters.put(PayoneParameters.AID, mAccountId);
        parameters.put(PayoneParameters.MID, mMId);
        parameters.put(PayoneParameters.PORTALID, mPortalId);

        parameters.put(PayoneParameters.FIRSTNAME, mUserFirstName);
        parameters.put(PayoneParameters.LASTNAME, mUserLastName);
        parameters.put(PayoneParameters.COUNTRY, mUserCountry);
        parameters.put(PayoneParameters.CURRENCY, mUserCurrency);

        parameters.put(PayoneParameters.RESPONSETYPE, PayoneParameters.ResponseTypeParameter.JSON);
        parameters.put(PayoneParameters.ENCODING, PayoneParameters.EncodingParameter.UTF_8);
        parameters.put(PayoneParameters.LANGUAGE, "de");

        return parameters;
    }

    private void addDebitParameters(ParameterCollection parameters)
    {
        parameters.put(PayoneParameters.BANKACCOUNT, mPaymentBankAccount);
        parameters.put(PayoneParameters.BANKCODE, mPaymentBankCode);
        parameters.put(PayoneParameters.BANKCOUNTRY, mUserCountry);
        parameters.put(PayoneParameters.AMOUNT, mPaymentAmount);

        parameters.put(PayoneParameters.REFERENCE, mReferenceId);

        parameters.put(PayoneParameters.CLEARINGTYPE, PayoneParameters.ClearingTypeParameter.DEBIT);
    }

    private void addHashParameters(ParameterCollection parameters)
    {
        for (int i = 1; i < 2; i++)
        {
            String id = String.format(PayoneParameters.ID_PLACEHOLDER, i);
            String pr = String.format(PayoneParameters.PR_PLACEHOLDER, i);
            String no = String.format(PayoneParameters.NO_PLACEHOLDER, i);
            String de = String.format(PayoneParameters.DE_PLACEHOLDER, i);
            String va = String.format(PayoneParameters.VA_PLACEHOLDER, i);

            parameters.put(id, "123-456");
            parameters.put(pr, mPaymentAmount);
            parameters.put(no, "1");
            parameters.put(de, "test");
            parameters.put(va, "19");
        }
    }
}
