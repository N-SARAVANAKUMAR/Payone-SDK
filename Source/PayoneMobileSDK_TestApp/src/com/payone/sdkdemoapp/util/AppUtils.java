/*
 * $Id$
 * 
 * Copyright 2012 Exozet Games GmbH
 */
/**
 * 
 */
package com.payone.sdkdemoapp.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.payone.sdkdemoapp.R;
import com.payone.sdkdemoapp.gui.base.InputActivity;
import com.payone.sdkdemoapp.model.PayoneRequestData;
import com.payone.sdkdemoapp.model.PayoneRequestData.RequestMode;

/**
 * @author hendrik.apel
 * @version $Rev$ $Date$
 *
 */
public class AppUtils
{
    //////////////////////////////////////////////////////////////////////////
    // PREFERENCES
    //////////////////////////////////////////////////////////////////////////

    public static String getStringSetting(int settingId, Context context)
    {
        return preferences(context).getString(context.getString(settingId), null);
    }

    public static void setStringSetting(int settingId, String settingValue, Context context)
    {
        preferences(context).edit().putString(context.getString(settingId), settingValue).commit();
    }

    private static SharedPreferences preferences(Context context)
    {
        return PreferenceManager.getDefaultSharedPreferences(context);
    }

    /**
     * Checks if the credentials in the app settings are defined.
     * @param context
     * @return true if all credentials are defined
     */
    public static boolean checkCredentials(Context context)
    {
        // gather data given in preferences 
        String accountId = getStringSetting(R.string.idPreferencePaymentAccountId, context);
        String mId = getStringSetting(R.string.idPreferencePaymentMid, context);
        String portalId = getStringSetting(R.string.idPreferencePaymentPortalId, context);
        String m5Key = getStringSetting(R.string.idPreferencePaymentKey, context);

        if (!isStringValue(accountId) || !isStringValue(mId) || !isStringValue(portalId) || !isStringValue(m5Key))
        {
            return false;
        }
        else
        {
            return true;
        }
    }

    /**
     * Writes all dynamic data collected by the app into the given request. 
     * @param activity - an InputActivity to provide access to the data that holds the user input and to the app preferences.
     * @param request
     */
    public static void setRequestData(InputActivity activity, PayoneRequestData request)
    {
        setCredentials(activity, request);
        setUserInfo(activity, request);
        setRequestMode(activity, request);
    }

    /**
     * Copies the user info defined in the UserInfoActivity into the given request.
     * @param context - an InputActivity to provide access to the data that holds the user input.
     * @param request
     */
    private static void setUserInfo(InputActivity activity, PayoneRequestData request)
    {
        SharedPreferences data = activity.getData();
        String firstName = data.getString(activity.getString(R.string.idPreferencesInputUserDataFirstName), "");
        String lastName = data.getString(activity.getString(R.string.idPreferencesInputUserDataLastName), "");
        String country = data.getString(activity.getString(R.string.idPreferencesInputUserDataCountry), "");
        String currency = data.getString(activity.getString(R.string.idPreferencesInputUserDataCurrency), "");

        request.setUserData(firstName, lastName, country, currency);
    }

    /**
     * Copies the credentials defined in the App settings into the given request.
     * @param context - the context that provides access to the preferences.
     * @param request
     */
    private static void setCredentials(Context context, PayoneRequestData request)
    {
        String accountId = getStringSetting(R.string.idPreferencePaymentAccountId, context);
        String mId = getStringSetting(R.string.idPreferencePaymentMid, context);
        String portalId = getStringSetting(R.string.idPreferencePaymentPortalId, context);
        String m5Key = getStringSetting(R.string.idPreferencePaymentKey, context);

        request.setCredentials(portalId, mId, accountId, m5Key);
    }

    /**
     * Sets the mode of the given request depending on the user input.
     * @param activity - an InputActivity to provide access to the data that holds the user input.
     * @param request
     */
    private static void setRequestMode(InputActivity activity, PayoneRequestData request)
    {
        String key = activity.getString(R.string.idPreferencesInputRequestMode);
        boolean isLiveRequest = activity.getData().getBoolean(key, false);
        if (isLiveRequest)
        {
            request.setRequestMode(RequestMode.LIVE);
        }
        else
        {
            request.setRequestMode(RequestMode.TEST);
        }
    }

    //////////////////////////////////////////////////////////////////////////
    // MISC
    //////////////////////////////////////////////////////////////////////////

    /**
     * Tests if the given string is not null and contains at least one character that is not a space.
     * @param testString - the string to test
     * @return false if string is null or contains only spaces, true otherwise
     */
    public static boolean isStringValue(String testString)
    {
        return (testString != null && testString.replace(" ", "").length() > 0);
    }
}
