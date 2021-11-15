/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package com.exigen.ipb.confluence.client.parser;

import org.jsoup.nodes.Element;

/**
 * Base interface for confluence page element parsing
 *
 * @autor esagan
 * @version 1.0
 */
public interface ElementParser {

    String CONTENT_TITLE_ELEMENT_ATTRIBUTE = "ri:content-title";
    String SPACE_KEY_ELEMENT_ATTRIBUTE = "ri:space-key";

    UrlResult parse(Element element);

}
