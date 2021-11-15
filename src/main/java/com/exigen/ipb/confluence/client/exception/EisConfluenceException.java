/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package com.exigen.ipb.confluence.client.exception;

/**
 * Client exception class to wrap exceptions
 * which could be raised becuase of diffrent reasons
 * during commands {@link com.exigen.ipb.confluence.client.command.Command} invocations and wrap them with this one.
 *
 * @author esagan on 1/16/2016.
 * @version 1.0
 */
public class EisConfluenceException extends RuntimeException {
    public EisConfluenceException() {
    }

    public EisConfluenceException(String message) {
        super(message);
    }

    public EisConfluenceException(String message, Throwable cause) {
        super(message, cause);
    }

    public EisConfluenceException(Throwable cause) {
        super(cause);
    }

    public EisConfluenceException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
