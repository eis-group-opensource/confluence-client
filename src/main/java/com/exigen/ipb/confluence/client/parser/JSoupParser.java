/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package com.exigen.ipb.confluence.client.parser;

import com.exigen.ipb.confluence.client.command.CommandUtils;
import com.exigen.ipb.confluence.client.config.Configuration;
import com.exigen.ipb.confluence.client.impl.xmlrpc.utils.ObjectUtils;
import com.exigen.ipb.confluence.client.parser.model.Page;
import com.exigen.ipb.confluence.client.snippet.impl.ConfluenceLinkSnippet;
import com.google.common.base.Strings;
import org.apache.commons.collections.CollectionUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.TextNode;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

/**
 * Wrapper class over jsoup framework to load or parse input information
 * to populate it on confluence
 *
 * @author  esagan on 1/16/2016.
 * @version 1.0
 */
public class JSoupParser {

    private static final String XHTML_DOCS = "XhtmlDocs";

    private final JSoupDocumentLoader loader;
    private final Configuration configuration;

    private List<String> includeLinks = new ArrayList<>();

    public JSoupParser(Configuration configuration){
        this.configuration = configuration;
        this.loader = new JSoupDocumentLoader(configuration.getInputRootFolder(),configuration.getInputFileEncoding());
        this.includeLinks =configuration.getInputInclusiveLinks();
    }

    public Page parseStructure(){
        return parseStructure(configuration.getInputRootFile(),configuration.getConfluencePagePrefix());
    }

    /*
     * TODO: refactor code to remove hardcoded logic and
     * support different content adoptions
     *
     */
    public Page parseStructure (String fileName, String subSystemName){
        Document root = loader.loadDocument(fileName);
        Page rootPage = new Page();
        rootPage.setTitle(subSystemName + " " + "References");
        for (Element element : root.select("h5:contains(References) ~ ul > li > a")) {
            String title = element.attr("title");
            if (CollectionUtils.isEmpty(includeLinks) || includeLinks.contains(title)) {
                Page page = new Page();
                page.setTitle(subSystemName + " " + title);
                if (!element.attr(CommandUtils.HTML_HREF_SELECTOR).contains("http")) {
                    if (XHTML_DOCS.equals(title)){
                        parseDocument(page, element.attr(CommandUtils.HTML_HREF_SELECTOR), subSystemName);
                    } else {
                        parseDocument(page, element.attr(CommandUtils.HTML_HREF_SELECTOR));
                    }
                }
                rootPage.addChild(page);
            }
        }
        return rootPage;
    }

    /*
     * TODO: refactor code to remove hardcoded logic and
     * support different content adoptions
     *
     */
    private void parseDocument(Page page, String fileName, String subSystemName){
        Document childDocument = loader.loadDocument(fileName);
        Elements select = childDocument.select("[name=pagesListFrame]");
        if (select != null && select.size() > 0) {
            parseDocument(page, select.attr(CommandUtils.HTML_MEDIA_SELECTOR),subSystemName);
        } else {

            select = childDocument.select("ul > li > a");
            if (select != null && select.size() > 0) {
                for (Element e : select) {
                    Page childPage = new Page();
                    if (Strings.isNullOrEmpty(e.text())){
                        childPage.setTitle(subSystemName + " " + e.attr(CommandUtils.HTML_HREF_SELECTOR).replace("_", "/").replace(".html",".xhtml"));
                    } else {
                        childPage.setTitle(subSystemName + " " + e.text().replace("_", "/").replace(".html",".xhtml"));
                    }
                    page.addChild(childPage);
                    parseDocument1(childPage, e.attr(CommandUtils.HTML_HREF_SELECTOR), subSystemName);
                }
            }
        }
    }

    private void parseDocument1(Page page, String fileName, String subSystemName){
        Document childDocument = loader.loadDocument(fileName);
        Elements select = childDocument.select(CommandUtils.HTML_BODY_SELECTOR);
        if (select != null && select.size() > 0) {
            page.setContent(prepareContentForConfluence(childDocument, subSystemName));
        }
    }

    /*
     * TODO: refactor code to remove hardcoded logic and
     * support different content adoptions
     *
     */
    private String prepareContentForConfluence(Document document, String subSystemName){
        Elements elements = document.select(CommandUtils.HTML_BODY_SELECTOR);
        if (elements != null && elements.size() > 0) {
            Elements alinks = elements.select("a");
            if (alinks != null && alinks.size() > 0) {
                for(Element a : alinks){
                    a.before(new ConfluenceLinkSnippet(a.text().replace(".html", ".xhtml"),subSystemName + " " + a.attr(CommandUtils.HTML_HREF_SELECTOR).replace("_", "/").replace("///","#{'").replace("//","'}").replace(".html",".xhtml")).getBody());
                    a.remove();
                }
            }
            return document.getElementsByTag(CommandUtils.HTML_BODY_SELECTOR).html();
        }
        return null;
    }

    /*
     * TODO: refactor code to remove hardcoded logic and
     * support different content adoptions
     *
     */
    private void parseDocument(Page page, String fileName){
        Document childDocument = loader.loadDocument(fileName);
        Elements select = childDocument.select("div#contentBox");
        if (select != null && select.size() > 0) {
            select.select("div#footer").remove();
            page.setContent(select.html());
        }
    }

}
