/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package com.exigen.ipb.confluence.client.command.impl;

import com.exigen.ipb.confluence.client.command.CommandCtx;
import com.exigen.ipb.confluence.client.config.Configuration;
import com.exigen.ipb.confluence.client.command.CommandUtils;
import com.exigen.ipb.confluence.client.model.Page;
import com.exigen.ipb.confluence.client.model.PageSummary;
import com.exigen.ipb.confluence.client.impl.xmlrpc.XmlRpcConfluenceClient;
import com.google.common.base.Strings;
import com.google.common.collect.Maps;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * FindLinksToExternalResources command is used to find all confluence page(s)
 * under some root page which has links to external resources.
 * As root page for updates {@link Configuration#getConfluenceRootPageTitle()} value.
 *
 * @autor esagan on 2/4/2016.
 * @version 1.0
 */
public class FindExternalLinksCommand extends AbstractCommand<Map<Page, List<String>>,CommandCtx> {

    /**
     * {@inheritDoc}
     */
    public FindExternalLinksCommand(Configuration configuration) {
        super(configuration);
    }

    /**
     * {@inheritDoc}
     *
     * @return Map<Page, List<String>>
     */
    @Override
    public Map<Page, List<String>> execute() {
        XmlRpcConfluenceClient xmlRpcConfluenceClient = new XmlRpcConfluenceClient(configuration);
        xmlRpcConfluenceClient.login();

        Page confluencePage = xmlRpcConfluenceClient.getPageBySpaceAndTitle(configuration.getConfluenceDefaultSpace(), configuration.getConfluenceRootPageTitle());
        List<PageSummary> descendents = xmlRpcConfluenceClient.getDescendents(String.valueOf(confluencePage.getId()));
        Map<Page, List<String>> map = Maps.newHashMap();
        for (PageSummary pageSummary : descendents) {
            Page pageById = xmlRpcConfluenceClient.getPageById(pageSummary.getId());
            collectPageDataWithExtrernalResources(map, configuration, pageById);
        }
        xmlRpcConfluenceClient.logout();
        return map;
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
    public Map<Page, List<String>> execute(CommandCtx input) {
        throw new UnsupportedOperationException();
    }

    private void collectPageDataWithExtrernalResources(Map<Page, List<String>> map, Configuration configuration, Page confluencePage) {
        boolean pageHasExternalResource = CommandUtils.isPageHasExternalResource(confluencePage);
        if (pageHasExternalResource) {
            Document document = Jsoup.parseBodyFragment(confluencePage.getContent());
            List<String> links = new ArrayList<>();

            for (Element element : document.select("[" + CommandUtils.HTML_HREF_SELECTOR + "]")) {
                String href = element.attr(CommandUtils.HTML_HREF_SELECTOR);
                if (!Strings.isNullOrEmpty(href) && !href.startsWith(configuration.getConfluenceServerURI())) {
                    links.add(href);
                }
            }

            for (Element element : document.select("[" + CommandUtils.HTML_MEDIA_SELECTOR + "]")) {
                String src = element.attr(CommandUtils.HTML_MEDIA_SELECTOR_PREFIX + CommandUtils.HTML_HREF_SELECTOR);
                if (!Strings.isNullOrEmpty(src) && !src.startsWith(configuration.getConfluenceServerURI())) {
                    links.add(src);
                }
            }

            for (Element element : document.getElementsByTag(CommandUtils.CONFLUENCE_IMAGE_SELECTOR)) {
                String attr = element.child(0).attr(CommandUtils.CONFLUENCE_IMAGE_VALIE_SELECTOR);
                if (!Strings.isNullOrEmpty(attr) && !attr.startsWith(configuration.getConfluenceServerURI())) {
                    links.add(attr);
                }
            }

            if (links.size() > 0) {
                map.put(confluencePage, links);
            }
        }
    }

}
