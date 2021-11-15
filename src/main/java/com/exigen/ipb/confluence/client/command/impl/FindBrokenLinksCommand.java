/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package com.exigen.ipb.confluence.client.command.impl;

import com.exigen.ipb.confluence.client.command.CommandCtx;
import com.exigen.ipb.confluence.client.config.Configuration;
import com.exigen.ipb.confluence.client.impl.ConfluenceClient;
import com.exigen.ipb.confluence.client.impl.xmlrpc.XmlRpcConfluenceClient;
import com.exigen.ipb.confluence.client.model.Page;
import com.exigen.ipb.confluence.client.model.PageSummary;
import com.exigen.ipb.confluence.client.parser.ElementParser;
import com.exigen.ipb.confluence.client.parser.UrlResult;
import com.exigen.ipb.confluence.client.parser.confluence.HtmlATagElementParser;
import com.exigen.ipb.confluence.client.parser.confluence.RiAttachmentTagElementParser;
import com.exigen.ipb.confluence.client.parser.confluence.RiPageTagElementParser;
import com.exigen.ipb.confluence.client.util.ClientConstants;
import com.google.common.base.Predicate;
import com.google.common.collect.FluentIterable;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * FindBrockenLinks command is used to find all confluence page(s)
 * under some root page which has links to external or internal resources.
 * As root page for updates {@link Configuration#getConfluenceRootPageTitle()} value.
 *
 * @autor esagan
 * @version 1.0
 */
public class FindBrokenLinksCommand extends AbstractCommand<Map<String, List<UrlResult>>,CommandCtx> {

    private static final String AC_LINK_TAG_NAME = "ac:link";
    private static final String HTML_LINK_TAG_NAME = "a";

    /**
     * {@inheritDoc}
     */
    public FindBrokenLinksCommand(Configuration configuration) {
        super(configuration);
    }

    /**
     * {@inheritDoc}
     *
     * @return Map<String, List<UrlResult>>
     */
    @Override
    public Map<String, List<UrlResult>> execute() {

        XmlRpcConfluenceClient xmlRpcConfluenceClient = new XmlRpcConfluenceClient(configuration);
        xmlRpcConfluenceClient.login();

        Page confluencePage = xmlRpcConfluenceClient.getPageBySpaceAndTitle(configuration.getConfluenceDefaultSpace(), configuration.getConfluenceRootPageTitle());
        List<PageSummary> descendents = xmlRpcConfluenceClient.getDescendents(String.valueOf(confluencePage.getId()));
        Map<String, List<UrlResult>> links = new HashMap<>();

        for (PageSummary pageSummary : descendents) {
            Page pageById = xmlRpcConfluenceClient.getPageById(pageSummary.getId());
            Map<String, List<UrlResult>> brockenLinks = findBrockenLinks(pageById,xmlRpcConfluenceClient);
            for (Map.Entry<String, List<UrlResult>> e : brockenLinks.entrySet()) {
                String key = e.getKey();
                if (links.containsKey(key)) {
                    links.get(key).addAll(FluentIterable.from(e.getValue()).filter(new Predicate<UrlResult>() {
                        @Override
                        public boolean apply(UrlResult urlResult) {
                            return urlResult.getStatusCode() != ClientConstants.VALID_STATUS_CODE;
                        }
                    }).toImmutableList());
                } else {
                    links.put(key, (FluentIterable.from(e.getValue()).filter(new Predicate<UrlResult>() {
                        @Override
                        public boolean apply(UrlResult urlResult) {
                            return urlResult.getStatusCode() != ClientConstants.VALID_STATUS_CODE;
                        }
                    }).toImmutableList()));
                }
            }
        }
        xmlRpcConfluenceClient.logout();

        return links;

    }

    /**
     * Not supported by this command - throws UnsupportedOperationException
     *
     * @param input - input type
     *                in most cases it could be 'command context'
     *
     * @return Map<Page, List<String>>
     */
    @Override
    public Map<String, List<UrlResult>> execute(CommandCtx input) {
       throw new UnsupportedOperationException();
    }

    private Map<String, List<UrlResult>> findBrockenLinks(Page page, ConfluenceClient client){
        Map<String, List<UrlResult>> stringStringHashMap = new HashMap<>();
        String key = page.getSpace() + "+" + page.getTitle();
        List<UrlResult> urls = new ArrayList<>();
        stringStringHashMap.put(key, urls);

        populateHtmlATags(page, urls);
        populateAcLinkTags(page, client, urls);

        return stringStringHashMap;
    }

    private void populateAcLinkTags(Page page, ConfluenceClient client,  List<UrlResult> urls) {
        Document document = Jsoup.parse(page.getContent());

        Elements acLinks = document.getElementsByTag(AC_LINK_TAG_NAME);
        if (acLinks != null && acLinks.size() > 0) {
            for (Element a : acLinks) {
                for (Element child : a.children()) {
                    ElementParser riAttachmentTagElementParser = new RiAttachmentTagElementParser(page, client);
                    UrlResult attachmentResult = riAttachmentTagElementParser.parse(child);
                    if (attachmentResult != null){
                        urls.add(attachmentResult);
                    }
                    ElementParser riPageTagElementParser = new RiPageTagElementParser(page, client);
                    UrlResult pageResult = riPageTagElementParser.parse(child);
                    if (pageResult != null){
                        urls.add(pageResult);
                    }

                }

            }
        }
    }


    private void populateHtmlATags(Page page, List<UrlResult> urls) {
        Document document = Jsoup.parse(page.getContent());

        Elements htmlLinks = document.select(HTML_LINK_TAG_NAME);
        if (htmlLinks != null && htmlLinks.size() > 0) {
            for (Element a : htmlLinks) {
                ElementParser elementParser = new HtmlATagElementParser();
                UrlResult parsingResult = elementParser.parse(a);
                if (parsingResult != null) {
                    urls.add(parsingResult);
                }
            }
        }
    }

}
