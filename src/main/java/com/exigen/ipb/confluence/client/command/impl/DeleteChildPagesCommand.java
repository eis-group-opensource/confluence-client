/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package com.exigen.ipb.confluence.client.command.impl;

import com.exigen.ipb.confluence.client.command.CommandCtx;
import com.exigen.ipb.confluence.client.config.Configuration;
import com.exigen.ipb.confluence.client.exception.EisConfluenceException;
import com.exigen.ipb.confluence.client.model.Page;
import com.exigen.ipb.confluence.client.model.PageSummary;
import com.exigen.ipb.confluence.client.impl.xmlrpc.XmlRpcConfluenceClient;

import java.util.Iterator;
import java.util.List;

/**
 * DeleteChildPagesCommand deletes child pages of
 * provided confluence root page {@link Configuration#getConfluenceRootPageTitle()}
 *
 * @autor esagan on 2/4/2016.
 * @version 1.0
 */
public class DeleteChildPagesCommand extends AbstractCommand<Void, CommandCtx> {

    /**
     * {@inheritDoc}
     */
    public DeleteChildPagesCommand(Configuration configuration) {
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

        deleteDescendents(xmlRpcConfluenceClient, confluencePage);

        xmlRpcConfluenceClient.logout();
        return (null);
    }

    private void deleteDescendents(XmlRpcConfluenceClient xmlRpcConfluenceClient, Page confluencePage) {
        List<PageSummary> children = xmlRpcConfluenceClient.getDescendents(String.valueOf(confluencePage.getId()));
        Iterator<PageSummary> iterator = children.iterator();
        while (iterator.hasNext()) {
            xmlRpcConfluenceClient.removePage(String.valueOf(iterator.next().getId()));
        }
    }

    /**
     * This command delete pages with it's childs.
     * The list of pages should be provided in command context
     *
     * @param commandCtx - commandCtx type
     *                in most cases it could be 'command context'
     *
     * @return Void
     */
    @Override
    public Void execute(CommandCtx commandCtx) {
        List<String> listOfPages = commandCtx.getListOfPages();
        if (listOfPages.isEmpty()){
            commandCtx.getLogger().warn("There is no pages found for the command");
        }

        XmlRpcConfluenceClient xmlRpcConfluenceClient = new XmlRpcConfluenceClient(configuration);
        xmlRpcConfluenceClient.login();

        for (String pageName : listOfPages) {
            Page confluencePage = null;
            String pageToDelete = configuration.getConfluencePagePrefix() + " " + pageName;
            try {
                confluencePage = xmlRpcConfluenceClient.getPageBySpaceAndTitle(configuration.getConfluenceDefaultSpace(), pageToDelete);
            } catch (EisConfluenceException e){
                commandCtx.getLogger().warn("There is issue to get page " + pageToDelete +" to delete: " + e.getMessage());
            }
            if (confluencePage != null) {
                final String confluencePageTitle = confluencePage.getTitle();
                deleteDescendents(xmlRpcConfluenceClient, confluencePage);
                xmlRpcConfluenceClient.removePage(String.valueOf(confluencePage.getId()));
                commandCtx.getLogger().info("There page " + confluencePageTitle + " with childs is deleted");
            }
        }


        xmlRpcConfluenceClient.logout();
        return (null);


    }
}
