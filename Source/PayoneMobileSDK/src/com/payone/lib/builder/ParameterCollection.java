/*
 * $Id$
 * 
 * Copyright 2012 Exozet Games GmbH
 */
package com.payone.lib.builder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Hashtable;
import java.util.Set;
import java.util.regex.Pattern;

import android.util.Log;

import com.payone.lib.parameter.PayoneParameters;
import com.payone.lib.utils.Utils;

public class ParameterCollection
{
    private Hashtable<String, String> mParameterCollection = null;
    
    public ParameterCollection()
    {
        mParameterCollection = new Hashtable<String, String>();
    }
    
    public Hashtable<String, String> getCollection()
    {
        return mParameterCollection;
    }
    
    public int getCount()
    {
        return mParameterCollection.size();
    }

    public void put(String key, String value)
    {
        mParameterCollection.put(key, value);
    }
    
    public String getValue(String key)
    {
        return mParameterCollection.get(key);
    }
    
    public String toPostBodyParameter(String additionalPayoneKey)
    {
        if(mParameterCollection != null)
        {
            String md5HashValue = createMD5HashFromArguments(mParameterCollection, additionalPayoneKey);
            mParameterCollection.put(PayoneParameters.HASH, md5HashValue);
            
            StringBuilder builder = new StringBuilder();

            int i = 0;
            
            // convert all collection entries into one concatenated string
            // format : "key=value&key2=value2&key3=value3
            for(String key : mParameterCollection.keySet())
            {
                if(i > 0)
                {
                    builder.append("&");
                }
                
                builder.append(key + "=" + mParameterCollection.get(key));
                i++;
            }
            
            return builder.toString();
        }
        
        return "";
        
    }
    
    private static String createMD5HashFromArguments(Hashtable<String, String> parameterCollection, String additionalPayoneKey)
    {
        // since not all parameters are used to build up the hash, this will give us a list of "relevant" key entries
        ArrayList<String> usedHasEntries = RetrieveHashRelevantKeyValuesAsList();
        
        // filter the parameters by using only "hash-relevant" key-value pairs
        Set<String> argumentDictAsList = parameterCollection.keySet();
        ArrayList<String> filteredList = new ArrayList<String>();

        // make intersection for both lists
        for(String entry : argumentDictAsList)
        {
            if(usedHasEntries.contains(entry))
            {
                filteredList.add(entry);
            }
        }

        // since there are hash relevant values, which are inconcrete (like id[1], id[2]...), we have to use regular expressions
        // for all existing keys and add them to the filteredList as well.
        for(String entryKey : parameterCollection.keySet())
        {
            if(Pattern.matches(PayoneParameters.ID_REGEX_PLACEHOLDER, entryKey) ||
               Pattern.matches(PayoneParameters.PR_REGEX_PLACEHOLDER, entryKey) ||
               Pattern.matches(PayoneParameters.NO_REGEX_PLACEHOLDER, entryKey) ||
               Pattern.matches(PayoneParameters.DE_REGEX_PLACEHOLDER, entryKey) ||
               Pattern.matches(PayoneParameters.VA_REGEX_PLACEHOLDER, entryKey))
            {
                filteredList.add(entryKey);
            }
        }
        
        
        Collections.sort(filteredList);
        
        String output = "";
        
        for(String key : filteredList)
        {
            output += parameterCollection.get(key);
            
            Log.d("DEBUG", "add value " + key);
        }
        
        output += additionalPayoneKey;
        
        String hash = Utils.createMD5Hash(output);
        return hash;
    }
    
    private static ArrayList<String> RetrieveHashRelevantKeyValuesAsList()
    {
        ArrayList<String> usedHashEntries = new ArrayList<String>();
        
        usedHashEntries.add(PayoneParameters.MID);
        usedHashEntries.add(PayoneParameters.AID);
        usedHashEntries.add(PayoneParameters.PORTALID);
        usedHashEntries.add(PayoneParameters.MODE);
        usedHashEntries.add(PayoneParameters.REQUEST);
        usedHashEntries.add(PayoneParameters.RESPONSETYPE);
        usedHashEntries.add(PayoneParameters.REFERENCE);
        usedHashEntries.add(PayoneParameters.USERID);
        usedHashEntries.add(PayoneParameters.CUSTOMERID);
        usedHashEntries.add(PayoneParameters.PARAM);
        usedHashEntries.add(PayoneParameters.NARRATIVE_TEXT);
        usedHashEntries.add(PayoneParameters.SUCCESSURL);
        usedHashEntries.add(PayoneParameters.ERRORURL);
        usedHashEntries.add(PayoneParameters.BACKURL);
        usedHashEntries.add(PayoneParameters.EXITURL);
        usedHashEntries.add(PayoneParameters.CLEARINGTYPE);
        usedHashEntries.add(PayoneParameters.ENCODING);
        usedHashEntries.add(PayoneParameters.AMOUNT);
        usedHashEntries.add(PayoneParameters.CURRENCY);
        usedHashEntries.add(PayoneParameters.DUE_TIME);
        usedHashEntries.add(PayoneParameters.STORECARDDATA);
        usedHashEntries.add(PayoneParameters.CHECKTYPE);
        usedHashEntries.add(PayoneParameters.ADDRESSCHECKTYPE);
        usedHashEntries.add(PayoneParameters.CONSUMERSCORETYPE);
        usedHashEntries.add(PayoneParameters.INVOICEID);
        usedHashEntries.add(PayoneParameters.INVOICEAPPENDIX);
        usedHashEntries.add(PayoneParameters.INVOICE_DELIVERYMODE);
        usedHashEntries.add(PayoneParameters.ECI);
        usedHashEntries.add(PayoneParameters.PRODUCTID);
        usedHashEntries.add(PayoneParameters.ACCESSNAME);
        usedHashEntries.add(PayoneParameters.ACCESSCODE);
        usedHashEntries.add(PayoneParameters.ACCESS_EXPIRETIME);
        usedHashEntries.add(PayoneParameters.ACCESS_CANCELTIME);
        usedHashEntries.add(PayoneParameters.ACCESS_STARTTIME);
        usedHashEntries.add(PayoneParameters.ACCESS_PERIOD);
        usedHashEntries.add(PayoneParameters.ACCESS_ABOPERIOD);
        usedHashEntries.add(PayoneParameters.ACCESS_PRICE);
        usedHashEntries.add(PayoneParameters.ACCESS_ABOPRICE);
        usedHashEntries.add(PayoneParameters.ACCESS_VAT);
        usedHashEntries.add(PayoneParameters.SETTLEPERIOD);
        usedHashEntries.add(PayoneParameters.SETTLETIME);
        usedHashEntries.add(PayoneParameters.VACCOUNTNAME);
        usedHashEntries.add(PayoneParameters.VREFERENCE);

        return usedHashEntries;
    }
    
}
