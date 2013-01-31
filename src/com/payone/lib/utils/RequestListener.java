/*
 * $Id$
 *
 * Copyright 2012 Exozet Games GmbH
 */
package com.payone.lib.utils;

import com.payone.lib.builder.ParameterCollection;

public abstract interface RequestListener
{
    /**
     * Call-Back method to be informed about the result.
     * @param result
     */
    public abstract void onRequestResult(ParameterCollection result);
}
