/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package com.exigen.ipb.confluence.client.snippet.impl;

import com.exigen.ipb.confluence.client.snippet.ConfluenceSnippet;
import com.google.common.base.Strings;

import static com.google.common.base.Preconditions.checkArgument;

/**
 * Confluence snippet generates confluence <ac:link> tag
 * with link to confluence page and some link title
 *
 * @autor esagan on 2/18/2016.
 * @version 1.0
 */
public class ConfluenceLinkSnippet implements ConfluenceSnippet {

    private static final String bodyTemplate = "<ac:link><ri:page ri:content-title=\"%s\" /><ac:plain-text-link-body><![CDATA[%s]]></ac:plain-text-link-body></ac:link>";

    private String linkTitle = null;
    private String linkUrl = null;

    public ConfluenceLinkSnippet(String linkTitle, String linkUrl) {
        checkArgument(!Strings.isNullOrEmpty(linkUrl));

        this.linkTitle = Strings.isNullOrEmpty(linkTitle) ? linkUrl : linkTitle;
        this.linkUrl = linkUrl;
    }

    @Override
    public String getBody() {
        return String.format(bodyTemplate,linkUrl,linkTitle);
    }
}
