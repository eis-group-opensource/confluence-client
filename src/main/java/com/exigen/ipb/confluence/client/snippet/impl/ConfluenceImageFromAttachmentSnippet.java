/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package com.exigen.ipb.confluence.client.snippet.impl;

import com.exigen.ipb.confluence.client.snippet.ConfluenceSnippet;
import com.google.common.base.Strings;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Confluence snippet generates confluence <ac:image> tag
 * with attachment name and confluence page title information
 *
 * @autor esagan on 2/3/2016.
 * @version 1.0
 */
public class ConfluenceImageFromAttachmentSnippet implements ConfluenceSnippet{

    private static final String bodyTemplate  = "<ac:image><ri:attachment ri:filename=\"%s\"><ri:page ri:content-title=\"%s\" /></ri:attachment></ac:image>";

    private String attachmentName = null;

    private String pageTitleWithAttachment = null;

    public ConfluenceImageFromAttachmentSnippet(String attachmentName, String pageTitleWithAttachment) {
        checkArgument(!Strings.isNullOrEmpty(attachmentName));
        checkArgument(!Strings.isNullOrEmpty(pageTitleWithAttachment));
        this.attachmentName = attachmentName;
        this.pageTitleWithAttachment = pageTitleWithAttachment;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getBody() {
        return String.format(bodyTemplate,attachmentName,pageTitleWithAttachment);
    }
}
