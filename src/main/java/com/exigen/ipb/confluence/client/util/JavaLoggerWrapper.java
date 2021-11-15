/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package com.exigen.ipb.confluence.client.util;

import com.exigen.ipb.confluence.client.exception.EisConfluenceException;

import java.util.logging.Level;

/**
 * @autor esagan on 4/7/2016.
 */
public class JavaLoggerWrapper implements Logger {

    private java.util.logging.Logger logger;

    public JavaLoggerWrapper(java.util.logging.Logger logger) {
        if (logger == null) {
            throw new EisConfluenceException("Logger cannot be null");
        }
        this.logger = logger;
    }

    @Override
    public void warn(CharSequence var1) {
        logger.log(Level.WARNING,var1.toString());
    }

    @Override
    public void warn(CharSequence var1, Throwable var2) {
        logger.log(Level.WARNING,var1.toString(),var2);
    }

    @Override
    public void warn(Throwable var1) {
        warn("Warning.",var1);
    }

    @Override
    public void info(CharSequence var1) {
        logger.log(Level.INFO,var1.toString());
    }

    @Override
    public void info(CharSequence var1, Throwable var2) {
        logger.log(Level.INFO,var1.toString(),var2);
    }

    @Override
    public void info(Throwable var1) {
        info ("Info.",var1);
    }
}
