/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package com.exigen.ipb.confluence.client.command.impl;

import com.exigen.ipb.confluence.client.command.CommandCtx;
import com.exigen.ipb.confluence.client.config.Configuration;
import com.exigen.ipb.confluence.client.model.Page;
import com.exigen.ipb.confluence.client.model.PageSummary;
import com.exigen.ipb.confluence.client.impl.xmlrpc.XmlRpcConfluenceClient;

import java.util.List;

/**
 * UpdatePageTitlesWithPrefixCommand is used to update confluence page(s)
 * with some prefix.
 * As root page for updates used - configuration.getConfluenceRootPageTitle() value;
 * as prefix - configuration.getConfluencePagePrefix()
 * method updates only pages with doesn't have such prefix
 *
 * @autor esagan on 2/4/2016.
 * @version 1.0
 */
public class UpdatePageTitlesWithPrefixCommand extends AbstractCommand<Void,CommandCtx> {

    /**
     * {@inheritDoc}
     */
    public UpdatePageTitlesWithPrefixCommand(Configuration configuration) {
        super(configuration);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Void execute() {
        XmlRpcConfluenceClient xmlRpcConfluenceClient = new XmlRpcConfluenceClient(configuration);
        xmlRpcConfluenceClient.login();

        Page confluencePage = xmlRpcConfluenceClient.getPageBySpaceAndTitle(configuration.getConfluenceDefaultSpace(), configuration.getConfluenceRootPageTitle());
        List<PageSummary> descendents = xmlRpcConfluenceClient.getDescendents(String.valueOf(confluencePage.getId()));
        for (PageSummary pageSummary : descendents) {
            if (!pageSummary.getTitle().startsWith(configuration.getConfluencePagePrefix())) {
                Page pageById = xmlRpcConfluenceClient.getPageById(pageSummary.getId());
                pageById.setTitle(configuration.getConfluencePagePrefix() + " " + pageById.getTitle());
                xmlRpcConfluenceClient.storePage(pageById);
            }
        }

        xmlRpcConfluenceClient.logout();
        return (null);
    }

    /**
     * Not supported by this command - throws UnsupportedOperationException
     *
     * @param input - input type
     *                in most cases it could be 'command context'
     *
     * @return Void
     */
    @Override
    public Void execute(CommandCtx input) {
      throw new UnsupportedOperationException();
    }
}
