/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package com.exigen.ipb.confluence.client.util;

import com.exigen.ipb.confluence.client.exception.EisConfluenceException;
import org.apache.maven.plugin.logging.Log;

/**
 * @autor esagan on 4/7/2016.
 * @since 1.0
 */
public class MavenLoggerWrapper implements Logger {

    private Log log;


    public MavenLoggerWrapper(Log log) {
        if (log == null) {
            throw new EisConfluenceException("Log parameter cannot be null");
        }
        this.log = log;
    }

    @Override
    public void warn(CharSequence var1) {
        log.warn(var1);
    }

    @Override
    public void warn(CharSequence var1, Throwable var2) {
        log.warn(var1,var2);
    }

    @Override
    public void warn(Throwable var1) {
        log.warn(var1);
    }

    @Override
    public void info(CharSequence var1) {
        log.info(var1);
    }

    @Override
    public void info(CharSequence var1, Throwable var2) {
        log.info(var1,var2);
    }

    @Override
    public void info(Throwable var1) {
        log.info(var1);
    }
}
