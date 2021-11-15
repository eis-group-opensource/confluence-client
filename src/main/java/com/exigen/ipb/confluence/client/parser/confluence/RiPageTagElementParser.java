/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package com.exigen.ipb.confluence.client.parser.confluence;

import com.exigen.ipb.confluence.client.exception.EisConfluenceException;
import com.exigen.ipb.confluence.client.impl.ConfluenceClient;
import com.exigen.ipb.confluence.client.model.Page;
import com.exigen.ipb.confluence.client.parser.ElementParser;
import com.exigen.ipb.confluence.client.parser.UrlResult;
import com.exigen.ipb.confluence.client.util.ClientConstants;
import com.google.common.base.Strings;
import org.jsoup.nodes.Element;

/**
 * <ri:page> confluence tag parser
 *
 * @autor esagan
 * @version 1.0
 */
public class RiPageTagElementParser  implements ElementParser {

    private static final String ELEMENT_TAG = "ri:page";

    private Page page;
    private ConfluenceClient client;

    public RiPageTagElementParser(Page page, ConfluenceClient client) {
        this.page = page;
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

        String contentTitle = element.attr(CONTENT_TITLE_ELEMENT_ATTRIBUTE);
        if (Strings.isNullOrEmpty(contentTitle)) {
            return new UrlResult(element.toString(), ClientConstants.INVALID_STATUS_CODE, "It was not possible to parse source - ri:content-title attribute not found");
        }
        String spaceKey = element.attr(SPACE_KEY_ELEMENT_ATTRIBUTE);
        //if element doesn't contain exact information about space - parsed page space will be used
        if (Strings.isNullOrEmpty(spaceKey)) {
            spaceKey = page.getSpace();
        }
        try {
            Page pageBySpaceAndTitle = client.getPageBySpaceAndTitle(spaceKey, contentTitle);
            return new UrlResult(String.valueOf(pageBySpaceAndTitle.getId()),ClientConstants.VALID_STATUS_CODE, ClientConstants.EMPTY);
        } catch (EisConfluenceException e) {
            return new UrlResult(element.toString(), ClientConstants.INVALID_STATUS_CODE, "Provided confluence page is not found or no permissions to receive it");
        }

    }
}
