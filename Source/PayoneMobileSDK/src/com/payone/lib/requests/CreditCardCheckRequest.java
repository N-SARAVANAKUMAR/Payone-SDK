/*
 * $Id$
 *
 * Copyright 2012 Exozet Games GmbH
 */
package com.payone.lib.requests;

import org.json.JSONException;

import com.payone.lib.builder.ParameterCollection;
import com.payone.lib.responses.CreditCardCheckResponse;

public class CreditCardCheckRequest extends IPayoneRequest
{
    @Override
    protected ParameterCollection convertResponseToCollection(String response)
    {
        CreditCardCheckResponse responseObject;
        try
        {
            responseObject = new CreditCardCheckResponse(response);
            return responseObject.GetResponseAsDictionary();
        }
        catch (JSONException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        return null;
    }

}