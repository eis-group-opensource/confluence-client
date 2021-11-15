/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package com.exigen.ipb.confluence.client.snippet.impl;

import com.exigen.ipb.confluence.client.snippet.ConfluenceSnippet;

/**
 * Confluence snippet whic generates confluence html tag marco
 * @deprecated it's deprecated, because of it's disabled by default.
 * @see <a href="http://confluence.atlassian.com/doc/html-macro-38273085.html">Html Macro</a>
 *
 * @author esagan on 1/16/2016.
 * @version 1.0
 */
@Deprecated
public class ConfluenceHtmlSnippet implements ConfluenceSnippet{

    /**
     * {@inheritDoc}
     */
    @Override
    public String getBody() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("<ac:structured-macro ac:name = \"html\">");
        stringBuilder.append("<ac:plain-text-body> <![CDATA[<a href=\"http://www.atlassian.com\">Click here</a> to see the <b>Atlassian</b> website.]]> </ac:plain-text-body>");
        stringBuilder.append("</ac:structured-macro>");
        return stringBuilder.toString();
    }
}
