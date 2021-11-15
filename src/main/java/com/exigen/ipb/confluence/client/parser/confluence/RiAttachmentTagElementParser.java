/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package com.exigen.ipb.confluence.client.parser.confluence;

import com.exigen.ipb.confluence.client.exception.EisConfluenceException;
import com.exigen.ipb.confluence.client.impl.ConfluenceClient;
import com.exigen.ipb.confluence.client.model.Attachment;
import com.exigen.ipb.confluence.client.model.Page;
import com.exigen.ipb.confluence.client.parser.ElementParser;
import com.exigen.ipb.confluence.client.parser.UrlResult;
import com.exigen.ipb.confluence.client.util.ClientConstants;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * <ri:attachment> confluence tag parser
 *
 * @autor esagan
 * @verion 1.0
 */
public class RiAttachmentTagElementParser implements ElementParser {

    private static final String ELEMENT_TAG = "ri:attachment";
    private static final String FILENAME_ELEMENT_ATTRIBUTE = "ri:filename";
    private static final String CHILD_ELEMENT_TAG = "ri:page";
    private static final String CURRENT_ATTACHMENT_VERSION = "0";

    private Page parsedPage;
    private ConfluenceClient client;

    public RiAttachmentTagElementParser(Page parsedPage, ConfluenceClient client) {
        if (parsedPage == null || client == null) {
            throw new EisConfluenceException("RiAttachmentTagElementParser: parsedPage or client parameters cannot be null");
        }
        this.parsedPage = parsedPage;
        this.client = client;
    }

    @Override
    public UrlResult parse(Element element) {
        if (element == null) {
            throw new EisConfluenceException("Element parameter for parsing cannot be null");
        }

        if (!ELEMENT_TAG.equalsIgnoreCase(element.tagName())) {
            return null;
        }

        long idOfPageWithAttachemnts = parsedPage.getId();

        //if attachment linked to the different page - find this page
        Elements childElemnts = element.getElementsByTag(CHILD_ELEMENT_TAG);
        if (childElemnts != null && childElemnts.size() > 0) {

            RiPageTagElementParser riPageTagElementParser = new RiPageTagElementParser(parsedPage, client);
            UrlResult parsingResult = riPageTagElementParser.parse(childElemnts.get(0));
            if (parsingResult != null) {
                if (ClientConstants.VALID_STATUS_CODE == parsingResult.getStatusCode()) {
                    idOfPageWithAttachemnts = Long.valueOf(parsingResult.getUri());
                } else {
                    return parsingResult;
                }
            }
        }
        String attr = element.attr(FILENAME_ELEMENT_ATTRIBUTE);
        if (attr != null) {
            try {
                Attachment attachment = client.getAttachment(String.valueOf(idOfPageWithAttachemnts), attr, CURRENT_ATTACHMENT_VERSION);
                if (attachment != null) {
                    return new UrlResult(element.toString(), ClientConstants.VALID_STATUS_CODE, ClientConstants.EMPTY);
                } else {
                    return new UrlResult(element.toString(), ClientConstants.INVALID_STATUS_CODE, "Attachment is not found");
                }
            } catch (EisConfluenceException e) {
                return new UrlResult(element.toString(), ClientConstants.INVALID_STATUS_CODE, "Attachment is not found or no permissions to receive it");
            }

        }
        return null;
    }

}
