/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package com.exigen.ipb.confluence.client.snippet;

/**
 * Confluence snippets interface.
 * Implementations could be used for convertion/generation
 * part of confluence page content.
 *
 * @author esagan on 1/16/2016.
 * @verion 1.0
 */
public interface ConfluenceSnippet {
    /**
     *
     * @return String with part of confluence page content
     */
    String getBody();
}
