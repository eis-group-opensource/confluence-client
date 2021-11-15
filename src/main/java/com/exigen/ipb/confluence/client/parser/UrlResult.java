/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package com.exigen.ipb.confluence.client.parser;

/**
 * UrlResult - class contains results of confluence content
 * which has links to the internal or external resources
 *
 * @autor esagan on 3/22/2016.
 * @version 1.0
 */
public class UrlResult {

    private String uri;
    private int statusCode;
    private String statusMessage;

    public UrlResult(String uri, int statusCode, String statusMessage) {
        this.uri = uri;
        this.statusCode = statusCode;
        this.statusMessage = statusMessage;
    }

    public String getUri() {
        return uri;
    }

    public int getStatusCode() {
        return statusCode;
    }


    public String getStatusMessage() {
        return statusMessage;
    }

    @Override
    public String toString() {
        return "UrlResult{" +
                "uri='" + uri + '\'' +
                ", statusCode=" + statusCode +
                ", statusMessage='" + statusMessage + '\'' +
                '}';
    }

}
