/*
 * $Id$
 * 
 * Copyright 2012 Exozet Games GmbH
 */
package com.payone.lib.utils;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Utils
{
    public static String createMD5Hash(String valueToHash)
    {
        // Create MD5 Hash
        MessageDigest digest;

        try 
        {
            digest = MessageDigest.getInstance("MD5");
            digest.update(valueToHash.getBytes(), 0, valueToHash.length());
            String hash = new BigInteger(1, digest.digest()).toString(16);
            
            return hash;
        } 
        catch (NoSuchAlgorithmException e)
        {
            e.printStackTrace();
        }

        return null;
    }

    public static boolean isNullOrEmpty(final String str)
    {
        if (str == null)
        {
            return true;
        }
        if (str.trim().equals(""))
        {
            return true;
        }

        return false;
    }
}
