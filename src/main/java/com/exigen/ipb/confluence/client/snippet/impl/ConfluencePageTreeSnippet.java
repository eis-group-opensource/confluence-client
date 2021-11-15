/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package com.exigen.ipb.confluence.client.snippet.impl;

import com.exigen.ipb.confluence.client.snippet.ConfluenceSnippet;

/**
 * Stub confluence page tree snippet
 *
 * @author esagan on 1/16/2016.
 * @version 1.0
 */
public class ConfluencePageTreeSnippet implements ConfluenceSnippet {

    /**
     * {@inheritDoc}
     */
    @Override
    public String getBody(){
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("<ac:structured-macro ac:name=\"pagetree\">");
        stringBuilder.append("<ac:parameter ac:name=\"reverse\">false</ac:parameter>");

        stringBuilder.append(" <ac:parameter ac:name=\"sort\">natural</ac:parameter>");
        stringBuilder.append(" <ac:parameter ac:name=\"root\">");
        stringBuilder.append(" <ac:link>");
        stringBuilder.append("<ri:page ri:content-title=\"Base Subsystems\"/>");
        stringBuilder.append("</ac:link>");
        stringBuilder.append("</ac:parameter>");
        stringBuilder.append("<ac:parameter ac:name=\"startDepth\">3</ac:parameter>");
        stringBuilder.append("<ac:parameter ac:name=\"excerpt\">true</ac:parameter>");
        stringBuilder.append("<ac:parameter ac:name=\"searchBox\">true</ac:parameter>");
        stringBuilder.append("<ac:parameter ac:name=\"expandCollapseAll\">true</ac:parameter>");
        stringBuilder.append("</ac:structured-macro>");
        return stringBuilder.toString();
    }
}
