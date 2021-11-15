/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package com.exigen.ipb.confluence.client.command;

import com.exigen.ipb.confluence.client.model.Page;
import com.google.common.collect.Lists;

import java.util.List;

/**
 * Utility class to support commands invocation.
 * Contains common methods and constants which
 * could be shared between commands {@link com.exigen.ipb.confluence.client.command.Command}
 *
 * @autor esagan on 2/4/2016.
 * @version 1.0
 */
public final class CommandUtils {

    private static final String URL_HTTP_PREFIX = "http://";
    private static final String URL_HTTPS_PREFIX = "https://";
    private static final String URL_FILE_PREFIX = "file://";
    private static final String URL_FTP_PREFIX = "ftp://";

    public static final String HTML_BODY_SELECTOR = "body";
    public static final String HTML_HREF_SELECTOR = "href";
    public static final String HTML_MEDIA_SELECTOR = "src";
    public static final String HTML_MEDIA_SELECTOR_PREFIX = "abs:";

    public static final String CONFLUENCE_IMAGE_SELECTOR = "ac:image";
    public static final String CONFLUENCE_IMAGE_VALIE_SELECTOR = "ri:value";

    public static final String FILE_EXTENSION_PNG = "png";
    public static final String FILE_EXTENSION_JPG = "jpg";

    private CommandUtils(){}

    /**
     * Method returs 'true' in case provided page content
     * has 'http://','file://' in sequences - so the page
     * should be validated on external references violations.
     *
     * @param page -{@link com.exigen.ipb.confluence.client.command.Command}
     * @return boolean
     */
    public static boolean isPageHasExternalResource(Page page) {
        return page.getContent().contains(URL_HTTP_PREFIX)
                || page.getContent().contains(URL_HTTPS_PREFIX)
                || page.getContent().contains(URL_FTP_PREFIX)
                || page.getContent().contains(URL_FILE_PREFIX);
    }

    /**
     * Returns list of acceptable image file extensions
     *
     * @return - List<String>
     */
    public static List<String> getListOfImgFileExtensions(){
        return Lists.newArrayList(FILE_EXTENSION_PNG, FILE_EXTENSION_JPG );
    }
}
