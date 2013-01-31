/*
 * $Id$
 *
 * Copyright 2012 Exozet Games GmbH
 */
package com.payone.lib.utils;

import com.payone.lib.builder.ParameterCollection;
import com.payone.lib.requests.AuthorizationRequest;
import com.payone.lib.requests.CreditCardCheckRequest;
import com.payone.lib.requests.PreAuthorizationRequest;

public class PayoneRequestFactory
{
    public static CreditCardCheckRequest createCreditCardCheckRequest(RequestListener listener, String key, ParameterCollection parameterCollection) throws Exception
    {            
        CreditCardCheckRequest request = new CreditCardCheckRequest();
        request.setKey(key);
        request.setParameterToDeliver(parameterCollection);
        request.RunASync(listener);

        return request;
    }

    public static PreAuthorizationRequest createPreAuthorizationRequest(RequestListener listener, String key, ParameterCollection parameterCollection) throws Exception
    {
        PreAuthorizationRequest request = new PreAuthorizationRequest();
        request.setKey(key);
        request.setParameterToDeliver(parameterCollection);
        request.RunASync(listener);

        return request;
    }

    public static AuthorizationRequest createAuthorizationRequest(RequestListener listener, String key, ParameterCollection parameterCollection) throws Exception
    {
        AuthorizationRequest request = new AuthorizationRequest();
        request.setKey(key);
        request.setParameterToDeliver(parameterCollection);
        request.RunASync(listener);

        return request;
    }
}
