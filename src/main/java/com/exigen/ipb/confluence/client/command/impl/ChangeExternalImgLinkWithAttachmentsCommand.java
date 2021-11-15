/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package com.exigen.ipb.confluence.client.command.impl;

import com.exigen.ipb.confluence.client.command.Command;
import com.exigen.ipb.confluence.client.command.CommandCtx;
import com.exigen.ipb.confluence.client.command.CommandFactory;
import com.exigen.ipb.confluence.client.command.CommandUtils;
import com.exigen.ipb.confluence.client.config.Configuration;
import com.exigen.ipb.confluence.client.impl.xmlrpc.XmlRpcConfluenceClient;
import com.exigen.ipb.confluence.client.model.Attachment;
import com.exigen.ipb.confluence.client.model.Page;
import com.exigen.ipb.confluence.client.snippet.impl.ConfluenceImageFromAttachmentSnippet;
import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.util.List;
import java.util.Map;

/**
 * If confluence pages were created by copy/paste,
 * this ChangeExternalImgLinkWithAttachmentsCommand can replace external link with attachment from {@link Configuration#getConfluenceRootPageTitle()} page,
 * in case such attachment with the same name exists.
 *
 * @version 1.0
 * @autor esagan on 2/4/2016.
 */
public class ChangeExternalImgLinkWithAttachmentsCommand extends AbstractCommand<Void, CommandCtx> {

    /**
     * {@inheritDoc}
     */
    public ChangeExternalImgLinkWithAttachmentsCommand(Configuration configuration) {
        super(configuration);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Void execute() {

        Command<Map<Page, List<String>>, Void> findExternalLinksCommand = CommandFactory.getFindExternalLinksCommand(configuration);
        Map<Page, List<String>> map = findExternalLinksCommand.execute();
        if (map != null && !map.isEmpty()) {
            XmlRpcConfluenceClient xmlRpcConfluenceClient = new XmlRpcConfluenceClient(configuration);
            xmlRpcConfluenceClient.login();
            Page rootPage = xmlRpcConfluenceClient.getPageBySpaceAndTitle(configuration.getConfluenceDefaultSpace(), configuration.getConfluenceRootPageTitle());
            List<Attachment> attachments = xmlRpcConfluenceClient.getAttachments(String.valueOf(rootPage.getId()));
            for (Map.Entry<Page, List<String>> e : map.entrySet()) {
                replaceExternalImageWithAttachment(xmlRpcConfluenceClient, configuration, e.getKey(), rootPage, attachments);
            }
            xmlRpcConfluenceClient.logout();
        }
        return (null);
    }

    /**
     * Not supported by this command - throws UnsupportedOperationException
     *
     * @param input - input type
     *              in most cases it could be 'command context'
     * @return Void
     */
    @Override
    public Void execute(CommandCtx input) {
        throw new UnsupportedOperationException();
    }


    private static void replaceExternalImageWithAttachment(XmlRpcConfluenceClient xmlRpcConfluenceClient, Configuration configuration, Page pageWithLink, Page pageWithAttachments, List<Attachment> attachments) {
        boolean pageHasExternalResource = CommandUtils.isPageHasExternalResource(pageWithLink);
        if (pageHasExternalResource) {
            boolean updated = false;

            Document document = Jsoup.parseBodyFragment(pageWithLink.getContent());

            for (Element element : document.getElementsByTag(CommandUtils.CONFLUENCE_IMAGE_SELECTOR)) {
                String attr = element.child(0).attr(CommandUtils.CONFLUENCE_IMAGE_VALIE_SELECTOR);
                if (attr.startsWith(configuration.getConfluenceServerURI())) {
                    continue;
                }

                String[] split = attr.split("/");

                if (split == null || split.length == 0) {
                    continue;
                }

                Attachment attachementByFileName = findAttachementByFileName(split[split.length - 1], attachments);
                if (attachementByFileName != null) {
                    updated = true;
                    element.html(new ConfluenceImageFromAttachmentSnippet(attachementByFileName.getFileName(), pageWithAttachments.getTitle()).getBody());
                }
            }
            if (updated) {
                pageWithLink.setContent(document.getElementsByTag(CommandUtils.HTML_BODY_SELECTOR).html());
                xmlRpcConfluenceClient.storePage(pageWithLink);
            }
        }
    }

    private static Attachment findAttachementByFileName(final String fileName, List<Attachment> attachments) {
        Iterable<Attachment> filter = Iterables.filter(attachments, new Predicate<Attachment>() {
            @Override
            public boolean apply(Attachment attachment) {
                return attachment.getFileName().equals(fileName);
            }
        });

        if (filter != null && filter.iterator().hasNext()) {
            return filter.iterator().next();
        }
        return null;
    }
}
