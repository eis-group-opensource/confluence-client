/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package com.exigen.ipb.confluence.client.parser.confluence;

import com.exigen.ipb.confluence.client.exception.EisConfluenceException;
import com.exigen.ipb.confluence.client.parser.ElementParser;
import com.exigen.ipb.confluence.client.parser.UrlResult;
import com.exigen.ipb.confluence.client.util.ClientConstants;
import com.google.common.base.Strings;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;

import java.net.UnknownHostException;

/**
 * <a> html tag parser
 *
 * @autor esagan
 * @version 1.0
 */
public class HtmlATagElementParser implements ElementParser {

    @Override
    public UrlResult parse(Element element) {
        if (element == null) {
            throw new EisConfluenceException("Element parameter for parsing cannot be null");
        }

        String href = element.attr("href");
        if (Strings.isNullOrEmpty(href)) {
            return new UrlResult(element.toString(), ClientConstants.INVALID_STATUS_CODE, "It was not possible to parse source - href attribute not found");
        }

        Connection connection = Jsoup.connect(href);
        try {
            Connection.Response execute = connection.execute();
            return new UrlResult(href, execute.statusCode(), execute.statusMessage());
        } catch (UnknownHostException e) {
            return new UrlResult(href, ClientConstants.INVALID_STATUS_CODE, e.toString());
        } catch (Exception e) {
            throw new EisConfluenceException("Unexpected exception is occured during validation of url: " + href, e);
        }
    }
}
