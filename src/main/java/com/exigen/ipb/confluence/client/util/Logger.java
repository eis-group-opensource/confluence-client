/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package com.exigen.ipb.confluence.client.util;

/**
 * @autor esagan on 4/7/2016.
 */
public interface Logger {

    void warn(CharSequence var1);

    void warn(CharSequence var1, Throwable var2);

    void warn(Throwable var1);

    void info(CharSequence var1);

    void info(CharSequence var1, Throwable var2);

    void info(Throwable var1);
}
