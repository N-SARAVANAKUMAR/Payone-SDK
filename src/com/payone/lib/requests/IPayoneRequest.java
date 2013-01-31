/*
 * $Id$
 *
 * Copyright 2012 Exozet Games GmbH
 */
package com.payone.lib.requests;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;

import android.os.AsyncTask;
import android.util.Log;

import com.payone.lib.builder.ParameterCollection;
import com.payone.lib.parameter.PayoneParameters;
import com.payone.lib.utils.RequestListener;
import com.payone.lib.utils.Utils;

public abstract class IPayoneRequest
{
    private static final String TAG = IPayoneRequest.class.getName();
    
    //region statics
    private static String TARGET_URL = "https://secure.pay1.de/client-api/";

    private static int USERCANCELLED_ERRORCODE = 9000;
    private static int INTERNALERROR_ERRORCODE = 9001;
    private static int ARGUMENTINVALID_ERRORCODE = 9002;
    private static int ARGUMENTCONVERSION_ERRORCODE = 9003;
    private static int REQUESTHANDLING_ERRORCODE = 9004;
    private static int REQUESTRESPONSEHANDLING_ERRORCODE = 9005;

    //region Properties
    protected HttpPost mHttpRequest = null;
    protected boolean mIsRunning = false;
    protected RequestListener mResponseAction = null;

    private String mKey;
    private ParameterCollection mParameterToDeliver;
    ParameterCollection mErrorParameter;

    public IPayoneRequest()        
    {
        mHttpRequest = null;
        mParameterToDeliver = null;
        mErrorParameter = null;
        mResponseAction = null;
        mIsRunning = false;
        mKey = null;
    }

    public void setKey(String key)
    {
        mKey = key;
    }
    
    public String getKey()
    {
        return mKey;
    }
    
    public void setParameterToDeliver(ParameterCollection parameterCollection)
    {
        mParameterToDeliver = parameterCollection;
    }
    
    public ParameterCollection getParameterCollection()
    {
        return mParameterToDeliver;
    }
    
    //region abstract methods
    protected abstract ParameterCollection convertResponseToCollection(String response);
    
    public void RunASync(RequestListener responseCallback) throws Exception
    {
        if (responseCallback == null)
            throw new Exception("Callback argument is invalid or empty!");

        if (mParameterToDeliver == null || mParameterToDeliver.getCount() == 0)
            throw new Exception("Request parameter-collection is empty or not specified");

        if(Utils.isNullOrEmpty(mKey))
            throw new Exception("Given Key property is null or empty");

        if (mIsRunning)
            throw new Exception("Request is already running!");
        
        mResponseAction = responseCallback;
        
        new DoHttpRequest().execute();
    }
    
    protected class DoHttpRequest extends AsyncTask<Void, Void, String>
    {
        @Override
        protected String doInBackground(Void... arg0)
        {
            mIsRunning = true;
            
            try
            {
                String params = mParameterToDeliver.toPostBodyParameter(mKey);
                
                if(Utils.isNullOrEmpty(params))
                {
                    createSDKError(ARGUMENTINVALID_ERRORCODE, "The given parameters are empty.");
                }
                
                HttpClient client = new DefaultHttpClient();
                mHttpRequest = new HttpPost(TARGET_URL);
                mHttpRequest.addHeader("Content-Type", "application/x-www-form-urlencoded");
                mHttpRequest.setEntity(new StringEntity(params));
                Log.d(TAG, "processing..." + params);
                
                HttpResponse response = client.execute(mHttpRequest);
                HttpEntity entity = response.getEntity();
                
                BufferedReader reader = new BufferedReader(new InputStreamReader(entity.getContent()));
                
                StringBuilder stringBuilder = new StringBuilder();
                String line = null;

                while ((line = reader.readLine()) != null)
                {
                    stringBuilder.append(line + "\n");
                }
                
                if(Utils.isNullOrEmpty(stringBuilder.toString()))
                {
                    createSDKError(ARGUMENTCONVERSION_ERRORCODE, "The request stream was empty.");
                }
                else
                {
                    if (stringBuilder.toString().contains("<html>"))
                    {
                        // TODO: what shall we do here!?
                        createSDKError(REQUESTRESPONSEHANDLING_ERRORCODE, "REDIRECT parameter is currently not supported.");
                        return null;
                    }
                }
                
                return stringBuilder.toString();
            }
            catch (ClientProtocolException e)
            {
                createSDKError(REQUESTHANDLING_ERRORCODE, "Client Protocol Error.");
            }
            catch (IOException e)
            {
                createSDKError(INTERNALERROR_ERRORCODE, "IO Error.");
            }
            catch (ParseException e1)
            {
                createSDKError(REQUESTHANDLING_ERRORCODE, "Parse Error.");
            }
            
            return null;
        }
        
        @Override
        protected void onCancelled()
        {
            super.onCancelled();
            
            createSDKError(USERCANCELLED_ERRORCODE, "Request cancelled by user.");
        }
        
        @Override
        protected void onPostExecute(String result)
        {
            super.onPostExecute(result);
            mIsRunning = false;
            
            if(!Utils.isNullOrEmpty(result))
            {
                ParameterCollection responseAsCollection = convertResponseToCollection(result);
                mResponseAction.onRequestResult(responseAsCollection);
            }
            else
            {
                mResponseAction.onRequestResult(mErrorParameter);
            }
        }
    }
    
    /// <summary>
    /// Creates an error response
    /// </summary>
    /// <param name="errorCode">internal error code.</param>
    /// <param name="reason">the reason of this error occurrency.</param>
    /// <param name="exception">Possible exception information - can be null.</param>
    private void createSDKError(int errorCode, String reason)
    {            
        mIsRunning = false;

        mErrorParameter = new ParameterCollection();
        mErrorParameter.put(PayoneParameters.STATUS, PayoneParameters.ResponseErrorCodes.ERROR);
        mErrorParameter.put(PayoneParameters.ERRORCODE, new Integer(errorCode).toString());
        mErrorParameter.put(PayoneParameters.ERRORMESSAGE, reason);
    }
}
