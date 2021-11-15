/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package com.exigen.ipb.confluence.client.command.impl;

import com.exigen.ipb.confluence.client.command.CommandCtx;
import com.exigen.ipb.confluence.client.config.Configuration;
import com.exigen.ipb.confluence.client.command.CommandFactory;
import com.exigen.ipb.confluence.client.model.Page;
import com.exigen.ipb.confluence.client.parser.JSoupParser;
import com.exigen.ipb.confluence.client.impl.xmlrpc.XmlRpcConfluenceClient;
import org.apache.commons.collections.CollectionUtils;

/**
 * GenerateReferencesCommand is used to generate confluence pages under
 * provided root page {@link Configuration#getConfluenceRootPageTitle()}
 * from provided html file {@link Configuration#getInputRootFile()} which
 * should exists in folder {@link Configuration#getInputRootFolder()} on local drive
 *
 * @autor esagan on 2/4/2016.
 * @version 1.0
 */
public class GenerateReferencesCommand extends AbstractCommand<Void,CommandCtx> {

    /**
     * {@inheritDoc}
     */
    public GenerateReferencesCommand(Configuration configuration) {
        super(configuration);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Void execute() {
        //first attempt to parse file to build new structure
        JSoupParser jSoupParser = new JSoupParser(configuration);
        com.exigen.ipb.confluence.client.parser.model.Page parsedPage = jSoupParser.parseStructure();

        XmlRpcConfluenceClient xmlRpcConfluenceClient = new XmlRpcConfluenceClient(configuration);
        xmlRpcConfluenceClient.login();

        Page confluencePage = xmlRpcConfluenceClient.getPageBySpaceAndTitle(configuration.getConfluenceDefaultSpace(), configuration.getConfluenceRootPageTitle());

        populatePageTreeOnConfluence(xmlRpcConfluenceClient, parsedPage, confluencePage, false);

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

    private void populatePageTreeOnConfluence(XmlRpcConfluenceClient xmlRpcConfluenceClient, com.exigen.ipb.confluence.client.parser.model.Page parsedPage, Page confluencePage, boolean populate) {
        Page currentPage;
        if (parsedPage == null) {
            return;
        }

        if (populate) {
            currentPage = new Page();
            currentPage.setSpace(confluencePage.getSpace());
            currentPage.setParentId(confluencePage.getId());
            currentPage.setTitle(parsedPage.getTitle());
            currentPage.setContent(parsedPage.getContent());
            currentPage = xmlRpcConfluenceClient.storePage(currentPage);
        } else {
            currentPage = confluencePage;
        }

        if (currentPage != null && !CollectionUtils.isEmpty(parsedPage.getChilds())) {
            for (com.exigen.ipb.confluence.client.parser.model.Page childPage : parsedPage.getChilds()) {
                populatePageTreeOnConfluence(xmlRpcConfluenceClient, childPage, currentPage, true);
            }
        }

    }
}
