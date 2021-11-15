/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package com.exigen.ipb.confluence.client.config;

import com.exigen.ipb.confluence.client.impl.ConfluenceClient;

import java.util.List;

/**
 * Configuration interface to provide
 * required for command's {@link com.exigen.ipb.confluence.client.command.Command Command} invocation information.
 *
 * @author esagan on 1/18/2016.
 * @version 1.0
 */
public interface Configuration {

    /**
     * Provides file name which is used
     * as start point for parse information
     * see {@link com.exigen.ipb.confluence.client.parser.JSoupDocumentLoader JSoupDocumentLoader} and
     * {@link com.exigen.ipb.confluence.client.parser.JSoupParser JSoupParser} for usage details
     *
     * @return String
     */
    String getInputRootFile();

    /**
     * Provides path on local disk to the folder
     * with generated documentation
     * see {@link com.exigen.ipb.confluence.client.parser.JSoupDocumentLoader JSoupDocumentLoader} and
     * {@link com.exigen.ipb.confluence.client.parser.JSoupParser JSoupParser} for usage details
     *
     * @return String
     */
    String getInputRootFolder();

    /**
     * Provides file encoding information
     * see {@link com.exigen.ipb.confluence.client.parser.JSoupDocumentLoader JSoupDocumentLoader} and
     * {@link com.exigen.ipb.confluence.client.parser.JSoupParser JSoupParser}
     *
     * @return String
     */
    String getInputFileEncoding();

    /**
     * Provides confluence server URI.
     * see implementations of {@link com.exigen.ipb.confluence.client.command.Command Command} and
     * {@link ConfluenceClient#login()}
     *
     * @return String
     */
    String getConfluenceServerURI();

    /**
     * Provides user name which will be used to
     * login and do parsing and changes confluence content
     * see implementations of {@link com.exigen.ipb.confluence.client.command.Command Command} and
     * {@link ConfluenceClient#login()}
     *
     * @return String
     */
    String getConfluenceUserName();

    /**
     * Provides user password which will be used to
     * login and do parsing and changes confluence content
     * see implementations of {@link com.exigen.ipb.confluence.client.command.Command Command} and
     * {@link ConfluenceClient#login()}
     *
     * @return String
     */
    String getConfluenceUserPassword();

    /**
     * Provides confluence work space name which is
     * used for pages search and generation
     * {@link com.exigen.ipb.confluence.client.impl.ConfluenceClient#getPageBySpaceAndTitle(String, String)}
     *
     * @return String
     */
    String getConfluenceDefaultSpace();

    /**
     * Provides confluence page title to have ability need page and it's childs.
     * {@link com.exigen.ipb.confluence.client.impl.ConfluenceClient#getPageBySpaceAndTitle(String, String)}
     *
     * @return String
     */
    String getConfluenceRootPageTitle();

    /**
     * Provides confluence page title prefix to generate pages with domain/product prefixes and
     * avoid problem described here {@link https://jira.atlassian.com/browse/CONF-2524}
     * see implementations of {@link com.exigen.ipb.confluence.client.command.Command Command}
     * for usage details
     *
     * @return String
     */
    String getConfluencePagePrefix();

    /**
     * Returns link's names to filter out not need references during
     * input source parsing {@link com.exigen.ipb.confluence.client.parser.JSoupDocumentLoader JSoupDocumentLoader} and
     * {@link com.exigen.ipb.confluence.client.parser.JSoupParser JSoupParser} for usage details
     *
     * @return List<String>
     */
    List<String> getInputInclusiveLinks();

    /**
     * Returns folder's names to filter out not need folders to parse input sources for xhtml documents generation
     *
     * @return List<String>
     */
    List<String> getInputSkipDirs();

    /**
     * Returns flag which used in decision generate xhtml documents information of not
     *   true (default value) - not generate;
     *   false - generate
     *
     * @return Boolean
     */
    Boolean getInputPublicDoc();
}
